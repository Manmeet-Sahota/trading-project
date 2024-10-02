package org.example.configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class TradePayLoad {


    public void insertTradePayload(List<Trading> tradeList) {
        String sql = "Insert into trade_payload(trade_id) VALUES(?)";
        ConnectivityWithDataBase connectivityWithDataBase=new ConnectivityWithDataBase();
        try(Connection connection= connectivityWithDataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (Trading trade : tradeList) {
                preparedStatement.setString(1, trade.getTradeId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
