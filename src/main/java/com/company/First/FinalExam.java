package com.company.First;

import java.util.*;


class TreeNodeInfo {
    public int parent;
    public int level;

    TreeNodeInfo(int parent, int level){
        this.parent = parent;
        this.level = level;
    }


}
public class FinalExam {
    /*
    * Q1:Given a string, we can insert at most one empty space between each pair of adjacent letters.
    *   Print all permutations of strings after insertions of empty spaces
    *   Input: str[] ="ABC"
    *   Output:
    *       ABC
    *       AB_C
    *       A_BC
    *       A_B_C
    *
    *
    * Q2: CHeck if two nodes are cousins in a Binary Tree
    * Given the bianry Tree and the two nodes say 'a' and 'b', determine the two nodes are cousins of each other or not.
    * Two Nodes are cousins of each other if they are at same level and have different parents.
    * Example:
    *        6
    *       / \
    *      3   5
    *     /\   /\
    *    7  8 1  2
    *
    *    7 and 1, return TRUE
    *    3 and 5, return False
    *    7 and 5, return False
    *
    * Q3: How to cut/split the number into a minimum number of items
    *  such that each item is equal to an integer's square value.
    *  For example: 4 can be split to 1+1+1+1(4 items) or 2^2(1 item, which is the solution)
    *  Return 1
    *  10 can be split to two items 3^2 +1^2 (solution) or four items 2^2+2^2+1^2+1^2
    *  Return 2
    *
    * Q4: Give0s, find if all the strings can be chained to form a circle.
    * Two string s1 and s2 can be chained, if the last letter of s1 is identical to first letter of s2.
    * For example, "abc" and "cd" can be chained,
    * "abc" and "dz" can not be chained.
    * Example Input: arr[] = {"abc". "bbb", "baa", "aab"};
    * Output: True,
    * The given input string scan be chained to form a circle. The strings can be chained as "aaa"-"aab"-"bbb"-"baa".
    *
    * */

    public static boolean isCousinII(TreeNode root, int a, int b){
        if(root == null || root.left == null || root.right == null){
            return false;
        }
        TreeNodeInfo A = check(root, 0, a);
        TreeNodeInfo B = check(root, 0, b);


        return A.parent != B.parent && A.level == B.level;

    }

    private static TreeNodeInfo check( TreeNode root, int level, int a){
        if(root == null){
            return null;
        }

        if((root.left != null && root.left.value == a) || (root.right != null && root.right.value == a)) {
            return new TreeNodeInfo(root.value, level+1);
        }


        TreeNodeInfo left = check(root.left, level+1, a);
        if(left != null){
            return left;
        }
        TreeNodeInfo right = check(root.right, level+1, a);
        return right;

    }
    public static boolean isChained(String[] input){
        //assume: the input array length is great than 1
        if(input == null){
            return false;
        }else if ( input.length <= 1){
            return true;
        }

        List<List> result = new ArrayList<>();
        findChain(input, input.length, 0, result );
        if(!result.isEmpty()){
            return true;
        }
        return false;

    }
    private static void findChain(String[] input,int length,  int index, List<List> result){
        if(index > length-1){

            result.add(new ArrayList<>(Arrays.asList(input)));
            System.out.println(Arrays.toString(input));
            return;
        }
        for(int i = index; i<length; i++){
            if(index == 0 || input[index-1].charAt(input[index-1].length()-1) == input[i].charAt(0)){
                swap(input, index, i);
                findChain(input, input.length, index+1,result);
                swap(input, index, i);
            }

        }
    }
    private static void swap(String[] input, int left, int right){
        String temp = input[left];
        input[left] = input[right];
        input[right] = temp;
    }


    public static int minCut(int n){
        //assume: n > 1
        //using a dp array to store the dp min cut result; minCut[i] represents the min cuts needed for number n
        int[] minCut = new int[n+1];
        minCut[0] = 1;
        minCut[1] = 1;

        int squareInteger = 2;
        for(int i = 2; i<=n;  i++){
            int sqaure = squareInteger * squareInteger;
            if(i == sqaure){
                minCut[i] = 1;
                squareInteger++;
                continue;
            } else {
                minCut[i] = i;
            }
            for(int j = 1; j<=i/2; j++){
                minCut[i] = Math.min(minCut[j] +minCut[i-j], minCut[i]);
            }
        }
        return minCut[n];
    }



    public static boolean isCousin(TreeNode root, int a, int b){
        if(root == null){
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int aLevel = -1, bLevel = -1;

        for(int curLevel = 0, curLevelCount = 1; !queue.isEmpty(); curLevel++){
            int size = 0;
            for(int i = 0; i<curLevelCount;i++){


                TreeNode temp = queue.poll();
                if(temp.value == a){
                    aLevel = curLevel;
                } else if(temp.value == b){
                    bLevel = curLevel;
                }

                if(temp.left != null){
                    queue.offer(temp.left);
                    size++;
                }

                if(temp.right != null){
                    queue.offer(temp.right);
                    size++;
                }

                if((temp.left != null && temp.right != null) &&
                        ((temp.left.value == a && temp.right.value == b) ||(temp.left.value == b && temp.right.value == a))){
                    return false;
                }

            }
            curLevelCount = size;
        }
        return aLevel > 0 && bLevel > 0 && aLevel == bLevel;

    }


    public static List<String> permuteString(String str){
        if(str == null){
            return null;
        }
        List<String> result = new ArrayList<>();
        if(str.length() <= 1){
            result.add(str);
            return result;
        }
        StringBuilder current = new StringBuilder();
        permutation(str, 0, current, result);
        return result;
    }

    private static void permutation(String input, int level, StringBuilder current, List<String> result) {
        //base case
        if(level==input.length()-1){
            current.append(input.charAt(level));
            result.add(current.toString());
            current.deleteCharAt(current.length()-1);
            return;
        }

        //recursion rules
        current.append(input.charAt(level));
        permutation(input, level+1, current,result);
        current.deleteCharAt(current.length()-1);

        current.append(input.charAt(level));
        current.append('_');
        permutation(input, level+1, current,result);
        current.deleteCharAt(current.length()-1);
        current.deleteCharAt(current.length()-1);
    }

    public static void main(String[] args){
        System.out.println("This is the problems set!");
        System.out.println(permuteString("abcd"));


        TreeNode root = new TreeNode(6);

        TreeNode left = new TreeNode(3);
        TreeNode right = new TreeNode(5);
        root.left = left;
        root.right = right;
        left.left = new TreeNode(7);
        left.right = new TreeNode(8);
        right.left = new TreeNode(1);
        right.right = new TreeNode(2);

        System.out.println(isCousin(root, 3,5));
//
//        System.out.println(minCut(29));
//
//        String[] inputI = {"aaa", "bbb", "baa", "aab"};
//        String[] inputII = {"abc", "df"};
//        String[] inputIII = {"a", "b"};
//        System.out.println(isChained(inputI));
//        System.out.println(isChained(inputIII));

        System.out.println(isCousinII(root, 7,8));

    }
}
