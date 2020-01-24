package com.unknown.other;

public class TestParentAndSon {

    public static void main(String[] args) {

        Parent.Son s = new Parent.Son();
        System.out.println(s);

    }

}
class Parent {

    public static class Son {
        public Son(){

        }
    }

}
