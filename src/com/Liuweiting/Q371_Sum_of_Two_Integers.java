package com.Liuweiting;

/**
 * Created by DamonLiu on 2017/8/18.
 */
public class Q371_Sum_of_Two_Integers {
    public int getSum(int a, int b) {
        if (a < 0 && b < 0) {
            int minus1 = (~1) ^ 1;
            a = getSumForPositive(~a, 1);
            b = getSumForPositive(~b, 1);
            int result = getSumForPositive(a, b);
//            System.out.println(Integer.toBinaryString(result));
            result = getSumForPositive(~result, 1);
//            System.out.println(Integer.toBinaryString(result));
            return result;
        } else {
            return getSumForPositive(a, b);
        }
    }

    private int getSumForPositive(int a, int b) {
        int oritinA = a;
        int originB = b;

        //without using + and minus.
//        System.out.println(Integer.toBinaryString(a));
//        System.out.println(Integer.toBinaryString(b));
        int result = 0;
        int c = 0;
        int counter = 0;
        while (counter <= 31 && !((a == 0 && b == 0) && c == 0)) {
            int tmpA = a & 1;
            int tmpB = b & 1;

            result = result | ((tmpA ^ tmpB ^ c) << counter);
            counter++;

            int newCarryIn = tmpA ^ tmpB ^ c;
            // 0'1 ==0  1'1==1 2'1==0 3'1==1  xor
            //
            int needCarryIn = (tmpA & tmpB) | (tmpA & c) | (tmpB & c);
//            c = newCarryIn==0 ? 1 : 0;//carry in.
            c = needCarryIn;
            a = a >>> 1;
            b = b >>> 1;
        }
//        if (counter < 31) {
//            if (a == 0) {
//                b = originB & (-1 << counter);
//                result = result | b;
//            } else if (b == 0) {
//                a = oritinA & (-1 << counter);
//                result = result | a;
//            }
//        }
        String tmp = Integer.toBinaryString(result);
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(Integer.toBinaryString(3));
//        System.out.println(Integer.toBinaryString(-3));
        System.out.println(Integer.toBinaryString(-2147483648));
        System.out.println(Integer.toBinaryString(2147483647));
        System.out.println(new Q371_Sum_of_Two_Integers().getSum(-2147483648,2147483647));
        System.out.println(new Q371_Sum_of_Two_Integers().getSum(10,20));
        System.out.println(new Q371_Sum_of_Two_Integers().getSum(10, -1));
        System.out.println(new Q371_Sum_of_Two_Integers().getSum(-1,-1));
        System.out.println(new Q371_Sum_of_Two_Integers().getSum(-1,-10));
    }
}
