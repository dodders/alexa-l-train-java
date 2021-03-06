package org.gad.dal;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.transit.realtime.GtfsRealtime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class FeedMessageHandler {

    Logger log = LogManager.getLogger(this.getClass());

    //get next 5 arrival times for the specified stop in posix time.
    public List<Long> getArrivalTimes(GtfsRealtime.FeedMessage msg, LocalDateTime currTime, String stop) {
        List<Long> ret = new ArrayList<>();

        for (GtfsRealtime.FeedEntity entity : msg.getEntityList()) {
            if (entity.hasTripUpdate()) {
                List<GtfsRealtime.TripUpdate.StopTimeUpdate> stopTimes = entity.getTripUpdate().getStopTimeUpdateList();
                for (GtfsRealtime.TripUpdate.StopTimeUpdate stopTime : stopTimes) {
                    if (stopTime.getStopId().equalsIgnoreCase(stop)) {
                        LocalDateTime arrivalTime = LocalDateTime.ofEpochSecond(stopTime.getArrival().getTime(), 0, ZoneOffset.UTC);
                        long diff = ChronoUnit.MINUTES.between(currTime, arrivalTime);
                        log.info("found time:" + currTime.toString() + " with diff:" + Long.toString(diff));
                        if (diff >= 0) {
                            ret.add(diff);
                            if (ret.size() == 5) {
                                return ret;
                            }
                        }
                    }
                }
            }
        }
        return ret;
    }
}
