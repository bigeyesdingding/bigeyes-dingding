package com.company.First;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class WordSearch {
    class TrieNode{
        public char content;
        public TrieNode[] children;
        public int count = 0;
        public boolean isEnd = false;
        TrieNode(char c){
            children = new TrieNode[26];
            content = c;
        }
    }
    class Trie{
        public TrieNode root;

        public Trie(String[] words){
            root = new TrieNode(' ');
            for(String word: words){
                insert(word);
            }
        }

        public void insert(String word){
            TrieNode cur = root;
            for(char c : word.toCharArray()){
                cur = cur.children[c-'a'];
                if(cur == null){
                    cur = new TrieNode(c);
                }
                cur.count++;
            }
            cur.isEnd = true;

        }
        public boolean search(String word){
            TrieNode cur = root;
            for(char c: word.toCharArray()){
                cur = cur.children[c-'a'];
                if(cur == null){
                    return false;
                }
            }
            if(!cur.isEnd){
                return false;
            }
            return true;
        }

        public boolean startWith(String word){
            TrieNode cur = root;
            for(char c: word.toCharArray()){
                cur = cur.children[c-'a'];
                if(cur == null){
                    return false;
                }
            }
            return true;
        }

        public void remove(String word){
            if(!search(word)){
                return;
            }
            TrieNode cur = root;
            for(char c: word.toCharArray()){
                cur = cur.children[c-'a'];
                if(cur.count == 1){
                    cur = null;
                    return;
                }
                cur.count--;
            }
            cur.isEnd = false;
        }
    }


    public List<String> findWords(char[][] board, String[] words){
        int m = board.length;
        int n = board[0].length;
        //first build trie to store the words
        //second do dfs of the borad

        Trie trie = new Trie(words);

        boolean[][] visited = new boolean[m][n];
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Set<String> set = new HashSet<>();

        for(int i = 0; i<m; i++){
            for(int j = 0; j < n; j++){
                dfs(board, i, j, sb, trie, visited,res);
            }
        }
        return res;
    }

    private void dfs(char[][] board, int i, int j, StringBuilder sb, Trie trie, boolean[][] visited, List<String> res){
        int m = board.length;
        int n = board[0].length;
        if(i>=m || i<0 || j>= n || j < 0){
            return;
        }
        if(visited[i][j]){
            return;
        }

        sb.append(board[i][j]);
        visited[i][j] = true;

        if(!trie.startWith(sb.toString())){
            return;
        }
        if(trie.search(sb.toString())){
            res.add(sb.toString());
        }

        dfs(board, i+1, j, sb, trie, visited,res);
        dfs(board, i, j+1, sb, trie, visited,res);
        dfs(board, i-1, j, sb, trie, visited,res);
        dfs(board, i, j-1, sb, trie, visited,res);

        sb.deleteCharAt(sb.length()-1);
        visited[i][j] = false;

    }


}