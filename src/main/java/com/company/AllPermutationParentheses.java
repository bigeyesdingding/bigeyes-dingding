package com.company;

import java.util.ArrayList;
import java.util.List;

public class AllPermutationParentheses {

    public List<String> validParentheses(int k){
        List<String> result = new ArrayList<String>();
        //the final string contains 2k characters
        char[] cur = new char[2*k];
        helper(cur,k, k, 0,result);
        return result;
    }

    public void helper(char[] cur, int left, int right, int level, List<String> result){
        //terminate condition:
        //wehn we do not have any parentheses left
        if(left == 0 && right == 0){
            result.add(new String(cur));
            return;
        }


        //when we can add '('? when there is some '('left
        if(left >0){
            cur[level] = '(';
            helper(cur, left-1,right,level+1, result);

        }

        //when we can add a ')' ? when there is some more '('than ')' used
        //because each ')' should be associated with a previous '('.
        if(left>right){
            cur[level] = ')';
            helper(cur, left, right-1, level+1,result);
        }
    }
}
