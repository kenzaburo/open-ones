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
import mks.dms.dao.entity.ResetPassword;

/**
 *
 * @author ThachLe
 */
public class ResetPasswordJpaController implements Serializable {

    public ResetPasswordJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ResetPassword resetPassword) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(resetPassword);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ResetPassword resetPassword) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            resetPassword = em.merge(resetPassword);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = resetPassword.getId();
                if (findResetPassword(id) == null) {
                    throw new NonexistentEntityException("The resetPassword with id " + id + " no longer exists.");
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
            ResetPassword resetPassword;
            try {
                resetPassword = em.getReference(ResetPassword.class, id);
                resetPassword.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resetPassword with id " + id + " no longer exists.", enfe);
            }
            em.remove(resetPassword);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ResetPassword> findResetPasswordEntities() {
        return findResetPasswordEntities(true, -1, -1);
    }

    public List<ResetPassword> findResetPasswordEntities(int maxResults, int firstResult) {
        return findResetPasswordEntities(false, maxResults, firstResult);
    }

    private List<ResetPassword> findResetPasswordEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ResetPassword.class));
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

    public ResetPassword findResetPassword(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ResetPassword.class, id);
        } finally {
            em.close();
        }
    }

    public int getResetPasswordCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ResetPassword> rt = cq.from(ResetPassword.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
