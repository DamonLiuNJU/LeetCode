package com.Liuweiting.ProblemSolutions;

import com.Liuweiting.DataStructure.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DamonLiu on 2017/5/31.
 * https://leetcode.com/problems/most-frequent-subtree-sum/#/description
 */
public class Q508_Most_Frequent_Subtree_Sum {
    static HashMap<Integer, Integer> sum2count = new HashMap<>();

    public int[] findFrequentTreeSum(TreeNode root) {
        sum2count.clear();
        maxCount = 0;
        getTreeSum(root);
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (Map.Entry<Integer,Integer> map : sum2count.entrySet()){
            if (map.getValue() == maxCount){
                list.add(map.getKey());
            }
        }
        int [] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
     }

    static int maxCount = 0;
    public int getTreeSum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int sum = node.val + getTreeSum(node.left) + getTreeSum(node.right);
        if (sum2count.containsKey(sum)) {
            int count = sum2count.get(sum);
            sum2count.put(sum, count + 1);
            if (count + 1 > maxCount){
                maxCount = count + 1;
            }
        } else {
            if (1 > maxCount){
                maxCount = 1;
            }
            sum2count.put(sum, 1);
        }
        return sum;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        System.out.println(Arrays.toString(new Q508_Most_Frequent_Subtree_Sum().findFrequentTreeSum(node)));
    }
}
