package com.company;

public class MyLinkedList {
    public static ListNode reverseLinkedList(ListNode head){
        if(head == null || head.next==null){
            return head;
        }

        ListNode prev = null;
        ListNode cur = head;
        ListNode next = null;



        while(cur != null){
            next = cur.next;
            cur.next = prev;
            cur = next;

        }
        return prev;
    }

    public static ListNode reverseLinkedListRecursive(ListNode head){
        if(head == null || head.next==null){//base case
            return head;
        }

        ListNode next = head.next;
        ListNode newHead = reverseLinkedListRecursive(next);
        next.next = head;
        head.next = null;

        return newHead;
    }

    public static ListNode middleNode(){
        if(head == null){
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;

        }
        return slow;

    }


    //check if has cycle in an linkedlist
    public static boolean hasCycle(){
        if(head == null){
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow){
                return true;
            }

        }
        return slow;

    }


    //insert into a sorted LinkedList
    public static ListNode insertion(){


    }

    public static void main(String[] args){
        System.out.println("This is my customized Linked-list");
        System.out.println("Some operations are re-designed by myself. Enjoy....");
    }


}
