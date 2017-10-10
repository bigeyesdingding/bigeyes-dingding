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
        //base case
        if(root == null){
            return 0;
        }
        if(root.left == null && root.right == null){
            return 1;
        }

        //step 1:
        int leftNum = leftSubTree(root.left);
        int rightNum = leftSubTree(root.right);
        //step 2:

        //step 3:
        return leftNum+rightNum+1;

    }

    //find the max difference of the total number descendents in the its left subtree
    public static int maxDiffNode(TreeNode root, TreeNode[] node, int[] diff){
        //base case
        if(root == null){
            return 0;
        }

        //step one
        int left = maxDiffNode(root.left, node, diff);
        int right = maxDiffNode(root.right, node, diff);
        if(Math.abs(left-right)>diff[0]){
            node[0] = root;
            diff[0] = Math.abs(left-right);
        }
        return 1;
    }
    public static void main(String[] args){
        TreeNode root = new TreeNode(3);
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        root.left = one;
        root.right = two;
        one.left = three;
        three.right = four;
        one.right = five;

        System.out.println(leftSubTree(root.right));
    }
}
