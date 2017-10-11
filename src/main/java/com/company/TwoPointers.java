package com.company;

import java.util.Arrays;

public class TwoPointers {

    /*
    *   Two pointers, two ways of moving
    *   I: fast and slow moving in the same direction
    *   left part is the result we need, between left and right is we not care, right part is the one going to explore
    *
    *   II: fast and slow moving in different directions
    *   Both left and right parts are what we need, between left and right is what we are going to explore
    *
    *
    *Q1:
    *   1.1 a sorted array,  保留k个重复元素
    *   1.2 a sorted array， 不保留任何重复元素
    *   1.3 unsorted array, remove adjanct characters , eg: abbbbazw -> zw
    *   1.4 an array of random numbers, push all zero to the end of the array,
    *       1.4.1 order of other elements can be changed
    *       1.4.2 order of others have to be the same as input
    *
    *Q2: Array comparisons
    *   2.1 using the least times comparisions to find the largest and smallest number in the array
    *   2.2 using the least times of comparisons to find the largest and second largest number in the array
    *
    *Q3: Print Matrix
    *   3.1 how to print a 2D array in  spiral order(N*N)
    *   3.2 how to rotate an N*N matrix clockwise by 90 degree ??
    *
    *
    *Q4: BFS & Binary Tree Problems
    *   4.1 Classic way to print the tree level by level (queue)
    *   4.2 Classic way to print the tree level by level in a zig-zag way
    *
    *
    *
    * */


    public static int[] moveZero(int[] input){
        if(input == null || input.length<=1){
            return input;
        }
        //all letters to the right of j should be zeros, ex-cluding j index
        int i = 0, j = input.length-1;
        while(i<=j){
            if(input[i] == 0){
               swap(input, i, j);
               j--;
            } else{
                i++;
            }
        }
        return input;
    }

    private static void swap(int[] input, int i, int j) {

        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;

    }

    public static int[] keepKCharacters(int[] input, int k){
        //assume: k>=1
        if(input == null || input.length <= k){
            return input;
        }
        //Time: O(n) Space: O(n)
        //all letters on the left of slow pointer, including the slow index
        int fast = 0;
        int slow = -1;
        int begin = fast;
        while(fast<input.length){
            if(input[begin] == input[fast] && fast-begin<k){
                input[++slow] = input[fast];
            } else if(input[begin] != input[fast]){
                input[++slow] = input[fast];
                begin = fast;

            }
            fast++;
        }
        return Arrays.copyOf(input, slow+1);
    }

    public static int[] remove(int[] input){
        if(input == null || input.length <=1){
            return input;
        }

        int slow = -1;
        int fast = 1;
        int begin = 0;

        while(fast< input.length){
            if(input[begin] != input[fast]){
                if(fast-begin<=1){
                    input[++slow] = input[begin];
                }

                begin = fast;
            }
            fast++;

        }
        return Arrays.copyOf(input, slow+1);
    }

    public static Character[] removeAllDup(Character[] input){
        if(input == null || input.length <=1){
            return input;
        }

        //two pointers: fast and slow, all index slow is the one uncertain

        int slow = 0; // all the letters on the left hand side of slow are needed, index slow is the one uncertain
        int fast = 1;
        boolean isDup = false;

        while(fast < input.length){
            if(slow<0 ||input[fast] != input[slow]){
                if(isDup){
                    slow--;
                } else{
                    input[++slow] = input[fast++];
                }
                isDup = false;
            } else {
                isDup = true;
                fast++;
            }
        }
        return Arrays.copyOf(input, slow+1 );
    }

    public static void findI( int[] input){
        int size = input.length;
        int[] loser = new int[size/2];
        int[] winner = new int[size - loser.length];

        for(int i = 0; i<size/2;i++){
            if(input[2*i]>input[2*i+1]){
                winner[i] = input[2*i];
                loser[i] = input[2*i+1];
            } else {
                winner[i] = input[2*i+1];
                loser[i] = input[2*i];
            }

        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i<winner.length;i++){
            max = Math.max(max, winner[i]);
        }
        for(int j = 0; j<loser.length;j++){
            min = Math.min(min, loser[j]);
        }
        System.out.println("The max value is: "+ max);
        System.out.println("The min value is: " + min);
    }

    public static void findII(int[] input){
        
    }

    public static void main(String[] args){
        System.out.println("this is the test!");
        int[] input = {2,2,2,2,2,2,2,1};
        System.out.println(Arrays.toString(keepKCharacters(input,3)));
        System.out.println(Arrays.toString(remove(input)));
        Character[] inputI = {'z','a','b','b','b','b','a','a', 'a','z','z','a','w'};
        System.out.println(Arrays.toString(removeAllDup(inputI)));
        int[] test = {2,0,4,6,9,-1,0,33,55};
        System.out.println(Arrays.toString(moveZero(test)));
        findI(test);

    }
}
