package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.example.model.Trading;

import java.util.List;

public class TradeProducer {

    private final static String QUEUE_NAME = "tradingQueue";

    public void sendToRabbitMQ(List<Trading> list) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // Change if RabbitMQ is hosted elsewhere
        try (Connection connection = factory.newConnection();
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