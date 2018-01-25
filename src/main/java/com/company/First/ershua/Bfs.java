package com.company.First.ershua;

import com.company.First.GraphNode;
import com.company.First.TreeNode;

import java.util.*;

public class Bfs {



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

}
