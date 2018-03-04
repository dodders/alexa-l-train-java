package org.gad;

import com.google.protobuf.CodedOutputStream;
import com.google.transit.realtime.GtfsRealtime;

import java.io.*;
import java.net.URL;
import java.nio.file.Paths;

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
//        File f = new File("api-coded-output");
//        CodedOutputStream cos = CodedOutputStream.newInstance(new FileOutputStream(f));
//        feed.writeTo(cos);
        byte[] flatArray = new byte[feed.getSerializedSize()];
        CodedOutputStream cos = CodedOutputStream.newInstance(flatArray);
        feed.writeTo(cos);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(flatArray, 0, flatArray.length);
        OutputStream os = new FileOutputStream(new File("api-coded-output2"));
        bos.writeTo(os);
//        for (GtfsRealtime.FeedEntity entity : feed.getEntityList()) {
//            if (entity.hasTripUpdate()) {
//                System.out.println(entity.getTripUpdate());
//            }
//        }
    }
}
