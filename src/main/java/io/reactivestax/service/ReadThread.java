//package io.reactivestax.service;
//
//import io.reactivestax.configuration.RabbitMQConfig;
//import io.reactivestax.entity.SecurityReference;
//import io.reactivestax.model.TradePayLoad;
//import io.reactivestax.model.Trading;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.*;
//
//public class ReadThread implements Runnable {
//    public ReadThread(int i) {
//        this.i = i;
//    }
//
//    private int i;
//    String line1 = null;
//
//    @Override
//    public void run() {
//        BufferedReader br = null;
//        List<Trading> list = new ArrayList<>();
//        try {
//            br = new BufferedReader(new FileReader("/Users/Manmeet.Singh/Student_Work/projects/trading-project/src/main/resources/trade_" + i + ".csv"));
//            while ((line1 = br.readLine()) != null) {
//                String[] splitLine = line1.split(",");
//                Trading trading = new Trading();
//                trading.setTradeId(splitLine[0]);
//                trading.setTransactionTime(splitLine[1]);
//                trading.setAccountNumber(splitLine[2]);
//                trading.setCusip(splitLine[3]);
//                trading.setActivity(splitLine[4]);
//                trading.setQuantity(splitLine[5]);
//                trading.setPrice(splitLine[6]);
//                trading.setPayload(line1);
//                list.add(trading);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                br.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        TradePayLoad tradePayLoad = new TradePayLoad();
//        tradePayLoad.insertTradePayload(list);
//
//        SecurityReference securityReference = new SecurityReference();
//        securityReference.insertSecurityReference(list);
//
//        ReteriveDataFromQueue reteriveDataFromQueue = new ReteriveDataFromQueue();
//        reteriveDataFromQueue.insertIntoJournalEntry(list);
//
//        ReteriveDataFromQueue reteriveDataFromQueue1 = new ReteriveDataFromQueue();
//        reteriveDataFromQueue1.insertIntoJournalEntry(list);
//        ReteriveDataFromQueue reteriveDataFromQueue2 = new ReteriveDataFromQueue();
//        reteriveDataFromQueue2.insertIntoPosition(list);
//
//        TradeProducer tradeProducer = new TradeProducer(RabbitMQConfig);
//        tradeProducer.sendToRabbitMQ(list);
//
//
//    }
//}
//

package io.reactivestax.service;

import io.reactivestax.configuration.RabbitMQConfig;
import io.reactivestax.entity.SecurityReference;
import io.reactivestax.model.TradePayLoad;
import io.reactivestax.model.Trading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReadThread implements Runnable {
    private int i;
    String line1 = null;

    public ReadThread(int i) {
        this.i = i;
    }

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
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        TradePayLoad tradePayLoad = new TradePayLoad();
        tradePayLoad.insertTradePayload(list);

        SecurityReference securityReference = new SecurityReference();
        securityReference.insertSecurityReference(list);

        ReteriveDataFromQueue reteriveDataFromQueue = new ReteriveDataFromQueue();
        reteriveDataFromQueue.insertIntoJournalEntry(list);

        // Create RabbitMQConfig instance
        RabbitMQConfig rabbitMQConfig = new RabbitMQConfig("localhost", 5672, "guest", "guest");

        // Instantiate TradeProducer with the RabbitMQConfig
        TradeProducer tradeProducer = new TradeProducer(rabbitMQConfig);
        tradeProducer.sendToRabbitMQ(list);
    }
}
