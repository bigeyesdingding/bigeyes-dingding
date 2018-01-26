package com.company.First.ershua;

import com.company.First.ListNode;

import java.util.PriorityQueue;

public class LinkedListR{
    PriorityQueue<Integer> s = new PriorityQueue<>();
    //linkedlist merge sort
    public ListNode mergeSort(ListNode head){
        if(head == null || head.next == null){
            return head;
        }

        ListNode fast = head, slow = head;
        while(fast != null && fast.next != null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode one = head;
        ListNode two = slow.next;
        slow.next = null;
        return merge(one, two);
    }

    private ListNode merge(ListNode one, ListNode two){
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;

        while(one!= null && two != null){
            if(one.value>two.value){
                prev.next = two;
                two = two.next;
            }else{
                prev.next = one;
                one = one.next;

            }
            prev = prev.next;
        }

        if(one!= null){
            prev.next = one;
        }
        if(two!= null){
            prev.next = two;
        }
        return dummy.next;
    }


    static public void main(String[] args){

        LinkedListR test = new LinkedListR();


    }


}
class HasCycle {
    //check if a linkedList has cycle
    public static boolean has(ListNode head){
        if(head == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;

        while(fast!= null && fast.next!=null){
            if(fast == slow){
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }

    //find the node where the cycle starts
    public ListNode start(ListNode head){
        ListNode fast = head;
        ListNode slow = head;

        while( fast!= null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;

            if(fast== slow){
                ListNode slow2= head;
                while(slow != slow2){
                    slow=slow.next;
                    slow2= slow2.next;
                }
                return slow;
            }

        }
        return null;
    }
    //find the duplicate given from 1-n
    public int findDup(int[] nums){
        //assume: nums.length>2
        if(nums == null || nums.length<2){
            return -1;
        }

        int slow = nums[0];
        int fast = nums[nums[0]];
        while(fast !=slow){
            fast = nums[nums[fast]];
            slow = nums[slow];
        }

        fast = 0;
        while(fast!=slow){
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;

    }

    public int findDupII(int[] nums){
        //assume: nums.length>2
        if(nums == null || nums.length<2){
            return -1;
        }
        int l = 1, r = nums.length-1;
        while(l<r){
            int mid = (l+r)/2;
            int left = 0;
            for(int i = 0; i<nums.length; i++){
                if(nums[i]<=mid){
                    left++;
                }
            }
            if(left==mid){
                l = mid+1;
            }else{
                r = mid;
            }

        }
        return l;

    }

    //insert into a sorted list
    public ListNode insert(ListNode head, int value){
        if(head == null ){
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        prev.next = head;
        ListNode cur = head;
        while(cur!=null && cur.value<value){
            cur = cur.next;
            prev =  prev.next;
        }

        ListNode add = new ListNode(value);
        add.next= cur;
        prev.next = add;
        return dummy.next;

    }

    //merge two linked list
    public ListNode merge(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while(l1!= null && l2!=null){
            if(l2.value<l1.value){
                cur.next = l2;
                l2 = l2.next;
            }else{
                cur.next = l1;
                l1 = l1.next;
            }
            cur = cur.next;
        }
        while(l1!=null){
            cur.next = l1;
            l1=l1.next;
        }
        while(l2!= null){
            cur.next = l2;
            l2 = l2.next;
        }
        return dummy.next;
    }


    //reorder linked list
    public ListNode reorder(ListNode head){
        //slow fast to get the second half
        ListNode slow = head, fast = head;
        while(fast!= null && fast.next != null  && fast.next.next != null){

            if(fast.next.next != null){
                fast = fast.next.next;
                slow = slow.next;
            }else{

            }

        }

        //reverse second half
        ListNode head2 = reverse2(slow);

        //merge two linkedlis
        return merge2(head, head2);

    }

    private ListNode reverse2(ListNode head){
        if(head == null){
            return null;
        }
        ListNode prev = head;
        ListNode cur = head.next;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    private ListNode merge2(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        int index =0;
        while(l1!=null || l2!=null){
            if(l1== null || index%2==1){
                prev.next = l2;
                l2 = l2.next;
            }else{
                prev.next = l1;
                l1 = l1.next;
            }
            index++;
            prev = prev.next;
        }
        return dummy.next;
    }

    //Partition LinkedList
    public ListNode partition(ListNode head, int target){
        if(head == null){
            return null;
        }

        ListNode oneHead = new ListNode(0);
        ListNode one = oneHead;
        ListNode twoHead = new ListNode(0);
        ListNode two = twoHead;


        while(head != null){
            if(head.value<target){
                two.next = head;
                two = two.next;
            }else{
                one.next = head;
                one = one.next;
            }
            head = head.next;
        }

        one.next = null;
        two.next = oneHead.next;

        return twoHead.next;

    }

    public void main(String[] args){

    }

}
