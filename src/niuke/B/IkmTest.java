package niuke.B;

import java.util.*;

/**
 * Created by DamonLiu-Lab on 2018/3/20.
 */
public class IkmTest {
    public static void main(String[] args) {
        System.out.println(Math.round(123456789123456789.12f)==Integer.MAX_VALUE);
    }
}

class A{
    Integer i;
    public A(Integer b){
        i = b;
    }
    public boolean equals(A a){
        return false;
    }
    public boolean equals(Object o){
        return true;
    }
    public int hashCode(){
        return 32;
    }
}

class Storate{
    static String result = "";
    public static void main(String[] args) {
        String s1 = "abcdef";
        String s2 = new String(s1);
        if (s1==s2)
            System.out.println("== success");
        if (s1.equals(s2))
            System.out.println(".equals success");
    }

    static void method(int i){
        try{
            if (i==1){
                throw new Exception();
            }
        }catch (Exception e){
            result += "2";
            return;
        }finally {
            result += "3";
        }
        result += "4";
    }
}