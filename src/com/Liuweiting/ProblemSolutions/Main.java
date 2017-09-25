package com.Liuweiting.ProblemSolutions;

import java.util.Arrays;

public class Main {

    /**
     * Q409. Longest Palindrome
     * https://leetcode.com/problems/longest-palindrome/description/
     *
     * @param args
     */
    public int longestPalindrome(String s) {
        //find even number of chars in string s.
        //add one odd number of char.
        int[] lower = new int[26];
        int[] upper = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c <= 'z' && c >= 'a') {
                lower[c-'a']++;
            } else {
                upper[c-'A']++;
            }
        }
        int length = 0;
        for (int i = 0; i < 26; i++) {
            length += (lower[i]/2)*2;
            length += (upper[i]/2)*2;
        }
        for (int i = 0; i < 26; i++) {
            if (lower[i]%2==1||upper[i]%2==1){
                length++;
                return length;
            }
        }
        return length;
    }


    /**
     * Q628. Maximum Product of Three Numbers
     * https://leetcode.com/problems/maximum-product-of-three-numbers/description/
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);

        //condition1
        int value1 = nums[0]*nums[1]*nums[2];
        if (nums.length==3) return value1;
        int value2 = nums[nums.length-1]*nums[nums.length-2]*nums[nums.length-3];
        int value3 = nums[0]*nums[1]*nums[nums.length-1];
        int value4 = nums[0]*nums[nums.length-2]*nums[nums.length-3];

    }


//    public int maximumProduct(int[] nums) {
//        int result1 =
//    }

    public static void main(String[] args) {

    }
}
