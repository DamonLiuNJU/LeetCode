package niuke;

import com.Liuweiting.ProblemSolutions.Main;

import java.util.Scanner;

/**
 * Created by DamonLiu on 2018/3/12.
 */
public class MainTmp {
    public void maxRegionProblem(){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextInt();
        }

        int ans = Integer.MIN_VALUE;

        for (int length = 1; length <= N; length++) {
            int[] numsInWindow = new int[length];
            int sum = 0;
            int min = Integer.MAX_VALUE;
            for (int beginIndex = 0; beginIndex < length; beginIndex++) {
                numsInWindow[beginIndex] = nums[beginIndex];
                sum += nums[beginIndex];
                min = Math.min(min,nums[beginIndex]);
            }
            ans = Math.max(ans, min * sum);
            for (int i = length; i < nums.length; i++) {
                int idxToReplace = i % length;
                sum = sum - numsInWindow[idxToReplace] + nums[i];
                if (nums[i] > numsInWindow[idxToReplace] && numsInWindow[idxToReplace] > min){
                    ans = Math.max(ans, min * sum);
                } else if (nums[i] == numsInWindow[idxToReplace]){
                    ans = Math.max(ans, min * sum);
                } else if (nums[i] < numsInWindow[idxToReplace] && numsInWindow[idxToReplace] > min){
                    min = Math.min(min, nums[i]);
                    ans = Math.max(ans, min * sum);
                } else {
                    numsInWindow[idxToReplace] = nums[i];
                    min = numsInWindow[0];
                    for (int j = 1; j < numsInWindow.length; j++) {
                        min = Math.min(min,numsInWindow[j]);
                    }
                    ans = Math.max(ans, min * sum);
                }
                numsInWindow[idxToReplace] = nums[i];
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        MainTmp m = new MainTmp();
        m.maxRegionProblem();
    }
}
