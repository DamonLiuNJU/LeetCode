package com.Liuweiting.ProblemSolutions;

/**
 * Created by DamonLiu on 2017/8/18.
 * Given an array of integers, every element appears twice except for one. Find that single one.

    Note:
    Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 */
public class Q136_Single_Number {
    public int singleNumber(int[] nums) {
        /*
            with [ 2 , 4 , 2 , 4 , 6 ]
         */
        return -1;

    }

    public static void main(String[] args) {
        int [] tmp  ={17,12,5,-6,12,4,17,-5,2,-3,2,4,5,16,-3,-4,15,15,-4,-5,-6};
        System.out.println(new Q136_Single_Number().singleNumber(tmp));
    }
}
