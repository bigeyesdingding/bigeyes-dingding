package com.company.First;

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

    public static void permute(String input){

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


        }
        if(slow > 0 && array[slow-1] == ' '){
            return new String(array, 0, slow-1);
        }
        return new String(array, 0, slow);


    }

    public static int kthSmallestSum(int[] A, int[] B, int k){
        /*
        * 1.initial state a[0] + b[0]
        * 2.expansion/generation rule:
        *   expand ij -> genarate i+1, j
        *                genarate i, j+1
        * 3.termination condition
        * when the k-th pair is poppes outof the min-heap, we can avoid maintain a nxn matrix explicitly
        * In fast, we only need a space for a heap, rather than a nXn matrix
        * Time = o(klog(k))
        *
        *
        * */
        // a max heap contains 1st smallest to k-th smallest number
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k,
                new Comparator<Integer>(){
            @Override
                    public int compare(Integer i1, Integer i2){
                if(i1.equals(i2)){
                    return 0;
                }
                return i1>i2?-1:1;
            }
                });
        for(int i = 0; i<A.length;i++){
            for(int j = 0; j<B.length; j++){
                int sum = A[i]+ B[j];
                //fill the maxHeap when less than k
                //replace the top of the heap when needed
                if(maxHeap.size()<k){
                    maxHeap.offer(sum);

                } else if(sum<maxHeap.peek()){
                    maxHeap.poll();
                    maxHeap.offer(sum);
                }
            }

        }
        return maxHeap.poll();
    }

    public static int kthSmallestSumII(int[] A, int[] B, int k){
        /*using BFS@, time is o(klogk)
        * */
        PriorityQueue<Cell> maxHeap = new PriorityQueue<>(2,
                new Comparator<Cell>(){
                    @Override
                    public int compare(Cell c1, Cell c2){
                        int sum1 = Cell.A[c1.aIdx] + Cell.B[c1.bIdx];
                        int sum2 = Cell.A[c2.aIdx] + Cell.B[c2.bIdx];
                        if(sum1 == sum2){
                            return 0;
                        }
                        return sum1<sum2?-1:1;
                    }
                });
        maxHeap.offer(new Cell(0,0)); //initial state
        boolean[][] visited = new boolean[A.length][B.length];
        visited[0][0] = true;
        for(int i = 0; i<k-1; i++){
            Cell temp = maxHeap.poll();
            if(temp.aIdx+1<A.length && !visited[temp.aIdx+1][temp.bIdx]){
                visited[temp.aIdx+1][temp.bIdx] = true;
                maxHeap.offer(new Cell(temp.aIdx+1,temp.bIdx));

            }
            if(temp.bIdx+1<B.length && !visited[temp.aIdx][temp.bIdx+1]){
                visited[temp.aIdx][temp.bIdx+1] = true;
                maxHeap.offer(new Cell(temp.aIdx,temp.bIdx+1));

            }
        }
        Cell result = maxHeap.poll();
        return A[result.aIdx]+B[result.bIdx];

    }

    public static void main(String[] args){
        System.out.println(removeSpace("  I am   a        student.   "));
    }
}
