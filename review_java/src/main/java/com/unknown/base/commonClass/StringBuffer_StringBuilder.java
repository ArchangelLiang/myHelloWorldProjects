package com.unknown.base.commonClass;

public class StringBuffer_StringBuilder {


    public static void main(String[] args) {

        StringBuffer sb = new StringBuffer("decade");
        for (int i = 0; i < sb.length(); i++) {
            System.out.println(sb.charAt(i));
        }
    }
}
