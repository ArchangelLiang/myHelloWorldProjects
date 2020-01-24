package com.unknown.base.collectionStudy;

import java.util.LinkedHashSet;
import java.util.Objects;

public class Set_Test {

    public static void main(String[] args) {

        /*  new ArrayList<>();
         *//*  HashSet set = new HashSet();
        set.add("zio");
        set.add("grand—zio");
        set.add("decade");
        set.add("neo_decade");
        set.add("homo_zio");
        for (Object o : set) {
            System.out.println(o);
        }*//*
        String[] s = {};
        System.out.println(s.length);*/

      /*  HashSet set = new HashSet();
        set.add(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).format(LocalDateTime.now()));
        set.add("decade");
        set.add("zio");
        set.add(12);

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }*/

      /*  HashSet<String> set = new HashSet<>();
        set.add(null);
        set.add(null);
        set.add(null);
        System.out.println(set.size());*/

        LinkedHashSet<Zio> set = new LinkedHashSet<>();
       /* set.add("zio");
        set.add("zio");*/
        set.add(new Zio("angel"));
        set.add(new Zio("devil"));
        set.add(new Zio("god"));
        set.add(new Zio("god"));
        System.out.println(set);

    }
}

class Zio {
    String name;

    public Zio(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("this is equals");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zio zio = (Zio) o;
        return Objects.equals(name, zio.name);
    }

    @Override
    public int hashCode() {
        System.out.println("this is hashCode");
        int result = 0;
        result = name.hashCode();
        result = result * 31 + 2;
        return result;
    }
}