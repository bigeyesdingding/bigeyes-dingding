package com.company.First;

import java.util.*;

public class Permutation {
    //given a string of non-duplicates characters, return all the permutation of the string
    public static void helper(char[] array, int index, List<String> result){
        //base case return the result
        if(index == array.length){
            result.add(new String(array));
            return;
        }
        //recursive rule
        for(int i = index; i < array.length; i++){
            swap(array, index, i);
            helper(array, index+1, result);
            swap(array, index, i);
        }
    }

    private static void swap(char[] array, int l, int r){

        char temp = array[l];
        array[l] = array[r];
        array[r] = temp;

    }

    public static List<String> permutate(String set){
        List<String> result = new ArrayList<>();
        if(set == null || set.length()<1){
            return result;
        }

        char[] array = set.toCharArray();
        helper(array, 0, result);
        return result;

    }

    public static void main(String[] args) {
        // write your code here
        System.out.println("Permutation I");
        System.out.println("Given a string of non-duplicates characters, return all the permutation of the string");
        List<String> result = new ArrayList<>();
        result = permutate(null);

        String a = new String("123456");
        String b = new String("123456");

        System.out.println(a.equals(b));
    }
}
