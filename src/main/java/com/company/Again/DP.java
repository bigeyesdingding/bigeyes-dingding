package com.company.Again;


import java.lang.String;

class RegexI {
    public boolean isMatch(String str, String rex) {
        if(rex == null || str == null) {
            return false;
        }
        int len1 = rex.length();
        int len2 = str.length();

        //isMatch[i][j] means rex from 0 index to index i-1, str from 0 index to j-1 index,
        //(i-1 , j-1 included )
        //is a match or not
        boolean[][] isMatch = new boolean[len1+1][len2+1];
        for(int i = 0; i<=len1; i++){
            for(int j = 0; j <= len2; j++){
                if(i == 0 && j == 0){
                    isMatch[i][j] = true;
                }else if(j == 0 && i>1 && rex.charAt(i-1) == '*'){
                    isMatch[i][j] = isMatch[i-2][j];
                }else if (i>0 && j>0){
                    if(rex.charAt(i-1) == str.charAt(j-1) || rex.charAt(i-1) == '.'){
                        isMatch[i][j] = isMatch[i-1][j-1];
                    }else if(rex.charAt(i-1) == '*'){
                        isMatch[i][j] = rex.charAt(i-2) == str.charAt(j-1) || rex.charAt(i-2)=='.'?
                                isMatch[i][j-1] || isMatch[i-2][j]: isMatch[i-2][j];
                    }
                }
            }
        }
        return isMatch[len1][len2];
    }
    public boolean isMatchII(String s, String p){
        if(s == null || p == null){
            return false;
        }
        int m = s.length(), n = p.length();
        char[] ws = s.toCharArray();
        char[] wp = p.toCharArray();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++)
            dp[0][j] = dp[0][j-1] && wp[j-1] == '*';
        for (int i = 1; i <= m; i++)
            dp[i][0] = false;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (wp[j-1] == '?' || ws[i-1] == wp[j-1])
                    dp[i][j] = dp[i-1][j-1];
                else if (wp[j-1] == '*')
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
            }
        }
        return dp[m][n];
    }
}
