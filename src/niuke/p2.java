package niuke;

/**
 * Created by DamonLiu on 2017/6/13.
 */
import java.util.Scanner;

public class p2{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int vals[] = new int[n];
        for (int i = 0; i < n; i++) {
            vals[i] = sc.nextInt();
        }

        int anchor = vals[0];
        int level = 0;
        int count = 0;

        for (int score: vals
             ) {
            if (score <= anchor) count++;
        }

        System.out.println((int)(Math.log(count)/Math.log(2)));

//        do {
//            int newVal[] = new int[vals.length/2];
//            if (newVal.length==0){
//                break;
//            }
//            for (int i = 0; i < vals.length; i+=2) {
//                if (vals[i] >= vals[i+1]){
//                    newVal[i/2] = vals[i];
//                    if (i==0){
//                        level++;
//                    }
//                } else {
//                    newVal[i/2] = vals[i+1];
//                    if (i==0){
//                        break;
//                    }
//                }
//            }
//            vals = newVal;
//        }while (true);
//        System.out.println(level);
    }
}