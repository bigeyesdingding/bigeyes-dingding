package com.company.First.Amazon;

import java.util.*;

public class KCharacters {
    public List<String> minWindow(String s, int k){


        Set<String> set = new HashSet<>();
        if(s == null || s.length()==0){
            return new ArrayList<>();
        }

        int fast = 0, slow = 0;
        Map<Character, Integer> map = new HashMap<>();
        while(fast<s.length()){
            Character c = s.charAt(fast);

            map.put(c, map.getOrDefault(c, 0)+1);
            if(fast-slow+1>k){
                Character sc = s.charAt(slow);
                map.put(sc, map.get(sc)-1);
                if(map.get(sc)==0){
                    map.remove(sc);
                }
                slow++;
            }


            if(fast-slow+1 == k && map.size()==k-1){
                set.add(s.substring(slow, fast+1));
            }
            fast++;

        }
        return new ArrayList<>(set);
    }

    public static void main(String args[]){
        KCharacters test = new KCharacters();
        System.out.println(test.minWindow("fff", 2));
    }
}
