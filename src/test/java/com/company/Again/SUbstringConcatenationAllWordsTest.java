package com.company.Again;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SUbstringConcatenationAllWordsTest {
    @Test
    public void testConcatenate() {
        SUbstringConcatenationAllWords myUnit = new SUbstringConcatenationAllWords();
        String[] list = {"foo","bar"};
        List<Integer> res = myUnit.findSubstring("barfoothefoobarman", list);
        System.out.println(res);
    }

    @Test
    public void testConcatenateII() {
        MinimumWindowSubstring myUnit = new MinimumWindowSubstring();
        String res= myUnit.findMinimumWindow("ADOBECODEBANC", "ABC");
        System.out.println(res);
    }
}