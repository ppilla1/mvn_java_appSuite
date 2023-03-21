package io.explore.jpa;

import io.explore.model.Employee;
import io.explore.util.HibernateUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
public class JpaExploreTest {
    static EntityManagerFactory emf;
    EntityManager em;

    @BeforeAll
    public static void init() {
        emf = HibernateUtil.entityManagerFactory();
    }

    @AfterAll
    public static void tearDown() {
        emf.close();
    }

    @BeforeEach
    public void initTest(){
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @AfterEach
    public void tearDownTest(){
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void create_new_employee() {
        Employee dalia = new Employee();
        dalia.setFName("Dalia");
        dalia.setLName("Abo Sheasha");

        em.persist(dalia);
        log.info("{} - Created", dalia);
        assertEquals(1l, dalia.getId());
    }

    @Test
    public void find_new_employee() {
        long id = 1l;
        Employee dalia = em.find(Employee.class, id);
        log.info("{} - Found[{}]", dalia, id);
        assertEquals(1l, dalia.getId());
    }

    @Test
    public void update_employee_fields(){
        long id = 1l;
        Employee dalia = em.find(Employee.class, id);
        dalia.setFName("Flower");
        log.info("{} - Updated[{}]", dalia, id);
        assertEquals("Flower", dalia.getFName());
    }

    @Test
    public void update_employee_fields_findUpdate(){
        long id = 1l;
        Employee dalia = em.find(Employee.class, id);
        log.info("{} - Updated Found[{}]", dalia, id);
        assertEquals("Flower", dalia.getFName());
    }
}
