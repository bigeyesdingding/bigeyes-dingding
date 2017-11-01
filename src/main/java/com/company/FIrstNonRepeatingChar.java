package com.company;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FIrstNonRepeatingChar {
    class Node{
        public Node prev;
        public Node next;
        public char c;
        Node(char c){
            this.c = c;
        }
    }

    public Node head;
    public Node tail;
    public Map<Character, Node> map = new HashMap<>();
    public Set<Character> set = new HashSet<>();
    FIrstNonRepeatingChar(){
        tail = null;
        tail.next = tail.prev = tail;
        head = tail;
        set = new HashSet<Character>();
        map = new HashMap<Character, Node>();
    }

    public char firstNonReap(){
        return tail.c;
    }

    public void load(char c){
        if(set.contains(c)){
            return;
        }

        if(map.containsKey(c)){
            Node node = map.get(c);
            map.remove(node);
            set.add(c);
        }else{
            Node node = append(c);
            map.put(c, node);
        }
    }

    private Node append(char c){
        Node node = new Node(c);
        if(head == null){
            head = null;
            tail = null;
        }
        tail.next = node;
        node.prev = tail;
        tail = node;
        return node;
    }
    private Node remove(Node node){
        if(node.prev != null){
            node.prev.next = node.next;
        }
        if(node.next != null){
            node.next.prev = node.prev;
        }
        if(node == head){
            head = head.next;
        }
        if(node == tail){
            tail = tail.prev;
        }
        return node;
    }
}
