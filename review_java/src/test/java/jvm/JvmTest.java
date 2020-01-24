package jvm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

public class JvmTest {

    public static void main(String[] args) {
        System.out.println(Son.b);
    }

}

interface Parent {
    int a = 1;
}

interface Son extends Parent {
    int b = new Random(9).nextInt();
}
