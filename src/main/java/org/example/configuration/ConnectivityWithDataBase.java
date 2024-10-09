package org.example.configuration;


import org.example.model.Trading;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ConnectivityWithDataBase {
    private Connection connection;

    private ConfigLoader configLoader=new ConfigLoader( "application.properties");
    public Connection getConnection() {
        if (connection == null) {
            try {
                String dbUrl = configLoader.getProperty("db.url");
                String dbUsername = configLoader.getProperty("db.username");
                String dbPassword = configLoader.getProperty("db.password");
                connection = DriverManager.getConnection( dbUrl,dbUsername,dbPassword);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    //
    public List<Trading> fetchTradeIds(List<String> tradeIds) throws SQLException {
        connection = getConnection();
        ResultSet resultSet = null;
        String query = "select trade_id,payload from trade_payload where trade_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (String tradeId : tradeIds) {
            preparedStatement.setString(1, tradeId);
            resultSet = preparedStatement.executeQuery();
        }
        List<Trading> list = new ArrayList<>();
        while (resultSet.next()) {
            Trading trading = new Trading();
            trading.setTradeId(resultSet.getString("trade_id"));
            trading.setPayload(resultSet.getString("payload"));
            list.add(trading);
            System.out.println(trading.toString());
        }
        return list;
    }

}


