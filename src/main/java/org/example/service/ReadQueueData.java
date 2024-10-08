package org.example.service;

import org.example.storage.DataQueue;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ReadQueueData implements Runnable {
    private final BlockingQueue<String> blockingQueue;

    public ReadQueueData(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        ReteriveDataFromQueue reteriveDataFromQueue = new ReteriveDataFromQueue();
        try {
            reteriveDataFromQueue.readQueueData(blockingQueue);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
