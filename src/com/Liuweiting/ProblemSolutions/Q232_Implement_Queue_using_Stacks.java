package com.Liuweiting.ProblemSolutions;

import java.util.Stack;

/**
 * Created by DamonLiu on 2017/9/10.
 */
public class Q232_Implement_Queue_using_Stacks {


    /** Initialize your data structure here. */
    public Q232_Implement_Queue_using_Stacks() {

    }

    private Stack<Integer> innerStack = new Stack<>();
    private Stack<Integer> helperStack = new Stack<>();

    /** Push element x to the back of queue. */
    public void push(int x) {
        while (!helperStack.isEmpty()){
            innerStack.push(helperStack.pop());
        }
        innerStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (helperStack.size() > 0){
            return helperStack.pop();
        }
        while (innerStack.size() > 1){
            helperStack.push(innerStack.pop());
        }
        return innerStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        return innerStack.lastElement();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return innerStack.isEmpty();
    }
}
