package com.company;

public class VotingAlgo {

    /*
    * In its simplest form, the algorithm finds a majority element, if there is one: that is, an element that occurs
    * repeatedly for more than half of the elements of the input.
    * However, if there is no majority, the algorithm will not detect that fact, and will still output one of the elements.
    * A version of the algorithm that makes a second pass through the data can be used to verify
    * that the element found in the first pass really is a majority.
    *Initialize an element m and a counter i with i = 0
     For each element x of the input sequence:
        If i = 0, then assign m = x and i = 1
        else if m = x, then assign i = i + 1
        else assign i = i − 1
     Return m
     Do a second pass
    *
    * */

    public static int findMajority(int[] array){
        int candidate = array[0];
        int counter = 1;
        int len = array.length;
        for(int i = 1; i< len; i++){
            if(counter == 0){
                candidate = array[i];
                counter = 1;
            }
            if(array[i] == candidate){
                counter++;
            }else{
                counter--;
            }
        }

        ;
        return secondCheck(array, candidate, 2)? candidate : Integer.MIN_VALUE;
    }

    public static int findMajorityK(int[] array, int k){
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<array.length; i++){
            Integer temp = map.get(array[i]);
            if(temp == null){
                if(map.size()<k-1){
                    map.put(array[i], 1);
                }else {
                    reduceOne(map);
                }
            }else {
                map.put(array[i], temp+1);
            }

        }
        int res = 0;
        for(Integer e: map.keySet()){
            res = Math.max(map.get(e), res);
        }
        return secondCheck(array, res, k)? res: Integer.MIN_VALUE;
    }
    private static void reduceOne(Map<Integer, Integer> map){
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            map.put(entry.getKey(), entry.getValue());
        }
    }

    private static boolean secondCheck(int[] array, int candidate, int k){
        int counter = 0;
        for(int i = 0; i<array.length; i++){
            if(array[i] == candidate){
                counter++;
            }
        }
        return counter>array.length/k;
    }



}
