package com.company.Again;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

class ZigZag {
    public String convert(String str, int n) {
        if(n == 1){
            return str;
        }
        StringBuilder[] res = new StringBuilder[n];
        for(int j = 0; j<n; j++){
            res[j] = new StringBuilder();
        }
        int len = str.length();
        boolean down = true;
        int row = 0;
        int i = 0;
        while(i<len){
            if(down){
                while(row<n-1 && i<len){
                    res[row].append(str.charAt(i));
                    i++;
                    row++;
                }
                down = false;
            }else{
                while(row>0 && i<len){
                    res[row].append(str.charAt(i));
                    i++;
                    row--;
                }
                down = true;
            }
        }
      String result = "";
        for(i = 0; i<n; i++){
            result = result+res[i].toString();
        }
        return result;
    }
}

class MyATOI {
    public int myAtoi(String str) {

        if(str == null || str.length()<1){
            return 0;
        }

        int res = 0;

        //check sign
        int j = 0;
        while(j<str.length() && str.charAt(j) == ' '){
            j++;
        }
        boolean sign = true;
        if(str.charAt(j) == '-'){
            sign = false;
            j++;
        }else if(str.charAt(j) == '+'){
            j++;
        }

        for(int i = j; i<str.length(); i++){
            if(!isValid(str.charAt(i))){
                break;
            }
            if(sign && res+ (str.charAt(i)-'0')/10.0>Integer.MAX_VALUE/10.0){
                return Integer.MAX_VALUE;
            }
            if(!sign && res+Integer.MIN_VALUE/10.0 + (str.charAt(i)-'0')/10.0>0){
                return Integer.MIN_VALUE;
            }
            res = res*10 + (str.charAt(i)-'0');
        }


        return sign? res : -res;
    }

    private boolean isValid(char c){
        if(c-'0'>=0 && c-'0'<=9){
            return true;
        }
        return false;
    }
}

class IsValidNumber{
    public boolean isNumber(String s) {
        s = s.trim();

        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        boolean numberAfterE = true;
        for(int i=0; i<s.length(); i++) {
            if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
                numberAfterE = true;
            } else if(s.charAt(i) == '.') {
                if(eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;
            } else if(s.charAt(i) == 'e') {
                if(eSeen || !numberSeen) {
                    return false;
                }
                numberAfterE = false;
                eSeen = true;
            } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
                if(i != 0 && s.charAt(i-1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }

        return numberSeen && numberAfterE;
    }
}