package com.company.First;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Spiral2D {
    private static class Inner{
        public int value;
        public String name;
        public Inner(int val, String nam){
            value = val;
            name = nam;
        }
        public void set(int val){
            value = val;
        }
    }

    public static void spiral(int size, int level, int offset, int[][] result){
        if(size == 0){ //base case one: size ==0
            return;
        } else if(size == 1){
            result[level][level] = offset;
            return;
        } //base case two: size == 1


        for(int col = level; col<level+ size-1; col++ ){

            result[level][col] = offset++;
        }
        for(int row = level; row<level+ size-1; row++ ){

            result[row][level+size-1] = offset++;
        }
        for(int col = level+size-1; col>level; col-- ){

            result[level+size-1][col] = offset++;
        }
        for(int row = level+size-1; row>level; row-- ){

            result[row][level] = offset++;
        }

        spiral(size-2,level+1, offset, result);

    }

    public static int[][] spiralII(int n){
        int[][] result = new int[n][n];
        //using iterative method to solve it
        if(n<0){
            return result;
        }

        int num = 1;
        int start = 0;
        int end = n-1;
        while(start<end){
            for(int i = start; i<end; i++){
                result[start][i] = num++;
            }
            for(int i = start; i<end; i++){
                result[i][end] = num++;
            }
            for(int i = end; i>start; i--){
                result[end][i] = num++;
            }
            for(int i = end; i>start; i--){
                result[i][start] = num++;
            }
            start++;
            end--;
        }

        if(start == end){
            result[start][end] = num++;
        }

        for(int[] list : result){
            System.out.println(Arrays.toString(list));
        }

        return result;
    }

    public static void main(String[] args){
        System.out.println("Please print a 2D spiral...");
        List<Inner> oldList =new ArrayList<>();
        oldList.add(new Inner(3,"first"));
        oldList.add(new Inner(5,"second"));
        oldList.add(new Inner(7,"third"));
        oldList.add(new Inner(9,"forth"));
        int[][] result =new int[6][6];

        result = spiralII(6);
//        for(int[] list : result){
//            System.out.println(Arrays.toString(list));
//        }



    }
}
