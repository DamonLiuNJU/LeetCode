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


    /**
     * Q303
     *
     * @param nums
     */


    /**
     * Q475.
     * https://leetcode.com/problems/heaters/description/
     *
     * @param houses  houses indexes. positive.
     * @param heaters heater indexes. positive.
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {

        int lower = 0;
        int upper = Integer.MAX_VALUE;

        int tmp;
        while (lower < upper) {
            tmp = (lower + upper) / 2;
            if (canSatisfy(houses, heaters, tmp)) {
                upper = tmp;
            } else {
                if (lower == tmp) {
                    return lower + 1;
                }
                lower = tmp;
            }
        }
        return lower;
    }

    /**
     * return true, if under the given radius r, heaters can heat all the houses.
     *
     * @param houses
     * @param heaters
     * @param r
     * @return
     */
    private static boolean canSatisfy(int[] houses, int[] heaters, int r) {
        for (int i = 0; i < houses.length; i++) {
            boolean canHeat = false;
            for (int j = 0; j < heaters.length; j++) {
                if (distance(houses[i], heaters[j]) <= r) {
                    canHeat = true;
                    break;
                }
            }
            if (!canHeat) return false;
        }
        return true;
    }

    private static int distance(int house, int heater) {
        return Math.abs(house - heater);
    }


    public int findUnsortedSubarray(int[] nums) {

        int i = 0;
        while (i < nums.length && isSmallestFromIndex(nums, i++)) ;
        int j = nums.length - 1;
        while (j >= 0 && isBiggestToIndex(nums, j--)) ;
        i--;
        j++;
        return j > i ? j - i + 1 : 0;
    }

    private static boolean isSmallestFromIndex(int[] nums, int fromIndex) {
        int shooter = nums[fromIndex];
        for (int i = fromIndex + 1; i < nums.length; i++) {
            if (nums[i] < shooter) return false;
        }
        return true;
    }

    private static boolean isBiggestToIndex(int[] nums, int toIndex) {
        int shooter = nums[toIndex];
        for (int i = toIndex - 1; i >= 0; i--) {
            if (nums[i] > shooter) return false;
        }
        return true;
    }

    /**
     * from index 0 to index endIndex. both included.
     *
     * @param nums
     * @param endIndex
     * @return
     */
    private static boolean isSorted(int[] nums, int endIndex) {
        if (endIndex == 0) return true;
        return nums[endIndex] >= nums[endIndex - 1] && isSorted(nums, endIndex - 1);
    }

    private static int getInsertIndex(int[] nums, int value) {
        for (int i = 0; i < nums.length; i++) {
            if (value <= nums[i]) {
                return i;
            }
        }
        return nums.length - 1;
    }

    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }


    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int count = 0;
        int prev = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == prev) continue;
            int index = Arrays.binarySearch(nums, i + 1, nums.length, nums[i] + k);
            count += index > 0 && index != i ? 1 : 0;
            prev = nums[i];
        }
        return count;
    }


    public int mySqrt(int x) {
        if (x == 0) return 0;
        if (x == 1 || x == 2 || x == 3) return 1;
        else {
            long lower = 1;
            long upper = x / 2;
            long tmp;
            while (lower < upper) {
                tmp = (lower + upper) / 2;
                if (tmp * tmp == x) {
                    return (int) tmp;
                }
                if (tmp * tmp < x) {
                    if (lower == tmp) {
                        return (int) ((lower + 1) * (lower + 1) > x ? lower : lower + 1);
                    } else {
                        lower = tmp;
                    }
                } else {
                    if (upper != tmp) {
                        upper = tmp;
                    } else {
                        return (int) (upper - 1);
                    }
                }

            }
            Math.sqrt(x);
            return (int) (lower + 1);
        }
    }

    /**
     * Q414
     *
     * @param nums
     * @return
     */
    public int thirdMax(int[] nums) {
        int[] tmp = new int[3];
        Arrays.fill(tmp, Integer.MIN_VALUE);
        int putPlace0Times = 0;
        int putPlace1Times = 0;
        int putPlace2Times = 0;

        HashSet<Integer> tmpSet = new HashSet<>();
        for (int x : nums) {
            if (tmpSet.size() < 3) {
                tmpSet.add(x);
            }
            if (x == tmp[0] || x == tmp[1] || x == tmp[2]) {
                continue;
            }
            if (x > tmp[0]) {
                putPlace0Times++;
                tmp[2] = tmp[1];
                tmp[1] = tmp[0];
                tmp[0] = x;
            } else if (x > tmp[1]) {
                putPlace1Times++;
                tmp[2] = tmp[1];
                tmp[1] = x;
            } else if (x > tmp[2]) {
                putPlace2Times++;
                tmp[2] = x;
            }
        }
        boolean containsLegalResult = tmpSet.size() >= 3;
        return containsLegalResult ? tmp[2] : tmp[0];
    }


    public int countPrimes(int n) {
        //1 2 3 5 7 11 13.

        return -1;
    }


    /**
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            while (left < s.length() && !(s.charAt(left) >= 'a' && s.charAt(left) <= 'z' || s.charAt(left) >= '0' && s.charAt(left) <= '9')) {
                left++;
            }
            while (right >= 0 && !(s.charAt(right) >= 'a' && s.charAt(right) <= 'z' || s.charAt(right) >= '0' && s.charAt(right) <= '9')) {
                right--;
            }
            if (left >= right) {
                return true;
            }
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }


    /**
     * 168. Excel Sheet Column Title
     *
     * @param n
     * @return
     */
    public String convertToTitle(int n) {
        n = n - 1;
        int base = 26;
        StringBuilder sb = new StringBuilder();
        while (n >= base) {
            sb.append(getRelative(n % base));
            n = n / base;
            n = n - 1;
        }
        sb.append(getRelative(n));
        return sb.reverse().toString();
    }

    private static String getRelative(int n) {
        char tmp = (char) (n + 'A');
        return String.valueOf(tmp);
    }


    private static boolean isBadVersion(int version) {
        return false;
    }

    /**
     * Q278
     *
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        int lower = 1;
        int upper = n;
        int tmp = 0;
        while (lower < upper) {
            int newTmp = (int) (.5 * upper + .5 * lower);
            if (newTmp == tmp) {
                return lower;
            }
            tmp = newTmp;
            if (isBadVersion(tmp)) {
                upper = tmp;
            } else {
                lower = tmp;
            }
        }
        return isBadVersion(lower) ? lower : lower + 1;
    }


    public int reverse(int x) {
        boolean negative = x < 0;
        x = Math.abs(x);
        if (x < 0) {
            return 0;
        }
        StringBuilder sb = new StringBuilder(x + "");
        String reversed = sb.reverse().toString();
        String MAX = Integer.MAX_VALUE + "";
        if (reversed.length() == MAX.length() && reversed.compareTo(MAX) > 0) {
            return 0;
        }
        int result = Integer.parseInt(reversed);
        return negative ? -result : result;
    }

    /**
     * Q189
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int[] tmp = new int[k];
        for (int i = nums.length - k; i < nums.length && i >= 0; i++) {
            tmp[i - nums.length + k] = nums[i];
        }
        for (int i = nums.length - k - 1; i >= 0 && i < nums.length; i--) {
            nums[i + k] = nums[i];
        }
        for (int i = 0; i < k; i++) {
            nums[i] = tmp[i];
        }
    }


    /**
     * Q665.
     *
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {
        boolean chance = true;
        int anotherOne[] = nums.clone();
        for (int i = 0; i < nums.length - 1; i++) {
            if (chance && nums[i] > nums[i + 1]) {
                chance = false;
                nums[i + 1] = nums[i];
                anotherOne[i] = anotherOne[i + 1];
                break;
            }
        }

        return isNondescrising(nums) || isNondescrising(anotherOne);
    }

    private boolean isNondescrising(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) return false;
        }
        return true;
    }

    public int largestPalindrome(int n) {
        int base = (int) Math.pow(10, n - 1);
        int end = base * 10 - 1;
        long max = Integer.MIN_VALUE;
        for (long i = end; i >= base; i--) {
            for (long j = i; j >= base; j--) {
                int tmp = getPalindromeLength(i, j);
                if (tmp > 0) {
                    max = Math.max(max, i * j);
                }
            }
        }
        return (int) (max % 1337);
    }


    /**
     * @param number1
     * @param number2
     * @return return -1 if the result is not palindrome, positive result if is a palindrome.
     */
    private int getPalindromeLength(long number1, long number2) {
        String tmp = number1 * number2 + "";
        String rev = new StringBuilder(tmp).reverse().toString();
        if (tmp.compareTo(rev) == 0) {
            return tmp.length();
        }
        return -1;
    }


    public int largestPalindrome2(int n) {
        if (n == 1) return 9;
        int max = (int) Math.pow(10, n) - 1;
        for (int v = max - 1; v > max / 10; v--) {
            long u = Long.valueOf(v + new StringBuilder().append(v).reverse().toString());
            for (long x = max; x * x >= u; x--)
                if (u % x == 0) {
                    return (int) (u % 1337);
                }
        }
        return 0;
    }


    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        map = (HashMap<Character, Integer>) sortMapByValue(map);
        StringBuilder sb = new StringBuilder();
        for (Character c : map.keySet()) {
            int cout = map.get(c);
            for (int i = 0; i < cout; i++) {
                sb.append(c);
            }
        }
        return sb.reverse().toString();
    }

    /**
     * 使用 Map按value进行排序
     *
     * @return
     */
    public static Map<Character, Integer> sortMapByValue(Map<Character, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return oriMap;
        }
        Map<Character, Integer> sortedMap = new LinkedHashMap<Character, Integer>();
        List<Map.Entry<Character, Integer>> entryList = new ArrayList<Map.Entry<Character, Integer>>(oriMap.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });

        Iterator<Map.Entry<Character, Integer>> iter = entryList.iterator();
        Map.Entry<Character, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }


    public List<List<String>> printTree(TreeNode root) {
        int height = heightOfTree(root);
        int array_length = (int) (Math.pow(2, height) - 1);
        List<String[]> list = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            String[] tmp = new String[array_length];
            Arrays.fill(tmp, "");
            list.add(tmp);
        }

        dfs(root, 0, list, 0, array_length);

        List<List<String>> re = new ArrayList<>(height);
        for (String[] tmp : list) {
            re.add(Arrays.asList(tmp));
        }
        return re;
    }

    private int heightOfTree(TreeNode node) {
        if (node == null) return 0;
        return Math.max(heightOfTree(node.left), heightOfTree(node.right)) + 1;
    }

    public void dfs(TreeNode node, int depth, List<String[]> list, int lower, int upper) {
        if (node == null) return;
        int len = list.get(depth).length;
        list.get(depth)[(lower + upper) / 2] = node.val + "";
        dfs(node.left, depth + 1, list, lower, (lower + upper) / 2);
        dfs(node.right, depth + 1, list, (lower + upper) / 2, upper);
    }


    /**
     * Q648 Replace words.
     *
     * @param dict     prefix set.
     * @param sentence the sentence to deal with.
     * @return
     */
    public String replaceWords(List<String> dict, String sentence) {
        dict.sort((o1, o2) -> o1.compareTo(o2));
        HashSet<String> prefix = new HashSet<>();
        prefix.add(dict.get(0));
        for (int i = 1; i < dict.size(); i++) {
            if (dict.get(i).startsWith(dict.get(0))) {
                continue;
            } else {
                prefix.add(dict.get(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        String[] words = sentence.split(" ");
        for (String word : words) {
            boolean havePre = false;
            for (String pre : dict) {
                if (word.startsWith(pre)) {
                    sb.append(pre + " ");
                    havePre = true;
                    break;
                }
            }
            if (!havePre) {
                sb.append(word + " ");
            }
        }
        return sb.toString().trim();
    }


    /**
     * [[1,0,0,0,1,0,0,0,0,0,0,0,0,0,0],    //  0 and 4  4 and 12 5 and 12  1 and 5  6 and 11  10 and 11  11 and 14 7 11 12.
     * [0,1,0,0,0,1,0,0,0,0,0,0,0,0,0],    //
     * [0,0,1,0,0,0,0,0,0,0,0,0,0,0,0],    //  2
     * [0,0,0,1,0,0,0,0,0,0,0,0,0,0,0],    //  3
     * [1,0,0,0,1,0,0,0,0,0,0,0,1,0,0],    //
     * [0,1,0,0,0,1,0,0,0,0,0,0,1,0,0],    //
     * [0,0,0,0,0,0,1,0,0,0,0,1,0,0,0],    //
     * [0,0,0,0,0,0,0,1,0,0,0,1,1,0,0],    //
     * [0,0,0,0,0,0,0,0,1,0,0,0,0,0,0],    //  8 and
     * [0,0,0,0,0,0,0,0,0,1,0,0,0,0,0],    //  9 and
     * [0,0,0,0,0,0,0,0,0,0,1,1,0,0,0],    //
     * [0,0,0,0,0,0,1,1,0,0,1,1,0,0,1],    //
     * [0,0,0,0,1,1,0,1,0,0,0,0,1,0,0],    //   and
     * [0,0,0,0,0,0,0,0,0,0,0,0,0,1,0],    //  13 and
     * [0,0,0,0,0,0,0,0,0,0,0,1,0,0,1]]    //
     *
     * @param M
     * @return
     */
    public int findCircleNum(int[][] M) {
        int[] relations = new int[M.length];
        for (int i = 0; i < relations.length; i++) {
            relations[i] = i;
        }
        for (int i = 0; i < M.length; i++) {
            int[] tmp = M[i];
            for (int j = i + 1; j < M[i].length; j++) {
                if (M[i][j] == 1) {
                    if (relations[j] == j)
                        relations[j] = relations[i];
                    else {
                        int totalMinRelations = Math.min(relations[i], relations[j]);
                        for (int k = 0; k < relations.length; k++) {
                            if (relations[k] == relations[i] || relations[k] == relations[j]) {
                                relations[k] = totalMinRelations;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < relations.length; i++) {
            int tmp = i;
            while (relations[tmp] != tmp) {
                relations[tmp] = relations[relations[tmp]];
                tmp = relations[tmp];
            }
            relations[i] = tmp;
        }

        HashSet<Integer> s = new HashSet<>();
        for (int tmp : relations) {
            s.add(tmp);
        }


        return s.size();
    }


    /**
     * Q565 longest
     *
     * @param nums input data.
     * @return
     */
    public int arrayNesting(int[] nums) {
        int longestLength = -1;
        HashSet<Integer> tmp;
        HashSet<Integer> maxSet = new HashSet<>();
        boolean isVisited[] = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (isVisited[i]) {
                continue;
            }
            int seed = i;
            int preSize = 0;
            tmp = new HashSet();
            while (true) {
                if (maxSet.contains(nums[seed])) {
                    break;
                }
                tmp.add(nums[seed]);
                isVisited[seed] = true;
                if (tmp.size() > preSize) {
                    preSize = tmp.size();
                    seed = nums[seed];
                } else {
                    if (preSize > longestLength) {
                        maxSet = tmp;
                    }
                    longestLength = Math.max(longestLength, preSize);
                    break;
                }
            }
        }
        return longestLength;
    }


    /**
     * 646. Maximum Length of Pair Chain
     *
     * @param pairs
     * @return
     */
//    public int findLongestChain(int[][] pairs) {
//        Arrays.sort(pairs, (o1, o2) -> o1[0] - o2[0] == 0 ? o1[1] - o2[1] : o1[0] - o2[0]);
//        int maxLength = -1;
//        for (int i = 0; i < pairs.length; i++) {
//            int[] prev = null;
//            ArrayList<Integer> chain = new ArrayList<>();
//            for (int j = i; j < pairs.length; j++) {
//                if (prev == null || pairs[j][0] > prev[1]) {
//                    chain.add(j);
//                    prev = pairs[j];
//                }
//            }
//            if (chain.size() > maxLength) maxLength = chain.size();
//        }
//        return maxLength;
//    }
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (o1, o2) -> o1[1] - o2[1] == 0 ? o1[0] - o2[0] : o1[1] - o2[1]);
        //need to remove the pairs that are the same or is
        int maxLength = -1;
        for (int i = 0; i < pairs.length; i++) {
            int[] prev = null;
            int tmpLength = 0;
            for (int j = i; j < pairs.length; j++) {
                if (prev == null || pairs[j][0] > prev[1]) {
                    tmpLength++;
                    prev = pairs[j];
                }
            }
            if (tmpLength > maxLength) maxLength = tmpLength;
        }
        return maxLength;
    }


    /**
     * @param nums the input param.
     */
    public int totalHammingDistance(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int tmp : nums) {
            map.put(tmp, map.getOrDefault(tmp, 0) + 1);
        }
        Integer[] keys = new Integer[map.keySet().size()];
        map.keySet().toArray(keys);
        int sum = 0;
        for (int i = 0; i < keys.length; i++) {
            for (int j = i + 1; j < keys.length; j++) {
                int tmp1 = hammingDis(keys[i], keys[j]);
                int times = (map.get(keys[i]) * map.get(keys[j]));
                sum += tmp1 * times;
            }
        }
        return sum;
    }

    private int hammingDis(int a, int b) {
        int c = a ^ b;
        int counter = 0;
        while (c != 0) {
            counter += c & 1;
            c = c >>> 1;
        }
        return counter;
    }




    public static void main(String[] args) {
        MainInMac m = new MainInMac();
        int[] input = {6, 1, 8, 6, 8};
        System.out.println(m.totalHammingDistance(input));
    }

}
