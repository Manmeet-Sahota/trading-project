package org.example;

import org.example.configuration.ConnectivityWithDataBase;
import org.example.configuration.ReadThread;
import org.example.configuration.TradePayLoad;
import org.example.service.FileReader1;
import org.example.service.FileReaderInterface;
import org.example.storage.DataStorage;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        FileReader1 fr1 = new FileReader1();
        fr1.fileReader("/Users/Manmeet.Singh/Student_Work/projects/trading-project/src/main/resources/trades.csv", 1000);
        System.out.println(System.currentTimeMillis());
        DataStorage dataStorage = new DataStorage();

        Map mapping = dataStorage.getMap();
       Thread.sleep(10000);
        for (int i = 1; i <= 5; i++) {
            System.out.println(i);
        }
    }
}