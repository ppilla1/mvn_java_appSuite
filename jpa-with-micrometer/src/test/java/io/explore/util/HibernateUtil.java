package io.explore.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    public static EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("testing");
    }
}
