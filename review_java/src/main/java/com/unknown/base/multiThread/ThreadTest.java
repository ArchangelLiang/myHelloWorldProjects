package com.unknown.base.multiThread;

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            if (Thread.currentThread().getName().equals("en")) {
                if (i % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + "输出偶数" + i);
                }
            } else {
                if (i % 2 != 0) {
                    System.out.println(Thread.currentThread().getName() + "输出基数" + i);
                }
            }
        }
    }
}

public class ThreadTest {

    public static void main(String[] args) {

        MyThread en = new MyThread();
        en.setName("en");
        en.start();
        MyThread on = new MyThread();
        on.setName("on");
        on.start();
    }
}
