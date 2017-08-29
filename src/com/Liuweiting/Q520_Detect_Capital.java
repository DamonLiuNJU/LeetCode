package com.Liuweiting;

/**
 * Created by DamonLiu on 2017/8/18.
 */
public class Q520_Detect_Capital {
    public boolean isValid(String word){
        char first = word.charAt(0);
        if (isUpperCase(first) && word.substring(1).compareTo(word.substring(1).toLowerCase())==1){
            return true;
        }

        return false;
    }

    private boolean isUpperCase(char c){
        return c >= 'A' && c <= 'Z';
    }

    private boolean isLowerCase(char c){
        return !isUpperCase(c);
    }
}
