package com.company.First.Amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoviePatition {

    public List<String> partition(String s){
        List<String> res = new ArrayList<>();

        Map<Character, Integer> show = new HashMap<>();
        for(int i = 0; i<s.length(); i++){
            show.put(s.charAt(i), i);
        }

        int cur = 0;
        while(cur < s.length()){
            int start = cur, end = cur;
            while(cur<=end){
                //System.out.println(cur);
                end = Math.max(end, show.get(s.charAt(cur)));
                cur++;
            }
            res.add(s.substring(start, end+1));
        }
        return res;
    }
    public static void main(String args[]){
        MoviePatition test = new MoviePatition();
        List<String> res = test.partition("abacdfeffhijkika");
        res.forEach(item->System.out.println(item));
    }

}
