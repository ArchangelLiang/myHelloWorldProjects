package com.unknown.base.collectionStudy;

import java.util.ArrayList;

public class List_Test {

    public static void main(String[] args) {

       /* ArrayList<Object> list = new ArrayList<>();
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        System.out.println(list1);*/

        ArrayList<String> list = new ArrayList<>();
        list.add("zio");
        list.add("decade");
        list.add("canada");
        list.add("china");
        Object[] objects = list.toArray();
        for (Object object : objects) {
            System.out.println(object);
        }
    }
}
