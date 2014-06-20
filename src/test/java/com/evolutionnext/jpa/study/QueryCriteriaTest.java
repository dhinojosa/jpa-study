package com.evolutionnext.jpa.study;

import org.junit.*;

import javax.persistence.*;
import javax.persistence.criteria.*;

import java.util.List;

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
    public void basicQueryCriteria() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Album> cq = cb.createQuery(Album.class); //return type
        Root<Album> a = cq.from(Album.class); //FROM
        Predicate condition = cb.equal(a.get(Album_.name), "Willie Nelson's Greatest Hits");
        cq.where(condition);
        TypedQuery<Album> q = em.createQuery(cq);
        List<Album> albums = q.getResultList();
        assertEquals(2, albums.size());
    }

    @Test
    public void basicQueryCriteriaWithOrder() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Album> cq = cb.createQuery(Album.class); //return type
        Root<Album> a = cq.from(Album.class); //FROM
        cq.orderBy(cb.asc(a.get(Album_.name)));
        Predicate condition = cb.equal(a.get(Album_.name), "Willie Nelson's Greatest Hits");
        cq.where(condition);
        TypedQuery<Album> q = em.createQuery(cq);
        List<Album> albums = q.getResultList();
        assertEquals(2, albums.size());
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
