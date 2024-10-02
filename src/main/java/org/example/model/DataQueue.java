package org.example.model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DataQueue {
    public static final BlockingQueue<String> queue1 = new LinkedBlockingQueue<>();
    public static final BlockingQueue<String> queue2 = new LinkedBlockingQueue<>();
    public static final BlockingQueue<String> queue3 = new LinkedBlockingQueue<>();

}