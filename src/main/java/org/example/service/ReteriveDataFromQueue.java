package org.example.service;

import org.example.configuration.ConfigLoader;
import org.example.configuration.ConnectivityWithDataBase;
import org.example.model.Trading;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ReteriveDataFromQueue {

    public List<String> readQueueData(BlockingQueue<String> blockingQueue) throws SQLException {
        List<String> list = new ArrayList<>();
        ConnectivityWithDataBase connectivityWithDataBase = new ConnectivityWithDataBase();

        while (!blockingQueue.isEmpty()) {
            String tradeId = blockingQueue.poll();

            retriveData(tradeId);

            convertStringToTrading(tradeId);

//         first method :  when you get tradeId here , go to DB and search for trade Id and retrieve the payload from there
//         second method : convert the data into pojo by using split string method.
//            third method : save the data into security
//            forth method : save the data into 2nd table
//            fifth : save into 3rd table
            list.add(tradeId);
        }
//        List<Trading> list1 = connectivityWithDataBase.fetchTradeIds(list);
        return list;
    }

    public String retriveData(String tradeId) {
        String payload = null;
        String sql = "select payload from trade_payload  where trade_id = ?";
        ConnectivityWithDataBase connectivityWithDataBase = new ConnectivityWithDataBase();
        try {
            Connection connection = connectivityWithDataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tradeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                payload = resultSet.getString("payload");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payload;
    }

    public Trading  convertStringToTrading(String payload){
      String[]  tradingData=payload.split(",");

      Trading trade=new Trading();
      trade.setTradeId(tradingData[0]);
      trade.setTransactionTime(tradingData[1]);
      trade.setAccountNumber(tradingData[2]);
      trade.setCusip(tradingData[3]);
      trade.setActivity(tradingData[4]);
      trade.setQuantity(tradingData[5]);
      trade.setPrice(tradingData[6]);
      return trade;
    }


    public void saveDataIntoSecurity() {
        String sql = "insert into security_reference ";
        ConnectivityWithDataBase connectivityWithDataBase = new ConnectivityWithDataBase();
        try {
            Connection connection = connectivityWithDataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public void insertTradePayload1(List<Trading> tradeList) {
        String sql = "Insert into security_reference (CUSIP) VALUES(?)";
        ConnectivityWithDataBase connectivityWithDataBase = new ConnectivityWithDataBase();
        try (Connection connection = connectivityWithDataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (Trading trade : tradeList) {
                preparedStatement.setString(1, trade.getCusip());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void insertIntoJournalEntry(List<Trading> tradeList) {
        String sql = "Insert into journal_entry (account,direction,quantity) VALUES(?,?,?)";
        ConnectivityWithDataBase connectivityWithDataBase = new ConnectivityWithDataBase();
        try (Connection connection = connectivityWithDataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (Trading trade : tradeList) {
                preparedStatement.setString(1, trade.getAccountNumber());
                preparedStatement.setString(2, trade.getActivity());
                preparedStatement.setString(3, trade.getQuantity());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void insertIntoPosition(List<Trading> tradeList) {
        String sql = "Insert into positions (account,position_id) VALUES(?,?)";
        ConnectivityWithDataBase connectivityWithDataBase = new ConnectivityWithDataBase();
        try (Connection connection = connectivityWithDataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (Trading trade : tradeList) {
                preparedStatement.setString(1, trade.getAccountNumber());
                preparedStatement.setString(2, trade.getQuantity());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
