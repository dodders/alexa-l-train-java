package com.gad.processor;

import org.gad.processor.MTAProcessor;

public class MTAProcessorTest {

    public static void main(String[] args) throws Exception {
        MTAProcessorTest me = new MTAProcessorTest();
        me.go();
    }

    private void go() throws Exception {
        MTAProcessor processor = new MTAProcessor();
        System.out.println(processor.getTrainTimes());
    }

}
