package com.Liuweiting.ProblemSolutions;

import java.util.Arrays;

/**
 * Created by DamonLiu on 2017/8/29.
 */
public class Q283_Move_Zeroes {
    /**
     * in place
     * without other memory cost.
     * minimize total operations.
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int totalZeros = 0;
        for (int i = nums.length-1; i > 0 ; i--) {
            if (nums[i]==0){
                totalZeros++;
                for (int j = i+1; j < nums.length; j++) {
                    nums[j-1] = nums[j];
                }
            }
        }
        for (int i = nums.length-1; i > nums.length-totalZeros ; i--) {
            nums[i] = 0;
        }
    }

    public int[] constructRectangle(int area) {
        int L = (int) (Math.sqrt(area));
        for (int i = L; i <= area; i++) {
            if (area % i == 0){
                int [] result = {i,area / i};
                if (area / i > i){
                    result = new int[]{area / i, i};
                }
                return result;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Q283_Move_Zeroes().constructRectangle(8)));
    }
}
