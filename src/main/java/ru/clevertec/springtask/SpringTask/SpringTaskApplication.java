package ru.clevertec.springtask.SpringTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTaskApplication.class, args);
	}

}
