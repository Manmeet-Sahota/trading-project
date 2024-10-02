package org.example.configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolConfiguration {
    private ExecutorService es;
    public void createThreadPool(int poolCount) {

         es = Executors.newFixedThreadPool(poolCount);
        for (int i = 1; i <= poolCount; i++) {
            ReadThread rt = new ReadThread(i);
            es.execute(rt);
        }
        System.out.println();
        es.shutdown();
        for (int i=1;i<=5;i++){
            System.out.println(Thread.currentThread().getName()+i);
        }
    }


}
