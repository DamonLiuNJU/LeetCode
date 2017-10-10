package com.Liuweiting.ProblemSolutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by DamonLiu on 2017/9/21.
 */
public class MagicDictionary {
    /** Initialize your data structure here. */

    public MagicDictionary() {

    }
    String[] dict;
    HashMap<Integer,List<Integer>> data;
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        this.dict = dict;
        data = new HashMap<>();
        for (int i=0;i<dict.length;i++){
            String tmp = dict[i];
            List l = data.getOrDefault(tmp.length(),new ArrayList<>());
            l.add(i);
            data.put(tmp.length(),l);
        }
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        List<Integer> tmp = data.getOrDefault(word.length(),new ArrayList<>());
        for (int  another : tmp){
            String s = this.dict[another];
            if (isOnePlaceDiff(s,word)){
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    private boolean isOnePlaceDiff(String s,String s1){
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)!=s1.charAt(i)){
                counter++;
                if(counter >= 2){
                    return false;
                }
            }
        }
        return counter==1;
    }

    public static void main(String[] args) {
        MagicDictionary m = new MagicDictionary();
        String[] input = {"hello","leetcode"};
        m.buildDict(input);
        System.out.println(m.search("hello"));
        System.out.println(m.search("hhllo"));
        System.out.println(m.search("hell"));
        System.out.println(m.search("leetcoded"));
    }
}
