package com.company.First.ershua;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PQRelated {

    public int kthSmallest(int[][] matrix, int k) {

        // Write your solution here.
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] i1, int[] i2){
                return Integer.compare(i1[2], i2[2]);
            }
        });

        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        pq.offer(new int[]{0,0,matrix[0][0]});
        int  value = matrix[0][0];
        while(k>0 && !pq.isEmpty()){
            value = pq.peek()[2];
            while(!pq.isEmpty() && pq.peek()[2] == value){
                //expand
                int[] cur = pq.poll();
                int x = cur[0];
                int y = cur[1];
                int val = cur[2];
                //generate
                if(x+1<m && !visited[x+1][y]){
                    pq.offer(new int[]{x+1, y, matrix[x+1][y]});
                    visited[x+1][y] = true;
                }

                if(y+1<n && !visited[x][y+1]){
                    pq.offer(new int[]{x, y+1, matrix[x][y+1]});
                    visited[x][y+1] = true;
                }
            }
            k--;
        }
        return value;
    }

    public boolean isInMatrix(int[][] matrix, int target) {
        // Write your solution here.

        //work from the right up to the left bottom
        int i = 0;
        int j = matrix[0].length-1;
        while(i<matrix.length && j>=0){
            if(matrix[i][j] == target){
                return true;
            }else if(matrix[i][j] > target){
                j--;
            }else{
                i++;
            }
        }
        return false;
    }
    public static void main(String[] args){

    }

}
