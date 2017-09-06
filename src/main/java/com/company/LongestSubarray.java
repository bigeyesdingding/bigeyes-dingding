package com.company;

import java.util.Arrays;
import java.util.Set;

public class LongestSubarray {

    /*
    * Be careful about the Subarray, Subsequence, Subset:
    *
    * 1. Subarray is a contiguous subarray
    * 2. Subsequence contain elements whose subscripts are increasing in the original sequence
    * 3. Subset contain any possible combinations of original set.
    *
    * */
    public static int[] subarray(int[] array){
        System.out.println("The subarray is: ");
/*
        //dp represents the longest subarray from index 0 to index i (the i element must be included)
        int[] dp = new int[array.length];
        dp[0] = 1;*/

        //using global to record the longest subarray
        int start = 0;
        int end = 0;
        int curStart = 0;

        int max = 1;

        for(int i = 1; i<array.length;i++){
            if(array[i]>array[i-1]){
                max = max +1;

            }else{
                max = 1;
                curStart = i;
            }

            //check if it is a global max
            if(max>end-start+1){
                start = curStart;
                end = i;
            }
        }
        return new int[]{start, end};
    }

    public static int subsequence(int[] array){
        System.out.println("The subsequence is: ");

        //dp represents the longest sub-sequence from index 0 to index i (the i element must be included)
        int[] dp = new int[array.length];
        dp[0] = 1;

        //using global to record the longest sub-sequence
        int global = 1;

        for(int i = 1; i<array.length;i++){
            dp[i] = 1;
            for(int j = i-1; j>=0;j--){
                if(array[j]<array[i]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            global = Math.max(global, dp[i]);
        }
        return global;
    }

    /*suppose you have a 10 meters long rope, you can cut it at any integer length as you want, make the production of
    * cutted parts the biggest. (Assume at least one cut should be made)
    *
    *
    * */

    public static int cutRope(int n){
        //assume: n >1;
        System.out.println("The max production is: ");

        //the max production with cuts
        int[] dp = new int[n+1];
        dp[1] = 1;
        int left = 0;
        int right = 0;

        for(int i = 2; i <=n; i++){
            dp[i] = Integer.MIN_VALUE;
            for(int j = 1; j<i;j++){
                left = Math.max(dp[j],j);
                right =i-j;
                dp[i] = Math.max(dp[i],left*right);

            }
        }
        return dp[n];
    }

    private static int calculateRight(int left, int right) {
        return 0;
    }

    /*Given a word, can it be composed by concatenating words from a given dictionary?
    * eg: bob, cat, rob
    * word1: bcoabt--> falkse
    * word2: bobcatrob-->true
    * word3: bobbob-->true
    *
    * */
    public static boolean composedFromDict(Set<String> dict, String word){
        return true;
    }


    /*
    * Jump game:
    * given ana array of non-negative integers, you are initially positioned at the first index of the array. Each element
    * in the array represents your max jump length at that position. Determine if you are able to reach the last index.
    *    index 0 1 2 3 4
    * eg: A = [2,3,1,1,4]-->true
    *     B = [3,2,1,0,4]-->false
    * */

    public static void main(String[] args){
        System.out.println(" The type A DP problem:");
        float bmi = 18.5f;
        int[] array = {1,3,5,9,14,2,5,4,7,43,23};
        System.out.println(Arrays.toString(subarray(array)));
        System.out.println(subsequence(array));
        System.out.println(cutRope(6));
    }
}
