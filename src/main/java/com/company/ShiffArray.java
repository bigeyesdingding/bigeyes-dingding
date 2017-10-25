package com.company;

public class ShiffArray {
    public static int find(int[] array, int target){
        //assume: taregt is not null
        if(array == null || array.length ==0){
            return -1;
        }
        if(array.length == 1){
            return array[0] == target ? 0: -1;
        }

        int left = 0;
        int right = array.length - 1;

        while(left+1 < right) {
            int mid = left + (right-left)/2;
            if(array[mid] == target){
                return mid;
            } else {
                if((array[mid] > target && target>array[left] && array[left]<array[mid]) ||
                        (array[left] < target && array[left]>array[mid])){
                    right = mid;
                } else {
                    left = mid;
                }
            }
        }
        if(array[left] == target){
            return left;
        } else {
            return array[right] == target? right: -1;
        }
    }

    public static void main(String[] args){
        int[] array = {5, 4};
        System.out.println(find(array, 4));
        System.out.println("test");
    }
}
