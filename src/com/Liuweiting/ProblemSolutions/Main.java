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
        // write your code here
        int[] input = {722,634,-504,-379,163,-613,-842,-578,750,951,-158,30,-238,-392,-487,-797,-157,-374,999,-5,-521,-879,-858,382,626,803,-347,903,-205,57,-342,186,-736,17,83,726,-960,343,-984,937,-758,-122,577,-595,-544,-559,903,-183,192,825,368,-674,57,-959,884,29,-681,-339,582,969,-95,-455,-275,205,-548,79,258,35,233,203,20,-936,878,-868,-458,-882,867,-664,-892,-687,322,844,-745,447,-909,-586,69,-88,88,445,-553,-666,130,-640,-918,-7,-420,-368,250,-786};
        System.out.println(new Main().maximumProduct(input));
    }
}
