package org.example.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QueueConnectivity {
    private Connection connection;
    ConfigLoader configLoader=new ConfigLoader("application.properties");
    public Connection getConnection(){
        if (connection!=null){
            try{
                String dbUrl = configLoader.getProperty("db.url");
                String dbUsername = configLoader.getProperty("db.username");
                String dbPassword = configLoader.getProperty("db.password");
                connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
                System.out.println("queue Connection done ");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
