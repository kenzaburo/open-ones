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
import mks.dms.dao.entity.User;
import mks.dms.dao.entity.Request;
import mks.dms.dao.entity.Watcher;

/**
 *
 * @author ThachLN
 */
public class WatcherJpaController implements Serializable {

    public WatcherJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Watcher watcher) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User userId = watcher.getUserId();
            if (userId != null) {
                userId = em.getReference(userId.getClass(), userId.getId());
                watcher.setUserId(userId);
            }
            Request reqId = watcher.getReqId();
            if (reqId != null) {
                reqId = em.getReference(reqId.getClass(), reqId.getId());
                watcher.setReqId(reqId);
            }
            em.persist(watcher);
            if (userId != null) {
                userId.getWatcherCollection().add(watcher);
                userId = em.merge(userId);
            }
            if (reqId != null) {
                reqId.getWatcherCollection().add(watcher);
                reqId = em.merge(reqId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Watcher watcher) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Watcher persistentWatcher = em.find(Watcher.class, watcher.getId());
            User userIdOld = persistentWatcher.getUserId();
            User userIdNew = watcher.getUserId();
            Request reqIdOld = persistentWatcher.getReqId();
            Request reqIdNew = watcher.getReqId();
            if (userIdNew != null) {
                userIdNew = em.getReference(userIdNew.getClass(), userIdNew.getId());
                watcher.setUserId(userIdNew);
            }
            if (reqIdNew != null) {
                reqIdNew = em.getReference(reqIdNew.getClass(), reqIdNew.getId());
                watcher.setReqId(reqIdNew);
            }
            watcher = em.merge(watcher);
            if (userIdOld != null && !userIdOld.equals(userIdNew)) {
                userIdOld.getWatcherCollection().remove(watcher);
                userIdOld = em.merge(userIdOld);
            }
            if (userIdNew != null && !userIdNew.equals(userIdOld)) {
                userIdNew.getWatcherCollection().add(watcher);
                userIdNew = em.merge(userIdNew);
            }
            if (reqIdOld != null && !reqIdOld.equals(reqIdNew)) {
                reqIdOld.getWatcherCollection().remove(watcher);
                reqIdOld = em.merge(reqIdOld);
            }
            if (reqIdNew != null && !reqIdNew.equals(reqIdOld)) {
                reqIdNew.getWatcherCollection().add(watcher);
                reqIdNew = em.merge(reqIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = watcher.getId();
                if (findWatcher(id) == null) {
                    throw new NonexistentEntityException("The watcher with id " + id + " no longer exists.");
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
            Watcher watcher;
            try {
                watcher = em.getReference(Watcher.class, id);
                watcher.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The watcher with id " + id + " no longer exists.", enfe);
            }
            User userId = watcher.getUserId();
            if (userId != null) {
                userId.getWatcherCollection().remove(watcher);
                userId = em.merge(userId);
            }
            Request reqId = watcher.getReqId();
            if (reqId != null) {
                reqId.getWatcherCollection().remove(watcher);
                reqId = em.merge(reqId);
            }
            em.remove(watcher);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Watcher> findWatcherEntities() {
        return findWatcherEntities(true, -1, -1);
    }

    public List<Watcher> findWatcherEntities(int maxResults, int firstResult) {
        return findWatcherEntities(false, maxResults, firstResult);
    }

    private List<Watcher> findWatcherEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Watcher.class));
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

    public Watcher findWatcher(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Watcher.class, id);
        } finally {
            em.close();
        }
    }

    public int getWatcherCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Watcher> rt = cq.from(Watcher.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
