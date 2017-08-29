package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Heap{

    /*
    * Heap which in Java, the acomplishment of PriorityQueue class, has following rules and principles
    * 1: A complete tree
    * 2. min heap max heap
    *
    * 3.lchild = parent *2 +1
    *   rchild = parent*2 +2
    * 4.unsorted but must follow those rules
    *
    * 5.operations
    *   a.insert
    *   b.update
    *   c.get/top
    *   d.pop
    *   e.heapify
    *
    *
    * Q1: find the smallest k elements from an unsorted array of size n
    *
    * Assumption: 1: array is not null
    *             2. k>=0 and k<=array.length
    * */


    //max-heap
    public static int[] maxHeap(int[] array, int k){
        if(array.length == 0 || k == 0){
            return new int[0];
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new
        Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2){
                //be careful do not use == to say equals
                if(o1.equals(o2)){
                    return 0;
                }
                return o1>o2?-1:1;
            }
        });
        for(int i = 0; i<array.length; i++){
            if(i<k){
                //offer the first k elements into the max-heap
                maxHeap.offer(array[i]);
            }else if(array[i]<maxHeap.peek()){
                //for otehr elements, only offer it into the heap only if it is smaller than the max value
                //in the max heap
                maxHeap.poll();
                maxHeap.offer(array[i]);
            }
        }

        int[] result = new int[k];
        for(int i = 0; i<k; i++){
            result[i] = maxHeap.poll();
        }
        return result;
    }


    //min-heap

    public static int[] minHeap(int[] array, int k){
        if(array.length<1 || k ==0){
            return new int[0];
        }

        PriorityQueue< Integer> minHeap = new PriorityQueue<>(array.length, new
            Comparator<Integer>(){

                @Override
                public int compare(Integer o1, Integer o2){
                    //be careful do not use == to say equals
                    if(o1.equals(o2)){
                        return 0;
                    }
                    return o1>o2?-1:1;
                }
            });


        //offer elements into the heap
        for(int i =0; i< array.length;i++){
            minHeap.offer(array[i]);
        }
        int[] result = new int[k];
        //poll out smaller elements
        for(int j =0; j<k; j++){
            result[j] = minHeap.poll();
        }
        return result;
    }


    //quick sort
    public static int[] quickSort(int[] array, int k){
        if(array.length == 0 || k == 0){
            return new int[0];
        }

        //quick sort the array to find the kth smallest element
        //after calling this method. the first k elements in the array are the k smallest ones(but not sorted)
        quickSelect(array, 0, array.length-1, k);

        //copy the first k elements
        int[] result = Arrays.copyOf(array, k);
        Arrays.sort(result);
        return result;
    }

    public static void quickSelect(int[] array, int left, int right, int target){
        //do partition and use the opivot value then find out the pivot index
        int mid = partition(array, left, right);

        //if the index plus 1 is the target k, then it is the array result we want to find
        //unlike quick sort, the terminate condition is not left>right, it should be returned back once we find the target

        if(mid+1 == target){
            return; //base case
        }else if(mid+1<target){
            //only need to do the recursive  call on right part if the kth smallest is in the right part
            quickSelect(array, mid+1,right,target);
        }else{
            //only need to do the recursive  call on left part if the kth smallest is in the left part
            quickSelect(array, left, mid-1,target);
        }

    }
    public static int partition(int[] array, int left, int right){
        int pivot = array[right];
        int l = left;
        int r = right-1;
        while(l<=r){
            if(array[l]>pivot){
                swap(array, l, r--);
            }else{
                l++;
            }
        }
        swap(array, l, right);
        return l;

    }
    public static void swap(int[] array, int left, int right){
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;

    }


    public static void main(String[] args){
        System.out.println("This is the algo Heap applications");
    }
}
