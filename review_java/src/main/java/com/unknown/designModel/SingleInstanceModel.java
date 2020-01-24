package com.unknown.designModel;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SingleInstanceModel {

    private static SingleInstanceModel instance;

    private SingleInstanceModel() {
    }

    public static SingleInstanceModel getInstance() {
        if (instance == null) {
            synchronized (SingleInstanceModel.class) {
                if (instance == null) {
                    instance = new SingleInstanceModel();
                }
                return instance;
            }
        }
        return instance;
    }

    public static void main(String[] args) throws Exception {
        Callable<My> c = new Callable<My>(){
            @Override
            public My call() throws Exception {
                return My.getIs();
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<My> c1 = executorService.submit(c);
        Future<My> c2 = executorService.submit(c);
        My my = c1.get();
        My my1 = c2.get();
        System.out.println(my == my1);
        executorService.shutdown();
    }
}


enum MyE{
    ZIO("decade"),DECADE("LO");
    String name;
    private MyE(){
    }
    private MyE(String name){
        this.name = name;
    }
}

class My{
    private static My is;

    private My(){}

    public static My getIs(){
        /*if (is == null) {
            synchronized (My.class) {*/
                if (is == null) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    is = new My();
             /*   }
            }*/
        }
        return is;
    }
}

