package com.company.First.ershua;

public class BitCalculation {
    //find the missing one number
    public int missing(int[] array) {
        // Write your solution here.
        if(array == null || array.length == 0){
            return -1;
        }
        int xor = array[0];
        for(int i = 1; i<array.length; i++){
            xor ^= array[i];
        }
        return xor;
    }

    //find the first missing number
    public int firstMiss( int[] array){
        if(array == null || array.length == 0){
            return -1;
        }


    }
}
