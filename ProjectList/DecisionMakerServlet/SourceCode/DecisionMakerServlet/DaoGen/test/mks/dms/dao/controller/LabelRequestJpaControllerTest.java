/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mks.dms.dao.controller;

import java.util.List;
import javax.persistence.EntityManager;
import mks.dms.dao.entity.LabelRequest;
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
public class LabelRequestJpaControllerTest {
    
    public LabelRequestJpaControllerTest() {
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
     * Test of getEntityManager method, of class LabelRequestJpaController.
     */
    @Test
    public void testGetEntityManager() {
        System.out.println("getEntityManager");
        LabelRequestJpaController instance = null;
        EntityManager expResult = null;
        EntityManager result = instance.getEntityManager();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class LabelRequestJpaController.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        LabelRequest labelRequest = null;
        LabelRequestJpaController instance = null;
        instance.create(labelRequest);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class LabelRequestJpaController.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        LabelRequest labelRequest = null;
        LabelRequestJpaController instance = null;
        instance.edit(labelRequest);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroy method, of class LabelRequestJpaController.
     */
    @Test
    public void testDestroy() throws Exception {
        System.out.println("destroy");
        Integer id = null;
        LabelRequestJpaController instance = null;
        instance.destroy(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findLabelRequestEntities method, of class LabelRequestJpaController.
     */
    @Test
    public void testFindLabelRequestEntities_0args() {
        System.out.println("findLabelRequestEntities");
        LabelRequestJpaController instance = null;
        List<LabelRequest> expResult = null;
        List<LabelRequest> result = instance.findLabelRequestEntities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findLabelRequestEntities method, of class LabelRequestJpaController.
     */
    @Test
    public void testFindLabelRequestEntities_int_int() {
        System.out.println("findLabelRequestEntities");
        int maxResults = 0;
        int firstResult = 0;
        LabelRequestJpaController instance = null;
        List<LabelRequest> expResult = null;
        List<LabelRequest> result = instance.findLabelRequestEntities(maxResults, firstResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findLabelRequest method, of class LabelRequestJpaController.
     */
    @Test
    public void testFindLabelRequest() {
        System.out.println("findLabelRequest");
        Integer id = null;
        LabelRequestJpaController instance = null;
        LabelRequest expResult = null;
        LabelRequest result = instance.findLabelRequest(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLabelRequestCount method, of class LabelRequestJpaController.
     */
    @Test
    public void testGetLabelRequestCount() {
        System.out.println("getLabelRequestCount");
        LabelRequestJpaController instance = null;
        int expResult = 0;
        int result = instance.getLabelRequestCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
