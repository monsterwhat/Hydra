package com.playdeca.threads.counters;

import com.playdeca.threads.app.mainFrame;

public class Reader implements Runnable {

    private final Counter counter;

    public Reader(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.interrupted()) {
                break;
            }

            long count = counter.getCounter();

            if (count > mainFrame.TARGET_NUMBER) {
                mainFrame.publish(System.currentTimeMillis());
                break;
            }
        }
    }
}
