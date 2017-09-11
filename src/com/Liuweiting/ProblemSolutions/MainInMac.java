package com.Liuweiting.ProblemSolutions;

import com.Liuweiting.DataStructure.ListNode;
import com.Liuweiting.DataStructure.TreeNode;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     *
     * @param
     * @param
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        ArrayList<Integer> result = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                result.add(nums1[i]);
                i++;
                j++;
                continue;
            }

            if (nums1[i] < nums2[j]) {
                i++;
                continue;
            }

            if (nums1[i] > nums2[j]) {
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
     *
     * @param num
     * @return
     */
    public String convertToBase7(int num) {
        int base = 7;
        boolean needFalse = num < 0;
        num = Math.abs(num);
        StringBuilder sb = new StringBuilder();
        while (num >= base) {
            sb.append(num % base);
            num = num / base;
        }
        sb.append(num);
        sb.append(needFalse ? "-" : "");
        return sb.reverse().toString();
    }

    /**
     * 551. Student Attendance Record I
     *
     * @param
     * @param
     * @return
     */
    public boolean checkRecord(String s) {
        int ACount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'A') {
                ACount++;
                if (ACount > 1) return false;
            }

            if (s.charAt(i) == 'L') {
                if (i - 1 >= 0 && s.charAt(i - 1) == 'L') {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 543. Diameter of Binary Tree
     *
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

    private int maxHeightForTree(TreeNode root) {
        int treeHeight = 0;
        if (root == null) return 0;
        treeHeight = dfs(root, treeHeight);
        return treeHeight;
    }

    private int dfs(TreeNode root, int height) {
        if (root == null) return 0;
        return Math.max(dfs(root.left, height) + 1, dfs(root.right, height) + 1);
    }

    private double distanceBetweenPoints(int[] a, int[] b) {
        return Math.pow((a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]), 0.5);
    }

    public boolean isPowerOfFour(int num) {
        return false;
    }


    /**
     * 459. Repeated Substring Pattern
     * https://leetcode.com/problems/repeated-substring-pattern/description/
     *
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        for (int i = 1; i <= s.length(); i++) {
            String pattern = s.substring(0, i);
            if (isPatternSolid(s, pattern)) return true;
        }
        return false;
    }

    private boolean isPatternSolid(String s, String pattern) {
        if (s.length() % pattern.length() != 0) {
            return false;
        }
        for (int j = 0; j < s.length(); j++) {
            int indexInPattern = j % pattern.length();
            if (pattern.charAt(indexInPattern) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 643. Maximum Average Subarray I
     * https://leetcode.com/problems/maximum-average-subarray-i/description/
     *
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {

        if (nums.length <= k) {
            double sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            sum /= nums.length;
            return sum;
        }

        long max = Long.MIN_VALUE;
        long average = 0;
        for (int j = 0; j < k; j++) {
            average += nums[j];
        }
        max = average;
        for (int i = k; i <= nums.length - 1; i++) {
            average = average - nums[i - k] + nums[i];
            max = average > max ? average : max;
        }
        double result = (double) (max / (double) k);
        return result;
    }


    /**
     * 501. Find Mode in Binary Search Tree
     * https://leetcode.com/problems/find-mode-in-binary-search-tree/description/
     *
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        TreeNode tmp = root;
        while (tmp.left != null) {
            tmp = tmp.left;
        }
        min = tmp.val;

        tmp = root;
        while (tmp.right != null) {
            tmp = tmp.right;
        }
        max = tmp.val;
        int[] array = new int[min + max];
        dfs(root, array, min);
        int mostFrequent = 0;
        for (int i = 0; i < array.length; i++) {
            mostFrequent = Math.max(array[i], mostFrequent);
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == mostFrequent) {
                result.add(i);
            }
        }
        int[] resultArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }
        return resultArray;

    }

    public void dfs(TreeNode root, int[] array, int min) {
        if (root == null) return;
        array[root.val - min]++;
        dfs(root.left, array, min);
        dfs(root.right, array, min);
    }


    /**
     * Q110
     * https://leetcode.com/problems/balanced-binary-tree/description/
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int depthOfLeft = getDepthOfTree(root.left);
        int depthOfRight = getDepthOfTree(root.right);
        return Math.abs(depthOfLeft - depthOfRight) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int getDepthOfTree(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getDepthOfTree(root.left), getDepthOfTree(root.right));
    }


    /**
     * 119. Pascal's Triangle II
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        Integer[] array1 = new Integer[rowIndex + 1];
        array1[0] = 1;
        if (rowIndex == 0) {
            return Arrays.asList(array1);
        }
        Integer[] array2 = new Integer[rowIndex + 1];
        array2[0] = 1;
        array2[1] = 1;
        if (rowIndex == 1) {
            return Arrays.asList(array2);
        }
        Integer[] tmp = array1;
        array1 = array2;
        array2 = tmp;
        for (int i = 2; i <= rowIndex; i++) {
            int rowLengh = i + 1;
            for (int j = 0; j < rowLengh; j++) {
                if (j == 0 || j == rowLengh - 1) {
                    array2[j] = 1;
                    continue;
                }

                array2[j] = array1[j - 1] + array1[j];
            }
            tmp = array1;
            array1 = array2;
            array2 = tmp;
        }
        return Arrays.asList(array1);
    }


    /**
     * Q441.
     *
     * @param n
     * @return
     */
//    public int arrangeCoins(int n) {
//        double x = (Math.pow(8 * n + 1, .05) - 1) / 2;
//        int xFloor = (int) Math.floor(x);
//        return xFloor;
//    }
    public int arrangeCoins(int n) {
        long lower = 1;
        long upper = (int) Math.pow(2, 16);
        long middle;
        while (lower < upper - 1) {
            middle = (lower + upper) / 2;
            long tmp = (1 + middle) * middle / 2;
            if (tmp == n) {
                return (int) middle;
            }

            if (tmp < n) {
                lower = middle;
                continue;
            }
            if (tmp > n) {
                upper = middle;
            }
        }
        return (int) lower;
    }


    /**
     * 172. Factorial Trailing Zeroes
     * https://leetcode.com/problems/factorial-trailing-zeroes/description/
     *
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int count = 0;
        int twoNumber = 0;
        for (int i = 1; i <= n; i++) {
            int tmp = i;
            if (tmp % 10 == 0) {
                do {
                    count++;
                    tmp /= 10;
                } while (tmp > 10 && tmp % 10 == 0);
                continue;
            }
            if (i % 2 == 0 && twoNumber == 0) {
                twoNumber++;
            }
            if (i % 5 == 0 && twoNumber-- > 0) {
                count++;
            }

        }
        return count;
    }

    int guess(int num) {
        return -1;
    }

    public int guessNumber(int n) {
        if (guess(1) == 0) return 1;
        if (guess(n) == 0) return n;
        int guessedResult = (1 + n) / 2;
        int lower = 1;
        int upper = n;
        while (lower < upper) {
            if (guess(guessedResult) == 0) {
                return guessedResult;
            }
            if (guess(guessedResult) > 0) {
                lower = guessedResult + 1;
            } else {
                upper = guessedResult - 1;
            }
            guessedResult = (int) (0.5 * lower + 0.5 * upper);
        }
        return lower;
    }


    /**
     * Q26.
     * removeDulicates.
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int beginIndex = -1;
        int endIndex = -1;
        int length = nums.length;
        int result = 1;
        for (int i = 1; i < length; i++) {
            if (beginIndex > 0 && endIndex > 0 && endIndex > beginIndex) {
                length -= (endIndex - beginIndex);
                for (int j = beginIndex; j < length; j++) {
                    if (j + endIndex - beginIndex < nums.length) {
                        nums[j] = nums[j + endIndex - beginIndex];
                    }
                }
                i = beginIndex;
                beginIndex = -1;
                endIndex = -1;
            }
            if (nums[i] == nums[i - 1] && beginIndex < 0) {
                beginIndex = i;
            }
            if (nums[i] != nums[i - 1] && beginIndex > 0 && endIndex < 0) {
                endIndex = i;
                result++;
            }
        }
        if (beginIndex > 0 && endIndex < 0) {
            endIndex = length;
            length -= endIndex - beginIndex;
            return length;
        }

        if (beginIndex > 0 && endIndex > 0) {
            length -= endIndex - beginIndex;
            for (int j = beginIndex; j < length; j++) {
                if (j + endIndex - beginIndex < nums.length) {
                    nums[j] = nums[j + endIndex - beginIndex];
                }
            }
        }
        return length;
    }

    public boolean hasCycle(ListNode head) {
        

        return false;
    }

    public static void main(String[] args) {
        MainInMac m = new MainInMac();
        int[] input = {1, 12, -5, -6, 50, 3};
        int k = 4;
//        System.out.println(m.findMaxAverage(input, k));


//        System.out.println(m.getRow(3));

//        System.out.println(m.arrangeCoins(1804289383));
        int[] input1 = {1,1, 1};
        System.out.println(m.removeDuplicates(input1));
    }


}
