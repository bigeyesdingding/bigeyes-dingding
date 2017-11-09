package com.company.First;

public class ReverseStringII {
    public static boolean isReplacable(String s1, String s2){
        if(s1 == null || s2 == null){
            return false;
        }
        int i = 0;
        int j = 0;

        while(i<s1.length()){
            //case one: number
            if(s1.charAt(i)-'0'< 0 || s1.charAt(i)-'9'>0){
                if(s1.charAt(i)!=s2.charAt(j)){
                    return false;
                } else {
                    i++;
                    j++;
                }

            } else { //case two: character
                int number = 0;
                while(i < s1.length() && (s1.charAt(i)-'0'>= 0 && s1.charAt(i)-'9'<=0)){
                    number = number*10+s1.charAt(i)-'0';
                    i++;
                }
                j = j +number;
            }
            if(i<s1.length() && j>=s2.length()){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        System.out.println("Reverse string II");
        System.out.println(isReplacable("a1f3","abffff"));
    }
}
