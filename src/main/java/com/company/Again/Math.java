package com.company.Again;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class Math {
    public static void main(String[] args){
    }
}

class KthPermutation{
    public String solution(int n, int k){
        //store factorial res
        int[] factorial = new int[n+1];
        factorial[0] = 1;
        for(int i = 1; i<=n; i++){
            factorial[i] = factorial[i-1]*i;
        }
        if(k>factorial[n]){
            return null;
        }
        List<Integer> list = new ArrayList<Integer>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<n; i++){
            list.add(i+1);
        }
        for(int i =1; i<=n; i++){
            int index = (k-1)/factorial[n-i];
            sb.append(list.get(index));
            list.remove(index);
            k = k - factorial[n-i]*index;
        }
        return sb.toString();
    }

}
