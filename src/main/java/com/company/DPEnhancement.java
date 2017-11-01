package com.company;

public class DPEnhancement {
    /*you have two arrays formed with integers from 0 - 9
*find a combination of k integers from the two arrays, which can make the combination number maxmum
*all integers int the combination must keep the original order in the original two arrays
*/
    public static int kCombination(int[] a, int[] b, int k){

        if(a == null || a.length== 0){
            return maxK(b, k);
        }
        if(b == null || b.length == 0){
            return maxK(a, k);
        }
        if(a.length+b.length<k){
            return Integer.MIN_VALUE;
        }
        //aMax[i][j] represents the max i elements in the range of index 0 to index j-1 of array a;
        //bMax[i][j] represents the max i elements in the range of index 0 to index j-1 of array b;
        int[][] aMax = fillMaxBoard(a, Math.min(k, a.length));
        int[][] bMax = fillMaxBoard(b, Math.min(k, b.length));

        int result = Integer.MIN_VALUE;

        for(int x = 0; x<=k; x++){
            if(x<=a.length && k-x<=b.length){
                result = Math.max(maxCombine(aMax[x][a.length], bMax[k-x][b.length]), result);
            }
        }
        return result;
    }

    private static int[][] fillMaxBoard(int[] array, int k){
        if(array == null || array.length<k){
            return null;
        }
        int alen = array.length;

        int[][] maxk = new int[k+1][alen+1];
        for(int i = 0; i<=k; i++){
            for(int j = i; j<=alen; j++){
                if(i == 0){
                    maxk[i][j] = 0;
                }else{
                    maxk[i][j] = i == j? maxk[i-1][j-1]*10 + array[i-1]: Math.max(maxk[i-1][j-1]*10 + array[i-1],maxk[i][j-1] );
                }
            }
        }
        return maxk;

    }

    private static int maxK(int[] array, int k){
        if(array == null || array.length<k){
            return Integer.MIN_VALUE;
        }
        int[][] max = fillMaxBoard(array, k);
        return max[k][array.length];
    }
    private static int[] toArray(int x){
        String str = Integer.toString(x);
        int len = str.length();

        int[] res = new int[len];
        for(int i = 0; i<len; i++){
            res[i] = str.charAt(i) -'0';
        }
        return res;
    }

    private static int maxCombine(int as, int bs){

        int[] a = toArray(as);
        int[] b = toArray(bs);
        if(a == null || a.length ==0){
            return toInteger(b);
        }
        if(b == null || b.length == 0){
            return toInteger(a);
        }
        int al = a.length;
        int bl = b.length;

        int[][] max = new int[al+1][bl+1];
        for(int i = 0; i<=al; i++){
            for(int j = 0; j<=bl; j++){
                if(i == 0 || j == 0){
                    max[i][j] = 0;

                }else{
                    max[i][j] = Math.max(max[i][j-1]*10+b[j-1], max[i-1][j]*10+a[i-1]);
                }
            }
        }
        return max[al][bl];
    }

    private static int toInteger(int[] array){
        if(array == null || array.length==0){
            return Integer.MIN_VALUE;
        }
        int res = 0;
        for(int e : array){
            res = res * 10+ e;
        }
        return res;
    }


    public static void main(String[] args){
        int[] a = {1,8,7,6,5,9,1,3,4};
        int[] b = {2,8,7,7,5,4,2};
        System.out.println(kCombination(a, b, 3));
    }


}
