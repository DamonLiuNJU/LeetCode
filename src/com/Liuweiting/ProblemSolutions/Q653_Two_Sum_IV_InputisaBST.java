package com.Liuweiting.ProblemSolutions;

import com.Liuweiting.DataStructure.TreeNode;
import com.sun.source.tree.Tree;

/**
 * Created by DamonLiu on 2017/8/20.
 */
public class Q653_Two_Sum_IV_InputisaBST {


    //    public boolean findTarget(TreeNode root, int k) {
//        if(k==0){
//            return root==null || root.val==0;
//        }
//        if (root==null){
//            return k==0;
//        }
//        boolean result = false;
//        if (k > root.val){
//            boolean condition1 = k-root.val < root.val ? findSingle(root.left,k-root.val) : findSingle(root.right,k-root.val);
//            if(condition1) return condition1;
//            for (int i = 0; i < root.val; i++) {
//                boolean tmp = findSingle(root.left,i) && findSingle(root.right,k-i);
//                condition1 |= tmp;
//            }
//            return condition1;
//        } else if(k<root.val){
//            return findTarget(root.left,k);
//        } else if(k==root.val){
//            boolean condition1 = false;
//            for (int i = 0; i < root.val; i++) {
//                boolean tmp = findSingle(root.left,i) && findSingle(root.right,k-i);
//                condition1 |= tmp;
//            }
//            return condition1;
//        }
//        return false;
//    }
//
    public boolean findTarget(TreeNode root, int k) {
        return dfs(root, root, k);
    }

    public boolean dfs(TreeNode root, TreeNode cur, int k) {
        if (cur == null) return false;
        return search(root, cur, k - cur.val) || dfs(root, cur.left, k) || dfs(root, cur.right, k);
    }

    public boolean search(TreeNode root, TreeNode cur, int value) {
        if (root == null) return false;
        return (root.val == value) && (root != cur)
                || (root.val < value) && search(root.right, cur, value)
                || (root.val > value) && search(root.left, cur, value);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(new Q653_Two_Sum_IV_InputisaBST().findTarget(root, 1));
    }
}
