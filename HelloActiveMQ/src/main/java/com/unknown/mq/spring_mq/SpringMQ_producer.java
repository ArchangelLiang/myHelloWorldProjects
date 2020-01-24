package com.unknown.mq.spring_mq;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public class SpringMQ_producer {


    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");

        JmsTemplate jmsTemplate = (JmsTemplate)applicationContext.getBean("jmsTemplate");
        jmsTemplate.setSessionTransacted(false);
        jmsTemplate.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        jmsTemplate.setDefaultDestination(new ActiveMQQueue("test_auto_queue"));

        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("hello integration spring and activeMQ");
            }
        });

        System.out.println("msg send successful");
    }

}
