package com.Liuweiting.ProblemSolutions;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by DamonLiu on 2017/9/19.
 */
class MapSum {

    /** Initialize your data structure here. */
    HashMap<String, Integer> map = new HashMap<>();
    HashMap<String, Integer> originMap = new HashMap<>();
    public MapSum() {

    }

    public void insert(String key, int val) {
        if (originMap.containsKey(key)){
            int diff = val - originMap.get(key);
            for (int i = 1; i <= key.length(); i++) {
                String tmpKey = key.substring(0, i);
                int count = map.getOrDefault(tmpKey, 0);
                map.put(tmpKey, count + diff);
            }
        } else {
            originMap.put(key,val);
            for (int i = 1; i <= key.length(); i++) {
                String tmpKey = key.substring(0, i);
                int count = map.getOrDefault(tmpKey, 0);
                map.put(tmpKey, count + val);
            }
        }
    }

    public int sum(String prefix) {
        return map.getOrDefault(prefix,0);
    }

    public static void main(String[] args) {
        MapSum obj = new MapSum();
        obj.insert("aa",3);
        System.out.println(obj.sum("a"));
        obj.insert("aa",2);
        System.out.println(obj.sum("a"));
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */