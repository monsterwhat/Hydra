package com.playdeca.threads.counters;

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

            if (count > Executor.TARGET_NUMBER) {
                Executor.publish(System.currentTimeMillis());
                break;
            }
        }
    }
}
