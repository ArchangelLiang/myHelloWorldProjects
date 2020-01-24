package com.unknown.base.multiThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Test01 {

    private Lock lock = new ReentrantLock();

    public void t1() {
        lock.lock();
        try {
            Thread.sleep(1000);
            System.out.println("t1方法");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public synchronized void t2() {
        lock.lock();
        try {
            Thread.sleep(1000);
            System.out.println("t2方法");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}

public class ThreadFinalTest {

    public static void main(String[] args) {

        Test01 test01 = new Test01();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test01.t1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test01.t2();
            }
        }).start();

    }

}
