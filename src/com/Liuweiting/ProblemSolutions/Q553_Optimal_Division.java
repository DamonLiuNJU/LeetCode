package com.Liuweiting.ProblemSolutions;

/**
 * https://leetcode.com/problems/optimal-division/#/description
 * Created by DamonLiu on 2017/5/29.
 *
 * so this problem is about some kind of thinking first.
 * I got stuck at considering DP solutions, so that I cannot figure out the true solution behind the description.
 */
public class Q553_Optimal_Division {

    public String optimalDivision(int[] nums) {
        if (nums.length==1){
            return nums[0] + "";
        }
        if (nums.length==2){
            return nums[0] + "/" + nums[1];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]);
        sb.append("/(");
        for (int i = 1; i < nums.length-1; i++) {
            sb.append(nums[i]);
            sb.append("/");
        }
        sb.append(nums[nums.length-1]);
        sb.append(")");
        return sb.toString();
    }

    public static void main(String[] args) {
        int arrays[] = {1000,100,10,2};
        System.out.println(new Q553_Optimal_Division().optimalDivision(arrays));
    }
}
