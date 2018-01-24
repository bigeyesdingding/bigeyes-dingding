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
        return res;
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

    //is balanced or not
    public boolean isBalanced(TreeNode root){
        if(root == null){
        return true;
    }
        return height(root) != -1;
}

    public int height(TreeNode root){
        if(root == null){
            return 0;
        }

        int left = height(root.left);
        if(left == -1){
            return -1;
        }
        int right = height(root.right);
        if(right == -1){
            return -1;
        }


        if(Math.abs(left-right) >1){
            return -1;
        }

        return Math.max(left, right) + 1;

    }

    //is symmetric or not(zhou duichen)
    public boolean pp(TreeNode root){
        if(root == null){
            return true;
        }
        return isSysmetric(root.left, root.right);
    }
    public boolean isSysmetric(TreeNode left, TreeNode right){

        if(left == null && right== null){
            return true;
        }else if(left == null || right == null){
            return false;
        }else if(left.value!= right.value){
            return false;
        }else {
            return isSysmetric(left.right, right.left) && isSysmetric(left.left, right.right);
        }

    }

    public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
        // Write your solution here.
        if(one == null && two == null) {
            return true;
        }else if(one == null || two == null){
            return false;
        }else if(one.value != two.value ){
            return false;
        }else{
            return (isTweakedIdentical(one.left, two.right) && isTweakedIdentical(one.right, two.left))
                    ||(isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right));
        }
    }

    public boolean isBST(TreeNode root) {
        // Write your solution here.
        if(root == null){
            return true;
        }
        return bstHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private boolean bstHelper(TreeNode root, int min, int max){
        if(root == null){
            return true;
        }

        if(root.value<=min || root.value>=max){
            return false;
        }else {
            return bstHelper(root.left, min,root.value) && bstHelper(root.right, root.value, max);
        }
    }

    public List<Integer> getRange(TreeNode root, int min, int max) {
        // Write your solution here.
        List<Integer> res = new ArrayList<>();
        range(root, res, min, max);
        return res;
    }
    private void range(TreeNode root, List<Integer> res, int min, int max){
        if(root == null){
            return;
        }
        range(root.left, res, min, max);
        if(root.value <=max && root.value>=min){
            res.add(root.value);
        }
        range(root.right, res, min, max);
    }
}
