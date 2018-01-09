package com.company.First.OA;

public class AnagramOfBit {

    public boolean isAnagram(int num){

        int fast = num;
        int count = 0;
        while(fast>0){
            fast = fast>>1;
            count++;
        }
        System.out.println(num>>0);
        int left = count-1;
        int right = 0;
        while(left>right){
            int l = num>>left & 1;
            int r = num>>right & 1;
            //System.out.println(l);
            //System.out.println(r);
            if(l!=r){
                return false;
            }
            left--;
            right++;
        }
        return true;
    }
}
