package com.company.First;

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

    public static ListNode middleNode(ListNode head){
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
    public static boolean hasCycle(ListNode head){
        if(head == null){
            return false;
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
        return false;
    }

    //insert into a sorted LinkedList
    public static ListNode insertion(ListNode head, int value){
        ListNode newNode = new ListNode(value);
        if(head == null || head.value>=value){
            newNode.next = head;
            return newNode;
        }

        ListNode prev = head;
        while(prev.next!=null && prev.next.value<value){
            prev = prev.next;
        }
        newNode.next = prev.next;
        prev.next = newNode;

        return head;

    }

    //merge two sorted linked-list together
    public static ListNode merge(ListNode one, ListNode two){
        ListNode head = new ListNode(0);
        ListNode cur = head;

        while(one.next!=null && two.next!=null){
            if(one.value<two.value){
                cur.next = one;
                one = one.next;

            }else{
                cur.next = two;
                two = two.next;

            }
            cur = cur.next;
        }

        if(one != null){
            cur.next = one;
        }else{
            cur.next = two;
        }

        return head.next;
    }


    //n1-n2-n3-...-n(n-1)-nn-null
    //(n1-nn)-(n2-n(n-1))-...n
    public static ListNode reverseMerge(ListNode head){
        /*
          s1: find the middle node and break the linkedlist into teo seperate one
          s2: reverse the second one
          s3: merge these two linkedlist
        */

        if(head==null || head.next ==null){
            return head;
        }

        //1 find the miidel node;
        ListNode middle = middleNode(head);
        //de-link the second half
        ListNode one = head;
        ListNode two = middle.next;
        middle.next =  null;

        //reverse the second half
        ListNode newTwo = reverseLinkedList(two);
        return head;

        //merge these two


    }

    //partition, smaller ones are on the left side, bigger ones are all on the right side
    //1-6-3-2-5-2, target 4, return 1-3-2-2-6-5
    public static ListNode partition(ListNode head, int target){
        /*
        * s1: allowcate two new linkedlist head
        * s2: iterate over every single element in the list, and compare with the current node's value
        * with the target's value
        *   case1: if bigger, add to large list
        *   case 2: otherwise. add to small list
        * s3: concatenate the tail of the first half to the head of the 2nd linkedlist
        * s4: reset the tail of large: large.next == null or dead loop
        *
        * */
        if(head == null || head.next ==null){
            return head;
        }

        ListNode small = new ListNode(0);
        ListNode large = new ListNode(0);
        ListNode smallTail = small;
        ListNode largeTail = large;
        ListNode cur = head;

        while(cur!=null){
            if(target>cur.value){
                smallTail.next = cur;
                smallTail= smallTail.next;

            }else{
                largeTail.next = cur;
                largeTail = largeTail.next;

            }
            cur = cur.next;
        }

        smallTail.next = large.next;
        largeTail.next = null;
        return small.next;

    }

    public static void main(String[] args){
        System.out.println("This is my customized Linked-list");
        System.out.println("Some operations are re-designed by myself. Enjoy....");
    }


}
