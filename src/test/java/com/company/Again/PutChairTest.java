package com.company.Again;

import org.junit.Test;

import java.util.Arrays;

public class PutChairTest {
    @Test
    public void testConcatenate() {
        PutChair myUnit = new PutChair();
        int[][] p = {{1,2,0,0},{2,0,0,1},{1,0,2,0}};
        int[] res = myUnit.putChair(p);
        System.out.println(Arrays.toString(res));

    }
}