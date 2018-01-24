package com.company.First.OA.Amazon;

import java.util.PriorityQueue;

public class TheMazeII {

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {

        int m =maze.length;
        int n = maze[0].length;
        int[][] length = new int[m][n];
        for(int i = 0; i<m*n; i++){
            length[i/n][i%n] = Integer.MAX_VALUE;
        }

        int[][] dir = new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
        PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2)->Integer.compare(i1[2],i2[2]));
        pq.add(new int[] {start[0], start[1], 0});
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            if(cur[2]>=length[cur[0]][cur[1]]){
                continue;
            }
            length[cur[0]][cur[1]] = cur[2];
            for(int i = 0; i<dir.length; i++){
                int xx = cur[0], yy = cur[1], l = cur[2];
                while(xx>=0 && xx<m&& yy<n && yy>=0 && maze[xx][yy] == 0){
                    xx += dir[i][0];
                    yy += dir[i][1];
                    l++;
                }

                xx-= dir[i][0];
                yy -= dir[i][1];
                l--;
                pq.offer(new int[]{xx, yy, l});
            }
        }
        return length[destination[0]][destination[1]]==Integer.MAX_VALUE?-1:length[destination[0]][destination[1]];
    }

    public int shortestDistanceII(int[][] maze, int[] start, int[] destination) {

        int m =maze.length;
        int n = maze[0].length;
        int[][] length = new int[m][n];
        for(int i = 0; i<m*n; i++){
            length[i/n][i%n] = Integer.MAX_VALUE;
        }
        dfs(length, maze, start[0], start[1], destination);
        return length[destination[0]][destination[1]]==Integer.MAX_VALUE?-1:length[destination[0]][destination[1]];
    }

    int[][] dir = new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
    private void dfs(int[][] length, int[][] maze, int x, int y, int[] end){
        if(x== end[0] && y == end[1]){
            return;
        }


        for(int i = 0; i<dir.length; i++){
            int xx = x, yy = y;
            int dist = length[xx][yy] == Integer.MAX_VALUE? 0 : length[xx][yy];
            System.out.println(length[xx][yy]);
            while(xx>=0 && xx<length.length&& yy>=0 && yy<length[0].length && maze[xx][yy] == 0 ){
                xx += dir[i][0];
                yy += dir[i][1];
                dist++;
            }

            xx -= dir[i][0];
            yy -= dir[i][1];
            dist --;
            if(dist<length[xx][yy]){
                length[xx][yy] = dist;
                dfs(length, maze, xx, yy, end);
            }

        }

    }

    public static void main(String args[]){

    }

}


class TheMazeIII {

    class Point implements Comparable<Point> {
        int x,y,l;
        String s;
        public Point(int _x, int _y) {x=_x;y=_y;l=Integer.MAX_VALUE;s="";}
        public Point(int _x, int _y, int _l,String _s) {x=_x;y=_y;l=_l;s=_s;}
        public int compareTo(Point p) {return l==p.l?s.compareTo(p.s):l-p.l;}
    }
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        if(maze == null){
            return "";
        }
        int m = maze.length;
        int n = maze[0].length;
        Point[][] points = new Point[m][n];
        for(int i = 0; i<m*n; i++){
            points[i/n][i%n] = new Point(i/n, i%n);
        }
        int[][] dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        String[] str = new String[]{"r", "l", "d", "u"};
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(ball[0], ball[1], 0, ""));
        //points[ball[0]][ball[1]].l= 0;
        while(!pq.isEmpty()){
            Point cur = pq.poll();
            if(points[cur.x][cur.y].compareTo(cur)<=0){
                continue;
            }
            points[cur.x][cur.y] = cur;
            for(int i = 0; i<dir.length; i++){
                int xx = cur.x;
                int yy = cur.y;
                int l = cur.l;
                while(xx<m && xx>=0 && yy<n && yy>=0 && maze[xx][yy] == 0 && (xx!= hole[0] || yy!=hole[1])){
                    xx+= dir[i][0];
                    yy += dir[i][1];
                    l++;
                }
                if(xx!=hole[0] || yy!=hole[1]){
                    xx -= dir[i][0];
                    yy -= dir[i][1];
                    l--;
                }
                pq.offer(new Point(xx, yy, l, cur.s+str[i]));
            }
        }
        return points[hole[0]][hole[1]].l == Integer.MAX_VALUE? "impossible": points[hole[0]][hole[1]].s;
    }
}

