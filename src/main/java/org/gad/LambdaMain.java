package org.gad;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.transit.realtime.GtfsRealtime;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class LambdaMain {

    DataProvider dataProvider = new DataProvider();
    FeedMessageHandler fmh = new FeedMessageHandler();
    final private String bedfordStop = "L08N";

    public String mainHandler(Object event, Context ctx) {
        LambdaLogger logger = ctx.getLogger();
        GetEnvVars envVars = new GetEnvVars(logger);
        String mtaKey = envVars.getVar("mtakey");
        LocalDateTime currTime = LocalDateTime.now(ZoneId.of("UTC"));
        logger.log("mainHandler invoked with: " + event.toString());
        if (mtaKey != null) {
            logger.log("mta key found!");
        } else {
            return "Could not find MTA key.";
        }
        try {
            GtfsRealtime.FeedMessage feed = dataProvider.getFeedData(mtaKey);
            List<Long> diffs = fmh.getArrivalTimes(feed, currTime, bedfordStop, ctx);
            StringBuffer ret = new StringBuffer();
            if (diffs.size() < 1) {
                ret.append("Did not find any L train arrival times.");
            } else {
                ret.append("The next L trains at Bedford Avenue will leave in ");
                for (int i = 0; i < diffs.size() - 1; i++) {
                    if (i > 0) {
                        ret.append(" ,");
                    }
                    ret.append(diffs.get(i).toString());
                }
                ret.append(" and ").append(diffs.get(diffs.size() - 1));
                ret.append(" minutes.");
            }
            logger.log(ret.toString());
            return ret.toString();
        } catch (MalformedURLException e) {
            logger.log("invalid URL: " + e.getMessage());
            return "Sorry I couldn't connect to the MTA";
        } catch (IOException e) {
            logger.log("error parsing MTA data:" + e.getMessage());
            return "Couldn't parse MTA data";
        }
    }
}
