package org.example.util;

import org.example.model.Trading;

import java.util.ArrayList;
import java.util.List;

public class Helper {
    public static List<Trading> fetching(List<Trading> tradeList){
        List<Trading> finalTradingList=new ArrayList<>();
        for (Trading tradings:tradeList){
            String tradingsPayload=tradings.getPayload();
           String[] trade= tradingsPayload.split(",");
            Trading newtrading=new Trading();
            newtrading.setTradeId(trade[0]);
            newtrading.setTransactionTime(trade[1]);
            newtrading.setAccountNumber(trade[2]);
            newtrading.setCusip(trade[3]);
            newtrading.setActivity(trade[4]);
            newtrading.setQuantity(trade[5]);
            newtrading.setPrice(trade[6]);
            finalTradingList.add(newtrading);

        }
        return finalTradingList;
    }
}
