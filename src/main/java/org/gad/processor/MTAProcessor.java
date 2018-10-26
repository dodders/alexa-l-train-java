package org.gad.processor;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.transit.realtime.GtfsRealtime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gad.dal.DataProvider;
import org.gad.dal.FeedMessageHandler;
import org.gad.util.GetEnvVars;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class MTAProcessor {

    DataProvider dataProvider = new DataProvider();
    FeedMessageHandler fmh = new FeedMessageHandler();
    final private String bedfordStop = "L08N";
    Logger log = LogManager.getLogger(this.getClass());
    GetEnvVars envVars = new GetEnvVars();

    public String getTrainTimes() {
        LocalDateTime currTime = LocalDateTime.now(ZoneId.of("UTC"));
        String mtaKey = envVars.getVar("mtakey");
        if (mtaKey == null) {
            return "Sorry, I couldn't log into the MTA.";
        }
        try {
            GtfsRealtime.FeedMessage feed = dataProvider.getFeedData(mtaKey);
            List<Long> diffs = fmh.getArrivalTimes(feed, currTime, bedfordStop);
            StringBuffer ret = new StringBuffer();
            if (diffs.size() < 1) {
                ret.append("Sorry I did not find any L train times.");
            } else {
                ret.append("The next L trains leave in ");
                for (int i = 0; i < diffs.size() - 1; i++) {
                    if (i > 0) {
                        ret.append(" ,");
                    }
                    ret.append(diffs.get(i).toString());
                }
                ret.append(" and ").append(diffs.get(diffs.size() - 1));
                ret.append(" minutes.");
            }
            log.info(ret.toString());
            return ret.toString();
        } catch (MalformedURLException e) {
            log.info("invalid URL: " + e.getMessage());
            return "Sorry, I can't connect to the MTA";
        } catch (IOException e) {
            log.info("error parsing MTA data:" + e.getMessage());
            return "Sorry, I don't understand the MTA data";
        }
    }
}
