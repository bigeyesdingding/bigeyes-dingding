package com.company;

import java.util.HashMap;
import java.util.Map;

public class TreeRecursion {
    /*
    * 1.从下向上传递直
    *
    *
    * */
    public static int getHeight(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return Math.max(left, right) + 1;
    }

    //how to store how many nodes in each node's left-subtree
    public static  int leftSubTree(TreeNode root){

        return 0;

    }

    int number = 0;
    public int pathSum(TreeNode root, int sum) {
        if(root == null){
            return 0;
        }

        //using a hashmap to store the prefix sum and the numbers
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int preSum = 0;
        findPath(root, preSum, sum, map);
        return number;
    }

    private void findPath(TreeNode root, int preSum, int target, Map<Integer, Integer> map){
        if(root == null){
            return;
        }
        preSum = preSum+root.value;

        Integer counter = map.get(preSum-target);
        if(counter != null){
            number += counter;
        }

        Integer add = map.get(preSum);
        if(add ==  null){
            add = 1;
        }else{
            add += 1;
        }
        map.put(preSum, add);


        findPath(root.left, preSum, target, map );
        findPath(root.right, preSum, target, map );
        map.put(preSum, add-1);
    }
    public static void main(String[] args){

    }
}
