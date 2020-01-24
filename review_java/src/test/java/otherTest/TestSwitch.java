package otherTest;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Vector;

public class TestSwitch {

    public static void main(String[] args) {

       /* Scanner sc = new Scanner(System.in);
        String name = sc.next();
        switch (name){
            case "zio":
                System.out.println("is zio");
                break;
            case "decade":
                System.out.println("is decade");
                break;
            default:
                System.out.println("判断结束");
        }*/

        int i = test03();
        System.out.println(i);
    }

    @Test
    public void test(){
      final int a = 2<<2;
        System.out.println(a);
    }

    @Test
    public void test01(){
        final char[] chars = new char[]{'a','b'};
        chars[0] = 's';
        System.out.println(chars);
    }

    @Test
    public void test02(){
        String a = "zio";
        String replace = a.replace("i", "s");
        System.out.println(a);
        Vector<Object> vector = new Vector<>();
        HashMap<Object, Object> map = new HashMap<>();
        HashSet<Object> set = new HashSet<>();

    }

    public static int test03(){
        int a = 1;
        try {
            return a;
        } finally {
            ++a;
        }
    }

}
