package org.example;

import java.io.FileNotFoundException;

public interface ChunkProcessor {
    Boolean processorChunks (String chunkFilePathName)throws FileNotFoundException;
}
