package com.company.First.Amazon;

import java.util.*;

public class ItemAssociation {
    public String[] association(String[][] input){

        if(input == null || input.length == 0){
            return new String[0];
        }

        //build a map
        Map<String, Integer> map = new HashMap<>();
        int index = 0;
        for(int i = 0; i<input.length; i++){
            if(!map.containsKey(input[i][0])){
                map.put(input[i][0], index);
                index++;
            }
            if(!map.containsKey(input[i][1])){
                map.put(input[i][1], index);
                index++;
            }
        }
        //union
        int[] parent = new int[index];
        Arrays.setAll(parent, i->i);
        for(int i = 0; i<input.length; i++){
            union(parent, map.get(input[i][0]), map.get(input[i][1]));
        }

        //find
        List<List<String>> counter = new ArrayList<>();
        //counter.forEach(item->new ArrayList<String>());
        for(int i = 0; i<index; i++){
            counter.add(new ArrayList<String>() );
        }
        for(String key : map.keySet()){
            counter.get(find(parent, map.get(key))).add(key);
        }
        //get max
        int max = -1;
        for(int i = 0; i<index; i++){
            Collections.sort(counter.get(i));
            if(max == -1 || counter.get(i).size()>counter.get(max).size()){
                max=i;
            }else if(counter.get(i).size()>counter.get(max).size() && compare(counter.get(i), counter.get(max))){
                max = i;
            }
        }

        return counter.get(max).toArray(new String[0]);

    }
    private void union(int[] parent, int i, int j){
        parent[j] = parent[i];
    }

    private int find(int[] parent, int i){
        while(parent[i] != i){
            i = parent[i];
        }
        return i;
    }
    private boolean compare(List<String> s1, List<String> s2){
        int len = s1.size();
        int i = 0;
        while(i<len ){

            if(s1.get(i).equals(s2.get(i))){
                i++;
            }else{
                return s1.get(i).compareTo(s2.get(i)) < 0;
            }
        }
        return false;
    }

    public static void main(String args[]){
        System.out.println("mytest");

        ItemAssociation test = new ItemAssociation();
        String[][] tt = new String[][]{{"A", "B"}, {"B", "C"}, {"D", "E"}};
        System.out.println(Arrays.toString(test.association(tt)));
    }
}
