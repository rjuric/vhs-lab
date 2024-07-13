package com.rjuric.vhs_lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class VhsLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(VhsLabApplication.class, args);
	}

}
