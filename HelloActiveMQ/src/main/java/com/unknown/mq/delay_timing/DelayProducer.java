package com.unknown.mq.delay_timing;

import com.unknown.mq.constant.MqURL;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

public class DelayProducer {

    public static void main(String[] args) throws Exception{

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(MqURL.MQ_URL + "/alwaysSyncSend=false&useAsyncSend=true");

        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("testDelay");

        ActiveMQMessageProducer producer = (ActiveMQMessageProducer) session.createProducer(queue);

        TextMessage message = session.createTextMessage("hello,this is delay delivery message");
        //设置延迟投递的延迟时间
        message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,15000L);

        producer.send(message, new AsyncCallback() {
            @Override
            public void onSuccess() {
                System.out.println("message send successful");
            }

            @Override
            public void onException(JMSException exception) {
                System.out.println("message send fail");
                exception.printStackTrace();
            }
        });

        TimeUnit.SECONDS.sleep(60);

        producer.close();
        session.close();
        connection.close();
    }

}
