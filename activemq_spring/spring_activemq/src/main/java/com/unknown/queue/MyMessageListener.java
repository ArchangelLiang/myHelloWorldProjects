package com.unknown.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            TextMessage msg = (TextMessage)message;
            try {
                System.out.println("接收到消息："+msg.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
