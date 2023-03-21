package io.explore;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Log4j2
@SpringBootApplication
public class JpaWithMicrometerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaWithMicrometerApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(@Qualifier("primaryEntityMangerFactory") EntityManagerFactory emf) {
		return args -> {
			log.info("Entity manager -> {}", emf.getProperties());
		};
	}
}
