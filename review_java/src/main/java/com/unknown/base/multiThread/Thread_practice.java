package com.unknown.base.multiThread;

import org.junit.Test;

import java.util.concurrent.*;

public class Thread_practice {

    @Test
    public void testCallable() throws ExecutionException, InterruptedException {

        MyThreadForCallable myThreadForCallable = new MyThreadForCallable();
        FutureTask<String> futureTask = new FutureTask<>(myThreadForCallable);
        Thread thread = new Thread(futureTask);
        thread.setName("zio_test");
        thread.start();
        String return_result = futureTask.get();
        System.out.println(return_result);
    }

    public void testThreadPool() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("test thread pool");
            }
        });
        Future<String> future = executorService.submit(new MyThreadForCallable());
        String result = future.get();
        executorService.shutdown();
    }
}

class MyThreadForCallable implements Callable<String> {

    @Override
    public String call() throws Exception {

        return "test thread";
    }
}
