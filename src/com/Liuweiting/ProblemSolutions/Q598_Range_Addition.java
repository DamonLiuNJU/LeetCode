package com.Liuweiting.ProblemSolutions;

import com.Liuweiting.DataStructure.ListNode;
import com.Liuweiting.DataStructure.TreeNode;


import java.util.*;

/**
 * Created by DamonLiu on 2017/8/30.
 */
public class Q598_Range_Addition {

    /**
     * 当两个range相加的时候，总是取a和b两者各自的最小值作为最终结果。
     *
     * @param m
     * @param n
     * @param ops
     * @return
     */
    public int maxCount(int m, int n, int[][] ops) {
        int[][] map = new int[m][n];
        int maxValue = -1;
        int maxCount = 0;
        if (ops.length == 0) {
            return 9;
        }
        int minA = Integer.MAX_VALUE;
        int minB = Integer.MAX_VALUE;
        for (int[] operations : ops) {
            int a = operations[0];
            int b = operations[1];
            minA = Math.min(a, minA);
            minB = Math.min(b, minB);
        }
        return minA * minB;
    }


    /**
     * Q453 LeetCode
     *
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        int moveCount = 0;

        while (!hasFinished(nums)) {

            int MAX_VALUE = Integer.MIN_VALUE;
            int SECOND_VALUE = Integer.MIN_VALUE;
            int MIN_VALUE = Integer.MAX_VALUE;
            boolean NeedFlip = true;
            for (int i = 0; i < nums.length; i++) {
                MAX_VALUE = Math.max(nums[i], MAX_VALUE);
                MIN_VALUE = Math.min(nums[i], MIN_VALUE);
            }
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] >= SECOND_VALUE && nums[i] < MAX_VALUE)
                    SECOND_VALUE = nums[i];
            }

            int thisMove = MAX_VALUE - MIN_VALUE;

            for (int i = 0; i < nums.length; i++) {
                if (NeedFlip && nums[i] == MAX_VALUE) {
                    NeedFlip = false;
                } else {
                    nums[i] += thisMove;
                }
            }

            moveCount += thisMove;

        }
        return moveCount;
    }

    private boolean hasFinished(int[] nums) {
        int base = nums[0];
        for (int tmp : nums
                ) {
            if (tmp != base) {
                return false;
            }
        }
        return true;
    }

    public int[][] imageSmoother(int[][] M) {
        int[][] result = new int[M.length][M[0].length];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                result[i][j] = M[i][j];
                int totalNums = 1;
                if (i - 1 >= 0) {
                    if (j - 1 >= 0) {
                        result[i][j] += M[i - 1][j - 1];
                        totalNums++;
                    }
                    if (j + 1 < M[0].length) {
                        result[i][j] += M[i - 1][j + 1];
                        totalNums++;
                    }
                    result[i][j] += M[i - 1][j];
                    totalNums++;
                }
                if (i + 1 < M.length) {
                    if (j - 1 >= 0) {
                        result[i][j] += M[i + 1][j - 1];
                        totalNums++;
                    }
                    if (j + 1 < M[0].length) {
                        result[i][j] += M[i + 1][j + 1];
                        totalNums++;
                    }
                    result[i][j] += M[i + 1][j];
                    totalNums++;
                }
                if (j - 1 >= 0) {
                    result[i][j] += M[i][j - 1];
                    totalNums++;
                }
                if (j + 1 < M[0].length) {
                    result[i][j] += M[i][j + 1];
                    totalNums++;
                }
                result[i][j] /= totalNums
                ;
            }

        }
        return result;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (int tmp : nums1) {
            set1.add(tmp);
        }
        HashSet<Integer> set2 = new HashSet<>();
        for (int tmp : nums2) {
            set2.add(tmp);
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int tmp : set1) {
            if (set2.contains(tmp)) {
                result.add(tmp);
            }
        }
        int[] result1 = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            result1[i] = result.get(i);
        }
        return result1;
    }


    /**
     * Q530
     * <p>
     * minimum absolute difference between values of any two nodes.
     */
    public int getMinimumDifference(TreeNode root) {
        return minnimunDifferenceForRoot(root);
    }

