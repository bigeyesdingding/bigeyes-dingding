package com.company;

public class ReverseString {
    public void swap(char[] array, int l, int r){
        int temp = array[l];
        array[l] = array[r];
        array[r] = temp;
    }

    public String reverse(String input){
        if(input== null || input.length()<=1){
            return input;
        }
        char[] array = input.toCharArray();
        for(int l = 0, r = array.length-1;l<r;l++, r--){
            swap(array, l, r);
        }
        return new String(array);
    }
    public void reverseRecursive(char[] array, int l, int r){
        if(l>=r){
            return;//base case
        }
        swap(array, l,r);
        reverseRecursive(array,l+1,r-1);
    }

    public String reverseWords(String input){
        /*
        * Assumptions
        * 1' the words are separated by one space character
        * 2.there are no leading or tailing spaces
        * 3.input is not null
        * However, I am gonna to write a general one
        * */
        //try to convert it to char array and sole the problem in-place
        char[] array = input.toCharArray();
        //0.pre-processing
        int slow =0;
        int fast = 0;
        int wordCount = 0;
        while(true){
            while(fast<array.length && array[fast]==' '){
                fast++;
            }
            if(fast>=array.length){
                break;
            }
            if(wordCount>0){
                array[slow++]=' ';
            }
            while(fast< array.length && array[fast]!= ' '){
                array[slow++]= array[fast++];
            }
        }
        //1.reverse the whole char array
        helper(array, 0, array.length-1);

        //2.reverse every word
        int start=0;
        for(int i=0;i<array.length;i++){
            //start of the world
            if(array[i]!=' '&&(i ==0 || array[i-1]==' ')){
                start = i;
            }
            //find end
            if(array[i]!=' ' && (i==array.length-1 || array[i+1]==' ')){
                helper(array, start,i);
            }
        }
        return new String(array);

    }
    public void helper(char[] array, int left, int right){
        while(left<right){
            char temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }

}
