package io.reactivestax.storage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataStorage {

    private static final Map<String, String> map = new ConcurrentHashMap<>();

    public Map<String, String> getMap() {
        return map;
    }
    public  void put(String key,String value){
        map.put(key, value);
    }
}
