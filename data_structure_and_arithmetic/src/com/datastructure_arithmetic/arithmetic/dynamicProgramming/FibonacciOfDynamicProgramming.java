package com.datastructure_arithmetic.arithmetic.dynamicProgramming;

public class FibonacciOfDynamicProgramming {

    private static int[] memory = new int[15];


    public static int getFibNum(int n) {
        if (n < 1) {
            System.out.println("斐波那契数列从1开始");
            return -1;
        }
        if (n <= 2) {
            return 1;
        } else {
            if (memory[n] == 0) {
                memory[n] = getFibNum(n - 1) + getFibNum(n - 2);
            }
            return memory[n];
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.print(getFibNum(i)+" ");
        }
    }

}
