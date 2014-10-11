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
import mks.dms.dao.entity.RequestType;

/**
 *
 * @author ThachLe
 */
public class RequestTypeJpaController implements Serializable {

    public RequestTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RequestType requestType) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(requestType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RequestType requestType) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            requestType = em.merge(requestType);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = requestType.getId();
                if (findRequestType(id) == null) {
                    throw new NonexistentEntityException("The requestType with id " + id + " no longer exists.");
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
            RequestType requestType;
            try {
                requestType = em.getReference(RequestType.class, id);
                requestType.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The requestType with id " + id + " no longer exists.", enfe);
            }
            em.remove(requestType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RequestType> findRequestTypeEntities() {
        return findRequestTypeEntities(true, -1, -1);
    }

    public List<RequestType> findRequestTypeEntities(int maxResults, int firstResult) {
        return findRequestTypeEntities(false, maxResults, firstResult);
    }

    private List<RequestType> findRequestTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from RequestType as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RequestType findRequestType(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RequestType.class, id);
        } finally {
            em.close();
        }
    }

    public int getRequestTypeCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from RequestType as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
