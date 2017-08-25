package com.company;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class DequeStack {
    private Deque<Integer> front;
    private Deque<Integer> back;

    public DequeStack(){
        front = new LinkedList<Integer>();
        back = new LinkedList<Integer>();
    }

    public int size(){
        return front.size()+back.size();
    }

    public boolean isEmpty(){
        return front.isEmpty() && back.isEmpty();
    }

    public Integer offerFirst(int value){
        front.offerFirst(value);
        return value;
    }

    public Integer offerLast(int value){
        back.offerFirst(value);
        return value;
    }
    public Integer pollFirst(){
        move(back, front);
         return front.pollFirst();
    }
    public Integer pollLast(){
        move(front, back);
        return back.pollFirst();
    }

    private void move(Deque<Integer> l1, Deque<Integer> l2){
        if(!l2.isEmpty()){
            return;
        }

        while(!(l1.isEmpty())){
            l2.offerFirst(l1.pollFirst());
        }
    }


    private Integer peekFirst(){
        move(back, front);
        return front.peekFirst();
    }
    private Integer peekLast(){
        move(front, back);
        return back.peekLast();
    }

    public static void main(String[] args){
        System.out.println("This is the class to implement de-que by using several stacks.");

        DequeStack test = new DequeStack();

        System.out.println(test.peekFirst());
        System.out.println(test.offerFirst(10));
        System.out.println(test.offerFirst(20));
        System.out.println(test.offerLast(565));
        System.out.println(test.offerLast(8));

        System.out.println(test.pollLast());
        System.out.println(test.pollLast());
        System.out.println(test.pollLast());
        System.out.println(test.pollLast());





    }
}
