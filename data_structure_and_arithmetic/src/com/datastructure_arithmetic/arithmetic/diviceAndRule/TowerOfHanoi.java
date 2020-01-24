package com.datastructure_arithmetic.arithmetic.diviceAndRule;

public class TowerOfHanoi {

    public static void hanoiTower(int num,char a,char b,char c) {
        if (num == 1) {
            System.out.println(a + " 到 " + c);
        } else if (num >= 2) {
            hanoiTower(num - 1, a, c, b);
            System.out.println(a + " 到 " + c);
            hanoiTower(num - 1, b, a, c);
        }
    }

    public static void main(String[] args) {
        hanoiTower(5,'A','B','C');
    }

}
