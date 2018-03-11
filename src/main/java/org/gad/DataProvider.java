package org.gad;

import com.google.transit.realtime.GtfsRealtime;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DataProvider {

    final private String address = "http://datamine.mta.info/mta_esi.php?key=";

    public GtfsRealtime.FeedMessage getFeedData(String mtaKey) throws IOException {
        URL url = new URL(address + mtaKey + "&feed_id=2");
        GtfsRealtime.FeedMessage feed = GtfsRealtime.FeedMessage.parseFrom(url.openStream());
        return feed;
    }

}
