package com.Liuweiting;

/**
 * https://leetcode.com/problems/beautiful-arrangement/#/description
 * Created by DamonLiu on 2017/5/29.
 */
public class Q526_Beautiful_Arrangement {

    public int countArrangement(int N) {
        if (N == 1) {
            return 1;
        }
        if (N == 2) {
            return 2;
        } else {
            int lastSolution = countArrangement(N - 1);

            int counter = 0;  // this counter represents the number of positions where N cannot put.
            int another_counter = 0;
            for (int i = 1; i <= N - 1; i++) {
                if (N % i != 0) {
                    counter ++;
                }
            }

            int totalSolution = (N - counter) * lastSolution - ;
            return totalSolution;
        }
    }


    public static void main(String[] args) {
        System.out.println(new Q526_Beautiful_Arrangement().countArrangement(3));
        System.out.println(new Q526_Beautiful_Arrangement().countArrangement(4));
        System.out.println(new Q526_Beautiful_Arrangement().countArrangement(5));
    }
}
