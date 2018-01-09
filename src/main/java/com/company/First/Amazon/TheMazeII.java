package com.company.First.Amazon;

public class TheMazeII {
    int min = Integer.MAX_VALUE;
    public int shortestPath(int[][] input, int[] start, int[] end){
        helper(input, start, end, null,0, new boolean[input.length][input[0].length] );
        return min;
    }
    private void helper(int[][] input, int[] cur, int[] end, Character dir, int steps, boolean[][] visited){
        if(cur[0] == end[0] && cur[1] == end[1] && stop(dir, cur, input) ){
            min= Math.min(min, steps);
            return;
        }




    }



}
