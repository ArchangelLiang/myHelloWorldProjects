package com.unknown.base.collectionStudy;

import java.util.*;

public class Map_Test {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "zio");
        map.put("b", "decade");

     /*   HashMap<String, String> map1 = new HashMap<>();
        map1.put("b","decade");
        map1.put("a","zio");

        System.out.println(map.equals(map1));*/

        Set<Map.Entry<String, String>> entries = map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        TreeMap treeMap = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });
    }
}
