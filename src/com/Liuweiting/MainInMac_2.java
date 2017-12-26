package com.Liuweiting;

import com.Liuweiting.DataStructure.ListNode;
import com.Liuweiting.DataStructure.TreeNode;
import com.sun.org.apache.xpath.internal.operations.Bool;
import sun.awt.image.ImageWatched;

import java.lang.reflect.Array;
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
        if (lengthP1P4 != lengthP2P3 || lengthP1P4 == 0 || lengthP2P3 == 0) {
            return false;
        }
        if (points[0][1] == points[3][1]) {
            return points[1][0] == points[2][0];
        }
//        double k1 = (double)((points[3][1] - points[0][1])) / (points[3][0] - points[0][0]);
//        double k2 = (double)((points[2][1] - points[1][1])) / (points[2][0] - points[1][0]);
        int val1 = (points[3][1] - points[0][1]) * (points[2][1] - points[1][1]);
        int val2 = (points[3][0] - points[0][0]) * (points[2][0] - points[1][0]);
        return val1 + val2 == 0 && isAngle90(points[0], points[1], points[2]);
    }

    private boolean isAngle90(int[] p0, int[] p1, int[] p2) {
        if (p0[0] == p1[0]) return p0[1] == p2[1];
        int v1 = (p2[1] - p0[1]) * (p1[1] - p0[1]);
        int v2 = (p2[0] - p0[0]) * (p1[0] - p0[0]);
        return v1 + v2 == 0;
    }

    /**
     * 215. Kth Largest Element in an Array
     *
     * @param nums all the numbers, unsorted.
     * @param k    the k's largest.
     * @return number result.
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }


    /**
     * 334. Increasing Triplet Subsequence
     *
     * @param nums array of number.
     * @return if there is an increasing triplet subsequence.
     */

    public boolean increasingTriplet(int[] nums) {
        int count[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    count[i] = Math.max(count[j] + 1, count[i]);
                }
                if (count[i] >= 2) {
                    return true;
                }
            }
        }
        return false;
    }


    int[] candidates;

    /**
     * can reuse the candidates.
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        List<List<Integer>> result = new ArrayList<>();
        backtrace(result, new ArrayList<>(), 0, target);
        return result;
    }

    private void backtrace(List<List<Integer>> result, List<Integer> current, int currentSum, int target) {
        if (currentSum > target) return;
        if (currentSum == target) {
            result.add(current);
            return;
        }
        for (Integer tmp : this.candidates) {
            if (current.size() > 0 && tmp < current.get(current.size() - 1)) continue;
            current.add(tmp);
            backtrace(result, current, currentSum + tmp, target);
            current.remove(tmp);
        }
    }


    /**
     * 找到数组A和B的共同子数组
     *
     * @param A 数组
     * @param B 数组
     * @return 共同数组
     */
    public int findLength(int[] A, int[] B) {
        int[][] map = new int[A.length + 1][B.length + 1];
        int max = 0;
        for (int i = 0; i < A.length + 1; i++) {
            for (int j = 0; j < B.length + 1; j++) {
                if (i == 0 || j == 0) {
                    map[i][j] = 0;
                } else {
                    if (A[i - 1] == B[j - 1]) {
                        map[i][j] = map[i - 1][j - 1] + 1;
                        max = Math.max(max, map[i][j]);
                    }
                }
            }
        }
        return max;
    }


    /**
     * rotate the input matrix 90 degree, clockwise.
     *
     * @param matrix input matrix
     */
    public void rotate(int[][] matrix) {
        //Step1, 沿2-4对折。
        //Step2, 沿水平对翻。
        int tmp = 0;
        int N = matrix.length;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (j - i > N) {
                    tmp = matrix[i][j];
                    matrix[i][j] = matrix[N - 1 - j][N - 1 - i];
                    matrix[N - 1 - j][N - 1 - i] = tmp;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= N / 2; j++) {
                tmp = matrix[i][j];
                matrix[i][j] = matrix[i][N - j - 1];
                matrix[i][N - j - 1] = tmp;
            }
        }
    }


    /**
     * 416. Partition Equal Subset Sum
     * https://leetcode.com/problems/partition-equal-subset-sum/description/
     *
     * @param nums input numbers.
     * @return if it can be split into two subarrays with equal sum.
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int max = -1;
        for (int tmp : nums) {
            sum += tmp;
            max = Math.max(max, tmp);
        }

        if (sum % 2 == 1) return false;
        int target = sum / 2;
        if (max > target) return false;
        boolean[] tmp = new boolean[nums.length];
//        Arrays.fill(tmp,0);
        return canFormTarget(nums, tmp, target);
    }

    HashMap<Integer, Boolean> canFormMap = new HashMap<>();

    private boolean canFormTarget(int[] nums, boolean used[], int target) {
        if (canFormMap.containsKey(target)) return canFormMap.get(target);
        if (target == 0) {
            canFormMap.put(target, true);
            return true;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (nums[i] > target) continue;
            used[i] = true;
            boolean tmp = canFormTarget(nums, used, target - nums[i]);
            if (tmp) {
                canFormMap.put(target - nums[i], true);
                return true;
            }
            used[i] = false;
        }
        canFormMap.put(target, false);
        return false;
    }

    /**
     * https://leetcode.com/problems/minimum-path-sum/description/
     *
     * @param grid the input m*n matrix. All elements are none negative.
     * @return return the minimum path sum.
     */
    public int minPathSum(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) continue;
                int left = Integer.MAX_VALUE, up = Integer.MAX_VALUE;
                if (j > 0) {
                    left = grid[i][j - 1];
                }
                if (i > 0) {
                    up = grid[i - 1][j];
                }
                grid[i][j] = Math.min(left, up) + grid[i][j];
            }
        }

        return grid[grid.length - 1][grid[0].length];
    }