    private int minnimunDifferenceForRoot(TreeNode root) {
        int diff1, diff2, diff3;
        if (root == null) return Integer.MAX_VALUE;
        if (!(root.left == null && root.right == null)) {
            diff1 = Integer.MAX_VALUE;
            diff2 = Integer.MAX_VALUE;
            diff3 = Integer.MAX_VALUE;
            if (root.left != null) {
                diff1 = Math.abs(root.val - getMax(root.left));
            }
            if (root.right != null) {
                diff2 = Math.abs(root.val - getMin(root.right));
            }
            diff3 = minnimunDifferenceForRoot(root.left);
            int diff4 = minnimunDifferenceForRoot(root.right);
            diff1 = Math.min(diff1, diff2);
            diff3 = Math.min(diff3, diff4);
            diff1 = Math.min(diff1, diff3);
            return diff1;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private int getMax(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root.val;
    }

    private int getMin(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.val;
    }

    /**
     * Q171. Excel Sheet Column Number
     */

    public int titleToNumber(String s) {
        int base = 1;
        int result = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char tmp = s.charAt(i);
            int val = tmp - 'A' + 1;
            result += val * base;
            base *= 26;
        }

        return result;
    }


    /**
     * Q383
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char tmp : magazine.toCharArray()) {
            if (map.containsKey(tmp)) {
                map.put(tmp, map.get(tmp) + 1);
            } else {
                map.put(tmp, 1);
            }
        }

        for (char tmp : ransomNote.toCharArray()) {
            if (!map.containsKey(tmp) || map.get(tmp) == 0)
                return false;
            else
                map.put(tmp, map.get(tmp) - 1);
        }
        return true;
    }

    private int getSum(TreeNode root) {
        if (root == null) return 0;
        return root.val + getSum(root.left) + getSum(root.right);
    }

    /**
     * Q563
     *
     * @param root
     * @return
     */
    public int findTilt(TreeNode root) {
        if (root == null) return 0;
        return Math.abs(getSum(root.left) - getSum(root.right)) + findTilt(root.left) + findTilt(root.right);
    }

    /**
     * Q455. Assign Cookies
     */
    public int findContentChildren(int[] children, int[] cookies) {
        Arrays.sort(cookies);
        Arrays.sort(children);
        int contentCount = 0;
        int childrenIndex = 0;
        for (int cookie : cookies) {
            for (int i = childrenIndex; i < children.length; i++) {
                if (children[i] <= cookie) {
                    contentCount++;
                    childrenIndex = i + 1;
                    break;
                }
            }
        }

        return contentCount;
    }

    /**
     * Q404
     * find the left nodes for each level and if the nodes have parent-leftchild relation, then remove the parent node.
     *
     * @param root
     * @return
     */
//    int result = 0;
//    public int sumOfLeftLeaves(TreeNode root) {
//        if(root==null) return 0;
//        LinkedList<TreeNode> level1 = new LinkedList<>();
//        LinkedList<TreeNode> level2 = new LinkedList<>();
//        level1.add(root);
//        ArrayList<TreeNode> left = new ArrayList<>();
//        while (!level1.isEmpty()){
//            TreeNode tmp = level1.pop();
//            if (tmp.left!=null) level2.add(tmp.left);
//            if (tmp.right!=null) level2.add(tmp.right);
//            if (level1.isEmpty()){
//                if (level2.isEmpty()) break;
//                level1 = level2;
//                left.add(level1.getFirst());
//                level2 = new LinkedList<>();
//            }
//        }
//
//        ArrayList<TreeNode> result = new ArrayList<>();
//        int sum = 0;
//        for (int i = 0; i < left.size()-1; i++) {
//            if (left.get(i).left!=left.get(i+1)){
//                result.add(left.get(i));
//                sum += left.get(i).val;
//            }
//        }
//        if(left.size()>=1)
//            sum += left.get(left.size()-1).val;
//        return sum;
//    }

    int sum_result = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        if (root.left != null) sum_result += root.left.val;
        sumOfLeftLeaves(root.left);
        sumOfLeftLeaves(root.right);
        return sum_result;
    }

    private void helper(TreeNode root) {

    }


    /**
     * Q599
     */
    public String[] findRestaurant(String[] list1, String[] list2) {
        int index = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for (String tmp : list1) {
//            if (map.containsKey(tmp)){
            map.put(tmp, index);
//            }
            index++;
        }
        HashMap<String, Integer> common = new HashMap<>();
        for (int i = 0; i < list2.length; i++) {
            String tmp = list2[i];
            if (map.containsKey(tmp)) {
                common.put(tmp, map.get(tmp) + i);
            }
        }
        String leastName = "error";
        int leastCount = Integer.MAX_VALUE;
        for (String tmp : common.keySet()) {
            if (common.get(tmp) < leastCount) {
                leastCount = common.get(tmp);
                leastName = tmp;
            }
        }
        ArrayList<String> result = new ArrayList<>();
        for (String tmp : common.keySet()) {
            if (common.get(tmp) == leastCount) {
                result.add(tmp);
            }
        }
        return result.toArray(new String[result.size()]);
    }


