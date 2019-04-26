package com.huahua.qa;

import huahua.until.IdWorker;
import huahua.until.Jwtutil.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScans(value = {@ComponentScan(value = "huahua.filter"),@ComponentScan(value = "com.huahua.qa")})
public class qaApplication {

	public static void main(String[] args) {
		SpringApplication.run(qaApplication.class, args);
	}

	//这就是一个注释
	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}


	@Bean
	public JwtUtil jwtUtil(){
		return new JwtUtil();
	}
	
}
