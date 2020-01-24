package com.unknown.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.TextMessage;

@Component
public class Queue_consumer {

    @JmsListener(destination = "${myqueue}")
    public void receiveMsg(TextMessage msg){
        System.out.println("接收到消息："+msg);
    }

}
