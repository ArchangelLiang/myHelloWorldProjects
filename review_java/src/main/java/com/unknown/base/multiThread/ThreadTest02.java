package com.unknown.base.multiThread;

class Count implements Runnable {

    private int num;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                this.notifyAll();
                if (num <= 100) {
                    System.out.println(Thread.currentThread().getName() + "打印的" + num);
                    num++;
                    try {
                        this.wait();
                        System.out.println(Thread.currentThread().getName() + "this is wait after content");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }
}

public class ThreadTest02 {

    public static void main(String[] args) {

        Count count = new Count();
        new Thread(count, "zio").start();
        new Thread(count, "decade").start();
    }

}
