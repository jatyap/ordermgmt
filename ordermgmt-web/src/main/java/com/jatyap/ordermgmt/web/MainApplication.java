package com.jatyap.ordermgmt.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@ComponentScan("com.jatyap.ordermgmt")
@EntityScan("com.jatyap.ordermgmt.entity")
@EnableJpaRepositories("com.jatyap.ordermgmt.repository")
public class MainApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MainApplication.class,
				args);
	}
}
