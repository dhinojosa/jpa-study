package com.evolutionnext.jpa.study;

import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.assertEquals;

/**
 * @author Daniel Hinojosa
 * @since 6/17/14 5:44 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */

public class QueryCriteriaTest {
    private static EntityManagerFactory emf = null;
    private EntityManager em = null;

    @BeforeClass
    public static void startup() {
        emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
    }

    @Before
    public void initEm() {
        em = emf.createEntityManager();
    }


    @Test
    public void deleteAlbum() {

//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//        try {
//            em.remove(em.find(Album.class, 1L));
//            em.flush();
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//        }
    }

    @After
    public void closeEm() {
        if (em != null) em.close();
    }

    @AfterClass
    public static void shutdown() {
        if (emf != null) emf.close();
    }
}
