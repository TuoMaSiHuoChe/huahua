package com.huahua.sms;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author: admin
 * @Date: 2019/4/18 15:45
 * @Description:
 */
@SpringBootApplication

public class SmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class,args);
    }

    @Bean
    public Queue sms() {
        return new Queue("sms");
    }
    @Bean
    public Queue email() {
        return new Queue("email");
    }

}
