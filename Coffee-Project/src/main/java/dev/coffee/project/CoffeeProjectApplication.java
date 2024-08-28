package dev.coffee.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class CoffeeProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeProjectApplication.class, args);
	}

}
