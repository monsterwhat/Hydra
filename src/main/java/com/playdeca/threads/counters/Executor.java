package com.playdeca.threads.counters;

import com.playdeca.threads.implementatios.Adder;
import com.playdeca.threads.implementatios.Atomic;
import com.playdeca.threads.implementatios.Dirty;
import com.playdeca.threads.implementatios.RWLock;
import com.playdeca.threads.implementatios.Synchronized;
import com.playdeca.threads.implementatios.Volatile;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author alvaro@playdeca.com
 */
public class Executor {

    public static long TARGET_NUMBER = 100000000l;
    public static int THREADS = 10;
    public static int ROUNDS = 10;
    private static String COUNTER; //   Counters.RWLOCK.toString();

    private static ExecutorService es;
    private static int round;
    private static long start;

    private static Boolean[] rounds;

    private static enum Counters {
        DIRTY, //About 25s
        VOLATILE, //Super Slow 2:40
        SYNCHRONIZED, //42s
        RWLOCK, //26s +/- 2s
        ATOMIC, //50s
        ADDER //18s
    }

    public void Execute(String type, int Threads, int Rounds, long TargetNumber) {

        switch (type.toUpperCase()) {
            case "DIRTY":
                COUNTER = Counters.DIRTY.toString();
                break;
            case "VOLATILE":
                COUNTER = Counters.VOLATILE.toString();
                break;
            case "SYNCHRONIZED":
                COUNTER = Counters.SYNCHRONIZED.toString();
                break;
            case "RWLOCK":
                COUNTER = Counters.RWLOCK.toString();
                break;
            case "ATOMIC":
                COUNTER = Counters.ATOMIC.toString();
                break;
            case "ADDER":
                COUNTER = Counters.ADDER.toString();
                break;
            default:
                System.out.println("Error en tipo de implementacion");
                throw new AssertionError();
        }


        THREADS = Threads;

        ROUNDS = Rounds;

        TARGET_NUMBER = TargetNumber;

        rounds = new Boolean[ROUNDS];

        System.out.println("Using " + COUNTER + ". threads: " + THREADS + ". rounds: " + ROUNDS
                + ". Target: " + TARGET_NUMBER);

        for (round = 0; round < ROUNDS; round++) {
            rounds[round] = Boolean.FALSE;

            Counter counter = getCounter();

            es = Executors.newFixedThreadPool(THREADS);

            start = System.currentTimeMillis();

            for (int j = 0; j < THREADS; j += 2) {
                es.execute(new Reader(counter));
                es.execute(new Writer(counter));
            }

            try {
                es.awaitTermination(10, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static Counter getCounter() {
        Counters counterTypeEnum = Counters.valueOf(COUNTER);

        switch (counterTypeEnum) {
            case ADDER:
                return new Adder();
            case ATOMIC:
                return new Atomic();
            case DIRTY:
                return new Dirty();
            case RWLOCK:
                return new RWLock();
            case SYNCHRONIZED:
                return new Synchronized();
            case VOLATILE:
                return new Volatile();
        }

        return null;
    }

    public static void publish(long end) {
        synchronized (rounds[round]) {
            if (Objects.equals(rounds[round], Boolean.FALSE)) {
                System.out.println(end - start + "ms");

                rounds[round] = Boolean.TRUE;

                es.shutdownNow();
            }
        }
    }
}
