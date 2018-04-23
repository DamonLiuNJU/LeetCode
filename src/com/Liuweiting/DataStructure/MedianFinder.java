package com.Liuweiting.DataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by DamonLiu on 2018/3/11.
 */
public class MedianFinder {

    /**
     * initialize your data structure here.
     */
    List<Integer> data;//consider data an always soted list.
    int size = 0;

    public MedianFinder() {
        data = new ArrayList<>();
    }

    public void addNum(int num) {
        size++;
        if (size == 1){
            data.add(num);
            return;
        }

        if (num < data.get(0)){
            data.add(0,num);
            return;
        }

        if (num > data.get(data.size()-1)){
            data.add(num);
            return;
        }

        int indexToInsert = binarySearchForPlaceToInsert(num);
        data.add(indexToInsert,num);
    }

    private int binarySearchForPlaceToInsert(int num) {
        int lo = 0;
        int hi = data.size();
        int mid;
        while(lo < hi - 1){
            mid = (int) (lo * 0.5 + hi * 0.5);
            if (data.get(mid)<=num){
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return hi;
    }

    public double findMedian() {
        if (size % 2 == 1) {
            return data.get(size / 2);
        } else {
            return (data.get(size / 2 - 1) + data.get(size / 2)) / 2.0;
        }
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
//        Integer[] numbers = {1,2,3,4,4,4,7,8,9};
//        Collections.addAll(mf.data, numbers);
        mf.addNum(-1);
        System.out.println(mf.findMedian());
        mf.addNum(-2);
        System.out.println(mf.findMedian());
        mf.addNum(-3);
        System.out.println(mf.findMedian());
        mf.addNum(-4);
        System.out.println(mf.findMedian());
        mf.addNum(-5);
        System.out.println(mf.findMedian());
    }
}
