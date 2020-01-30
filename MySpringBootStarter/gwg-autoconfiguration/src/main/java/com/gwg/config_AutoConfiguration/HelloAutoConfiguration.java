package com.gwg.config_AutoConfiguration;

import com.gwg.config_properties.HelloProperties;
import com.gwg.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(HelloProperties.class)
@ConditionalOnWebApplication
@Configuration
public class HelloAutoConfiguration {

    @Bean
    public HelloService helloService(HelloProperties helloProperties){
        return new HelloService(helloProperties);
    }

}
