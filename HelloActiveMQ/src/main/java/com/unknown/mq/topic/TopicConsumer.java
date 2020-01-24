package com.unknown.mq.topic;

import com.unknown.mq.constant.MqURL;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TopicConsumer {

    public static void main(String[] args) throws JMSException, InterruptedException, IOException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(MqURL.MQ_URL);

        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic myTopic = session.createTopic("myTopic");

        MessageConsumer consumer = session.createConsumer(myTopic);

        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    TextMessage msg = (TextMessage)message;
                    try {
                        System.out.println("receive msg is " + msg.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        System.out.println("consumer two startup.....");
        System.in.read();

        consumer.close();
        session.close();
        connection.close();
    }

}
