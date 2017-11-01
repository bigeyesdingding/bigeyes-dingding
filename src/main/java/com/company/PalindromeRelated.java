package com.company;

public class PalindromeRelated {
    /*
    * 1. Min Cut of a giving string
    * 2. is palindrome
    *
    * */

    public int minCut(String str){
        if(str == null || str.length()<=1){
            return 0;
        }

        //using boolean[i][j] to store from index i to index j , is palindrome or not
        int len = str.length();
        boolean[][] isPalindrome = fillBoard(str);
        int[] minCut = new int[len];
        minCut[0] = 0;
        for(int i = 1; i<len; i++){
            minCut[i] = i;
            for(int j = 0; j <= i; j++){
                if(isPalindrome[j][i]){
                    if(j == 0){
                        minCut[i] = 0;
                    }else {
                        minCut[i] = Math.min(minCut[i], minCut[j-1]+1);
                    }
                }
            }
        }


    }
    private boolean[][] fillBoard(String str){
        int len = str.length();
        boolean[][] isPalindrome = new boolean[len][len];
        for(int i = 0; i< len; i++){
            for(int j = i; j<len;j++ ){
                if(i == j){
                    isPalindrome[i][j] = true;
                }else if(j == i + 1){
                    isPalindrome[i][j] = str.charAt(i) == str.charAt(j);
                }else{
                    isPalindrome[i][j] = isPalindrome[i-1][j-1] && str.charAt(i) == str.charAt(j);
                }
            }
        }
        return isPalindrome;
    }

    //Longest Palindromic Substring
    //Given a string S, find the longest palindromic substring in S.


    //Valid Palindrome


}
