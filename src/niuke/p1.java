package niuke;

/**
 * Created by DamonLiu on 2017/6/13.
 */

import java.util.Scanner;

public class p1{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int val1[] =new int[n];
        for (int i = 0; i < n; i++) {
            val1[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int val2[] = new int[m];
        for (int i = 0; i < m; i++) {
            val2[i] = sc.nextInt();
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m-n+1; i++) {
            int diff = 0;
            for (int j = i; j < i + n; j++) {
                diff += Math.pow((val1[j-i]-val2[j]),2);
            }
            min = diff < min?diff:min;
        }

        System.out.println(min);
    }
}