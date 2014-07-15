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
import mks.dms.dao.entity.Request;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mks.dms.dao.controller.exceptions.NonexistentEntityException;
import mks.dms.dao.entity.Department;

/**
 *
 * @author ThachLe
 */
public class DepartmentJpaController implements Serializable {

    public DepartmentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Department department) {
        if (department.getRequestCollection() == null) {
            department.setRequestCollection(new ArrayList<Request>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Request> attachedRequestCollection = new ArrayList<Request>();
            for (Request requestCollectionRequestToAttach : department.getRequestCollection()) {
                requestCollectionRequestToAttach = em.getReference(requestCollectionRequestToAttach.getClass(), requestCollectionRequestToAttach.getId());
                attachedRequestCollection.add(requestCollectionRequestToAttach);
            }
            department.setRequestCollection(attachedRequestCollection);
            em.persist(department);
            for (Request requestCollectionRequest : department.getRequestCollection()) {
                Department oldDepartmentsIdOfRequestCollectionRequest = requestCollectionRequest.getDepartmentsId();
                requestCollectionRequest.setDepartmentsId(department);
                requestCollectionRequest = em.merge(requestCollectionRequest);
                if (oldDepartmentsIdOfRequestCollectionRequest != null) {
                    oldDepartmentsIdOfRequestCollectionRequest.getRequestCollection().remove(requestCollectionRequest);
                    oldDepartmentsIdOfRequestCollectionRequest = em.merge(oldDepartmentsIdOfRequestCollectionRequest);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Department department) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Department persistentDepartment = em.find(Department.class, department.getId());
            Collection<Request> requestCollectionOld = persistentDepartment.getRequestCollection();
            Collection<Request> requestCollectionNew = department.getRequestCollection();
            Collection<Request> attachedRequestCollectionNew = new ArrayList<Request>();
            for (Request requestCollectionNewRequestToAttach : requestCollectionNew) {
                requestCollectionNewRequestToAttach = em.getReference(requestCollectionNewRequestToAttach.getClass(), requestCollectionNewRequestToAttach.getId());
                attachedRequestCollectionNew.add(requestCollectionNewRequestToAttach);
            }
            requestCollectionNew = attachedRequestCollectionNew;
            department.setRequestCollection(requestCollectionNew);
            department = em.merge(department);
            for (Request requestCollectionOldRequest : requestCollectionOld) {
                if (!requestCollectionNew.contains(requestCollectionOldRequest)) {
                    requestCollectionOldRequest.setDepartmentsId(null);
                    requestCollectionOldRequest = em.merge(requestCollectionOldRequest);
                }
            }
            for (Request requestCollectionNewRequest : requestCollectionNew) {
                if (!requestCollectionOld.contains(requestCollectionNewRequest)) {
                    Department oldDepartmentsIdOfRequestCollectionNewRequest = requestCollectionNewRequest.getDepartmentsId();
                    requestCollectionNewRequest.setDepartmentsId(department);
                    requestCollectionNewRequest = em.merge(requestCollectionNewRequest);
                    if (oldDepartmentsIdOfRequestCollectionNewRequest != null && !oldDepartmentsIdOfRequestCollectionNewRequest.equals(department)) {
                        oldDepartmentsIdOfRequestCollectionNewRequest.getRequestCollection().remove(requestCollectionNewRequest);
                        oldDepartmentsIdOfRequestCollectionNewRequest = em.merge(oldDepartmentsIdOfRequestCollectionNewRequest);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = department.getId();
                if (findDepartment(id) == null) {
                    throw new NonexistentEntityException("The department with id " + id + " no longer exists.");
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
            Department department;
            try {
                department = em.getReference(Department.class, id);
                department.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The department with id " + id + " no longer exists.", enfe);
            }
            Collection<Request> requestCollection = department.getRequestCollection();
            for (Request requestCollectionRequest : requestCollection) {
                requestCollectionRequest.setDepartmentsId(null);
                requestCollectionRequest = em.merge(requestCollectionRequest);
            }
            em.remove(department);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Department> findDepartmentEntities() {
        return findDepartmentEntities(true, -1, -1);
    }

    public List<Department> findDepartmentEntities(int maxResults, int firstResult) {
        return findDepartmentEntities(false, maxResults, firstResult);
    }

    private List<Department> findDepartmentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Department.class));
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

    public Department findDepartment(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Department.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepartmentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Department> rt = cq.from(Department.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
