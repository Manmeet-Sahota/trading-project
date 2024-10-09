package org.example.service;

import org.example.configuration.ChunkProcessorThreadPool;
import org.example.configuration.ConfigLoader;

import java.io.*;

public class ChunkGenerator implements ChunkGeneratorInterface {
    int count = 0;
    int rowCount=0;
    String line ;
    int fileCount = 1;

    @Override
    public void fileReader(String filePath, int chunkSize) {
        ConfigLoader configLoader=new ConfigLoader("application.properties");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String header = reader.readLine();

            BufferedWriter writer = new BufferedWriter(new FileWriter( "trade.filePath"+ fileCount + ".csv"));
            while ((line = reader.readLine()) != null) {
                if (rowCount >= chunkSize) {
                    writer.close();
                    fileCount++;
                    writer = new BufferedWriter(new FileWriter("trade.filePath" + fileCount + ".csv"));
                    rowCount = 0;
                }
                writer.write(line);
                writer.newLine();
                rowCount++;
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        call thread pool
        ChunkProcessorThreadPool tpc=new ChunkProcessorThreadPool();
        tpc.createThreadPool(fileCount);


    }
}
