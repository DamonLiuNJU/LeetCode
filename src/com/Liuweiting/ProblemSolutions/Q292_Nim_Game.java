package com.Liuweiting.ProblemSolutions;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by DamonLiu on 2017/8/16.
 */
public class Q292_Nim_Game {
    HashMap<Integer,Boolean> set = new HashMap<>();
    public boolean canWinNim(int n) {
        if (set.containsKey(n)) return set.get(n);
        boolean result = false;
        if(n==4) {result= false;return result;}
        if(n<=3) {result= true;return result;}

        result = canWinNim(n-6) || canWinNim(n-5) || canWinNim(n-4)||canWinNim(n-3)||canWinNim(n-2);
        set.put(n,result);
        //possible remove 2 3 4 5 6. then their minist common multiply is 60.
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(new Q292_Nim_Game().canWinNim(1443212));
        int value = -5;
        String tmp = Integer.toBinaryString(value);
        System.out.println(tmp);
    }
}
