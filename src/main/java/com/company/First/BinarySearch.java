package com.company.First;


import java.util.ArrayList;
import java.util.Arrays;

public class BinarySearch {
    public static void binarySearchTest(){

        ArrayList result = new ArrayList();

        int[] arrayI = {1,2,4,5,7,8,9};
        int[] arrayII = {1,2,9};
        int[] arrayIII = {};
        int[] arrayIV = {1};
        int[] arrayV = null;
        int[] arrayVI = {1,2,3,8,9};
        int[] arrayVII = {4,5,5,5,5,5,5};
        int[] arrayVIII = {1,2,3,8,9};


        result.add(binarySearchIterative(arrayI, 4));//pass
        result.add(binarySearchIterative(arrayI, 1));//pass
        result.add(binarySearchIterative(arrayI, 6));//pass
        result.add(binarySearchIterative(arrayIII, 6));//pass
        result.add(binarySearchIterative(arrayIV, 6));//pass
        result.add(binarySearchIterative(arrayV, 6));//pass

        result.add(binarySearchRecursive(arrayI, 6, 0, 6));//pass
        result.add(binarySearchRecursive(arrayI, 7, 0, 6));//pass
        result.add(binarySearchRecursive(arrayIV, 1, 0, 0));//pass

        result.add(closestIndex(arrayVI, 6));//pass
        result.add(closestIndex(arrayIII, 6));//pass
        result.add(closestIndex(arrayIV, 6));//pass
        result.add(closestIndex(arrayV, 6));//pass


        result.add(firstOccurance(arrayVII, 5));//pass
        result.add(firstOccurance(arrayVII, 10));//pass
        result.add(firstOccurance(arrayIII, 10));//pass
        result.add(firstOccurance(arrayIV, 1));//pass
        result.add(firstOccurance(arrayV, 10));//pass


        result.add(lastOccurance(arrayVII, 5));//pass
        result.add(lastOccurance(arrayVII, 10));//pass
        result.add(lastOccurance(arrayIII, 10));//pass
        result.add(lastOccurance(arrayIV, 1));//pass
        result.add(lastOccurance(arrayV, 10));//pass


        closestK(arrayVIII, 2,3);

        result.add(smallestLargerOne(arrayV, 4));//pass
        result.add(smallestLargerOne(arrayIV, 0));//pass
        result.add(smallestLargerOne(arrayIII, 4));//pass
        result.add(smallestLargerOne(arrayVIII, 0));//pass



        System.out.println(result);
    }
    public static int binarySearchIterative(int[] array, int k){

        if(array==null || array.length<1){
            return -1;
        }
        int left =0;
        int right =array.length-1;


        while(left<=right){
            int mid = left+(right-left)/2;
            if(k==array[mid]){
               return mid;
            }else{
                if(k<array[mid]){
                    right = mid-1;
                }else{
                    left = mid+1;
                }
            }
        }
        return -1;

    }
    public static int binarySearchRecursive(int[] array, int k, int l, int r){

        //base case
        if(l>r){
            return -2;
        }

        //recusive rules
        int mid = l+(r-l)/2;
        if(k==array[mid]){
            return mid;
        }else if(k<array[mid]){
            return binarySearchRecursive(array, k,l,mid-1);
        }else{
            return binarySearchRecursive(array, k,mid+1,r);
        }

    }


    public static int closestIndex(int[] array, int target){
        if(array==null || array.length<1){
            return -1;
        }
        int left =0;
        int right =array.length-1;


        while(left<right-1){
            int mid = left+(right-left)/2;
            if(target==array[mid]){
                return mid;
            }else{
                if(target<array[mid]){
                    right = mid;
                }else{
                    left = mid;
                }
            }
        }

        if(Math.abs(array[left]-target)<Math.abs(array[right]-target)){
            return left;
        }else{
            return right;
        }

    }

