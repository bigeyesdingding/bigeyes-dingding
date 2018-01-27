package com.company.First.ershua;

import com.company.First.GraphNode;
import com.company.First.TreeNode;

import java.util.*;

public class Bfs {

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        return res;
    }
    //alien dictionary
    public String alienOrder(String[] words) {



        //for words, word[i] word[i+1] first differ char

        Map<Character, Set<Character>> map = new HashMap<>();
        Map<Character, Integer> degree = new HashMap<>();
        for(String s: words){
            for(char c: s.toCharArray()){
                degree.put(c,0);
            }
        }
        for(int i = 0; i<words.length-1; i++){
            findDif(words[i], words[i+1], degree, map);

        }

        //s2: useing q to store 0 degree nodes
        Queue<Character> q = new LinkedList<>();
        for(Character c: degree.keySet()){
            if(degree.get(c)==0){
                q.offer(c);
            }
        }

        //s3: for 0 degree node, -- the next nodes's degree

        String res = "";
        while(!q.isEmpty()) {
            Character c = q.poll();
            res += c;
            if (map.containsKey(c)) {
                for (Character cur : map.get(c)) {

                    degree.put(cur, degree.get(cur) - 1);
                    if (degree.get(cur) == 0) {
                        q.offer(cur);
                    }
                }
            }
        }

        return res.length()==degree.size()? res:"";

    }
    private void findDif(String s1, String s2, Map<Character, Integer> degree, Map<Character, Set<Character>> map){
        int len = Math.min(s1.length() , s2.length());
        for(int j=0; j<len; j++){
            char c1=s1.charAt(j);
            char c2=s2.charAt(j);
            if(c1!=c2){
                Set<Character> set=new HashSet<Character>();
                if(map.containsKey(c1)) set=map.get(c1);
                if(!set.contains(c2)){
                    set.add(c2);
                    map.put(c1, set);
                    degree.put(c2, degree.get(c2)+1);
                }
                break;
            }
        }
    }


    //level travesala
    public List<List<Integer>> layerByLayer(TreeNode root) {
        // Write your solution here.
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            res.add(new ArrayList<Integer>());
            return res;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);



        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> cur = new LinkedList<>();
            for(int i = 0; i<size; i++){
                TreeNode node = q.poll();
                cur.add(node.value);
                if(node.left!=null){
                    q.offer(node.left);
                }
                if(node.right!=null){
                    q.offer(node.right);
                }
            }
            res.add(cur);
        }
        return res;
    }

    //bipartite
    public boolean isBipart(List<GraphNode> graph){
        if(graph == null || graph.size() == 0){
            return true;
        }

        Map<GraphNode, Integer> map = new HashMap<>();
        for(int i = 0; i<graph.size(); i++){

            if(!checkGroup(map, graph, i)){
                return false;
            }

        }
        return true;
    }
    private boolean checkGroup(Map<GraphNode, Integer> map, List<GraphNode> list, int i){
        if(map.get(list.get(i))!= null){
            return true;
        }
        map.put(list.get(i), 1);
        Queue<GraphNode> q = new LinkedList<> ();
        q.offer(list.get(i));

        while(!q.isEmpty()){
            GraphNode cur = q.poll();
            int group = map.get(cur);
            int neiGroup = group==1?2:1;
            for(GraphNode nei: cur.neighbors){
                if(!map.containsKey(nei)){
                    map.put(nei, neiGroup);
                }else if(map.get(nei) == group){
                    return false;
                }
            }
        }
        return true;
    }
    static public void main(String[] args){
        Bfs t = new Bfs();
        String[] p = {"za","zb","ca","cb"};
        System.out.println(t.alienOrder(p));
    }

}
