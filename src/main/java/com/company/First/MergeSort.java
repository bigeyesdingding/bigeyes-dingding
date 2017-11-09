package com.company.First;

public class MergeSort {

    public static char[] sortI(char[] array){

        char[] helper = new char[array.length];

        mergeSort(array, helper, 0, array.length-1 );

        System.out.println(array);
        return array;
    }
    private static void mergeSort(char[] array, char[] helper,int l,int r ){
        //base case
        if(l==r){
            return;
        }
        //recersive rule
        int mid = l+(r-l)/2;
        mergeSort(array, helper, l, mid);
        mergeSort(array, helper, mid+1, r);
        merge(array, helper, l, mid, r);

    }
    private static void merge(char[] array, char[] helper,int l,int mid, int r ){
        for(int i=l; i<=r;i++){
            helper[i]=array[i];
        }
        int rightIndex = mid+1;
        int leftIndex =  l;
        int index = l;
        while(leftIndex<= mid && rightIndex <=r){
            if(helper[leftIndex]>helper[rightIndex]){
                //swap the left and right elements
                array[index++] = helper[rightIndex++];
            } else{
                array[index++] = helper[leftIndex++];

            }
        }

        //if there is some remaining elements in left half, please add them to the end of the subarray,
        //if there is some in the right half, there is no need to do so, since they are at the end of the subarray already

        while(leftIndex <= mid){
            array[index++] = helper[leftIndex++];
        }

    }


    public static void main(String[] args){
        /*
        Please be notified that a good solution contains:
        1.document your assumptions
        2.explain your approach and how you intend ti solve the problem
        3.provide code comments where applicable
        4.explain the big-O run time complexity of your solution. Justify your answer.
        5.Identifyany additional data strctures you used and justify why you use them.
        6.only provide  your best answer to each part of the question


        Merge Sort:
        Break the unsorted list into n sublists, each containing 1 element
        Then, Repeatedly merge sublists to produce new sorted sublists until there is only 1 sublists remaining.
        While doing merging, the key is to move the smaller element each time(assume that is an ascending sorting problem)

        It divides input array in two halves, calls itself for the two halves and then merges the two sorted halves.

        Q1: A1B2C3D4 --> ABCD1234



        Q2: ABCD1234 -->  A1B2C3D4


        Q3:Count-array Problem
        A[N] = {4,1,3,2} --> B[N] = {3,0,1,0}


        */

        String s = "A1B2C3D4";
        char[] array = s.toCharArray();
        char[] result = new char[array.length];
        result = sortI(array);


    }
}
