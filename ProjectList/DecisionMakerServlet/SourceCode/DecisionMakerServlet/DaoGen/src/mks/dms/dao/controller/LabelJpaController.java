/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mks.dms.dao.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mks.dms.dao.entity.LabelRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mks.dms.dao.controller.exceptions.IllegalOrphanException;
import mks.dms.dao.controller.exceptions.NonexistentEntityException;
import mks.dms.dao.entity.Label;

/**
 *
 * @author ThachLN
 */
public class LabelJpaController implements Serializable {

    public LabelJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Label label) {
        if (label.getLabelRequestCollection() == null) {
            label.setLabelRequestCollection(new ArrayList<LabelRequest>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<LabelRequest> attachedLabelRequestCollection = new ArrayList<LabelRequest>();
            for (LabelRequest labelRequestCollectionLabelRequestToAttach : label.getLabelRequestCollection()) {
                labelRequestCollectionLabelRequestToAttach = em.getReference(labelRequestCollectionLabelRequestToAttach.getClass(), labelRequestCollectionLabelRequestToAttach.getId());
                attachedLabelRequestCollection.add(labelRequestCollectionLabelRequestToAttach);
            }
            label.setLabelRequestCollection(attachedLabelRequestCollection);
            em.persist(label);
            for (LabelRequest labelRequestCollectionLabelRequest : label.getLabelRequestCollection()) {
                Label oldLabelIdOfLabelRequestCollectionLabelRequest = labelRequestCollectionLabelRequest.getLabelId();
                labelRequestCollectionLabelRequest.setLabelId(label);
                labelRequestCollectionLabelRequest = em.merge(labelRequestCollectionLabelRequest);
                if (oldLabelIdOfLabelRequestCollectionLabelRequest != null) {
                    oldLabelIdOfLabelRequestCollectionLabelRequest.getLabelRequestCollection().remove(labelRequestCollectionLabelRequest);
                    oldLabelIdOfLabelRequestCollectionLabelRequest = em.merge(oldLabelIdOfLabelRequestCollectionLabelRequest);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Label label) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Label persistentLabel = em.find(Label.class, label.getId());
            Collection<LabelRequest> labelRequestCollectionOld = persistentLabel.getLabelRequestCollection();
            Collection<LabelRequest> labelRequestCollectionNew = label.getLabelRequestCollection();
            List<String> illegalOrphanMessages = null;
            for (LabelRequest labelRequestCollectionOldLabelRequest : labelRequestCollectionOld) {
                if (!labelRequestCollectionNew.contains(labelRequestCollectionOldLabelRequest)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain LabelRequest " + labelRequestCollectionOldLabelRequest + " since its labelId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<LabelRequest> attachedLabelRequestCollectionNew = new ArrayList<LabelRequest>();
            for (LabelRequest labelRequestCollectionNewLabelRequestToAttach : labelRequestCollectionNew) {
                labelRequestCollectionNewLabelRequestToAttach = em.getReference(labelRequestCollectionNewLabelRequestToAttach.getClass(), labelRequestCollectionNewLabelRequestToAttach.getId());
                attachedLabelRequestCollectionNew.add(labelRequestCollectionNewLabelRequestToAttach);
            }
            labelRequestCollectionNew = attachedLabelRequestCollectionNew;
            label.setLabelRequestCollection(labelRequestCollectionNew);
            label = em.merge(label);
            for (LabelRequest labelRequestCollectionNewLabelRequest : labelRequestCollectionNew) {
                if (!labelRequestCollectionOld.contains(labelRequestCollectionNewLabelRequest)) {
                    Label oldLabelIdOfLabelRequestCollectionNewLabelRequest = labelRequestCollectionNewLabelRequest.getLabelId();
                    labelRequestCollectionNewLabelRequest.setLabelId(label);
                    labelRequestCollectionNewLabelRequest = em.merge(labelRequestCollectionNewLabelRequest);
                    if (oldLabelIdOfLabelRequestCollectionNewLabelRequest != null && !oldLabelIdOfLabelRequestCollectionNewLabelRequest.equals(label)) {
                        oldLabelIdOfLabelRequestCollectionNewLabelRequest.getLabelRequestCollection().remove(labelRequestCollectionNewLabelRequest);
                        oldLabelIdOfLabelRequestCollectionNewLabelRequest = em.merge(oldLabelIdOfLabelRequestCollectionNewLabelRequest);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = label.getId();
                if (findLabel(id) == null) {
                    throw new NonexistentEntityException("The label with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Label label;
            try {
                label = em.getReference(Label.class, id);
                label.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The label with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<LabelRequest> labelRequestCollectionOrphanCheck = label.getLabelRequestCollection();
            for (LabelRequest labelRequestCollectionOrphanCheckLabelRequest : labelRequestCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Label (" + label + ") cannot be destroyed since the LabelRequest " + labelRequestCollectionOrphanCheckLabelRequest + " in its labelRequestCollection field has a non-nullable labelId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(label);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Label> findLabelEntities() {
        return findLabelEntities(true, -1, -1);
    }

    public List<Label> findLabelEntities(int maxResults, int firstResult) {
        return findLabelEntities(false, maxResults, firstResult);
    }

    private List<Label> findLabelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Label.class));
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

    public Label findLabel(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Label.class, id);
        } finally {
            em.close();
        }
    }

    public int getLabelCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Label> rt = cq.from(Label.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
