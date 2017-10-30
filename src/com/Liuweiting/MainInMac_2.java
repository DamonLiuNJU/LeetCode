package com.Liuweiting;

import com.Liuweiting.DataStructure.TreeNode;
import com.sun.org.apache.bcel.internal.generic.INEG;
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


    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    /**
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.end == o2.end) return o1.start - o2.start;
                return o1.end - o2.end;
            }
        });

        int maxNoneOverlappingIntervals = 1;
        int currentEnd = intervals[0].end;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < currentEnd) {
                continue;
            } else {
                maxNoneOverlappingIntervals++;
                currentEnd = intervals[i].end;
            }
        }

        return intervals.length - maxNoneOverlappingIntervals;
    }


    /**
     * Q525
     *
     * @param nums sequence of 1s and 0s.
     * @return
     */
    public int findMaxLength_TLE(int[] nums) {
        int hi = (nums.length / 2) * 2;
        int lo = 0;
        for (int middle = hi; middle >= lo; ) {
            int sum = 0, maxSum = -1;
            for (int i = 0; i < middle; i++) {
                sum += nums[i];
            }
            maxSum = Math.max(maxSum, sum);
            if (sum == middle / 2) {
                return middle;
            }
            for (int i = middle; i < nums.length; i++) {
                sum = sum + nums[i] - nums[i - middle];
                maxSum = Math.max(maxSum, sum);
                if (sum == middle / 2) {
                    return middle;
                }
            }
            middle = Math.min(middle - 2, maxSum * 2);
        }
        return 0;
    }

//    public int findMaxLength(int[] nums) {
//
//    }

    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int zero = 0;
        int one = 0;
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zero++;
            } else {
                one++;
            }

            if (map.containsKey(zero - one)) {
                len = Math.max(len, i - map.get(zero - one));
            } else {
                map.put(zero - one, i);
            }
        }

        return len;
    }

    /**
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        if (nums.length == 1) return nums[0];
        int mi = -1;  //index for middle.
        int hi = nums.length - 1;
        int lo = 0;
        while (lo < hi) {
            mi = (int) (.5 * hi + .5 * lo);
            if (lo == mi || hi == mi) {
                return Math.min(nums[lo], nums[hi]);
            }
            if (nums[lo] > nums[hi]) {
                if (nums[mi] > nums[lo]) {
                    lo = mi;
                    continue;
                } else if (nums[mi] > nums[hi]) {
                    //
                } else {
                    hi = mi;
                    continue;
                }
            } else { //if the left is smaller than right, then the array is already sorted.
                return nums[lo];
            }
        }
        return nums[lo];
    }

    /**
     * 77. Combinations
     * https://leetcode.com/problems/combinations/description/
     *
     * @param n 1~n available numbers.
     * @param k the length of each combination.
     * @return all combinations.
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<>(), 1, n, k);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> currentList, int lo, int hi, int leftChoices) {
        if (leftChoices == 0) {
            result.add(new ArrayList<>(currentList));
            return;
        }
        for (int i = lo; i <= hi; i++) {
            currentList.add(i);
            dfs(result, currentList, i + 1, hi, leftChoices - 1);
            currentList.remove(currentList.size() - 1);
        }
    }


    /**
     * 560. Subarray Sum Equals K
     *
     * @param nums
     * @param k
     * @return
     */
//    public int subarraySum(int[] nums, int k) {
//        int sums[] = new int[nums.length + 1];
//        sums[0] = 0;
//        for (int i = 0; i < nums.length; i++) {
//            sums[i+1] = sums[i] + nums[i];
//        }
//        int ans = 0;
//        for (int i = 0; i < sums.length; i++) {
//            for (int j = 0; j < i; j++) {
//                if (sums[i] - sums[j]==k){
//                    ans++;
//                }
//            }
//        }
//        return ans;
//    }
    public int subarraySum(int[] nums, int k) {
        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (preSum.containsKey(sum - k)) {
                result += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }

        return result;
    }

