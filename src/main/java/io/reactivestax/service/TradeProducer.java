//package io.reactivestax.service;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import io.reactivestax.model.Trading;
//
//import java.util.List;
//
//public class TradeProducer {
//
//    private final static String QUEUE_NAME = "tradingQueue";
//
//    public void sendToRabbitMQ(List<Trading> list) {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost"); // Change if RabbitMQ is hosted elsewhere
//        try (Connection connection = factory.newConnection();
//             Channel channel = connection.createChannel()) {
//
//            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            for (Trading trading : list) {
//                String jsonMessage = objectMapper.writeValueAsString(trading);
//                channel.basicPublish("", QUEUE_NAME, null, jsonMessage.getBytes("UTF-8"));
//                System.out.println("Sent: " + jsonMessage);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}


package io.reactivestax.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import io.reactivestax.configuration.RabbitMQConfig;
import io.reactivestax.model.Trading;

import java.util.List;

public class TradeProducer {

    private final static String QUEUE_NAME = "tradingQueue";
    public RabbitMQConfig rabbitMQConfig;

    public TradeProducer(RabbitMQConfig rabbitMQConfig) {
        this.rabbitMQConfig = rabbitMQConfig;
    }

    public void sendToRabbitMQ(List<Trading> list) {
        try (Connection connection = rabbitMQConfig.createConnectionFactory().newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            ObjectMapper objectMapper = new ObjectMapper();

            for (Trading trading : list) {
                String jsonMessage = objectMapper.writeValueAsString(trading);
                channel.basicPublish("", QUEUE_NAME, null, jsonMessage.getBytes("UTF-8"));
                System.out.println("Sent: " + jsonMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

