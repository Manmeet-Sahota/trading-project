package org.example;

import org.example.configuration.ConfigLoader;
import org.example.service.ChunkGenerator;
import org.example.storage.DataStorage;

import java.sql.SQLException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException, SQLException {

        ConfigLoader configLoader = new ConfigLoader("application.properties");
        String value = configLoader.getProperty("fileName");
        int  chunkSize = Integer.parseInt( configLoader.getProperty("chunkSize"));

        ChunkGenerator fr1 = new ChunkGenerator();

        fr1.fileReader(value, chunkSize);

    }
}