package com.company.Again;
import java.lang.String;
import java.util.*;

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

class SUbstringConcatenationAllWords {
    public List<Integer> findSubstring(String s, String[] l){
        List<Integer> result = new ArrayList<>();
        if(s == null || l == null || l.length == 0){
            return result;
        }

        int num = l.length;
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i<num; i++){
            map.put( l[i], map.get(l[i]) == null ? 1: map.get(l[i])+1);
        }
        int len = l[0].length();

        for(int i = 0; i<=s.length()-len*num; i++){

            Map<String, Integer> copy = new HashMap<>(map);
            for(int j = 0; j<num; j++){
                Integer temp = copy.get(s.substring(i+j*len, i+j*len+len));
                if(temp != null){
                    if(temp == 1){
                        copy.remove(s.substring(i+j*len, i+j*len+len));
                    }else{
                        copy.put(s.substring(i+j*len, i+j*len+len), temp-1);
                    }
                    if(copy.isEmpty()){
                        result.add(i);
                        break;
                    }
                }else{
                    break;
                }
            }
        }
        return result;
    }
}

class MinimumWindowSubstring{
    public String findMinimumWindow(String s, String t) {
        if(s == null || s.length() == 0 || s.length()<t.length()){
            return "";
        }

        // map to store the char and number info of t
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i<t.length(); i++){
            map.put(t.charAt(i), map.containsKey(t.charAt(i))? map.get(t.charAt(i))+1:1);
        }

        int l = 0, r = 0, minStart = 0, minLen = s.length()+1;
        int counter = 0;
        while(r<s.length()){
            if(map.containsKey(s.charAt(r))){
                //update map and counter
                map.put(s.charAt(r), map.get(s.charAt(r))-1);
                if(map.get(s.charAt(r))>=0){
                    counter++;
                }


                //update global and left
                while(counter == t.length()){
                    if(r-l +1 < minLen){
                        minStart = l;
                        minLen = r-l+1;
                    }

                    //move left
                    if(map.containsKey(s.charAt(l))){
                        map.put(s.charAt(l), map.get(s.charAt(l))+1);
                        if(map.get(s.charAt(l))>0){
                            counter--;
                        }
                    }
                    l++;
                }

            }
            r++;
        }

        return minLen!=s.length()+1? s.substring(minStart, minStart+minLen): "";
    }

}

class MaxSlidingWindow{
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k <= 0){
            return new int[0];
        }
        int len = nums.length;
        int[] result = new int[len - k +1];
        int index = 0;
        Deque<Integer> deque = new LinkedList<>(); //to store the index of all max elements

        for(int i = 0; i<len; i++){
            //remove elements out of the k window
            while(!deque.isEmpty() &&  deque.peekFirst()<i-k+1){
                deque.pollFirst();

            }
            //remove elements smaller then nums[i] cuz useless
            while(!deque.isEmpty() && nums[deque.peekLast()] <nums[i]){
                deque.pollLast();
            }

            //poll out max
            deque.offerLast(i);
            if(i>=k-1){
                result[index++] = nums[deque.peekFirst()];
            }
        }
        return result;
    }
}
class LongestSubstringKDistinctChars{
    public int solution(String s, int k){
        if(s == null || s.length() == 0 || s.length()<=k){
            return s == null ? 0: s.length();
        }
        int slow = 0;
        int fast = 0;
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();

        while(fast < s.length()){
            char c = s.charAt(fast);
            if(map.containsKey(c)){
                map.put(c, map.get(c)+1);
            }else{
                if(map.size()<k){
                    map.put(c, 1);
                }else{
                    //update global maximum
                    max = Math.max(max, fast-slow);
                    //move the slow pointer until the size of map smaller than k
                    while(map.size()>=k){
                        map.put(s.charAt(slow), map.get(s.charAt(slow))-1);
                        if(map.get(s.charAt(slow)) == 0){
                            map.remove(s.charAt(slow));
                        }
                        slow++;
                    }
                    map.put(s.charAt(fast), 1);
                }
            }
            fast++;
        }

        max = Math.max(max, fast-slow);
        return max;
    }
}

public class MinWindowSubSequence{
    public String minWindowSequence(String S, String T){

        //1. How to design the induction rule? Why we require s[i] == t[j]
        //2. How to reduce the dimensions? Means how to pass the info of k
        //
        //dp[i][j] = k represents from k to i of s, from 0 to j of t, the start point of the substring
        //dp[i][j] = -1 if therre is no substring
        if(S == null || S.length() == 0 || S.length() < T.length()){
            return "";
        }

        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int sl = s.length;
        int tl = t.length;
        int[][] dp = new int[sl][tl];

        //base case, j == 0
        for(int i = 0; i<sl; i++){
            dp[i][0] = -1;
            if(s[i] == t[0]){
                dp[i][0] = i;
            }
        }

        //induction rules
        for(int j = 1; j<tl; j++){
            int k = -1;
            for(int i = 0; i<sl;i++){
                dp[i][j] = -1;
                if(k != -1 && s[i] == t[j]){
                    dp[i][j] = k;
                }
                if(dp[i][j-1] != -1){
                    k = dp[i][j-1];
                }
            }
        }

        //loop the last level and return the global
        int minLen = Integer.MAX_VALUE, start = -1;
        for(int i = 0; i<sl; i++){
            if(dp[i][tl-1]!= -1){
                if(i-dp[i][tl-1]+1<minLen){
                    minLen = i-dp[i][tl-1]+1;
                    start = dp[i][tl-1];
                }else if(i-dp[i][tl-1]+1<minLen){
                    start = Math.min(start, dp[i][tl-1]);
                }
            }
        }
        List<Integer> l = new ArrayList<>(1);
        return minLen == Integer.MAX_VALUE? "": S.substring(start, start+minLen);
    }

}


