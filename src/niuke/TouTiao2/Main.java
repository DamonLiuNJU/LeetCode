package niuke.TouTiao2;

import java.util.Scanner;

/**
 * Created by DamonLiu on 2018/3/12.
 */
public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] likeness = new int[N];
        for (int i = 0; i < N; i++) {
            likeness[i] = sc.nextInt();
        }
        int M = sc.nextInt();
        int [][] queries = new int[M][3];
        for (int i = 0; i < M; i++) {
            queries[i][0] = sc.nextInt();
            queries[i][1] = sc.nextInt();
            queries[i][2] = sc.nextInt();
        }

        for (int i = 0; i < M; i++) {
            int counter = 0;
            for (int j = queries[i][0] - 1; j < queries[i][1] - 1; j++) {
                if (likeness[j] == queries[i][2]){
                    counter++;
                }
            }
            System.out.println(counter);
        }
    }
}
