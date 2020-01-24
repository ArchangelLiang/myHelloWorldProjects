package com.datastructure_arithmetic.arithmetic.sort;

import org.junit.Test;

import java.util.Arrays;

public class SortArithmetic {

    private static int changeCount = 0;

    public static void main(String[] args) {

//        int[] array = {9, 8, 5, -1, -3, 5, 0, 2, 4, 6, 1, 3, 7};
//        int[] testRadixArr = {10, 22, 1, 2, 5, 4, 3, 6, 8, 9, 7, 0, 99, 42, 654, 111, 66, 77, 1, 2, 3, 0};
//        bubblingSort(array);
//        selectorSort(array);
//        insertSort(array);
//        shellSortOfExchange(array);
//        shellSortOfInsert(array);
//        quickSort(array, 0, array.length - 1);
//        mergerSort(array, 0, array.length - 1, new int[array.length]);
//        radixSort(testRadixArr);
//        System.out.println(Arrays.toString(array));
//        System.out.println(Arrays.toString(testRadixArr));
//        System.out.println(changeCount);
        int[] arr = {9, 8, 5, -1, -3, 5, 0, 2, 4, 6, 1, 3, 7};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 堆排序
     */
    public static void heapSort(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustArr(arr, i, arr.length);
        }
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            adjustArr(arr, 0, i);
        }
    }

    /**
     * 用来将以i开始的树结构调整成一个大顶堆
     *
     * @param arr    待调整得到数组
     * @param i      非叶子节点在数组中的索引
     * @param length 可以调整的数组的范围，该范围会随着排序得到进行而逐渐减小
     */
    private static void adjustArr(int[] arr, int i, int length) {
        for (int j = 2 * i + 1; j < length; j = 2 * j + 1) {
            if (j + 1 < length && arr[j] < arr[j + 1]) {
                j++;
            }
            if (arr[i] < arr[j]) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i = j;
            } else {
                break;
            }
        }
    }

    /**
     * 基数排序
     * 注意：该版本不支持负数
     *
     * @param arr 要排序的数组
     */
    public static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = String.valueOf(max).length();
        int[][] buckets = new int[10][arr.length];
        int[] bucketCount = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int value : arr) {
                int digitNum = value / n % 10;
                buckets[digitNum][bucketCount[digitNum]] = value;
                bucketCount[digitNum]++;
            }
            int index = 0;
            for (int j = 0; j < buckets.length; j++) {
                for (int i1 = 0; i1 < bucketCount[j]; i1++) {
                    arr[index++] = buckets[j][i1];
                }
                bucketCount[j] = 0;
            }
        }
    }

    /**
     * 归并排序
     *
     * @param arr   要排序的数组
     * @param left  每次拆分的起始索引
     * @param right 每次拆分的结束索引
     * @param temp  用来做临时缓冲的数组
     */
    public static void mergerSort(int[] arr, int left, int right, int[] temp) {
        int middle = (left + right) / 2;
        if (right - left > 1) {
            mergerSort(arr, left, middle, temp);
            mergerSort(arr, middle + 1, right, temp);
        }
        merge(arr, left, middle, right, temp);
    }

    /**
     * 归并排序中的合并算法
     *
     * @param arr    要排序的数组
     * @param left   起始索引
     * @param middle 用来分割的中间索引
     * @param right  结束索引
     * @param temp   用来做临时缓冲的数组
     */
    private static void merge(int[] arr, int left, int middle, int right, int[] temp) {
        int l1 = left;
        int l2 = middle + 1;
        int i = 0;
        while (l1 <= middle && l2 <= right) {
            if (arr[l1] < arr[l2]) {
                temp[i] = arr[l1];
                l1++;
            } else {
                temp[i] = arr[l2];
                l2++;
            }
            i++;
        }
        while (l1 <= middle) {
            temp[i] = arr[l1];
            i++;
            l1++;
        }
        while (l2 <= right) {
            temp[i] = arr[l2];
            i++;
            l2++;
        }
        int index = 0;
        int le = left;
        while (le <= right) {
            arr[le] = temp[index];
            index++;
            le++;
        }
    }

    /**
     * 快速排序
     *
     * @param arr   要排序的数组
     * @param left  数组的起始索引
     * @param right 数组的结束索引
     */
    public static void quickSort(int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2];
        int l = left;
        int r = right;
        while (l < r) {
            while (arr[l] < pivot) {
                l++;
            }
            while (arr[r] > pivot) {
                r--;
            }
            if (l >= r) {
                break;
            } else {
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
                if (arr[l] == pivot) {
                    r--;
                }
            }
        }
        if (right - left > 1) {
            quickSort(arr, left, l - 1);
            quickSort(arr, l + 1, right);
        }
    }

    /**
     * 希尔排序之位移法
     *
     * @param arr 要排序的数组
     */
    public static void shellSortOfInsert(int[] arr) {
        for (int step = arr.length / 2; step > 0; step /= 2) {
            for (int i = step; i < arr.length; i++) {
                if (arr[i - step] > arr[i]) {
                    int temp = arr[i];
                    int j = i;
                    while (j - step >= 0 && temp < arr[j - step]) {
                        arr[j] = arr[j - step];
                        j -= step;
                    }
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 希尔排序之交换法【不推荐使用】
     *
     * @param arr 要排序的数组
     */
    public static void shellSortOfExchange(int[] arr) {
        for (int step = arr.length / 2; step > 0; step /= 2) {
            for (int i = step; i < arr.length; i++) {
                for (int j = i - step; j >= 0; j -= step) {
                    if (arr[j] > arr[j + step]) {
                        int temp = arr[j];
                        arr[j] = arr[j + step];
                        arr[j + step] = temp;
                    }
                }
            }
        }
    }

    /**
     * 插入排序
     *
     * @param array 要排序的数组
     */
    public static void insertSort(int[] array) {
        int insertNum;
        int insertIndex;
        for (int i = 1; i < array.length; i++) {
            insertNum = array[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertNum < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            array[insertIndex + 1] = insertNum;
        }
    }

    /**
     * 选择排序
     *
     * @param array 要排序的数组
     */
    public static void selectorSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1 + i; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    changeCount++;
                }
            }
        }
    }

    /**
     * 冒泡排序
     *
     * @param array 要排序的数组
     */
    public static void bubblingSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < array.length - 1 - i; j++) {
                int temp;
                if (array[j] > array[j + 1]) {
                    changeCount++;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    @Test
    public void test() {
        int[] arr = {9, 8, 5, -1, -3, 5, 0, 2, 4, 6, 1, 3, 7};

        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            testSort(arr, i, arr.length);
        }
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            testSort(arr, 0, i);
        }

        System.out.println(Arrays.toString(arr));
    }

    public static void testSort(int[] arr, int i, int length) {

    }
}
