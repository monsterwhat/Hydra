package com.playdeca.threads.implementatios;

import java.util.concurrent.atomic.LongAdder;

import com.playdeca.threads.counters.Counter;

public class Adder implements Counter {

    private final LongAdder adder = new LongAdder();

    @Override
    public long getCounter() {
        return adder.longValue();
    }

    @Override
    public void increment() {
        adder.increment();
    }
}
