package com.huahua.base;

import huahua.until.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @Auther: admin
 * @Date: 2019/4/10 21:03
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class);
    }
    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
