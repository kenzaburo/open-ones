/**
 * MKS Copyright 2012.
 */
package com.mks.cms.gae.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.mks.cms.gae.dto.ModuleDTO;

/**
 * @author thachle
 *
 */
public class ModuleDaoTest {
    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    
    ModuleDao moduleDao = new ModuleDao("user01", "email01@test.com");
    /**
     * Test method for {@link com.mks.cms.gae.dao.ModuleDao#save(com.mks.cms.gae.dto.ModuleDTO)}.
     */
    @Test
    public void testSave() {
        ModuleDTO module = new ModuleDTO("id1", "name1", "Content1");
        boolean saveOk = moduleDao.save(module);
        assertTrue(saveOk);
        
        List<ModuleDTO> moduleList = moduleDao.getModules();
        assertNotNull(moduleList);
        assertEquals(1, moduleList.size());
        ModuleDTO loadedModule = moduleList.get(0);
        
        assertEquals(module, loadedModule);
        
        assertEquals("email01@test.com", loadedModule.getCreatedBy());
        
        moduleDao.delete(module);
        
        moduleList = moduleDao.getModules();
        assertNotNull(moduleList);
        assertEquals(0, moduleList.size());
    }

    /**
     * Test method for {@link com.mks.cms.gae.dao.ModuleDao#getModules()}.
     */
    @Test
    public void testGetModules() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.mks.cms.gae.dao.ModuleDao#delete(com.mks.cms.gae.dto.ModuleDTO)}.
     */
    @Test
    public void testDeleteModuleDTO() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.mks.cms.gae.dao.ModuleDao#update(com.mks.cms.gae.dto.ModuleDTO)}.
     */
    @Test
    public void testUpdate() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link com.mks.cms.gae.dao.ModuleDao#getModuleByKey(java.lang.Long)}.
     */
    @Test
    public void testGetModuleByKey() {
        fail("Not yet implemented");
    }

}
