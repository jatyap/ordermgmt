package com.jatyap.ordermgmt.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jatyap.ordermgmt.service.DefaultProductManager;
import com.jatyap.ordermgmt.service.ProductService;

@Configuration
public class AppConfig {

	@Bean
	public ProductService productService(){
		return new DefaultProductManager();
	}

}
