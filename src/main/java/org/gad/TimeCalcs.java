package org.gad;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class TimeCalcs {

    public List<Long> getTimeDiffs(LocalDateTime currTime, List<LocalDateTime> trainTimes) {
        ArrayList<Long> ret = new ArrayList<>();
        for (LocalDateTime tt : trainTimes) {
            long diff = ChronoUnit.MINUTES.between(currTime, tt);
            ret.add(diff);
        }
        return ret;
    }
}
