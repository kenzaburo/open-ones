/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mks.dms.dao.controller;

import java.util.List;
import javax.persistence.EntityManager;
import mks.dms.dao.entity.Template;
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
public class TemplateJpaControllerTest {
    
    public TemplateJpaControllerTest() {
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
     * Test of getEntityManager method, of class TemplateJpaController.
     */
    @Test
    public void testGetEntityManager() {
        System.out.println("getEntityManager");
        TemplateJpaController instance = null;
        EntityManager expResult = null;
        EntityManager result = instance.getEntityManager();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class TemplateJpaController.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Template template = null;
        TemplateJpaController instance = null;
        instance.create(template);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class TemplateJpaController.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        Template template = null;
        TemplateJpaController instance = null;
        instance.edit(template);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroy method, of class TemplateJpaController.
     */
    @Test
    public void testDestroy() throws Exception {
        System.out.println("destroy");
        Integer id = null;
        TemplateJpaController instance = null;
        instance.destroy(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findTemplateEntities method, of class TemplateJpaController.
     */
    @Test
    public void testFindTemplateEntities_0args() {
        System.out.println("findTemplateEntities");
        TemplateJpaController instance = null;
        List<Template> expResult = null;
        List<Template> result = instance.findTemplateEntities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findTemplateEntities method, of class TemplateJpaController.
     */
    @Test
    public void testFindTemplateEntities_int_int() {
        System.out.println("findTemplateEntities");
        int maxResults = 0;
        int firstResult = 0;
        TemplateJpaController instance = null;
        List<Template> expResult = null;
        List<Template> result = instance.findTemplateEntities(maxResults, firstResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findTemplate method, of class TemplateJpaController.
     */
    @Test
    public void testFindTemplate() {
        System.out.println("findTemplate");
        Integer id = null;
        TemplateJpaController instance = null;
        Template expResult = null;
        Template result = instance.findTemplate(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTemplateCount method, of class TemplateJpaController.
     */
    @Test
    public void testGetTemplateCount() {
        System.out.println("getTemplateCount");
        TemplateJpaController instance = null;
        int expResult = 0;
        int result = instance.getTemplateCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
