package com.company.First;

public class DecompressString {
    public static String decompressI(String input){
        if(input.isEmpty()){
            return input;
        }
        char[] array = input.toCharArray();
        //consider two different cases
        //typeA: a0,a1,a2 case(the decoded string is shorter)
        //typeB: a3,a4,a5..(the decoded string is longer
        return decodeLong(array, decodeShort(array));
    }
    public static int  decodeShort(char[] input){
        //work from left to right
        int end = 0;
        for(int i = 0; i<input.length; i +=2){
            int digit = getDigit(input[i+1]);
            if(digit>=0 && digit<=2){
                for(int j =0; j<digit;j++){
                    input[end++] = input[i];
                }


            } else {
                //in this method, we don't take care of this case
                input[end++] = input[i];
                input[end++] = input[i+1];
            }
        }
        System.out.println(end);
        return end;
    }

    //the decode string is longer
    private static String decodeLong(char[] input, int length){
        int newLength = length;
        for(int i = 0; i<length;i++){
            int digit = getDigit(input[i]);
            if(digit>2 && digit<=9){
                newLength += digit-2;
            }
        }

        char[] result = new char[newLength];
        int end = newLength-1;
        for(int i = length-1; i>=0;i--){
            int digit = getDigit(input[i]);
            if(digit>2 && digit<=9){
                i--;
                for(int j = 0; j<digit;j++ ){
                    result[end--] = input[i];
                }
            } else {
                //we have already take the shorter case
                result[end--] = input[i];
            }
        }
        return new String(result);
    }

    private static int getDigit(char digit){
        return digit - '0';
    }


    //method2: using string builder to do it
    public static String decompressII(String input){
        //assumption: input is not null
        char[] array = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<array.length; i++ ){
            char ch =array[i++];
            int count = array[i] - '0';
            for(int c = 0; c<count;c++){
                sb.append(ch);
            }
        }
        return sb.toString();
    }


    public static void main(String[] args){
        System.out.println("Try to decompress a string");
        /*
        * Assumption:
        * 1.the string is not null
        * 2.the characters used are guranteed to be 'a' to 'z'
        * 3.there are no adjacent repeated characters with length>9
        * */
        System.out.println(decompressI("a3b0c2"));
        System.out.println(decompressII("a3b0c2"));
    }
}
