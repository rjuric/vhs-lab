package com.rjuric.vhs_lab;

import com.rjuric.vhs_lab.config.RsaKeysProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeysProperties.class)
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class VhsLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(VhsLabApplication.class, args);
	}

}
