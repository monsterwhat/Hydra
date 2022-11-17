package com.playdeca.threads.implementatios;

import com.playdeca.threads.counters.Counter;

public class Dirty implements Counter {

    private long counter;

    @Override
    public long getCounter() {
        return counter;
    }

    @Override
    public void increment() {
        ++counter;
    }
}
