package com.unknown.mq.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Queue;

@Component
public class Queue_producer {

    @Autowired
    private JmsMessagingTemplate template;

    @Autowired
    private Queue queue;

    public void sendMsg(){
        this.template.convertAndSend(this.queue,"this is boot integration mq a msg");
    }

  /*  @Scheduled(fixedDelay = 3000) //每三秒种发送一次
    public void scheduleSendMsg(){
        this.template.convertAndSend(this.queue,"this is scheduled msg");
        System.out.println("send scheduled msg successful...");
    }*/

    @Transactional//对消息发送加入事务管理（同时JDBC的事务也会生效）
    public void sendTxMsg(){
        for (int i = 0; i < 5; i++) {
           /* if (i == 2){
                throw new ArithmeticException("test send tx msg generated exception");
            }*/
            this.template.convertAndSend(this.queue,"this is transaction msg of boot integration activemq --> " + i);
        }
    }
}
