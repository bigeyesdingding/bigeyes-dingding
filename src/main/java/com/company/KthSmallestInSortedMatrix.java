package com.company;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthSmallestInSortedMatrix {
    public int kthSmallest(int[][] matrix, int k){
        int rows = matrix.length;
        int columns = matrix[0].length;
        //best first search, need a minheap on the value of each cells
        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new Comparator<Cell>()
        {
            @Override
            public int compare(Cell c1, Cell c2){
                if(c1.value == c2.value){
                    return 0;
                }
                return c1.value<c2.value? -1:1;
            }
        });

        //all the generated cells will be marked true
        //to avoid being generateds more than once.
        boolean[][] visited = new boolean[rows][columns];
        minHeap.offer(new Cell(0,0,matrix[0][0]));
        visited[0][0] = true;
        //iterate k-1 rounds, and best first search the smallest k-1 cells
        for(int i =0; i<k-1;i++){
            Cell cur = minHeap.poll();
            //the neightbor cell will be inserted back to the minheap only if :
            //1;it is not our of boundary
            //2.it has not been generated before
            //beacause for each cell it could be generated twice
            if(cur.row+1<rows&& !visited[cur.row+1][cur.column]){
                minHeap.offer(new Cell(cur.row+1,cur.column,matrix[cur.row+1][cur.column]));
                visited[cur.row+1][cur.column] = true;
            }
            if(cur.column+1<rows&& !visited[cur.row][cur.column+1]){
                minHeap.offer(new Cell(cur.row,cur.column+1,matrix[cur.row][cur.column+1]));
                visited[cur.row][cur.column+1] = true;
            }
        }
        return minHeap.peek().value;
    }

    static class Cell{
        int row;
        int column;
        int value;
        Cell(int row, int column, int value){
            this.row = row;
            this.column = column;
            this.value = value;
        }
    }



    public static int kthSmallestInTwo(int[] a, int[] b, int k){
        if(a.length+b.length<k){
            return Integer.MIN_VALUE;
        }

        return find(a, 0, b, 0, k);

    }

    private static int find(int[] a, int as, int[] b, int bs, int k){
        if(as>=a.length){
            return b[bs+k-1];
        }
        if(bs>=b.length){
            return a[as+k-1];
        }
        if(k == 1){
            return Math.min(a[as], b[bs]);
        }

        int amid = as+k/2-1;
        int bmid = bs+k/2-1;
        int aval = amid>=a.length? Integer.MAX_VALUE: a[amid];
        int bval = bmid>=b.length? Integer.MAX_VALUE: b[bmid];
        if(aval==bval){
            return aval;
        }else if(aval<bval){
            return find(a, amid+1,b, bs, k-k/2 );
        }else{
            return find(a, as, b, bmid+1, k-k/2);
        }
    }


    //find median of two sorted arrays
    public double median(int[] a, int[] b) {
        // write your solution here
        int totalLen = a.length+b.length;
        int first = (totalLen+1)/2;
        int second = (totalLen+2)/2;
        return (helper(a, 0, b, 0, first) + helper(a, 0, b,0, second))/2;


    }
    private double helper(int[] a, int al, int[] b, int bl, int k){
        if(al>=a.length){
            return b[bl+k-1];
        }
        if(bl>=b.length){
            return a[al+k-1];
        }
        if(k==1){
            return Math.min(a[al],b[bl]);
        }

        int amid = al+k/2-1;
        int bmid = bl+k/2-1;
        int aval= amid>=a.length?Integer.MAX_VALUE: a[amid];
        int bval= bmid>=b.length?Integer.MAX_VALUE: b[bmid];

        if(aval<bval){
            return helper(a, amid+1, b, bl,k- k/2);
        }else{
            return helper(a, al, b, bmid+1, k - k/2);
        }
    }

}
