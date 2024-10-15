package org;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class RabbitMQUtil {
    private final static String QUEUE_NAME = "hibernate_queue";

    public static Channel getChannel() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        return connection.createChannel();
    }

    public static String getQueueName() {
        return QUEUE_NAME;
    }
}