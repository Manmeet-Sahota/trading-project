package org.example;

import org.example.service.ChunkGenerator;
import org.example.storage.DataStorage;

import java.sql.SQLException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException, SQLException {

        ChunkGenerator fr1 = new ChunkGenerator();
        fr1.fileReader("/Users/Manmeet.Singh/Student_Work/projects/trading-project/src/main/resources/trades.csv", 1000);
    }
}