package com.unknown.base.timerTask;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskTest {

    public static void main(String[] args) {
        Timer timer = new Timer();

//        timer.schedule(new MyTimerTask(),3000);//定时任务安排，3秒后执行任务一次

//        timer.schedule(new MyTimerTask(),2000,1000);//定时任务安排，2秒后执行任务,每隔1秒执行一次

        timer.schedule(new MyTimerTask(),new Date(new Date().getTime()+5000L),2000);//定时任务安排，5秒后执行任务,每隔2秒执行一次
    }
}

class MyTimerTask extends TimerTask{

    @Override
    public void run() {
        System.out.println("当前在定时任务的run方法");
    }
}
