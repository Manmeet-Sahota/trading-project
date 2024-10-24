package io.reactivestax.configuration;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQConfig {

    private String host;
    private int port;
    private String username;
    private String password;

    public RabbitMQConfig(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public ConnectionFactory createConnectionFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        return factory;
    }
}



//package io.reactivestax.configuration;
//
//import com.rabbitmq.client.ConnectionFactory;
//
//public class RabbitMQConfig {
//
//    private final String host;
//    private final int port;
//    private final String username;
//    private final String password;
//
//    public RabbitMQConfig(String host, int port, String username, String password) {
//        this.host = host;
//        this.port = port;
//        this.username = username;
//        this.password = password;
//    }
//
//    public ConnectionFactory createConnectionFactory() {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost(host);
//        factory.setPort(port);
//        factory.setUsername(username);
//        factory.setPassword(password);
//        return factory;
//    }
//}
