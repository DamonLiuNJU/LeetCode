package com.Liuweiting.DataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DamonLiu on 2018/3/10.
 */
public class TreeSerialize2 {

    List<Integer> preOrder, leftTreeSize;
    HashMap<TreeNode, Integer> node2size;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        leftTreeSize = new ArrayList<>();
//        rightTreeSize = new ArrayList<>();
        node2size = new HashMap();
        preOrder(root, values);
        this.preOrder = values;
        return null;
    }

    private void preOrder(TreeNode root, List<Integer> values) {
        if (root == null) return;
        values.add(root.val);
        leftTreeSize.add(treeSize(root.left));
        preOrder(root.left, values);
//        rightTreeSize.add(treeSize(root.right));
        preOrder(root.right, values);
    }


    /**
     * return the size of a tree with root = root.
     *
     * @param root the current root;
     * @return
     */

    private int treeSize(TreeNode root) {
        if (node2size.containsKey(root)) return node2size.get(root);
        if (root == null) return 0;
        int left = treeSize(root.left);
        int right = treeSize(root.right);
        node2size.put(root, left + right + 1);
        return left + right + 1;
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return deserializeHelper(preOrder,leftTreeSize);
    }

    private TreeNode deserializeHelper(List<Integer> preOrder, List<Integer> leftTreeSize) {
        if (preOrder.size() == 0) return null;
        TreeNode root = new TreeNode(preOrder.get(0));
        int leftList = leftTreeSize.get(0);
        List<Integer> leftPreOrder = preOrder.subList(1, 1 + leftList);
        List<Integer> leftleftSize = leftTreeSize.subList(1,1+leftList);

        List<Integer> rightPreOrder = preOrder.subList(1 + leftList, preOrder.size());
        List<Integer> rightLeftSize = leftTreeSize.subList(1+leftList, preOrder.size());

        root.left = deserializeHelper(leftPreOrder,leftleftSize);
        root.right = deserializeHelper(rightPreOrder,rightLeftSize);
        return root;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        TreeSerialize2 tre2 = new TreeSerialize2();
        tre2.serialize(root);
        TreeNode root2 = tre2.deserialize("");
        System.out.println(root2);
    }
}
