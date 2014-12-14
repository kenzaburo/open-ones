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
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mks.dms.dao.controller.exceptions.NonexistentEntityException;
import mks.dms.dao.entity.StatusFlow;

/**
 *
 * @author ThachLe
 */
public class StatusFlowJpaController implements Serializable {

    public StatusFlowJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(StatusFlow statusFlow) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(statusFlow);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(StatusFlow statusFlow) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            statusFlow = em.merge(statusFlow);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = statusFlow.getId();
                if (findStatusFlow(id) == null) {
                    throw new NonexistentEntityException("The statusFlow with id " + id + " no longer exists.");
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
            StatusFlow statusFlow;
            try {
                statusFlow = em.getReference(StatusFlow.class, id);
                statusFlow.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The statusFlow with id " + id + " no longer exists.", enfe);
            }
            em.remove(statusFlow);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<StatusFlow> findStatusFlowEntities() {
        return findStatusFlowEntities(true, -1, -1);
    }

    public List<StatusFlow> findStatusFlowEntities(int maxResults, int firstResult) {
        return findStatusFlowEntities(false, maxResults, firstResult);
    }

    private List<StatusFlow> findStatusFlowEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StatusFlow.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public StatusFlow findStatusFlow(Integer id) {
        EntityManager em = getEntityManager();
        try {
            if (id != null) {
                return em.find(StatusFlow.class, id);
            } else {
                return null;
            }
        } finally {
            em.close();
        }
    }

    public int getStatusFlowCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StatusFlow> rt = cq.from(StatusFlow.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
