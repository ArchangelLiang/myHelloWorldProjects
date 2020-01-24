package com.unknown.mq.helloWorld;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class HelloConsumer {

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.43.145:61616");

        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue myQueue = session.createQueue("testDelay");

        MessageConsumer consumer = session.createConsumer(myQueue);

        System.out.println("consumer startup...");
        while (true) {
            TextMessage message = (TextMessage)consumer.receive();
            if (message != null) {
                System.out.println("receive msg is " + message.getText());
            } else {
                System.out.println("no msg");
                break;
            }
        }

        consumer.close();
        session.close();
        connection.close();

    }

}
