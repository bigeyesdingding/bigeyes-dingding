package com.company.First;


import java.util.HashMap;
import java.util.Map;

class Node<K, V>{
    K key;
    V value;
    Node next;
    Node prev;
    Node(K key, V value){
        this.key = key;
        this.value = value;
    }
    public void update(K key, V value){
        this.key = key;
        this.value = value;
    }
}
public class LUC <K, V>{

    private int limit = 5000;
    private Map<K, Node<K, V>> map;
    private Node<K, V> head;
    private Node<K, V> tail;

    LUC(int limit){
        this.limit = limit;
        this.map = new HashMap<K, Node<K, V>>();
    }

    public void set(K key, V value){
        Node<K, V> node = null;
        if(map.size()<limit){
            node = new Node<K,V>(key, value);
        }else if(map.containsKey(key)){
            node = map.get(key);
            node.value = value;
            remove(node);
        }else{
            node = tail;
            remove(node);
            node.update(key, value);
        }

        append(node);
    }

    public V get( K key){
        Node<K, V> node = map.get(key);
        if(node == null){
            return null;
        }
        //even itr is a read operation, it is still a write operation to the
        //dll, and we need to move the node to teh head
        remove(node);
        append(node);
        return node.value;
    }

    private Node<K, V> remove(Node<K, V> node){
        map.remove(node.key);
        if(node.prev != null){
            node.prev.next = node.next;
        }
        if(node.next!= null){
            node.next.prev = node.prev;
        }

        if(node == head){
            head = head.next;
        }
        if(node == tail){
            tail = tail.prev;
        }
        node.next = node.prev = null;
        return node;

    }

    private Node<K, V> append(Node<K, V> node){
        map.put(node.key, node);
        if(head == null){
            head = tail = node;
        }else{
            node.next = head;
            head.prev = node;
            head = node;
        }
        return node;
    }

}
