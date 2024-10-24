package io.reactivestax.configuration;//package io.reactivestax.configuration;
//
//import com.rabbitmq.client.ConnectionFactory;
//
//public class RabbitMQConfig {
//
//    private String host;
//    private int port;
//    private String username;
//    private String password;
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


import com.rabbitmq.client.ConnectionFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RabbitMQConfig {
    private String host;
    private int port;
    private String username;
    private String password;

    public RabbitMQConfig(String propertiesFilePath) throws IOException {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(propertiesFilePath)) {
            properties.load(input);
            this.host = properties.getProperty("rabbitmq.host");
            this.port = Integer.parseInt(properties.getProperty("rabbitmq.port"));
            this.username = properties.getProperty("rabbitmq.username");
            this.password = properties.getProperty("rabbitmq.password");
        }
    }

    public RabbitMQConfig(String localhost, int i, String guest, String guest1) {
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
