package com.unknown.framework.spring.bean.testIoc;

import java.util.*;

public class Test_bean {

    private String name;
    private int age;
    private Date birthday;

    private String[] myArray;
    private List<String> myList;
    private Map<String, Integer> myMap;
    private Set mySet;
    private Properties myPros;

    public Test_bean() {
    }

    public Test_bean(String name, int age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<String> getMyList() {
        return myList;
    }

    public void setMyList(List<String> myList) {
        this.myList = myList;
    }

    public Map<String, Integer> getMyMap() {
        return myMap;
    }

    public void setMyMap(Map<String, Integer> myMap) {
        this.myMap = myMap;
    }


    public Properties getMyPros() {
        return myPros;
    }

    public void setMyPros(Properties myPros) {
        this.myPros = myPros;
    }

    public String[] getMyArray() {
        return myArray;
    }

    public void setMyArray(String[] myArray) {
        this.myArray = myArray;
    }

    public Set getMySet() {
        return mySet;
    }

    public void setMySet(Set mySet) {
        this.mySet = mySet;
    }

    @Override
    public String toString() {
        return "Test_bean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", myArray=" + Arrays.toString(myArray) +
                ", myList=" + myList +
                ", myMap=" + myMap +
                ", mySet=" + mySet +
                ", myPros=" + myPros +
                '}';
    }
}
