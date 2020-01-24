package com.unknown.mq.queue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component
public class Queue_consumer {

    //该接收方法加入了事务控制，加入事务控制仅仅就是在参数位置添加了个session的形参
    @JmsListener(destination = "${myQueue}")
    public void receiveMsg(TextMessage msg, Session session){//此处的msg以及session会有springBoot传递过来
        try {
            System.out.println("receive msg is " + msg.getText());
            int i = 1 / 0;
            session.commit();//提交，即确认消费消息
        } catch (JMSException e) {
            e.printStackTrace();
            try {
                session.rollback();//回滚，不消费消息
            } catch (JMSException ex) {
                ex.printStackTrace();
            }
        }
    }
}
