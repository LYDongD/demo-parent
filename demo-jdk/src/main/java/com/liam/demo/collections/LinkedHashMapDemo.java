package com.liam.demo.collections;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapDemo {

    public static void insertOrderTest() {
        //FIFO
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < 10; i++) {
            linkedHashMap.put(i, i + "");
        }
        linkedHashMap.get(4);

        for (Map.Entry entry : linkedHashMap.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    public static void accessOrderTest () {

        float loadFactor = (float) 0.7;
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>(16, loadFactor, true);
        for (int i = 0; i < 10; i++) {
            linkedHashMap.put(i, i + "");
        }
        linkedHashMap.get(4);
        linkedHashMap.get(5);

        for (Map.Entry entry : linkedHashMap.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    public static void LRU() {
        Map<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>(16, (float)0.7, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest){
                return size() > 3;
            }
        };

        linkedHashMap.put(1,"1");
        linkedHashMap.put(2,"1");
        linkedHashMap.put(3,"3");
        linkedHashMap.get(1);
        linkedHashMap.put(4,"3");


        for (Map.Entry entry : linkedHashMap.entrySet()) {
            System.out.println(entry.getKey());
        }

    }

    public static void main(String args[]) {

        LRU();

    }
}
