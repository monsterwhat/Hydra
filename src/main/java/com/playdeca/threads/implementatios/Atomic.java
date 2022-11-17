package com.playdeca.threads.implementatios;

import java.util.concurrent.atomic.AtomicLong;

import com.playdeca.threads.counters.Counter;

public class Atomic implements Counter {

    private final AtomicLong atomic = new AtomicLong();

    @Override
    public long getCounter() {
        return atomic.get();
    }

    @Override
    public void increment() {
        atomic.incrementAndGet();
    }
}
