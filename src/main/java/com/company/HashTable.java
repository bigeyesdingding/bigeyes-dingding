package com.company;

import java.util.*;

public class HashTable {
    public String[] topKFrequent(String[] combo, int k){
        //handle the special case of empty combo at the very begining
        if(combo.length == 0){
            return new String[0];
        }

        //get all the diostinct strings as keys and their frequency as values
        //notice: the freqMap has at least size 1
        Map<String, Integer> freqMap = getFreqMap(combo);

        //use a min-heap of size k
        //Notice: usifn map.entry as the element type directly so that all the operations are mostly efficient
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k,
                new Comparator<Map.Entry<String, Integer>>() {

                    @Override
                    public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                        return e1.getValue().compareTo(e2.getValue());
                    }
                });
        for(Map.Entry<String, Integer> entry: freqMap.entrySet()){
            if(minHeap.size()<k){
                minHeap.offer(entry);
            }else if(entry.getValue()>minHeap.peek().getValue()){
                minHeap.poll();
                minHeap.offer(entry);
            }
        }
        //since the returned arraty requires the strings sorted by their
        //frequencies ,uyse a separate helper methoid to do this operation
        return freqArray(minHeap);
    }

    private Map<String, Integer> getFreqMap(String[] combo){
        Map<String, Integer> freqMap = new HashMap<>();
        for(String s : combo){
            Integer freq = freqMap.get(s);
            if(freq== null){
                freqMap.put(s, 1);
            }else{
                freqMap.put(s, freq+1);
            }
        }
        return freqMap;
    }

    private String[] freqArray(PriorityQueue<Map.Entry<String, Integer>> minHeap){
        String[] result = new String[minHeap.size()];
        for(int i = minHeap.size()-1; i>=0;i--){
            result[i] = minHeap.poll().getKey();
        }
        return result;
    }

    public int missingI(int[] array){
        int n = array.length+1;
        HashSet<Integer> set = new HashSet<Integer>();
        for(int number: array){
            set.add(number);
        }
        for(int i =1; i<n; i++){
            if(!set.contains(i)){
                return i;
            }
        }
        return n;
    }
}
