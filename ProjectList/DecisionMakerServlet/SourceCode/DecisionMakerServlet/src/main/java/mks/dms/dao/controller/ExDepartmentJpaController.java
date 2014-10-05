/**
 * Licensed to Open-Ones under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package mks.dms.dao.controller;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import mks.dms.dao.controller.exceptions.NonexistentEntityException;
import mks.dms.dao.entity.Department;
import mks.dms.dao.entity.User;

import org.apache.log4j.Logger;
import org.eclipse.persistence.jpa.config.NamedQuery;

/**
 * @author ThachLe
 *
 */
public class ExDepartmentJpaController extends DepartmentJpaController {
    /** Logging. */
    private final static Logger LOG = Logger.getLogger(ExDepartmentJpaController.class);
    
    public ExDepartmentJpaController(EntityManagerFactory emf) {
        super(emf);
    }

    /**
    * [Give the description for method].
    * @param deptCd department code
    * @return
    * null if no existed department code
    */
    public Department findDepartmentByCd(String deptCd) {
        EntityManager em = getEntityManager();
        
        try {
            Department dept = (Department) em.createNamedQuery("Department.findByCd")
                    .setParameter("cd", deptCd)
                    .getSingleResult();
            
            return dept;
        } finally {
            em.close();
        }
    }

    /**
    * Delete and create new departments.
    * @param departments
    * @return true if success
    * @throws NonexistentEntityException
    */
    public boolean recreateAll(List<Department> departments) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Department department;

                Query query = em.createQuery("DELETE FROM Department");
                
                int deletedCount = query.executeUpdate();
                LOG.debug("deletedCount=" + deletedCount);
                // Create departments
                Iterator<Department> itDepartment = departments.iterator();

                while (itDepartment.hasNext()) {
                    department = itDepartment.next();
                    
                    // Refer public void create(Department department) in super class
                    em.persist(department);
                    // Refer
                }
            
            em.getTransaction().commit();
            
            return true;
        } catch (Exception ex) {
            LOG.error("Could not re-create Department", ex);
            em.getTransaction().rollback();
            
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
    }
}
