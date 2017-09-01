package com.Liuweiting.ProblemSolutions;

import com.Liuweiting.DataStructure.TreeNode;


/**
 * Created by DamonLiu on 2017/8/29.
 */
public class Q606_Construct_String_from_Binary_Tree {
    public String tree2str(TreeNode t) {
        return preOrder(t,t);
    }

    private String preOrder(TreeNode root, TreeNode current){
        if (current==null) return "";
        int val = current.val;
        if (current.right!=null)
            return val + "(" + preOrder(root,current.left) + ")(" + preOrder(root,current.right) + ")";

        if (current.left!=null && current.right==null)
            return val + "(" + preOrder(root,current.left) + ")";

        if (current.left==null && current.right==null)
            return val+"";
        return "error";
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(new Q606_Construct_String_from_Binary_Tree().tree2str(root));
    }
}
