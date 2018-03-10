package com.Liuweiting.DataStructure;

/**
 * Created by DamonLiu on 2018/1/30.
 */


class TrieNode {
    TrieNode[] nestArray = new TrieNode[26];
    boolean isWord;
}

class Trie {

    //just create a nested data structure.
    /**
     * Initialize your data structure here.
     */
    TrieNode dummy = new TrieNode();

    public Trie() {

    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode cur = dummy;
        for (char c : word.toCharArray()) {
            if (cur.nestArray[c - 'a'] == null) {
                cur.nestArray[c - 'a'] = new TrieNode();
            }
            cur = cur.nestArray[c - 'a'];
        }
        cur.isWord = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode tmp = dummy;
        for (char c : word.toCharArray()) {
            if (tmp.nestArray[c-'a']==null){
                return false;
            } else {
                tmp = tmp.nestArray[c-'a'];
            }
        }
        return tmp.isWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode tmp = dummy;
        for (char c : prefix.toCharArray()) {
            if (tmp.nestArray[c-'a']==null){
                return false;
            } else {
                tmp = tmp.nestArray[c-'a'];
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("abc");
        System.out.println(t.search("abc"));
        System.out.println(t.search("abcd"));
        System.out.println(t.startsWith("ab"));
    }
}
