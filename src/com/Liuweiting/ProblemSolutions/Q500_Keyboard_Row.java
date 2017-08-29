package com.Liuweiting.ProblemSolutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by DamonLiu-Lab on 2017/8/15.
 */
public class Q500_Keyboard_Row {
    public String[] findWords(String[] words) {
        Character[] line1 = {'q','w','e','r','t','y','u','i','o','p'};
        Character[] line2 = {'a','s','d','f','g','h','j','k','l'};
        Character[] line3 = {'z','x','c','v','b','n','m'};
        List<Character> list1 = Arrays.asList(line1);
        List<Character> list2 = Arrays.asList(line2);
        List<Character> list3 = Arrays.asList(line3);

        List<String> safeStrs = new ArrayList<>();

        for (String str: words
             ) {
            boolean safe = true;
            int firstLineIndex = -1;
            for (char tmp : str.toCharArray()
                 ) {
                if (list1.contains(tmp)){
                    if (firstLineIndex < 0)
                        firstLineIndex=1;
                    else if (firstLineIndex!=1) {
                        safe = false;
                        break;
                    }
                    continue;
                }

                if (list2.contains(tmp)){
                    if (firstLineIndex < 0)
                        firstLineIndex=2;
                    else if (firstLineIndex!=2) {
                        safe = false;
                        break;
                    }
                    continue;
                }

                if (list3.contains(tmp)){
                    if (firstLineIndex < 0)
                        firstLineIndex=3;
                    else if (firstLineIndex!=3) {
                        safe = false;
                        break;
                    }
                    continue;
                }
            }
            if (safe){
                safeStrs.add(str);
            } else {

            }
        }

        String[] strings = new String[safeStrs.size()];

        safeStrs.toArray(strings);

        return strings;
    }
}
