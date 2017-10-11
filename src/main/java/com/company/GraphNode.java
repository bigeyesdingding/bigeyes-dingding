package com.company;

import java.util.ArrayList;

public class GraphNode {
    public int key;
    public int value;
    public char set;
    public ArrayList<GraphNode> successors;
    public ArrayList<GraphNode> neighbors;
    public GraphNode(int val, char s, ArrayList<GraphNode> suc){
        value = val;
        set = s;
        successors = suc;
    }
    public GraphNode(int k){
        key = k;
    }
}
