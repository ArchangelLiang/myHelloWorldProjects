package com.unknown.mq.spring_mq;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class SpringMQ_consumer {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        JmsTemplate jmsTemplate = (JmsTemplate)context.getBean("jmsTemplate");

        Object msg = jmsTemplate.receiveAndConvert();

        System.out.println("receive msg is " + msg);

    }

}
