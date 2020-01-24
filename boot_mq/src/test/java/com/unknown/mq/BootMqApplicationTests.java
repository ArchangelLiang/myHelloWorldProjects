package com.unknown.mq;

import com.unknown.mq.queue.Queue_producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootTest
class BootMqApplicationTests {

    @Autowired
    private Queue_producer producer;

    @Test
    void testSendMsg() {

        this.producer.sendTxMsg();
        System.out.println("smg send successful");
    }

}
