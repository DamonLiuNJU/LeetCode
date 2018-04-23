package com.Liuweiting.ProblemSolutions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by DamonLiu on 2017/10/24.
 */


// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}

class NestInt implements NestedInteger {

    boolean isInteger;
    int val;
    List<NestedInteger> list;

    public NestInt(int n){
        isInteger = true;
        val = n;
    }



    @Override
    public boolean isInteger() {
        return isInteger;
    }

    @Override
    public Integer getInteger() {
        return null;
    }

    @Override
    public List<NestedInteger> getList() {
        return null;
    }
}

public class NestedIterator implements Iterator<Integer> {

    ArrayList<Integer> list = new ArrayList<>();
    int currentIndex;
    public NestedIterator(List<NestedInteger> nestedList) {
        currentIndex = 0;
    }
    private void dealWithNestedList(List<NestedInteger> nestedList, List<Integer> list){
        for (int i = 0; i < nestedList.size(); i++) {
            if (nestedList.get(i).isInteger()){
                list.add(nestedList.get(i).getInteger());
            } else {
                dealWithNestedList(nestedList.get(i).getList(),list);
            }
        }
    }

    @Override
    public Integer next() {
        return list.get(currentIndex++);
    }

    @Override
    public boolean hasNext() {
        return currentIndex < list.size();
    }

    public static void main(String[] args) {
        List<NestedInteger> nestedList = new ArrayList<>();
        nestedList.add(new NestedInteger() {
            @Override
            public boolean isInteger() {
                return false;
            }

            @Override
            public Integer getInteger() {
                return null;
            }

            @Override
            public List<NestedInteger> getList() {
                return new ArrayList<NestedInteger>();
            }
        });
        NestedIterator i = new NestedIterator(nestedList);
        while (i.hasNext()) System.out.println(i.next());
    }
}

