package com.company.Again;

import com.company.First.ListNode;

public class LinkedList {

    public ListNode removeLastK(ListNode head, int k){
        if(head == null){
            return null;
        }

        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode res = newHead;
        int i = 1;
        while(head != null){
            while(i < k){
                head = head.next;
                i++;
            }
            res.next = head;
            head = head.next;
        }
        res.next = res.next.next;
        return newHead.next;
    }
}
