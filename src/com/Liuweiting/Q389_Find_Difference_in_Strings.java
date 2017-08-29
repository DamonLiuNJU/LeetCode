package com.Liuweiting;

import java.util.Arrays;

/**
 * Created by DamonLiu on 2017/8/29.
 */
public class Q389_Find_Difference_in_Strings {
    public char findTheDifference(String s, String t) {
        char[] s1 = s.toCharArray();
        char[] s2 = t.toCharArray();
//        Arrays.sort(s1);
//        Arrays.sort(s2);
        int tmp = 0x00000000;
        for (int i = 0; i < s1.length; i++) {
            tmp = tmp ^ s1[i];
        }
        for (int i = 0; i < s2.length; i++) {
            tmp = tmp ^ s2[i];
        }
        return (char) tmp;
    }
}
