package org.example.service;

import org.example.configuration.ConnectivityWithDataBase;
import org.example.model.Trading;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class SecurityReference {
    public void insertSecurityReference(List<Trading> tradeList) {
        String sql = "Insert into security_reference(CUSIP) VALUES(?)";
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
}
