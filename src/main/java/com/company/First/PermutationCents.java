package com.company.First;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationCents {

    public static void helper(int moneyLeft, int level, int[] solu, int[] coinValue){
        //base case
        if(level == coinValue.length-1){
            solu[level] = moneyLeft;
            System.out.println(Arrays.toString(solu));
            return;
        }

        //recursive rule
        for(int i = 0; i <= moneyLeft/coinValue[level]; i++){
            solu[level] = i;
            helper(moneyLeft-i*coinValue[level], level+1, solu, coinValue);
        }
    }

    public static void findCombination(){
        int[] coinValue = new int[] {25,10,5,1};
        int moneyTotal = 99;
        int[] solu = new int[4];
        helper(moneyTotal, 0, solu, coinValue);
    }

    public static void main(String[] args) {
        // write your code here
        findCombination();
        String a = new String("123456");
        String b = new String("123456");
        System.out.println(a.equals(b));
    }
}