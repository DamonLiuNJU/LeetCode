package com.Liuweiting.ProblemSolutions;

import com.Liuweiting.DataStructure.TreeNode;

import java.util.*;

/**
 * Created by DamonLiu on 2017/8/16.
 */
public class Q637_Average_Value_For_Tree_Levels {
    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Double> result = new ArrayList<>();
        queue.add(root);
        while (true) {
            if (queue.isEmpty()){
                break;
            }
            double value = 0.0;
            int counter = 0;
            Queue<TreeNode> tmpQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                TreeNode tmp = queue.remove();
                value += tmp.val;
                counter++;
                if (tmp.left!=null)
                    tmpQueue.add(tmp.left);
                if (tmp.right!=null)
                    tmpQueue.add(tmp.right);
            }
            value /= counter;
            result.add(value);
            queue = tmpQueue;
        }
        return result;
    }


    //8

    //1: 6
    //2:
}
