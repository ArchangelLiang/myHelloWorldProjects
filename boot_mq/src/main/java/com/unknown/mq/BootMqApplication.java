package com.unknown.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class BootMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMqApplication.class, args);
    }

}
