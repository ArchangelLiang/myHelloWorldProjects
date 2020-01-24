package com.unknown.base.multiThread;

class Clerk {
    int num;

    public synchronized void increment() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (num >= 19) {
            try {
                System.out.println("数量充足，等待消费");
                this.notifyAll();
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num++;
        System.out.println("生成一个，当前剩余：" + num);
    }

    public synchronized void decrement() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (num == 0) {
            try {
                System.out.println("缺货，等待补货");
                this.notifyAll();
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num--;
        System.out.println("消费一个，当前剩余：" + num);
    }
}

class Producer extends Thread {
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; true; i++) {
            clerk.increment();
        }
    }
}

class Consumer extends Thread {
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }


    @Override
    public void run() {
        for (int i = 0; true; i++) {
            clerk.decrement();
        }
    }
}

public class ThreadTestOfPC {

    public static void main(String[] args) {

        Clerk clerk = new Clerk();
        new Consumer(clerk).start();
        new Producer(clerk).start();
    }
}
