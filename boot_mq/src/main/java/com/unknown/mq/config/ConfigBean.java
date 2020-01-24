package com.unknown.mq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Session;

@Configuration
@EnableJms
//@EnableScheduling //启用定时器
public class ConfigBean {

    @Value("${myQueue}")
    private String queueName;

    @Bean
    public Queue activeMQQueue(){
        return new ActiveMQQueue(this.queueName);
    }

    @Bean//添加事务管理器，用来做事务发送
    public PlatformTransactionManager transactionManager(ConnectionFactory connectionFactory){
        return new JmsTransactionManager(connectionFactory);
    }



}
