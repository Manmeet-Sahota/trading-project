package org.example.service;

import com.google.gson.Gson;
import org.example.storage.DataQueue;
import org.example.model.Trading;
import org.example.storage.DataStorage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.BlockingQueue;

public class ReadThread implements Runnable {
    public ReadThread(int i) {
        this.i = i;
    }

    private int i;
    String line1 = null;

    @Override
    public void run() {
        BufferedReader br = null;
        List<Trading> list = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader("/Users/Manmeet.Singh/Student_Work/projects/trading-project/src/main/resources/trade_" + i + ".csv"));
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
                trading.setPayload(line1);
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
        TradePayLoad tradePayLoad = new TradePayLoad();
        tradePayLoad.insertTradePayload(list);
        List<BlockingQueue<String>> blockingQueues = null;
        try {
            blockingQueues = saveDataIntoMap(list);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        SecurityReference securityReference = new SecurityReference();
        securityReference.insertSecurityReference(list);
    }


    public List<BlockingQueue<String>> saveDataIntoMap(List<Trading> list) throws InterruptedException {
        DataStorage ds = new DataStorage();
        Map<String, String> dsMap = ds.getMap();
        for (Trading td : list) {
            String value = String.valueOf((int) (Math.random() * 3) + 1);
            dsMap.put(td.getTradeId(), value);
            if (value.equals("1")) {
                DataQueue.setQueue1(td.getTradeId());
            } else if (value.equals("2")) {
                DataQueue.setQueue2(td.getTradeId());
            } else {
                DataQueue.setQueue3(td.getTradeId());
            }
        }
        return Arrays.asList(DataQueue.getQueue1(), DataQueue.getQueue2(), DataQueue.getQueue3());
    }
}

