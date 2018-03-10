package org.gad;

import com.google.transit.realtime.GtfsRealtime;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class FeedMessageHandler {

    //get next 5 arrival times for the specified stop in posix time.
    public List<LocalDateTime> getArrivalTimes(GtfsRealtime.FeedMessage msg, String stop) {

        List<LocalDateTime> ret = new ArrayList<>();
        for (GtfsRealtime.FeedEntity entity : msg.getEntityList()) {
            if (entity.hasTripUpdate()) {
                List<GtfsRealtime.TripUpdate.StopTimeUpdate> stopTimes = entity.getTripUpdate().getStopTimeUpdateList();
                for (GtfsRealtime.TripUpdate.StopTimeUpdate stopTime : stopTimes) {
                    if (stopTime.getStopId().equalsIgnoreCase(stop)) {
                        LocalDateTime arrivalTime = LocalDateTime.ofEpochSecond(stopTime.getArrival().getTime(), 0, ZoneOffset.of("-5"));
                        ret.add(arrivalTime);
                        if (ret.size() == 5) {
                            return ret;
                        }
                    }
                }
            }
        }
        return ret;
    }
}
