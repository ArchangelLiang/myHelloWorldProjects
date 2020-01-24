package com.datastructure_arithmetic.datastructure.tree;

public class ArrayBinaryTree {

    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 以二叉树的前序遍历的方式遍历数组
     * @param index 初始为0，之后使用公式计算出左/右节点在数组中的索引
     */
    public void preOrder(int index) {

        if (arr == null || arr.length == 0) {
            System.out.println("数组为空！");
            return;
        }
        System.out.println(arr[index]);
        if ((2 * index + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        if ((2 * index + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    /**
     * 以二叉树的中序遍历的方式遍历数组
     * @param index 初始为0，之后使用公式计算出左/右节点在数组中的索引
     */
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空！");
            return;
        }
        if ((2 * index + 1) < arr.length) {
            infixOrder(2 * index + 1);
        }
        System.out.println(arr[index]);
        if ((2 * index + 2) < arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree tree = new ArrayBinaryTree(arr);
        tree.infixOrder(0);
    }
}
