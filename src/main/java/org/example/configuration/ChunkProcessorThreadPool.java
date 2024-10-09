package org.example.configuration;

import org.example.service.ReadQueueData;
import org.example.service.ReadThread;
import org.example.storage.DataQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ChunkProcessorThreadPool {
    private ExecutorService es;
    private ExecutorService executor;

    public void createThreadPool(int poolCount) {
        es = Executors.newFixedThreadPool(poolCount);
        for (int i = 1; i <= poolCount; i++) {
            ReadThread rt = new ReadThread(i);
            es.execute(rt);
        }

        try {
            if (!es.awaitTermination(10, TimeUnit.SECONDS)) {
                System.out.println("I waited ");
                es.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        createThreadPoolToRetriveData();
    }

    public void createThreadPoolToRetriveData() {
        ConfigLoader configLoader=new ConfigLoader("application.properties");
       String poolSize= configLoader.getProperty("chunkProcessorThreadPool");
        executor = Executors.newFixedThreadPool(1);
        System.out.println("-------::::::> " + DataQueue.getQueue1());
        executor.execute(new ReadQueueData(DataQueue.getQueue1()));
        executor.execute(new ReadQueueData(DataQueue.getQueue2()));
        executor.execute(new ReadQueueData(DataQueue.getQueue3()));
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
