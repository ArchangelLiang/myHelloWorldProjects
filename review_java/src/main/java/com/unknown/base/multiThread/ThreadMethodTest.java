package com.unknown.base.multiThread;

import java.util.ArrayList;

public class ThreadMethodTest {

    public static void main(String[] args) throws Exception{

       /* Thread t1 = new Thread();
        t1.join();
        Thread.yield();
        Thread.sleep(1000);
        t1.sleep(1000);*/

     /*   ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                list.add(Thread.currentThread().getName());
            }).start();
        }
        Thread.sleep(3000);
        System.out.println(list.size());*/
        int i = 0;
        while (i < 10 && i++<10);
        System.out.println(i);
    }
}
