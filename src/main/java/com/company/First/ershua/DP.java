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
    public int change(int amount, int[] coins) {
        int[] ways = new int[amount+1];
        for(int i = 1; i<=amount; i++){
            for(int j = 0; j<coins.length; j++){
                if(i-coins[j]>=0){
                    ways[i] += coins[i-coins[j]];
                }
            }
        }
        return ways[amount];
    }
}
