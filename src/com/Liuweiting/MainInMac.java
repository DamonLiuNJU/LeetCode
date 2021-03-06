package com.Liuweiting;

import com.Liuweiting.DataStructure.ListNode;
import com.Liuweiting.DataStructure.TreeNode;

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
//    HashMap<Character, Character> map = new HashMap<>();
//    HashMap<Character, Character> reversedMap = new HashMap<>();
//
//    public boolean isIsomorphic(String s, String t) {
//        if (s.length() == 0 && t.length() == 0) return true;
//        for (int i = 0; i < s.length(); i++) {
//            char key = s.charAt(i);
//            char mapped = t.charAt(i);
//            if (map.containsKey(key)) {
//                if (map.get(key) != mapped) {
//                    return false;
//                }
//            } else if (reversedMap.containsKey(mapped)) {
//                return false;
//            } else {
//                map.put(key, mapped);
//                reversedMap.put(mapped, key);
//            }
//        }
//        return true;
//    }

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


    /**
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int length = nums.length - 1;
        int tmp = length;
        int counter = 0;
        int k;
        int max[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            max[i] = -1;
            counter = nums.length;
            k = 1;
            for (; k < counter; k++) {
                if (nums[(i + k) % nums.length] > nums[i]) {
                    max[i] = nums[(i + k) % nums.length];
                    break;
                }
            }
        }
        return max;
    }


    /**
     * 454. 4Sum II
     * https://leetcode.com/problems/4sum-ii/description/
     * the target is to find sum to zero indexes.
     *
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCount_V1(int[] A, int[] B, int[] C, int[] D) {
        int c = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                for (int k = 0; k < C.length; k++) {
                    for (int l = 0; l < D.length; l++) {
                        int sum = A[i] + B[j] + C[k] + D[l];
                        c += sum == 0 ? 1 : 0;
                    }
                }
            }
        }
        return c;
    }

    public int fourSumCount_V2(int[] A, int[] B, int[] C, int[] D) {
        int c = 0;
        Arrays.sort(D);
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                for (int k = 0; k < C.length; k++) {
                    int sum = A[i] + B[j] + C[k];
                    if (Arrays.binarySearch(D, -sum) >= 0) {
                        c++;
                    }
                }
            }
        }
        return c;
    }


    /**
     * 672. Bulb Switcher II
     * https://leetcode.com/problems/bulb-switcher-ii/description/
     *
     * @param n
     * @param m
     * @return
     */
    public int flipLights(int n, int m) {
        int totalCount = 0;


        return totalCount;
    }


    /**
     * 623. Add One Row to Tree
     * https://leetcode.com/problems/add-one-row-to-tree/description/
     *
     * @param root
     * @param v
     * @param d
     * @return
     */
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode tmpRoot = new TreeNode(v);
            tmpRoot.left = root;
            return tmpRoot;
        }
        dfs(root, 1, d, v);
        return root;
    }

    private void dfs(TreeNode node, int depth, int targetDepth, int value) {
        if (node == null) {
            if (depth == targetDepth) {
                node = new TreeNode(value);
            }
            return;
        }
        if (targetDepth == depth) {
            TreeNode tmpLeft = new TreeNode(value);
            TreeNode tmpRight = new TreeNode(value);
            tmpLeft.left = node.left;
            tmpRight.right = node.right;
            node.left = tmpLeft;
            node.right = tmpRight;
        } else {
            dfs(node.left, depth + 1, targetDepth, value);
            dfs(node.right, depth + 1, targetDepth, value);
        }
    }

    /**
     * 592. Fraction Addition and Subtraction
     * https://leetcode.com/problems/fraction-addition-and-subtraction/description/
     *
     * @param expression
     * @return
     */
    public String fractionAddition(String expression) {
        boolean firstNegative = expression.startsWith("-");
        if (firstNegative) expression = expression.substring(1);
        int length = 1;
        for (int i = 1; i < expression.length(); i++) {
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-') {
                length++;
            }
        }
        String[] numbers = new String[length];
        int currIndex = 0;
        int beginIndex = -1;
        for (int i = 0; i < expression.length(); i++) {
            if (beginIndex < 0) beginIndex = i;
            if (i - 1 >= 0 && expression.charAt(i - 1) == '-') {
                beginIndex = i - 1;
            }
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-') {
                numbers[currIndex++] = expression.substring(beginIndex, i);
                beginIndex = -1;
            }
        }
        numbers[length - 1] = expression.substring(beginIndex);
        String sum = (firstNegative ? "-" : "") + numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            String tmp = numbers[i];
            sum = addTwoNumbers(tmp, sum);
        }
        return sum;
    }

    /**
     * add two numbers in fraction style.
     *
     * @param num1
     * @param num2
     * @return
     */
    private static String addTwoNumbers(String num1, String num2) {
        boolean num1negative = num1.startsWith("-");
        int num1son = Integer.parseInt(num1.substring(num1negative ? 1 : 0, num1.indexOf('/')));
        if (num1negative) num1son = -num1son;
        int num1mom = Integer.parseInt(num1.substring(num1.indexOf('/') + 1));

        boolean num2negative = num2.startsWith("-");
        int num2son = Integer.parseInt(num2.substring(num2negative ? 1 : 0, num2.indexOf('/')));
        if (num2negative) num2son = -num2son;
        int num2mom = Integer.parseInt(num2.substring(num2.indexOf('/') + 1));

        if (num1mom >= num2mom && num1mom % num2mom == 0) {
            int num2times = num1mom / num2mom;
            num2son *= num2times;
            int resultSon = num1son + num2son;
            return getSimplifiedFraction(resultSon, num1mom);
        }


        if (num1mom <= num2mom && num2mom % num1mom == 0) {
            int num2times = num2mom / num1mom;
            num1son *= num2times;
            int part1 = num1son;
            int resultSon = part1 + num2son;
            return getSimplifiedFraction(resultSon, num2mom);
        }

//        int son = (num1negative?-(num1son * num2mom):(num1son * num2mom)) + (num2negative?-(num2son * num1mom):(num2son * num1mom));
        int son = num1son * num2mom + num2son * num1mom;
        int mon = num1mom * num2mom;
        return getSimplifiedFraction(son, mon);
    }

    private static String getSimplifiedFraction(int son, int mom) {

        if (son == 0) return son + "/" + 1;
        if (son == 1 || mom == 1) return son + "/" + mom;


        for (int i = 2; i <= Math.min(Math.abs(son), Math.abs(mom)); i++) {
            if (son % i == 0 && mom % i == 0) {
                return getSimplifiedFraction(son / i, mom / i);
            }
        }
        return son + "/" + mom;
    }


    /**
     * 421. Maximum XOR of Two Numbers in an Array
     * https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/description/
     * Other options: need to solve in O(n) time complexity.
     *
     * @param nums
     * @return
     */
    public int findMaximumXOR(int[] nums) {
        int prevMaxIndex = 0;
        int prevMaxIndex2 = 1;
        int max = nums[0] ^ nums[1];
        for (int i = 2; i < nums.length; i++) {
            int max1 = max ^ nums[prevMaxIndex] ^ nums[i];
            int max2 = max ^ nums[prevMaxIndex2] ^ nums[i];
            if (max1 > max2 && max1 > max) {
                prevMaxIndex = i;
                max = max1;
            } else if (max2 > max) {
                prevMaxIndex2 = i;
                max = max2;
            }
        }
        return nums[prevMaxIndex] ^ nums[prevMaxIndex2];
    }


    public int countNumbersWithUniqueDigits(int n) {
        if (n == 1) return 10;
        if (n > 10) {
            n = 10;
        }
        int orig = n;
        int total = 1;
        int currentChoice = 9;
        while (n > 1) {
            total *= currentChoice;
            currentChoice--;
            n--;
        }
        total *= (currentChoice + 1);

        return total + countNumbersWithUniqueDigits(orig - 1);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode reversedL1 = reverseList(l1);
        ListNode reversedL2 = reverseList(l2);
        int carryIn = 0;
        ListNode preL1 = null, preL2;
        ListNode newList = null;
        ListNode current = null;
        while (reversedL1 != null || reversedL2 != null) {
            int currentL1val = reversedL1 == null ? 0 : reversedL1.val;
            int currentL2val = reversedL2 == null ? 0 : reversedL2.val;
            int addResult = currentL1val + currentL2val + carryIn;
            carryIn = addResult / 10;
            addResult %= 10;
            if (newList == null) {
                newList = new ListNode(addResult);
                current = newList;
            } else {
                current.next = new ListNode(addResult);
                current = current.next;
            }
            if (reversedL1 != null)
                reversedL1 = reversedL1.next;
            if (reversedL2 != null)
                reversedL2 = reversedL2.next;
        }
        if (carryIn != 0) {
            current.next = new ListNode(carryIn);
        }
        ListNode re = reverseList(newList);
        return re;
    }

    private static ListNode reverseList(ListNode root) {
        ListNode tmpPre = null;
        while (root != null) {
            ListNode originNext = root.next;
            root.next = tmpPre;
            tmpPre = root;
            root = originNext;
        }
        return tmpPre;
    }

    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int M = matrix.length; //row count;
        int N = matrix[0].length; // column count;
        int result[] = new int[M * N];
        int x = 0, y = 0;
        boolean goUp = true;
        for (int i = 0; i < result.length; i++) {
            result[i] = matrix[x][y];
            if (goUp && (x == 0 || y == N - 1)) {
                goUp = false;
                if (x == 0 && y < N - 1) {
                    y++;
                } else {
                    x++;
                }
                continue;
            } else if (!goUp && (y == 0 || x == M - 1)) {
                goUp = true;
                if (y == 0 && x < M - 1) {
                    x++;
                } else {
                    y++;
                }
                continue;
            }
            if (goUp) {
                x--;
                y++;
            } else {
                x++;
                y--;
            }
        }
        return result;
    }


    public int findMinDifference(List<String> timePoints) {
        timePoints.sort((o1, o2) -> o1.compareTo(o2));
        String time0 = timePoints.get(0);
        int MIN = Integer.MAX_VALUE;
        for (int i = 1; i < timePoints.size(); i++) {
            MIN = Integer.min(MIN, timeGap(timePoints.get(i - 1), timePoints.get(i)));
        }
        MIN = Integer.min(MIN, timeGap(timePoints.get(timePoints.size() - 1), timePoints.get(0)));
        return MIN;
    }


    /**
     * assume calculate time from
     *
     * @param t1
     * @param t2
     * @return
     */
    private static int timeGap(String t1, String t2) {
        int h1 = Integer.parseInt(t1.substring(0, t1.indexOf(':')));
        int m1 = Integer.parseInt(t1.substring(t1.indexOf(':') + 1));

        int h2 = Integer.parseInt(t2.substring(0, t2.indexOf(':')));
        int m2 = Integer.parseInt(t2.substring(t2.indexOf(':') + 1));

        int tmp;
        if (h1 == h2) {
            return Math.abs(m1 - m2);
        } else if (h1 < h2) {
            tmp = (h2 - (h1 + 1)) * 60 + (60 - m1) + m2;
        } else {
            tmp = (h1 - (h2 + 1)) * 60 + (60 - m2) + m1;
        }
        tmp = Math.min(tmp, 24 * 60 - tmp);
        return tmp;
    }


    /**
     * 22. Generate Parentheses
     * https://leetcode.com/problems/generate-parentheses/description/
     *
     * @param n
     * @return
     */
