package com.company.First;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermutationWithDuplicates {
    public static List<String> permutation(String set){
        List<String> result = new ArrayList<>();
        char[] array = set.toCharArray();
        if(set == null){
            return result;
        }
        helper(result,array,0);
        return result;
    }

    private static void helper(List<String> result, char[] array, int cur){
        if(cur == array.length){//base case
            result.add(new String(array));
            return;
        }
        //use a set to record all the distinct elements we have added
        Set<Character> used = new HashSet<Character>();
        for (int i = cur;i<array.length;i++){
            if(used.add(array[i])){
                swap(array,i,cur);
                //go to the next level, index + 1
                helper(result, array, cur+1);
                //don't forget to do the clear operation when backtracking
                swap(array, i, cur);
            }
        }

    }

    private static void swap(char[] array, int left, int right){
        char tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }

    public static void main(String[] args){
        System.out.println("Do permutation with duplicates in the set");
        System.out.println(permutation("aabc"));
    }
}
