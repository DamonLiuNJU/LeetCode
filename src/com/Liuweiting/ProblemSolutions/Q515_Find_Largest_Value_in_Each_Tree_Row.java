package com.Liuweiting;

import com.Liuweiting.DataStructure.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DamonLiu on 2017/5/29.
 */
public class Q515_Find_Largest_Value_in_Each_Tree_Row {
    HashMap<Integer,Integer> hightBiggest = new HashMap<>();
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        int depth = 0;
        recursive(root,depth);
        for (Map.Entry<Integer,Integer> entry: hightBiggest.entrySet()
             ) {
            result.add(entry.getValue());
        }
        return result;
    };

    public void recursive(TreeNode node, int depth){
        if (node == null){
            return;
        }
        if (hightBiggest.containsKey(depth)){
            hightBiggest.put(depth,Math.max(node.val,hightBiggest.get(depth)));
        } else {
            hightBiggest.put(depth,node.val);
        }
        recursive(node.left,depth+1);
        recursive(node.right,depth+1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
    }

}