//    public List<String> generateParenthesis(int n) {
//        return lengthGenerate(n);
//    }
//
//    private static List<String> lengthGenerate(int n) {
//        Set<String> list = new HashSet<>();
//        if (n == 1) {
//            List<String> tmp = new ArrayList();
//            tmp.add("()");
//            return tmp;
//        }
//        if (n==2){
//            List<String> tmp = new ArrayList();
//            tmp.add("()()");
//            tmp.add("(())");
//            return tmp;
//        }
//
//        for (int i = 1; i <= n / 2; i++) {
//            for (String tmp : lengthGenerate(n - i)) {
//                for (String tmp2 : lengthGenerate(i)){
//                    list.add(tmp + tmp2);
//                    list.add(tmp2 + tmp);
//                }
//            }
//        }
//
//        for (String tmp : lengthGenerate(n - 1)) {
//            list.add("("+tmp+")");
//        }
////        if (n % 2==0){
////            for(String tmp : lengthGenerate(n / 2)){
////                for (String tmp2 : lengthGenerate(n / 2)){
////                    list.add(tmp + tmp2);
////                }
////            }
////        }
//        String[] ar = list.toArray(new String[list.size()]);
//        List<String> tmp = Arrays.asList(ar);
//        return tmp;
//    }
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        helper(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    public void helper(List<String> res, StringBuilder sb, int left, int right, int max) {
        if (sb.length() == max * 2) {
            res.add(sb.toString());
            return;
        }
        if (left < max) {
            int len = sb.length();
            helper(res, sb.append('('), left + 1, right, max);
            sb.setLength(len);
        }
        if (right < left) {
            int len = sb.length();
            helper(res, sb.append(')'), left, right + 1, max);
            sb.setLength(len);
        }
    }

    private static void printDiff(String[] s1, String[] s2) {
        Set<String> list = new HashSet<>();
        for (String tmp : s1) {
            boolean have = false;
            for (String tmp2 : s2) {
                if (tmp.compareTo(tmp2) == 0) {
                    have = true;
                    break;
                }
            }
            if (!have)
                list.add(tmp);
        }

        for (String tmp : s2) {
            boolean have = false;
            for (String tmp2 : s1) {
                if (tmp.compareTo(tmp2) == 0) {
                    have = true;
                    break;
                }
            }
            if (!have)
                list.add(tmp);
        }
        String[] ar = list.toArray(new String[list.size()]);
        System.out.println(Arrays.toString(ar));
    }


    /**
     * 216. Combination Sum III
     * https://leetcode.com/problems/combination-sum-iii/description/
     *
     * @param k
     * @param n
     * @return
     */
    List<List<Integer>> re = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        if (n > 45 || (n == 45 && k < 9)) {
            return new ArrayList<>();
        }
        if (n == 45 && k == 9) {
            Integer[] tmp = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            re.add(Arrays.asList(tmp));
            return re;
        }

//        Set<Integer> alreadyTaken =
        dfs(n, k, new ArrayList<Integer>());

        return re;
    }

    private void dfs(int target, int possibleChoice, ArrayList<Integer> alreadyTaken) {
        if (possibleChoice == 0) {
            if (target == 0) {
                re.add(alreadyTaken);
            }
            return;
        }
        int alreadyMax = 0;
        for (int tmp : alreadyTaken) {
            alreadyMax = Math.max(alreadyMax, tmp);
        }

        for (int i = alreadyMax + 1; i <= 9; i++) {
            boolean tag = false;
            for (int index : alreadyTaken) {
                if (index == i) {
                    tag = true;
                }
            }
            if (!tag) {
                ArrayList<Integer> tmp = (ArrayList<Integer>) alreadyTaken.clone();
                tmp.add(i);
                dfs(target - i, possibleChoice - 1, tmp);
            }
        }
    }


    /**
     * 554. Brick Wall
     * https://leetcode.com/problems/brick-wall/description/
     *
     * @param wall
     * @return
     */
    public int leastBricks(List<List<Integer>> wall) {

        int height = wall.size();
        int width = 0;
        int mostCount = 0;
        int newVal;
        int cur;

        HashMap<Integer, Integer> positionCount = new HashMap<>();

        for (int tmp : wall.get(0)) {
            width += tmp;
        }

        for (List<Integer> oneLine : wall) {
            cur = 0;
            for (int i = 0; i < oneLine.size(); i++) {
                oneLine.set(i, cur + oneLine.get(i));
                cur = oneLine.get(i);
                if (cur < width) {
                    newVal = positionCount.getOrDefault(cur, 0) + 1;
                    positionCount.put(cur, newVal);
                    mostCount = Math.max(newVal, mostCount);
                }
            }
        }

        return height - mostCount;
    }


    /**
     * 144. Binary Tree Preorder Traversal
     * https://leetcode.com/problems/binary-tree-preorder-traversal/description/
     *
     * @param root
     * @return
     */
    List<Integer> result = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root != null) {
            result.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
        return result;
    }

    /**
     * 481. Magical String
     * https://leetcode.com/problems/magical-string/description/
     *
     * @param n
     * @return
     */
    public int magicalString(int n) {
        String base = "122112122";
        int counter = 0;
        while (base.length() < n) {
            boolean start = true;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < base.length(); i++) {
                if (base.charAt(i) == '1') {
                    sb.append(start ? 1 : 2);
                    start = !start;
                } else {
                    sb.append(start ? 1 : 2);
                    sb.append(start ? 1 : 2);
                    start = !start;
                }
            }
            base = sb.toString();
        }
        for (int i = 0; i < n; i++) {
            if (base.charAt(i) == '1') {
                counter++;
            }
        }
        return counter;
    }


    /**
     * 12. Integer to Roman
     *
     * @param num 1~3999
     *            <p>
     *            把一个给定的罗马字符转为数字。首先要了解罗马字符表示的规则。
     *            一，羅馬數字共有7個，即I（1）、V（5）、X（10）、L（50）、C（100）、D（500）和M（1000）。
     *            二，在較大的羅馬數字的右邊記上較小的羅馬數字，表示大數字加小數字，例如：VI（6），VIII（8），LV（55=50 + 5），LX（60=50 + 10）
     *            三，在較大的羅馬數字的左邊記上較小的羅馬數字，表示大數字减小數字，例如：IX（9），XL（40=50 - 10），XC（90=100 - 10）
     *            四，左减的数字有限制，仅限于I、X、C。比如45不可以写成VL，只能是XLV。
     *            五，左減時不可跨越一個位數。比如，99不可以用IC（100 - 1）表示，而是用XCIX（[100 - 10] + [10 - 1]）表示。
     * @return
     */
    public String intToRoman(int num) {
        //TODO: try to understand this problem.
        return null;
    }

    private static int Char2Int(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }
        return -1;
    }

    private static char Int2Char(int c) {
        switch (c) {
            case 1:
                return 'I';
            case 5:
                return 'V';
            case 10:
                return 'X';
            case 50:
                return 'L';
            case 100:
                return 'C';
            case 500:
                return 'D';
            case 1000:
                return 'M';
        }
        return 'E';
    }

    /**
     * Q378.https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi)
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0, j = matrix[0].length - 1;
            for (int i = 0; i < matrix.length; i++) {
                while (j >= 0 && matrix[i][j] > mid) j--;
                count += (j + 1);
            }
            if (count < k) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    /**
     * Q287
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int low = 1;
        int high = nums.length;  //[low,high)
        int counter = 0;
        while (low < high) {
            counter = 0;
            int mid = low + (high - low) / 2;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    counter++;
                    if (counter > mid) {
                        break;
                    }
                }
            }
            if (counter > mid) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }


    /**
     * Q142. Linked List Cycle II
     * https://leetcode.com/problems/linked-list-cycle-ii/description/
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        do {
            if (slow == null || fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        while (slow != fast); // question is, this meet point is not necessarily the entry point, it's just a point in cycle.

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }


    /**
     * 486. Predict the Winner
     * https://leetcode.com/problems/predict-the-winner/description/
     *
     * @param nums
     * @return
     */
    public boolean PredictTheWinner(int[] nums) {
//        return predictHelper(nums, 0, nums.length, 0, 0, true);
        return player1CanWin(nums, 0, nums.length, 0, 0);
    }

    private boolean player1CanWin(int[] nums, int beginIndex, int endIndex, int player1score, int player2score) {
        if (endIndex - beginIndex == 1) {
            return player1score + nums[beginIndex] >= player2score;
        }
        return !player2CanWin(nums, beginIndex + 1, endIndex, player1score + nums[beginIndex], player2score)
                || !player2CanWin(nums, beginIndex, endIndex - 1, player1score + nums[endIndex - 1], player2score);
    }

    private boolean player2CanWin(int[] nums, int beginIndex, int endIndex, int player1score, int player2score) {
        if (endIndex - beginIndex == 1) {
            return player2score + nums[beginIndex] > player1score;
        }
        return !player1CanWin(nums, beginIndex + 1, endIndex, player1score, player2score + nums[beginIndex])
                || !player1CanWin(nums, beginIndex, endIndex - 1, player1score, player2score + nums[endIndex - 1]);
    }

    private boolean predictHelper(int[] nums, int begin, int end, int player1score, int player2score, boolean player1round) {
        if (begin == end) {
            return player1score >= player2score;
        }
        boolean dfs = false;
        if (player1round) {
            return predictHelper(nums, begin + 1, end, player1score + nums[begin], player2score, !player1round) ||
                    predictHelper(nums, begin, end - 1, player1score + nums[end - 1], player2score, !player1round);
        } else {
            return predictHelper(nums, begin + 1, end, player1score, player2score + nums[begin], !player1round) ||
                    predictHelper(nums, begin, end - 1, player1score, player2score + nums[end - 1], !player1round);
        }
    }


    /**
     * 46. Permutations
     * https://leetcode.com/problems/permutations/description/
     *
     * @param nums
     * @return
     */
    List<List<Integer>> permuteResult = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> indexes = permute(nums.length, nums.length);
        for (List<Integer> index : indexes) {
            List<Integer> result = new ArrayList<>();
            for (Integer i : index) {
                result.add(nums[i]);
            }
            permuteResult.add(result);
        }

        return permuteResult;
    }

    /**
     * return 0~n-1 's permute.
     *
     * @param n
     * @return
     */
    private static List<List<Integer>> permute(int n, int currentLeft) {

        if (currentLeft == 1) {
            List<List<Integer>> re = new ArrayList<>(currentLeft);
            for (int i = 0; i < n; i++) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(i);
                re.add(tmp);
            }
            return re;
        }

        List<List<Integer>> newre = new ArrayList<>();
        List<List<Integer>> tmp = permute(n, currentLeft - 1);
        for (List<Integer> list : tmp) {
            for (int i = 0; i < n; i++) {
                if (!list.contains(i)) {
                    List<Integer> newlist = (List<Integer>) ((ArrayList) list).clone();
                    newlist.add(i);
                    newre.add(newlist);
                }
            }
        }
        return newre;
    }


    /**
     * 318. Maximum Product of Word Lengths
     * https://leetcode.com/problems/maximum-product-of-word-lengths/description/
     * Given a string array words, find the maximum value of length(word[i]) * length(word[j])
     * where the two words do not share common letters. You may assume that each word will contain
     * only lower case letters. If no such two words exist, return 0.
     *
     * @param
     * @return
     */

    private static int relative(char tmp) {
        return tmp - 'a';
    }

    public int maxProduct(String[] words) {
        int maxLength = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                boolean used[] = new boolean[26];
                Arrays.fill(used, false);
                for (char tmp : words[i].toCharArray()) {
                    used[relative(tmp)] = true;
                }
                boolean haveCommon = false;
                for (char c : words[j].toCharArray()) {
                    if (used[relative(c)]) {
                        haveCommon = true;
                        break;
                    }
                }
                if (!haveCommon) {
                    maxLength = Math.max(maxLength, words[i].length() * words[j].length());
                }
            }
        }
        return maxLength;
    }


    /**
     * 423. Reconstruct Original Digits from English
     * zero -- z   six -- x  eight -- g  two -- w  three -- h(after eight)  four(r)  one(o) five(f) seven(v)
     * nIne(*)
     *
     * @param s input String.
     * @return the original result.
     */
    public String originalDigits(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[relative(c)]++;
        }
        ArrayList<Integer> list = new ArrayList<>();
        removeThings(count, 'z', "zero", list, 0);
        removeThings(count, 'x', "six", list, 6);
        removeThings(count, 'g', "eight", list, 8);
        removeThings(count, 'w', "two", list, 2);
        removeThings(count, 'h', "three", list, 3);
        removeThings(count, 'r', "four", list, 4);
        removeThings(count, 'o', "one", list, 1);
        removeThings(count, 'f', "five", list, 5);
        removeThings(count, 'v', "seven", list, 7);
        removeThings(count, 'i', "nine", list, 9);

        list.sort((o1, o2) -> o1 - o2);
        StringBuilder sb = new StringBuilder();
        for (int tmp : list) {
            sb.append(tmp);
        }
        return sb.toString();
    }

    private static void removeThings(int[] count, char key, String word, List<Integer> list, int num) {
        while (count[relative(key)] > 0) {
            list.add(num);
            for (char c : word.toCharArray()) {
                count[relative(c)]--;
            }
        }
    }


    /**
     * 230. Kth Smallest Element in a BST
     * https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        TreeNode tmp = root;
        while (tmp.left != null) {
            tmp = tmp.left;
        }
        int lo = -1, high = -1;
        lo = tmp.val;
        tmp = root;
        while (tmp.right != null) {
            tmp = tmp.right;
        }
        high = tmp.val + 1;
        int mid = -1;
        while (lo < high) {
            mid = lo + (high - lo) / 2;
            int count = 0;
            int totalCount = dfs(mid, root);
            System.out.println("search for : " + mid + " result : " + totalCount);
            if (totalCount > k) {
                high = mid;
            } else if (totalCount < k) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        return lo;
    }

    private static int dfs(int val, TreeNode root) {
        if (root == null) return 0;
        if (root.val > val) {
            return dfs(val, root.left);
        }
        if (root.val <= val) {
            return 1 + dfs(val, root.left) + dfs(val, root.right);
        }
        return 0;
    }


    /**
     * 452. Minimum Number of Arrows to Burst Balloons
     * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/description/
     *
     * @param points points of ballons.
     * @return number of arrows.
     */
