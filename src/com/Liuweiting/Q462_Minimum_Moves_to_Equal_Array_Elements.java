package com.Liuweiting;

import java.util.Arrays;

/**
 * Created by DamonLiu on 2017/6/9.
 * https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/#/description
 */
public class Q462_Minimum_Moves_to_Equal_Array_Elements {
    public int minMoves2_brutal(int[] nums) {
        // first thought : Brutal Method.
        int min_move = Integer.MAX_VALUE;
        for (int anchor : nums){
            int moves = 0;
            for (int value : nums){
                moves += Math.abs(anchor-value);
                if (moves < 0){
                    break;
                }
            }
            if (moves < 0){
                continue;
            }
            min_move = moves < min_move ? moves : min_move;
        }
        return min_move;
    }

    public int minMoves2(int[] nums) {
        // first thought : Brutal Method.
        int min_move = 0;
        Arrays.sort(nums);
        int anchor = nums[nums.length/2];
        for (int val : nums){
            min_move += Math.abs(anchor - val);
        }
        return min_move;
    }


    public static void main(String[] args) {
        int[] input = {1,2,3};
        System.out.println(new Q462_Minimum_Moves_to_Equal_Array_Elements().minMoves2(input));
    }
}
