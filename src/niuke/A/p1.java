package niuke.A;

import niuke.B.B1;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by DamonLiu on 2017/6/18.
 */
public class p1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        double v = sc.nextDouble();
        double u = sc.nextDouble();
        double speed[] = new double[n];//the speed.
        double decay[] = new double[n];//the decay num.
        for (int i = 0; i < n; i++) {
            speed[i] = sc.nextDouble();
        }
        for (int i = 0; i < n; i++) {
            decay[i] = sc.nextDouble();
        }
        System.out.println(String.format("%.3f",calculate(n,v,u,speed,decay)));
    }

    public static ArrayList<String> allCombines(int n){
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            indexes.add(i);
        }
        deep(result,indexes,"",n);
        return result;
    }

    public static void deep(ArrayList<String> result,ArrayList<Integer> indexes,String prefix,int n){
        if (prefix.length()==n){
            result.add(prefix);
        }
        for (int i = 0; i < indexes.size(); i++) {
            int usingValue = indexes.get(i);
            ArrayList<Integer> newtmp = (ArrayList<Integer>) indexes.clone();
            newtmp.remove(Integer.valueOf(usingValue));
            deep(result,newtmp,prefix+usingValue,n);
        }
    }

    public static double calculate(int n,double v,double u,double speed[],double decay[]){
        double totalTime = 0;
        ArrayList<String> arrange = allCombines(n);

        for (String arr : arrange) {
            int[] index = new int[arr.length()];
            for (int i = 0; i < arr.length(); i++) {
                char tmp = arr.charAt(i);
                index[i] = Integer.parseInt(String.valueOf(tmp));
            }
            double expectedDistance = n * u;
            for (int i = 0; i < n; i++) {
                double expectedSpeed = speed[index[i]] - decay[index[i]] * (i) - v;
                double expectedTime = expectedDistance / expectedSpeed;
                totalTime += expectedTime;
            }
        }
        totalTime /= arrange.size();
        return totalTime;
    }



//    public static void allIndexCombinations(ArrayList<Integer> usingIndex,String str){
//        for (Integer value : usingIndex){
//            ArrayList<Integer> newArray = (ArrayList<Integer>) usingIndex.clone();
//            newArray.remove(value);
//            allIndexCombinations(newArray,str+value);
//        }
//    }
}
