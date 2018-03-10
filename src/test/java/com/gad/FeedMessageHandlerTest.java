package com.gad;

import com.google.protobuf.CodedInputStream;
import com.google.transit.realtime.GtfsRealtime;
import org.gad.FeedMessageHandler;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class FeedMessageHandlerTest {

    GtfsRealtime.FeedMessage msg;
    Logger log = Logger.getLogger(this.getClass().getName());
    final String stop = "L08N"; //bedford ave.

    @Test
    public void testGetTimes() {
        List<LocalDateTime> expected = TestTrainTimes.getTimes();
        FeedMessageHandler fmh = new FeedMessageHandler();
        List<LocalDateTime> ret = fmh.getArrivalTimes(msg, stop);
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
