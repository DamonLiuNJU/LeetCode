package com.Liuweiting.DataStructure;

import java.util.Iterator;

/**
 * Created by DamonLiu on 2018/1/21.
 */
public class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> ite;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        ite = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return -1;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        return ite.next();
    }

    @Override
    public boolean hasNext() {
        return ite.hasNext();
    }
}
