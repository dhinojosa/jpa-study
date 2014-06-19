package com.evolutionnext.jpa.study;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Runner {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        {
            em.getTransaction().begin();
            Album album = new Album("Rock and Roll Pt. 1");
            em.persist(album);
            em.getTransaction().commit();

            TypedQuery<Album> typedQuery = em.createQuery("SELECT a FROM Album", Album.class);
            List<Album> albums = typedQuery.getResultList();

            for (Album a : albums) {
                System.out.println(a.getName());
            }
        }

        {
            // Simple Criteria Query
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Album> criteriaQuery = cb.createQuery(Album.class);
            Root<Album> root = criteriaQuery.from(Album.class);
            criteriaQuery.select(root);
            TypedQuery<Album> typedQuery = em.createQuery(criteriaQuery);
            List<Album> albums = typedQuery.getResultList();

            for (Album a : albums) {
                System.out.println(a.getName());
            }
        }
    }
}