    /**
     * Q169
     */
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> num2count = new HashMap<>();
        int lowerBound = nums.length / 2;
        for (int tmp : nums) {
            if (num2count.containsKey(tmp)) {
                int count = num2count.get(tmp);
                if (count >= lowerBound) {
                    return tmp;
                }
                num2count.put(tmp, count + 1);

            } else {
                num2count.put(tmp, 1);
            }
        }
        return -1;
    }

    /**
     * Q387
     */
    public int firstUniqChar(String s) {
        int[] count = new int[26];
        for (char tmp : s.toCharArray()) {
            if (count[tmp - 'a'] == 0) {
                count[tmp - 'a']++;
            } else {
                count[tmp - 'a'] = -1;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                return s.charAt(i);
            }
        }
        return '0';
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }


    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p != null && q == null) return false;
        if (p == null && q == null) return false;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public String[] findRelativeRanks(int[] nums) {
        int[] copy = nums.clone();
        Arrays.sort(copy);
        String[] result = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int score = nums[i];
            for (int j = copy.length - 1; j >= 0; j--) {
                if (copy[j] == score) {
                    if (copy.length - j == 3) {
                        result[i] = "Bronze Medal";
                    } else if (copy.length - j == 2) {
                        result[i] = "Silver Medal";
                    } else if (copy.length - j == 1) {
                        result[i] = "Gold Medal";
                    } else {
                        result[i] = copy.length - j + "";
                    }
                }
            }
        }
        return result;
    }


    /**
     * Q242
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        int[] count = new int[26];
        for (char tmp : s.toCharArray()) {
            count[tmp - 'a']++;
        }
        for (char tmp : t.toCharArray()) {
            if (count[tmp - 'a'] < 0) {
                return false;
            } else {
                count[tmp - 'a']--;
            }
        }
        for (int tmp : count) {
            if (tmp != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Q217
     */
    public boolean containsDuplicate(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        if (nums.length < max - min + 1) return false;
        int[] hash = new int[max - min + 1];
        for (int value : nums) {
            hash[value - min]++;
            if (hash[value - min] >= 2) {
                return false;
            }
        }

        return true;
    }


    /**
     * Q206
     */

    public ListNode reverseList(ListNode head) {
        ListNode originHead = head;
        ListNode previous = null;
        if (head == null) return head;
        while (head.next != null) {
            ListNode current = head;
            ListNode next = head.next;
            current.next = previous;
            head = next;
            previous = current;
        }
        head.next = previous;
        return head;
    }

/**
 * recursive version for Q206
 */
