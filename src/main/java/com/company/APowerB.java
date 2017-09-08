package com.company;

public class APowerB {

    public static double aPowerB(int a , int b){
        if(a == 0){
            return 0;
        }

        if(b == 0){
            return 1;
        } else if(b>0){
            return helper(a, b);
        } else{
            return 1/helper(a, -b);
        }

    }

    private static double helper(int a, int i) {
        if(i == 1){ //base case
            return a;
        }

        int half = i/2;
        int remain = i - half;
        return helper(a,half)*helper(a,remain);
    }


    public static void main(String[] args){

        System.out.println("This is the Recursion II a power b problem!");
        System.out.println(aPowerB(2,10));
    }

}
