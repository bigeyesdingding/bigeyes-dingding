package com.company.Again;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
}


class PutChair{
    public int[] putChair(int[][] matrix){
    if(matrix == null || matrix.length == 0){
        return null;
    }
    int m = matrix.length;
    int n = matrix[0].length;
    int[][] cost = new int[m][n];
    //1
    for(int i = 0; i<m; i++){
        for(int j = 0; j<n; j++){
            if(matrix[i][j] == 2){
                if(!addCost(i, j, matrix, cost)){
                    return null;
                }
            }
        }
    }
    int[] result = new int[2];

    //2
    for(int i = 0; i<m;i++){
        for(int j = 0; j<n; j++){
            if(result == null){
                result = new int[]{i, j};
            }else if(cost[i][j] < cost[result[0]][result[1]]){
                result[0] = i;
                result[1] = j;
            }
        }
    }
    return result;
}

    private boolean addCost(int x, int y, int[][] matrix, int[][] cost){
        //BFS
        Queue< Integer[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        q.offer(new Integer[]{x,y});
        int lvl = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i<size; i++){
                Integer[] node = q.poll();
                cost[node[0]][node[1]] = lvl;
                List<int[]> neis = getNeis(matrix, node);
                for(int[] nei : neis){
                    visited[nei[0]][nei[1]] = true;
                }
            }
            lvl++;
        }
        //check if there is one not reachable
        for(int i = 0; i<matrix.length;i++){
            for(int j = 0; j<matrix[0].length; j++){
                if(matrix[i][j] == 2 && !visited[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    private List<int[]> getNeis(int[][] matrix, Integer[] node){
        int m = matrix.length;
        int n = matrix[0].length;
        List<int[]> res  = new ArrayList<>();
        if(node[0]+1<m){
            res.add(new int[]{node[0]+1, node[1]});
        }
        if(node[0]-1>=m){
            res.add(new int[]{node[0]-1, node[1]});
        }
        if(node[1]+1<n){
            res.add(new int[]{node[0], node[1]+1});
        }
        if(node[1]-1>=0){
            res.add(new int[]{node[0], node[1]-1});
        }
        return res;
    }
}