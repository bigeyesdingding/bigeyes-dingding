package com.company.First.ershua;


import java.util.ArrayList;
import java.util.List;

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
    public int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length ==0){
            return 1;
        }
        int i = 0;
        while(i<nums.length){
            if(nums[i] == i+1 || nums[i]<=0 || nums[i]>nums.length){
                i++;
            }else if(nums[nums[i]-1] != nums[i]){
                swap(nums, nums[i]-1, i);
            }else{
                i++;
            }
        }
        i = 0;
        while(i<nums.length && nums[i] == i+1){
            i++;
        }
        return i+1;
    }
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int firstMiss(int[] array){
        if(array== null || array.length == 0){
            return 0;
        }
        //using bit operation
        int xor = array[0];
        for(int i = 1; i<array.length; i++){
            xor ^= array[i];
        }

        for(int i = 1; i<=array.length+1; i++ ){
            xor ^= i;
        }
        return xor;
    }



    //common elements from two sorted list
    public List<Integer> common(List<Integer> a, List<Integer> b) {
        // Write your solution here.
        List<Integer> res = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i<a.size() && j<b.size()){
            if(a.get(i) == b.get(j)){
                res.add(a.get(i));
                i++;
                j++;
            }else if(a.get(i)<b.get(j)){
                i++;
            }else{
                j++;
            }
        }
        return res;
    }
}