//    public int findMaxForm(String[] strs, int m, int n) {
//        int counter = 0;
//        max = 0;
//        int[][] map = new int[strs.length][2];  // each row is  number of 0, number of 1, is used.
//        boolean [] used = new boolean[strs.length];
//        for (int i=0;i<strs.length;i++){
//            String s  = strs[i];
//            for (char c : s.toCharArray()){
//                map[i][c-'0']++;
//            }
//        }
//        backtrace(map,m,n,0,used);
//        return max;
//    }
//    static int max;
//    private void backtrace(int[][] map,int leftZero,int leftOnes,int currentIndex,boolean used[]){
//        int counter = 0;
//        for (boolean b : used){
//            if (b) counter++;
//        }
//        max = Math.max(counter,max);
//        for (int i = currentIndex; i < used.length; i++) {
//            if (map[i][0] <= leftZero && map[i][1] <= leftOnes && !used[i]) {
//                used[i] = true;
//                backtrace(map, leftZero - map[i][0], leftOnes - map[i][1], currentIndex + 1, used);
//                used[i] = false;
//            }
//        }
//    }

    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            int[] count = count(s);
            for (int i = m; i >= count[0]; i--)
                for (int j = n; j >= count[1]; j--)
                    dp[i][j] = Math.max(1 + dp[i - count[0]][j - count[1]], dp[i][j]);
        }
        return dp[m][n];
    }

    public int[] count(String str) {
        int[] res = new int[2];
        for (int i = 0; i < str.length(); i++)
            res[str.charAt(i) - '0']++;
        return res;
    }


    /**
     * https://leetcode.com/problems/increasing-subsequences/description/
     *
     * @param nums input array of number.
     * @return all subsequences that is increasing.
     */

    public List<List<Integer>> findSubsequences(int[] nums) {
        return findSubsequences(nums, nums.length - 1);
    }


    public List<List<Integer>> findSubsequences(int[] nums, int endIndex) {
        if (endIndex == 0) {

        }
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> list : findSubsequences(nums, endIndex - 1)) {
            if (list.get(list.size() - 1) <= nums[endIndex]) {
                List<Integer> tmp = new ArrayList<>(list);
                tmp.add(nums[endIndex]);
                result.add(tmp);
            } else {
                result.add(list);
            }
        }
        return result;
    }


    /**
     * https://leetcode.com/problems/redundant-connection/description/
     *
     * @param edges all the edges, undirected.
     * @return the last edge that caused the graph to be none-tree structure.
     */
    public int[] findRedundantConnection(int[][] edges) {
        int N = edges.length;
        int[] root = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            root[i] = i;
        }
        for (int[] edge : edges) {
            int head = edge[0];
            int tail = edge[1];
            while (root[head] != head) {
                head = root[head];
            }
            while (root[tail] != tail) {
                tail = root[tail];
            }
            if (head == tail) {
                return edge;
            }

            int smallRoot = Math.min(head, tail);
            int bigRoot = Math.max(head, tail);
//            for (int i = 1; i <= N; i++) {
//                if (root[i]==bigRoot)
//                    root[i] = smallRoot;
//            }
            root[bigRoot] = smallRoot;
            root[edge[1]] = smallRoot;
            root[edge[0]] = smallRoot;
        }
        return null;
    }

    /**
     * https://leetcode.com/problems/search-a-2d-matrix-ii/description/
     *
     * @param matrix input
     * @param target searching target
     * @return if target exist in matrix.
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;
        if (n == 0) return false;
        return searchMatrix(matrix, target, 0, 0, m, n);
    }

    private static boolean searchMatrix(int[][] matrix, int target, int beginX, int beginY, int endX, int endY) {
        System.out.println("searching from (" + beginX + "," + beginY + ") to (" + endX + "," + endY + ")");
        if (beginX >= matrix.length || beginY >= matrix[0].length) {
            return false;
        }
        if (beginX == endX - 1 && beginY == endY - 1 || (beginX == endX && beginY == endY))
            return matrix[beginX][beginY] == target;
        if (beginX == endX && beginY == endY - 1) return matrix[beginX][beginY] == target;
        if (beginX == endX - 1 && beginY == endY) return matrix[beginX][beginY] == target;
        int middleX = (int) (beginX * 0.5 + (endX) * .5);
        int middleY = (int) (beginY * .5 + (endY) * .5);
        if (matrix[middleX][middleY] == target) return true;

        if (matrix[middleX][middleY] > target) {
            if (beginX == endX) {
                return searchMatrix(matrix, target, beginX, beginY, endX, middleY - 1);
            }
            if (beginY == endY) {
                return searchMatrix(matrix, target, beginX, beginY, middleX - 1, endY);
            }
            return searchMatrix(matrix, target, beginX, beginY, middleX, middleY) || searchMatrix(matrix, target, beginX, middleY, middleX, endY) || searchMatrix(matrix, target, middleX, beginY, endX, middleY);
        }
        if (matrix[middleX][middleY] < target) {
            if (beginX == endX) {
                return searchMatrix(matrix, target, beginX, middleY + 1, endX, endY);
            }
            if (beginY == endY) {
                return searchMatrix(matrix, target, middleX + 1, beginY, endX, endY);
            }
            return searchMatrix(matrix, target, middleX, middleY, endX, endY) || searchMatrix(matrix, target, beginX, middleY, middleX, endY) || searchMatrix(matrix, target, middleX, beginY, endX, middleY);
        }
        return false;
    }


    /**
     * https://leetcode.com/problems/longest-increasing-subsequence/description/
     * <p>
     * use the idea of tail array, and because it is sorted, thus we can improve the speed to nlogn.
     *
     * @param nums the input array.
     * @return the longest increasing subsequence.
     */
    int maxLIS = -1;

    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int i = 0, j = size;
            while (i != j) {
                int m = (i + j) / 2;
                if (tails[m] < x)
                    i = m + 1;
                else
                    j = m;
            }
            tails[i] = x;
            if (i == size) ++size;
        }
        return size;
    }

    private void backtraceLIS(int[] nums, int index, List<Integer> list) {
        maxLIS = Math.max(maxLIS, list.size());
        if (index == nums.length) return;
        if (list.size() == 0 || nums[index] > list.get(list.size() - 1)) {
            list.add(nums[index]);
            backtraceLIS(nums, index + 1, list);
            list.remove(list.size() - 1);
        }
        backtraceLIS(nums, index + 1, list);
    }


    /**
     * https://leetcode.com/problems/swap-nodes-in-pairs/description/
     * swap each two nodes.
     *
     * @param head the head of linked list.
     * @return the swap result linked list head.
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode originHead = head;
        ListNode next = head.next;
        ListNode tmp = swapPairs(next.next);
        originHead.next = tmp;
        next.next = originHead;
        return next;
    }


    /**
     * https://leetcode.com/problems/knight-probability-in-chessboard/description/
     *
     * @param N the board is N * N
     * @param K total steps
     * @param r start point row
     * @param c start point column
     * @return the possibility that the chess is still on board.
     */
    double[][] map;

    public double knightProbability(int N, int K, int r, int c) {
        //Consider1. if the calculate of
        map = new double[N][N];
        double total = totalPossibleSteps(N, r, c, K);
        return total / Math.pow(8, K);
    }

    private double totalPossibleSteps(int N, int r, int c, int leftStep) {
        if (map[r][c] != 0) return map[r][c];
        if (!isValid(r, c, N)) return 0;
        if (leftStep == 0) return 1;
        int counter = 0;
        if (isValid(r - 1, c - 2, N)) {
            counter += totalPossibleSteps(N, r - 1, c - 2, leftStep - 1);
        }
        if (isValid(r - 2, c - 1, N)) {
            counter += totalPossibleSteps(N, r - 2, c - 1, leftStep - 1);
        }
        if (isValid(r - 2, c + 1, N)) {
            counter += totalPossibleSteps(N, r - 2, c + 1, leftStep - 1);
        }
        if (isValid(r - 1, c + 2, N)) {
            counter += totalPossibleSteps(N, r - 1, c + 2, leftStep - 1);
        }
        if (isValid(r + 1, c + 2, N)) {
            counter += totalPossibleSteps(N, r + 1, c + 2, leftStep - 1);
        }
        if (isValid(r + 2, c + 1, N)) {
            counter += totalPossibleSteps(N, r + 2, c + 1, leftStep - 1);
        }
        if (isValid(r + 2, c - 1, N)) {
            counter += totalPossibleSteps(N, r + 2, c - 1, leftStep - 1);
        }
        if (isValid(r + 1, c - 2, N)) {
            counter += totalPossibleSteps(N, r + 1, c - 2, leftStep - 1);
        }
        map[r][c] = counter;
        return counter;
    }

    private static boolean isValid(int r, int c, int N) {
        return !(r < 0 || r >= N || c < 0 || c >= N);
    }

    /**
     * @param args
     */
    int[][] moves = {{1, 2}, {1, -2}, {2, 1}, {2, -1}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};

    public double knightProbability2(int N, int K, int r, int c) {
        int len = N;
        double dp0[][] = new double[len][len];
        for (double[] row : dp0) Arrays.fill(row, 1);
        for (int l = 0; l < K; l++) {
            double[][] dp1 = new double[len][len];
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    for (int[] move : moves) {
                        int row = i + move[0];
                        int col = j + move[1];
                        if (isLegal(row, col, len))
                            dp1[i][j] += dp0[row][col];
                    }
                }
            }
            dp0 = dp1;
        }
        return dp0[r][c] / Math.pow(8, K);
    }

    private boolean isLegal(int r, int c, int len) {
        return r >= 0 && r < len && c >= 0 && c < len;
    }


    /**
     * swap 2 digits in num, to make num increase mostly.
     * this solution is wrong, because if the max num is just in position,
     * you cannot deal with this condition.
     *
     * @param num the input num.
     * @return the result.
     */
