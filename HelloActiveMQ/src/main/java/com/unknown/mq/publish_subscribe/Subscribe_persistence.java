package com.unknown.mq.publish_subscribe;

import com.unknown.mq.constant.MqURL;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

public class Subscribe_persistence {

    public static void main(String[] args) throws Exception{

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(MqURL.MQ_URL);

        Connection connection = connectionFactory.createConnection();

        connection.setClientID("zio_subscribe");

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic("publish_topic");

        TopicSubscriber zio = session.createDurableSubscriber(topic, "my name is zio");

        connection.start();

        zio.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    TextMessage msg = (TextMessage)message;
                    try {
                        System.out.println("receive msg is a " + msg.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        TimeUnit.SECONDS.sleep(30);

        zio.close();
        session.close();
        connection.close();
    }

}
