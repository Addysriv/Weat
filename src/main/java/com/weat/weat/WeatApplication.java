package com.weat.weat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.weat.weat.repository")
public class WeatApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WeatApplication.class, args);
	}

}
