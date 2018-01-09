package com.company.First.OA;

import java.util.Deque;
import java.util.LinkedList;

public class BaseballGame {
    public int countScore(String[] s){
        if(s == null || s.length == 0){
            return 0;
        }


        int sum = 0;
        Deque<Integer> stack = new LinkedList<>();

        for(int i = 0; i<s.length; i++){

            if(s[i] == "Z"){
                sum -= stack.pollLast();
            }else if(s[i] == "X"){
                int cur = stack.peekLast()*2;
                sum += cur;
                stack.offerLast(cur);
            }else if(s[i] == "+"){
                int num1 = stack.pollLast();
                int num2 = stack.peekLast();
                stack.offerLast(num1);
                stack.offerLast(num1+num2);
                sum += num1+num2;
            }else {
                int num = Integer.parseInt(s[i]);
                sum += num;
                stack.offerLast(num);
            }

        }
        return sum;

    }
}
