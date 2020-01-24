package com.datastructure_arithmetic.datastructure.recursion;

public class EightQueen {

    private static int[] array = new int[8];
    private static int count;

    public static void put(int n){
        if (n == array.length){
            printResult();
            return;
        }
        for (int i = 0; i < array.length; i++) {
            array[n] = i;
            if (check(n)){
                put(n+1);
            }
        }
    }
    private static boolean check(int n){
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    private static void printResult(){
        count++;
        for (int i : array) {
            System.out.print(i);
        }
        System.out.println();
    }


    public static void main(String[] args) {

        put(0);
        System.out.println(count);
    }
}
