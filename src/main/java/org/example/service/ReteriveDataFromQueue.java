package org.example.service;

import org.example.configuration.ConnectivityWithDataBase;
import org.example.model.Trading;
import org.example.util.Helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ReteriveDataFromQueue {

//    public List<String> readQueueData(BlockingQueue<String> blockingQueue) throws SQLException {
//        List<String> list = new ArrayList<>();
//        ConnectivityWithDataBase connectivityWithDataBase = new ConnectivityWithDataBase();
//
//        while (!blockingQueue.isEmpty()) {
//            String tradeId = blockingQueue.poll();
//            list.add(tradeId);
//        }
//        List<Trading> list1 = connectivityWithDataBase.fetchTradeIds(list);
//
//        List<Trading> fetching = Helper.fetching(list1);
//        insertTradePayload1(fetching);
//        insertIntoJournalEntry(fetching);
//        insertIntoPosition(fetching);
//
//        return list;
//
//    }

    public void insertTradePayload1(List<Trading> tradeList) {
        String sql = " INSERT IGNORE INTO security_reference (cusip) VALUES (?)";
        ConnectivityWithDataBase connectivityWithDataBase = new ConnectivityWithDataBase();
        try (Connection connection = connectivityWithDataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (Trading trade : tradeList) {
                preparedStatement.setString(1, trade.getCusip());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            System.out.println("data save in security reference table ");
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
                preparedStatement.setString(2,trade.getActivity());
                preparedStatement.setString(3,trade.getQuantity());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void insertIntoPosition(List<Trading> tradeList) {
        String sql = "Insert into positions (account,quantity,created_at) VALUES(?,?,?)";
        ConnectivityWithDataBase connectivityWithDataBase = new ConnectivityWithDataBase();
        try (Connection connection = connectivityWithDataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (Trading trade : tradeList) {
                preparedStatement.setString(1, trade.getAccountNumber());
                preparedStatement.setString(2,trade.getQuantity());
                preparedStatement.setString(3,trade.getTransactionTime());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
