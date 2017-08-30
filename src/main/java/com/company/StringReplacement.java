package com.company;

public class StringReplacement {

    //M1: using char[] to do it in-place
    public String replaceI(String input, String s, String t){
        //assumptions: input, s, t are not null,s is not empty
        char[] array = input.toCharArray();
        if(s.length()>=t.length()){
            return replaceShorter(array, s, t);
        }else{
            return replaceLonger(array, s, t);
        }
    }

    public String replaceShorter(char[] input, String s, String t){
        //in this case, we can reuse this input char array since the number of characters needed is less
        //fast and slow pointers both from left to right direction
        int slow = 0;
        int fast = 0;
        while(fast<input.length){
            //when we find a match of s on the substring starting from the fast pointer, we copy the t at slow pointer
            if(fast<input.length-s.length() && equalSubstring(input, fast, s)){
                copySubstring(input, slow, t);
                slow+= t.length();
                fast+= s.length();
            }else{
                input[slow++]=input[fast++];
            }
        }
        return new String(input, 0, slow);

    }

    private boolean equalSubstring(char[] input, int fast, String s) {
        return false;
    }
    private void copySubstring(char[] input, int fast, String s) {
    }

    public String replaceLonger(char[] input, String s, String t){
        return s;
    }

    //M2: using StingBuilder


}
