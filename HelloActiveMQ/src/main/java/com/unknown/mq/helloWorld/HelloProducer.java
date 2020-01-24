package com.unknown.mq.helloWorld;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class HelloProducer {

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.43.145:61616");

        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue myQueue = session.createQueue("myQueue");

        MessageProducer producer = session.createProducer(myQueue);

        for (int i = 0; i < 10; i++) {
            TextMessage textMessage = session.createTextMessage(i + " : this is hello msg");
            producer.send(textMessage);
        }

        producer.close();
        session.close();
        connection.close();

        System.out.println("msg send successful");

    }

}
