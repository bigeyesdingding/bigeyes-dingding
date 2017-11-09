package com.company.First;

import java.util.HashSet;
import java.util.Set;

public class StringOperations {

    /*
    * 1.removal
    *   remove some chars
    * 2. de-duplication
    * 3.replace empty space with "afdafsd"
    * 4.reversal(swap)
    * 5.substring
    *   5.1 regular method
    *   5.2 robin-carp(hash based string matching) & KMP
    *
    * */
    public String removeI(String str, String t){
        /*

        *assumption: str is not empty
        * i, j two pointers
        * i --slow pointer, all letters to the left hand side(not include i) are all processed letters that should not remove
        * j --fast pointer, all letters to the right hand side*(not include j) are all we dont care
        * unknow area we want to explore
        * */
        char[] array = str.toCharArray();
        //get set of alll distinct characters int so that lookup  operation will be efficient
        Set<Character> set = buildSet(t);
        int slow = 0;

        for(int fast = 0; fast < array.length; fast++){
            if(!set.contains(array[fast])){
                array[slow++] = array[fast];
            }

        }
        return new String(array, 0,slow);
    }
    public Set<Character> buildSet(String str){
        Set<Character> result = new HashSet<Character>();
        for(int i =0; i < str.length();i++){
            result.add(str.charAt(i));
        }
        return result;
    }
    public String removeII(String str){
        if(str==null || str.isEmpty()){
            return str;
        }
        char[] array = str.toCharArray();
        int slow = 0;
        int fast = 0;
        int wordCount = 0;

        while(true){
            while(fast<array.length && array[fast]==' '){
                fast++;
            }

            if(fast==array.length){
                break;
            }

            if(wordCount>0){
                array[slow++]=' ';
            }
            while(fast<array.length && array[fast]!=' '){
                array[slow++]= array[fast];
            }
            wordCount++;
        }
        return new String(array, 0,slow);
    }

    //remove adjenct and duplicate characters from an input string, only one left in it
    //eg: {a b b b _ c d b b b b a a b } -->  {ab_cd}
    //Assumption: input string is not null
    public String removeDuplicate(String input){
        //all letters on the left hand-side of slow pointer (not including the slow pointer) are all non-repeated ones we need
        if(input == null){
            return null;
        }
        char[] array = input.toCharArray();
        int slow = 0;
        for(int fast = 0; fast < array.length;fast++ ){
            //repeated characters will be ignored except for the first chracter in the sequence.
            if(fast==0|| array[fast]!=array[slow-1]){
                array[slow++]= array[fast];
            }

        }
        return new String(array, 0,slow);
    }

    //remove all duplicat adjacent letters repeatedly
    //abbbbbaz --> z
    public String removeDuplicateII(String input){
        if(input == null || input.length()<=1){
            return input;
        }
        char[] array = input.toCharArray();
        //instead of using an extra stack explicitly, we can actually resuse the left side of the original char[] as the stack.
        //slow is where the top of the stack is

        int slow = 0;
        for(int fast = 1; fast<array.length; fast++){
            if(slow == -1 || array[slow]!=array[fast]){
                array[++slow] = array[fast];
            }else{
                slow--;
                while(fast+1<array.length && array[fast]==array[fast+1]){
                    fast++;
                }
            }
        }
        return new String(array, 0,slow+1);

    }

    //determine whether a string is a substring of another string
    //s1="abcde"  s2="cd"
    //return 2; return -1 if s2 is not in s1
    public int substring(String s1, String s2){
        //assumption: s1 s2 are all not null
        if(s1.length()<s2.length()){
            return -1;
        }
        for(int i =0; i<s1.length()-s2.length();i++){
            if(equals(s1, i, s2)){
                return i;
            }
        }
        return -1;
    }

    private boolean equals(String s1, int i, String s2) {
        String temp =s1.substring(i,i+s2.length());
        return temp.equals(s2);
    }

    //Rabin-karp


}
