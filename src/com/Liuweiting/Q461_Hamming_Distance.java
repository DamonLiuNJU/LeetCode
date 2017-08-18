package com.Liuweiting;

/**
 * Created by DamonLiu on 2017/8/14.
 */
public class Q461_Hamming_Distance {
    public int hammingDistance(int x, int y) {
        int result = x ^ y;
        String tmp = Integer.toBinaryString(result);
        int counter = 0;
        for (char c : tmp.toCharArray()
             ) {
            if (c=='1'){
                counter++;
            }
        }
        return counter;

//        return Integer.bitCount(x ^ y);
    }

}
