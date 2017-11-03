package com.company;

import java.util.*;

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


    //Valid Palindrome(int or string or LinkedList)



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

    //longest palindrome sub-sequence
    public static int subSequence(String s){
        int len = s.length();
        int[][] longPalin = new int[len][len];
        int max = 0;

        for(int j = 0; j<len; j++){
            for(int i = 0; i <len; i++){
                if(i+j<len){
                    if(j == 0){
                        longPalin[i][i+j] = 1;
                    }else if(j == 1){
                        longPalin[i][j+i] = s.charAt(i+j) == s.charAt(i)? 2:1;
                    }else{
                        longPalin[i][j+i] = s.charAt(i+j) == s.charAt(i)? longPalin[i+1][j+i-1]+2:longPalin[i+1][j+i-1];
                        longPalin[i][j+i] = Math.max(longPalin[i][j+i],Math.max(longPalin[i][j+i-1], longPalin[i+1][j+i]));
                    }
                    max = Math.max(max,longPalin[i][i+j]);
                }
            }
        }
        return max;
    }

    //valid palindrome at most one deletion of one character
    public static boolean validPalinII(String s){
        if(s == null || s.length()<2){
            return true;
        }
        int left = 0;
        int right = s.length()-1;
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                return checkValid(s, left+1, right) || checkValid(s, left, right-1);
            }
            left++;
            right--;

        }
        return true;

    }
    private static boolean checkValid(String s, int left, int right){
        while(left<right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    //longest palindrome substring
    public static int start = 0;
    public static int number = 0;
    public static String longestSubstringII(String s){
        int len = s.length();
        if(s.length()<2){
            return s;
        }
        for(int i = 0; i< s.length()-1; i++){
            checkPalin(s, i,i);//old number
            checkPalin(s, i, i+1); //even number
        }
        return s.substring(start, start+number);

    }
    private static void checkPalin(String s, int mid1, int mid2){
        while(mid1>=0 && mid2 <s.length() && s.charAt(mid1) == s.charAt(mid2)){
            mid1--;
            mid2++;
        }
        if(mid2-mid1+1>number){
            start = mid1;
            number = mid2-mid1+1;
        }
    }

    public static String longestSubstring(String s){
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int start = 0;
        int end = 0;
        for(int i = 0; i<len;i++){
            int j = 0;
            while(i+j<len){
                if(i == 0){
                    dp[j][j+i] = true;
                }else{
                    dp[j][j+i] = i == 1? s.charAt(j) == s.charAt(i+j):s.charAt(j)== s.charAt(i+j) && dp[j+1][i+j-1];
                }
                if(dp[j][j+i] && i>end-start){
                    start = j;
                    end = j+i;
                }
                j++;
            }
        }
        return s.substring(start, end+1);
    }
    //all palindrome substrings


    private int count;
    public int solution(String s){
        if(s.length()<2){
            return s.length();
        }
        int len = s.length();
        for(int i = 0; i<len; i++){
            expand(s, i, i+1); //odd
            expand(s, i, i); //even//
        }
        return count;
    }
    private  void expand(String s, int l, int r){
        while(l>=0 && r<s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
            count++;
        }
    }


    //shortest palindrome
    public static String shortest(String s){
        if(s.length()<2){
            return s;
        }
        int len = s.length();
        int start = 0;
        int end = 0;
        for(int i = len-1; i>=0;i--){
            if(checkPalinI(s, 0, i)){
                end = i;
                break;
            }
        }

        String add = new StringBuilder(s.substring(end+1, len)).reverse().toString();
        return add+s;
    }
    public static boolean checkPalinI( String s, int left, int right){
        while(left<=right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }






    //TODO: all palindrome pairs
    //TODO: closest palindrome
    //eg: 123-->121
    //TODO: Largest Palindrome Product
    //Find the largest palindrome made from the product of two n-digit numbers.
    //Since the result could be very large, you should return the largest palindrome mod 1337.




    public static void main(String[] args){
        String s= "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        System.out.println(allPalindromePartition("abbbc"));
        System.out.println(subSequence("abcdefda"));
        System.out.println(validPalinII("ab"));
        System.out.println(longestSubstring("defabada"));

        System.out.println(shortest("absdee"));

    }
}
