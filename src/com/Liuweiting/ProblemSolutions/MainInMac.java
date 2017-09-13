package com.Liuweiting.ProblemSolutions;

import com.Liuweiting.DataStructure.ListNode;
import com.Liuweiting.DataStructure.TreeNode;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.util.*;

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

//    public boolean hasCycle(ListNode head) {
//        HashSet<ListNode> set = new HashSet<>();
//        while (head!=null){
//            if (set.contains(head)) return true;
//            set.add(head);
//            head = head.next;
//        }
//        return false;
//    }

    public boolean hasCycle(ListNode head) {
        while (head != null && head.next != null) {
            head.next = head.next.next;
            head = head.next;
            if (head.next == head) return true;
        }
        return false;
    }


    public int[] twoSum(int[] nums, int target) {
        int MIN = Integer.MAX_VALUE;
        int MAX = Integer.MIN_VALUE;
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            MIN = Math.min(MIN, nums[i]);
            MAX = Math.max(MAX, nums[i]);
        }
        int[][] count = new int[MAX - MIN + 1][2];
        for (int i = 0; i < count.length; i++) {
            count[i] = new int[]{-1, -1};
        }
        for (int i = 0; i < nums.length; i++) {
            int tmp = nums[i];
            if (count[tmp - MIN][0] == -1) {
                count[tmp - MIN][0] = i;
            } else {
                count[tmp - MIN][1] = i;
                if (2 * nums[i] == target) {
                    result[0] = count[tmp - MIN][0];
                    result[1] = count[tmp - MIN][1];
                    return result;
                }
            }
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i][0] >= 0) {
                int originNum = i + MIN;
                int needNum = target - originNum;
                if (needNum - MIN < count.length && count[needNum - MIN][0] >= 0) {
                    result[0] = count[i][0];
                    result[1] = count[needNum - MIN][0];
                    return result;
                }
            }
        }
        return result;
    }


    /**
     * 38. Count and Say
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if (n == 1) return "1";
        if (n == 2) return "11";
        String tmp = countAndSay(n - 1);
        int curLength = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < tmp.length(); i++) {
            if (tmp.charAt(i) == tmp.charAt(i - 1)) curLength++;
            else {
                sb.append(curLength);
                sb.append(tmp.charAt(i - 1));
                curLength = 1;
            }
        }
        sb.append(curLength);
        sb.append(tmp.charAt(tmp.length() - 1));
        return sb.toString();
    }


    /**
     * 112. Path Sum
     * from Root to Leave path.
     *
     * @param root
     * @param sum
     * @return
     */
    int target;
    boolean hasPathSum = false;

    public boolean hasPathSum(TreeNode root, int sum) {
        target = sum;
        if (root == null) return sum == 0;
        sumToThisNode(root, 0);
        return hasPathSum;
    }

    private int sumToThisNode(TreeNode node, int prevSum) {
        if (node == null) {
            hasPathSum |= prevSum == target;
            return prevSum;
        }
        sumToThisNode(node.left, prevSum + node.val);
        sumToThisNode(node.right, prevSum + node.val);
        return prevSum + node.val;
    }


    /**
     * 205. Isomorphic Strings
     * https://leetcode.com/problems/isomorphic-strings/description/
     *
     * @param s
     * @param t
     * @return
     */
    HashMap<Character, Character> map = new HashMap<>();
    HashMap<Character, Character> reversedMap = new HashMap<>();

    public boolean isIsomorphic(String s, String t) {
        if (s.length() == 0 && t.length() == 0) return true;
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            char mapped = t.charAt(i);
            if (map.containsKey(key)) {
                if (map.get(key) != mapped) {
                    return false;
                }
            } else if (reversedMap.containsKey(mapped)) {
                return false;
            } else {
                map.put(key, mapped);
                reversedMap.put(mapped, key);
            }
        }
        return true;
    }

    /**
     * Q507 Perfect Number.
     *
     * @param num
     * @return
     */
    HashSet<Integer> perfectNumbers = new HashSet<>();

    public boolean checkPerfectNumber(int num) {
        int sum = 0;
        for (int i = 1; i <= num; i++) {
            if (num != i && num % i == 0) {
                sum += i;
            }
        }
        return num == sum;
    }

    public List<Integer> findAnagrams(String s, String p) {
        int[] pCharCount = new int[26];
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < p.length(); i++) {
            pCharCount[p.charAt(i) - 'a']++;
        }
        for (int i = 0; i <= s.length() - p.length(); i++) {
            int[] tmp = pCharCount.clone();
            boolean isValid = true;
            for (int j = 0; j < p.length(); j++) {
                if (tmp[s.charAt(i + j) - 'a'] == 0) {
                    isValid = false;
                    break;
                } else if (tmp[s.charAt(i + j) - 'a'] > 0) {
                    tmp[s.charAt(i + j) - 'a']--;
                }
            }
            if (isValid) result.add(i);
        }
        return result;
    }


    public boolean isValid(String s) {
        if (s.length() % 2 != 0) return false;
        Stack<Character> stack = new Stack<>();
        char[] tmp = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            switch (tmp[i]) {
                case '(':
                case '{':
                case '[':
                    stack.push(tmp[i]);
                    break;
                case ')':
                case '}':
                case ']':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    char c = stack.pop();
                    if (isParentAPari(c, tmp[i])) {
                        continue;
                    } else {
                        return false;
                    }
            }
        }
        return stack.isEmpty();
    }

    private boolean isParentAPari(Character left, Character right) {
        if (left == ')' || left == ']' || left == '}') return false;
        if (left == '(') return right == ')';
        if (left == '[') return right == ']';
        if (left == '{') return right == '}';
        return false;
    }


    /**
     * @param root
     * @return
     */
    LinkedList<TreeNode> currLevel = new LinkedList<>();
    LinkedList<TreeNode> nextLevel = new LinkedList<>();

    public int minDepth(TreeNode root) {
        currLevel.add(root);
        int depth = 0;
        while (!currLevel.isEmpty()) {
            TreeNode tmp = currLevel.removeFirst();
            if (isNodeLeave(tmp)) {
                return depth;
            }
            if (tmp.left != null) nextLevel.add(tmp.left);
            if (tmp.right != null) nextLevel.add(tmp.right);
            if (currLevel.isEmpty()) {
                currLevel = nextLevel;
                nextLevel = new LinkedList<>();
            }
        }
        return depth;
    }

    private boolean isNodeLeave(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }


    /**
     * Q290.
     * https://leetcode.com/problems/word-pattern/description/
     *
     * @param pattern only lower case letters.
     * @param str     seperated by space.
     * @return if match.
     */
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (str.length() != words.length) {
            return false;
        }
        HashMap<Character, String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            if (map.containsKey(pattern.charAt(i))) {
                if (map.get(pattern.charAt(i)).compareTo(words[i]) != 0) {
                    return false;
                }
            } else {
                map.put(pattern.charAt(i), words[i]);
            }
        }
        return true;
    }

    public boolean wordPatternFull(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }
        HashMap<Character, String> map = new HashMap<>();
        HashMap<String, Character> map2 = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (map.containsKey(pattern.charAt(i))) {
                if (map.get(pattern.charAt(i)).compareTo(words[i]) != 0) {
                    return false;
                }
            } else if (map2.containsKey(words[i])) {
                if (map2.get(words[i]) == (pattern.charAt(i))) {

                } else {
                    return false;
                }
            } else {
                map.put(pattern.charAt(i), words[i]);
                map2.put(words[i], pattern.charAt(i));
            }
        }
        return true;
    }


    /**
     * 234. Palindrome Linked List
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        //TODO: this question is some how wrong in description, maybe solve it later.

        return false;
    }


    public ListNode removeElements(ListNode head, int val) {
        ListNode tmpHead = new ListNode(Integer.MAX_VALUE);
        ListNode newHead = tmpHead;
        tmpHead.next = head;
        newHead.next = head;
        ListNode originHead = head;
        while (tmpHead.next != null) {
            if (tmpHead.next.val == val) {
                tmpHead.next = tmpHead.next.next;
                continue;
            }
            tmpHead = tmpHead.next;
        }
        return newHead.next;
    }


    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> numberToIndex = new HashMap<>();
        k++;
        for (int i = 0; i < k && i < nums.length; i++) {
            int tmp = nums[i];
            numberToIndex.put(tmp, numberToIndex.getOrDefault(tmp, 0) + 1);
            if (numberToIndex.get(tmp) >= 2) {
                return true;
            }
        }
        //deal with the left nums.
        for (int i = k; i < nums.length; i++) {
            int numToRemove = nums[i - k];
            numberToIndex.put(numToRemove, numberToIndex.get(numToRemove) - 1);
            int numToAdd = nums[i];
            numberToIndex.put(numToAdd, numberToIndex.getOrDefault(numToAdd, 0) + 1);
            if (numberToIndex.get(numToAdd) >= 2) {
                return true;
            }
        }
        return false;
    }


    /**
     * 88. Merge Sorted Array
     * https://leetcode.com/problems/merge-sorted-array/description/
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge_Cheated(int[] nums1, int m, int[] nums2, int n) {
        int index1 = 0;
        int index2 = 0;
        for (int i = m; i < m + n; i++) {
            nums1[i] = nums2[i - m];
        }
        Arrays.sort(nums1, 0, m + n);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int currentIndex = m + n - 1;
        int i = m - 1;
        int j = n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j])
                nums1[currentIndex--] = nums1[i--];
            else
                nums1[currentIndex--] = nums2[j--];
        }
        while (j >= 0) {
            nums1[currentIndex--] = nums2[j--];
        }
    }


    /**
     * 633. Sum of Square Numbers
     * Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.
     * https://leetcode.com/problems/sum-of-square-numbers/description/
     *
     * @param c
     */
    public boolean judgeSquareSum(int c) {
        int tmp = 0;
        while (c - tmp * tmp > tmp * tmp) {
            if (isNumSquare(c - tmp * tmp)) {
                return true;
            }
            tmp++;
        }
        return false;
    }

    private boolean isNumSquare(int num) {
//        int half = (int) Math.pow(num,.5);
        int half = (int) Math.sqrt(num);
        return num == half * half;
    }


    /**
     * 14. Longest Common Prefix
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String s = strs[0];
        int length = s.length();
        for (String others : strs) {
            length = Math.min(length, getPrefixLength(others, s));
        }
        return s.substring(0, length);
    }

    public int getPrefixLength(String s1, String s2) {
        for (int i = 0; i < s1.length() && i < s2.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return i;
            }
        }
        s1.indexOf(s2);
        return Math.min(s1.length(), s2.length());
    }


    /**
     * 160. Intersection of Two Linked Lists
     * https://leetcode.com/problems/intersection-of-two-linked-lists/description/
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tmpAHead = headA;
        while (tmpAHead != null) {
            ListNode tmpBHead = headB;
            while (tmpBHead != null) {
                if (tmpBHead == tmpAHead) return tmpAHead;
                tmpBHead = tmpBHead.next;
            }
            tmpAHead = tmpAHead.next;
        }
        return null;
    }


    /**
     * the point is to find the n's digit's corresponding number.
     *
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        int base = 1;
        long count = 9;

        while (true) {
            if (n > base * count) {
                n -= base * count;
                base++;
                count *= 10;
            } else {
                break;
            }
        }
        int currentValue = (int) Math.pow(10, base - 1);
//        while (n > base){
//            n -= base;
//            currentValue++;
//        }
        currentValue += (n - 1) / base;
        n -= n % base;

        int result = (currentValue + "").charAt((n - 1)) - '0';
//        System.out.println(result);
        return result;
    }


    /**
     * Q605.
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                boolean canPlace = true;
                canPlace &= (i - 1 >= 0 ? flowerbed[i - 1] == 0 : true);
                canPlace &= (i + 1 < flowerbed.length ? flowerbed[i + 1] == 0 : true);
                n -= canPlace ? 1 : 0;
                if (n == 0) return true;
                flowerbed[i] = canPlace ? 1 : 0;
            }
        }
        return n <= 0;
    }


    public static void main(String[] args) {
        MainInMac m = new MainInMac();
        int[] input = {-11, -2, 4, 6, 50, 3};
        int[] input1 = {0, 0, 1, 0, 0};
        ListNode root = new ListNode(1);
        ListNode cur = root;
        cur.next = new ListNode(2);
        cur = cur.next;
        cur.next = new ListNode(6);
        cur = cur.next;
        cur.next = new ListNode(3);
        cur = cur.next;
        cur.next = new ListNode(4);
        cur = cur.next;
        cur.next = new ListNode(5);
        cur = cur.next;
        cur.next = new ListNode(6);
    }


}
