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
import mks.dms.dao.entity.Request;
import mks.dms.dao.entity.Label;
import mks.dms.dao.entity.LabelRequest;

/**
 *
 * @author ThachLN
 */
public class LabelRequestJpaController implements Serializable {

    public LabelRequestJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LabelRequest labelRequest) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Request requestId = labelRequest.getRequestId();
            if (requestId != null) {
                requestId = em.getReference(requestId.getClass(), requestId.getId());
                labelRequest.setRequestId(requestId);
            }
            Label labelId = labelRequest.getLabelId();
            if (labelId != null) {
                labelId = em.getReference(labelId.getClass(), labelId.getId());
                labelRequest.setLabelId(labelId);
            }
            Request reqId = labelRequest.getReqId();
            if (reqId != null) {
                reqId = em.getReference(reqId.getClass(), reqId.getId());
                labelRequest.setReqId(reqId);
            }
            em.persist(labelRequest);
            if (requestId != null) {
                requestId.getLabelRequestCollection().add(labelRequest);
                requestId = em.merge(requestId);
            }
            if (labelId != null) {
                labelId.getLabelRequestCollection().add(labelRequest);
                labelId = em.merge(labelId);
            }
            if (reqId != null) {
                reqId.getLabelRequestCollection().add(labelRequest);
                reqId = em.merge(reqId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LabelRequest labelRequest) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LabelRequest persistentLabelRequest = em.find(LabelRequest.class, labelRequest.getId());
            Request requestIdOld = persistentLabelRequest.getRequestId();
            Request requestIdNew = labelRequest.getRequestId();
            Label labelIdOld = persistentLabelRequest.getLabelId();
            Label labelIdNew = labelRequest.getLabelId();
            Request reqIdOld = persistentLabelRequest.getReqId();
            Request reqIdNew = labelRequest.getReqId();
            if (requestIdNew != null) {
                requestIdNew = em.getReference(requestIdNew.getClass(), requestIdNew.getId());
                labelRequest.setRequestId(requestIdNew);
            }
            if (labelIdNew != null) {
                labelIdNew = em.getReference(labelIdNew.getClass(), labelIdNew.getId());
                labelRequest.setLabelId(labelIdNew);
            }
            if (reqIdNew != null) {
                reqIdNew = em.getReference(reqIdNew.getClass(), reqIdNew.getId());
                labelRequest.setReqId(reqIdNew);
            }
            labelRequest = em.merge(labelRequest);
            if (requestIdOld != null && !requestIdOld.equals(requestIdNew)) {
                requestIdOld.getLabelRequestCollection().remove(labelRequest);
                requestIdOld = em.merge(requestIdOld);
            }
            if (requestIdNew != null && !requestIdNew.equals(requestIdOld)) {
                requestIdNew.getLabelRequestCollection().add(labelRequest);
                requestIdNew = em.merge(requestIdNew);
            }
            if (labelIdOld != null && !labelIdOld.equals(labelIdNew)) {
                labelIdOld.getLabelRequestCollection().remove(labelRequest);
                labelIdOld = em.merge(labelIdOld);
            }
            if (labelIdNew != null && !labelIdNew.equals(labelIdOld)) {
                labelIdNew.getLabelRequestCollection().add(labelRequest);
                labelIdNew = em.merge(labelIdNew);
            }
            if (reqIdOld != null && !reqIdOld.equals(reqIdNew)) {
                reqIdOld.getLabelRequestCollection().remove(labelRequest);
                reqIdOld = em.merge(reqIdOld);
            }
            if (reqIdNew != null && !reqIdNew.equals(reqIdOld)) {
                reqIdNew.getLabelRequestCollection().add(labelRequest);
                reqIdNew = em.merge(reqIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = labelRequest.getId();
                if (findLabelRequest(id) == null) {
                    throw new NonexistentEntityException("The labelRequest with id " + id + " no longer exists.");
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
            LabelRequest labelRequest;
            try {
                labelRequest = em.getReference(LabelRequest.class, id);
                labelRequest.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The labelRequest with id " + id + " no longer exists.", enfe);
            }
            Request requestId = labelRequest.getRequestId();
            if (requestId != null) {
                requestId.getLabelRequestCollection().remove(labelRequest);
                requestId = em.merge(requestId);
            }
            Label labelId = labelRequest.getLabelId();
            if (labelId != null) {
                labelId.getLabelRequestCollection().remove(labelRequest);
                labelId = em.merge(labelId);
            }
            Request reqId = labelRequest.getReqId();
            if (reqId != null) {
                reqId.getLabelRequestCollection().remove(labelRequest);
                reqId = em.merge(reqId);
            }
            em.remove(labelRequest);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LabelRequest> findLabelRequestEntities() {
        return findLabelRequestEntities(true, -1, -1);
    }

    public List<LabelRequest> findLabelRequestEntities(int maxResults, int firstResult) {
        return findLabelRequestEntities(false, maxResults, firstResult);
    }

    private List<LabelRequest> findLabelRequestEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LabelRequest.class));
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

    public LabelRequest findLabelRequest(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LabelRequest.class, id);
        } finally {
            em.close();
        }
    }

    public int getLabelRequestCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LabelRequest> rt = cq.from(LabelRequest.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
