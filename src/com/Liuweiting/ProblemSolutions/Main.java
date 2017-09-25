package com.Liuweiting.ProblemSolutions;

import com.Liuweiting.DataStructure.ListNode;

import java.util.Arrays;
import java.util.Random;

public class Main {

    /**
     * Q409. Longest Palindrome
     * https://leetcode.com/problems/longest-palindrome/description/
     *
     * @param args
     */
    public int longestPalindrome(String s) {
        //find even number of chars in string s.
        //add one odd number of char.
        int[] lower = new int[26];
        int[] upper = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c <= 'z' && c >= 'a') {
                lower[c-'a']++;
            } else {
                upper[c-'A']++;
            }
        }
        int length = 0;
        for (int i = 0; i < 26; i++) {
            length += (lower[i]/2)*2;
            length += (upper[i]/2)*2;
        }
        for (int i = 0; i < 26; i++) {
            if (lower[i]%2==1||upper[i]%2==1){
                length++;
                return length;
            }
        }
        return length;
    }


    /**
     * Q628. Maximum Product of Three Numbers
     * https://leetcode.com/problems/maximum-product-of-three-numbers/description/
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);

        //condition1
        int value1 = nums[0]*nums[1]*nums[2];
        if (nums.length==3) return value1;
        int value2 = nums[nums.length-1]*nums[nums.length-2]*nums[nums.length-3];
        int value3 = nums[0]*nums[1]*nums[nums.length-1];
        int value4 = nums[0]*nums[nums.length-2]*nums[nums.length-3];
        return -1;
    }


    /**
     * @param head
     *            The linked list's head. Note that the head is guaranteed to be
     *            not null, so it contains at least one node.
     */
    ListNode head;
    int totalLength = 0;
    public Main(ListNode head) {
        this.head = head;
        while (head!=null){
            totalLength++;
            head = head.next;
        }
    }

    /** Returns a random node's value. */
    public int getRandom() {
        int randomIndex = new Random().nextInt(totalLength);
        ListNode tmp = head;
        while (randomIndex>0 && tmp.next!=null){
            randomIndex--;
            tmp = tmp.next;
        }
        return tmp.val;
    }


//    public int maximumProduct(int[] nums) {
//        int result1 =
//    }

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        Main m = new Main(root);
        while (true){
            System.out.println(m.getRandom());
        }
    }
}
