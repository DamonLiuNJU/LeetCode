package com.Liuweiting.ProblemSolutions;

import com.Liuweiting.DataStructure.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DamonLiu on 2017/9/15.
 */
public class Medium {


    /**
     * 654
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return helper(nums, 0, nums.length);
    }

    /**
     * DFS
     *
     * @param nums  array
     * @param begin included.
     * @param end   not included.
     * @return
     */
    private static TreeNode helper(int[] nums, int begin, int end) {
        if (begin == end) return null;
        else {
            TreeNode tmp = new TreeNode(1);
            int max = Integer.MIN_VALUE;
            int i;
            int maxIndex = -1;
            for (i = begin; i < end; i++) {
                if (nums[i] > max) {
                    maxIndex = i;
                    max = nums[i];
                }
            }
            tmp.val = max;
            tmp.left = helper(nums, begin, maxIndex);
            tmp.right = helper(nums, maxIndex + 1, end);
            return tmp;
        }
    }

    /**
     * 647. Palindromic Substrings
     * 找到连续回文子串，不同index视为不同的子串 即使内容一样。
     *
     * @param s The string.
     * @return the count.
     */
    public int countSubstrings(String s) {
        int count = 0;
        count += s.length(); // all the single chars are palindromic.
        for (int i = 2; i <= s.length(); i++) {
            //here i is the length.
            for (int j = 0; j <= s.length() - i; j++) {
                String tmp = s.substring(j, j + i);
                if (isPalindrome(tmp)) count++;
            }
        }
        return count;
    }

    private static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Q526 Beautiful Arrangement.
     *
     * @param N the number from 1 ~ N (N<=15.)
     * @return all the legal arrangements.
     */
    public int countArrangement(int N) {


        return -1;
    }


    /**
     * return true if is a legal arrangement. false if not legal.
     *
     * @param arr possible array.
     * @return if legal.
     */
    private static boolean isArrangementLegal(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            if (!(arr[i] % i + 1 == 0 || i + 1 % arr[i] == 0)) {
                return false;
            }
        }
        return true;
    }



    //609. Find Duplicate File in System
    public List<List<String>> findDuplicate(String[] paths) {
        HashMap<String,List<String>> map = new HashMap<>();
        for (String file : paths){
            String[] lineContent = file.split(" ");
            String path = lineContent[0];
            for (int i = 1; i < lineContent.length; i++) {
                String nameAndContent = lineContent[i];
                String realFileName = nameAndContent.split("\\(")[0];
                String content = nameAndContent.split("\\(")[1];
                content = content.substring(0,content.length()-1);
                if (map.containsKey(content)){
                    map.get(content).add(path+"/"+realFileName);
                } else {
                    List<String> tmp = new ArrayList<>();
                    tmp.add(path+"/"+realFileName);
                    map.put(content,tmp);
                }
            }
        }

        List<List<String>> result = new ArrayList<>();
        for (String key : map.keySet()){
            if (map.get(key).size()>1)
                result.add(map.get(key));
        }
        return result;
    }



    public static void main(String[] args) {
        Medium m = new Medium();
        int[] input = {3, 2, 1, 6, 0, 5};
        System.out.println(m.constructMaximumBinaryTree(input));
    }
}
