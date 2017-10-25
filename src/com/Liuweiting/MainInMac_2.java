package com.Liuweiting;

import com.Liuweiting.DataStructure.TreeNode;
import com.sun.source.tree.Tree;

import java.util.*;

/**
 * Created by DamonLiu on 2017/10/25.
 * The old java file is too long, so a new file is needed.
 */
public class MainInMac_2 {


    /**
     * 386. Lexicographical Numbers
     * Given an integer n, return 1 - n in lexicographical order.
     * For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
     * Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
     *
     * @param n numbers count.
     * @return the list.
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            result.add(i);
        }
        final int[] length1 = {1, 1};
        final int[] tmp = new int[1];
        final int[] num1 = new int[7];
        final int[] num2 = new int[7];
        result.sort((o1, o2) -> {
            tmp[0] = o1;
            length1[0] = 1;
            length1[1] = 1;
            Arrays.fill(num1, 0);
            Arrays.fill(num2, 0);
            while (tmp[0] >= 10) {
                length1[0]++;
                tmp[0] /= 10;
            }
            tmp[0] = o1;
            int c = length1[0];
            while (tmp[0] > 0) {
                num1[--c] = tmp[0] % 10;
                tmp[0] /= 10;
            }

            tmp[0] = o2;
            while (tmp[0] >= 10) {
                length1[1]++;
                tmp[0] /= 10;
            }
            tmp[0] = o2;
            c = length1[1];
            while (tmp[0] > 0) {
                num2[--c] = tmp[0] % 10;
                tmp[0] /= 10;
            }

            for (int i = 0; i < Math.min(length1[0], length1[1]); i++) {
                if (num1[i] < num2[i]) {
                    return -1;
                } else if (num1[i] > num2[i]) {
                    return 1;
                }
            }
            return length1[0] - length1[1];
        });
        return result;
    }

    public List<Integer> grayCode(int n) {
        if (n == 1) {
            List<Integer> re = new ArrayList<>();
            re.add(0);
            re.add(1);
            return re;
        }
        List<Integer> shorter = grayCode(n - 1);
        List<Integer> current = new ArrayList<>();
        current.addAll(shorter);
        for (int i = 0; i < shorter.size(); i++) {
            current.add((int) (shorter.get(shorter.size() - i - 1) + Math.pow(2, n - 1)));
        }
        return current;
    }

    /**
     * 394. Decode String
     * s = "3[a]2[bc]", return "aaabcbc".
     * s = "3[a2[c]]", return "accaccacc".
     * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        if (s.length() == 0) return "";
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        char[] sChar = s.toCharArray();
        for (int i = 0; i < sChar.length; i++) {
            if (sChar[i] <= '9' && sChar[i] >= '0') {
                int j = i;
                while (isNumeric(sChar[++j])) ;
                stack.push(s.substring(i, j));
                i = j - 1;
                continue;
            } else if (sChar[i] == '[') {

            } else if (sChar[i] == ']') {
                StringBuilder s1 = new StringBuilder();
                while (!isNumeric(stack.peek())) {
                    s1.append(stack.pop());
                }
//                s1 = s1.reverse();
                int repeat = Integer.parseInt(stack.pop());
                StringBuilder s2 = new StringBuilder();
                while (--repeat >= 0) {
                    s2.append(s1);
                }
                stack.push(s2.toString());
            } else {
                stack.push(String.valueOf(sChar[i]));
            }
        }
        StringBuilder s1 = new StringBuilder();
        while (stack.size() > 0) {

            s1.append(stack.pop());
        }

        return s1.reverse().toString();
    }

    private static boolean isNumeric(String tmp) {
        for (char c : tmp.toCharArray()) {
            if (!(c <= '9' && c >= '0')) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNumeric(char c) {
        return c <= '9' && c >= '0';
    }


    /**
     * 199. Binary Tree Right Side View
     *
     * @param root root for tree.
     * @return the right side view.
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Queue<TreeNode> nextLevel = new LinkedList<>();
            while (!queue.isEmpty()) {
                if (queue.size() == 1) {
                    result.add(queue.peek().val);
                }
                TreeNode tmp = queue.remove();
                if (tmp.left != null) nextLevel.add(tmp.left);
                if (tmp.right != null) nextLevel.add(tmp.right);
            }
            queue = nextLevel;
        }
        return result;
    }

    /**
     * Try to solve it in O(n log k) time and O(n) extra space.
     *
     * @param words list of words.
     * @param k     top k most frequent words.
     * @return the result.
     */
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : words) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        String[] singleWords = map.keySet().toArray(new String[map.size()]);
        Arrays.sort(singleWords, (o1, o2) -> {
            if (map.get(o1) == map.get(o2)) {
                return o1.compareTo(o2);
            }
            return map.get(o2) - map.get(o1);
        });

        List<String> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            list.add(singleWords[i]);
        }
        return list;
    }

    public static void main(String[] args) {
        MainInMac_2 m = new MainInMac_2();
        long t1 = System.currentTimeMillis();
        List<Integer> tmp = m.lexicalOrder(50000);
        long t2 = System.currentTimeMillis();
        System.out.println("time used : " + (t2 - t1) + " ms");
        System.out.println(tmp.toString());
        System.out.println(m.grayCode(3));
        System.out.println(m.decodeString("3[a]2[bc]"));
    }
}
