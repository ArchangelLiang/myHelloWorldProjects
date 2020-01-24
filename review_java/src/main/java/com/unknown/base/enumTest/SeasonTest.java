package com.unknown.base.enumTest;

import java.util.Arrays;

public class SeasonTest {

    public static void main(String[] args) {
        Season season = Season.SPRING;
//        System.out.println(season);

        Season[] values = season.values();
        String s = Arrays.toString(values);
        System.out.println(s);

    }

}

interface hello {
    void hi();
}

enum Season implements hello {
    SPRING("春天") {
        @Override
        public void hi() {
            System.out.println("zio");
        }
    },
    SUMMER("夏天") {
        @Override
        public void hi() {
            System.out.println("decade");
        }
    },
    WINTER("冬天") {
        @Override
        public void hi() {
            System.out.println("zero");
        }
    };

    private final String name;
    private Object o;

    private Season(String name) {
        this.name = name;
    }

}
