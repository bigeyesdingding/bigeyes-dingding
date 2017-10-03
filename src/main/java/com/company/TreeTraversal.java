package com.company;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreeTraversal {

    public static void inorderTraversal(TreeNode root)
    {
        /*
        * using a stack to store the current node, add it first and then set
        * current = current.left
        *
        * while current == null and statck is not null, stack.pop()
        * while current == null and stack == null,
        * return
        *
        * */
        if(root == null){
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode current = root.left;

        while(!stack.isEmpty()){
            if(current != null){
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();
                System.out.println(current.value);
                current = current.right;
            }
        }



    }

    public static void preOrderTraversal()
    {
        /*
        * using a stack to store the current node, add it first and then set
        * current = current.left
        *
        * while current == null and statck is not null, stack.pop()
        * while current == null and stack == null,
        * return
        *
        * */


    }

    public static void backOrderTraversal()
    {
        /*
        * using a stack to store the current node, add it first and then set
        * current = current.left
        *
        * while current == null and statck is not null, stack.pop()
        * while current == null and stack == null,
        * return
        *
        * */


    }

    public static void main(String[] args){
        List<TreeNode> tree = new LinkedList<>();
//        tree.add()
    }
}
