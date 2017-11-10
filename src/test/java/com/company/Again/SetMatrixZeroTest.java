package com.company.Again;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SetMatrixZeroTest {

    @Test
    public void testConcatenate() {
        SetMatrixZero myUnit = new SetMatrixZero();
        int[][] p = {{1,2,3,4},{2,3,0,5},{1,2,3,4}};
        myUnit.solution(p);
        for(int[] ele:  p){
            System.out.println(Arrays.toString(ele));
        }

        int[][] p1 = {{1,0,3,4},{2,3,0,5},{1,2,3,4}};
        myUnit.solution(p1);
        for(int[] ele:  p1){
            System.out.println(Arrays.toString(ele));
        }


        System.out.println("break");

        int[][] p2 = {{0},{1}};
        myUnit.solution(p1);
        for(int[] ele:  p1){
            System.out.println(Arrays.toString(ele));
        }
    }
}