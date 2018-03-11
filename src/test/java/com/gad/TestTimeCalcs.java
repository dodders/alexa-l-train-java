package com.gad;

import org.gad.TimeCalcs;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class TestTimeCalcs {

    Logger log = Logger.getLogger(this.getClass().getName());
    TimeCalcs calcs = new TimeCalcs();

    @Test
    public void testCalcs() {
        //3/10/2018 2:50pm UTC.
        LocalDateTime ldt = LocalDateTime.ofEpochSecond(1520711400, 0, ZoneOffset.UTC);
        List<LocalDateTime> trainTimes = TestTrainTimes.getTimes();
        List<Long> diffs = calcs.getTimeDiffs(ldt, trainTimes);
        log.info("curr time:" + ldt.toString());
        log.info("train times:" + trainTimes.toString());
        log.info("diffs:" + diffs.toString());
        ArrayList<Long> expected = new ArrayList<>();
        expected.add((long) 4);
        expected.add((long) 11);
        expected.add((long) 13);
        expected.add((long) 18);
        expected.add((long) 24);
        assertEquals(expected, diffs);
    }
}
