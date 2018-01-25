package com.company.First.ershua;

import java.util.*;

public class StringI {
    //remove adjunct strings
    static public String deDup(String input) {
        // Write your solution here.
        if(input== null || input.length() == 0){
            return input;
        }
        char[] array = input.toCharArray();

        int fast = 0, slow = 0;
        while(fast<array.length){
            while(fast != array.length-1 && array[fast] == array[fast+1]){
                fast++;
            }
            array[slow++] = array[fast++];
        }
        return new String(array, 0, slow);

    }

    public String deDupIV(String input) {
        // Write your solution here
        if(input== null || input.length() == 0){
            return input;
        }
        char[] array = input.toCharArray();
        //Deque<Character> stack = new LinkedList<>();
        int i = 0;
        int end = 0;
        while(i<array.length){
            if(end == 0 || array[end] != array[i]){
                array[end++] = array[i++];
            }else{
                char prev = array[end--];
                while(i<array.length && prev == array[i]){
                    i++;
                }

            }


//            if(stack.isEmpty() || stack.peekLast() != array[i]){
//                stack.offerLast(array[i]);
//                i++;
//            }else{
//                char prev = stack.pollLast();
//                while( i<array.length && array[i] == prev){
//                    i++;
//                }
//            }
        }
//        String res = "";
//        while(!stack.isEmpty()){
//            res = stack.pollLast() + res;
//        }

        return new String(array, 0, end);
    }


    public class Solution {
        public String deDup(String input) {
            // Write your solution here.
            // Write your solution here
            if(input== null || input.length() == 0){
                return input;
            }
            char[] array = input.toCharArray();
            //Deque<Character> stack = new LinkedList<>();
            int i = 0;
            int end = -1;
            while(i<array.length){
                if(end == -1 || array[end] != array[i]){
                    array[++end] = array[i++];
                }else{
                    char prev = array[end--];
                    while(i<array.length && prev == array[i]){
                        i++;
                    }

                }


//            if(stack.isEmpty() || stack.peekLast() != array[i]){
//                stack.offerLast(array[i]);
//                i++;
//            }else{
//                char prev = stack.pollLast();
//                while( i<array.length && array[i] == prev){
//                    i++;
//                }
//            }
            }
//        String res = "";
//        while(!stack.isEmpty()){
//            res = stack.pollLast() + res;
//        }

            return new String(array, 0, end+1);
        }
    }



    //is substring
    static public int strstr(String large, String small) {
        // write your solution here
        if(large == null || large.length()==0 || large.length()<small.length()){
            return -1;
        }

        for(int i = 0; i<large.length()-small.length()+1; i++){
            if(equal(small, large, i)){
                return i;
            }
        }
        return -1;
    }
    static private boolean equal(String s, String l, int i){
        int j = 0;
        while(j<s.length()){
            if(s.charAt(j)!=l.charAt(j+i)) {
                return false;
            }else{
                j++;
            }
        }
        return true;
    }






    //is subsquence , follow-up: a lot of short strings how to optimize?
    public boolean isSubsequence(String l, String s){
        if(s.length()>l.length()){
            return false;
        }else if(s.length()== 0){
            return true;
        }

        int i = 0, j = 0;
        while(i<l.length() && j<s.length()){
            if(l.charAt(i) == s.charAt(j)){
                i++;
                j++;
            }else{
                i++;
            }
        }

        return j==s.length();
    }
    public boolean isSubsequenceII(String l, String s){
        if(s.length()>l.length()){
            return false;
        }else if(s.length()== 0){
            return true;
        }
        Map<Character, List<Integer>> map = new HashMap<>();
        for(int i = 0; i<l.length(); i++){
            Character c = l.charAt(i);
            if(!map.containsKey(c)){
                map.put(c, new ArrayList<Integer>());
            }
            map.get(c).add(i);
        }

        int cur = 0;
        for(int i = 0; i<s.length(); i++){
            Character c = s.charAt(i);
            cur = binarySearch(map, c, cur);
            if(cur == -1){
                return false;
            }
            cur++;
        }
        return true;
    }
    private int binarySearch(Map<Character, List<Integer>> map, Character c, int cur){
        if(!map.containsKey(c)){
            return -1;
        }

        List<Integer> nums = map.get(c);
        int l = nums.get(0);
        int r = nums.get(nums.size()-1);
        while(l+1<r){
            int mid = l+(r-l)/2;
            if(nums.get(mid) == cur){
                return cur;
            }else if(nums.get(mid) < cur){
                r = mid;
            }else{
                l = mid;
            }
        }
        return nums.get(r)>=cur?r:-1;

    }



    static public void main(String[] args){
        System.out.println(deDup("aaacccdef"));
        System.out.println(strstr("aaacccdef", "cdef"));
    }

}
