package com.company.First.OA;

import java.util.*;

public class WareHouse {
    public List<List<Integer>> getClosest(List<List<Integer>> list, int m){
        if(list ==  null || list.size() <= m){
            return list;
        }
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>(
                (i2,i1)->Integer.compare(i1.get(0)*i1.get(0) + i1.get(1) * i1.get(1), i2.get(0)*i2.get(0) + i2.get(1) * i2.get(1))
        );
        for(int i = 0; i<list.size(); i++){
            List<Integer> cur = list.get(i);
            pq.offer(cur);
            if(pq.size()>m) {
                pq.poll();
            }
        }
        while(!pq.isEmpty()){
            res.add(pq.poll());
        }
        return res;

    }
    public static void main(String args[]){
        System.out.println("test");


        System.out.println("1: Warehouse");
        WareHouse wh = new WareHouse();
        Integer[][] temp1 = new Integer[][]{{2,3},{3,4},{1,-3}};
        Integer[][] temp2 = new Integer[][]{{1,8},{2,4},{8,9},{5,3},{2,7},{3,5}};
        List<List<Integer>> list1 = new ArrayList<>();
        for(int i = 0; i<temp2.length; i++){
            list1.add(Arrays.asList(temp2[i]));
        }
        List<List<Integer>> res = wh.getClosest(list1, 3);
        for(int i = 0; i<res.size(); i++){
            System.out.println(Arrays.toString(res.get(i).toArray()));
        }

        System.out.println("3: Baseball game");
        BaseballGame bg = new BaseballGame();
        String[] s = new String[] {"5", "-2", "4", "Z","X", "9", "+", "+"};
        System.out.println(bg.countScore(s));

        System.out.println("4: Buy Fruit");
        BuyFruit fruit = new BuyFruit();
        List<List<String>> codeList = new ArrayList<>();
        String[][] temp3 = new String[][]{ {"apple","apple"},{"orange","anything", "orange"}, {"pear", "grape"}};
        for(int i = 0; i<temp3.length; i++){
            codeList.add(Arrays.asList(temp3[i]));
        }
        String[] temp4 = new String[]{"orange", "apple", "apple", "orange", "banana", "orange", "pear", "grape"};
        List<String> shopping = Arrays.asList(temp4);
        System.out.println(fruit.buyFruit(codeList, shopping));

        TopKMovie movie = new TopKMovie();
        Movie mm = new Movie();
        mm.rating= 3;
        mm.similarMovies = new ArrayList<Movie>();
        List<Movie> ll = movie.getNearest(mm, 8);
        System.out.println(ll.size());

        System.out.println("5: anagram bit");
        AnagramOfBit ana = new AnagramOfBit();

        System.out.println(ana.isAnagram(Integer.MAX_VALUE));

    }
}
