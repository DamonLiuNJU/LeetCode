package com.Liuweiting;

import com.Liuweiting.DataStructure.ListNode;
import com.Liuweiting.DataStructure.TreeNode;

import java.util.*;

/**
 * Created by DamonLiu on 2018/3/11.
 */
public class MainInMac_3 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        ListNode result = lists[0];
        for (int i = 1; i < lists.length; i++) {
            result = mergeTwoList(result, lists[i]);
        }
        return result;
    }


    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        return mergeKListsHelper(lists, 0, lists.length);
    }

    public ListNode mergeKListsHelper(ListNode[] lists, int fromIndex, int toIndex) {
        int size = toIndex - fromIndex;
        if (size == 1) return lists[fromIndex];
        if (size == 2) return mergeTwoList(lists[fromIndex], lists[fromIndex + 1]);
        ListNode tmp1 = mergeKListsHelper(lists, fromIndex, fromIndex + size / 2);
        ListNode tmp2 = mergeKListsHelper(lists, fromIndex + size / 2, toIndex);
        return mergeTwoList(tmp1, tmp2);
    }

    private static ListNode mergeTwoList(ListNode n1, ListNode n2) {
        ListNode root, tmp;
        root = tmp = new ListNode(Integer.MIN_VALUE);
        while (n1 != null && n2 != null) {
            if (n1.val <= n2.val) {
                tmp.next = n1;
                n1 = n1.next;
            } else {
                tmp.next = n2;
                n2 = n2.next;
            }
            tmp = tmp.next;
        }
        while (n1 != null) {
            tmp.next = n1;
            tmp = tmp.next;
            n1 = n1.next;
        }

        while (n2 != null) {
            tmp.next = n2;
            tmp = tmp.next;
            n2 = n2.next;
        }
        return root.next;
    }


    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > nums.length) nums[i] = nums.length + 1;
        }
        for (int i = 0; i < nums.length; i++) {
            int abs = Math.abs(nums[i]);
            if (abs <= nums.length) {
                nums[abs - 1] = -Math.abs(nums[abs - 1]);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) return i + 1;
        }
        return nums.length + 1;
    }


    /**
     * if a path go through root, then this path goes from root to both children's leaves.
     * if this path doesn't go through root, then this path is the max sum path between left subtree and right subtree.
     *
     * @param root the root.
     * @return the max path sum.
     */
    int maxPathSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;

        return maxPathSum;
    }


    public void skyLinePoints() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
