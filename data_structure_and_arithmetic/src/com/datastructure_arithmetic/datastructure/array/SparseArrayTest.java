package com.datastructure_arithmetic.datastructure.array;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;

public class SparseArrayTest {

    /**
     * 稀疏数组之还原
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

        ObjectInputStream stream = new ObjectInputStream(new FileInputStream("file/sparseArray,.txt"));
        Object object = stream.readObject();
        int[][] sparseArray = (int[][])object;
        int[][] array = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            array[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.printf("%d\t",anInt);
            }
            System.out.println();
        }
    }

    /**
     * 稀疏数组之转换
     * @throws Exception
     */
    @Test
    public void test01() throws Exception{
        int[][] array = new int[10][10];
        array[3][1] = 7;
        array[6][5] = 5;
        /*for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]+"  ");
            }
            System.out.println();
        }*/
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j]!=0){
                    count++;
                }
            }
        }
        count++;
        int[][] sparseArray = new int[count][3];
        sparseArray[0][0] = array.length;
        sparseArray[0][1] = array[0].length;
        sparseArray[0][2] = count-1;
        int index = 1;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j]!=0){
                    sparseArray[index][0] = i;
                    sparseArray[index][1] = j;
                    sparseArray[index][2] = array[i][j];
                    index++;
                }
            }
        }
        for (int i = 0; i < sparseArray.length; i++) {
            for (int j = 0; j < sparseArray[i].length; j++) {
                System.out.print(sparseArray[i][j]+"\t");
            }
            System.out.println();
        }
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("file/sparseArray,.txt"));
        outputStream.writeObject(sparseArray);
        outputStream.close();
    }
}
