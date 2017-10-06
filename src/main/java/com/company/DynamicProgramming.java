package com.company;

import java.util.HashSet;
import java.util.Set;

public class DynamicProgramming {
    public static int longestAscendingSub(int[] array){
        //it is actually contiguous
        //how is the return: which means you have to ask for the signatures
        //suppose: that the given array is not null
        //          no duplicates in the array
        if(array.length == 0){
            return 0;
        }
        //optimize the space by only keep the latest longest subarray

        int result = 1;
        int cur = 1;
        //cur represents the longest length of subarray, from 0 index to i index(index i included)
        for(int i = 1; i<array.length; i++){
            if(array[i]>array[i-1]){
                cur++;

            }else {
                cur = 0;
            }
            result = Math.max(result, cur);
        }
        return result;

        //time: o(n); space: o(1)
    }

    //Cutting a given rope and make the product of each part maximum
    //Two Methods: left big part and right small part  VS left big part and right small part
    public static int cutRopeI(int n){
        //assumptionL at least one cut has to be made
        if(n<=1){
            return n;
        }

        //maxPro[i]: reprents the max product of a rope of i
        int[] maxPro=new int[n+1];
        maxPro[1] = 1;
        for(int i = 2; i<=n;i++){
            maxPro[i] = 1;
            for(int j = 1;j<=i/2;j++){
                maxPro[i] = Math.max(maxPro[i],Math.max(maxPro[j],j)*Math.max(maxPro[i-j],i-j));
            }
        }
        return maxPro[n];

    }

    //Dictionary: it's different, cause we cann't using the dp result before of the right part
    //using manual check of the right part, rather than reading the dictionary
    public static boolean isBreakable(String input, String[] dict){
        //assumption: input is not null or empty
        //dict is not null and dict is not empty
        //all the strings in the dict are not null or empty
        Set<String> dictSet = toSet(dict);
        //using canBreak[i] represents: from the input string index 0 to index i-1,whether it is breakable
        boolean[] canBreak = new boolean[input.length()+1];
        canBreak[0] = true;
        for(int i = 1;i<input.length();i++ ){
            for(int j = 0; j<i; j++){
                if(canBreak[j] && dictSet.contains(input.substring(j,i))){
                    canBreak[i] = true;
                    break;
                }
            }
        }
        return canBreak[input.length()];

    }

    private static Set<String> toSet(String[] dict) {
        Set<String> set = new HashSet<>();
        for(String s: dict){
            set.add(s);
        }
        return set;
    }

    public static boolean canJumpI(int[] array){
        //assume: array is not null and is not empty
        boolean[] canJump = new boolean[array.length];
        //canJump[i] means from index 0, can jump to index i
        canJump[0] = true;
        for(int i = 1; i<array.length; i++){
            for(int j = 0; j<i; j++){
                if(canJump[j] && array[j] + j >=j){
                    canJump[i] = true;
                    break;
                }
            }
        }
        return canJump[array.length-1];
        //time o(n^2)
    }
    public static boolean canJumpII(int[] array){
        if(array.length ==1){
            return true;
        }
        boolean[] canJump = new boolean[array.length];
        //canJump[i] represnts that from index i whether can jump to the target, which is the last element of the  array
        canJump[array.length-1] = true;
        for(int i = array.length-1; i>=0; i--){
            if(array[i]+i>=array.length-1){
                canJump[i] = true;
            }else {
                for(int j = array[i]; j>=1; j--){
                    if(canJump[i+j]){
                        canJump[i] = true;
                        break;
                    }
                }
            }
        }
        return canJump[0];

    }

    public static int minJump(int[] array){
        //assumption: string not sorted
        int length = array.length;
        int[] minJump = new int[length];//minJump[i] represents min jump from index 0 to index i;

        minJump[0] = 0;
        for(int i = 1; i<length; i++){
            minJump[i] = -1;
            for(int j = 0; j<i; j++){
                if(minJump[j]+j>= i && minJump[j]!=-1){
                    //TODO: it's very easily to get into the wrong bug, be careful
                    if(minJump[i] == -1|| minJump[i]> minJump[j]+1){
                        minJump[i] =  minJump[j]+1;
                    }
                }
            }

        }
        return minJump[length-1];
    }

    public static int editDistance( String one, String two){
        //asssume:one and two are not null
        //using distance[i][j] to represent substring(0,i), substring(0,j)
        int[][] distance = new int[one.length()+1][two.length()+1];
        for(int i = 0; i<one.length();i++ ){
            for(int j = 0; j<two.length(); j++){
                if(i == 0){
                    distance[i][j] = j;
                }else if(j == 0){
                    distance[i][j] = i;
                } else if(one.charAt(i)== two.charAt(j)){
                    distance[i][j] = distance[i-1][j-1];

                } else {
                    distance[i][j] = Math.min(distance[i][j-1], distance[i-1][j]);
                    distance[i][j] = Math.min(distance[i-1][j-1], distance[i][j]);
                }
            }
        }
        return distance[one.length()][two.length()];

    }

    public static int largestSquareOfOnes(int[][] matrix){
        //assumption: matrix is a binary matrix, only 0 and 1 in it
        //matrix is not null
        int n = matrix.length;
        if( n == 0){
            return 0;
        }

        int result = 0; //store the final result

        int[][] largest = new int[n][n]; //largets[i][j] rep[resents the largets matrix of all ones with matrix[i][j] as the bottom right one
        for(int i = 0; i<n;i++){
            for(int j = 0; j<n;j++){
                if(i == 0 || j == 0){
                    largest[i][j] = matrix[i][j] == 1? 1:0;
                } else if(matrix[i][j]== 1){
                    largest[i][j] = Math.min(largest[i][j-1]+1, largest[i-1][j]+1);
                    largest[i][j] = Math.min(largest[i][j], largest[i-1][j-1]+1);
                }
                result = Math.max(result, largest[i][j]);
            }
        }
        return result;
    }

}
