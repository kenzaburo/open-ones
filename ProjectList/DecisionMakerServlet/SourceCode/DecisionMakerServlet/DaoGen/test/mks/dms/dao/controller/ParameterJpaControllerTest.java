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

import mks.dms.dao.entity.Parameter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author ThachLe
 */
public class ParameterJpaControllerTest {
    
    public ParameterJpaControllerTest() {
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
     * Test of getEntityManager method, of class ParameterJpaController.
     */
    @Test
    public void testGetEntityManager() {
        System.out.println("getEntityManager");
        ParameterJpaController instance = null;
        EntityManager expResult = null;
        EntityManager result = instance.getEntityManager();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class ParameterJpaController.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Parameter parameter = new Parameter();
        parameter.setCd("MailServer");
        parameter.setValue("mail.google.com");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DecisionMaker-DBModelPU");
        ParameterJpaController daoCtrl = new ParameterJpaController(emf );
        daoCtrl.create(parameter);
        
        // Find
        Parameter createParam = daoCtrl.findParameter(parameter.getId());
        assertEquals("MailServer", createParam.getCd());
        assertEquals("mail.google.com", createParam.getValue());
    }

    /**
     * Test of edit method, of class ParameterJpaController.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        Parameter parameter = null;
        ParameterJpaController instance = null;
        instance.edit(parameter);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroy method, of class ParameterJpaController.
     */
    @Test
    public void testDestroy() throws Exception {
        System.out.println("destroy");
        Integer id = null;
        ParameterJpaController instance = null;
        instance.destroy(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findParameterEntities method, of class ParameterJpaController.
     */
    @Test
    public void testFindParameterEntities_0args() {
        System.out.println("findParameterEntities");
        ParameterJpaController instance = null;
        List<Parameter> expResult = null;
        List<Parameter> result = instance.findParameterEntities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findParameterEntities method, of class ParameterJpaController.
     */
    @Test
    public void testFindParameterEntities_int_int() {
        System.out.println("findParameterEntities");
        int maxResults = 0;
        int firstResult = 0;
        ParameterJpaController instance = null;
        List<Parameter> expResult = null;
        List<Parameter> result = instance.findParameterEntities(maxResults, firstResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findParameter method, of class ParameterJpaController.
     */
    @Test
    public void testFindParameter() {
        System.out.println("findParameter");
        Integer id = null;
        ParameterJpaController instance = null;
        Parameter expResult = null;
        Parameter result = instance.findParameter(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getParameterCount method, of class ParameterJpaController.
     */
    @Test
    public void testGetParameterCount() {
        System.out.println("getParameterCount");
        ParameterJpaController instance = null;
        int expResult = 0;
        int result = instance.getParameterCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
