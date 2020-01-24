package com.unknown.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
public class Queue_producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;

    @Scheduled(fixedDelay = 3000L)
    public void produceMsg(){
        this.jmsMessagingTemplate.convertAndSend(queue,"这是消息");
    }

}
