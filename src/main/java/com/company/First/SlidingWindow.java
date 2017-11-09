package com.company.First;

import java.util.*;

public class SlidingWindow {
    public static int longestSubstringWithoutDup(String input){

        Set<Character> set = new HashSet<>();
        int slow = 0;
        int fast = 0;
        int globalLongest = 0;

        //in the sliding window, it contains all the characters, with index slow included
        while (fast<input.length()){
            if(set.contains(input.charAt(fast))){
                set.remove(input.charAt(slow++));
            } else {
                set.add(input.charAt(fast++));
                globalLongest = Math.max(globalLongest, fast-slow);
            }
        }
        return globalLongest;


    }
    public static String longestAnagram(String s, String l){
        //assumptions:  s, l are not null, l and s is not empty, s is shorter than l
        List<Integer> result = new ArrayList<Integer>();
        Map<Character, Integer> map = countMap(s);

        int left = 0;

        int match = 0;
        int globalStart = 0;
        int globalEnd = 0;

        for(int right = 0; right < l.length();right++){
            char tmp = l.charAt(right);
            Integer count = map.get(tmp);
            if(count != null){
                map.put(tmp, count -1);
                if(count == 1){
                    match++;
                } else if( count == 0){ //move left to make it a match
                    continue;

                }
            }

        }
        return new String("123");
    }


    public static List<Integer> allAnagrams(String s, String l){

        //assumption: s, l are not null, s is not empty

        List<Integer> result = new ArrayList<>();
        if(l.length() == 0){
            return result;
        }


        //when s is longer than l, there is no way any of the substring coulc be an anagra
        if(s.length()>l.length()){
            return result;
        }

        //use a map to record the distinct characters in s and the number of each characters
        //when we get an instance of 'a' in l, we let count of 'a' decremented by 1
        //and only when the count is from 1 to 0, we have a totally matched
        Map<Character, Integer> map = countMap(s);

        //use a flag to record how many characters match the short string
        int match = 0;

        for(int i = 0; i<l.length(); i ++){
            //check whether the character is in the count map
            char tmp = l.charAt(i);
            Integer count = map.get(tmp);
            if(count != null){
                map.put(tmp, count -1);
                if(count == 1){
                    match++;
                }
            }

            //handle pull out the left characters at the previous window
            if(i>=s.length()){
                tmp = l.charAt(i-s.length());
                count = map.get(tmp);
                if(count != null){
                    map.put(tmp,count+1);
                    if(count == 0){
                        match--;
                    }
                }
            }
            //for the current sliding windwo, if all the distinct characters are matched, add to the reuslt
            if(match == map.size()){
                result.add(i-s.length()+1);
            }
        }
        return result;

    }

    private static Map<Character,Integer> countMap(String s) {
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        for(char ch: s.toCharArray()){
            Integer count = map.get(ch);
            if(count == null){
                map.put(ch,1);
            } else {
                map.put(ch, count+1);
            }
        }
        return map;
    }

    public static int longestFlip(int[] array, int k){
        //it''s actually a sliding window problem
        //means the longest subarrray that contains at most k 0s
        //assume array is not null and array.length >0

        if(k>=array.length){
            return array.length;
        }

        int start = 0;
        int zeros= 0;
        int globallongest = 0;

        for(int i = 0;i<array.length;i++){
            if(array[i]==0 && zeros < k){
                zeros++;
                globallongest = Math.max(globallongest, i-start+1);

            } else if(array[i]==0 && zeros >= k){
                while(array[start]!=0){
                    start++;
                }
                start++;

            } else {
                globallongest = Math.max(globallongest, i-start+1);

            }
        }
        return globallongest;


    }

    public static List<Integer> largestInWindow(int[] array, int k){
        if(array == null || k>array.length){
            return null;
        }
        //use a deque to store the index of elements in descending order
        Deque<Integer> deque = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for(int i = 0; i<array.length;i++){
            //if the tail of the deque is smaller than array[i], poll
            while(!deque.isEmpty() && array[deque.peekLast()] <= array[i]){
                deque.pollLast();
            }

            //if the head odf the deque is out of the current window
            if(!deque.isEmpty() && deque.peekFirst() <= i-k){
                deque.pollFirst();
            }

            //add the current array[i]
            deque.offerLast(i);

            //update max
            if(i>=k-1){
                result.add(array[deque.peekFirst()]);
            }
        }
        return result;
    }


    public static void main(String[] args){

        /*System.out.println("The sets of sliding window problems");
        System.out.println(longestSubstringWithoutDup("The sets of sliding window problems"));
        System.out.println(allAnagrams("in", "Thesetsofslidingwindowproblems" ));*/
        int[] array = {0,1,1,1,0,0,0,0,1,0,0,1,0,1,1,0,1,1,1,0,0,1};
        System.out.println(longestFlip(array,4));

    }
}
