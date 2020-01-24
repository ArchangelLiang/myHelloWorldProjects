package com.unknown.mq.topic;

import com.unknown.mq.constant.MqURL;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicProducer {

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(MqURL.MQ_URL);

        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic myTopic = session.createTopic("myTopic");

        MessageProducer producer = session.createProducer(myTopic);

        for (int i = 0; i < 5; i++) {
            TextMessage textMessage = session.createTextMessage(i + " -> a topic message");
            producer.send(textMessage);
        }
        System.out.println("msg send ok...");
        producer.close();
        session.close();
        connection.close();
    }

}
