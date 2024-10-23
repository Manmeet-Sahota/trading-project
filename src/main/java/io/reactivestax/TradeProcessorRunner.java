package io.reactivestax;

import io.reactivestax.service.ChunkGenerator;

import java.sql.SQLException;

public class TradeProcessorRunner {
    public static void main(String[] args) throws InterruptedException, SQLException {

        ChunkGenerator fr1 = new ChunkGenerator();
        fr1.fileReader("/Users/Manmeet.Singh/Student_Work/projects/trading-project/src/main/resources/trades.csv", 1000);

    }
}