package com.company.First;

public class MaxLeafPath {
    public class TreeNode{
        public int value;
        public TreeNode left;
        public TreeNode right;
    }

    public Integer maxLeafPath(TreeNode root){
        if(root == null ){
            return 0;
        }

        int globalMax = Integer.MIN_VALUE;
        helper(root, globalMax);
        return globalMax;
    }

    private Integer helper(TreeNode root, int globalMax) {
        //base case, null
        if(root == null){
            return 0;
        }

        int leftMax = helper(root.left, globalMax);//recursion rules
        int rightMax = helper(root.right, globalMax);//

        if(root.left != null && root.right != null){//if root node is full, first update global max, then return max branch
            globalMax = Math.max(globalMax,leftMax+root.value+rightMax);//operations on this level
            return Math.max(leftMax,rightMax) + root.value; //return the max branch to the upper level

        }else{//if tree node is not full
            return root.left != null? leftMax+root.value : rightMax+root.value;

        }
    }

}
