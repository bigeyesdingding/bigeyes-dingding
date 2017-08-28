package com.company;

public class BinarySearchTree {

    /*
    *
    * Please be sure what is binary tree::
    *
    * Binary Search Tree: every node, the left subtree is smaller than this node value, the right subtree is larger
    * Complete Binary Tree: in every level, except possiblly the last level, is completely filled, and all nodes are as far left as possible
    * Balanced Binary Tree: every node, the depth of the left and right subtrees of every single node differs only one or less
    *
    *
    * Q1: How to determine whether a binary tree is a balanced binary tree?
    *
    * can use recursive solutions, for every node, check whether the node's left and right subtree height
    *
    *
    * Q2:how to judge a binary tree is symmetric
    *
    *
    * Q3: check whether two trees are identical
    * if we tweak the lchild with the rchild of an arbitary node in a binary tree, then the structure of the tree not changed
    * case 1: totally the same position
    * case 2: just changed the left and right subtrees
    *
    *
    *
    *
    * */
    public static int getHeight(TreeNode root){
        if(root == null ){
            retrun 0;
        }
        return 1+Math.max(getHeight(root.left), getHeight(root.right));
    }

    public static boolean isBalanced(TreeNode root){


        if(root == null) {
            return true;
        }

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        if(Math.abs(leftHeight-rightHeight)>1){
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);

    }

    public static boolean isSymmetric(TreeNode root){
        if(root == null){
            return true;
        }

        return isSymmetric(root.left, root.right);
    }

    public static boolean isSymmetric(TreeNode left, TreeNode right){
        if(left == null  && right == null){
            return true;
        }else if(left == null | right == null){
            return false;
        }else if(left.value != right.value){
            return false;
        }

        return isSymmetric(left.left, right.right) && isSymmetric(right.left, right.right);

    }

    public static  boolean isIdentical(TreeNode left, TreeNode right){

        //Assume: that the original input tree is balanced, therefore we have log_2(n) in the original tree
        //the time complexity is o(n^2);  4^log_2(n)
        //What if it is not balanced? Time Complexity is 2^n

        if(left== null && right == null){
            return true;
        }else if(left == null || right == null){
            return false;
        }else if(left.value != right.value){
            return false;
        }

        return isIdentical(left.left, right.left) &&  isIdentical(left.right, right.right)
                ||
                isIdentical(left.left, right.right) &&  isIdentical(left.right, right.left);
    }


    public static

    public static void main(String[] args){
        System.out.println("This is the Binary Search Tree operations and problems");
    }


}
