package com.datastructure_arithmetic.arithmetic.search;

import java.util.Arrays;

public class SearchArithmetic {

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 7, 4, 9, 8, 0, -1};
//        int i = OrderSearch(arr, -1);
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
//        int i = binarySearch(arr, 9);
//        int i = insertValueSearch(arr, -1);
        int i = fibonacciSearch(arr, 4);
        System.out.println(i);
    }

    /**
     * 用来获取一个标准的斐波那契数组
     * @param size 数组的长度
     * @return 返回一个标准的斐波那契数组
     */
    public static int[] fibonacciArray(int size) {
        int[] arr = new int[size];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr;
    }

    /**
     * 斐波那契查找算法【难点】
     * @param arr 要查找的数组(该数组必需为有序数组)
     * @param target 目标值
     * @return 查到的目标值所在的索引
     */
    public static int fibonacciSearch(int[] arr, int target) {
        int low = 0;
        int height = arr.length - 1;
        int k = 0;
        int mid = 0;
        int[] fibArr = fibonacciArray(20);
        while (height > fibArr[k] - 1) {
            k++;
        }
        int[] array = Arrays.copyOf(arr, fibArr[k]);
        for (int i = height + 1; i < array.length; i++) {
            array[i] = arr[height];
        }
        while (low <= height) {
            mid = low + fibArr[k - 1] - 1;
            if (target < array[mid]) {
                height = mid - 1;
                k--;
            } else if (target > array[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                return Math.min(mid, height);
            }
        }
        return -1;
    }

    /**
     * 插值查找【难点】
     *
     * @param arr    要查找的数组(该数组必需为有序数组)
     * @param target 目标值
     * @return 查到的目标值所在的索引
     * 注意：在关键字分布均匀的情况下比较适用
     */
    public static int insertValueSearch(int[] arr, int target) {
        if (target < arr[0] || target > arr[arr.length - 1]) {
            return -1;
        }
        int startIndex = 0;
        int overIndex = arr.length - 1;
        while (startIndex <= overIndex) {
            int middle = startIndex + (overIndex - startIndex) * (target - arr[startIndex]) / (arr[overIndex] - arr[startIndex]);
            if (arr[middle] == target) {
                return middle;
            } else if (target > arr[middle]) {
                startIndex = middle + 1;
            } else {
                overIndex = middle - 1;
            }
        }
        return -1;
    }

    /**
     * 二分法查找
     *
     * @param arr    要查找的数组(该数组必需为有序数组)
     * @param target 目标值
     * @return 查到的目标值所在的索引
     */
    public static int binarySearch(int[] arr, int target) {
        if (target < arr[0] || target > arr[arr.length - 1]) {
            return -1;
        }
        int startIndex = 0;
        int overIndex = arr.length - 1;
        while (startIndex <= overIndex) {
            int middle = (startIndex + overIndex) / 2;
            if (arr[middle] == target) {
                return middle;
            } else if (target > arr[middle]) {
                startIndex = middle + 1;
            } else {
                overIndex = middle - 1;
            }
        }
        return -1;
    }

    /**
     * 线性查找
     *
     * @param arr    要查询的数组
     * @param target 查询的目标值
     * @return 查到的目标值所在的索引
     */
    public static int OrderSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
