package com.company;

import java.util.*;

public class WaterContainer {
    public static int maxContainer(int[] array){
        if(array == null || array.length<1){
            return 0;
        }
        Deque<Integer> stack = new LinkedList<>();
        int max = 0;
        int i = 0;
        while(i<array.length){
            if(stack.isEmpty() ||  array[i] >= array[stack.peekLast()]){
                stack.offerLast(i++);
            } else {
                int height = array[stack.pollLast()];
                int width = stack.isEmpty()? i: i - stack.peek()-1;
                max = Math.max(max, height*width);
            }
        }
        while(!stack.isEmpty()){
            int index = stack.pollLast();
            int height = array[index];
            int width = stack.isEmpty()? index: index - stack.peekLast()-1;
            max = Math.max(max, height*width);
        }
        return max;
    }

    public static int trapWaterVolumnI(int[] array){
        //for each index i in the array, calculate the the water it can trap
        //the max water at index i is: min(leftMax,rightMax)
        //two directions: l-->r r-->l, travesal of the input array. get leftMax and rightMax
        int len = array.length;
        int res = 0;
        int[] leftMax = new int[len+1];
        int[] rightMax = new int[len+1];
        for(int i = 1; i<len;i++){
            leftMax[i] = Math.max(leftMax[i-1], array[i-1]);
            rightMax[len-i] = Math.max(rightMax[len-i+1], array[len-i]);
        }

        for(int j = 0; j<array.length; j++){
            res += Math.max(Math.min(leftMax[j+1], rightMax[j])-array[j],0);
        }
        return res;

    }
    public static int trapWaterVolumnII(int[] array){
        int res = 0;
        int left = 0;
        int right = array.length-1;
        int leftMax = array[left];
        int rightMax = array[right];
        while(left < right-1){
            if(array[left]<array[right]){
                left++;
                if(array[left]<leftMax){
                    res += leftMax-array[left];
                }else{
                    leftMax = array[left];
                }
            }else{
                right--;
                if(array[right]<rightMax){
                    res += rightMax - array[right];
                }else{
                    rightMax = array[right];
                }
            }
        }
        return res;
    }

    static class Cell{
        public int x;
        public int y;
        public int value;
        Cell(int x, int y, int value ){
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    public static int trapWater3D(int[][] matrix){
        int res = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(m*n, new Comparator<Cell>() {
            @Override
            public int compare(Cell c1, Cell c2){
                if(c1.value == c2.value){
                    return 0;
                }
                return c1.value>c2.value?1:-1;
            }
        });

        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i<m; i++){
            minHeap.offer(new Cell(i, 0, matrix[i][0]));
            minHeap.offer(new Cell(i, n-1, matrix[i][n-1]));
            visited[i][0] = true;
            visited[i][n-1] = true;
        }
        for(int j = 1; j<n-1; j++){
            minHeap.offer(new Cell(0, j, matrix[0][j]));
            minHeap.offer(new Cell(m-1, j, matrix[m-1][j]));
            visited[0][j] = true;
            visited[m-1][j] = true;
        }

        while(!minHeap.isEmpty()){
            //using best first search

            Cell cur = minHeap.poll();

            List<Cell> neis = getNeis(matrix, cur);
            for(Cell nei : neis){
                if(!visited[nei.x][nei.y]){
                    minHeap.offer(nei);
                    visited[nei.x][nei.y] = true;
                    res += nei.value<cur.value?cur.value-nei.value:0;
                    //TODO: be careful about the boundary
                    nei.value = Math.max(nei.value, cur.value);
                }
            }
        }
        return res;
    }

    private static List<Cell> getNeis(int[][] matrix, Cell cell){
        List<Cell> res = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        if(cell.x+1<m){
            res.add(new Cell(cell.x+1,cell.y, matrix[cell.x+1][cell.y]));
        }

        if(cell.x-1>=0){
            res.add(new Cell(cell.x-1,cell.y, matrix[cell.x-1][cell.y]));
        }
        if(cell.y+1<n){
            res.add(new Cell(cell.x,cell.y+1, matrix[cell.x][cell.y+1]));
        }
        if(cell.y-1>=0){
            res.add(new Cell(cell.x,cell.y-1, matrix[cell.x][cell.y-1]));
        }
        return res;
    }


    public static void main(String[] args){
        int[] array = {1,3,6,4,2,5,3};
        int[] arrayI = {1,3,6,4,2,1,9,5,3};
        System.out.println(maxContainer(array));
        System.out.println(trapWaterVolumnI(arrayI));
        System.out.println(trapWaterVolumnII(arrayI));
        int[][] matrix  =  { {1,4,3,1,3,2},{3,2,1,3,2,4},{2,3,3,2,3,1} };
        System.out.println(trapWater3D(matrix));

    }

}