//    ListNode pre = null;
//
//    public ListNode reverseList(ListNode head) {
//        if (head == null) return head;
//        if (head.next == null) {
//            head.next = pre;
//            return head;
//        }
//        ListNode next = head.next;
//        head.next = pre;
//        pre = head;
//        return reverseList(next);
//    }

    /**
     * [27,-49,96,-18,-90,-6,-2,92,82,53,
     * -47,-98,-53,-48,-46,55,-86,91,86,38,
     * -40,44,28,-12,-17,45,-78,-66,-63,44,
     * 7,65,96,-45,-53,-94,49,-3,50,-17,-98,
     * -97,-62,-50,-45,-8,-95,-79,-65,91,11,
     * -5,-12,-61,83,23,36,57,58,26,2,-35,-9,
     * -50,20,-10,-92,-78,88,58,-43,90,61,-5,
     * -60,68,40,97,41,75,41,-47,-77,-71,-56,58,52,33,16,10,-41,70,27,50,72,-100,-60,80,74,80,38,-69,71,-1,-21,63,-81,-81,61,12,46,-46,17,69,-17,62,28,87,-5,96,-50,54,66,29,-44,38,29,-4,18,-45,-71,-92,-13,-100,7,18,15,-22,37,-72,90,-65,-18,8,-95,17,-78,-15,-43,-31,-19,7,-77,99,-64,-21,-11,18,27,-93,73,56,-85,-88,8,-26,30,76,4,-81,56,-54,-45,-61,6,-88,8,28,-3,65,97,30,-28,72,29,-39,-97,-82,31,-17,77,-44,91,44,-31,-48,70,51,-72,-74,23,84,-28,30,75,79,-6,-16,-41,91,-51,-91,-27,-26,81,-98,35,37,72,-34,-80,1,-26,-89,-55,43,-85,67,-53,-57,93,22,80,-83,-96,7,96,-2,91,-92,-11,93,-83,62,-33,50,16,54,-13,-60,72,-41,41,-54,-77,38,42,-62,5,89,-18,-50,-37,-86,-33,-33,21,-84,65,-35,-76,-94,-42,-59,20,25,91,88,-21,-69,-72,-49,-10,69,-3,13,-41,91,52,16,32,86,66,95,-100,85,-86,-27,-99,79,38,77,37,96,-82,-43,-27,62,-55,4,93,-75,7,-65,46,-95,49,-95,48,-47,-27,-19,-61,-61,-24,91,77,-9,-36,30,-78,55,8,60,51,26,69,77,-60,-85,-19,-67,92,89,-31,39,46,70,-4,-6,-77,70,75,-86,61,-96,-95,38,-5,-79,-31,-31,76,77,-71,-20,-45,99,-43,-4,66,38,-19,58,-21,2,-51,-75,-76,46,-28,-53,16,-53,-39,-23,-49,-82,-84,-2,-60,-63,68,-84,66,49,-4,-79,48,5,-31,-34,96,51,77,-25,-95,-74,-47,-70,72,25,-71,-12,72,-57,18,76,-39,-14,-74,1,23,94,-30,-11,-4,18,-38,96,-76,32,-37,-80,-65,-60,-53,92,-82,0,-78,43,-75,-96,83,50,-53,1,-22,8,87,4,62,-38,-49,-68,51,47,2,66,43,26,-50,58,-2,-63,50,-2,-71,69,98,-96,12,76,-92,-53,-22,-45,49,8,63,36,64,-23,51,-85,9,-46,14,-88,-80,-90,-10,-30,68,41,7,-29,-61,-11,-60,89,-7,4,17,-47,51,47,8,-48,-45,-77,41,72,1,-8,39,62,46,-46,26,19,-84,69,89,84,10,-51,-45,1,-10,-53,-58,83,3,12,88,-93,-89,-4,-89,-81,-29,52,91,24,96,30,-13,43,36,65,-38,52,34,-97,-11,96,-96,-4,-3,-6,-4,-8,-71,99,56,-83,58,67,-35,22,86,37,-26,77,-39,-29,8,0,66,96,-34,80,49,-100,35,-62,97,40,34,-54,86,-70,-10,-84,82,-54,85,-8,-86,-49,-86,52,40,-59,30,53,-36,90,-46,-70,86,-28,62,-13,-28,-3,25,-79,89,-40,-80,76,-58,10,92,-76,9,29,-31,-25,80,-17,-73,20,24,9,-26,88,-1,-20,70,-62,52,32,25,76,82,-49,98,71,-37,70,47,-43,-68,-9,-66,41,-79,3,-84,53,38,96,-74,-37,5,-48,-97,57,32,26,95,-64,-42,20,-88,92,23,62,64,38,32,-37,48,65,7,82,-42,28,-63,-73,-67,75,-77,-41,90,28,11,46,-15,95,-28,-68,-69,-18,5,96,-25,-72,58,-61,19,-57,-46,-33,60,61,-99,70,-59,-62,97,-25,-87,-80,-14,56,1,98,2,38,45,74,23,29,-44,-72,25,31,-92,-65,22,-21,-70,77,46,90,-10,47,13,32,37,10,-93,51,-17,-7,7,36,-57,-91,-74,89,35,49,18,91,77,95,23,38,-70,97,-83,-87,74,16,55,-35,-85,-32,49,53,79,56,-44,-86,-99,15,50,97,-24,76,38,-89,26,-92,-98,-45,-97,-23,93,85,-25,-89,98,-99,27,6,-82,42,74,67,-53,53,23,3,67,-23,-82,-31,-74,-6,-54,64,5,24,72,-40,79,27,37,25,-36,64,36,-85,66,-85,21,84,9,-5,52,57,-99,-73,-40,20,-44,-69,90,-18,25,88,-54,83,12,70,-57,-9,-51,32,-84,-34,-3,4,-19,-37,-29,54,99,81,-99,51,-10,-98,31,2,-25,87,33,-35,-30,-89,-95,-32,46,17,91,89,60,92,-27,29,-42,70,-67,-9,85,-43,97,37,90,99,-12,-20,53,71,34,-72,59,-80,-55,81,83,-50,-99,29,19,92,-30,-20,37,43,-91,47,-34,94,-61,-97,-97,88,40,93,87,81,-75,41,52,12,21,-37,32,67,-56,15,69,-54,-56,-11,90,14,21,27,-91,30,-25,-25,-24,-34,-69,-20,-46,71,-75,-58,4,-97,35,-43,15,-44,-28,-53,75,17,14,45,63,58,86,53,-76,7,-19,-67,89,8,-39,-83,74,-8,-51,-72,-85,-25]
     *
     * @param args
     */
    public static void main(String[] args) {
//        System.out.println(new Q598_Range_Addition().imageSmoother(new int[][]{{2,3,4},
//                                                                                {5,6,7}
//                ,{8,9,10}}));
//        System.out.println(9/2);


//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(9);
//        root.right = new TreeNode(20);
//        root.right.left = new TreeNode(15);
//        root.right.right = new TreeNode(7);
//        System.out.println(new Q598_Range_Addition().sumOfLeftLeaves(root));

        String[] s1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] s2 = {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};
        System.out.println(Arrays.toString(new Q598_Range_Addition().findRestaurant(s1, s2)));
    }
}
