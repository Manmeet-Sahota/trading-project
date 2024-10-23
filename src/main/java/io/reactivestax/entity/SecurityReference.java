package io.reactivestax.entity;

import io.reactivestax.configuration.ConnectivityWithDataBase;
import io.reactivestax.model.Trading;

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
