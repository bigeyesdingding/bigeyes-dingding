package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class Pair{
    public int left;
    public int right;
    Pair(int left, int right){
        this.left = left;
        this.right = right;
    }
}
public class DeSerialization{

    //suppose you have the pre order and in-order of the tree, return the tree back
    public TreeNode deSerializeI(int[] pre, int[] in){
        //asssume pre and in are not null
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<pre.length; i++){
            map.put(in[i], i);
        }

        return helper(pre, map, 0, pre.length, 0, in.length);
    }
    private TreeNode helper(int[] pre, Map<Integer, Integer> map,  int preLeft, int preRight, int inLeft, int inRight){
        if(inLeft>inRight){
            return null;
        }

        TreeNode root = new TreeNode(pre[preLeft]);
        int inRoot = map.get(root);

        root.left = helper(pre, map, preLeft+1, preLeft + inRoot-inLeft, inLeft, inRoot-1);

        root.right = helper(pre, map, preRight-inRight+inRoot, preRight, inRoot+1, inRight);

        return root;

    }

    //suppose you have the post-order in a binary search tree, return the tree root back
    public TreeNode deSerializeII(int[] post){
        if(post == null){
            return null;
        }

        int[] rootIndex = new int[]{post.length-1};
        return helperII(post, rootIndex, Integer.MIN_VALUE);
    }

    private TreeNode helperII(int[] post, int[] rootIndex, int minValue){
        if(rootIndex[0] < 0 || post[rootIndex[0]]<minValue){
            return null;
        }
        TreeNode root = new TreeNode(post[rootIndex[0]--]);
        helperII(post, rootIndex, root.value);
        helperII(post, rootIndex, minValue);
        return root;
    }


    //suppose you have the in-order and level-order, return the tree root back
    public TreeNode deSerializeIII(int[] level, int[] in){

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<in.length;i++){
            map.put(in[i], i);
        }
        LinkedList<Integer> lvl = new LinkedList<>();
        for(int i = 0; i<level.length; i++  ){
            lvl.offer(level[i]);
        }

        return helperIII(lvl, map);
    }

    private TreeNode helperIII(LinkedList<Integer> lvl, Map<Integer, Integer> map){
        if(lvl.isEmpty()){
            return null;
        }
        TreeNode root = new TreeNode(lvl.pollFirst());
        int rootIndex = map.get(root.value);
        LinkedList<Integer> left = new LinkedList<>();
        LinkedList<Integer> right = new LinkedList<>();

        for(Integer item: lvl){
            int index = map.get(item);
            if(index<rootIndex){
                left.add(item);
            } else {
                right.add(item);
            }

        }
        root.left = helperIII(left, map);
        root.right = helperIII(right, map);
        return root;
    }
}
