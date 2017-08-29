package com.Liuweiting;

import javax.lang.model.type.ArrayType;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/arithmetic-slices/#/description
 * Created by DamonLiu on 2017/5/29.
 *
 *
 * A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

 For example, these are arithmetic sequence:

 1, 3, 5, 7, 9
 7, 7, 7, 7
 3, -1, -5, -9
 The following sequence is not arithmetic.

 1, 1, 2, 5, 7

 A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

 A slice (P, Q) of array A is called arithmetic if the sequence:
 A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

 The function should return the number of arithmetic slices in the array A.

 Example:

 A = [1, 2, 3, 4]

 return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.

 */
public class Q413_Arithmetic_Slices {
    /**
     * A slice is a sequence of at least 3 length.
     * @param A
     * @return
     */
    public int numberOfArithmeticSlices(int[] A) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < A.length-2; i++) {
            if (A[i]-A[i+1]==A[i+1]-A[i+2]){
                int index = getLastIndexSinceIndex(A,i);
                map.put(i,index);
                i = index;
            }
        }
        int sum = 0 ;

        for (Map.Entry<Integer,Integer> tmp : map.entrySet()){
            sum += getCountForPair(tmp.getKey(),tmp.getValue());
        }

        return sum;
    }

    HashMap<Integer,Integer> length2count = new HashMap<>();
    public int getCountForPair(int begin,int end){

        int length = end - begin +1;

        if (length2count.containsKey(length)) return length2count.get(length);

        if (length == 3){
            return 1;
        }
        int counter = 0;
        int anotherCounter = 0;
        for ( ;length>=3;length--){
            anotherCounter ++;
            counter += anotherCounter;
        }

        length2count.put(length, counter);

        return counter;
    }

    public int getLastIndexSinceIndex(int[]A, int index){
        int diff = A[index+1] - A[index];
        for (int i=index+3;i<A.length;i++){
            if (A[i]-A[i-1] == diff){
                continue;
            } else{
                return i-1;
            }
        }
        return A.length-1;
    }

    public static void main(String[] args) {
//        int[] input = {1,1, 3, 5, 7, 9,12};
        int [] input = {1,2,3,8,9,10};
        System.out.println(new Q413_Arithmetic_Slices().numberOfArithmeticSlices(input));
    }
}
