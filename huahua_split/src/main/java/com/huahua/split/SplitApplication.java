package com.huahua.split;

import huahua.until.IdWorker;
import huahua.until.Jwtutil.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans(value = {@ComponentScan(value = "huahua.filter"),@ComponentScan(value = "com.huahua.split")})
public class SplitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SplitApplication.class, args);
	}

	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}

	@Bean
	public JwtUtil jwtUtil(){
		return new JwtUtil();
	}
}
