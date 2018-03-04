package org.gad;

import com.google.transit.realtime.GtfsRealtime;

import java.io.IOException;
import java.net.URL;

public class Main {

    final String address = "http://datamine.mta.info/mta_esi.php?key=9a707b925e75f2830938dba89a0c62f2&feed_id=2";

    public static void main(String[] args) throws IOException {
        Main me = new Main();
        me.go();
    }

    private void go() throws IOException {
        System.out.println("running...");
        URL url = new URL(address);
        GtfsRealtime.FeedMessage feed = GtfsRealtime.FeedMessage.parseFrom(url.openStream());
        for (GtfsRealtime.FeedEntity entity : feed.getEntityList()) {
            if (entity.hasTripUpdate()) {
                System.out.println(entity.getTripUpdate());
            }
        }
    }
}
