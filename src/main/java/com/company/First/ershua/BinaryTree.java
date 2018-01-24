package com.company.First.ershua;

import com.company.First.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTree {

    //pre order travesal, in-order, post-order
    public List<Integer> post(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        postHelper(root, res);
        return res;
    }
    private void postHelper(TreeNode root, List<Integer> res){
        if(root == null){
            return;
        }
        postHelper(root.left, res);
        postHelper(root.right, res);
        res.add(root.value);

    }

    public List<Integer> post2(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerLast(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.peekLast();
            if(cur.left != null){
                stack.offerLast(cur.left);
                cur.left = null;
            }else if(cur.right != null){
                stack.offerLast(cur.right);
                cur.right = null;
            }else{
                res.add(stack.pollLast().value);
            }
        }
    }

    public List<Integer> post3(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerLast(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pollLast();
            res.add(0, cur.value);
            if(cur.left != null){
                stack.offerLast(cur.left);
            }
            if(cur.right!=null) {
                stack.offerLast(cur.right);
            }
        }
        return res;
    }
    public List<Integer> pre(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerLast(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pollLast();
            res.add( cur.value);
            if(cur.right!=null) {
                stack.offerLast(cur.right);
            }
            if(cur.left != null){
                stack.offerLast(cur.left);
            }

        }
        return res;

    }

    public List<Integer> inOrder(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;

        while(cur!= null || !stack.isEmpty()){
            if(cur!=null){
                stack.offerLast(cur);
                cur = cur.left;
            }else{
                cur = stack.pollLast();
                res.add(cur.value);
                cur = cur.right;
            }

        }
        return res;
    }


}
