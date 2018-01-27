package com.company.First.ershua;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ArrayR {

    //check whether two strings are one edit distance away
    public boolean oneEditDis(String s1, String s2){
        if(Math.abs(s1.length()-s2.length())>1){
            return false;
        }
        int i = 0;
        while(i<s1.length() && i<s2.length() && s1.charAt(i)== s2.charAt(i) ){
            i++;
        }
        return compare(s1, i+1, s2, i+1 ) || compare(s1, i, s2, i+1) || compare(s1, i+1, s2, i);

    }

    private boolean compare(String s1, int i, String s2, int j){
        while(i<s1.length() && j<s2.length() && s1.charAt(i) == s2.charAt(j)){
            i++;
            j++;
        }
        return i==s1.length() && j == s2.length();
    }

    //in-place to do it
    public void gameOfLife(int[][] board) {
        if(board== null || board.length == 0 || board[0].length == 0){
            return;
        }
        int m = board.length;
        int n = board[0].length;
        //count live neighbors and add  new status
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                int num = count(m,n, board, i, j);
                if(board[i][j] == 1 && (num==2 || num ==3) ){
                    board[i][j] = 3;
                }else if(board[i][j] == 0 && (num == 3)){
                    board[i][j] = 2;
                }
            }
        }
        //change status
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                board[i][j] = board[i][j] >>1;
            }
        }
    }


    int[][] dirs = new int[][]{{0,1}, {0,-1},{-1, 0},{1,0}, {1,1},{1,-1},{-1,1},{-1,-1}};
    private int count(int m, int n, int[][] board, int i, int j){
        int count = 0;
        for(int x= 0;x<dirs.length; x++){
            int ii = i+dirs[x][0];
            int jj = j + dirs[x][1];

            if(ii>=0 && ii<m && jj>=0 && jj<n){
                if((board[ii][jj] & 1) == 1){
                    count++;
                }
            }
        }
        return count;
    }


    //f2: infinite
    //signiture: in a set of live, out a set of live

    public void gameOfLifeII(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        Set<int[]> live = new HashSet<>();
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(board[i][j] == 1){
                    live.add(new int[]{i,j});
                }
            }
        }
        live = getLive(live);

        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                int[] cur = new int[]{i, i};
                if(live.contains(cur)){
                    board[i][j] = 1;
                }
            }
        }
    }

    public Set<int[]> getLive( Set<int[]> live){
        Map<int[], Integer> map = new HashMap<>();

        for(int[] l: live){
            for(int i = -1; i<dirs.length; i++){
                int ii = dirs[i][0] + l[0];
                int jj = dirs[i][1] + l[1];
                int[] cur = new int[]{ii, jj};
                if(live.contains(cur)){
                    map.put(cur, map.getOrDefault(cur, 0)+1);
                }
            }
        }
        Set<int[]> res = new HashSet<>();
        for(Map.Entry<int[], Integer> e: map.entrySet()){
            if(e.getValue()==2 || e.getValue()== 3 && live.contains(e.getKey())) {
                res.add(e.getKey());
            }else if(e.getValue()== 3 && !live.contains(e.getKey())){
                res.add(e.getKey());
            }
        }
        return res;
    }


}
