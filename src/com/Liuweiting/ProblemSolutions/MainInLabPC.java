package com.Liuweiting.ProblemSolutions;

import com.Liuweiting.DataStructure.ListNode;
import com.Liuweiting.DataStructure.TreeNode;
import sun.awt.image.ImageWatched;
import sun.awt.image.IntegerComponentRaster;
import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * Created by DamonLiu-Lab on 2017/9/4.
 */
public class MainInLabPC {


    /**
     * 541. Reverse String II
     * Given a string and an integer k, you need to reverse the first k characters for
     * every 2k characters counting from the start of the string. If there are less
     * than k characters left, reverse all of them. If there are less than 2k but
     * greater than or equal to k characters, then reverse the first k characters
     * and left the other as original.
     *
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0; i < Math.min(s.length(), k); i++) {
            sb.append(s.charAt(i));
        }
        sb.reverse();
        int left = s.length() - 2 * k;
        for (int i = k; i < Math.min(2 * k, s.length()); i++) {
            sb.append(s.charAt(i));
        }
        if (left > 0) {
            if (left < k) {
                int counter = left;
                while (counter > 0) {
                    sb.append(s.charAt(s.length() - left + counter - 1));
                    counter--;
                }

            } else if (left < 2 * k) {
                StringBuilder sb1 = new StringBuilder();
                for (int i = 2 * k; i < 3 * k; i++) {
                    sb1.append(s.charAt(i));
                }
                sb1.reverse();
                for (int i = 3 * k; i < s.length(); i++) {
                    sb1.append(s.charAt(i));
                }
                sb.append(sb1);

            } else {
                sb.append(s.substring(2 * k));
            }
            return sb.toString();
        }
        return sb.toString();
    }


    /**
     * 108. Convert Sorted Array to Binary Search Tree
     * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;
        TreeNode root = new TreeNode(nums[nums.length / 2]);
        helperForSortedArray(nums, root, 0, nums.length / 2, true);
        helperForSortedArray(nums, root, nums.length / 2 + 1, nums.length, false);
        return root;
    }

    private void helperForSortedArray(int[] nums, TreeNode root, int beginIndex, int endIndex, boolean isLeft) {
        if (beginIndex == endIndex) return;
        TreeNode currentRoot = new TreeNode(nums[(beginIndex + endIndex) / 2]);
        if (isLeft) root.left = currentRoot;
        else root.right = currentRoot;
        helperForSortedArray(nums, currentRoot, beginIndex, (beginIndex + endIndex) / 2, true);
        helperForSortedArray(nums, currentRoot, (beginIndex + endIndex) / 2 + 1, endIndex, false);
        return;
    }


    /**
     * 671. Second Minimum Node In a Binary Tree
     * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/description/
     *
     * @param root
     * @return
     */
    int smallest = Integer.MAX_VALUE;
    int second_small = Integer.MAX_VALUE;

