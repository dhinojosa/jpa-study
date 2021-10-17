package com.evolutionnext.jpa.study;

import org.junit.*;

import javax.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Daniel Hinojosa
 * @since 6/17/14 5:44 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */

public class JPAQLTest {
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
    public void createAlbumThriller() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Album album = new Album("Thriller");
            Artist michaelJackson = new Artist("Michael", "Jackson");
            album.setPerformers(Collections.singletonList(michaelJackson));
            album.setReleaseDate(Date.from(LocalDate.of(1982, 11, 30).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            em.persist(album);
            em.flush();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    public void selectAlbumByNameQuery() {
        Query query = em.createQuery
                ("SELECT a FROM Album as a WHERE a.name = 'Thriller'");
        List<Album> list = (List<Album>) query.getResultList();
        assertEquals(1, list.size());
    }

    @Test
    public void selectAlbumByNameTypedQuery() {
        TypedQuery<Album> query = em.createQuery
                ("SELECT a FROM Album as a WHERE a.name = 'Thriller'", Album.class);
        List<Album> list = query.getResultList();
        assertEquals(1, list.size());
    }

    @Test
    public void selectAlbumByLikeName() {
        TypedQuery<Album> query = em.createQuery
                ("SELECT a FROM Album as a WHERE a.name LIKE 'Thri%'", Album.class);
        List<Album> list = query.getResultList();
        assertEquals(1, list.size());
    }

    @Test
    public void selectAllPerformers() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Band soundgarden = new Band("Soundgarden");
            soundgarden.addPerformer(new Artist("Chris", "Cornell"));
            soundgarden.addPerformer(new Artist("Kim", "Thayil"));
            soundgarden.addPerformer(new Artist("Matt", "Cameron"));
            soundgarden.addPerformer(new Artist("Ben", "Shepherd"));

            em.persist(soundgarden);
            tx.commit();

            Query query = em.createQuery("SELECT p.firstName, p.lastName from Band as b left join b.performers as p WHERE Type(p) = Artist");
            for (Object list : query.getResultList()) {
                Object[] array = (Object[]) list;
                System.out.println(array[0] + " " + array[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
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
