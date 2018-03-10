package com.Liuweiting.DataStructure;

import java.util.HashMap;

/**
 * Created by DamonLiu on 2018/3/9.
 */
class LRUCache {
    int capacity;
    int operationCount = 0;
    HashMap<Integer,Integer> map = new HashMap<>();


    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        return map.getOrDefault(key,-1);
    }

    public void put(int key, int value) {
        if (map.keySet().size() < capacity){
            map.put(key,value);
        } else {

        }
    }
}