//    int lo = Integer.MAX_VALUE, high = -1;
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, (a, b) -> a[1] - b[1]);
        int arrowPos = points[0][1];
        int arrowCnt = 1;
        for (int i = 1; i < points.length; i++) {
            if (arrowPos >= points[i][0]) {
                continue;
            }
            arrowCnt++;
            arrowPos = points[i][1];
        }
        return arrowCnt;
    }


    /**
     * find a max point and shoot. return the shoot result.
     *
     * @param points
     * @param count
     * @return
     */
    private void shoot(int[][] points, Map<Integer, Integer> count, int shootPoint) {
        for (int[] point : points) {
            if (shootPoint <= point[1] && shootPoint >= point[0]) {
                count.keySet().stream().filter(key -> key >= point[0] && key <= point[1]).forEach(key -> count.put(key, count.get(key) - 1));
            }
        }
    }


    /**
     * 241. Different Ways to Add Parentheses
     * https://leetcode.com/problems/different-ways-to-add-parentheses/description/
     *
     * @param input
     * @return
     */
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ret = new LinkedList<Integer>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '-' ||
                    input.charAt(i) == '*' ||
                    input.charAt(i) == '+') {
                String part1 = input.substring(0, i);
                String part2 = input.substring(i + 1);
                List<Integer> part1Ret = diffWaysToCompute(part1);
                List<Integer> part2Ret = diffWaysToCompute(part2);
                for (Integer p1 : part1Ret) {
                    for (Integer p2 : part2Ret) {
                        int c = 0;
                        switch (input.charAt(i)) {
                            case '+':
                                c = p1 + p2;
                                break;
                            case '-':
                                c = p1 - p2;
                                break;
                            case '*':
                                c = p1 * p2;
                                break;
                        }
                        ret.add(c);
                    }
                }
            }
        }
        if (ret.size() == 0) {
            ret.add(Integer.valueOf(input));
        }
        return ret;
    }


    /**
     * 328. Odd Even Linked List
     * https://leetcode.com/problems/odd-even-linked-list/description/
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return head;
        ListNode originHead = head, originEvenHead = head.next, curOdd = head, tmpOdd;
        while (true) {
            if (curOdd.next == null) {
                curOdd.next = originEvenHead;
                break;
            }
            tmpOdd = curOdd.next == null ? null : curOdd.next.next;
            curOdd.next.next = tmpOdd == null ? null : tmpOdd.next;
            if (tmpOdd == null) {
                curOdd.next = originEvenHead;
                break;
            }
            curOdd.next = tmpOdd;
            curOdd = tmpOdd;
        }
        return originHead;
    }


    /**
     * 650. 2 Keys Keyboard
     * https://leetcode.com/problems/2-keys-keyboard/description/
     *
     * @param n
     * @return
     */
    public int minSteps(int n) {
        if (n == 1) return 0;
        minStep = Integer.MAX_VALUE;
        minStepHelper(n, 1, 1, 1); // n the length target, 1 the currentLength, 1 already toke a copy op.
        return minStep;
    }

    static int minStep = Integer.MAX_VALUE;

    private static void minStepHelper(int n, int currentLength, int currentOp, int base) {
        if (currentLength == n) {
            minStep = Math.min(minStep, currentOp);
            return;
        }
        if (currentOp > minStep || currentOp > n || currentLength > n) return;
        if (currentLength * 2 <= n) minStepHelper(n, currentLength * 2, currentOp + 2, currentLength);
        if (currentLength + base <= n) minStepHelper(n, currentLength + base, currentOp + 1, base);
    }


    /**
     * 337. House Robber III
     * https://leetcode.com/problems/house-robber-iii/description/
     *
     * @param root
     * @return
     */
    HashMap<TreeNode, Integer> node2max = new HashMap<>();

    public int rob(TreeNode root) {
        //condition 1 take root.
        if (node2max.containsKey(root)) return node2max.get(root);
        if (root == null) {
            return 0;
        }
        int v1 = root.val + (root.left == null ? 0 : (rob(root.left.left) + rob(root.left.right))) + (root.right == null ? 0 : (rob(root.right.left) + rob(root.right.right)));

        //condition 2 do not take root.
        int v2 = rob(root.left) + rob(root.right);
        v1 = v2 > v1 ? v2 : v1;
        node2max.put(root, v1);
        return v1;
    }


    /**
     * 494. Target Sum
     * https://leetcode.com/problems/target-sum/description/
     * kind of brute force solution, time limit exceed.
     * consider sort the input array.
     */
    static int S;
    static int[] nums;
    static boolean[] isAdd;
    static int counter = 0;

    public int findTargetSumWays1(int[] nums, int S) {
        this.S = S;
        this.nums = nums;
        isAdd = new boolean[nums.length];
        Arrays.fill(isAdd, false);
        counter = 0;
        helper(0);
        return counter;
    }

    private static void helper(int curIndex) {
        if (curIndex == nums.length) {//end condition.
            int sum = 0;
            for (int i = 0; i < nums.length; i++) sum += (isAdd[i] ? (1) : (-1)) * nums[i];
            if (sum == S) counter++;
            return;
        }
        //condition1. set curIndex as false;
        isAdd[curIndex] = false;
        helper(curIndex + 1);

        //condition2. set curIndex as true.
        isAdd[curIndex] = true;
        helper(curIndex + 1);
    }

    static int c = 0;

    public int findTargetSumWays2(int[] nums, int S) {
        canFormTarget(nums, 0, S);
        return c;
    }

    private void canFormTarget(int[] nums, int index, int target) {
        if (index == nums.length) {
            c += (target == 0) ? 1 : 0;
            return;
        }
        canFormTarget(nums, index + 1, target - nums[index]);
        canFormTarget(nums, index + 1, target + nums[index]);
    }

    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0)
            return 0;
        int sum = 0;
        for (int num : nums)
            sum += num;
        if ((sum + S) % 2 != 0 || sum < S || -sum > S)
            return 0;
        return helper(nums, (sum + S) / 2);
    }

    public int helper(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int n : nums)
            for (int i = target; i >= n; i--)
                dp[i] += dp[i - n];
        return dp[target];
    }


    /**
     * 524. Longest Word in Dictionary through Deleting
     *
     * @param s
     * @param d
     * @return
     */
    public String findLongestWord(String s, List<String> d) {
        String re = "";
        int MAX_LENGTH = 0;
        for (String tmp : d) {
            char[] c1 = s.toCharArray(), c2 = tmp.toCharArray();
            if (isSubsequence(c1, c2, 0, 0)) {
                if (c2.length > MAX_LENGTH) {
                    re = tmp;
                    MAX_LENGTH = c2.length;
                } else if (c2.length == MAX_LENGTH) {
                    if (tmp.compareTo(re) < 0) {
                        re = tmp;
                        MAX_LENGTH = c2.length;
                    }
                }
            }
        }
        return re;
    }

    /**
     * actually this method can be transformed into none recursive form.
     * TODO: simplify this method, make it run faster.
     *
     * @param s
     * @param tmp
     * @param index1
     * @param index2
     * @return
     */
    private static boolean isSubsequence(char[] s, char[] tmp, int index1, int index2) {
        if (index2 == tmp.length) return true;
        int i = -1;
        for (int j = index1; j < s.length; j++) {
            if (s[j] == tmp[index2]) {
                i = j;
                break;
            }
        }
        return i >= 0 && isSubsequence(s, tmp, i + 1, index2 + 1);
    }


    class Log {
        //function_id:start_or_end:timestamp
        public int functionId, timeStamp, innerTaken;
        public boolean isStart;

        public Log(String log) {
            String[] t = log.split(":");
            functionId = Integer.parseInt(t[0]);
            timeStamp = Integer.parseInt(t[2]);
            isStart = t[1].startsWith("s");
            innerTaken = 0;
        }
    }

    /**
     * 636. Exclusive Time of Functions
     * https://leetcode.com/problems/exclusive-time-of-functions/description/
     *
     * @param n    total n functions, id from 0 to n-1;
     * @param logs log list, size should be exactly 2 * n;
     * @return the times cost by each function.
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        Stack<Log> stack = new Stack<>();
        int timeLong;
        for (String log : logs) {
            Log l = new Log(log);
            if (l.isStart) {
                stack.push(l);
            } else {
                Log prevStart = stack.pop();
                timeLong = (l.timeStamp - prevStart.timeStamp + 1 - prevStart.innerTaken);
                result[prevStart.functionId] += timeLong;
                for (Log tmpLog : stack) {
                    tmpLog.innerTaken += timeLong;
                }
            }
        }
        return result;
    }

    /**
     * 638. Shopping Offers
     * https://leetcode.com/problems/shopping-offers/description/
     *
     * @param price the prices list.
     * @param special some offers.
     * @param needs the amound needed to get.
     * @return minimum price.
     */
    static int MIN = Integer.MAX_VALUE;
    static List<Integer> price;
    static List<List<Integer>> special;
    static int N;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        MIN = Integer.MAX_VALUE;
        this.special = special;
        this.price = price;
        this.N = price.size();
        shoppingOffersHelper(needs, 0);
        return MIN;
    }

    private static void shoppingOffersHelper(List<Integer> needs, int currentSpend) {
        boolean canFindOfferToTake = false;
        for (List<Integer> offer : special) {
            boolean canTake = canTakeOffer(needs, offer);
            if (canTake) {
                canFindOfferToTake = true;
                List<Integer> newNeeds = new ArrayList<>(N);
                for (int i = 0; i < N; i++) {
                    newNeeds.add(needs.get(i) - offer.get(i));
                }
                shoppingOffersHelper(newNeeds, currentSpend + offer.get(N - 1));
            }
        }

        if (!canFindOfferToTake) {
            int cost = currentSpend;
            for (int i = 0; i < N; i++) {
                cost += needs.get(i) * price.get(i);
            }
            MIN = Math.min(MIN, cost);
        }
    }

    private static boolean canTakeOffer(List<Integer> needs, List<Integer> offer) {
        for (int i = 0; i < N; i++) {
            if (offer.get(i) > needs.get(i)) {
                return false;
            }
        }
        // if it goes here, that means this offer is under the needs. but still need to consider if offer is really saving money.
        int originCost = 0;
        for (int i = 0; i < N; i++) {
            originCost += offer.get(i) * price.get(i);
        }
        return originCost >= offer.get(N);
    }


    /**
     * 681. Next Closest Time
     *
     * @param time
     * @return
     */
    static String anchorTime;
    static String resultTime;
    static int currentGap;
    static List<Character> possibleAlpha;

    public String nextClosestTime(String time) {
        this.anchorTime = time;
        this.resultTime = null;
        currentGap = Integer.MAX_VALUE;
        List<Character> list = new ArrayList<>();
        for (char tim : time.toCharArray()) {
            if (tim != ':') {
                list.add(tim);
            }
        }
        this.possibleAlpha = list;
        helper("");
        return resultTime;
    }

    private static void helper(String currentTime) {
        if (currentTime.length() == 5) {
            if (isTimeValid(currentTime)) {
                int tmpGap = getTimeGapBetween(anchorTime, currentTime);

                if (tmpGap < currentGap) {
                    currentGap = tmpGap;
                    resultTime = currentTime;
                }
            }
            return;
        }
        if (currentTime.length() == 2) {
            helper(currentTime + ":");
            return;
        }

        for (int i = 0; i < possibleAlpha.size(); i++) {
            helper(currentTime + possibleAlpha.get(i));
        }
    }

    /**
     * 0 if anchor == time, (negative if time is before anchor and the return value will be + 24h,
     *
     * @param anchor the anchor time spot.
     * @param time   the calculating time.
     * @return the gap.
     */
    private static int getTimeGapBetween(String anchor, String time) {
        int h1 = Integer.parseInt(anchor.substring(0, 2));
        int m1 = Integer.parseInt(anchor.substring(3, 5));
        int t1 = h1 * 60 + m1;

        int h2 = Integer.parseInt(time.substring(0, 2));
        int m2 = Integer.parseInt(time.substring(3, 5));
        int t2 = h2 * 60 + m2;

        return t2 > t1 ? t2 - t1 : 24 * 60 + t2 - t1;
    }

    private static boolean isTimeValid(String time) {
        int h = Integer.parseInt(time.substring(0, 2));
        int m = Integer.parseInt(time.substring(3, 5));
        return 0 <= h && h <= 23 && 0 <= m && m <= 59;
    }


    public int longestPalindromeSubseq(String s) {
        return getMaxSubsequenceLength(s, new StringBuilder(s).reverse().toString());
    }


    private int getMaxSubsequenceLength(String word1, String word2) {
        int[][] map = new int[word1.length() + 1][word2.length() + 1];
        int real_i, real_j;
        for (int i = 1; i < word1.length() + 1; i++) {
            for (int j = 1; j < word2.length() + 1; j++) {
                real_i = i - 1;
                real_j = j - 1;
                if (word1.charAt(real_i) == word2.charAt(real_j)) {
                    map[i][j] = 1 + map[i - 1][j - 1];
                } else {
                    map[i][j] = Math.max(map[i - 1][j], map[i][j - 1]);
                }
            }
        }
        return map[word1.length()][word2.length()];
    }


    //TODO:think about how to solve this.
    public int characterReplacement(String s, int k) {


        return -1;
    }

    /**
     * 449. Serialize and Deserialize BST
     * https://leetcode.com/problems/serialize-and-deserialize-bst/description/
     *
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        return serializeHelper(root);
    }

    private String serializeHelper(TreeNode root) {
        if (root == null) return "";
        String left = serializeHelper(root.left);
        String right = serializeHelper(root.right);
        return root.val + "#" + left.length() + "(" + left + ")(" + right + ")";
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) return null;
        int val = 0, leftLength = 0, rightLength = 0;
        String leftStr, rightStr;
        int i;
        for (i = 0; i < data.length(); i++) {
            if ('#' == data.charAt(i)) {
                val = Integer.parseInt(data.substring(0, i));
                break;
            }
        }
        int j;
        for (j = i + 1; j < data.length(); j++) {
            if ('(' == data.charAt(j)) {
                leftLength = Integer.parseInt(data.substring(i + 1, j));
                break;
            }
        }
        leftStr = data.substring(j + 1, j + 1 + leftLength);
        int rightStringStart = j + 1 + leftLength + 1;
        rightStr = data.substring(rightStringStart + 1, data.length() - 1);


        TreeNode root = new TreeNode(val);
        root.left = deserialize(leftStr);
        root.right = deserialize(rightStr);
        return root;
    }


    /**
     * 390. Elimination Game
     * TODO: solve it.
     *
     * @param n
     * @return
     */
    public static int eliminationGame(int n) {
        int leftCount = n;

        while (leftCount != 1) {

        }

        return totalCombine;
    }


    /**
     * 377. Combination Sum IV
     *
     * @param nums   positive but no duplicate numbers.
     * @param target the target sum result.
     * @return all possible combinations, different orders are counted as different solutions.
     */
