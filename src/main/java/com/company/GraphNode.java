package com.company;

import java.util.ArrayList;

public class GraphNode {

    public int value;
    public char set;
    public ArrayList<GraphNode> successors;
    public GraphNode(int val, char s, ArrayList<GraphNode> suc){
        value = val;
        set = s;
        successors = suc;
    }
}
