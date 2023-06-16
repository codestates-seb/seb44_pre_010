package com.rainbow.sof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SofApplication {

	public static void main(String[] args) {
		SpringApplication.run(SofApplication.class, args);
	}

}
