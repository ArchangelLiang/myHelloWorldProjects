package com.unknown.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Map;

@Service
public class QueueTest_consumer {
    @Autowired
    private JmsTemplate jmsTemplate;
    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        QueueTest_consumer consumer = (QueueTest_consumer) context.getBean("queueTest_consumer");
        Message msg = null;
        while ((msg = consumer.jmsTemplate.receive()) != null){
            if (msg instanceof TextMessage){
                TextMessage textMessage = (TextMessage)msg;
                System.out.println("收到消息："+textMessage.getText());
            }
        }
    }
}
