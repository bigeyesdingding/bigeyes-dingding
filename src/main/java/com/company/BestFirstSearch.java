package com.company;

import java.util.*;



public class BestFirstSearch {

    static class Pair{
        public int x = 0;
        public int y = 0;
        Pair( int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static int smallestProductionK(int k){

        if(k<1){
            return -1;
        }
        //setting the start point of the BFS
        int seed = 3*5*7;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(seed);
        Set<Integer> visited = new HashSet<>();
        while(k>1){
            //expand cur and generate 3 neibors
            int cur = minHeap.poll();
            if(visited.add(3*cur)){
                minHeap.offer(3*cur);
            }
            if(visited.add(5*cur)){
                minHeap.offer(5*cur);
            }
            if(visited.add(5*cur)){
                minHeap.offer(5*cur);
            }
            k--;
        }
        return minHeap.peek();
    }

    //TODO: It's very important to mark the visited!!!!
    public static long closestKDistance(final int[] a, final int[] b, final int[] c, int k){
        //assume: k >= 1

        PriorityQueue<List<Integer>> pq = new PriorityQueue<>(2*k,new Comparator<List<Integer>>(){
        @Override
        public int compare(List<Integer> l1, List<Integer> l2){
            long d1 = distance(l1, a, b, c);
            long d2 = distance(l2, a, b, c);
            if(d1 == d2){
                return 0;
            }
            return d1>d2?1:-1;
        }
		});

        List<Integer> start = Arrays.asList(0,0,0);
        pq.offer(start);
        List<Integer> cur;
        boolean[][][] visited = new boolean[a.length][b.length][c.length];
        visited[0][0][0] = true;

        while(k>1 ){
            cur = pq.poll();
            int x = cur.get(0);
            int y = cur.get(1);
            int z = cur.get(2);

            if(x+1<a.length && !visited[x+1][y][z]){
                pq.offer(Arrays.asList(x+1, y, z));
                visited[x+1][y][z] = true;
            }
            if(y+1<b.length && !visited[x][y+1][z]){
                pq.offer(Arrays.asList(x, y+1, z));
                visited[x][y+1][z] = true;
            }
            if(z+1<c.length &&!visited[x][y][z+1]){
                pq.offer(Arrays.asList(x, y, z+1));
                visited[x][y][z+1] = true;
            }
            k--;
        }
        System.out.println(pq.peek().toString());
        return distance(pq.peek(), a, b, c);

    }

    private static long distance(List<Integer> list, int[] a, int[] b, int[] c){
        long res = 0;
        res += a[list.get(0)]*a[list.get(0)];
        res += b[list.get(1)]*b[list.get(1)];
        res += c[list.get(2)]*c[list.get(2)];
        return res;
    }


    	/*Put a chair into a two diemnsion matrix
	* 1.the cost from one cell to any of its neighbors(up/down/left/right) is 1
	* 2. 'E' denotes equipments, 'O'denotes obstacle, 'C' denotes empty cell
	* 3. the chair can not be put on equipment or obstacle
	* 4. each 'C' cell is reachable from all 'E' cells
	*


	*/

    public static List<Integer> addingChair(char[][] gym){
        //assume: gym is not null, has size M*N
        //where M>=1, N >=1
        //return null if you cannot find a place to put the chair
        //there is at least one equipment in the gym
        int M = gym.length;
        int N = gym[0].length;


        //calculate the cost of each equipment to any other reachable cell
        int[][] cost = new int[M][N];
        for(int i = 0; i<M;i++){
            for(int j = 0; j<N;j++){
                //if the addCost returns false, means there is no place to put
                //the chair to make it reachable to any other equipment, return null
                if(gym[i][j] == 'E' && !addCost(cost, i, j, gym)){
                    return null;
                }

            }
        }

        //go through all the cell and find out the smallest cost cell, return it
        List<Integer> res = null;
        for(int i = 0; i<M; i++){
            for(int j = 0; j<N; j++){
                if(gym[i][j] != 'E' && gym[i][j] != 'O'){
                    if(res == null){
                        res = Arrays.asList(i, j);
                    } else if( cost[i][j]<cost[res.get(0)][res.get(1)] ){
                        res.set(0, i);
                        res.set(1, j);
                    }

                }
            }


        }
        return res;


    }
    private static boolean addCost(int[][] cost, int row , int col, char[][] gym){
        //using Breadth-First-Search to search each i-j, add the cost to the input cost array
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(row, col));
        boolean[][] visited = new boolean[gym.length][gym[0].length];
        visited[row][col] = true;

        int pathCost = 1;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i<size; i++){
                Pair cur = q.poll();
                List<Pair> neis = getNeis(cur, gym);
                for(int j = 0; j <neis.size(); j++){
                    Pair nei = neis.get(j);
                    if(!visited[nei.x][nei.y]){
                        visited[nei.x][nei.y] = true;
                        cost[nei.x][nei.y] += pathCost;
                        q.offer(nei);
                    }
                }
            }
            pathCost++;
        }

        for(int i = 0; i<gym.length;i++){
            for(int j = 0; j<gym.length; j++){
                if(gym[i][j] == 'E' && !visited[i][j]){
                    return false;
                }
            }

        }
        return true;
    }
    private static List<Pair> getNeis(Pair cur, char[][] gym){
        List<Pair> res = new ArrayList<>();
        if(cur.x+1<gym.length && gym[cur.x+1][cur.y]!='O'){
            res.add(new Pair(cur.x+1, cur.y));
        }
        if(cur.x-1>=0 && gym[cur.x-1][cur.y]!='O'){
            res.add(new Pair(cur.x-1, cur.y));
        }
        if(cur.y+1<gym[0].length && gym[cur.x][cur.y+1]!='O'){
            res.add(new Pair(cur.x, cur.y+1));
        }
        if(cur.y-1>=0 && gym[cur.x][cur.y-1]!='O'){
            res.add(new Pair(cur.x, cur.y-1));
        }
        return res;
    }

    public static void main(String[] args){
        int[] a = {1,3,5,7,9};
        int[] b = {2,5,9,10,11};
        int[] c = {2,6,7,8,9};

        closestKDistance(a,b,c,120);
    }
}