//    public int maximumSwap(int num) {
//        String tmp = num + "";
//        char max = 0;
//        int maxIndex = -1;
//        int index = 0;
//        for (char c : tmp.toCharArray() ){
//            if (c> max){
//                maxIndex = index;
//            }
//            max = (char) Math.max(max,c);
//            index++;
//        }
//        char[] array = tmp.toCharArray();
//        for (int i = 0; i < Math.min(maxIndex,tmp.length()); i++) {
//            if (tmp.charAt(i)< max){
//                array[maxIndex] = tmp.charAt(i);
//                array[i] = max;
//                break;
//            }
//        }
//        return Integer.parseInt(new String(array));
//    }
    public int maximumSwap(int num) {
        char[] tmp = (num + "").toCharArray();
        int[] dp = new int[tmp.length];
        dp[tmp.length - 1] = 0;
        for (int i = tmp.length - 2; i >= 0; i--) {
            if (tmp[i] > tmp[i + 1]) {
                for (int j = i + 1; j < tmp.length; j++) {
                    if (tmp[i] < tmp[j]) {
                        dp[i] = dp[j] + 1;
                        break;
                    }
                }
            } else if (tmp[i] == tmp[i + 1]) {
                dp[i] = dp[i + 1];
            } else {
                dp[i] = dp[i + 1] + 1;
            }
        }

        for (int i = 0; i < dp.length; i++) {
            if (dp[i] != 0) {
                int maxIndex = i;
                for (int j = i + 1; j < tmp.length; j++) {
                    if (tmp[j] >= tmp[maxIndex]) {
                        maxIndex = j;
                    }
                }
                tmp[maxIndex] += tmp[i];
                tmp[i] = (char) (tmp[maxIndex] - tmp[i]);
                tmp[maxIndex] = (char) (tmp[maxIndex] - tmp[i]);
                break;
            }
        }
        return Integer.parseInt(new String(tmp));
    }


    /**
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
    }


    /**
     * 652. Find Duplicate Subtrees
     * https://leetcode.com/problems/find-duplicate-subtrees/description/
     * <p>
     * TODO really solve this.
     * I think this problem is NP-Complete, so the time is not poly.
     *
     * @param root the root of tree.
     * @return a list of duplicate trees.
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        if (root == null) return list;
        dpsFindDuplicateSubtrees(root.left, list, root);
        dpsFindDuplicateSubtrees(root.right, list, root);
        return list;
    }

    /**
     * @param root           the current searching tree.
     * @param list           the result list.
     * @param searchingRange the search range.
     */
    private static void dpsFindDuplicateSubtrees(TreeNode root, List<TreeNode> list, TreeNode searchingRange) {
        if (root == null || searchingRange == null) return;

//        if (contains(searchingRange,root) && !list.contains(root)){
//            list.add(root);
//        }
        dpsFindDuplicateSubtrees(root.left, list, searchingRange);
        dpsFindDuplicateSubtrees(root.right, list, searchingRange);
    }

    //    private static TreeNode contains(TreeNode mainTree,TreeNode pattern){
