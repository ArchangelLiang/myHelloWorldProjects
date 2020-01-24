package com.unknown.base.io;

import java.io.Serializable;

public class TestObjectBean implements Serializable {

    private static final long serialVersionUID = 5235353123710L;

    transient String name;
    int age;

    public TestObjectBean() {
    }

    public TestObjectBean(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "TestObjectBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
