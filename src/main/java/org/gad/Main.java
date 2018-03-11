package org.gad;

import java.util.logging.Logger;

public class Main {

    Logger log = Logger.getLogger(this.getClass().getName());

    public static void main(String[] args) {
        Main m = new Main();
        m.go();
    }

    private void go() {
        log.info("starting...");

        LambdaMain lambda = new LambdaMain();
        lambda.mainHandler("event", new MyContext());
    }

}
