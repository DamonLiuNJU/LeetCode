package niuke;

import java.util.Scanner;

/**
 * Created by DamonLiu on 2017/6/16.
 */
public class p5 {

    static int[] numbers = new int[9];

    public static void analysis(int num){
        for (int i = 1; i <= num; i++) {
            if (num % i == 0){
                int tmp =i;
                while(tmp >= 10){
                    tmp /= 10;
                }
                numbers[tmp-1]++;
            }
        }
    }

    public static void printResult(){
        for (int tmp : numbers){
            System.out.println(tmp);
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        int r = sc.nextInt();

        for (int x = l; x <= r; x++) {
            analysis(x);
        }

        printResult();

    }
}
