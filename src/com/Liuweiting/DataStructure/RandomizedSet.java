package com.Liuweiting.DataStructure;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by DamonLiu on 2017/11/6.
 */
class RandomizedSet {

    /**
     * Initialize your data structure here.
     */
    HashSet<Integer> hashSet;
    Random r;

    public RandomizedSet() {
        hashSet = new HashSet<>();
        r = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        boolean alreadyContains = hashSet.contains(val);
        hashSet.add(val);
        return !alreadyContains;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        boolean alreadContains = hashSet.contains(val);
        hashSet.remove(val);
        return alreadContains;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        int tmp = r.nextInt(hashSet.size());
        Iterator<Integer> i = hashSet.iterator();
        while (--tmp >= 0){
            i.next();
        }
        return i.next();
    }
}