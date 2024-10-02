package org.example.configuration;

import org.example.model.DataQueue;
import org.example.storage.DataStorage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadThread implements Runnable {
    public ReadThread(int i) {
        this.i = i;
    }

    private int i;
    String line1 = null;

    @Override
    public void run() {
        BufferedReader br = null;
        System.out.println(Thread.currentThread().getName() + ".   i am in thread");

        List<Trading> list = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader("/Users/Manmeet.Singh/Student_Work/projects/trading/trades_" + i + ".csv"));
            while ((line1 = br.readLine()) != null) {
                String[] splitLine = line1.split(",");
                Trading trading = new Trading();
                trading.setTradeId(splitLine[0]);
                trading.setTransactionTime(splitLine[1]);
                trading.setAccountNumber(splitLine[2]);
                trading.setCusip(splitLine[3]);
                trading.setActivity(splitLine[4]);
                trading.setQuantity(splitLine[5]);
                trading.setPrice(splitLine[6]);
                list.add(trading);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + ".    ---  > " + list.size());
        saveDataIntoMap(list);
        TradePayLoad tradePayLoad = new TradePayLoad();
        System.out.println(Thread.currentThread().getName() + "########## > " + list.size());
        tradePayLoad.insertTradePayload(list);
        getQueueData();

    }


    public void saveDataIntoMap(List<Trading> list) {
        DataStorage ds = new DataStorage();
        Map<String, String> dsMap = ds.getMap();
        for (Trading td : list) {
            String value = String.valueOf((int) (Math.random() * 3) + 1);
            dsMap.put(td.getAccountNumber(), value);
            if (value.equals("1")) {
                DataQueue.queue1.add(td.getAccountNumber());
            } else if (value.equals("2")) {
                DataQueue.queue2.add(td.getAccountNumber());
            } else {
                DataQueue.queue3.add(td.getAccountNumber());
            }

        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("----->" + dsMap.size());
    }
}

