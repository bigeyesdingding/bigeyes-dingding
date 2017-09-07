package com.company;

import java.util.Arrays;

public class StringShuffling {
    public static int[] reorder(int[] array) {
        //assumptions: array is not null
        if (array.length % 2 == 0) {
            reorder(array, 0, array.length-1);
        }else{
            reorder(array, 0, array.length-2);
        }
        return array;
    }

    private static void reorder(int[] array, int left, int right){
        int length = right-left+1;
        //if the subarray has 2 or 0 elements, we can just return as this should be the base case
        if(length<=2){
            return;
        }
        int mid = left + length/2;
        int lmid = left +length/4;
        int rmid = left + length*3/4;
        reverse(array, lmid, mid-1);
        reverse(array, mid, rmid-1);
        reverse(array, lmid, rmid-1);

        //half of the left partition's size = lmid-left.
        reorder(array, left, left+(lmid-left)*2-1);
        reorder(array, left+(lmid-left)*2,right);
    }

    private static void reverse(int[] array, int left, int right){
        while(left<right){
            int tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
            left++;
            right--;
        }
    }


    public static void main(String[] args){
        System.out.println("Array reorder in place implementation...");
        int[] array = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(Arrays.toString(reorder(array)));
    }
}

