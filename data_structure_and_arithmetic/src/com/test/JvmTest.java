package com.test;

public class JvmTest {

    public static void main(String[] args) {
        String s = new String("a")+new String("b");
        String w = s.intern();
        System.out.println(s == w);
    }
}
