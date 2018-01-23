package com.company.First.OA.Amazon;

import java.util.HashMap;
import java.util.Map;

public class MinWindow {
    public int minWindow(String[] list, String[] find){
        if(find.length== 0){
            return 0;
        }

        if(list.length < find.length){
            return -1;
        }

        int max = Integer.MAX_VALUE;

        int fast = 0, slow = 0;
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i<find.length; i++){
            map.put(find[i], map.getOrDefault(find[i], 0) + 1);
        }

        int counter = map.size();
        while(fast<list.length){
            if(map.containsKey(list[fast])){
                map.put(list[fast], map.get(list[fast])-1);
                if(map.get(list[fast]) == 0){
                    counter--;
                }
            }

            while(counter==0){
                max = Math.min(max, fast-slow+1);

                if(map.containsKey(list[slow])){
                    map.put(list[slow], map.get(list[slow])+1);
                    if(map.get(list[slow]) == 1){
                        counter++;
                    }
                }
                slow++;
            }
            fast++;
        }
        return max== Integer.MAX_VALUE? -1: max;
    }

    public static void main(String args[]){
        MinWindow test = new MinWindow();
        //String[] list = {"apple", "pen", "orange", "banana", "apple", "banaba", "kiwi"};
        String[] list = {"kiwi"};
        String[] find = {"apple"};

        System.out.println(test.minWindow(list, find));
    }
}
