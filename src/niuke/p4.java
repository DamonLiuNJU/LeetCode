package niuke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by DamonLiu on 2017/6/16.
 * n 个小区排成一列，编号为从 0 到 n-1 。一开始，美团外卖员在第0号小区，目标为位于第 n-1 个小区的配送站。
 给定两个整数数列 a[0]~a[n-1] 和 b[0]~b[n-1] ，在每个小区 i 里你有两种选择：
 1) 选择a：向前 a[i] 个小区。
 2) 选择b：向前 b[i] 个小区。

 把每步的选择写成一个关于字符 ‘a’ 和 ‘b’ 的字符串。求到达小区n-1的方案中，字典序最小的字符串。如果做出某个选择时，你跳出了这n个小区的范围，则这个选择不合法。
 • 当没有合法的选择序列时，输出 “No solution!”。
 • 当字典序最小的字符串无限长时，输出 “Infinity!”。
 • 否则，输出这个选择字符串。

 字典序定义如下：串s和串t，如果串 s 字典序比串 t 小，则
 • 存在整数 i ≥ -1，使得∀j，0 ≤ j ≤ i，满足s[j] = t[j] 且 s[i+1] < t[i+1]。
 • 其中，空字符 < ‘a’ < ‘b’。
 输入描述:
 输入有 3 行。

 第一行输入一个整数 n (1 ≤ n ≤ 10^5)。

 第二行输入 n 个整数，分别表示 a[i] 。

 第三行输入 n 个整数，分别表示 b[i] 。

 −n ≤ a[i], b[i] ≤ n


 输出描述:
 输出一行字符串表示答案。

 输入例子:
 7
 5 -3 6 5 -5 -1 6
 -6 1 4 -2 0 -2 0

 输出例子:
 abbbb

 */
public class p4 {

    public p4(){}

    static ArrayList<String> result = new ArrayList<>();

    public static String getToPositionN(int a[],int b[],int currentIndex,String prefix){
        if (currentIndex==a.length-1){
//            System.out.println(prefix);
            result.add(prefix);
            return prefix;
        }

        if (prefix.length() == a.length){
            return "";
        }

        if (currentIndex > a.length-1 || currentIndex < 0){
            return "";
        }

        String s1 = getToPositionN(a,b,currentIndex+a[currentIndex],prefix+"a");
        String s2 = getToPositionN(a,b,currentIndex+b[currentIndex],prefix+"b");

        return "";
    }


    public static void printResult(){
        if (result.size()==0){
            System.out.println("No solution!");
            return;
        }

        String minResult = result.remove(0);
        for (String s : result){
            if (compare(s,minResult) < 0){
                minResult = s;
            }
        }
        System.out.println(minResult);
    }

    /**
     * return -1 if s1 < s2.
     * @param s1
     * @param s2
     * @return
     */
    public static int compare(String s1,String s2){
        int length = Math.min(s1.length(),s2.length());
        for (int i = 0; i < length; i++) {
            int diff = s1.charAt(i) - s2.charAt(i);
            if (diff != 0){
                return diff;
            }
        }
        return s1.length() - s2.length();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int a[] = new int[N];
        int b[] = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            b[i] = sc.nextInt();
        }
        getToPositionN(a,b,0,"");
        printResult();
    }
}
