package org.gad.util;

import com.google.protobuf.CodedOutputStream;
import com.google.transit.realtime.GtfsRealtime;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.*;

public class ExtractSchedule {

    final String address = "http://datamine.mta.info/mta_esi.php?feed_id=2&key=";

    public static void main(String[] args) throws IOException {
        ExtractSchedule me = new ExtractSchedule();
        me.go();
    }

    private void go() throws IOException {
        System.out.println("running...");
        GetEnvVars vars = new GetEnvVars();
        String key = vars.getVar("mtakey");
        URL url = new URL(address + key);
        InputStream is = url.openStream();
        GtfsRealtime.FeedMessage feed = GtfsRealtime.FeedMessage.parseFrom(is);
        is.close();
        byte[] flatArray = new byte[feed.getSerializedSize()];
        CodedOutputStream cos = CodedOutputStream.newInstance(flatArray);
        feed.writeTo(cos);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(flatArray, 0, flatArray.length);
        OutputStream os = new FileOutputStream(new File("api-coded-output2"));
        bos.writeTo(os);
        Path p = Paths.get("api-output2.txt");
        BufferedWriter bw = Files.newBufferedWriter(p, Charset.defaultCharset(), StandardOpenOption.CREATE_NEW, StandardOpenOption.TRUNCATE_EXISTING);
        for (GtfsRealtime.FeedEntity entity : feed.getEntityList()) {
            if (entity.hasTripUpdate()) {
                bw.write(entity.getTripUpdate().toString());
                bw.newLine();
                //System.out.println(entity.getTripUpdate());
            }
        }
        bw.flush();
        bw.close();
    }
}
