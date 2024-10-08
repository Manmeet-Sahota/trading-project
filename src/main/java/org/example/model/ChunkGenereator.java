package org.example.model;

import java.io.FileNotFoundException;
import java.util.Map;

interface ChunkGenereator {
//    List<String> generateChunks(String filePath, Integer numberOfChunks);
//    Map<String , Boolean>submitChunks(List<String> chunkFilePaths);
//    <<<<<<<<<<<<<<<<<<<__________>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    Map<String,Boolean> generateAndSubmitChunks(String filePath, Integer numberOfChunks) throws FileNotFoundException;
}
