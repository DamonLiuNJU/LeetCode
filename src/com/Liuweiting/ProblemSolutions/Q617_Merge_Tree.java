package com.Liuweiting.ProblemSolutions;

import com.Liuweiting.DataStructure.TreeNode;

/**
 * Created by DamonLiu-Lab on 2017/8/15.
 */
public class Q617_Merge_Tree {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1==null && t2==null) return null;
        TreeNode root = mergeRoot(t1, t2);
//        root.left = mergeRoot(t1.left, t2.left);
//        root.right = mergeRoot(t1.right, t2.right);
        if (null!=t1 && null!=t2) {
            root.left = mergeTrees(t1.left, t2.left);
            root.right = mergeTrees(t1.right, t2.right);
        } else if (null == t1 && null != t2){
            root.left = mergeTrees(null, t2.left);
            root.right = mergeTrees(null, t2.right);
        } else if (null == t2 && null != t1){
            root.left = mergeTrees(t1.left, null);
            root.right = mergeTrees(t1.right, null);
        }
        return root;
    }

    private TreeNode mergeRoot(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return null;
        }
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        return new TreeNode(root1.val + root2.val);
    }
}
