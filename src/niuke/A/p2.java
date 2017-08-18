package niuke.A;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by DamonLiu on 2017/6/18.
 */
public class p2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T>0){
            T--;
            String s1 = sc.next();
            String s2 = sc.next();
            System.out.println(cal(s1,s2));
        }
    }

    public static int cal(String s1,String s2){
        int max_length = -1;
        ArrayList<String> allCombinations = new ArrayList<>();
        allCombinations(s1,s2,allCombinations,0);
        for(String s : allCombinations){
            int max = maxSubStringLength(s,new StringBuilder(s).reverse().toString());
            if (max > max_length){
                max_length = max;
            }
        }
        return max_length;
    }

    public static void allCombinations(String s1,String s2,ArrayList<String> result,int s2beginIndex){
//        if (s1.length() < s2.length()){
//            String tmp = s1;
//            s1 = s2;
//            s2 = tmp;
//        }
        if (0 == s2.length()){
            result.add(s1);
            return;
        }
        for (int i = s2beginIndex; i < s1.length(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(s1.substring(0,i));
            sb.append(s2.charAt(0));
            sb.append(s1.substring(i));
            allCombinations(sb.toString(),s2.substring(1),result,i+1);
        }

        result.add(s1+s2);
        return;
    }

    public static int maxSubStringLength(String s1,String s2){
//        int[][] result = lcs(s1,s2);
//        return result[s1.length()-1][s2.length()-1];
        return getLCString(s1.toCharArray(),s2.toCharArray());
    }

    public static int  getLCString(char[] str1, char[] str2) {
        int len1, len2;
        len1 = str1.length;
        len2 = str2.length;
        int maxLen = len1 > len2 ? len1 : len2;

        int[] max = new int[maxLen];// 保存最长子串长度的数组
        int[] maxIndex = new int[maxLen];// 保存最长子串长度最大索引的数组
        int[] c = new int[maxLen];

        int i, j;
        for (i = 0; i < len2; i++) {
            for (j = len1 - 1; j >= 0; j--) {
                if (str2[i] == str1[j]) {
                    if ((i == 0) || (j == 0))
                        c[j] = 1;
                    else
                        c[j] = c[j - 1] + 1;//此时C[j-1]还是上次循环中的值，因为还没被重新赋值
                } else {
                    c[j] = 0;
                }

                // 如果是大于那暂时只有一个是最长的,而且要把后面的清0;
                if (c[j] > max[0]) {
                    max[0] = c[j];
                    maxIndex[0] = j;

                    for (int k = 1; k < maxLen; k++) {
                        max[k] = 0;
                        maxIndex[k] = 0;
                    }
                }
                // 有多个是相同长度的子串
                else if (c[j] == max[0]) {
                    for (int k = 1; k < maxLen; k++) {
                        if (max[k] == 0) {
                            max[k] = c[j];
                            maxIndex[k] = j;
                            break; // 在后面加一个就要退出循环了
                        }
                    }
                }
            }
//            for (int temp : c) {
//                System.out.print(temp);
//            }
//            System.out.println();
        }
        //打印最长子字符串
        int max_length = -1;
        for (j = 0; j < maxLen; j++) {
            if (max[j] > 0) {
//                for (i = maxIndex[j] - max[j] + 1; i <= maxIndex[j]; i++)
//                    System.out.print(str1[i]);
//                System.out.println(" ");
                int length = max[j];
                if (length>max_length) max_length = length;
            }
        }
        return max_length;
    }
}
