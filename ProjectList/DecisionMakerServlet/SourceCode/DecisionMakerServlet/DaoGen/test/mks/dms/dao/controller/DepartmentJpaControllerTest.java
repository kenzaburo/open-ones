/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mks.dms.dao.controller;

import java.util.List;
import javax.persistence.EntityManager;
import mks.dms.dao.entity.Department;
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
public class DepartmentJpaControllerTest {
    
    public DepartmentJpaControllerTest() {
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
     * Test of getEntityManager method, of class DepartmentJpaController.
     */
    @Test
    public void testGetEntityManager() {
        System.out.println("getEntityManager");
        DepartmentJpaController instance = null;
        EntityManager expResult = null;
        EntityManager result = instance.getEntityManager();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class DepartmentJpaController.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Department department = null;
        DepartmentJpaController instance = null;
        instance.create(department);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class DepartmentJpaController.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        Department department = null;
        DepartmentJpaController instance = null;
        instance.edit(department);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroy method, of class DepartmentJpaController.
     */
    @Test
    public void testDestroy() throws Exception {
        System.out.println("destroy");
        Integer id = null;
        DepartmentJpaController instance = null;
        instance.destroy(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findDepartmentEntities method, of class DepartmentJpaController.
     */
    @Test
    public void testFindDepartmentEntities_0args() {
        System.out.println("findDepartmentEntities");
        DepartmentJpaController instance = null;
        List<Department> expResult = null;
        List<Department> result = instance.findDepartmentEntities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findDepartmentEntities method, of class DepartmentJpaController.
     */
    @Test
    public void testFindDepartmentEntities_int_int() {
        System.out.println("findDepartmentEntities");
        int maxResults = 0;
        int firstResult = 0;
        DepartmentJpaController instance = null;
        List<Department> expResult = null;
        List<Department> result = instance.findDepartmentEntities(maxResults, firstResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findDepartment method, of class DepartmentJpaController.
     */
    @Test
    public void testFindDepartment() {
        System.out.println("findDepartment");
        Integer id = null;
        DepartmentJpaController instance = null;
        Department expResult = null;
        Department result = instance.findDepartment(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDepartmentCount method, of class DepartmentJpaController.
     */
    @Test
    public void testGetDepartmentCount() {
        System.out.println("getDepartmentCount");
        DepartmentJpaController instance = null;
        int expResult = 0;
        int result = instance.getDepartmentCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
