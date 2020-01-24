package com.unknown.base.multiThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Account {
    private int balance = 0;
    private Lock lock = new ReentrantLock();

    public void deposit(int money) {
        lock.lock();
        this.balance += money;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "存了" + money + "元，当前余额：" + balance);
        lock.unlock();
    }
}

public class MultiThreadTest {

    public static void main(String[] args) {

        Account account = new Account();

        Thread a = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    account.deposit(1000);
                }
            }
        };
        a.setName("zio");
        Thread b = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    account.deposit(1000);
                }
            }
        };
        b.setName("decade");
        a.start();
        b.start();
    }

}
