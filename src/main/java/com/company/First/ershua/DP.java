package com.company.First.ershua;

public class DP {
    //frog jump
    public boolean canCross(int[] stones) {
        if(stones.length<2){
            return true;
        }
        if(stones[1]-stones[0] != 1){
            return false;
        }
        int n = stones.length;
        //from i can jump to end, j steps
        int[][] reach = new int[n][n];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++) {
                reach[i][j] = -1;
            }
        }
        return canCross(stones, 1,1, reach, n);
    }
    private boolean canCross(int[] stones, int i, int j, int[][] memo, int n){
        if(memo[i][j] != -1){
            return memo[i][j] == 1;
        }

        if(i==n-1){
            memo[i][j] = 1;
            return true;
        }


        for(int k = i+1; k<n; k++){
            if(stones[i]+j+1<stones[k] || stones[i]+j-1>stones[k]){
                continue;
            }else{
                if(canCross(stones, k, stones[k]-stones[i], memo, n)){
                    memo[i][j] = 1;
                    return true;
                }
            }
        }
        memo[i][j] = 0;
        return false;
    }


    //coins
    public int coinChange(int[] coins, int amount) {
        int[] numCoins = new int[amount+1];

        for(int i = 1; i<=amount; i++){
            numCoins[i] = Integer.MAX_VALUE;
            for(int j = 0; j<coins.length; j++){
                if(i-coins[j]>=0){
                    numCoins[i] = Math.min(numCoins[i], numCoins[i-coins[j]]+1);
                }
            }
        }
        return numCoins[amount] == Integer.MAX_VALUE? -1:numCoins[amount];
    }

    //return the number of ways to change coins
    static public int change(int amount, int[] coins) {
        int[][] ways = new int[coins.length][amount+1];
        //ways[0] = 1;
        for(int i = 0; i<coins.length; i++){
            for(int j = 1; j<=amount; j++){

            }
        }
        return 1;
    }
    public static void  main(String[] args){
        int[] t = {1, 2,5};
        System.out.println(change(5, t));
    }
}
