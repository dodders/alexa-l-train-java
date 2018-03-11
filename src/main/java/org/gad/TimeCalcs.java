package org.gad;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class TimeCalcs {

    public List<Long> getTimeDiffs(LocalDateTime currTime, List<LocalDateTime> trainTimes) {
        return getDiffs(currTime, trainTimes);
    }

    public List<Long> getTimeDiffs(List<LocalDateTime> trainTimes) {
        LocalDateTime currTime = LocalDateTime.now(ZoneId.of("UTC"));
        return getDiffs(currTime, trainTimes);
    }

    private List<Long> getDiffs(LocalDateTime currTime, List<LocalDateTime> trainTimes) {
        ArrayList<Long> ret = new ArrayList<>();
        for (LocalDateTime tt : trainTimes) {
            long diff = ChronoUnit.MINUTES.between(currTime, tt);
            if (diff >= 0) {
                ret.add(diff);
            }
        }
        return ret;
    }
}
