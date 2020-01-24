package com.unknown.base.collectionStudy;

import java.util.*;

public class Practice {

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(2);
        list.add(5);
        list.add(7);
        list.add(1);

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -o1.compareTo(o2);
            }
        });

        System.out.println(list);

        ArrayList<String> list_str = new ArrayList<>();

        HashMap<String, String> map = new HashMap<>();
        map.put("name", "zio");
        map.put("age", "12");
        map.put("education", "doctor");

        Set<String> key_set = map.keySet();
        Iterator<String> iterator = key_set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        Set<Map.Entry<String, String>> entry_set = map.entrySet();
        Iterator<Map.Entry<String, String>> entry_iterator = entry_set.iterator();
        while (entry_iterator.hasNext()) {
            System.out.println(entry_iterator.next());
            System.out.println(entry_iterator.next().getKey());
            System.out.println(entry_iterator.next().getValue());
        }

        Collection<String> values = map.values();
        values.forEach(System.out::println);

    }
}
