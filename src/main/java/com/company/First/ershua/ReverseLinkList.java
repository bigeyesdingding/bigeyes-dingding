package com.company.First.ershua;

import com.company.First.ListNode;

public class ReverseLinkList {
    public ListNode reverse(ListNode head){
        if(head == null){
            return null;
        }

        ListNode prev = null;
        while(head!=null){
            ListNode next = head.next;
            head.next= prev;

            head = next;
            prev = head;
        }
        return prev;
    }
    public ListNode reverseII(ListNode head){
        if(head == null){
            return null;
        }else if(head.next == null){
            return head;
        }

        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next= null;
        return newHead;
    }
}
