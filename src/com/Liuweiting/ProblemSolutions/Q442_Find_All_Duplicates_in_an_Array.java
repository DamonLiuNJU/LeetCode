package com.Liuweiting.ProblemSolutions;

import java.util.*;

/**
 * Created by DamonLiu on 2017/5/30.
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/#/description
 * <p>
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * <p>
 * Find all the elements that appear twice in this array.
 * <p>
 * Could you do it without extra space and in O(n) runtime?
 * <p>
 * Example:
 * Input:
 * [4,3,2,7,8,2,3,1]
 * <p>
 * Output:
 * [2,3]
 */
public class Q442_Find_All_Duplicates_in_an_Array {
    /**
     * 不使用额外的内存，在On的时间内解决。
     * 其实就是扫描一遍，将每一个数放到自己应该放到的位置上，如果该位置上有相同的数，那么就是重复了。
     *
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
//        boolean swaphappened = false;
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            int k = nums[i];
            if (nums[k - 1] != k) {
                swap(nums, k - 1, i);
//                swaphappened = true;
                i = Math.min(k - 1, i) - 1;
            } else {
                if (k - 1 != i)
                    set.add(k);
            }
        }
        result.addAll(set);
        return result;
    }

    private void swap(int[] nums, int index1, int index2) {
        nums[index1] += nums[index2];
        nums[index2] = nums[index1] - nums[index2];
        nums[index1] -= nums[index2];
    }

    public static void main(String[] args) {
        int input[] = {4};
        System.out.println(new Q442_Find_All_Duplicates_in_an_Array().findDuplicates(input).toString());
    }
}
