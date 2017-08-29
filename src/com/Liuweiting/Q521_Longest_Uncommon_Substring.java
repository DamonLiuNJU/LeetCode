package com.Liuweiting;

/**
 * Created by DamonLiu on 2017/8/18.
 * 521. Longest Uncommon Subsequence I
 *
 */
public class Q521_Longest_Uncommon_Substring {
    public int findLUSlength(String a, String b) {
        if(a.compareTo(b)!=0){
            return Math.min(a.length(),b.length());
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        new Q521_Longest_Uncommon_Substring().findLUSlength("","");
    }
}
