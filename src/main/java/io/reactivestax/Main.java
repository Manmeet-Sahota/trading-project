package io.reactivestax;

import io.reactivestax.configuration.RabbitMQConfig;
import io.reactivestax.service.ChunkGenerator;
import io.reactivestax.service.TradeProducer;

import java.sql.SQLException;

import io.reactivestax.configuration.RabbitMQConfig;
import io.reactivestax.service.TradeProducer;

public class Main {
    public static void main(String[] args) {
        // Create RabbitMQ configuration
        RabbitMQConfig rabbitMQConfig = new RabbitMQConfig("localhost", 5672, "guest", "guest");

        // Create the producer
        TradeProducer tradeProducer = new TradeProducer(rabbitMQConfig);

        // Call sendToRabbitMQ with a list of Trading objects
        // List<Trading> tradingList = ...; // Initialize your trading list
        // tradeProducer.sendToRabbitMQ(tradingList);
    }
}
