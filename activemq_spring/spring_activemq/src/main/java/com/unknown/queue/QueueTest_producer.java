package com.unknown.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service
public class QueueTest_producer {
    @Autowired
    private JmsTemplate jmsTemplate;
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        QueueTest_producer producer = (QueueTest_producer) context.getBean("queueTest_producer");
        producer.jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("hello mq");
            }
        });
        System.out.println("消息发送完毕************");
    }
}
