package com.company;

public class IfFormater {

    public static void ifFormater(int n){
        //using Dfs to solve it

        char[] array = new char[2*n];

        helper(0, n, n, array);

    }
    private static void helper(int level, int left, int right, char[] array){
        if(level == array.length){
            printer(array);
            return;
        }//base case

        if(left>0){//case one: adding a left '{'
            array[level] = '{';
            helper(level+1,left-1,right,array);
        }

        if(right>left){
            array[level] = '}';
            helper(level+1, left,right-1,array);
        }

    }
    public static void printer(char[] array){
        int space = 0;
        for(int i = 0; i<array.length;i++){
            if(array[i]== '{'){
                //print blank
                printSpace(space);
                System.out.println("if{");
                space+=2;
            }else{
                //reduce blank
                printSpace(space);
                System.out.println("}");
                space-=2;
            }
        }

    }

    private static void printSpace(int space) {
        for(int i = 0; i<space; i++){
            System.out.print(' ');
        }
    }

    public static void main(String[] args){
        ifFormater(2);
    }
}
