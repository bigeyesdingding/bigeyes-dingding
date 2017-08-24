package com.company;

import java.util.Deque;
import java.util.LinkedList;

public class StackWithMin {

    private Deque<Integer> stack;
    private Deque<Integer> minStack;

    public StackWithMin(){
        stack = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
    }

    public Integer min(){

        if(stack.isEmpty()){
            return null;
        }
        return minStack.peekFirst();
    }

    public Integer poll(){
        if(stack.isEmpty()){
            return null;
        }

        Integer result = stack.pollFirst();
        if(result==minStack.peekFirst()){
            minStack.pollFirst();
        }
        return result;
    }
    public Integer offer(int value){
        stack.offerFirst(value);
        if(minStack.isEmpty() || value <= minStack.peekFirst()){
            minStack.offerFirst(value);
        }
        return value;

    }
    public Integer top(){
        if(stack.isEmpty()){
            return null;
        }

        return stack.peekFirst();
    }


    public static void main(String[] args){
        /*how to optimize the stack space usage of min-stack??*/

        StackWithMin test = new StackWithMin();


        System.out.println(test.offer(3));
        System.out.println(test.min());

        System.out.println(test.offer(7));
        System.out.println(test.min());
        System.out.println(test.offer(3));
        System.out.println(test.min());
        System.out.println(test.offer(2));
        System.out.println(test.min());
        System.out.println(test.offer(1));
        System.out.println(test.min());
        System.out.println(test.offer(1));
        System.out.println(test.min());
        System.out.println(test.offer(4));
        System.out.println(test.min());
        System.out.println(test.top());

        System.out.println(test.poll());
        System.out.println(test.min());
        System.out.println(test.poll());
        System.out.println(test.min());
        System.out.println(test.top());
        System.out.println(test.poll());
        System.out.println(test.min());
        System.out.println(test.poll());
        System.out.println(test.min());

    }
}
