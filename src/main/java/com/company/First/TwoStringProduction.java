package com.company.First;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class TwoStringProduction {

    /*find out the largest production of two strings' lengths from  a dictionary,
the two strings has to have no common characters */
    public static int largestProduction(String[] set){

        //assume: all the characters in the string of the set contains only 'a' to 'z'
        if(set == null){
            return -1;
        }

        //0. sort the set according to the length of the string
        Arrays.sort(set, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2){
                if(s1.length()== s2.length()){
                    return 0;
                }
                return s1.length()>s2.length()? -1:1;
            }
        });


        //1. get bit code of each string
        Map<String, Integer> bitCode = getBitCode(set);

        //2. for for loop all the string pairs
        int production = Integer.MIN_VALUE;
        for(int i = 0; i<set.length; i++){
            for(int j = 0; j<i; j++){
                int curProd = set[i].length() * set[j].length();
                if(curProd <production ){
                    continue;
                }

                //if the bit and of the two string is 0,
                //it means there are no two characters apearing both in string1 and string2, return this production
                if((bitCode.get(set[i]) & bitCode.get(set[j])) == 0){
                    System.out.println(set[i]);
                    System.out.println(set[j]);
                    return curProd;
                } else {
                    continue;
                }

            }

        }
        return production;
    }
    private static Map<String, Integer> getBitCode(String[] set){
        Map<String, Integer> result = new HashMap<>();
        if(set == null){
            return result;
        }
        for(int i =0; i<set.length;i++){
            int bitHelper = 0;
            //using the last 26 bits to store the info of the 26 characters from 'a' to 'z'
            for(int j = 0; j<set[i].length(); j++){
                int setter = set[i].charAt(j) - 'a';
                bitHelper |= 1<<setter;

            }
            result.put(set[i], bitHelper);
        }
        return result;
    }

    public static void main(String[] args){
        String[] set = {"sdfagers", "afdvsdf", "bob", "jtikyiuols", "piprtr"};
        System.out.println(largestProduction(set));
    }

}
