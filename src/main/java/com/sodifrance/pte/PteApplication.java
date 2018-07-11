package com.sodifrance.pte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.sodifrance.model.entity"})  // scan JPA entities
public class PteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PteApplication.class, args);
		
		System.out.println("test app");
	}
}