//    public int subarraySum(int[] nums, int k) {
//        int sums[] = new int[nums.length];
//        sums[0] = nums[0];
//        int maxSum = Integer.MIN_VALUE,minSum = Integer.MAX_VALUE;
//        maxSum = Math.max(maxSum,sums[0]);
//        minSum = Math.min(minSum,sums[0]);
//        for (int i = 1; i < nums.length; i++) {
//            sums[i] = sums[i-1] + nums[i-1];
//            maxSum = Math.max(maxSum,sums[i]);
//            minSum = Math.min(minSum,sums[i]);
//        }//sum[i] is the sum of total i numbers.
//
//        int[] map = new int[maxSum-minSum+1];
//        Arrays.fill(map,0);
//        for (int i = 0; i < sums.length; i++) {
//            map[sums[i] - minSum]++;
//        }
//        int ans = 0;
//        for (int i = 0; i < sums.length; i++) {
//            int currentSum = sums[i];
//            int matchingSum = sums[i] + k;
//            if (currentSum == k){
//                ans += map[currentSum - minSum];
//            }
//            if (map[currentSum - minSum]>0 && currentSum!=matchingSum  && matchingSum <= maxSum && minSum <= matchingSum && map[matchingSum - minSum]>0){
//                ans += map[sums[i] - minSum] * map[sums[i] + k - minSum];
//            }
//        }
//        return ans;
//    }


    public int[][] generateMatrix(int n) {
        int currentDirection = 1; // 1-right 2-down 3-left 4-up.
        int currentStep = 2;
        int x = 0;
        int y = 0;
        int[][] result = new int[n][n];
        if (n == 0) return result;
        result[0][0] = 1;
        while (currentStep <= n * n) {
            switch (currentDirection) {
                case 1: {
                    while (++y < n && result[x][y] == 0) {
                        result[x][y] = currentStep;
                        currentStep++;
                    }
                    currentDirection++;
                    currentDirection %= 4;
                    y--;
                    break;
                }

                case 2: {
                    while (++x < n && result[x][y] == 0) {
                        result[x][y] = currentStep;
                        currentStep++;
                    }
                    currentDirection++;
                    currentDirection %= 4;
                    x--;
                    break;
                }
                case 3: {
                    while (--x >= 0 && result[x][y] == 0) {
                        result[x][y] = currentStep;
                        currentStep++;
                    }
                    currentDirection++;
                    currentDirection %= 4;
                    x++;
                    break;
                }
                case 0: {
                    while (--y >= 0 && result[x][y] == 0) {
                        result[x][y] = currentStep;
                        currentStep++;
                    }
                    currentDirection++;
                    currentDirection %= 4;
                    y++;
                    break;
                }
            }
        }
        return result;
    }


    public int subarraySum2(int[] nums, int k) {
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        return sub(sums, new int[sums.length], 0, sums.length - 1, k);
    }

    private int sub(int[] sums, int[] aux, int start, int end, int k) {
        if (start >= end) {
            return 0;
        }
        int mid = start + (end - start) / 2;
        int res = sub(sums, aux, start, mid, k) + sub(sums, aux, mid + 1, end, k);
        res += merge(sums, aux, start, mid, end, k);
        return res;
    }

    private int merge(int[] sums, int[] aux, int start, int mid, int end, int k) {
        System.out.println("start: " + start + " mid: " + mid + " end: " + end);
        System.out.println("Before merge: " + Arrays.toString(sums));
        for (int i = start; i <= end; i++) {
            aux[i] = sums[i];
        }
        int count = 0;
        int i = start, j = mid + 1, t = start;
        int p = mid + 1;
        while (i <= mid) {
            while (p <= end && aux[p] - k < aux[i]) p++;
            for (int q = 0; q + p <= end; q++) {
                if (aux[p + q] - k > aux[i]) break;
                else count++;
            }

            while (j <= end && aux[i] > aux[j]) sums[t++] = aux[j++];
            sums[t++] = aux[i++];
        }

        while (j <= end) sums[t++] = aux[j++];
        System.out.println("After merge: " + Arrays.toString(sums));
        return count;
    }


    /**
     * 593. Valid Square
     *
     * @param p1 point 1
     * @param p2 point 2
     * @param p3 point 3
     * @param p4 point 4
     * @return if four points form a square.
     */
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] points = {p1, p2, p3, p4};
        Arrays.sort(points, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        double lengthP1P4 = Math.pow(points[0][0] - points[3][0], 2) + Math.pow(points[0][1] - points[3][1], 2);
        double lengthP2P3 = Math.pow(points[1][0] - points[2][0], 2) + Math.pow(points[1][1] - points[2][1], 2);
        if (lengthP1P4 != lengthP2P3 || lengthP1P4==0 || lengthP2P3==0) {
            return false;
        }
        if (points[0][1]==points[3][1]){
            return points[1][0]==points[2][0];
        }
//        double k1 = (double)((points[3][1] - points[0][1])) / (points[3][0] - points[0][0]);
//        double k2 = (double)((points[2][1] - points[1][1])) / (points[2][0] - points[1][0]);
        int val1 = (points[3][1] - points[0][1]) * (points[2][1] - points[1][1]);
        int val2 = (points[3][0] - points[0][0]) * (points[2][0] - points[1][0]);
        return val1 + val2 == 0 && isAngle90(points[0],points[1],points[2]);
    }

    private boolean isAngle90(int[] p0,int[] p1,int[] p2){
        if (p0[0]==p1[0]) return p0[1]==p2[1];
        int v1 = (p2[1] - p0[1]) * (p1[1] - p0[1]);
        int v2 = (p2[0] - p0[0]) * (p1[0] - p0[0]);
        return v1 + v2 == 0;
    }

    public static void main(String[] args) {
        MainInMac_2 m = new MainInMac_2();
        int[] input = {1, 1, 1};
//        System.out.println(m.subarraySum2(input, 2));
//        System.out.println(m.generateMatrix(3));
//        [1134,-2539]
//[492,-1255]
//[-792,-1897]
//[-150,-3181]

        int[] p1 = {0,0};
        int[] p2 = {0,1};
        int[] p3 = {0,2};
        int[] p4 = {1,2};
        System.out.println(m.validSquare(p1,p2,p3,p4));
    }
}