//        if (mainTree==null && pattern==null) return true;
//        if (mainTree==null || pattern==null) return null;
//
//        if (mainTree.val == pattern.val && mainTree!=pattern){
//            TreeNode searching = contains(mainTree.left,pattern.left);
//            TreeNode searching2 = contains(mainTree.right,pattern.right);
//        }
//        if (mainTree==pattern || mainTree.val!=pattern.val){
//            TreeNode searching = contains(mainTree.left,pattern) || contains(mainTree.right,pattern);
//            if (searching!=null) return searching;
//        }
//        return null;
//    }
    public int nthSuperUglyNumber(int n, int[] primes) {
        int counter = 0;
        for (int i = 1; counter < 100 && counter < n; i++) {
            if (isUgly(i, primes)) {
                counter++;
                if (counter == n) {
                    return i;
                }
            }
        }
        return -1;
    }

    HashMap<Integer, Boolean> isNumberUgly = new HashMap<Integer, Boolean>();

    private boolean isUgly(int m, int[] primes) {
        if (m == 1) return true;
        for (int i = 2; i <= m; i++) {
            if (m % i == 0 && isPrime(i)) {
                if (!isNumberUgly.getOrDefault(i, true)) {
                    isNumberUgly.put(m, false);
                    return false;
                } else {
                    boolean isInPrimes = false;
                    for (int tmp : primes) {
                        if (tmp == i) {
                            isInPrimes = true;
                            break;
                        }
                    }
                    if (isInPrimes) continue;
                    else {
                        isNumberUgly.put(m, false);
                        return false;
                    }
                }
            }
        }
        isNumberUgly.put(m, true);
        return true;
    }

    /**
     * 1~10^6's judgement of whether if it's prime.
     *
     * @param i
     * @return
     */
    HashSet<Integer> prime;

    private boolean isPrime(int i) {
        if (prime == null) {
            prime = new HashSet<>();
            for (int j = 1; j < 1000000; j++) {
                System.out.println(j);
                boolean isPrime = true;
                for (int k = 2; k < Math.pow(j, 0.5); k++) {
                    if (j % k == 0) {
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime) prime.add(j);
            }
        }

        return prime.contains(i);
    }

    public int nthSuperUglyNumberI(int n, int[] primes) {
        int[] ugly = new int[n];
        int[] idx = new int[primes.length];

        ugly[0] = 1;
        for (int i = 1; i < n; i++) {
            //find next
            ugly[i] = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++)
                ugly[i] = Math.min(ugly[i], primes[j] * ugly[idx[j]]);

            System.out.println("");
            //slip duplicate
            for (int j = 0; j < primes.length; j++) {
                while (primes[j] * ugly[idx[j]] <= ugly[i]) idx[j]++;
            }
        }

        return ugly[n - 1];
    }


    public int[] asteroidCollision(int[] asteroids) {
        int size = asteroids.length;
        while (true) {
            int oldSize = size;
            for (int i = 0; i < asteroids.length - 1; i++) {
                if (asteroids[i] == 0) continue;
                if (asteroids[i] > 0) {
                    int j;
                    for (j = i + 1; j < asteroids.length; j++) {
                        if (asteroids[j] != 0) {
                            break;
                        }
                    }
                    if (j == asteroids.length) break;
                    if (asteroids[j] < 0) {
                        if (asteroids[i] > -asteroids[j]) {
                            asteroids[j] = 0;
                            size--;
                            break;
                        } else if (asteroids[i] < -asteroids[j]) {
                            asteroids[i] = 0;
                            size--;
                            break;
                        } else {
                            asteroids[i] = 0;
                            asteroids[j] = 0;
                            size -= 2;
                            break;
                        }
                    } else if (asteroids[j] > 0) {
                    } else {
                    }
                }
            }
            if (oldSize == size) break;
        }
        int[] result = new int[size];
        int index = 0;
        for (int tmp : asteroids) {
            if (tmp != 0) {
                result[index++] = tmp;
            }
        }
        return result;
    }


    /**
     * 662. Maximum Width of Binary Tree
     * https://leetcode.com/problems/maximum-width-of-binary-tree/description/
     *
     * @param root a binary tree's root.
     * @return the maxLength of all the levels.
     */
    public int widthOfBinaryTree(TreeNode root) {

        return -1;
    }


    /**
     * 279. Perfect Squares
     * https://leetcode.com/problems/perfect-squares/description/
     *
     * @param n the target sum.
     * @return total number of square numbers needed to add up to @param(n).
     */
    HashMap<Integer, Integer> memory = new HashMap();

    public int numSquares(int n) {
        if (memory.containsKey(n)) return memory.get(n);
        if (n <= 3) {
            return n;
        }
        int result = Integer.MAX_VALUE;
        for (int i = (int) Math.sqrt(n); i >= 1; i--) {
            if (i * i <= n) {
                result = Math.min(result, 1 + numSquares(n - i * i));
            }
        }
        memory.put(n, result);
        return result;
    }


    /**
     * https://leetcode.com/problems/sum-root-to-leaf-numbers/description/
     * 129. Sum Root to Leaf Numbers
     * <p>
     * consider dfs search.
     *
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        List<Integer> res = new ArrayList<>();
        int[] sum = new int[1];
        res.add(root.val);
        dfs(res, root, sum);
        return sum[0];
    }

    private static void dfs(List<Integer> path, TreeNode root, int[] sum) {
        if (root.left == null && root.right == null) {
            int base = 1;
            for (int i = path.size() - 1; i >= 0; i--) {
                sum[0] += base * path.get(i);
                base *= 10;
            }
        }
        if (root.left != null) {
            path.add(root.left.val);
            dfs(path, root.left, sum);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            path.add(root.right.val);
            dfs(path, root.right, sum);
            path.remove(path.size() - 1);
        }
    }


    /**
     * 450. Delete Node in a BST
     * <p>
     * https://leetcode.com/problems/delete-node-in-a-bst/description/
     *
     * @param root root of a binary search tree.
     * @param key  the node to remove.
     * @return the result tree.
     */
    private static TreeNode treeRoot;

    public TreeNode deleteNode(TreeNode root, int key) {
        treeRoot = root;
        deleteNodeHelper(root, key, null, false);
        return treeRoot;
    }

    private void deleteNodeHelper(TreeNode root, int key, TreeNode parent, boolean isleft) {
        if (root == null) return;
        if (root.val == key) {
            deleteNodeInBST(treeRoot, root, parent, isleft);
        } else {
            if (root.val > key) {
                deleteNodeHelper(root.left, key, root, true);
            } else {
                deleteNodeHelper(root.right, key, root, false);
            }
        }
    }

    private void deleteNodeInBST(TreeNode treeRoot, TreeNode toDelete, TreeNode toDeleteParent, boolean isLeft) {
        if (toDelete.left == null) {
            if (toDeleteParent == null) {
                this.treeRoot = toDelete.right;
                return;
            }
            if (isLeft) {
                toDeleteParent.left = toDelete.right;
            } else {
                toDeleteParent.right = toDelete.right;
            }
        } else if (toDelete.right == null) {
            if (toDeleteParent == null) {
                this.treeRoot = toDelete.left;
                return;
            }
            if (isLeft) {
                toDeleteParent.left = toDelete.left;
            } else {
                toDeleteParent.right = toDelete.left;
            }
        } else {//to delete's node has two children.
            //Step1. find the smallest node in toDelete's right branch.
            TreeNode tmp = toDelete.right;
            TreeNode tmpParent = toDelete;
            while (tmp.left != null) {
                tmpParent = tmp;
                tmp = tmp.left;
            }

            // tmp is the node to replace toDelete node.
            //
            if (tmpParent != toDelete) {
                tmpParent.left = tmp.right;
                tmp.left = toDelete.left;
                tmp.right = toDelete.right;
                if (toDeleteParent == null) {
                    this.treeRoot = tmp;
                    return;
                }
                if (isLeft) {
                    toDeleteParent.left = tmp;
                } else {
                    toDeleteParent.right = tmp;
                }
            } else {
                tmp.left = toDelete.left;
                if (toDeleteParent == null) {
                    this.treeRoot = tmp;
                    return;
                }
                if (isLeft) {
                    toDeleteParent.left = tmp;
                } else {
                    toDeleteParent.right = tmp;
                }
            }
        }
    }


    /**
     * https://leetcode.com/problems/longest-absolute-file-path/description/
     * if match : \n\t then the next string is a directory.
     * if match : \n\t\t then the next is a file.
     *
     * @param input the file system representation
     * @return the longest file path string length.
     */
    HashMap<String, ArrayList<String>> file2content = new HashMap<>();

    public int lengthLongestPath(String input) {

//        String rootDirectory = "";
//        int i = -1;
//        for (i = 0; i < input.length(); i++) {
//            if (input.charAt(i)=='\\'){
//                rootDirectory = input.substring(0,i);
//                break;
//            }
//        }
//        file2content.put(rootDirectory,new ArrayList<>());
        String parentDir = null;
        Stack<String> currentFilePathStack = new Stack<>();
        int begin = 0, end = 0;
        while (end <= input.length()) {
            while (end + 4 <= input.length() && input.substring(end, end + 4).compareToIgnoreCase("\\n\\t") != 0) {
                end++;
            }
            if (end + 4 == input.length()) {
                String lastFile = input.substring(begin);
                if (parentDir == null) {
                    parentDir = lastFile;
                    file2content.put(parentDir, new ArrayList<>());
                } else {
                    ArrayList<String> dir = file2content.getOrDefault(parentDir, new ArrayList<>());
                    dir.add(lastFile);
                }
                break;
            }
            //current end meet a \n\t.
            //condition1. if this is just a \n\t not a \n\t\t
            if (input.substring(end, end + 6).compareToIgnoreCase("\\n\\t\\t") != 0) {
                String currentDir = input.substring(0, end);
                if (parentDir == null) {
                    parentDir = currentDir;
                    file2content.put(parentDir, new ArrayList<>());
                } else {
                    ArrayList<String> dir = file2content.getOrDefault(parentDir, new ArrayList<>());
                    dir.add(currentDir);
                }
                parentDir = currentDir;
                begin = end + 4;
                end = begin;
            } else { //meet a file.
                String fileName = input.substring(0, end);
                ArrayList<String> dir = file2content.getOrDefault(parentDir, new ArrayList<>());
                dir.add(fileName);
                begin = end + 6;
                end = begin;
            }
        }
        return -1;
    }


    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
