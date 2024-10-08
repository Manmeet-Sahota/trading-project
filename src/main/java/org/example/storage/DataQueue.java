package org.example.storage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DataQueue {
    private static BlockingQueue<String> queue1;
    private static BlockingQueue<String> queue2;
    private static BlockingQueue<String> queue3;

    static {
        queue1 = new LinkedBlockingQueue<>();
        queue2 = new LinkedBlockingQueue<>();
        queue3 = new LinkedBlockingQueue<>();
    }

    public static BlockingQueue<String> getQueue1() {
        return queue1;
    }

    public static void setQueue1(String value) throws InterruptedException {

        DataQueue.queue1.put(value);

    }

    public static BlockingQueue<String> getQueue2() {
        return queue2;
    }

    public static void setQueue2(String value) throws InterruptedException {
        DataQueue.queue2.put(value);

    }

    public static BlockingQueue<String> getQueue3() {
        return queue3;
    }

    public static void setQueue3(String value) throws InterruptedException {
        DataQueue.queue3.put(value);

    }
}