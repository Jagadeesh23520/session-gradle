package com.example.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = "com.example.session")
public class SessionGradleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessionGradleApplication.class, args);
	}

}
