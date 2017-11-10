package com.company.Again;

public class Array {
    public static void main(String[] args){
        System.out.println("Array test");
    }
}
class FirstNonApearInt{

}
class SetMatrixZero{
    public void solution(int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;
        boolean flag = false;
        for(int i = 0; i<n;i++){
            if(matrix[0][i] == 0){
                flag = true;
                break;
            }
        }
        for(int i = 1; i<m;i++) {

            for (int j = 0; j <n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                }
                if (j !=0) {
                    matrix[i][j] = matrix[i][j - 1] == 0 ? 0 : matrix[i][j];
                }
            }
            for (int j = n - 1; j >= 0; j--) {
                if (j != n - 1) {
                    matrix[i][j] = matrix[i][j + 1] == 0 ? 0 : matrix[i][j];
                }
            }
        }
        for(int j = 0; j<n;j++){
            if(matrix[0][j]==0){
                for(int i = 0; i <m; i++){
                    matrix[i][j] = 0;
                }
            }
        }
        if(flag){
            for(int i = 0; i<n; i++){
                matrix[0][i] = 0;
            }
        }

    }
}
