package com.Liuweiting;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by DamonLiu on 2017/8/16.
 */
public class Q575_Distribute_Dandies {
    public int distributeCandies(int[] candies) {
        Set<Integer> candyCategorySet = new HashSet<>();
        for (int candyKind:candies
             ) {
            candyCategorySet.add(candyKind);
        }
        int base = candies.length/2;
        return Math.min(base,candyCategorySet.size());
    }
}
