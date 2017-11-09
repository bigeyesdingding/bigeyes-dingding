package com.company.First;

public class ListNode {

    /*
        ListNode is a class for storing a single node of a linked list storing
        integer values.  It has two public data fields for the data and the link to
        the next node in the list and has three constructors:
        1.public ListNode()
        creates node with data 0, null link
        2.public ListNode(int data)
        creates node with given data, null link
        3.public ListNode(int data, ListNode next)
        creates node with given data and given link
    */

        public int value;       // value stored in this node
        public ListNode next;  // link to next node in the list

        // post: constructs a node with data 0 and null link
        public ListNode() {
            value = 0;
            next = null;
        }

        // post: constructs a node with given data and null link
        public ListNode(int val) {
            value = val;
            next = null;
        }

        // post: constructs a node with given data and given link
        public ListNode(int val, ListNode nex) {
            value = 0;
            next = nex;
        }



    public static void main(String[] args){
        ListNode node = new ListNode(3);
        node.next = new ListNode(5);
        System.out.println(node.value);
        System.out.println(node.next.value);


    }
}
