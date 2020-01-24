package com.unknown.mq.msgFormat;

import com.unknown.mq.constant.MqURL;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TestProducer {

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(MqURL.MQ_URL);

        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic myTopic = session.createTopic("myTopic");

        MessageProducer producer = session.createProducer(myTopic);

        MapMessage mapMessage = session.createMapMessage();
        mapMessage.setString("name","zio");

        producer.send(mapMessage);

        producer.close();
        session.close();
        connection.close();

    }

}
