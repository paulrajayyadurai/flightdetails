package com.tokigames.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Anand Kumar
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = ("com.**"))
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
@EnableConfigurationProperties
public class FlightDetailsApplication {

	public static void main(final String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "true");
		SpringApplication.run(FlightDetailsApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}


}
