package io.reactivestax.util;

import io.reactivestax.model.Trading;

import java.util.ArrayList;
import java.util.List;

public class  Helper {
    public static List<Trading> fetching(List<Trading> tradeList) {
        List<Trading> finalTradingList = new ArrayList<>();
        for(Trading  tradings:tradeList ){
            String tradingsPayload=tradings.getPayload();
            String[] split = tradingsPayload.split(",");
            Trading newtrading=new Trading();
            newtrading.setTradeId(split[0]);
            newtrading.setTransactionTime(split[1]);
            newtrading.setAccountNumber(split[2]);
            newtrading.setCusip(split[3]);
            newtrading.setActivity(split[4]);
            newtrading.setQuantity(split[5]);
            newtrading.setPrice(split[6]);

        finalTradingList.add(newtrading);
        }
        return finalTradingList;
    }
}



