package org.example.service;

import org.example.configuration.ConfigLoader;
import org.example.model.Trading;
import org.example.storage.DataQueue;
import org.example.storage.DataStorage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class ReadBlockingQueue implements Runnable {
    public ReadBlockingQueue(int i) {
        this.i = i;
    }

    private int i;
    String line1 = null;

    @Override
    public void run() {

    }
}

