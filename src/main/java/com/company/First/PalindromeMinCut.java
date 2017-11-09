package com.company.First;

public class PalindromeMinCut {



    public static Integer minCutPalin(String str){
        if(str == null || str.length()<1){
            return 0;
        }

        char[] array = str.toCharArray();
        boolean[][] palinBoard = new boolean[array.length][array.length];
        //using dp to store the dp result, dp[i] represents the min cut from index 0 to index i
        int[] dp = new int[array.length];

        fillBoard(array, palinBoard);
        System.out.println(palinBoard[1][5]);

        //do dp
        dp[0] = 0;
        for(int i = 1; i<array.length; i++){
            dp[i] = i;
            for(int j = 0; j<i;j++){

                if(palinBoard[0][i]){
                    dp[i] = 0;
                    continue;
                }else if(palinBoard[j+1][i]){
                    dp[i] = Math.min(dp[i], dp[j]+1);
                }
            }
        }
        return dp[array.length-1];


    }

    private static void fillBoard(char[] array, boolean[][] palinBoard) {

        for(int i = 0;i<array.length;i++){
            for(int j = i; j<array.length;j++){
                if(isPalindrome(array, i,j)){
                    palinBoard[i][j] = true;
                }else{
                    palinBoard[i][j] = false;
                }
            }
        }
    }

    private static boolean isPalindrome(char[] array, int left, int right){
        while(left<right){
            if(array[left]!=array[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args){
        int result = 0;
        result = minCutPalin("abaffarerafer");
        System.out.println(result);
    }

}
