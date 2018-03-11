package com.gad;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class TestTrainTimes {

    public static List<LocalDateTime> getTimes() {
        ArrayList<LocalDateTime> ret = new ArrayList<>();
        ret.add(LocalDateTime.ofEpochSecond(1520711657, 0, ZoneOffset.UTC));
        ret.add(LocalDateTime.ofEpochSecond(1520712104, 0, ZoneOffset.UTC));
        ret.add(LocalDateTime.ofEpochSecond(1520712198, 0, ZoneOffset.UTC));
        ret.add(LocalDateTime.ofEpochSecond(1520712537, 0, ZoneOffset.UTC));
        ret.add(LocalDateTime.ofEpochSecond(1520712877, 0, ZoneOffset.UTC));
        return ret;
    }
}
