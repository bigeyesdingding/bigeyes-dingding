package com.company.First.ershua;

import java.util.*;

public class YiKaoDaiLian {

    public static int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        boolean[] visited = new boolean[n+1];
        for(int i = 1; i<=n; i++){
            map.put(i, new ArrayList<List<Integer>>());
        }
        for(int i = 0; i<times.length;i++){
            List<Integer> list = new ArrayList<Integer>();
            list.add(times[i][1]);
            list.add(times[i][2]);
            map.get(times[i][0]).add(list);
        }
        //pq
        PriorityQueue<List<Integer>> q = new PriorityQueue<>(new Comparator<List<Integer>>(){
            @Override
            public int compare(List<Integer> l1, List<Integer> l2){
                return Integer.compare(l1.get(1), l2.get(1));
            }
        });
        List<Integer> cur = Arrays.asList(new Integer[]{k, 0});
        q.offer(cur);
        int res = 0;
        while(!q.isEmpty()){
            while(!q.isEmpty() && visited[q.peek().get(0)]){
                q.poll();
            }
            if(q.isEmpty()){
                break;
            }
            cur = q.poll();
            visited[cur.get(0)] = true;
            //System.out.println(cur.get(1));
            res = Math.max(res, cur.get(1));

            for(List<Integer> next: map.get(cur.get(0))){
                if(visited[next.get(0)]){
                    continue;
                }else{
                    next.set(1, cur.get(1)+next.get(1));
                    q.offer(next);
                }
            }
        }
        for(int i = 1; i<=n; i++){
            if(!visited[i]){
                return -1;
            }
        }
        return res;
    }

    public static int[] findRedundantConnection(int[][] edges) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<edges.length;i++){
            int a = edges[i][0];
            int b = edges[i][1];
            if(!map.containsKey(a)){
                map.put(a,a);
            }
            if(!map.containsKey(b)){
                map.put(b,b);
            }
            if(!union(a,b, map)){
                return new int[]{a,b};
            }
        }
        return new int[2];
    }

    private static int find(int a, Map<Integer, Integer> map){
        while(map.get(a) != a){
            a = map.get(a);
        }
        return a;
    }

    private static boolean union(int a, int b, Map<Integer, Integer> map){
        int l = find(a, map);
        int r = find(b, map);
        if(l==r){
            return false;
        }

        map.put(r, l);
        return true;
    }

    public int[] findRedundantDirectedConnection(int[][] edges) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] dup = new int[0];
        for(int i = 0; i<edges.length; i++){
            int a = edges[i][0];
            int b = edges[i][1];

            if(!map.containsKey(a)){
                map.put(a, a);
            }
            if(map.containsKey(b) && map.get(b) != b){
                dup = edges[i];
            }else{
                map.put(b, a);
            }
        }

        for(int i = 0; i<edges.length; i++){
            int a = edges[i][0];
            int b = edges[i][1];
            if(!union(a,b, map)){
                return new int[]{map.get(dup[1]), dup[1]};
            }
        }
        return dup;
    }

    private boolean union(int a, int b, Map<Integer, Integer> map){


        
    }


    public static void main(String[] args){
        int[][] t1 = new int[][]{{2,1,1},{2,3,1},{3,4,1}};
        System.out.println(networkDelayTime(t1, 4,2));

        int[][] t2 = new int[][]{{1,2}, {2,3}, {3,4}, {1,4}, {1,5}};
        int[][] t3 = new int[][]{{1,2}, {1,3}, {2,3}};
        System.out.println(Arrays.toString(findRedundantConnection(t2)));
        System.out.println(Arrays.toString(findRedundantConnection(t3)));
    }

}
