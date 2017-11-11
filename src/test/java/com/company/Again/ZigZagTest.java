package com.company.Again;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ZigZagTest {
    @Test
    public void testConcatenate() {
        ZigZag myUnit = new ZigZag();
        String res = myUnit.convert("PAYPALISHIRING", 3);
        System.out.println(res);

        System.out.println(new String("2e10").indexOf('4'));

    }

}