//    public int combinationSum4(int[] nums, int target) {
//        int max = -1;
//        int min = Integer.MAX_VALUE;
//        for (int tmp : nums) {
//            max = Math.max(max, tmp);
//            min = Math.min(min, tmp);
//        }
//        int[] result = new int[Math.max(max, target) + 1];
//        for (int tmp : nums) result[tmp]++;
//
//        for (int i = 0; i < target / min; i++) {
//            for (int t : nums) {
//                for (int j = 1; j < result.length; j++) {
//                    if (t + j < result.length)
//                        result[t + j] += (result[j]);
//                    else {
//                        break;
//                    }
//                }
//            }
//        }
//
//        return result[target];
//    }

    static int maxDepth = 0;
    static int totalCombine = 0;
    static int totalMin;

    private void combinationHelper(int[] nums, int currentDepth, int target) {
        if (target == 0) {
            totalCombine++;
            return;
        }
        if (target < 0 || target < totalMin || currentDepth > maxDepth + 2) {
            return;
        }
        for (int t : nums) {
            if (t <= target) {
                combinationHelper(nums, currentDepth + 1, target - t);
            }
        }
    }


    /**
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char tmp : tasks) {
            map.put(tmp, map.getOrDefault(tmp, 0) + 1);
        }
        return helper(map, n);
    }

    private int helper(HashMap<Character, Integer> map, int n) {
        if (map.size() == 0) return 0;
        int minRepeatTime = Integer.MAX_VALUE;
        int totalTasks = map.size();
        for (int t : map.values()) {
            minRepeatTime = Math.min(t, minRepeatTime);
        }
        if (totalTasks >= n + 1) {
            for (Character c : map.keySet().toArray(new Character[map.size()])) {
                if (map.get(c) == minRepeatTime) {
                    map.remove(c);
                }
            }
            return minRepeatTime * totalTasks + helper(map, n);
        } else {
            for (Character c : map.keySet().toArray(new Character[map.size()])) {
                if (map.get(c) == minRepeatTime) {
                    map.put(c, 1);
                }
            }
            return (n + 1) * (minRepeatTime - 1) + helper(map, n);
        }
    }

    public int lastRemaining(int n) {
        int removed = 0;
        boolean right = true;
        int begin = 1;
        int end = n;
        int step = 2;
        HashSet<Integer> removedNums = new HashSet<>();
        while (n - removed > 1) {
            if (right) {
                int tmpRemoved = (end - begin + 1) / step + 1;
                right = false;
                end = ((tmpRemoved - 1) * step) + begin - step / 2;
                begin += step / 2;
                step = step * 2;
                removed += tmpRemoved;
            } else {
                int tmpRemovd = (end - begin) / step + 1;
                right = true;
                begin = end - tmpRemovd * step + step / 2;
                end -= step / 2;
                step *= 2;
                removed += tmpRemovd;
            }
        }
        return begin;
    }


    public int combinationSum4(int[] nums, int target) {

//        int maxRound = target / nums[0];
//        while (maxRound > 0) {
//            for (int i = nums.length - 1; i >= 0; i--) {
//                comb[target] += combinationSum4(nums, target - nums[i]);
//            }
//            maxRound--;
//        }
        Arrays.sort(nums);
        return dfs(nums, target);
    }

    HashMap<Integer, Integer> map1 = new HashMap<>();

    private int dfs(int[] nums, int target) {
        if (map1.containsKey(target)) return map1.get(target);
        if (target <= 0) return 0;
        int comb[] = new int[target + 1];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < comb.length) {
                comb[nums[i]] = 1;
            }
        }
        for (int tmp : nums) {
            comb[target] += combinationSum4(nums, target - tmp);
        }
        map1.put(target, comb[target]);
        return comb[target];
    }


    /**
     * TODO: solve this.
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval2(char[] tasks, int n) {
        HashMap<Character, Integer> map = new HashMap<>();
        int maxValue = -1;
        for (char tmp : tasks) {
            map.put(tmp, map.getOrDefault(tmp, 0) + 1);
            maxValue = Math.max(maxValue, map.get(tmp));
        }
        int maxCount = 0;
        for (char key : map.keySet()) {
            if (map.get(key) == maxValue) {
                maxCount++;
            }
        }
        if (map.keySet().size() > n) {

        } else {
            return (n + 1) * (maxValue - 1) + maxCount;
        }
        return -1;
    }


    /**
     * 78. Subsets
     *
     * @param nums all possible elements.
     * @return combinations, power set.
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int elements = 0; elements <= nums.length; elements++) {
            result.addAll(getAllCombinations(nums, elements));
        }
        return result;
    }

    /**
     * generate all possible combinations with given length.
     *
     * @param nums
     * @param length
     * @return
     */
    private List<List<Integer>> getAllCombinations(int[] nums, int length) {
        List<List<Integer>> result = new ArrayList<>();
        if (length == 0) {
            result.add(new ArrayList<>());
            return result;
        }
        if (length == nums.length) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int i : nums) tmp.add(i);
            result.add(tmp);
            return result;
        }

        for (List<Integer> tmp : getAllCombinations(nums, length - 1)) {
            for (int i = 0; i < nums.length; i++) {
                if (tmp.size() == 0 || nums[i] > tmp.get(tmp.size() - 1)) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.addAll(tmp);
                    list.add(nums[i]);
                    result.add(list);
                }
            }

        }
        return result;
    }


    /**
     * TODO: understand this question.
     * 137. Single Number II
     * linear runtime complexity, without using extra memory.
     *
     * @param nums input nums.
     * @return the number which didn't appear 3 times, exacly once.
     */
    public int singleNumber(int[] nums) {
        int tmp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i - 1] = tmp ^ nums[i];
        }

        return -1;
    }


    public int triangleNumber(int[] nums) {
        int[] index = new int[nums.length];
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }
        List<List<Integer>> allCombines = getAllCombinations(index, 3);
        int counter = 0;
        for (List<Integer> tmp : allCombines) {
            if (formTriangle(tmp.get(0), tmp.get(1), tmp.get(2))) {
                counter++;
            }
        }
        return counter;
    }

    private boolean formTriangle(Integer a, Integer b, Integer c) {
        return (a + b > c && a + c > b && b + c > a);
    }

    private List<List<Integer>> dfs(int[] nums, int leftTaking, ArrayList<Integer> usingIndex, List<List<Integer>> list) {
        if (leftTaking == 0) {
            return list;
        }
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> tmp : list) {
            for (int i = 0; i < nums.length; i++) {
                if (usingIndex.contains(i) || i < usingIndex.get(usingIndex.size() - 1)) {
                    continue;
                } else {
                    ArrayList newlist = new ArrayList(tmp);
                    newlist.add(i);
                    result.add(newlist);
                }
            }
        }
        return result;
    }


    /**
     * 62. Unique Paths
     * Total time used : 7 min;
     *
     * @param m
     * @param n
     * @return
     */
    int[][] map;

    public int uniquePaths(int m, int n) {
        if (map == null) {
            map = new int[m + 1][n + 1];
        }
        if (m == 1 || n == 1) return 1;
        if (map[m][n] > 0) return map[m][n];
        int tmp = uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
        map[m][n] = tmp;
        return tmp;
    }

    /**
     * 482.
     *
     * @param S
     * @param K
     * @return
     */
    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        char[] tmp = S.toCharArray();
        int counter = 0;
        for (int i = S.length() - 1; i >= 0; i--) {
            if (tmp[i] == '-') {
                continue;
            }
            counter++;
            sb.append(tmp[i]);
            if (counter % K == 0 && i > 0) {
                sb.append('-');
            }
        }
        sb = sb.reverse();
        while (sb.length() > 0 && sb.charAt(0) == '-') {
            sb.deleteCharAt(0);
        }
        return sb.toString().toUpperCase();
    }


    public static void main(String[] args) {
        MainInMac m = new MainInMac();
//        TreeNode root = new TreeNode(5);
//        root.left = new TreeNode(3);
//        root.right = new TreeNode(7);
//        root.left.right = new TreeNode(4);
//        String tmp = m.serialize(root);
//        System.out.println(tmp);
//
//        m.deserialize(tmp);

        int[] nums = {4, 2, 1};
        System.out.println(m.combinationSum4(nums, 32));//181997601
        char[] set = {'A', 'A', 'A', 'B', 'B', 'B'};
//        System.out.println(m.leastInterval(set,2));
//        System.out.println(m.lastRemaining(20));
    }
}
