package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        return minCut[len-1];


    }
    private static boolean[][] fillBoard(String str){
        int len = str.length();
        boolean[][] isPalindrome = new boolean[len][len];
        for(int i = 0; i< len; i++){
            for(int j = 0; j<len;j++ ){
                if(i == 0){
                    isPalindrome[j][j+i] = true;
                }else if(i+j<len){
                    isPalindrome[j][j+i] = (i==1)? str.charAt(j) == str.charAt(j+i)
                            : isPalindrome[j+1][j+i-1] && str.charAt(j) == str.charAt(j+i);
                }
            }
        }
        return isPalindrome;
    }

    //Longest Palindromic Substring
    //Given a string S, find the longest palindromic substring in S.


    //Valid Palindrome(int or string)



    //palindrome partition I
    //given a string find all the valid palindrome patitions
    public static List<List<String>> allPalindromePartition(String s){

        List<List<String>> res = new ArrayList<>();
        if(s == null || s.length()<1){
            return res;
        }

        List<String> cur = new ArrayList<>();
        boolean[][] board = fillBoard(s);
        for(boolean[] e : board){
            System.out.println(Arrays.toString(e));
        }


        helper(s, 0, 0, cur, res, board);
        return res;
    }
    private static void helper(String s, int cur, int start, List<String> onHand, List<List<String>> res, boolean[][] board){
        if(cur == s.length()-1){
            if(board[start][cur]){
                onHand.add(s.substring(start, cur+1));
                res.add(new ArrayList<>(onHand));
                onHand.remove(onHand.size()-1);
            }
            return;
        }

        if(board[start][cur]){
            helper(s, cur+1, start, onHand, res, board);
            //cut here
            onHand.add(s.substring(start, cur+1));
            helper(s, cur+1, cur+1, onHand, res, board);
            //not cut
            onHand.remove(onHand.size()-1);

        }else{
            helper(s, cur+1, start, onHand, res, board);
        }
    }

    public static void main(String[] args){
        System.out.println(allPalindromePartition("abbbc"));
    }
}
