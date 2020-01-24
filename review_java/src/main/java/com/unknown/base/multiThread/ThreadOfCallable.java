package com.unknown.base.multiThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class TestCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("hello,callable");
        return 99;
    }
}

public class ThreadOfCallable {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        TestCallable callable = new TestCallable();

        FutureTask futureTask = new FutureTask(callable);

        Thread thread = new Thread(futureTask);
        thread.start();
        Object o = futureTask.get();
        System.out.println(o);
    }
}
