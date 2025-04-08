package com.apigateway.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.apigateway.gateway.Services.AdminManager;
import com.apigateway.gateway.Services.UserManager;

@SpringBootApplication
@Configuration
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	RestTemplate RestTemplate() {
		RestTemplate restTemplate = new RestTemplate();

		return restTemplate;
	}

	@Bean
	public UserManager userManager(RestTemplate restTemplate) {
		return new UserManager(restTemplate);
	}

	@Bean
	public AdminManager adminManager(RestTemplate restTemplate) {
		return new AdminManager(restTemplate);
	}

}
