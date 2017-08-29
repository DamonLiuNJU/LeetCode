package com.Liuweiting.ProblemSolutions;

/**
 * Created by DamonLiu on 2017/5/31.
 * https://leetcode.com/problems/teemo-attacking/#/description
 */
public class Q495_Teemo_Attacking {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries.length == 0 || timeSeries.length==1){
            return timeSeries.length * duration;
        }
        int timeDuration = 0;
        for (int i = 1; i < timeSeries.length; i++) {
            int lastTimeStamp = timeSeries[i-1];
            int currentTimeStamp = timeSeries[i];
            timeDuration += Math.min(duration,currentTimeStamp- lastTimeStamp);
        }
        return timeDuration + duration;
    }

    public static void main(String[] args) {
        int []input = {1,2};
        System.out.println(new Q495_Teemo_Attacking().findPoisonedDuration(input,2));
    }
}
