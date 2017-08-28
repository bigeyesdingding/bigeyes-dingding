package com.company;

public class TreeNode {

    public int value;       // value stored in this node
    public TreeNode left;  // link to next node in the list
    public TreeNode right;  // link to next node in the list

    // post: constructs a node with data 0 and null link
    public TreeNode() {
        value = 0;
        left = null;
        right = null;
    }

    // post: constructs a node with given data and null link
    public TreeNode(int val) {
        value = val;
        left = null;
        right = null;
    }

    // post: constructs a node with given data and given link
    public TreeNode(int val, TreeNode l,TreeNode r) {
        value = 0;
        left = l;
        right = r;
    }



    public static void main(String[] args){
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(5);
        node.right = new TreeNode(10);
        System.out.println(node.value);
        System.out.println(node.left.value);
        System.out.println(node.right.value);


    }
}
