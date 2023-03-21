package io.explore;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;

@Log4j2
@SpringBootApplication
public class JpaWithMicrometerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaWithMicrometerApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(EntityManager em) {
		return args -> {
			log.info("Entity manager -> {}", em);
		};
	}
}
