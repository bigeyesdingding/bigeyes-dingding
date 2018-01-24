package com.company.First.ershua;

import com.company.First.ListNode;

public class MiddleNodeOfLinedList {
    public ListNode find(ListNode head){

        ListNode fast = head;
        ListNode slow = head;
        while(fast!=null && fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
