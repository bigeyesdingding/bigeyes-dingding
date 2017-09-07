package com.company;

import java.util.HashSet;
import java.util.Set;

public class SlidingWindow {
    public static int longestSubstringWithoutDup(String input){
        Set<Character> set = new HashSet<Character>();
        //longestSubstring represents the length of the longest substring from index 0 to index i(i included);
        int[] longestSubstring = new int[input.length()];
        longestSubstring[0] = 1;
        for(int i = 1; i<input.length();i++){
            if(!set.contains(input.charAt(i)) ){
                longestSubstring
            }
        }

    }
    public static void main(String[] args){
        System.out.println("The sets of sliding window problems");
    }
}
