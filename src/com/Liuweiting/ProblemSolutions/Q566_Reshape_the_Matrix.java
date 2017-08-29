package com.Liuweiting.ProblemSolutions;

/**
 * Created by DamonLiu on 2017/8/16.
 */
public class Q566_Reshape_the_Matrix {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (canReshape(nums,r,c)){
            int[][] result = new int[r][c];
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < nums[0].length;j++){
                    int totalIndex = i * nums[0].length + j;
                    int newX = totalIndex / c;
                    int newY = totalIndex % c;
                    result[newX][newY] = nums[i][j];
                }
            }
            return result;
        } else {
            return nums;
        }
    }

    private boolean canReshape(int[][] nums, int r, int c){
        return  (nums.length * nums[0].length == r * c);
    }

    public static void main(String[] args) {
        int input [][] = { {1,2},{3,4} };

        new Q566_Reshape_the_Matrix().matrixReshape(input,1,4);
    }
}