//        int[][] newBoard = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int sum = 0;
                sum = getAllLiveNeighbours(board, i, j);
                if (1 == board[i][j]) {
                    if (sum < 2) {
                        board[i][j] = 1;
                    } else if (sum < 4) {
                        board[i][j] = 3;
                    } else {
                        board[i][j] = 1;
                    }
                } else {
                    if (sum == 3) {
                        board[i][j] = 2;
                    }
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = board[i][j] >> 1;
            }
        }
    }

    private static int getAllLiveNeighbours(int[][] boards, int i, int j) {
        int sum = 0;
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                if (i + k >= 0 && i + k < boards.length && j + l >= 0 && j + l < boards[0].length && (k != 0 || l != 0)) {
                    sum += boards[i + k][j + l] & 1;
                }
            }
        }
        return sum;
    }

    /**
     * https://leetcode.com/problems/container-with-most-water/description/
     * 11. Container With Most Water
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {


        return -1;
    }


    /**
     * constant space complexity.
     * <p>
     * Consider record the previous visited node.
     * Add a label to mark if this node is finished visit.
     * <p>
     * TODO:
     * <p>
     * BFS or DFS? BFS need to record the current level's elements. So the space complexity will not be constant.
     * The only result is DFS.
     * when dfs, we
     *
     * @param root the root of
     */


    TreeLinkNode tmp = null;

    public void connect(TreeLinkNode root) {
        tmp = null;
        dfs(root);
    }

    private void dfs(TreeLinkNode root) {
        if (root.left != null) {
            root.left.next = root.right;
            root.right.next = root.next == null ? null : root.next.left;
        }
        dfs(root.left);
        dfs(root.right);
    }

