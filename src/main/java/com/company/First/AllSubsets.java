package com.company.First;

import java.util.ArrayList;
import java.util.List;

public class AllSubsets {

    /*assumptions:
    * There are no duplicate characters in the array
    * */

    public List<String> subset(String set){
        List<String> result = new ArrayList<String>();
        if(set == null){
            return result;
        }
        char[] arraySet = set.toCharArray();
        //record the current subset
        StringBuilder sb = new StringBuilder();
        helper(arraySet, sb, 0,result);
        return result;
    }
    private void helper(char[] arraySet, StringBuilder sb, int level, List<String> result){
        if(level == arraySet.length){
            result.add(sb.toString());
            return;
        }
        //not pick the charracter
        helper(arraySet, sb, level+1,result);
        //pick the character

        helper(arraySet, sb.append(arraySet[level]), level+1,result);
        //remember to remove tyhe added character when back tracking to the previous level
        sb.deleteCharAt(sb.length()-1);


    }

}
