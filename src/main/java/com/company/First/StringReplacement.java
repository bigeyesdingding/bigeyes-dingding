package com.company.First;

import java.util.ArrayList;

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



    public String replaceLonger(char[] input, String s, String t){
        /*
        * step1: iterate over the string and count how many s inside the input,
        * by doing so, we can find how many extra spaces we need to reserve on the input
        *   slow: move from the right to the left, all the letters on the right are the result to return
        *   fast: current index move from the right to the left
        * step2: iterate from right to left over the input
        *
        * */
        ArrayList<Integer> matches = getAllMatches(input, s);

        //calculate the extra spaces
        char[] result = new char[input.length + matches.size()*(t.length()-s.length())];

        //fast and slow pointers
        int slow = input.length-1; //old string
        int fast = result.length-1; //result string

        //lastIndex the rightmost matching and position's index
        int lastIndex = matches.size()-1;
        while(slow >= 0){
            //only if we still have some match and slow is in the position of right most
            if(lastIndex>=0 && slow == matches.get(lastIndex)) {
                copySubstring(result, fast - t.length() + 1, t);
                fast -= t.length();
                slow -= s.length();
                lastIndex--;
            } else {
                result[fast--] = input[slow--];

            }

        }
        return new String(result);


    }

    private static ArrayList<Integer> getAllMatches(char[] input, String s){

        //assume that at least one of string s in the input
        ArrayList<Integer> matches = new ArrayList<>();
        int i =0;
        while(i<=input.length-s.length()){
            if(equalSubstring(input, i, s)){
                //we record the match substring's end index
                matches.add(i+s.length()-1);
                i += s.length();
            } else {
                i++;
            }

        }
        return matches;

    }

    private static boolean equalSubstring(char[] input, int fromIndex, String s){
        for (int i = 0; i<s.length();i++ ){
            if(input[fromIndex+i] != s.charAt(i)){
                return false;
            }
        }
        return true;
    }

    private static void copySubstring(char[] result, int fromIndex, String t){
        for(int i =0; i<t.length(); i++){
            result[fromIndex+i] = t.charAt(i);

        }
    }

    //M2: using StingBuilder
    //in this way, it's not using string's replace
    public static String replaceII(String input, String s, String t){
        //assumption: input, s, t are not null, s is not empty
        StringBuilder sb = new StringBuilder();
        int fromIndex = 0;
        int matchIndex = input.indexOf(s, fromIndex);
        while(matchIndex != -1){
            sb.append(input,fromIndex, matchIndex).append(t);
            //next time we need to start from matchindex + s.length()
            fromIndex = matchIndex + s.length();
            matchIndex = input.indexOf(s, fromIndex);
        }
        sb.append(input, fromIndex, input.length());
        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println(replaceII(new String("Studentu"), "tu","rst"));
    }








}
