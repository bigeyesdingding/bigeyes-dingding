package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllPermutation {
/*
* must maintain the order of all the ppermutations
* */
    public List<String> permutation(String set){
        List<String> result = new ArrayList<String>();
        if( set == null ){
            return null;
        }
        char[] arraySet = set.toCharArray();
        Arrays.sort(arraySet);
        //record which index has been used in the original arraySet
        boolean[] used = new boolean[arraySet.length];
        StringBuilder cur = new StringBuilder();
        helperWithOrder(arraySet, used, cur, result);
        return result;

    }
    private void helperWithOrder(char[] array, boolean[] used, StringBuilder cur, List<String> result){
        //terminate condition
        //when the permutation contains all the characters in the original array
        if(cur.length()== array.length){
            result.add(cur.toString());
            return;
        }
        //when pickiung the next char, always according to the order
        for(int i =0; i<array.length; i++){
            //if a character is already used, we can not pick it
            if(!used[i]){
                used[i]=true;
                cur.append(array[i]);
                helperWithOrder(array, used, cur,result);
                used[i] = false;
                cur.deleteCharAt(cur.length()-1);
            }
        }

    }

}
