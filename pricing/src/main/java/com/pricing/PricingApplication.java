package com.pricing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PricingApplication {

	public static void main(String[] args) {

		SpringApplication.run(PricingApplication.class, args);
	}

}
