package niuke.B;

import com.sun.xml.internal.ws.api.model.CheckedException;

import java.math.BigInteger;

/**
 * Created by DamonLiu-Lab on 2018/3/20.
 */

class Decrem implements Runnable{
    public void Ddecrement(double data){
        data = data - 1.0;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20000; i++) {
            System.out.println("running");
        }
    }
}

public class B1 {
    int data;
    public B1(){
        this(10);
    }
    public B1(int a){
        data = a;
    }
    void disp(){
        System.out.println(data);
    }


    static char[] getCharArray(byte[] arr){
        char[] carr = new char[4];
        int i=0;
        for (byte c : arr){
            carr[i] = (char)c++;
            i++;
        }
        return carr;
    }

    public static void main(String[] args) {
        int x=0,y=4,z=5;
        if (x>0)
            if (y<3)
                System.out.println("1");
            else if (y<4)
                System.out.println("2");
            else if (z >5)
                System.out.println("3");
        else
                System.out.println("4");
    }
}