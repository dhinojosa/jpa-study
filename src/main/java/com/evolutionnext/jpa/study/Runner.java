package com.evolutionnext.jpa.study;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Runner {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("aimswebPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(...);
        em.getTransaction().commit();

    }
}
