package com.unknown;

import com.unknown.bean.Queue_producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BootActivemqApplicationTests {

    @Autowired
    private Queue_producer producer;

    @Test
    public void contextLoads() {
        this.producer.produceMsg();
    }

}
