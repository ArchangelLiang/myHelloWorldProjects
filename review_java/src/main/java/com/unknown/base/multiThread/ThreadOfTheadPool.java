package com.unknown.base.multiThread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


class RunnableTest implements Runnable {
    @Override
    public void run() {
        System.out.println("this is runnable instance");
    }
}

public class ThreadOfTheadPool {

    public static void main(String[] args) {

        RunnableTest runnableTest = new RunnableTest();
        TestCallable testCallable = new TestCallable();
        //创建一个10个大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //可以强转为子类以使用更多的功能
        ThreadPoolExecutor executor = (ThreadPoolExecutor) executorService;
        //执行runnable接口类型的线程
        executorService.execute(runnableTest);
        //执行callable接口类型的线程
        executorService.submit(testCallable);
        //关闭线程池
        executorService.shutdown();
    }
}
