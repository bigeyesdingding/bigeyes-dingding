package com.company;

import java.util.Deque;
import java.util.LinkedList;

public class QueueStack {
    private LinkedList<Integer> in;
    private LinkedList<Integer> out;


    public  QueueStack(){
        in = new LinkedList<Integer>();
        out = new LinkedList<Integer>();
    }

    public Integer poll(){
        //if out is empty, neet topo move elements from in to out
        move();
        return out.isEmpty()? null: out.pollFirst();
    }
    public void offer(int value){
        in.offerFirst(value);

    }

    public Integer  peek(){

        //move firtst
        move();
        return out.isEmpty()? null: out.peekFirst();

    }

    public int size(){
        return in.size()+out.size();
    }

    public boolean isEmpty(){
        return in.size()==0 && out.size()==0;
    }

    private void move(){
        if(out.isEmpty()){
            while(!in.isEmpty()){
                out.offerFirst(in.pollFirst());
            }
        }
    }

    public static void main(String[] args){
        System.out.println("this is the queue, stack, deque");
        QueueStack test = new QueueStack();
        test.offer(3);
        test.offer(2);
        test.offer(1);
        System.out.println(test.size());
        System.out.println(test.isEmpty());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.poll());
        System.out.println(test.size());
        System.out.println(test.poll());
        System.out.println(test.poll());
        System.out.println(test.isEmpty());


        //personal test

    }
}
