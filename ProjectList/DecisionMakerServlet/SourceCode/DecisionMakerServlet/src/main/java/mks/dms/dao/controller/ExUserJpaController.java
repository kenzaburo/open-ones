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

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import mks.dms.dao.entity.Department;
import mks.dms.dao.entity.User;
import mks.dms.extentity.ExUser;
import mks.dms.util.AppCons;
import mks.dms.util.SaveBatchException;
import mks.dms.util.AppCons.RESULT;

import org.apache.log4j.Logger;

/**
 * This class extends interfaces of UserJpaController. <br/>
 * 
 * @author ThachLe
 */
public class ExUserJpaController extends UserJpaController {
    /** Logging. */
    private final static Logger LOG = Logger.getLogger(ExDepartmentJpaController.class);
    
    public ExUserJpaController(EntityManagerFactory emf) {
        super(emf);
    }
    
    /**
    * Get user information by username.
    * @param username
    * @return
    * @see http://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development/Querying/JPQL
    */
    public User findUserByUsername(String username) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("User.findByUsername");
            query.setParameter("username", username);
            User user = (User) query.getSingleResult();
            
            return user;
        } catch (NoResultException nsEx) { 
            LOG.warn("User '" + username + "' not found.", nsEx);
            return null;
        } finally {
            em.close();
        }
    }

    public User findUserByUsername(String username, EntityManager em) {
        User user = null;

        try {
            
            Query query = em.createNamedQuery("User.findByUsername");
            query.setParameter("username", username);
            user = (User) query.getSingleResult();
            
            return user;
        } catch (NoResultException nsEx) { 
            // Do nothing
        }

        return user;
    }
    
    /**
     * Get user information by departmentCd.
     * @param departmentCd
     * @return
     * @see http://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development/Querying/JPQL
     */
    public List<User> getUserByDepartmentCd(String departmentCd) {
    	List<User> listUser;
    	EntityManager em = getEntityManager();
    	try {
            Query query = em.createQuery("Select e FROM User e WHERE e.departmentCd = :departmentCd");
            query.setParameter("departmentCd", departmentCd);
            listUser = (List<User>) query.getResultList();
            
            return listUser;
        } finally {
            em.close();
        }
    }
    
    public List<RESULT> save(List<User> lstUser, String username) throws SaveBatchException {
        List<RESULT> lstResult = new ArrayList<AppCons.RESULT>();
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            User user;

            User currentUser;
            AppCons.RESULT result;
            for (Iterator<User> itRequestType = lstUser.iterator(); itRequestType.hasNext(); ) {
                user = itRequestType.next();

                currentUser = findUserByUsername(username);
                
                if (currentUser == null) {
                    // Create
                    em.persist(user);
                    result = AppCons.RESULT.CREATE_OK;
                } else {
                    // Update
                    // Name
                    currentUser.setFirstname(user.getFirstname());
                    currentUser.setLastname(user.getLastname());
                    currentUser.setEmail(user.getEmail());
                    currentUser.setLastmodified(new Date());
                    currentUser.setLastmodifiedbyUsername(username);

                    em.merge(currentUser);
                    result = AppCons.RESULT.UPDATE_OK;
                }
                
                lstResult.add(result);
            }            
            
            em.getTransaction().commit();
        } catch (Throwable th) {
            em.getTransaction().rollback();
            
            throw new SaveBatchException(th);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return lstResult;
    }
}
