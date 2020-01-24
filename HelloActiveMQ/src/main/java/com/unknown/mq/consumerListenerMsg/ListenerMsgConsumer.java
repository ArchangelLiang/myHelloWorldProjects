package com.unknown.mq.consumerListenerMsg;

import com.unknown.mq.constant.MqURL;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ListenerMsgConsumer {

    public static void main(String[] args) throws JMSException, InterruptedException, IOException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(MqURL.MQ_URL);

        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue myQueue = session.createQueue("myQueue");

        MessageConsumer consumer = session.createConsumer(myQueue);

        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    TextMessage msg = (TextMessage) message;
                    try {
                        System.out.println("receive msg is " + msg.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        TimeUnit.SECONDS.sleep(10);

        consumer.close();
        session.close();
        connection.close();
    }

}
