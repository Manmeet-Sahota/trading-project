package org.example.service;

public interface TradePayLoadService {
   <TradePayload> TradePayload readTradePayLoadTradeId(String trade_id);
   Boolean insertTradeIntoTradePayloadTable(String trade_id,String status, String payload);
}
