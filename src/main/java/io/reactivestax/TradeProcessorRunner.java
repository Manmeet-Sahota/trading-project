package io.reactivestax;

import io.reactivestax.configuration.RabbitMQConfig;
import io.reactivestax.service.ChunkGenerator;
import io.reactivestax.service.TradeProducer;

import java.sql.SQLException;

public class TradeProcessorRunner {
    public static void main(String[] args) throws InterruptedException, SQLException {
        RabbitMQConfig rabbitMQConfig=new RabbitMQConfig("localhost",5672,"guest","guest");
        TradeProducer tradeProducer= new TradeProducer(rabbitMQConfig);

        ChunkGenerator fr1 = new ChunkGenerator();
        fr1.fileReader("/Users/Manmeet.Singh/Student_Work/projects/trading-project/src/main/resources/trades.csv", 1000);

        
        
    }
}
