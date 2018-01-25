package com.company.First.ershua;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HashTable {

    //top k frequency
    public String[] topKFrequent(String[] combo, int k) {
        // Write your solution here.

        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i<combo.length; i++){
            map.put(combo[i], map.getOrDefault(combo[i], 0)+1);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>(){
            @Override
            public int compare(Map.Entry<String, Integer> i1, Map.Entry<String, Integer> i2){
                return Integer.compare(i1.getValue(), i2.getValue());
            }
        });

        for(Map.Entry<String, Integer> entry: map.entrySet()){
            pq.offer(entry);
            if(pq.size()>k){
                pq.poll();
            }
        }
        String[] res = new String[k];
        for(int i = Math.min(k, pq.size())-1; i>=0; i--){
            res[i] = pq.poll().getKey();
        }
        return res;
    }
}