//    private static void dfsRightChild(TreeLinkNode root,TreeLinkNode tmp){
//        if (tmp==null || root==null) return;
//        tmp.next = root.left;
//        root.left.next = root.right;
//        dfs(root.left);
//        dfsRightChild(root.right,root.left.right);
//    }


    /**
     * given a list of number, return the power set of all possible combinations.
     *
     * @param nums list of number.
     * @return all combinations.
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        subsetsWithDup(nums, result, new ArrayList<Integer>(), 0);
        return result;
    }

    private void subsetsWithDup(int[] nums, List<List<Integer>> result, List<Integer> current, int currentIndex) {
        result.add((List<Integer>) ((ArrayList) current).clone());
        for (int i = currentIndex; i < nums.length; i++) {
            if (i == currentIndex || nums[i] != nums[i - 1]) {
                current.add(nums[i]);
                subsetsWithDup(nums, result, current, i + 1);
                current.remove(current.size() - 1);
            }
        }
    }


    /**
     * backtrace problem.
     * 就是不带重复的子集和问题。
     *
     * @param nums the input nums.
     * @param k    the k subsets.
     * @return if array can be partitioned.
     */
    static int target;

    public boolean canPartitionKSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = 0;
        for (int tmp : nums) sum += tmp;
        if (sum % k != 0) return false;
        int subSetCount = k;
        int subSetSum = sum / k;
        target = subSetSum;

        ArrayList<Integer> usingIndex = new ArrayList<>();
        backTrace(nums, usingIndex, 0);

        return false;
    }

    private void backTrace(int[] nums, ArrayList<Integer> usingIndex, int currentIndex) {

    }


    public static void main(String[] args) {
        MainInMac_2 m = new MainInMac_2();
        int[] input = {2, 3, 5};
//        System.out.println(m.knightProbability(3, 2, 0, 0));
//        System.out.println(m.knightProbability2(3, 2, 0, 0));
//        System.out.println(m.maximumSwap(9973));
//        boolean tp = m.isPrime(200123);
//        System.out.println(m.nthSuperUglyNumberI(10,input));
        int[] ast = {-2, -2, 1, -2};
//        System.out.println(Arrays.toString(m.asteroidCollision(ast)));
//        System.out.println(m.lengthLongestPath("dir\\n\\tsub dir1\\n\\t\\tfile1.ext\\n\\t\\tsubsubdir1\\n\\tsubdir2\\n\\t\\tsubsubdir2\\n\\t\\t\\tfile2.ext"));

        int[][] input2 = {{1, 1},
                {1, 0}};
        m.gameOfLife(input2);

        int[] nums = {1, 2, 3, 4};
        System.out.println("Subset With Duplication`");
        System.out.println(m.subsetsWithDup(nums));
    }
}
