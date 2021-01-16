/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facadeTest;

import DTO.BookingDTO;
import entities.Booking;
import facades.FacadeExample;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasProperty;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author matti
 */
public class Facatest {

    /**
     *
     * @author vnord
     */
    private static EntityManagerFactory emf;
    private static FacadeExample facade;
    private Booking f1;
    private Booking f2;
    private Booking f3;

    public Facatest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = FacadeExample.getFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    //Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {

            f1 = new Booking(1, 22, "binno");
            f2 = new Booking(2, 23, "binno2");
            f3 = new Booking(3, 44, "bob");
            BookingDTO dto =new BookingDTO(f3);

            em.getTransaction().begin();
            em.createNamedQuery("Booking.deleteAllRows").executeUpdate();

            em.persist(f1);
            em.persist(f2);
            em.getTransaction().commit();
            facade.add(dto);

            //em.persist(f3);
            
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of fetchReviewByTitle method, of class FetchFacade. Test of a single
     * search result.
     */
    @Test
    public void edit() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        BookingDTO fr = new BookingDTO(f1.getId(), 123, 1111, "Bobi");
        facade.edit(fr);
        Booking o = em.find(Booking.class, f1.getId());
        em.getTransaction().commit();
        assertEquals("Bobi", o.getName());
    }

    @Test
    public void add() {
        System.out.println(facade.getAll("bob"));
    assertEquals(1, facade.getAll("bob").size());
    }

    @Test
    public void list() {
        assertEquals(facade.getAll("binno").size(), 1);
    }

    @Test
    public void del() {
        facade.deleted(f2.getId());
        assertEquals(0, facade.getAll("binno2").size());
    }
}
