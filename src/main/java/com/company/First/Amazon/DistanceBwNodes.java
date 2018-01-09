package com.company.First.Amazon;

public class DistanceBwNodes {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public int findDist(int[] a, int n, int p, int q){
        if(a == null || a.length == 0){
            return 0;
        }
        int res = 0;
        TreeNode root = new TreeNode(a[0]);
        for(int i = 1; i<a.length; i++){
            add(root, a[i]);
        }
        if(find(root, p) && find(root, q)){

            TreeNode lca = findLca(root, p, q);
            System.out.println(lca.val);
            res += dist(lca, p);
            res += dist(lca, q);
        }else{
            return -1;
        }

        return res;
    }

    private TreeNode add(TreeNode root, int value){
        if(root == null){
            return new TreeNode(value);
        }
        if(value>root.val){
            root.right = add(root.right, value);
        }else{
            root.left = add(root.left, value);
        }
        return root;
    }

    private boolean find(TreeNode root, int p){
        if(root == null){
            return false;
        }

        while(root != null){
            if(root.val == p){
                return true;
            }
            else if(root.val < p){
                root = root.right;
            }else {
                root = root.left;
            }
        }
        return false;
    }

    private TreeNode findLca(TreeNode root, int p, int q){
        if(root == null) {
            return null;
        }

        while(root!= null){
            if(root.val > Math.max(p,q)){
                root = root.left;
            }else if(root.val<Math.min(p, q)){
                root = root.right;
            }else{
                return root;
            }
        }
        return null;
    }
    private int dist(TreeNode root, int value){
        if(root == null){
            return -1;
        }

        int res = 0;

        while(root!= null){
            if(root.val == value){
                return res;
            }else if(root.val<value){
                root = root.right;
                res++;
            }else{
                root = root.left;
                res++;
            }
        }
        return res;
    }




    public static void main(String args[]){
        DistanceBwNodes test = new DistanceBwNodes();
        System.out.println(test.findDist(new int[]{5,6,3,1,2,4}, 6, 2, 4));
    }



}
