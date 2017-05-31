package com.Liuweiting;


import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/find-bottom-left-tree-value/#/description
 * Created by DamonLiu on 2017/5/29.
 */
public class Q513_Find_Bottom_Left_Tree_Value {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    public static class Solution {

        public int findBottomLeftValue(TreeNode root) {
            Queue queue = new LinkedList<>();
            Queue nextLevel = new LinkedList();
            int level = 0;
            TreeNode tmp = root;
            queue.add(tmp);
            boolean needToRecord = false;
            int result = tmp.val;

            do {

                if (tmp.left != null)
                    nextLevel.add(tmp.left);
                if (tmp.right != null)
                    nextLevel.add(tmp.right);
                queue.remove();
                if (needToRecord) {
                    result = tmp.val;
                    needToRecord = false;
                }
//                System.out.print(tmp.val + ",");
                if (queue.size() == 0) {
//                    System.out.println("finish level : "+level);
                    needToRecord = true;
                    level++;
                    queue = nextLevel;
                    nextLevel = new LinkedList();
                }
                tmp = (TreeNode) queue.peek();
            } while ((tmp != null));
            return result;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(10);
        node.left = new TreeNode(1);
        node.right = new TreeNode(3);
        System.out.println("finally " + new Solution().findBottomLeftValue(node));
    }

}
