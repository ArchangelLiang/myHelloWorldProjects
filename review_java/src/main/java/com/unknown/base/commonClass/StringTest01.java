package com.unknown.base.commonClass;


public class StringTest01 {

    public static String reverse(String str, int startIndex, int endIndex) {
        StringBuilder sb = new StringBuilder(str.length());
        sb.append(str.substring(startIndex, endIndex));
        sb.reverse();
        sb.insert(0, str.substring(0, startIndex));
        sb.append(str.substring(endIndex));
        return sb.toString();
    }


    public static void main(String[] args) {

    /*    String a = null;

        String s1 = "hello decade";
        String sr = s1.replace("de", "zio");
        System.out.println(sr);
        char[] c = {};*/

    /*    String s1 = "hello";
        String s2 = "world";
        String s3 = "helloworld";
        String s4 = s1 + s2;
        System.out.println(s3 == s4);//false
        */
       /* String a = "abc";
        String b = "abd";
        int i = a.compareTo(b);
        System.out.println(i);*/

      /*  String s = new StringBuffer("decade").toString();
        System.out.println(s);*/

        String str = "abcdefg";
        String reverse = reverse(str, 2, 6);
        System.out.println(reverse);

    }
}
