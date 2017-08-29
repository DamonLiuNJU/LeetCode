package com.Liuweiting.ProblemSolutions;

/**
 * Created by DamonLiu on 2017/8/14.
 */
public class Q657_Judge_Route_Circle {
    public boolean judgeCircle(String moves) {
        int x,y;
        x = y = 0;
        for (char tmp : moves.toCharArray()
             ) {
            switch (tmp){
                case 'U':
                    y++;
                    break;
                case 'D':
                    y--;
                    break;
                case 'L':
                    x--;
                    break;
                case 'R':
                    x++;
                    break;
                default:
                    return false;
            }
        }
        if (x==0 && y==0){
            return true;
        } else {
            return false;
        }
    }

    public int reverseBits(int n) {
        int mask = 1<<31;
        String tmp = Integer.toBinaryString(mask);
        int res = 0;
        String tmp2 = Integer.toBinaryString(res);

        for(int i = 0;i<32;i++){
            if((n&1)==1)res = res|mask;
            mask = mask>>>1;
            n= n>>>1;

            tmp = Integer.toBinaryString(mask);
            tmp2 = Integer.toBinaryString(res);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Q657_Judge_Route_Circle().reverseBits(7));


    }
}
