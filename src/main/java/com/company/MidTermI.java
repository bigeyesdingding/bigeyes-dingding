package com.company;

import java.util.*;

public class MidTermI {
    /*
    * Q1: reverse a linked list, iterative + recursive
    * Q2: how to judge whether a binary tree is a binary search tree? Assume: the data type is integer
    * Q3: Given a string with duplicate letters, how to print out all permutations of the string. Write runable
    *     program with test cases and output
    * Q4: remove empty spaces
    *     ___I_am_a___student___ --> I_am_a_student
    * Q5: Given two sorted arrays A and B, sizes m and n. We can pick one element a from A and the other b from B
    *     and their sum is defined to be s = a+b. How to find the k-th smallest s form all possible values of s.
    *     A[m] = {1,4,6,8,10....}
    *     B[n] = {1,4,5,7,8,....}
    */
    public static ListNode reverseLinkedList(ListNode head){
        //o(n) time, o(1) space
        if(head == null){
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    public static ListNode reverseLinkedListII(ListNode head){
        //o(n) time, o(n) extra space
        if(head == null || head.next == null){
            return head;
        }
        //new head is the new head of reverse list
        ListNode newHead = reverseLinkedListII(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static boolean isBST(TreeNode root, int min, int max){
        //base case
        if(root == null){
            return true;

        }
        //check value in current node
        if(!(min < root.value && root.value < max)){
            return false;
        }

        return isBST(root.left,min,root.value)
                && isBST(root.right, root.value,max);
    }
    public static boolean isBST(TreeNode root){
        //assumption: the tree has no node with value of Integer max and min
        //BST contains no duplicate value
        //o(n)times, o(should be n)
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static void permute(char[] input, int index, List<String> result){
        //base case
        if(index == input.length){
            result.add(new String(input));
            return;

        }

        //record the kinds of chars that has been selected in cur level
        Set<Character> selected = new HashSet<>();
        for(int i = index; i<input.length; i++){
            //if this kind of hcar has  not been selected in cur level
            if(!selected.contains(input[i])){
                selected.add(input[i]);
                //select this char
                swap(input,i,index);
                permute(input, index+1,result);
                //recover the state
                swap(input, i, index);

            }
        }

    }
    public static List<String> permutation(String input){
        //o(n!) times, o(n^2) extra space, n is the length of the input
        List<String> result = new ArrayList<>();
        // corner case
        if(input == null){
            return result;

        }
        permute(input.toCharArray(),0,result);
        return result;
    }

    private static void swap(char[] input, int i, int index) {

    }

    public static List<String> permute(String input){


    }
    public static String removeSpace(String input){
        if(input == null || input.length() == 0){
            return input;
        }
        char[] array = input.toCharArray();
        int slow = 0;
        for(int i = 0; i<array.length; i++){
            if(array[i] == ' ' && (i ==0|| array[i-1] == ' ')){
                continue;
            } else {
                array[slow++] = array[i];
            }

            if(slow > 0 && array[slow-1] == ' '){
                return new String(array, 0, slow-1);
            }
            return new String(array, 0, slow);
        }


    }



    public static void main(String[] args){

    }
}
