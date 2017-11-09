package com.company.First;

import java.util.Arrays;

public class QuickSort {

    public static int[] quickSort(int[] array){
        if(array== null || array.length<2){
            return array;
        }

        sort(array, 0, array.length-1);
        return array;
    }

    public static int choosePivot(int start, int end){
        return start + (int)(Math.random()*( end - start +1 ));
    }

    public static void swap(int[] array, int left, int right){
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void sort(int[] array, int left, int right){

        if(left>=right){
            return;
        }

        //1. partition
        int pivotIndex = partition(array, left, right);

        //2.do recursion
        sort(array, left, pivotIndex-1);
        sort(array, pivotIndex+1, right);

    }

    public static int partition(int[] array, int left, int right){
        //0. pre-processing
        int pivotIndex = choosePivot(left, right);
        swap(array, pivotIndex, right);
        int pivot = array[right];

        int leftPointer = left;
        int rightPointer = right-1;

        //1.1 partition moving two pointers
        while(leftPointer<=rightPointer){
            if(array[leftPointer]>pivot){
                swap(array, leftPointer, rightPointer--);
            } else{
                leftPointer++;
            }
        }

        //1.2 swap the pivot to the right place
        swap(array, ++rightPointer,right);

        return rightPointer;
    }

    public static void main(String[] args){
        System.out.println(Arrays.toString(args));

        int[] arrayI = {10, 80, 30, 90, 40, 50, 70};
        int[] arrayII = {32,5,98,0};
        int[] arrayIII = {};
        int[] arrayIV = {32};
        int[] arrayV = {5,39};

        /*
            Please be notified that a good solution contains:
            1.document your assumptions
            2.explain your approach and how you intend ti solve the problem
            3.provide code comments where applicable
            4.explain the big-O run time complexity of your solution. Justify your answer.
            5.Identifyany additional data strctures you used and justify why you use them.
            6.only provide  your best answer to each part of the question

            Quick Sort  -----(assumption: there is no same value numbers in the array)------
            It picks an element as pivot and partitions the given unsorted array arround the picked pivot.
            There are different ways to to pick the pivot.

            Step 1; choose the very right item as the pivot

            Step 2: using two pointers, i starts from 0 index and moves forward,
            all elements before i index should be smaller then the pivot

            j from the last index and moves backward
            all elements after index j (ex-cludes j) represents elements bigger than pivot picked
            while i<=j, do:
                case one: if array[i] > pivot: swap(array, i, j), j--;
                case two: else i++

            Step 3: do recursive:
            base case: left>=right
            recursive rule: recursive all elements left of the pivot; recursive all elements right of the pivot

            ?? How to deal with the pivot?
               a. One way is not deal with it at all, cus it can be compared in step 2
               b. Another way is to choose the very right one as pivot, then keep it at the last index. the pointer j
               should start from array.length-2 and backward; after the iteration for the round, swap(array, --j,array.length-1)


       */
        quickSort(arrayII);

        System.out.println(Arrays.toString(arrayII));
        System.out.println(Long.MAX_VALUE);



        /*

        Conclusions:
        1.  Be careful about the rightPointer and leftPointer,
            start position;
            representations;
            movement;
            end position;
            return value;

        */


    }
}
