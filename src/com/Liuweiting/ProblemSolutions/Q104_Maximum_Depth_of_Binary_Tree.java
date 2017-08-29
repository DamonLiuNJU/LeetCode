package com.Liuweiting.ProblemSolutions;

import com.Liuweiting.DataStructure.TreeNode;

/**
 * Created by DamonLiu on 2017/8/18.
 */
public class Q104_Maximum_Depth_of_Binary_Tree {

    public int maxDepth(TreeNode root) {
        if (root==null) return 0;
        else return 1 + Math.max(maxDepth(root.left),maxDepth(root.right));
    }
}
