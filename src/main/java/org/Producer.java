package org;

import com.rabbitmq.client.Channel;

public class Producer {
    public void sendMessage(String message) throws Exception {
        Channel channel = RabbitMQUtil.getChannel();
        channel.queueDeclare(RabbitMQUtil.getQueueName(), false, false, false, null);
        channel.basicPublish("", RabbitMQUtil.getQueueName(), null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
    }
}