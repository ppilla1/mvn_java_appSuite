package io.explore;

import io.explore.model.jpa.airport.Airport;
import io.explore.model.jpa.airport.Passenger;
import io.explore.model.jpa.airport.Ticket;
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
			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();

			Airport henriCoanda = new Airport(1, "Henri Coanda");

			Passenger john = new Passenger(1, "John Smith");
			john.setAirport(henriCoanda);
			Passenger mike = new Passenger(2, "Michael Johnson");
			mike.setAirport(henriCoanda);

			henriCoanda.addPassenger(john);
			henriCoanda.addPassenger(mike);

			Ticket ticket1 = new Ticket(1, "AA1234");
			ticket1.setPassenger(john);

			Ticket ticket2 = new Ticket(2, "BB5678");
			ticket2.setPassenger(john);

			john.addTicket(ticket1);
			john.addTicket(ticket2);

			Ticket ticket3 = new Ticket(3, "CC0987");
			ticket3.setPassenger(mike);

			mike.addTicket(ticket3);

			em.persist(henriCoanda);
			em.persist(john);
			em.persist(mike);
			em.persist(ticket1);
			em.persist(ticket2);
			em.persist(ticket3);

			em.getTransaction().commit();
			log.info("** All Objects persisted **");
			emf.close();
		};
	}
}