    public int findSecondMinimumValue(TreeNode root) {
        helper(root);
        second_small = second_small == Integer.MAX_VALUE ? -1 : second_small;
        return second_small;
    }

    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.val <= smallest) {
            smallest = root.val;
            helper(root.left);
            helper(root.right);
        } else if (root.val <= second_small && root.val > smallest) {
            second_small = root.val;
            return;
        } else if (root.val > second_small) {
            return;
        }
    }


    /**
     * 415. Add Strings
     * https://leetcode.com/problems/add-strings/description/
     * You must not use any built-in BigInteger library or convert the inputs to integer directly.
     *
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        if (num1.length() < num2.length()) {
            String tmp = num1;
            num1 = num2;
            num2 = tmp;
        }
        int length = num1.length();
        int carryIn = 0;
        StringBuilder result = new StringBuilder(num1.length());
        for (int i = 0; i < length; i++) {
            int tmp1 = num1.charAt(num1.length() - i - 1) - '0';
            int tmp2;
            if (num2.length() - i - 1 >= 0) {
                tmp2 = num2.charAt(num2.length() - i - 1) - '0';
            } else {
                tmp2 = 0;
            }
            int tmpResult = carryIn + tmp1 + tmp2;
            result.append(tmpResult % 10);
            carryIn = tmpResult / 10;
        }
        if (carryIn != 0) {
            result.append(carryIn);
        }
        return result.reverse().toString();
    }

    /**
     * 121. Best Time to Buy and Sell Stock
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int tmpProfit = -1;
        for (int i = 0; i < prices.length; i++) {
            int profit = prices[i];
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > prices[i]) {
                    tmpProfit = prices[j] - prices[i];
                    maxProfit = tmpProfit > maxProfit ? tmpProfit : maxProfit;
                }
            }
        }
        return maxProfit;
    }

    /**
     * 405. Convert a Number to Hexadecimal
     * https://leetcode.com/problems/convert-a-number-to-hexadecimal/description/
     *
     * @param num
     * @return
     */
    public String toHex(int num) {
        boolean isFalse = false;
        if (num < 0) {
            isFalse = true;
            num = -num - 1;
        }
        StringBuilder sb = new StringBuilder();
        while (num >= 16) {
            sb.append(relativeCharInHex(num % 16));
            num /= 16;
        }
        sb.append(relativeCharInHex(num));
        return isFalse ? compliment(sb.reverse()) : sb.reverse().toString();
    }

    /**
     * 取反加一
     *
     * @param input
     * @return
     */
    private String compliment(StringBuilder input) {
        char[] zeros = new char[8 - input.length()];
        for (int i = 0; i < zeros.length; i++) {
            zeros[i] = '0';
        }
        if (input.length() < 8) {
            input.insert(0, zeros);
        }
        for (int i = 0; i < input.length(); i++) {
            input.setCharAt(i, relativeCharInHex(15 - relativeNumInHex(input.charAt(i))));
        }
//        input.setCharAt(input.length() - 1,relativeCharInHex(1+relativeNumInHex(input.charAt(input.length() - 1))));
        return input.toString();
    }

    /**
     * 1~15 对应到 1~9+a~f
     *
     * @param num
     * @return
     */
    private char relativeCharInHex(int num) {
        if (num <= 9) {
            return (char) (num + '0');
        } else {
            return (char) (num - 10 + 'a');
        }
    }

    /**
     * 1~9+a~f 对应到 1~15
     *
     * @param num
     * @return
     */
    private int relativeNumInHex(char num) {
        if (num <= '9') {
            return num - '0';
        } else {
            return (num - 'a' + 10);
        }
    }


    /**
     * 594. Longest Harmonious Subsequence
     * https://leetcode.com/problems/longest-harmonious-subsequence/description/
     *
     * @param nums
     * @return
     */
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int maxLength = 0;
        for (int i = 0; i < nums.length - 1; ) {
            int current = nums[i];
            int currentIndex = i;
            int nextI = i + 1;
            while (i < nums.length && nums[i] == current) {
                nextI = i + 1;
                i++;
            }

            while (i < nums.length && nums[i] == current + 1) {
                i++;
            }
            if (nextI == i) {

            } else
                maxLength = i - currentIndex > maxLength ? i - currentIndex : maxLength;
            i = nextI;
        }
        return maxLength;
    }


    /**
     * 202. Happy Number
     * https://leetcode.com/problems/happy-number/description/
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        int lastMark = -1;
        while (true) {
            if (lastMark > 0 && lastMark < 10 && n == lastMark) {
                return n == 1;
            }
            if (n < 10) {
                lastMark = n;
            }

            int sum = 0;
            while (n >= 10) {
                sum += (n % 10) * (n % 10);
                n = n / 10;
            }
            sum += n * n;
            if (sum == 1) {
                return true;
            } else {
                n = sum;
            }
        }
    }

    /**
     * 572. Subtree of Another Tree
     * https://leetcode.com/problems/subtree-of-another-tree/description/
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null && t != null || s != null && t == null) return false;

        if (s.val == t.val) {
            boolean result = isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
            if (!result) {
                return isSubtree(s.left, t) || isSubtree(s.right, t);
            } else {
                return true;
            }
        } else {
            return isSubtree(s.left, t) || isSubtree(s.right, t);
        }
    }

    private boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null && t != null || s != null && t == null) return false;
        return s.val == t.val && isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }


    /**
     * 107. Binary Tree Level Order Traversal II
     * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        LinkedList<TreeNode> nextLevel = new LinkedList<>();
        list.add(root);
        List<Integer> line1 = new LinkedList<>();
        List<Integer> line2 = new LinkedList<>();
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> tmp = new LinkedList<>();
        tmp.add(root.val);
        result.add(tmp);
        while (!list.isEmpty()) {
            TreeNode cur = list.pop();
            if (cur.left != null)
                nextLevel.add(cur.left);
            if (cur.right != null)
                nextLevel.add(cur.right);

            if (list.isEmpty()) {
                list = nextLevel;
                if (!list.isEmpty()) {
                    nextLevel = new LinkedList<>();
                    line1.clear();
                    for (TreeNode node : list) {
                        line1.add(node.val);
                    }

                    result.add(0, line1);
                    line1 = new LinkedList<>();
                }
            }
        }
        return result;

    }


    /**
     * 326. Power of Three
     * https://leetcode.com/problems/power-of-three/description/
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        return false;
    }


    /**
     * 437. Path Sum III
     * https://leetcode.com/problems/path-sum-iii/description/
     *
     * @param root
     * @param sum
     * @return
     */
    int count = 0;

    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            if (sum == 0) {
                return 0;
            }
        } else {
            canFormASumOf(root, 0, sum);
            pathSum(root.left, sum);
            pathSum(root.right, sum);
        }

        return count;
    }


    private boolean canFormASumOf(TreeNode root, int sum, int target) {
        if (root == null) {
            return sum == 0;
        }
        if (sum + root.val == target) {
            count++;
        }
        canFormASumOf(root.left, sum + root.val, target);
        canFormASumOf(root.right, sum + root.val, target);
        return false;
    }


    /**
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode originHead = head;
        while (head != null && head.next != null) {
            if (head.next.val == head.val) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }

        return originHead;
    }

    /**
     * 35. Search Insert Position
     * https://leetcode.com/problems/search-insert-position/description/
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (cur == target) return i;
            if (cur > target) {
                return i;
            } else {
                continue;
            }
        }
        return nums.length;
    }


    /**
     * 53. Maximum Subarray
     * https://leetcode.com/problems/maximum-subarray/description/
     *
     * @param nums
     * @return
     */

    int max = Integer.MIN_VALUE;

    public int maxSubArray(int[] nums) {
        int previouslyMax = 0;

        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (cur >= previouslyMax + cur) {
                previouslyMax = 0;
//                continue;
            }
            previouslyMax = Math.max(cur, previouslyMax + cur);
            if (previouslyMax > max) max = previouslyMax;
        }
        return max;
    }

    /**
     * 263. Ugly Number
     * https://leetcode.com/problems/ugly-number/description/
     *
     * @param num
     * @return
     */
    public boolean isUgly(int num) {
        if (num == 0) return false;
        if (num == 1 || num == 2 || num == 3 || num == 5) return true;
        if (num % 2 == 0 || num % 3 == 0 || num % 5 == 0) {
            return (num % 2 == 0 ? isUgly(num / 2) : true) && (num % 3 == 0 ? isUgly(num / 3) : true) && (num % 5 == 0 ? isUgly(num / 5) : true);
        } else {
            return false;
        }
    }

    /**
     * 27. Remove Element
     * https://leetcode.com/problems/remove-element/description/
     * <p>
     * Given an array and a value, remove all instances of that value in place and return the new length.
     * <p>
     * Do not allocate extra space for another array, you must do this in place with constant memory.
     * <p>
     * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
     * <p>
     * Example:
     * Given input array nums = [3,2,2,3], val = 3
     * <p>
     * Your function should return length = 2, with the first two elements of nums being 2.
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        // solution 1 . just sort the array and then do a 'for' for this num array.
        Arrays.sort(nums);
        int beginIndex = -1;
        int endIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val && beginIndex < 0) beginIndex = i;
            if (nums[i] > val && endIndex < 0) endIndex = i;
        }
        if (nums[nums.length - 1] == val) endIndex = nums.length;
        int length = endIndex - beginIndex;
        if (length <= 0 || beginIndex < 0 || endIndex < 0) {
            return nums.length;
        }
        int currentOffset = 0;
        for (int index = nums.length - length; index < nums.length; index++) {
            if (index >= beginIndex && index < endIndex) {
                continue;
            }
            nums[currentOffset + beginIndex] = nums[index];
            currentOffset++;
        }

        return nums.length - length;
    }

    /**
     * 21. Merge Two Sorted Lists
     * https://leetcode.com/problems/merge-two-sorted-lists/description/
     *
     * @param l1 list1.
     * @param l2 list2.
     * @return merged list.
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l2.val < l1.val) {
            ListNode tmp = l1;
            l1 = l2;
            l2 = tmp;
        }
        ListNode newRoot = l1;
        ListNode current = newRoot;
        l1 = l1.next;
        while (l1 != null && l2 != null) {
            ListNode winner;
            winner = l1.val <= l2.val ? l1 : l2;
            current.next = winner;
            boolean flag = l1.val <= l2.val;
            l1 = flag ? l1.next : l1;
            l2 = flag ? l2 : l2.next;
            current = current.next;
        }
        ListNode winner = l1 == null ? l2 : l1;
        while (winner != null) {
            current.next = winner;
            winner = winner.next;
            current = current.next;
        }
        return newRoot;
    }

    /**
     * 235. Lowest Common Ancestor of a Binary Search Tree
     * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
     *
     * @param root root of binary search tree.
     * @param p    first node.
     * @param q    second node.
     * @return common lowest ancestor.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LinkedList<TreeNode> l1 = getPathToNode(root, p);
        LinkedList<TreeNode> l2 = getPathToNode(root, q);
        TreeNode commonLast = null;
//        while (!l1.isEmpty() && !l2.isEmpty()){
//            TreeNode tmp1 = l1.getFirst();
//            TreeNode tmp2 = l2.getFirst();
//            if (tmp1==tmp2) commonLast = tmp1;
//            if ((tmp1.val > tmp2.val && tmp1.left==tmp2)||(tmp1.val < tmp2.val && tmp1.right == tmp2)) {
//                commonLast = tmp1;
//            }
//            if ((tmp1.val > tmp2.val && tmp2.right==tmp1)||(tmp1.val < tmp2.val && tmp2.left == tmp1)) {
//                commonLast = tmp2;
//            }
//            if (tmp1.val < tmp2.val){
//                l1.removeLast();
//            } else {
//                l2.removeLast();
//            }
//        }
        for (TreeNode tmp : l1) {
            if (l2.contains(tmp)) {
                commonLast = tmp;
            }
        }
        return commonLast;
    }

    private LinkedList<TreeNode> getPathToNode(TreeNode root, TreeNode target) {
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        helper(root, target, linkedList);
        return linkedList;
    }

    private void helper(TreeNode root, TreeNode target, List<TreeNode> list) {
        list.add(root);
        if (root == target) {
            return;
        }
        if (root.val < target.val) {
            helper(root.right, target, list);
        } else {
            helper(root.left, target, list);
        }
    }

    /**
     * 198. House Robber
     * https://leetcode.com/problems/house-robber/description/
     *
     * @param nums
     * @return
     */
    HashMap<Integer, Integer> robMap = new HashMap<>();

    public int rob(int[] nums) {
        return robHelper(nums, 0);
    }

    private int robHelper(int[] nums, int begin) {
        if (begin == nums.length) return 0;
        if (begin == nums.length - 1) return nums[nums.length - 1];
        if (robMap.containsKey(begin)) {
            return robMap.get(begin);
        } else {
            int val = Math.max(robHelper(nums, begin + 1), nums[begin] + robHelper(nums, begin + 2));
            robMap.put(begin, val);
            return val;
        }
    }

    /**
     * 101. Symmetric Tree
     * https://leetcode.com/problems/symmetric-tree/description/
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;
        if (root.right == null || root.left == null) return false;
        return root.left.val == root.right.val && isTreeSymmetric(root.left,root.right);
    }

    private boolean isTreeSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (right == null || left == null) return false;
        return left.val==right.val && isTreeSymmetric(left.left,right.right) && isTreeSymmetric(left.right,right.left);
    }


    /**
     * 66. Plus One
     * https://leetcode.com/problems/plus-one/description/
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        return null;
    }


    public static void main(String[] args) {
        MainInLabPC m = new MainInLabPC();
//        String s = "abcdefg";
//        String tmp = new MainInLabPC().reverseStr(s,1);
//        System.out.println(tmp);

//        TreeNode root;
//        root = new TreeNode(2);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(5);
//        root.right.left = new TreeNode(5);
//        root.right.right = new TreeNode(7);
//        System.out.println(m.findSecondMinimumValue(root));


//        System.out.println(m.addStrings("79999","1"));

//        System.out.println(m.toHex(-100000));
//        System.out.println(m.findLHS(new int[]{1,1,1,1,}));
//        System.out.println(m.isHappy(2));

//        TreeNode root = new TreeNode(3); root.left = new TreeNode(4);root.right=new TreeNode(5);
//        root.left.left = new TreeNode(1); root.left.right = new TreeNode(2);
//
//        TreeNode s = new TreeNode(4);
//        s.left = new TreeNode(1);
//        s.right = new TreeNode(2);
//        System.out.println(m.isSubtree(root,s));

//        TreeNode root = new TreeNode(10);
//        root.left = new TreeNode(5);
//        root.right = new TreeNode(-3);
//        root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(2);
//        root.right.right = new TreeNode(11);
//
//        root.left.left.left = new TreeNode(3);
//        root.left.left.right = new TreeNode(-2);
//
//        root.left.right.right = new TreeNode(1);
//
//        System.out.println(m.pathSum(root, 8));

        int nums[] = null;


//        nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
//        System.out.println(m.maxSubArray(nums));
//        nums = new int[]{3, 2, 2, 3};
//        System.out.println(m.removeElement(nums, 3));
        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
        l2.next.next = new ListNode(4);
        m.mergeTwoLists(l1, l2);
        System.out.println();
    }
}
