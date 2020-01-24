package com.unknown.base.genericity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Genericity_test {

    public static void main(String[] args) {

        Son<String> son = new Son<String>();
        List<String> list = son.list;

        List<String> strings = Son.sayHello(new String[]{"zio", "kiva"});
        System.out.println(strings);

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        list1 = list2;

        HashSet<? extends Object> set1 = null;
        HashSet<String> set2 = null;
        set1 = set2;
        set1.add(null);

    }
}

class decade<T> {

    int id;
    String name;
    int age;
    List<T> list;

    public decade() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

class Son<T> extends decade<T> {

    public String education;


    public static <E> List<E> sayHello(E[] message) {
        return Arrays.asList(message);
    }
}

