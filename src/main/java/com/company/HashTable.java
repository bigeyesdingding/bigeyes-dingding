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

    public int missingII(int[] array){

        int n = array.length+1;
        int xor = 0;
        //xor 1 to n
        for(int i = 1; i<n; i++){
            xor ^= i;
        }
        //after this operation, all the numbers from 1 to n are pair xor'ed except for the missing number
        //sinve x^x =0; the number is the result
        for(int num : array){
            xor ^=num;
        }
        return xor;
    }

    public int missingIII(int[] array){
        //try to swap the numberts to the rihgt position
        //for the number x, the corresponding is x-1.
        for(int i = 0; i<array.length;i++){
            //while array[i]\ is not i+1,swap[i] to its correct position if possible
            while(array[i]!=i+1 && array[i]!=array.length+1){
                swap(array,i, array[i]-1);
            }
        }
        for(int i =0; i<array.length;i++){
            if(array[i]!=i+1){
                return i+1;
            }
        }
        //if all the numbers opf 1-n-1 are in position, the missing number is n
        return array.length+1;
    }
    public void swap(int[] array, int l, int r){
        int temp =array[l];
        array[l] = array[r];
        array[r]= temp;
    }

    //find the common numbers between teo sorted arrays a[n],b[m],m,n.
    /*assumptions:
    * duplicates?
    * ascending or descending
    * n vs m, who is greater?
    *
    *
    * */
    public List<Integer> commonI(int[] a, int[] b){
        //assumption: a, b is not null
        //     ascending order
        List<Integer> common = new ArrayList<Integer>();

        int i =0;
        int j = 0;
        while(i<a.length && j<b.length){
            if(a[i]==b[j]){
                common.add(a[i]);
                j++;
                i++;
            }else if(a[i]>b[j]){
                j++;
            }else{
                i++;
            }
        }
        return common;
    }
/*
    public List<Integer> commonII(int[] a, int[] b){
        List<Integer> common = new ArrayList<Integer>();
        HashMap<Integer,Integer> countA = new HashMap<>();
        for(int i =0; i <a.length;i++){
            if(true){
                int count = countA.get(a[i]);
                if(count!=null){
                    countA.put(a[i],count+1);
                }else{
                    countA.put(a[i],1);
                }
            }
        }
        HashMap<Integer,Integer> countB = new HashMap<>();
        for(int i =0; i <b.length;i++){
            if(true){
                int count = countB.get(b[i]);
                if(count!=null){
                    countB.put(b[i],count+1);
                }else{
                    countB.put(b[i],1);
                }
            }
        }
        for(Map.Entry<Integer, Integer> entry: countA.entrySet()){
            Integer ctInB = countB.get(entry.getKey());
            if(ctInB != null){
                int appear = Math.min(entry.getValue(),ctInB);
                for(int i =0; i<appear; i++){
                    common.add(entry.getKey());
                }
            }
        }
        return common;
    }
*/

}
