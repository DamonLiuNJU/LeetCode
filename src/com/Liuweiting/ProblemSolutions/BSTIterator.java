package com.Liuweiting.ProblemSolutions;

import com.Liuweiting.DataStructure.TreeNode;
import com.sun.source.tree.Tree;

import java.util.Stack;

/**
 * Created by DamonLiu on 2017/10/24.
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * <p>
 * Calling next() will return the next smallest number in the BST.
 * <p>
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * <p>
 * Credits:
 * Special thanks to @ts for adding this problem and creating all test cases.
 */
public class BSTIterator {

    TreeNode root;
    private int _MAX;
    int currentVal;

    TreeNode current;
    boolean emptyTree = false;
    Stack<TreeNode> parents = new Stack<>();
    Stack<TreeNode> visitedpParents = new Stack<>();

    public BSTIterator(TreeNode root) {
        if (root == null) {
            emptyTree = true;
            return;
        }
        this.root = root;
        TreeNode tmp = root;
        while (tmp != null) {
            _MAX = Math.max(_MAX, tmp.val);
            tmp = tmp.right;
        }
        tmp = root;
        current = root;
        while (current != null && current.left != null) {
            parents.push(current);
            current = current.left;
        }
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        if (emptyTree) return false;
        return currentVal != _MAX;
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        if (emptyTree) return Integer.MAX_VALUE;
        currentVal = current.val;
        if (current.right!=null){
            parents.push(current);
            visitedpParents.push(current);
            current = current.right;
            while (current!=null && current.left!=null){
                parents.push(current);
                current = current.left;
            }
        } else {
            //here means the right branch is empty, then the next bigger value must be the parent.
            while (!parents.isEmpty() && !visitedpParents.isEmpty() && parents.peek()==visitedpParents.peek()){
                parents.pop();
                visitedpParents.pop();
            }
            if (!parents.isEmpty())
                current = parents.pop();
        }
        return currentVal;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(4);
        BSTIterator i = new BSTIterator(root);
        while (i.hasNext()) System.out.println(i.next());
    }
}
