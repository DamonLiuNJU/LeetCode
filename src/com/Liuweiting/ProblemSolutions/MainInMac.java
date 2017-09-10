package com.Liuweiting.ProblemSolutions;

import com.Liuweiting.DataStructure.TreeNode;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by DamonLiu on 2017/9/4.
 */
public class MainInMac {

    /**
     * 447. Number of Boomerangs
     */
    public int numberOfBoomerangs(int[][] points) {
        return -1;
    }


    /**
     * 350
     * @param
     * @param
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i=0,j=0;
        ArrayList<Integer> result = new ArrayList<>();
        while(i<nums1.length && j<nums2.length){
            if (nums1[i]==nums2[j]){
                result.add(nums1[i]);
                i++;j++;
                continue;
            }

            if (nums1[i]<nums2[j]){
                i++;
                continue;
            }

            if (nums1[i]>nums2[j]){
                j++;
                continue;
            }
        }

        int[] tmp = new int[result.size()];
        for (int k = 0; k < result.size(); k++) {
            tmp[k] = result.get(k);
        }

        return tmp;
    }


    /**
     * 504. Base 7
     * @param num
     * @return
     */
    public String convertToBase7(int num) {
        int base = 7;
        boolean needFalse = num < 0;
        num = Math.abs(num);
        StringBuilder sb = new StringBuilder();
        while (num >= base){
            sb.append(num%base);
            num = num / base;
        }
        sb.append(num);
        sb.append(needFalse?"-":"");
        return sb.reverse().toString();
    }

    /**
     * 551. Student Attendance Record I
     * @param
     * @param
     * @return
     */
    public boolean checkRecord(String s) {
        int ACount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)=='A'){
                ACount++;
                if (ACount>1) return false;
            }

            if (s.charAt(i)=='L'){
                if (i-1>=0 && s.charAt(i-1)=='L'){
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 543. Diameter of Binary Tree
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int max1 = maxHeightForTree(root.left) + maxHeightForTree(root.right);
        int max2 = diameterOfBinaryTree(root.left);
        int max3 = diameterOfBinaryTree(root.right);
        int max = Math.max(max1, max2);
        max = Math.max(max, max3);
        return max;
    }

    private int maxHeightForTree(TreeNode root){
        int treeHeight = 0;
        if (root == null) return 0;
        treeHeight = dfs(root,treeHeight);
        return treeHeight;
    }

    private int dfs(TreeNode root,int height){
        if (root==null) return 0;
        return Math.max(dfs(root.left,height)+1,dfs(root.right,height)+1);
    }

    private double distanceBetweenPoints(int[] a,int [] b) {
        return Math.pow((a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]), 0.5);
    }

    public boolean isPowerOfFour(int num) {
        return false;
    }


    /**
     * 459. Repeated Substring Pattern
     * https://leetcode.com/problems/repeated-substring-pattern/description/
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        for (int i = 1; i <=s.length(); i++) {
            String pattern = s.substring(0,i);
            if (isPatternSolid(s,pattern)) return true;
        }
        return false;
    }

    private boolean isPatternSolid(String s, String pattern){
        if (s.length() % pattern.length()!=0) {
            return false;
        }
        for (int j = 0; j < s.length(); j++) {
            int indexInPattern = j % pattern.length();
            if (pattern.charAt(indexInPattern)!=s.charAt(j)){
                return false;
            }
        }
        return true;
    }


    /**
     * 643. Maximum Average Subarray I
     * https://leetcode.com/problems/maximum-average-subarray-i/description/
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {


    }


}
