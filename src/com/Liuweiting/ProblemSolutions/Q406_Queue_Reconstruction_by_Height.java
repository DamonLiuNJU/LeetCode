package com.Liuweiting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/queue-reconstruction-by-height/#/description
 * Created by DamonLiu on 2017/5/29.
 */
public class Q406_Queue_Reconstruction_by_Height {

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] - o2[1] != 0){
                    return o1[1] - o2[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
        });
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < people.length; i++) {
            int[] tmp = people[i];
            if (list.size()==0){
                list.add(tmp);
                continue;
            }
            int h = tmp[0];
            int k = tmp[1];
            int j;
            for (j = 0; j < list.size() && k>0; j++) {
                if (list.get(j)[0] >= h){
                    k--;
                }
            }
            if (k==0){
                while (j<list.size() && list.get(j)[0]<h){
                    j++;
                }
            }
            list.add(j,tmp);
        }
        return list.toArray(new int[list.size()][]);
    }

    public static void main(String[] args) {
        int[][] input = {
                {7,0},
                {4,4},
                {7,1},
                {5,0},
                {6,1},
                {5,2}
        };


        input = new Q406_Queue_Reconstruction_by_Height().reconstructQueue(input);
        for (int[] tmp : input)
            System.out.println(Arrays.toString(tmp));

    }
}
