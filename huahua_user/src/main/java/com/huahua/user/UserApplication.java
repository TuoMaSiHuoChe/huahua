package com.huahua.user;

import huahua.until.IdWorker;
import huahua.until.Jwtutil.JwtUtil;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableEurekaClient
@ComponentScans(value = {@ComponentScan(value = "huahua.filter"),@ComponentScan(value = "com.huahua.user")})
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}

	@Bean
	public Queue sms() {
		return new Queue("sms");
	}
	@Bean
	public Queue email() {
		return new Queue("email");
	}
	@Bean
	public JwtUtil jwtUtil(){
		return new JwtUtil();
	}
	@Bean
	public BCryptPasswordEncoder bcryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
