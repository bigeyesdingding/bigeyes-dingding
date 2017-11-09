package com.company.First;

import java.util.*;

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


    //zig -zag level traversal of Tree
    public List<TreeNode> serializeI(TreeNode root){

        if(root == null){
            return null;
        }

        List<TreeNode> result = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offerFirst(root);
        boolean reverse = true;
        int size = 1;
        while(!deque.isEmpty()){
            int currentLevel = size;
            size = 0;
            for(int i = 0; i<currentLevel; i++){

                if(reverse){
                    TreeNode temp = deque.pollLast();
                    result.add(temp);
                    if(temp.right!=null){
                        deque.offerFirst(temp.right);
                        size += 1;
                    }
                    if(temp.left!=null){
                        deque.offerFirst(temp.left);
                        size += 1;
                    }

                } else {
                    TreeNode temp = deque.pollFirst();
                    result.add(temp);
                    if(temp.left!=null){
                        deque.offerLast(temp.left);
                        size += 1;
                    }
                    if(temp.right!=null){
                        deque.offerLast(temp.right);
                        size += 1;
                    }

                }
            }
            reverse = !reverse;
        }
        return result;

    }

    //in order travesal of tree
    public List<TreeNode> serializeII(TreeNode root){
        if(root == null){
            return null;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        List<TreeNode> result = new ArrayList<>();
        TreeNode p = root;
        while(!stack.isEmpty() || p!= null){

            if(p.left != null){
                stack.push(p.left);
                p = p.left;
            } else {
                //processing the root node
                TreeNode temp = stack.pop();
                result.add(temp);
                p = temp.right;
            }
        }
        return result;
    }

    // pre order tree Travesal
    public List<TreeNode> serializeIII(TreeNode root){
        if(root != null){
            return null;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        List<TreeNode> result = new ArrayList<>();
        stack.offer(root);

        while(!stack.isEmpty()){
            TreeNode p = stack.pop();
            result.add(p);
            if(p.right!=null){
                stack.push(p.right);
            }
            if(p.left!=null){
                stack.push(p.left);
            }
        }
        return result;
    }

    //post order tree travesal
    public List<TreeNode> serializeIV(TreeNode root){

        if(root == null){
            return  null;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        List<TreeNode> result = new ArrayList<>();
        stack.offer(root);
        while(!stack.isEmpty()){
            TreeNode temp = stack.peek();
            if(temp.left == null && temp.right == null){
                stack.pop();
                result.add(temp);
            }else{
                if(temp.right != null){
                    stack.offer(temp.right);
                }
                if(temp.left !=null){
                    stack.offer(temp.left);
                }
            }
        }
        return result;
    }
}

