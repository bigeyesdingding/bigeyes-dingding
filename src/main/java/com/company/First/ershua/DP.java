package com.company.First.ershua;

public class DP {
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
    }
    public static void  main(String[] args){
        int[] t = {1, 2,5};
        System.out.println(change(5, t));
    }
}
