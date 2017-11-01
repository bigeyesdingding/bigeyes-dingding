package com.company;

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
    public static void main(String[] args){

    }
}
