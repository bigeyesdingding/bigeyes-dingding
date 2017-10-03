package com.company;

public class BitOperation {

    public static int setXtoOne(int x, int k){
        int bitSetter = 1;
        bitSetter = bitSetter<<k;
        x = x | bitSetter;
        return x;
    }
    public static int setXtoZero(int x, int k){
        int bitSetter = 1;
        bitSetter = bitSetter<<(k-1);
        bitSetter = ~bitSetter;
        x = x & bitSetter;
        return x;
    }
    /*
    * Q1: determine whether a number is a power of 2
    * Q2: determine how many different between two positive integers
    * Q3: suppose we assign a negative value to an unsigned int, what will happen?
    *   when we assign a negative number to an unsigned integer, the binary representation does not change.
    *   However, we will calculate the value of the unsigned integer by using the current binary representation.
    *   FOr example, int a = -1;
    *                unsigned int b = 1, then b = z^32-1
    * Q4: determine whether a word contains all letters that are unique (no duplicate letters in the word)
    * Q5: how to reverse all bits of a number( 翻转)
    * Q6: given a number x. how to get the hexadecimal representation of the number in string type
    *
    *
    * */
    public static boolean isPowerOfTwo(int number){
        //m1: right shift unsigned, return number==1
        if(number <= 0){
            return false;
        }
        while((number&1) == 0){
            number >>>= 1;
        }
        return number == 1;

    }

    public static boolean isPowerOfTwoII(int number){
        //m2: counting all ones
        if(number <= 0){
            return false;
        }
        int count =0;
        while(number>0){
            count = count + (number&1);
            number >>>= 1;
        }
        return count == 1;
    }
    public static boolean isPowerOfTwoIII(int number){
        //m3: n&(n-1)
        return (number > 0) && ((number & (number-1)) == 0);
    }

    public static int differentBits(int a, int b){
        //after exclusive or, only the bits where a abd b are different will be 1
        a^=b;
        int count = 0;
        while(a != 0){
            count += a&1;
            a >>>= 1;
        }
        return count;
    }


    public static boolean containsUniqueLetters(String input){
        /*
        * we are using the ASCII encoding the number of valid letters is 256, encoded from 0 to 255
        * the input word is not null
        *
        * each int value can represent 32 bit, so we need 8 ints to represent 256 bits.
        *
        * */

        int[] vec = new int[8];
        char[] array = input.toCharArray();
        for(char c : array){
            //for a value c in the range of 256, the row index is c/32, the column index is c%32
            //we only need to check the value of vec[c/32][c%32], to determine if it is already in the input string
            if(((vec[c/32]>>>c%32) & 1) == 1){
                return false;
            }
            vec[c/32] |= 1 << (c%32);
        }
        return true;
    }
    public static int swapPair(int num){
        for(int offset = 0; offset <16;++offset){
            int right = (num>>offset) &1;
            int left = (num >>(31-offset)) & 1;
            if(left != right){
                num ^= (1<<offset);
                num ^= (1<<(31-offset));
            }
        }
        return num;
    }
    public static String hexadecimalString(int number){
        // assumptions: number >=0
        String prefix = "0x";
        //handle the special case of 0 first.
        if(number == 0){
            return prefix+ "0";
        }
        //using StringBuilder so append operation is moreefficient
        StringBuilder sb = new StringBuilder();
        while(number>0){
            int rem = number % 16;
            if(rem <10){
                sb.append((char) ('0' +rem));

            } else {
                sb.append((char)(rem-10)+'A');
            }
            number >>>= 4;
        }
        //reverse it at last so in all complexity is o(n);
        return prefix + sb.reverse().toString();
    }

    public static void main(String[] args){
        System.out.println(containsUniqueLetters("abcdefag"));
        System.out.println(hexadecimalString(1314));
    }
}
