package org.example.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectivityWithDataBase {
    private Connection connection = null;

    public  Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Trading", "root", "password123");

                System.out.println("connected to dataBase");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
