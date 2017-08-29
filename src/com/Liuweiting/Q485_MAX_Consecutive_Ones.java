package com.Liuweiting;

/**
 * Created by DamonLiu on 2017/8/18.
 */
public class Q485_MAX_Consecutive_Ones {
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxLength = 0;
        boolean isTracking = false;
        int currentLength = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!isTracking && nums[i]==1){
                isTracking = true;
                currentLength = 0;
                currentLength ++;
            } else if (isTracking && nums[i]==1){
                currentLength ++;
            } else if (isTracking && nums[i]==0){
                isTracking = false;
                maxLength = currentLength > maxLength ? currentLength :  maxLength;
            } else if (!isTracking && nums[i]==0){
                //
            }
        }
        return maxLength;
    }
}