//        int[][] points = new int[n][2];
        List<int[]> points = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            points.add(new int[]{sc.nextInt(), sc.nextInt()});
        }
        if (points.size() == 1) {
            System.out.println(points.get(0)[0] + " " + points.get(0)[1]);
        }
        points.sort((o1, o2) -> {
            int diff = o1[0] - o2[0];
            return diff == 0 ? o1[1] - o2[1] : diff;
        });

        while (true) {
            boolean detectDominatedPoint = false;
            for (int i = 1; i < points.size(); i++) {
                if (points.get(i)[1] > points.get(i - 1)[1]) {
                    detectDominatedPoint = true;
                    points.remove(i - 1);
                    i = i - 1;
                }
            }
            if (!detectDominatedPoint) {
                break;
            }
        }
        for (int[] point : points) {
            System.out.println(point[0] + " " + point[1]);
        }
    }


    /**
     * Search in sorted 2D array.
     *
     * @param target
     * @param array
     * @return
     */
    public boolean Find(int target, int[][] array) {
        return Find(target, array, 0, 0, array.length, array[0].length);
    }

    public boolean searchMatrix(int[][] array, int target) {
        return Find(target, array, 0, 0, array.length, array[0].length);
    }


    private boolean Find(int target, int[][] array, int x0, int y0, int x1, int y1) {
        if (x1 <= x0 || y1 <= y0) return false;
        if (x1 - x0 <= 1 && y1 - y0 <= 1) {
            return array[x0][y0] == target;
        }
        int centerX, centerY;
        centerX = (x0 + x1) / 2;
        centerY = (y0 + y1) / 2;
        if (target == array[centerX][centerY]) {
            return true;
        } else if (target < array[centerX][centerY]) {
            return Find(target, array, x0, y0, x1, centerY) || Find(target, array, x0, y0, centerX, y1);
        } else {
            return Find(target, array, centerX, y0, x1, y1) || Find(target, array, x0, centerY, x1, y1);
        }
    }


    public String replaceSpace(StringBuffer str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                str.replace(i, i + 1, "%20");
            }
        }
        return str.toString();
    }


    public TreeNode reConstructBinaryTreeHelper(int[] pre, int[] in, int pre0, int pre1, int in0, int in1) {

        if (pre1 == pre0 || in0 == in1 || pre0 >= pre.length || in0 >= in.length) {
            return null;
        }
        TreeNode root = new TreeNode(pre[pre0]);
        int rootPositionInInOrder = -1;
        for (int i = in0; i < in1; i++) {
            if (in[i] == pre[pre0]) {
                rootPositionInInOrder = i;
            }
        }
        int leftSize = rootPositionInInOrder - in0;
        int rightSize = pre1 - pre0 - 1 - leftSize;
        int leftPre0 = pre0 + 1;
        int leftPre1 = pre0 + 1 + leftSize;
        int leftIn0 = in0;
        int leftIn1 = in0 + leftSize;


        int rightPre0 = pre0 + 1 + leftSize;
        int rightPre1 = pre1;
        int rightIn0 = in0 + leftSize + 1;
        int rightIn1 = in1;
        if (leftSize > 0)
            root.left = reConstructBinaryTreeHelper(pre, in, leftPre0, leftPre1, leftIn0, leftIn1);
        if (rightSize > 0)
            root.right = reConstructBinaryTreeHelper(pre, in, rightPre0, rightPre1, rightIn0, rightIn1);

        return root;
    }


    public int NumberOf1(int n) {
        int counter = 0;
        while (n != 0) {
            counter += ((n & 1) == 1) ? 1 : 0;
            n = n >>> 1;
        }
        return counter;
    }


    public void reOrderArray(int[] array) {
        int oddCount = 0;
        for (int tmp : array) {
            if (tmp % 2 == 1) oddCount++;
        }
        int oddIndex = 0;
        int[] newArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 1) {
                newArray[oddIndex++] = array[i];
            } else {
                newArray[oddCount++] = array[i];
            }
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = newArray[i];
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            twoSum(nums, i, path, result);
        }
        return result;
    }


    private void twoSum(int[] nums, int index, List<Integer> path, List<List<Integer>> result) {

        if (path.size() == 3) {
            int sum = 0;
            for (int i : path) sum += i;
            if (sum == 0 && !result.contains(path)) {
                result.add(new ArrayList<>(path));
            }
            return;
        }

        if (index >= nums.length) {
            return;
        }


        if (path.size() == 2) {
            int sum = 0;
            for (int i : path) sum += i;
            if (sum + nums[nums.length - 1] < 0 || sum + nums[index] > 0) return;
        }
        for (int i = index; i < nums.length; i++) {
            path.add(nums[i]);
            twoSum(nums, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        boolean result = false;
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null && root2 != null) return false;
        if (root1 != null && root2 == null) return false;
        result = (root1.val == root2.val && HasSubtree(root1.left, root2.left) && HasSubtree(root1.right, root2.right))
                || HasSubtree(root1.left, root2)
                || HasSubtree(root1.right, root2);
        return result;
    }


    static class Solution131 {
        public static List<List<String>> partition(String s) {
            int len = s.length();
            List<List<String>>[] result = new List[len + 1];
            result[0] = new ArrayList<>();
            result[0].add(new ArrayList<>());

            boolean[][] pair = new boolean[len][len];
            for (int i = 0; i < s.length(); i++) {
                result[i + 1] = new ArrayList<List<String>>();
                for (int left = 0; left <= i; left++) {
                    if (s.charAt(left) == s.charAt(i) && (i - left <= 1 || pair[left + 1][i - 1])) {
                        pair[left][i] = true;
                        String str = s.substring(left, i + 1);
                        for (List<String> r : result[left]) {
                            List<String> ri = new ArrayList<String>(r);
                            ri.add(str);
                            result[i + 1].add(ri);
                        }
                    }
                }
            }
            return result[len];
        }
    }


    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        subsetsWithDup(nums,result,new ArrayList<Integer>(),0);
        return result;
    }

    private void subsetsWithDup(int[] nums,List<List<Integer>> result, List<Integer> current,int currentIndex){
        result.add((List<Integer>) ((ArrayList)current).clone());
        for (int i=currentIndex;i<nums.length;i++){
            if (i==currentIndex || nums[i]!=nums[i-1]){
                current.add(nums[i]);
                subsetsWithDup(nums,result,current,i+1);
                current.remove(current.size()-1);
            }
        }
    }



    public int longestConsecutive(int[] num) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int n : num) {
            if (!map.containsKey(n)) {
                int left = (map.containsKey(n - 1)) ? map.get(n - 1) : 0;
                int right = (map.containsKey(n + 1)) ? map.get(n + 1) : 0;
                // sum: length of the sequence n is in
                int sum = left + right + 1;
                map.put(n, sum);

                // keep track of the max length
                res = Math.max(res, sum);

                // extend the length to the boundary(s)
                // of the sequence
                // will do nothing if n has no neighbors
                map.put(n - left, sum);
                map.put(n + right, sum);
            }
            else {
                // duplicates
                continue;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        MainInMac_3 m3 = new MainInMac_3();
        int[] nums = {0,3,7,2,5,8,4,6,0,1};
//        m3.subsetsWithDup(nums);
        System.out.println(m3.longestConsecutive(nums));
    }


}
