//package io.reactivestax.service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.rabbitmq.client.*;
//import io.reactivestax.model.Trading;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class TradingConsumer {
//    private final static String QUEUE_NAME = "tradingQueue";
//    public static void main(String[] argv) throws Exception {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
//        System.out.println("Waiting for messages...");
//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
//            ObjectMapper objectMapper = new ObjectMapper();
//            Trading trading = objectMapper.readValue(message, Trading.class);
//            processTrading(trading);
//        };
//        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
//    }
//    private static void processTrading(Trading trading) {
//        System.out.println("Received: " + trading);
//
//        List<Trading> list=new ArrayList<>();
//        list.add(trading);
//
//
////        ReteriveDataFromQueue reteriveDataFromQueue=new ReteriveDataFromQueue();
////        reteriveDataFromQueue.insertIntoPosition(list);
//
//        // You can add the logic to handle the trading data here
//    }
//
//}
