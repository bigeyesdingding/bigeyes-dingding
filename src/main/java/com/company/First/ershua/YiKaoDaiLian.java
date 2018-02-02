package com.company.First.ershua;

import java.util.*;
/*Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number).
Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries ,
where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.

*/


/*Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.

Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].

Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
* */

public class YiKaoDaiLian {

    public static void main(String[] args){
        int[][] t1 = new int[][]{{2,1,1},{2,3,1},{3,4,1}};
        //System.out.println(networkDelayTime(t1, 4,2));

        int[][] t2 = new int[][]{{1,2}, {2,3}, {3,4}, {1,4}, {1,5}};
        int[][] t3 = new int[][]{{1,2}, {1,3}, {2,3}};
        //System.out.println(Arrays.toString(findRedundantConnection(t2)));
        //System.out.println(Arrays.toString(findRedundantConnection(t3)));

        int[][] t4 = new int[][]{{1,2},{1,3},{2,3}};
        int[][] t5 = new int[][]{{1,2}, {2,3}, {3,4}, {4,1}, {1,5}};
        System.out.println(Arrays.toString(findRedundantDirectedConnection(t4)));
        System.out.println(Arrays.toString(findRedundantDirectedConnection(t5)));

        String[][] e1 = new String[][]{{"a", "b"}, {"b", "c"}};
        double[] v1 = new double[]{2.0,3.0};
        String[][] q1 = new String[][]{{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
        System.out.println(Arrays.toString(calcEquation(e1,v1,q1)));
    }

    public static double[] calcEquation(String[][] e, double[] values, String[][] q) {

        //find a path from a to c if there is no path, return -1
        Map<String, Map<String, Double>> map = new HashMap<>();
        for(int i = 0; i<e.length; i++){
            if(!map.containsKey(e[i][0])){
                map.put(q[i][0], new HashMap<String, Double>());
            }
            if(!map.containsKey(e[i][1])){
                map.put(e[i][1], new HashMap<String, Double>());
            }
            map.get(e[i][0]).put(e[i][1], values[i]);
            map.get(e[i][1]).put(e[i][0], 1.0/values[i]);

        }
        System.out.println(map.get("b").get("c"));
        double[] res = new double[q.length];
        for(int i = 0; i<q.length; i++){
            res[i] = dfs(map, q[i][0], q[i][1], 1.0, new HashSet<>());
        }
        return res;

    }

    private static double dfs(Map<String, Map<String, Double>> map, String start, String end, double cur, Set<String> visited){

        if(map.get(start) == null){
            return -1.0;
        }

        if(visited.contains(start)){
            return -1.0;
        }

        if(start.equals(end)){
            return cur;
        }

        visited.add(start);
        Set<String> set = map.get(start).keySet();

        for(String next: set){
            if(!visited.contains(next)){
                double nextCur = cur*map.get(start).get(next);
                double res = dfs(map, next, end, nextCur, visited);
                if(res != -1.0){
                    return res;
                }
            }
        }
        return -1.0;
    }


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

    public static int[] findRedundantDirectedConnection(int[][] edges) {
        System.out.println("fdg");
        Map<Integer, Integer> map = new HashMap<>();
        int[] dup = new int[]{-1,-1};
        for(int i = 0; i<edges.length; i++){
            int a = edges[i][0];
            int b = edges[i][1];

            if(!map.containsKey(a)){
                map.put(a, a);
            }
            if(map.containsKey(b) && map.get(b) != b){
                dup = new int[]{map.get(b), b};
            }
            map.put(b, a);
        }


        Map<Integer, Integer> group = new HashMap<>();
        for(int i = 0; i<edges.length; i++){
            int a = edges[i][0];
            int b = edges[i][1];
            if(!group.containsKey(a)) {
                group.put(a, a);
            }
            if(!group.containsKey(b)){
                group.put(b, b);
            }
            if(!unionII(a,b, group)){
                if(dup[0] != -1){
                    return new int[]{map.get(dup[1]), dup[1]};
                }else {
                    return edges[i];
                }

            }
        }
        return dup;
    }

    private static boolean unionII(int a, int b, Map<Integer, Integer> group){
        int aa = find(a, group);
        int bb = find(b, group);
        if(aa == bb){
            return false;
        }
        group.put(bb, aa);
        return true;


        
    }




}
