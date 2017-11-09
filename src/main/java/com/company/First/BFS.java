package com.company.First;

import java.util.*;

public class BFS {
    public static List<List<Integer>> printBinaryTreeLayer(TreeNode root){
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if(root == null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        //traverse all the nodes on the current level and prepare for the next layer
        while (!queue.isEmpty()) {
            List<Integer> curLayer = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                curLayer.add(cur.value);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            list.add(curLayer);
        }
        return list;
    }

    public static boolean isBipartite(List<GraphNode> graph){
        //assumption: the given graph is not null


        //use 0 and 1 to denote two different groups.
        //the mao maintains for each node which group it belongs to.
        HashMap<GraphNode, Integer> visited =  new HashMap<GraphNode, Integer>();
        //the graph can be represented by a list of nodes (if it is i not guaranteed to be connected)
        //we have to do BFS from each of the nodes
        for(GraphNode node: graph){
            if(!helper(node, visited)){
                return false;
            }
        }
        return true;
    }
    private static boolean helper(GraphNode node, HashMap<GraphNode, Integer> visited){
        //if this node has been traversed, no need ot do bfs again.
        if(visited.containsKey(node)){
            return true;
        }
        Queue<GraphNode> queue = new LinkedList<GraphNode>();
        queue.offer(node);
        //start bfs from the node, since the node has not been visited, we can assign it to any of the groups, for example, group 0
        visited.put(node,0);
        while(!queue.isEmpty()){
            GraphNode cur = queue.poll();
            //the group assigned for cur node
            int curGroup = visited.get(cur);
            //the group assigned for any neighbors of the node
            int neiGroup = curGroup == 0?1:0;
            for(GraphNode nei: cur.successors){
                //if the neighbors has not been visited, just put it in the queue and choose the correct group.
                if(!visited.containsKey(nei)){
                    visited.put(nei, neiGroup);
                    queue.offer(nei);
                }else if(visited.get(nei)!=neiGroup){
                    //only if the neighbors has been traversed and the group does not mathch to the desired one, return false
                    return false;
                }
                //if the neighbors has been tarversed and the group mathces the designed one, we do nopt need to do anything
            }
        }
        return true;

    }

    public static boolean isCompleterTree(TreeNode root){
        //assumption: the original tree is not null
        if(root == null){
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        boolean flag = false;

        while(!queue.isEmpty()){
            //if any of the child is not present, set the flag to true
            TreeNode cur = queue.poll();
            if(cur.left == null){
                flag = true;
            }else if(flag){
                //if a flag is set but we still see cur has a left child, the binary tree is not a completed one.
                return false;
            }else{
                //if a flag is not set and left child is present
                queue.offer(cur.left);
            }

            //same logic applied to the right child
            if(cur.right == null){
                flag = true;
            }else if(flag){
                return false;
            }else{
                queue.offer(cur.right);
            }
        }
        return true;
    }

    //find the k smallest elements in an sorted matrix
    //assumptions

    public static void main(String[] args){
        System.out.println("This is the BFS related problems");
        /*
        * BFS-1 (Nodes we use TreeNode instead)
        *  1. expand: visit/pritn its value
        *  2. generate: reach out to the neightbors: generate node2, node 3
        *  3. data structure: maintain a fifo queue, put all generate nodes in the queue
        *  4. terminate conditions: dp a loop until the queue is empty
        *  5. processing
        *Q1: 打印一个Binary tree layer by layer
        *Q2: bi-partite
        * whether the graph's nodes can be divided ito two groups, such that the nodes in each group do not have direct edges
        * between the nodes that belong to the same group
        *Q3: whether  a binary tree is complete binary tree or not??
        * using bfs, find the first null node, and check whether all following nodes expanded
        *
        *
        *BFS-2
        *
        *
        *
        *
        * */

        String test = "234";
        System.out.println(Integer.valueOf("46545"));

    }

}
