package com.gad.dal;

import com.google.protobuf.CodedInputStream;
import com.google.transit.realtime.GtfsRealtime;
import org.gad.dal.FeedMessageHandler;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class FeedMessageHandlerTest {

    GtfsRealtime.FeedMessage msg;
    Logger log = Logger.getLogger(this.getClass().getName());
    final String stop = "L08N"; //bedford ave.

    @Test
    public void testGetTimes() {
        List<Long> expected = Arrays.asList(new Long[]{Long.valueOf(4), Long.valueOf(11), Long.valueOf(13), Long.valueOf(18), Long.valueOf(24)});
        FeedMessageHandler fmh = new FeedMessageHandler();
        //curr time is 2018/3/10 19:50:00
        LocalDateTime currTime = LocalDateTime.ofEpochSecond(1520711400, 0, ZoneOffset.UTC);
        List<Long> ret = fmh.getArrivalTimes(msg, currTime, stop);
        assertEquals(expected, ret);
    }

    @Before
    public void readFile() throws IOException {
        log.info("starting...");
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("api-coded-output2");
        CodedInputStream cis = CodedInputStream.newInstance(is);
        msg = GtfsRealtime.FeedMessage.parseFrom(cis);
        log.info("api output file loaded.");
    }
}
