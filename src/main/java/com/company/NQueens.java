package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {
    /*
    * recursive way of sloving problem
    * BAse case: the last row of queen is done, 0 row left
    * Recursive rule: if position (i, j) is valid, then go to the next row(i, j)
    * Using a list of arraylist to store the result. wehn the Arraylist represents the current solution on each row,
    *   For example, A[8] = {1,3,5,7...}
    *
    *
    *
    * */
    public static List<List<Integer>> nqueens(int n){

        //result is used to store the result, it is a list of arraylist, the array list inside represents the position of the ith queen
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        //use position to record the current recursion result
        List<Integer> position = new ArrayList<>();

        //use boolean array to store the vertical column usage
        boolean[] column = new boolean[n];
        //store the usage of diagonal and reverse diagonal
        boolean[] diagonal = new boolean[2*n-1];
        boolean[] revDiagonal = new boolean[2*n-1];

        nqueensII(n, position, column, diagonal,revDiagonal,result);
        return result;

    }


    public static void nqueensI(List<Integer> position, int n, List<List<Integer>> result){
        if(n == position.size()){
            //base case
            //processing the case
            result.add(new ArrayList<Integer>(position));
//            System.out.println(position);
            return;
        }
        //cur represents the current row of the matrix

        for(int i = 0; i<n;i++){
            //check if putting a queen at i is valid
            if(isValidI(position, i)){
                position.add(i);
                nqueensI(position,n, result);
                position.remove(position.size()-1);
            }
        }
    }

    public static boolean isValidI(List<Integer> position, int index) {
        int row = position.size();
        for(int i =0; i<row;i++){
            if(position.get(i)==index || Math.abs(position.get(i)-index)==row-i){
                return false;
            }
        }
        return true;
    }

    public static void nqueensII(int n, List<Integer> position, boolean[] column, boolean[] diagonal,
                                 boolean[] revDiagonal, List<List<Integer>> result){
        if(position.size()==n){
            //base case
            result.add(new ArrayList<Integer>(position));//new a arraylist of the current position
            return;
        }

        for(int col =0; col<n;col++){
            int row = position.size();
            if(isValid(n,col,row,column,diagonal,revDiagonal)){
                //add column info
                position.add(col);

                //set the usage boolean[] to true  of columns, diagonals, revDiagonals
                column[col] = true;
                diagonal[row+col] = true;
                revDiagonal[row-col+n-1] = true;

                //recursion
                nqueensII(n, position,column, diagonal,revDiagonal,result);

                //reset the usage of boolean to false
                column[col] = false;
                diagonal[row+col] = false;
                revDiagonal[row-col+n-1] = false;

                //remove column info
                position.remove(position.size()-1);

            }
        }

    }

    private static boolean isValid(int n, int col, int row, boolean[] column, boolean[] diagonal, boolean[] revDiagonal) {
        return !column[col] && !diagonal[col+row] && !revDiagonal[row-col+n-1];
    }


    public static void main(String[] args){
        nqueens(8);
    }
}
