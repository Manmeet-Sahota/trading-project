package io.reactivestax.model;

import io.reactivestax.configuration.ConnectivityWithDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class TradePayLoad {

    public void insertTradePayload(List<Trading> tradeList) {
        String sql = "Insert into trade_payload(trade_id,payload,status) VALUES(?,?,?)";
        ConnectivityWithDataBase connectivityWithDataBase=new ConnectivityWithDataBase();
        try(Connection connection= connectivityWithDataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (Trading trade : tradeList) {
                preparedStatement.setString(1, trade.getTradeId());
                preparedStatement.setString(2, trade.getPayload());
                preparedStatement.setString(3,"valid");
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getPayload (List<String>  tradeId ){
        String tradeList="Select payload from trade_payload  where (tradeId) values(?)";
        ConnectivityWithDataBase connectivityWithDataBase=new ConnectivityWithDataBase();
        try{
             Connection connection= connectivityWithDataBase.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(tradeList);
                for (String idTrade:tradeId){
                    preparedStatement.setString(1,tradeList.toString());
                }
            }
         catch (Exception e) {
            e.printStackTrace();
        }
    return tradeList;
    }
}
