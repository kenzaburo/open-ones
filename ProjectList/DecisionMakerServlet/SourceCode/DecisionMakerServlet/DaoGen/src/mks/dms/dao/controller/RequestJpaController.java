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
import mks.dms.dao.entity.Department;
import mks.dms.dao.entity.User;
import mks.dms.dao.entity.Watcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mks.dms.dao.controller.exceptions.IllegalOrphanException;
import mks.dms.dao.controller.exceptions.NonexistentEntityException;
import mks.dms.dao.entity.LabelRequest;
import mks.dms.dao.entity.Request;

/**
 *
 * @author ThachLN
 */
public class RequestJpaController implements Serializable {

    public RequestJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Request request) {
        if (request.getWatcherCollection() == null) {
            request.setWatcherCollection(new ArrayList<Watcher>());
        }
        if (request.getLabelRequestCollection() == null) {
            request.setLabelRequestCollection(new ArrayList<LabelRequest>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Department departmentsId = request.getDepartmentsId();
            if (departmentsId != null) {
                departmentsId = em.getReference(departmentsId.getClass(), departmentsId.getId());
                request.setDepartmentsId(departmentsId);
            }
            User createdbyId = request.getCreatedbyId();
            if (createdbyId != null) {
                createdbyId = em.getReference(createdbyId.getClass(), createdbyId.getId());
                request.setCreatedbyId(createdbyId);
            }
            User managerId = request.getManagerId();
            if (managerId != null) {
                managerId = em.getReference(managerId.getClass(), managerId.getId());
                request.setManagerId(managerId);
            }
            User assignedId = request.getAssignedId();
            if (assignedId != null) {
                assignedId = em.getReference(assignedId.getClass(), assignedId.getId());
                request.setAssignedId(assignedId);
            }
            Collection<Watcher> attachedWatcherCollection = new ArrayList<Watcher>();
            for (Watcher watcherCollectionWatcherToAttach : request.getWatcherCollection()) {
                watcherCollectionWatcherToAttach = em.getReference(watcherCollectionWatcherToAttach.getClass(), watcherCollectionWatcherToAttach.getId());
                attachedWatcherCollection.add(watcherCollectionWatcherToAttach);
            }
            request.setWatcherCollection(attachedWatcherCollection);
            Collection<LabelRequest> attachedLabelRequestCollection = new ArrayList<LabelRequest>();
            for (LabelRequest labelRequestCollectionLabelRequestToAttach : request.getLabelRequestCollection()) {
                labelRequestCollectionLabelRequestToAttach = em.getReference(labelRequestCollectionLabelRequestToAttach.getClass(), labelRequestCollectionLabelRequestToAttach.getId());
                attachedLabelRequestCollection.add(labelRequestCollectionLabelRequestToAttach);
            }
            request.setLabelRequestCollection(attachedLabelRequestCollection);
            em.persist(request);
            if (departmentsId != null) {
                departmentsId.getRequestCollection().add(request);
                departmentsId = em.merge(departmentsId);
            }
            if (createdbyId != null) {
                createdbyId.getRequestCollection().add(request);
                createdbyId = em.merge(createdbyId);
            }
            if (managerId != null) {
                managerId.getRequestCollection().add(request);
                managerId = em.merge(managerId);
            }
            if (assignedId != null) {
                assignedId.getRequestCollection().add(request);
                assignedId = em.merge(assignedId);
            }
            for (Watcher watcherCollectionWatcher : request.getWatcherCollection()) {
                Request oldReqIdOfWatcherCollectionWatcher = watcherCollectionWatcher.getReqId();
                watcherCollectionWatcher.setReqId(request);
                watcherCollectionWatcher = em.merge(watcherCollectionWatcher);
                if (oldReqIdOfWatcherCollectionWatcher != null) {
                    oldReqIdOfWatcherCollectionWatcher.getWatcherCollection().remove(watcherCollectionWatcher);
                    oldReqIdOfWatcherCollectionWatcher = em.merge(oldReqIdOfWatcherCollectionWatcher);
                }
            }
            for (LabelRequest labelRequestCollectionLabelRequest : request.getLabelRequestCollection()) {
                Request oldRequestIdOfLabelRequestCollectionLabelRequest = labelRequestCollectionLabelRequest.getRequestId();
                labelRequestCollectionLabelRequest.setRequestId(request);
                labelRequestCollectionLabelRequest = em.merge(labelRequestCollectionLabelRequest);
                if (oldRequestIdOfLabelRequestCollectionLabelRequest != null) {
                    oldRequestIdOfLabelRequestCollectionLabelRequest.getLabelRequestCollection().remove(labelRequestCollectionLabelRequest);
                    oldRequestIdOfLabelRequestCollectionLabelRequest = em.merge(oldRequestIdOfLabelRequestCollectionLabelRequest);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Request request) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Request persistentRequest = em.find(Request.class, request.getId());
            Department departmentsIdOld = persistentRequest.getDepartmentsId();
            Department departmentsIdNew = request.getDepartmentsId();
            User createdbyIdOld = persistentRequest.getCreatedbyId();
            User createdbyIdNew = request.getCreatedbyId();
            User managerIdOld = persistentRequest.getManagerId();
            User managerIdNew = request.getManagerId();
            User assignedIdOld = persistentRequest.getAssignedId();
            User assignedIdNew = request.getAssignedId();
            Collection<Watcher> watcherCollectionOld = persistentRequest.getWatcherCollection();
            Collection<Watcher> watcherCollectionNew = request.getWatcherCollection();
            Collection<LabelRequest> labelRequestCollectionOld = persistentRequest.getLabelRequestCollection();
            Collection<LabelRequest> labelRequestCollectionNew = request.getLabelRequestCollection();
            List<String> illegalOrphanMessages = null;
            for (Watcher watcherCollectionOldWatcher : watcherCollectionOld) {
                if (!watcherCollectionNew.contains(watcherCollectionOldWatcher)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Watcher " + watcherCollectionOldWatcher + " since its reqId field is not nullable.");
                }
            }
            for (LabelRequest labelRequestCollectionOldLabelRequest : labelRequestCollectionOld) {
                if (!labelRequestCollectionNew.contains(labelRequestCollectionOldLabelRequest)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain LabelRequest " + labelRequestCollectionOldLabelRequest + " since its requestId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (departmentsIdNew != null) {
                departmentsIdNew = em.getReference(departmentsIdNew.getClass(), departmentsIdNew.getId());
                request.setDepartmentsId(departmentsIdNew);
            }
            if (createdbyIdNew != null) {
                createdbyIdNew = em.getReference(createdbyIdNew.getClass(), createdbyIdNew.getId());
                request.setCreatedbyId(createdbyIdNew);
            }
            if (managerIdNew != null) {
                managerIdNew = em.getReference(managerIdNew.getClass(), managerIdNew.getId());
                request.setManagerId(managerIdNew);
            }
            if (assignedIdNew != null) {
                assignedIdNew = em.getReference(assignedIdNew.getClass(), assignedIdNew.getId());
                request.setAssignedId(assignedIdNew);
            }
            Collection<Watcher> attachedWatcherCollectionNew = new ArrayList<Watcher>();
            for (Watcher watcherCollectionNewWatcherToAttach : watcherCollectionNew) {
                watcherCollectionNewWatcherToAttach = em.getReference(watcherCollectionNewWatcherToAttach.getClass(), watcherCollectionNewWatcherToAttach.getId());
                attachedWatcherCollectionNew.add(watcherCollectionNewWatcherToAttach);
            }
            watcherCollectionNew = attachedWatcherCollectionNew;
            request.setWatcherCollection(watcherCollectionNew);
            Collection<LabelRequest> attachedLabelRequestCollectionNew = new ArrayList<LabelRequest>();
            for (LabelRequest labelRequestCollectionNewLabelRequestToAttach : labelRequestCollectionNew) {
                labelRequestCollectionNewLabelRequestToAttach = em.getReference(labelRequestCollectionNewLabelRequestToAttach.getClass(), labelRequestCollectionNewLabelRequestToAttach.getId());
                attachedLabelRequestCollectionNew.add(labelRequestCollectionNewLabelRequestToAttach);
            }
            labelRequestCollectionNew = attachedLabelRequestCollectionNew;
            request.setLabelRequestCollection(labelRequestCollectionNew);
            request = em.merge(request);
            if (departmentsIdOld != null && !departmentsIdOld.equals(departmentsIdNew)) {
                departmentsIdOld.getRequestCollection().remove(request);
                departmentsIdOld = em.merge(departmentsIdOld);
            }
            if (departmentsIdNew != null && !departmentsIdNew.equals(departmentsIdOld)) {
                departmentsIdNew.getRequestCollection().add(request);
                departmentsIdNew = em.merge(departmentsIdNew);
            }
            if (createdbyIdOld != null && !createdbyIdOld.equals(createdbyIdNew)) {
                createdbyIdOld.getRequestCollection().remove(request);
                createdbyIdOld = em.merge(createdbyIdOld);
            }
            if (createdbyIdNew != null && !createdbyIdNew.equals(createdbyIdOld)) {
                createdbyIdNew.getRequestCollection().add(request);
                createdbyIdNew = em.merge(createdbyIdNew);
            }
            if (managerIdOld != null && !managerIdOld.equals(managerIdNew)) {
                managerIdOld.getRequestCollection().remove(request);
                managerIdOld = em.merge(managerIdOld);
            }
            if (managerIdNew != null && !managerIdNew.equals(managerIdOld)) {
                managerIdNew.getRequestCollection().add(request);
                managerIdNew = em.merge(managerIdNew);
            }
            if (assignedIdOld != null && !assignedIdOld.equals(assignedIdNew)) {
                assignedIdOld.getRequestCollection().remove(request);
                assignedIdOld = em.merge(assignedIdOld);
            }
            if (assignedIdNew != null && !assignedIdNew.equals(assignedIdOld)) {
                assignedIdNew.getRequestCollection().add(request);
                assignedIdNew = em.merge(assignedIdNew);
            }
            for (Watcher watcherCollectionNewWatcher : watcherCollectionNew) {
                if (!watcherCollectionOld.contains(watcherCollectionNewWatcher)) {
                    Request oldReqIdOfWatcherCollectionNewWatcher = watcherCollectionNewWatcher.getReqId();
                    watcherCollectionNewWatcher.setReqId(request);
                    watcherCollectionNewWatcher = em.merge(watcherCollectionNewWatcher);
                    if (oldReqIdOfWatcherCollectionNewWatcher != null && !oldReqIdOfWatcherCollectionNewWatcher.equals(request)) {
                        oldReqIdOfWatcherCollectionNewWatcher.getWatcherCollection().remove(watcherCollectionNewWatcher);
                        oldReqIdOfWatcherCollectionNewWatcher = em.merge(oldReqIdOfWatcherCollectionNewWatcher);
                    }
                }
            }
            for (LabelRequest labelRequestCollectionNewLabelRequest : labelRequestCollectionNew) {
                if (!labelRequestCollectionOld.contains(labelRequestCollectionNewLabelRequest)) {
                    Request oldRequestIdOfLabelRequestCollectionNewLabelRequest = labelRequestCollectionNewLabelRequest.getRequestId();
                    labelRequestCollectionNewLabelRequest.setRequestId(request);
                    labelRequestCollectionNewLabelRequest = em.merge(labelRequestCollectionNewLabelRequest);
                    if (oldRequestIdOfLabelRequestCollectionNewLabelRequest != null && !oldRequestIdOfLabelRequestCollectionNewLabelRequest.equals(request)) {
                        oldRequestIdOfLabelRequestCollectionNewLabelRequest.getLabelRequestCollection().remove(labelRequestCollectionNewLabelRequest);
                        oldRequestIdOfLabelRequestCollectionNewLabelRequest = em.merge(oldRequestIdOfLabelRequestCollectionNewLabelRequest);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = request.getId();
                if (findRequest(id) == null) {
                    throw new NonexistentEntityException("The request with id " + id + " no longer exists.");
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
            Request request;
            try {
                request = em.getReference(Request.class, id);
                request.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The request with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Watcher> watcherCollectionOrphanCheck = request.getWatcherCollection();
            for (Watcher watcherCollectionOrphanCheckWatcher : watcherCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Request (" + request + ") cannot be destroyed since the Watcher " + watcherCollectionOrphanCheckWatcher + " in its watcherCollection field has a non-nullable reqId field.");
            }
            Collection<LabelRequest> labelRequestCollectionOrphanCheck = request.getLabelRequestCollection();
            for (LabelRequest labelRequestCollectionOrphanCheckLabelRequest : labelRequestCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Request (" + request + ") cannot be destroyed since the LabelRequest " + labelRequestCollectionOrphanCheckLabelRequest + " in its labelRequestCollection field has a non-nullable requestId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Department departmentsId = request.getDepartmentsId();
            if (departmentsId != null) {
                departmentsId.getRequestCollection().remove(request);
                departmentsId = em.merge(departmentsId);
            }
            User createdbyId = request.getCreatedbyId();
            if (createdbyId != null) {
                createdbyId.getRequestCollection().remove(request);
                createdbyId = em.merge(createdbyId);
            }
            User managerId = request.getManagerId();
            if (managerId != null) {
                managerId.getRequestCollection().remove(request);
                managerId = em.merge(managerId);
            }
            User assignedId = request.getAssignedId();
            if (assignedId != null) {
                assignedId.getRequestCollection().remove(request);
                assignedId = em.merge(assignedId);
            }
            em.remove(request);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Request> findRequestEntities() {
        return findRequestEntities(true, -1, -1);
    }

    public List<Request> findRequestEntities(int maxResults, int firstResult) {
        return findRequestEntities(false, maxResults, firstResult);
    }

    private List<Request> findRequestEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Request.class));
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

    public Request findRequest(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Request.class, id);
        } finally {
            em.close();
        }
    }

    public int getRequestCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Request> rt = cq.from(Request.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
