package io.reactivestax.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMqUtil {

    private final static String  Queue_Name="";
    public static Channel getChannel() throws Exception {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection=factory.newConnection();
        return connection.createChannel();
    }
    public static String getQueueName(){
        return Queue_Name;
    }


}
