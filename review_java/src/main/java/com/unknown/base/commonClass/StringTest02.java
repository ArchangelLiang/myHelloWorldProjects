package com.unknown.base.commonClass;

import java.util.Arrays;

public class StringTest02 {

    public static String trim(String str) {
        String s = null;
        char[] chars = str.toCharArray();
        int begin = 0;
        int end = str.length();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                begin = i + 1;
            } else {
                break;
            }
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            if (chars[i] == ' ') {
                end = i;
            } else {
                break;
            }
        }
        s = str.substring(begin, end);
        return s;
    }

    public static String reversal(String tarStr, String tarRvs) {
        String str = null;
        char[] chars = tarRvs.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            char a = chars[i];
            chars[i] = chars[chars.length - i - 1];
            chars[chars.length - i - 1] = a;
        }
        String ss = new String(chars);
        str = tarStr.replace(tarRvs, ss);
        return str;
    }

    public static int getRecount(String str, String childStr) {
        int count = 0;//初始化计数器
        int begin = 0;//开始索引
        int startIndex = 0;//用来跳出循环的标识
        while (startIndex < str.length()) {
            int index = str.indexOf(childStr, begin);//查询目标字符串的出现的索引位置
            if (index >= 0) {//大于等于0说明该字符串存在
                count++;//计数器加1
                begin = index + 1;//重置开始索引
                startIndex = begin;//重置循环条件，优化性能
            }
            startIndex++;//循环条件递增，最终结束循环
        }
        return count;
    }

    public static String getSort(String str) {
        String s = null;
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        s = new String(chars);
        return s;
    }

    public static String getMaxChildStr(String s1, String s2) {
        char[] min = null;
        if (s1.length() < s2.length()) {
            min = s1.toCharArray();
        } else {
            min = s2.toCharArray();
        }
        String reStr = "";
        for (int i = 0; i < min.length; i++) {
            String si = String.valueOf(min[i]);
            for (int j = i + 1; j < min.length; j++) {
                si += min[j];
                if (s1.contains(si)) {
                    if (si.length() > reStr.length()) {
                        reStr = si;
                    }
                }
            }
        }
        return reStr;
    }

    public static String getMaxChildStr2(String s1, String s2) {
        String min = s1.length() <= s2.length() ? s1 : s2;
        String max = s1.length() > s2.length() ? s1 : s2;
        int len = min.length();
        if (s1 != null && s2 != null) {
            for (int i = 0; i < len; i++) {
                for (int x = 0, y = len - i; y <= len; x++, y++) {
                    String subStr = min.substring(x, y);
                    if (max.contains(subStr)) {
                        return subStr;
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {

      /*  String str = "      he    llo     ";
        String trim = trim(str);
        System.out.println("-------"+trim+"-------");*/

    /*  String str = "myIsZio";
        String cdef = reversal(str, "Zio");
        System.out.println(cdef);*/

        /*String str = "hello zio,zio is finally  kamen rider,zio forte fortissimo!";
        int count = getRecount(str, "zio");
        System.out.println(count);*/

      /*  String s = "fedcba";
        String sort = getSort(s);
        System.out.println(sort);*/

        String s1 = "abhekdecadehelosdzioskdww";
        String s2 = "ziossdadecadedefawe";
        String maxChildStr = getMaxChildStr2(s1, s2);
        System.out.println(maxChildStr);
    }
}
