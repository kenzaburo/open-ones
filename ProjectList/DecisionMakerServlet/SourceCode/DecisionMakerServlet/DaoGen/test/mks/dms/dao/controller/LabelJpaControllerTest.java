/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mks.dms.dao.controller;

import java.util.List;
import javax.persistence.EntityManager;
import mks.dms.dao.entity.Label;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ThachLN
 */
public class LabelJpaControllerTest {
    
    public LabelJpaControllerTest() {
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
     * Test of getEntityManager method, of class LabelJpaController.
     */
    @Test
    public void testGetEntityManager() {
        System.out.println("getEntityManager");
        LabelJpaController instance = null;
        EntityManager expResult = null;
        EntityManager result = instance.getEntityManager();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class LabelJpaController.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Label label = null;
        LabelJpaController instance = null;
        instance.create(label);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class LabelJpaController.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        Label label = null;
        LabelJpaController instance = null;
        instance.edit(label);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroy method, of class LabelJpaController.
     */
    @Test
    public void testDestroy() throws Exception {
        System.out.println("destroy");
        Integer id = null;
        LabelJpaController instance = null;
        instance.destroy(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findLabelEntities method, of class LabelJpaController.
     */
    @Test
    public void testFindLabelEntities_0args() {
        System.out.println("findLabelEntities");
        LabelJpaController instance = null;
        List<Label> expResult = null;
        List<Label> result = instance.findLabelEntities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findLabelEntities method, of class LabelJpaController.
     */
    @Test
    public void testFindLabelEntities_int_int() {
        System.out.println("findLabelEntities");
        int maxResults = 0;
        int firstResult = 0;
        LabelJpaController instance = null;
        List<Label> expResult = null;
        List<Label> result = instance.findLabelEntities(maxResults, firstResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findLabel method, of class LabelJpaController.
     */
    @Test
    public void testFindLabel() {
        System.out.println("findLabel");
        Integer id = null;
        LabelJpaController instance = null;
        Label expResult = null;
        Label result = instance.findLabel(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLabelCount method, of class LabelJpaController.
     */
    @Test
    public void testGetLabelCount() {
        System.out.println("getLabelCount");
        LabelJpaController instance = null;
        int expResult = 0;
        int result = instance.getLabelCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