    public static int firstOccurance(int[] array,int target){
        if(array==null ||array.length<1){
            return -1;
        }

        int left = 0;
        int right = array.length-1;

        while(left<right-1){
            int mid = left+(right-left)/2;
            if(array[mid]==target){
                right = mid;//very important, must keep the mid in this array
            }else if(array[mid]>target){
                right = mid-1;
            }else{
                left= mid+1;
            }
        }

        //post-precessiong
        if(array[left]==target){
            return left;
        }else if(array[right]==target){
            return right;
        }else{
            return -1;
        }

    }

    public static int lastOccurance(int[] array,int target){
        if(array==null ||array.length<1){
            return -1;
        }

        int left = 0;
        int right = array.length-1;

        while(left<right-1){
            int mid = left+(right-left)/2;
            if(array[mid]==target){
                left = mid;//very important, must keep the mid in this array
            }else if(array[mid]>target){
                right = mid-1;
            }else{
                left= mid+1;
            }
        }

        //post-precessiong
        if(array[right]==target){
            return right;
        }else if(array[left]==target){
            return left;
        }else{
            return -1;
        }

    }

    public static int[] closestK(int[] array, int k, int target){
        if(array==null || array.length<1 || array.length<=k){
            System.out.println(Arrays.toString(array));
            return array;
        }

        int left = 0;
        int right = array.length-1;

        while(left<right-1){
            int mid = left+(right-left)/2;
            if(array[mid]==target){
                left = mid;
                right = mid+1;
            }else if(array[mid]>target){
                right = mid;
            }else{
                left = mid;
            }
        }

        //post-processing
        while(right-left-1<k && right<array.length && left>=0){
            if(Math.abs(array[left]-target)== Math.abs(array[right]-target)){
                right++;
                left--;
            }else if(Math.abs(array[left]-target)> Math.abs(array[right]-target)){
                right++;
            }else{
                left--;
            }
        }

        System.out.println(Arrays.toString(Arrays.copyOfRange(array, left+1,right)));
        return Arrays.copyOfRange(array, left+1,right);


    }

    public static int smallestLargerOne(int[] array, int target ){

        if(array == null || array.length<1){
            return -1;
        }

        int left = 0;
        int right = array.length-1;

        while(left<right){
            int mid = left + (right-left)/2;
            if(array[mid]<=target){
                left = mid+1;
            }else{
                right = mid;
            }

        }

        //post-processing
        if(array[right]>target){
            return right;
        }

        return -1;
    }


    public static void main(String[] args){
        /*
        Binary Search:
        The principle of binary search is to reduce the search space by 1/2 of its original size.
        In the meanwhile, you must guarantee the target must not be rules out.

        ?? Does the original array should be sorted or not?

        Q1: return an target element's index
        {1,2,4,5,7,8,9} --> find 4, return, 2


        Q2: binary search in 2D space
        sorted on each row, first element of next row is larger or eaqual to the last element of previous row,
        now giving a target number, return the poisition that the target located within the matrix.

        Matrix:
        1  2  3  4
        5  6  7  8
        9  10 11 12
        target == 7
            s1: to 1D
            s2:run two times binary search, one for finding row, another for finding column

        Q3: find an element in the array that is closest to the target number

        int a[5] = {1,2,3,8,9}  target == 4
        return  2

        Q4: rewturn the index of the first occurrence of an element, say5
        a[7] = {4,5,5,5,5,5,5}
        if target == 5 return 1;
        if target ==10 return -1

        Q5: return the index of the last occurance of amn elmemnt
        a[7] = {4,5,5,5,5,5,5}
        if target == 5 return 6;
        if target ==10 return -1;

        Q6: find the closest k elements that are closest to the given target
        k ==3   target==4   int[5] = {1,2,3,8,9}   --> return {1,2,3}
        Step 1: move l and r to make it close to the target number, until there is only two or less eleements between l and r
        Step2:  merge, move left and right by comparing the difference of Math.abs(array[left]- target) vs Math.abs(array[right]- target),
                left--, if left one is smaller
                right++, if right one is smaller
                until right-left>=k

        Q7: find the smallest element that is larger than a target number?
        k == 3   target==4   int[5] = {1,2,3,8,9}   --> 8

        Q8: find the largest element in the array
        int[] array = {1,3,7,23,57,100,99,86,44,32,21}



        */


        binarySearchTest();


    }
}
