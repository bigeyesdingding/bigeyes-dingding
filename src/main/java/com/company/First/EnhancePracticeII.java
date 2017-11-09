package com.company.First;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnhancePracticeII {
    public static void main(String[] args){
        System.out.println("This is the test of enhancement practice II..");
    }
    //Q1
    //Deep Copy Linked List With Random Pointer
    //Each of the nodes in the linked list has another pointer pointing to a random node in the list or null. Make a deep copy of the original list.
    ///**
    // * class RandomListNode {
    // *   public int value;
    // *   public RandomListNode next;
    // *   public RandomListNode random;
    // *   public RandomListNode(int value) {
    // *     this.value = value;
    // *   }
    // * }
    // */
    //    public class Solution {
    //        public RandomListNode copy(RandomListNode head) {
    //            // Write your solution here.
    //            return null;
    //        }
    //    }
    public static RandomListNode copyList(RandomListNode head){

        if(head == null){
            return null;
        }

        RandomListNode dummy = new RandomListNode(0);
        RandomListNode cur = dummy;
        Map<RandomListNode,RandomListNode> map = new HashMap<>();


        while(head != null){
            if(!map.containsKey(head)){
                map.put(head, new RandomListNode(head.value));
            }
            //copy head
            cur.next = map.get(head);
            //copy random
            if(head.random !=null){
                if(!map.containsKey(head.random)){
                    map.put(head.random, new RandomListNode(head.random.value));
                }
            }
            cur.next.random = map.get(head.random);
            head = head.next;
            cur = cur.next;
        }
        return dummy.next;
    }


    //Q2
    //    Graph
    //    Deep Copy Undirected Graph
    //    Make a deep copy of an undirected graph, there could be cycles in the original graph.
    //
    //            Assumptions
    //
    //    The given graph is not null
    //
    //    /*
    //    * class GraphNode {
    //    *   public int key;
    //    *   public List<GraphNode> neighbors;
    //    *   public GraphNode(int key) {
    //    *     this.key = key;
    //    *     this.neighbors = new ArrayList<GraphNode>();
    //    *   }
    //    * }
    //    */
    //    public class Solution {
    //        public List<GraphNode> copy(List<GraphNode> graph) {
    //            // Write your solution here.
    //            return null;
    //        }
    //    }
    public List<GraphNode> copyGraph(List<GraphNode> graph){
        if(graph == null){
            return null;
        }
        HashMap<GraphNode, GraphNode> map = new HashMap<>();
        for(GraphNode node : graph){
            if(!map.containsKey(node)){
                map.put(node, new GraphNode(node.key));
                DFS(node, map);
            }
        }
        return new ArrayList<GraphNode>(map.values());

    }
    private void DFS(GraphNode seed, HashMap<GraphNode, GraphNode> map){
        if(seed == null){
            return;
        }

        GraphNode copy = map.get(seed);
        for(GraphNode nei: seed.neighbors){
            if(!map.containsKey(seed)){
                map.put(seed, new GraphNode(seed.value));
                DFS(nei,map);
            }
            copy.neighbors.add(map.get(nei));
        }

    }

    //Q3:
    //    Data Structure
    //    Insert In Binary Search Tree
    //    Insert a key in a binary search tree if the binary search tree does not already contain the key.
    //    Return the root of the binary search tree.
    //
    //    Assumptions
    //
    //    There are no duplicate keys in the binary search tree
    //
    //    If the key is already existed in the binary search tree, you do not need to do anything
    //
    //            Examples
    //
    //        5
    //
    //                /    \
    //
    //                3        8
    //
    //                /   \
    //
    //                1     4
    //
    //    insert 11, the tree becomes
    //
    //        5
    //
    //                /    \
    //
    //                3        8
    //
    //                /   \        \
    //
    //                1     4       11
    //
    //    insert 6, the tree becomes
    //
    //        5
    //
    //                /    \
    //
    //                3        8
    //
    //                /   \     /  \
    //
    //                1     4   6    11
    //

    public static TreeNode insert(TreeNode root, int value){
        if(root == null){
            return null;
        }

        if(value>root.value){
            if(root.right != null){
                return insert(root.right,value);
            } else {
                root.right = new TreeNode(value);

            }

        } else if(value < root.value){
            if(root.left != null){
                return insert(root.left, value);
            } else {
                root.right = new TreeNode(value);
            }
        }
        return root;
    }

    //Q4:
    //    Data Structure
    //    Search In Binary Search Tree
    //    Find the target key K in the given binary search tree, return the node that contains the key if K is found, otherwise return null.
    //
    //    Assumptions
    //
    //    There are no duplicate keys in the binary search tree
    public static TreeNode find(TreeNode root, int value){
        if(root == null){
            return null;
        }


        if(value == root.value){
            return root;
        }else if(value>root.value){
            return find(root.right,value);
        } else{
            return find(root.left, value);
        }
    }

    //Q5:
    //    Data Structure
    //    Delete In Binary Search Tree
    //    Delete the target key K in the given binary search tree if the binary search tree contains K. Return the root of the binary search tree.
    //
    //    Find your own way to delete the node from the binary search tree, after deletion the binary search tree's property should be maintained.
    //
    //    Assumptions
    //
    //    There are no duplicate keys in the binary search tree

    public static TreeNode delete(TreeNode root, int key){
        if(root == null){
            return null;
        }

        if(key == root.value){
            if(root.left == null){
                return root.right;
            } else if(root.right == null){
                return root.left;
            } else if(root.right.left == null){
                root.right.left = root.left;
            } else{
                TreeNode newRoot = deleteSmallest(root.right);
                newRoot.left = root.left;
                newRoot.right = root.right;
                return newRoot;
            }
        }

        if(key <root.value){
            root.left = delete(root.left, key);
        }else if(key > root.value){
            root.right = delete(root.right, key);
        }
        return root;
    }


    private static TreeNode deleteSmallest(TreeNode root){
        while(root.left.left!=null){
            root = root.left;
        }
        TreeNode smallest = root.left;
        root.left = root.left.right;
        return smallest;
    }

    //Q6:
    //    Data Structure
    //    Closest Number In Binary Search Tree
    //    In a binary search tree, find the node containing the closest number to the given target number.
    //
    //    Assumptions:
    //
    //    The given root is not null.
    //    There are no duplicate keys in the binary search tree.
    //    Examples:
    //
    //            5
    //
    //            /    \
    //
    //            2      11
    //
    //            /    \
    //
    //            6     14
    //
    //    closest number to 4 is 5
    //
    //    closest number to 10 is 11
    //
    //    closest number to 6 is 6
    //
    //    How is the binary tree represented?
    //
    //    We use the level order traversal sequence with a special symbol "#" denoting the null node.
    //
    //    For Example:
    //
    //    The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
    //
    //            1
    //
    //            /   \
    //
    //            2     3
    //
    //            /
    //
    //            4


    public static TreeNode closest(TreeNode root, int value){
        if(root == null){
            return null;
        }
        TreeNode result = new TreeNode();
        int diff = Integer.MAX_VALUE;
        while(root != null){
            int curDiff = Math.abs(root.value-value);

            if(curDiff == 0){
                return root;
            }

            if(curDiff < diff){
                result = root;
                diff = curDiff;
            }

            if(value>root.value){
                root = root.right;
            } else{
                root = root.left;
            }
        }
        return result;

    }
    //Q7:
    //    Data Structure
    //    Largest Number Smaller In Binary Search Tree
    //    In a binary search tree, find the node containing the largest number smaller than the given target number.
    //
    //    If there is no such number, return INT_MIN.
    //
    //            Assumptions:
    //
    //    The given root is not null.
    //    There are no duplicate keys in the binary search tree.
    //    Examples
    //
    //    5
    //
    //            /    \
    //
    //            2      11
    //
    //            /    \
    //
    //            6     14
    //
    //    largest number smaller than 1 is Integer.MIN_VALUE(Java) or INT_MIN(c++)
    //
    //    largest number smaller than 10 is 6
    //
    //    largest number smaller than 6 is 5
    //
    //    How is the binary tree represented?
    //
    //    We use the level order traversal sequence with a special symbol "#" denoting the null node.
    //
    //    For Example:
    //
    //    The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
    //
    //            1
    //
    //            /   \
    //
    //            2     3
    //
    //            /
    //
    //            4


    public static TreeNode largestSmall(TreeNode root, int value){
        if(root == null){
            return null;
        }
        TreeNode result = new TreeNode();
        while(root!= null){
            if(value < root.value ){
                root =  root.left;
            } else if(value == root.value) {
                return root;
            } else {
                result = root;
                root = root.right;
            }
        }
        return result;
    }

    //Q8:
    //    Data Structure
    //    Merge K Sorted Array
    //    Merge K sorted array into one big sorted array in ascending order.
    //
    //    Assumptions
    //
    //    The input arrayOfArrays is not null, none of the arrays is null either.



    //Q9:
    //    Data Structure
    //    Merge K Sorted Lists
    //    Merge K sorted lists into one big sorted list in ascending order.
    //
    //    Assumptions
    //
    //    ListOfLists is not null, and none of the lists is null.



    //Q10:
    //    Merge Stones
    //    We have a list of piles of stones, each pile of stones has a certain weight, represented by an array of integers.
    //    In each move, we can merge two adjacent piles into one larger pile, the cost is the sum of the weights of the two piles.
    //    We merge the piles of stones until we have only one pile left. Determine the minimum total cost.
    //
    //            Assumptions
    //
    //    stones is not null and is length of at least 1
    //    Examples
    //
    //    {4, 3, 3, 4}, the minimum cost is 28
    //
    //    merge first 4 and first 3, cost 7
    //
    //    merge second 3 and second 4, cost 7
    //
    //    merge two 7s, cost 14
    //
    //    total cost = 7 + 7 + 14 = 28


    //Q11:
    //    Cutting Wood I
    //    There is a wooden stick with length L >= 1, we need to cut it into pieces, where the cutting positions are defined in an int array A. The positions are guaranteed to be in ascending order in the range of [1, L - 1]. The cost of each cut is the length of the stick segment being cut. Determine the minimum total cost to cut the stick into the defined pieces.
    //
    //            Examples
    //
    //    L = 10, A = {2, 4, 7}, the minimum total cost is 10 + 4 + 6 = 20 (cut at 4 first then cut at 2 and cut at 7)

}
