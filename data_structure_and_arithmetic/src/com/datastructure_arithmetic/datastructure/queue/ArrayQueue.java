package com.datastructure_arithmetic.datastructure.queue;

import org.junit.Test;

import java.util.Scanner;

public class ArrayQueue {

    private int maxSize;
    private int front;
    private int rear;
    private int[] queue;
    private int size;

    public ArrayQueue(int queueSize){
        maxSize = queueSize;
        queue = new int[maxSize];
        front = -1;
        rear = -1;
    }

    public int getFromQueue() {
        if (front != -1 && front != rear){
            size--;
            return queue[front++];
        } else if (front == rear){
            size--;
            front = -1;
            return queue[rear];
        } else {
            throw new RuntimeException("队列为空");
        }
    }

    private int getNextIndex(){
        if (rear < maxSize - 1){
            return ++rear;
        } else {
            rear = 0;
            return rear;
        }
    }

    public void addQueue(int num) {
        if (size < maxSize){
            if (front == -1){
                front = 0;
            }
            size++;
            queue[getNextIndex()] = num;
        } else {
            System.out.println("队列已满");
        }
    }

    private void showData() {
        int start = front;
        int end = rear;
        for (int i = 0; i < size; i++) {
            if (start >= maxSize){
                System.out.print(queue[end++]);
            } else {
                System.out.print(queue[start++]+"  ");
            }
        }
    }


    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
       while (flag){
           System.out.println("请输入：");
           System.out.println("a(add)：添加数据");
           System.out.println("g(get)：取出数据");
           System.out.println("s(show)：显示数据");
           System.out.println("e(exit)：退出程序");
           char c = sc.next().charAt(0);
           switch (c){
               case 'a':
                   System.out.println("请输入要插入的数：");
                   int num = sc.nextInt();
                   queue.addQueue(num);
                   break;
               case 'g':
                   System.out.println("本次取出的数据为："+queue.getFromQueue());
                   break;
               case 's':
                   queue.showData();
                   break;
               case 'e':
                   flag = false;
                   break;
           }
       }

    }

    @Test
    public void test() {
        int[] ints = new int[10];
        System.out.println(ints.length);
    }
}
