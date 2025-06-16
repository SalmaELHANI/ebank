package com.ebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.ebank")
@EnableJpaRepositories(basePackages = "com.ebank.repository")
@EntityScan(basePackages = "com.ebank.entity")
public class EbankBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(EbankBackendApplication.class, args);
	}


}
