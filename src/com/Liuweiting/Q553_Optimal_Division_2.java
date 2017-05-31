package com.Liuweiting;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * https://leetcode.com/problems/encode-and-decode-tinyurl/#/description
 * Created by DamonLiu on 2017/5/29.
 */
public class Q553_Optimal_Division_2 {

    String base = "http://tinyurl.com/";
    HashMap<String, String> long2short = new HashMap<>();
    HashMap<String, String> short2long = new HashMap<>();

    String alpha = "";

    public Q553_Optimal_Division_2() {
        for (int i = 0; i <= 9; i++) {
            alpha += i;
        }
        for (char i = 'A'; i <= 'Z'; i++) {
            alpha += i;
        }
        for (char i = 'a'; i <= 'z'; i++) {
            alpha += i;
        }
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (long2short.get(longUrl)!=null){
            return long2short.get(longUrl);
        }
        String url;
        do {
            url = generateRandSixLengthURL();
        }while (short2long.containsKey(url));
        short2long.put(url,longUrl);
        long2short.put(longUrl,url);
        return base + url;
    }

    private String generateRandSixLengthURL(){
        String encoded = "";
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            int randIndex = rand.nextInt(alpha.length());
            encoded += alpha.charAt(randIndex);
        }
        return encoded;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        String tail = extractLast(shortUrl);
        return short2long.get(tail);
    }

    private String extractLast(String longUrl){
        int index = longUrl.lastIndexOf('/');
        return longUrl.substring(index+1);
    }

    public static void main(String[] args) {
        /**
         * http://tinyurl.com/4e9iAk
         */
        Q553_Optimal_Division_2 test = new Q553_Optimal_Division_2();
        while(true) {
            Scanner cs = new Scanner(System.in);
            String url = cs.next();
            String encoded = test.encode(url);
            System.out.println("encoded :" + encoded);
            String decoded = test.decode(encoded);
            System.out.println("decoded :" + decoded);
            System.out.println(url.compareTo(decoded) == 0);
        }
    }
}
