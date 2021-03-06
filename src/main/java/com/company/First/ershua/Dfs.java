package com.company.First.ershua;

import java.util.*;

public class Dfs {

    public String parseTernary(String expression) {

    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<Integer>(), k, n, n, 1 );
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> cur, int k, int n, int target, int index){

        if(cur.size() == k){
            if(target == 0){
                res.add(new ArrayList<>(cur));
            }
            return;
        }

        for(int i = index; i<=n; i++){
            if(target>=i){
               cur.add(i);
               dfs(res, cur, k, n, target-i, i+1);
               cur.remove(cur.size()-1);
            }
        }

    }


    //frog jump
    public boolean canCross(int[] stones) {

        if(stones.length<2){
            return true;
        }
        if(stones[1]-stones[0] != 1){
            return false;
        }

        return helper(stones, 1,1);
    }
    private boolean helper(int[] stones, int i, int steps){

        boolean pass = false;
        if(i==stones.length-1){
            return true;
        }
        for(int j = i+1; j<stones.length; j++){
            if(stones[i]+steps-1>=stones[j] && stones[i]+steps+1<=stones[j] ){
                pass = pass||helper(stones, j, stones[j]-stones[i]);
            }
        }
        return pass;
    }



    //coin combination
    public List<List<Integer>> combinations(int target, int[] coins) {
        // Write your solution here.

        List<List<Integer>> res = new ArrayList<>();
        if(target<=0){
            return res;
        }

        coinHelper(res, target, coins, 0, new Integer[coins.length]);
        return res;
    }

    private void coinHelper(List<List<Integer>> res, int target, int[] coins, int index, Integer[] cur){
        if(index>=coins.length){
            if(target == 0){
                res.add(Arrays.asList(cur));
            }
            return;
        }

        int max = target/coins[index];
        for(int i = 0; i<=max; i++){
            cur[index] = i;
            coinHelper(res, target-coins[index]*i, coins, index+1, cur);
        }
    }

    //valid parentgese
    public List<String> validParentheses(int n) {
        // Write your solution here.
        List<String> res = new ArrayList<>();
        if(n == 0){
            res.add("");
            return res;
        }

        parent(res, new char[2*n],n,n, 0);
        return res;
    }

    private void parent(List<String> res, char[] array, int left, int right, int index){
        if(right == 0 && left == 0){
            res.add(new String(array));
            return;
        }

        if(left>0){
            array[index] = '(';
            parent(res, array, left-1, right, index+1);
        }
        if(right>left){
            array[index] = ')';
            parent(res, array, left, right-1, index+1);
        }
    }

    //subsets
    public List<String> subSets(String set) {
        // Write your solution here.
        List<String> res = new ArrayList<>();
        if(set == null || set.length()== 0){
            return res;
        }

        findSets(res, set.toCharArray(), new StringBuilder(), 0);
        return res;
    }
    private void findSets(List<String> res, char[] array, StringBuilder sb, int level){
        if(level>=array.length){
            res.add(new String(sb.toString()));
            return;
        }

        //add
        sb.append(array[level]);
        findSets(res, array, sb, level+1);
        sb.deleteCharAt(sb.length()-1);

        //not add
        findSets(res, array, sb, level+1);
    }


    //all permutation
    public List<String> permutations(String set) {
        // Write your solution here.re

        List<String> res = new ArrayList<>();
        if(set == null){
            return res;
        }else if(set.length()== 0){
            res.add("");
            return res;
        }

        permute(set.toCharArray(), res, 0);
        return res;
    }
    private void permute(char[] array, List<String> res, int index){
        if(index>=array.length){
            res.add(Arrays.toString(array));
            return;
        }

        for(int i = index; i<array.length; i++){
            swap(array, index, i);
            permute(array, res, index+1);
            swap(array, index, i);
        }
    }
    private void swap(char[] array, int i, int j){
        char c = array[i];
        array[i] = array[j];
        array[j] = c;
    }

    public static void main(String[] args){

    }
}
