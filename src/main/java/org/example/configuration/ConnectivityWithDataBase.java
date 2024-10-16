package org.example.configuration;

import org.example.model.Trading;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConnectivityWithDataBase {
    private Connection connection;

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Trading", "root", "password123");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public List<Trading> fetchTradeIds(List<String> tradeIds) throws SQLException {
        connection = getConnection();
        ResultSet resultSet = null;
        String query = "select trade_id,payload from trade_payload where trade_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        List<Trading> list= new ArrayList<>();
        for (String tradeId : tradeIds) {
            preparedStatement.setString(1, tradeId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Trading trading = new Trading();
                trading.setTradeId(resultSet.getString("trade_id"));
                trading.setPayload(resultSet.getString("payload"));
                list.add(trading);
            }
        }

        return list;
    }
}

