package com.company;
import java.util.*;
import java.lang.*;

public class Main {
    public static int countOne(int n){

        int bitter = 1;
        while(true){
            if(n<1){
                break;
            }
            n = n>>bitter;
            System.out.println(n);
            bitter = bitter<<1;
        }
        return 4;
    }
    public static int[] countArray(int n){
        int[] result = new int[n+1];
        //base case
        result[0] = 0;
        result[1] = 1;
        result[2] = 1;
        // production rule
        // 1. M[i] respresents the number of '1's for i
        // 2. lastPower represent the biggest integer which is the power of 2
        // M[i]=M[lastPower]+M[i-lastPower] if i-lastPower < lastPower
        int lastPower = (int)Math.pow(2,1);
        for(int i = 3; i<= n;i++){
            if(i<2*lastPower){
                result[i] = result[lastPower]+result[i-lastPower];
            }else{
                result[i] = 1;
                lastPower = i;
            }
        }
        return result;
    }



    public static void main(String[] args) {
	    // write your code here
        int[] q = countArray(15);
        System.out.println(Arrays.toString(q));


    }
}
