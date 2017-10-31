package com.company;

public class TwoStringEditting {

    public static String longestCommonSubstring(String s1, String s2){
        if(s1 == null || s2 == null ){
            return null;
        }
        int max = 0;
        String result = null;

        int[][] commonSub = new int[s1.length()+1][s2.length()+1];
        //commonSub[i][j] represents from row index 0 to index i(i included) and column index 0 to j( j included),
        //the longest common substring length, with i j included
        for(int i = 0; i<=s1.length(); i++){

            for(int  j = 0; j<=s2.length();j++){
                //to make it easier to grown up the size of i and j, using dummy 0 row index and 0 column index
                //all the common substring at 0 index should be 0
                if(i == 0 || j == 0){
                    commonSub[i][j] = 0;
                }else {
                    if(s1.charAt(i-1) == s2.charAt(j-1)){
                        commonSub[i][j] = commonSub[i-1][j-1]+1;
                    } else {
                        commonSub[i][j] = 0;
                    }
                }

                if(commonSub[i][j] > max){
                    max = commonSub[i][j];
                    result = new String(s1.substring(i-max, i));
                    //System.out.println(max);
                }
            }
        }
        //System.out.println(result);
        return result;
    }
    public static int longestCommonSubseq(String s1, String s2){
        if(s1 == null || s2 == null ){
            return 0;
        }

        //commonSubSeq[i][j] represents the longest common subsequence of s1 from 0 to index i-1, and s2 from 0 to index j-1
        int[][] commonSubSeq = new int[s1.length()+1][s2.length()+1];
        int max = 0;

        for(int i = 0; i<=s1.length();i++){
            for(int j = 0; j<=s2.length();j++){
                if(i == 0 || j ==0){
                    commonSubSeq[i][j] = 0;
                } else {
                    if(s1.charAt(i-1) == s2.charAt(j-1)){
                        commonSubSeq[i][j] = Math.max(commonSubSeq[i][j],commonSubSeq[i-1][j-1]+1);
                    } else{
                        commonSubSeq[i][j] = Math.max(commonSubSeq[i][j],commonSubSeq[i-1][j-1]);
                    }
                    commonSubSeq[i][j] = Math.max(commonSubSeq[i][j],Math.max(commonSubSeq[i-1][j], commonSubSeq[i][j-1]));
                }
                max = Math.max(max,commonSubSeq[i][j]);
            }

        }
        System.out.println(max);
        return max;
    }
    public static int leastEdit(String s1, String s2){
        if(s1 == null || s2 == null){
            return -1;
        }

        //leastEdit[i][j] represents the least edit number of s1 from index to index i-1, and s2 from 0 to index j-1
        int[][] leastEdit = new int[s1.length()+1][s2.length()+1];
        for(int i = 0; i <= s1.length(); i++){
            for(int j = 0 ; j<=s2.length(); j++){
                if(i == 0 || j ==0){
                    leastEdit[i][j] = Math.max(i, j);
                }else {

                    leastEdit[i][j] = Math.min(leastEdit[i-1][j],leastEdit[i][j-1])+1;
                    if(s1.charAt(i-1) == s2.charAt(j-1)){
                        leastEdit[i][j] = Math.min(leastEdit[i][j], leastEdit[i-1][j-1]);
                    } else {
                        leastEdit[i][j] = Math.min(leastEdit[i][j], leastEdit[i-1][j-1]+1);
                    }
                }
            }

        }
        System.out.println(leastEdit[s1.length()][s2.length()]);
        return leastEdit[s1.length()][s2.length()];
    }

    public static void main(String[] args){
        //longestCommonSubstring("student", "swedendsfgsdfgt");
        //longestCommonSubseq("student", "");
        leastEdit("student", "");
    }
}
