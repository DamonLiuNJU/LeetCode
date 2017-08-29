package com.Liuweiting;

import com.Liuweiting.DataStructure.TreeNode;
import sun.reflect.generics.tree.Tree;

/**
 * Created by DamonLiu on 2017/5/30.
 * https://leetcode.com/problems/convert-bst-to-greater-tree/#/description
 * <p>
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.
 * <p>
 * Example:
 * <p>
 * Input: The root of a Binary Search Tree like this:
 * 5
 * /   \
 * 2     13
 * <p>
 * Output: The root of a Greater Tree like this:
 * 18
 * /   \
 * 20     13
 */
public class Q538_Convert_BST_to_Greater_Tree {
    /**
     * Binary Search Tree have some property.
     *
     * @param root
     * @return The converted tree.
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return root;
        getConvertedResultRight(root); // this line finish the right sibling convert.
        getConvertedResultLeft(root);
        return root;
    }

    public void getConvertedResultLeft(TreeNode node) {
        if (node == null) {
            return;
        }
        spreadThrowRightBranch(node.left, node.val);
        getConvertedResultLeft(node.left);
        getConvertedResultLeft(node.right);
    }

    public void spreadThrowRightBranch(TreeNode node, int value) {
        if (node == null) return;
        node.val += value;
        spreadThrowRightBranch(node.right, value);
    }


    public void getConvertedResultRight(TreeNode node) {
        if (node == null) {
            return;
        }
        int rightSubValue = getAllSumUp(node.right);
        node.val += rightSubValue;
        getConvertedResultRight(node.left);
        getConvertedResultRight(node.right);
    }

    public int getAllSumUp(TreeNode node) {
        if (node == null) return 0;
        return node.val + getAllSumUp(node.left) + getAllSumUp(node.right);
    }

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(2);
//        root.left = new TreeNode(0);
//        root.right = new TreeNode(3);
//        root.left.left = new TreeNode(-4);
//        root.left.right = new TreeNode(1);

        //1,0,4,-2,null,3
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(-2);
        root.right.left = new TreeNode(3);

        root = new Q538_Convert_BST_to_Greater_Tree().convertBST(root);
        System.out.println(" " + root);
    }
}
