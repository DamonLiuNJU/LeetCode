package com.Liuweiting.DataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by DamonLiu on 2018/3/10.
 */
public class TreeSerialize {


    private static int getDepth(TreeNode root){
        if (root == null) return 0;
        return 1 + Math.max(getDepth(root.left),getDepth(root.right));
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        int depth = getDepth(root);
        String[] tmp = new String[(int) Math.pow(2,depth+1)];
        tmp[0] = root.val + "";
        serializeHelper(root,tmp,0);
        int counter = 0;
        for (int i = tmp.length-1; i >= 0 ; i--) {
            if (tmp[i]==null || tmp[i].compareTo("null") == 0){
                counter++;
            } else {
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(tmp[0]);
        for (int i = 1; i < tmp.length - counter; i++) {
            sb.append(","+tmp[i]);
        }
        return sb.toString();
    }
    private void serializeHelper(TreeNode root, String[] list, int parentIndex){
        if(root==null) return;
        list[2*parentIndex+1] = root.left==null ? "null" : root.left.val+"";
        list[2*parentIndex+2] = root.right==null ? "null" : root.right.val+"";
        serializeHelper(root.left,list,2*parentIndex+1);
        serializeHelper(root.right,list,2*parentIndex+2);
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] datas = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(datas[0]));
        root.left = deserializeHelper(datas,1);
        root.right = deserializeHelper(datas,2);
        return root;
    }

    private TreeNode deserializeHelper(String[] data,int rootIndex){
        if (rootIndex >= data.length || data[rootIndex].compareTo("null")==0){
            return null;
        }
        TreeNode tmp = new TreeNode(Integer.parseInt(data[rootIndex]));
        tmp.left = deserializeHelper(data,2*rootIndex+1);
        tmp.right = deserializeHelper(data,2*rootIndex+2);
        return tmp;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        String tmp;
        TreeSerialize2 tre = new TreeSerialize2();
        System.out.println(tmp = tre.serialize(root));
        TreeNode root2 = tre.deserialize(tmp);
        System.out.println(root2);
    }
}
