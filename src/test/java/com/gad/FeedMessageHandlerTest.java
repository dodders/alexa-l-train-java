package com.gad;

import com.google.protobuf.CodedInputStream;
import com.google.transit.realtime.GtfsRealtime;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class FeedMessageHandlerTest {

    GtfsRealtime.FeedMessage msg;
    Logger log = Logger.getLogger(this.getClass().getName());

    @Test
    public void testGetTimes() {
        for (GtfsRealtime.FeedEntity entity : msg.getEntityList()) {
            if (entity.hasTripUpdate()) {
                System.out.println(entity.getTripUpdate());
            }
        }
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
