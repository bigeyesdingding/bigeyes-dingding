package com.company;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FIrstNonRepeatingChar {
    class Node{
        public Node prev;
        public Node next;
        public Character c;
        Node(Character c){
            this.c = c;
        }
    }

    public Node head;
    public Node tail;
    public Map<Character, Node> map = new HashMap<>();
    public Set<Character> set = new HashSet<>();
    FIrstNonRepeatingChar(){
        tail = new Node(null);
        tail.next = tail.prev = tail;
        head = tail;
        set = new HashSet<Character>();
        map = new HashMap<Character, Node>();
    }

    public Character firstNonReap(){
        if(head == tail){
            return null;
        }
        return head.next.c;
    }

    public void load(char c){
        if(set.contains(c)){
            return;
        }

        if(map.containsKey(c)){
            Node node = map.get(c);
            remove(node);

        }else{
            Node node = new Node(c);
            append(node);
        }
    }

    private void append(Node node){
        map.put(node.c, node);
        tail.next = node;
        node.prev = tail;
        node.next = head;
        tail = tail.next;
    }
    private Node remove(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
        if(node == tail){
            tail = node.prev;
        }
        node.next = node.prev = null;
        set.add(node.c);
        map.remove(node.c);
        return node;
    }
}
