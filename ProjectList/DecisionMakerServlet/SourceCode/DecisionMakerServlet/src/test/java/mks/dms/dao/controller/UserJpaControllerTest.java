/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mks.dms.dao.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mks.dms.dao.controller.exceptions.NonexistentEntityException;
import mks.dms.dao.entity.User;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 *
 * @author ThachLe
 */
public class UserJpaControllerTest {
    
    public UserJpaControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getEntityManager method, of class UserJpaController.
     */
    @Test
    public void testGetEntityManager() {
        System.out.println("getEntityManager");
        UserJpaController instance = null;
        EntityManager expResult = null;
        EntityManager result = instance.getEntityManager();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class UserJpaController.
     */
    @Test
    public void testCreate_001() {
        System.out.println("create");
        User user = new User();
        user.setUsername("Test User1");
        user.setEnabled(true);
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DecisionMaker-DBModelPU");
        UserJpaController daoCtrl = new UserJpaController(emf);
        daoCtrl.create(user);
        
        System.out.println("Created user id:" + user.getId());
        try {
            daoCtrl.destroy(user.getId());
        } catch (NonexistentEntityException ex) {
            fail(ex.getMessage());
        }
        
    }

    @Test
    public void testCreate_002() {
        User user = new User();
        user.setUsername("user2");
        user.setEnabled(true);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DecisionMaker-DBModelPU");
        UserJpaController instance = new UserJpaController(emf);
        instance.create(user);

    }

    @Test
    public void testCreate_003() {
        User user = new User();
        user.setUsername("user2");
        user.setEnabled(true);
        
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserJpaController daoController = context.getBean(UserJpaController.class);
        
        daoController.create(user);

    }    
    /**
     * Test of edit method, of class UserJpaController.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        User user = null;
        UserJpaController instance = null;
        instance.edit(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroy method, of class UserJpaController.
     */
    @Test
    public void testDestroy() throws Exception {
        System.out.println("destroy");
        Integer id = null;
        UserJpaController instance = null;
        instance.destroy(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUserEntities method, of class UserJpaController.
     */
    @Test
    public void testFindUserEntities_0args() {
        System.out.println("findUserEntities");
        UserJpaController instance = null;
        List<User> expResult = null;
        List<User> result = instance.findUserEntities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUserEntities method, of class UserJpaController.
     */
    @Test
    public void testFindUserEntities_int_int() {
        System.out.println("findUserEntities");
        int maxResults = 0;
        int firstResult = 0;
        UserJpaController instance = null;
        List<User> expResult = null;
        List<User> result = instance.findUserEntities(maxResults, firstResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findUser method, of class UserJpaController.
     */
    @Test
    public void testFindUser() {
        System.out.println("findUser");
        Integer id = null;
        UserJpaController instance = null;
        User expResult = null;
        User result = instance.findUser(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserCount method, of class UserJpaController.
     */
    @Test
    public void testGetUserCount() {
        System.out.println("getUserCount");
        UserJpaController instance = null;
        int expResult = 0;
        int result = instance.getUserCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
