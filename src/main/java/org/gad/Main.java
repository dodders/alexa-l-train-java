package org.gad;

import org.gad.processor.MTAProcessor;

import java.util.logging.Logger;

public class Main {

    Logger log = Logger.getLogger(this.getClass().getName());

    public static void main(String[] args) throws Exception {
        Main m = new Main();
        m.go();
    }

    private void go() throws Exception {
        log.info("starting...");

        MTAProcessor processor = new MTAProcessor();
        processor.getTrainTimes();
    }

}
