package com.unknown.mq.publish_subscribe;

import com.unknown.mq.constant.MqURL;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Publish_persistence {

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(MqURL.MQ_URL);

        Connection connection = connectionFactory.createConnection();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic("publish_topic");

        MessageProducer producer = session.createProducer(topic);

        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        connection.start();

        TextMessage message = session.createTextMessage("this is persistence topic test msg");

        producer.send(message);

        producer.close();
        session.close();
        connection.close();

    }

}
