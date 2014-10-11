/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mks.dms.dao.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import mks.dms.dao.controller.exceptions.NonexistentEntityException;
import mks.dms.dao.entity.RequestDepartment;

/**
 *
 * @author ThachLe
 */
public class RequestDepartmentJpaController implements Serializable {

    public RequestDepartmentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RequestDepartment requestDepartment) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(requestDepartment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RequestDepartment requestDepartment) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            requestDepartment = em.merge(requestDepartment);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = requestDepartment.getId();
                if (findRequestDepartment(id) == null) {
                    throw new NonexistentEntityException("The requestDepartment with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RequestDepartment requestDepartment;
            try {
                requestDepartment = em.getReference(RequestDepartment.class, id);
                requestDepartment.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The requestDepartment with id " + id + " no longer exists.", enfe);
            }
            em.remove(requestDepartment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RequestDepartment> findRequestDepartmentEntities() {
        return findRequestDepartmentEntities(true, -1, -1);
    }

    public List<RequestDepartment> findRequestDepartmentEntities(int maxResults, int firstResult) {
        return findRequestDepartmentEntities(false, maxResults, firstResult);
    }

    private List<RequestDepartment> findRequestDepartmentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from RequestDepartment as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RequestDepartment findRequestDepartment(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RequestDepartment.class, id);
        } finally {
            em.close();
        }
    }

    public int getRequestDepartmentCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from RequestDepartment as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
