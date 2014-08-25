/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mks.dms.dao.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import mks.dms.dao.entity.Request;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mks.dms.dao.controller.exceptions.NonexistentEntityException;
import mks.dms.dao.entity.Role;
import mks.dms.dao.entity.User;
import mks.dms.dao.entity.Watcher;

/**
 *
 * @author ThachLe
 */
public class UserJpaController implements Serializable {

    public UserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(User user) {
        if (user.getRequestCollection() == null) {
            user.setRequestCollection(new ArrayList<Request>());
        }
        if (user.getRequestCollection1() == null) {
            user.setRequestCollection1(new ArrayList<Request>());
        }
        if (user.getRequestCollection2() == null) {
            user.setRequestCollection2(new ArrayList<Request>());
        }
        if (user.getRoleCollection() == null) {
            user.setRoleCollection(new ArrayList<Role>());
        }
        if (user.getWatcherCollection() == null) {
            user.setWatcherCollection(new ArrayList<Watcher>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Request> attachedRequestCollection = new ArrayList<Request>();
            for (Request requestCollectionRequestToAttach : user.getRequestCollection()) {
                requestCollectionRequestToAttach = em.getReference(requestCollectionRequestToAttach.getClass(), requestCollectionRequestToAttach.getId());
                attachedRequestCollection.add(requestCollectionRequestToAttach);
            }
            user.setRequestCollection(attachedRequestCollection);
            Collection<Request> attachedRequestCollection1 = new ArrayList<Request>();
            for (Request requestCollection1RequestToAttach : user.getRequestCollection1()) {
                requestCollection1RequestToAttach = em.getReference(requestCollection1RequestToAttach.getClass(), requestCollection1RequestToAttach.getId());
                attachedRequestCollection1.add(requestCollection1RequestToAttach);
            }
            user.setRequestCollection1(attachedRequestCollection1);
            Collection<Request> attachedRequestCollection2 = new ArrayList<Request>();
            for (Request requestCollection2RequestToAttach : user.getRequestCollection2()) {
                requestCollection2RequestToAttach = em.getReference(requestCollection2RequestToAttach.getClass(), requestCollection2RequestToAttach.getId());
                attachedRequestCollection2.add(requestCollection2RequestToAttach);
            }
            user.setRequestCollection2(attachedRequestCollection2);
            Collection<Role> attachedRoleCollection = new ArrayList<Role>();
            for (Role roleCollectionRoleToAttach : user.getRoleCollection()) {
                roleCollectionRoleToAttach = em.getReference(roleCollectionRoleToAttach.getClass(), roleCollectionRoleToAttach.getId());
                attachedRoleCollection.add(roleCollectionRoleToAttach);
            }
            user.setRoleCollection(attachedRoleCollection);
            Collection<Watcher> attachedWatcherCollection = new ArrayList<Watcher>();
            for (Watcher watcherCollectionWatcherToAttach : user.getWatcherCollection()) {
                watcherCollectionWatcherToAttach = em.getReference(watcherCollectionWatcherToAttach.getClass(), watcherCollectionWatcherToAttach.getId());
                attachedWatcherCollection.add(watcherCollectionWatcherToAttach);
            }
            user.setWatcherCollection(attachedWatcherCollection);
            em.persist(user);
            for (Request requestCollectionRequest : user.getRequestCollection()) {
                User oldCreatedbyIdOfRequestCollectionRequest = requestCollectionRequest.getCreatedbyId();
                requestCollectionRequest.setCreatedbyId(user);
                requestCollectionRequest = em.merge(requestCollectionRequest);
                if (oldCreatedbyIdOfRequestCollectionRequest != null) {
                    oldCreatedbyIdOfRequestCollectionRequest.getRequestCollection().remove(requestCollectionRequest);
                    oldCreatedbyIdOfRequestCollectionRequest = em.merge(oldCreatedbyIdOfRequestCollectionRequest);
                }
            }
            for (Request requestCollection1Request : user.getRequestCollection1()) {
                User oldManagerIdOfRequestCollection1Request = requestCollection1Request.getManagerId();
                requestCollection1Request.setManagerId(user);
                requestCollection1Request = em.merge(requestCollection1Request);
                if (oldManagerIdOfRequestCollection1Request != null) {
                    oldManagerIdOfRequestCollection1Request.getRequestCollection1().remove(requestCollection1Request);
                    oldManagerIdOfRequestCollection1Request = em.merge(oldManagerIdOfRequestCollection1Request);
                }
            }
            for (Request requestCollection2Request : user.getRequestCollection2()) {
                User oldAssignedIdOfRequestCollection2Request = requestCollection2Request.getAssignedId();
                requestCollection2Request.setAssignedId(user);
                requestCollection2Request = em.merge(requestCollection2Request);
                if (oldAssignedIdOfRequestCollection2Request != null) {
                    oldAssignedIdOfRequestCollection2Request.getRequestCollection2().remove(requestCollection2Request);
                    oldAssignedIdOfRequestCollection2Request = em.merge(oldAssignedIdOfRequestCollection2Request);
                }
            }
            for (Role roleCollectionRole : user.getRoleCollection()) {
                User oldUserIdOfRoleCollectionRole = roleCollectionRole.getUserId();
                roleCollectionRole.setUserId(user);
                roleCollectionRole = em.merge(roleCollectionRole);
                if (oldUserIdOfRoleCollectionRole != null) {
                    oldUserIdOfRoleCollectionRole.getRoleCollection().remove(roleCollectionRole);
                    oldUserIdOfRoleCollectionRole = em.merge(oldUserIdOfRoleCollectionRole);
                }
            }
            for (Watcher watcherCollectionWatcher : user.getWatcherCollection()) {
                User oldUserIdOfWatcherCollectionWatcher = watcherCollectionWatcher.getUserId();
                watcherCollectionWatcher.setUserId(user);
                watcherCollectionWatcher = em.merge(watcherCollectionWatcher);
                if (oldUserIdOfWatcherCollectionWatcher != null) {
                    oldUserIdOfWatcherCollectionWatcher.getWatcherCollection().remove(watcherCollectionWatcher);
                    oldUserIdOfWatcherCollectionWatcher = em.merge(oldUserIdOfWatcherCollectionWatcher);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User persistentUser = em.find(User.class, user.getId());
            Collection<Request> requestCollectionOld = persistentUser.getRequestCollection();
            Collection<Request> requestCollectionNew = user.getRequestCollection();
            Collection<Request> requestCollection1Old = persistentUser.getRequestCollection1();
            Collection<Request> requestCollection1New = user.getRequestCollection1();
            Collection<Request> requestCollection2Old = persistentUser.getRequestCollection2();
            Collection<Request> requestCollection2New = user.getRequestCollection2();
            Collection<Role> roleCollectionOld = persistentUser.getRoleCollection();
            Collection<Role> roleCollectionNew = user.getRoleCollection();
            Collection<Watcher> watcherCollectionOld = persistentUser.getWatcherCollection();
            Collection<Watcher> watcherCollectionNew = user.getWatcherCollection();
            Collection<Request> attachedRequestCollectionNew = new ArrayList<Request>();
            for (Request requestCollectionNewRequestToAttach : requestCollectionNew) {
                requestCollectionNewRequestToAttach = em.getReference(requestCollectionNewRequestToAttach.getClass(), requestCollectionNewRequestToAttach.getId());
                attachedRequestCollectionNew.add(requestCollectionNewRequestToAttach);
            }
            requestCollectionNew = attachedRequestCollectionNew;
            user.setRequestCollection(requestCollectionNew);
            Collection<Request> attachedRequestCollection1New = new ArrayList<Request>();
            for (Request requestCollection1NewRequestToAttach : requestCollection1New) {
                requestCollection1NewRequestToAttach = em.getReference(requestCollection1NewRequestToAttach.getClass(), requestCollection1NewRequestToAttach.getId());
                attachedRequestCollection1New.add(requestCollection1NewRequestToAttach);
            }
            requestCollection1New = attachedRequestCollection1New;
            user.setRequestCollection1(requestCollection1New);
            Collection<Request> attachedRequestCollection2New = new ArrayList<Request>();
            for (Request requestCollection2NewRequestToAttach : requestCollection2New) {
                requestCollection2NewRequestToAttach = em.getReference(requestCollection2NewRequestToAttach.getClass(), requestCollection2NewRequestToAttach.getId());
                attachedRequestCollection2New.add(requestCollection2NewRequestToAttach);
            }
            requestCollection2New = attachedRequestCollection2New;
            user.setRequestCollection2(requestCollection2New);
            Collection<Role> attachedRoleCollectionNew = new ArrayList<Role>();
            for (Role roleCollectionNewRoleToAttach : roleCollectionNew) {
                roleCollectionNewRoleToAttach = em.getReference(roleCollectionNewRoleToAttach.getClass(), roleCollectionNewRoleToAttach.getId());
                attachedRoleCollectionNew.add(roleCollectionNewRoleToAttach);
            }
            roleCollectionNew = attachedRoleCollectionNew;
            user.setRoleCollection(roleCollectionNew);
            Collection<Watcher> attachedWatcherCollectionNew = new ArrayList<Watcher>();
            for (Watcher watcherCollectionNewWatcherToAttach : watcherCollectionNew) {
                watcherCollectionNewWatcherToAttach = em.getReference(watcherCollectionNewWatcherToAttach.getClass(), watcherCollectionNewWatcherToAttach.getId());
                attachedWatcherCollectionNew.add(watcherCollectionNewWatcherToAttach);
            }
            watcherCollectionNew = attachedWatcherCollectionNew;
            user.setWatcherCollection(watcherCollectionNew);
            user = em.merge(user);
            for (Request requestCollectionOldRequest : requestCollectionOld) {
                if (!requestCollectionNew.contains(requestCollectionOldRequest)) {
                    requestCollectionOldRequest.setCreatedbyId(null);
                    requestCollectionOldRequest = em.merge(requestCollectionOldRequest);
                }
            }
            for (Request requestCollectionNewRequest : requestCollectionNew) {
                if (!requestCollectionOld.contains(requestCollectionNewRequest)) {
                    User oldCreatedbyIdOfRequestCollectionNewRequest = requestCollectionNewRequest.getCreatedbyId();
                    requestCollectionNewRequest.setCreatedbyId(user);
                    requestCollectionNewRequest = em.merge(requestCollectionNewRequest);
                    if (oldCreatedbyIdOfRequestCollectionNewRequest != null && !oldCreatedbyIdOfRequestCollectionNewRequest.equals(user)) {
                        oldCreatedbyIdOfRequestCollectionNewRequest.getRequestCollection().remove(requestCollectionNewRequest);
                        oldCreatedbyIdOfRequestCollectionNewRequest = em.merge(oldCreatedbyIdOfRequestCollectionNewRequest);
                    }
                }
            }
            for (Request requestCollection1OldRequest : requestCollection1Old) {
                if (!requestCollection1New.contains(requestCollection1OldRequest)) {
                    requestCollection1OldRequest.setManagerId(null);
                    requestCollection1OldRequest = em.merge(requestCollection1OldRequest);
                }
            }
            for (Request requestCollection1NewRequest : requestCollection1New) {
                if (!requestCollection1Old.contains(requestCollection1NewRequest)) {
                    User oldManagerIdOfRequestCollection1NewRequest = requestCollection1NewRequest.getManagerId();
                    requestCollection1NewRequest.setManagerId(user);
                    requestCollection1NewRequest = em.merge(requestCollection1NewRequest);
                    if (oldManagerIdOfRequestCollection1NewRequest != null && !oldManagerIdOfRequestCollection1NewRequest.equals(user)) {
                        oldManagerIdOfRequestCollection1NewRequest.getRequestCollection1().remove(requestCollection1NewRequest);
                        oldManagerIdOfRequestCollection1NewRequest = em.merge(oldManagerIdOfRequestCollection1NewRequest);
                    }
                }
            }
            for (Request requestCollection2OldRequest : requestCollection2Old) {
                if (!requestCollection2New.contains(requestCollection2OldRequest)) {
                    requestCollection2OldRequest.setAssignedId(null);
                    requestCollection2OldRequest = em.merge(requestCollection2OldRequest);
                }
            }
            for (Request requestCollection2NewRequest : requestCollection2New) {
                if (!requestCollection2Old.contains(requestCollection2NewRequest)) {
                    User oldAssignedIdOfRequestCollection2NewRequest = requestCollection2NewRequest.getAssignedId();
                    requestCollection2NewRequest.setAssignedId(user);
                    requestCollection2NewRequest = em.merge(requestCollection2NewRequest);
                    if (oldAssignedIdOfRequestCollection2NewRequest != null && !oldAssignedIdOfRequestCollection2NewRequest.equals(user)) {
                        oldAssignedIdOfRequestCollection2NewRequest.getRequestCollection2().remove(requestCollection2NewRequest);
                        oldAssignedIdOfRequestCollection2NewRequest = em.merge(oldAssignedIdOfRequestCollection2NewRequest);
                    }
                }
            }
            for (Role roleCollectionOldRole : roleCollectionOld) {
                if (!roleCollectionNew.contains(roleCollectionOldRole)) {
                    roleCollectionOldRole.setUserId(null);
                    roleCollectionOldRole = em.merge(roleCollectionOldRole);
                }
            }
            for (Role roleCollectionNewRole : roleCollectionNew) {
                if (!roleCollectionOld.contains(roleCollectionNewRole)) {
                    User oldUserIdOfRoleCollectionNewRole = roleCollectionNewRole.getUserId();
                    roleCollectionNewRole.setUserId(user);
                    roleCollectionNewRole = em.merge(roleCollectionNewRole);
                    if (oldUserIdOfRoleCollectionNewRole != null && !oldUserIdOfRoleCollectionNewRole.equals(user)) {
                        oldUserIdOfRoleCollectionNewRole.getRoleCollection().remove(roleCollectionNewRole);
                        oldUserIdOfRoleCollectionNewRole = em.merge(oldUserIdOfRoleCollectionNewRole);
                    }
                }
            }
            for (Watcher watcherCollectionOldWatcher : watcherCollectionOld) {
                if (!watcherCollectionNew.contains(watcherCollectionOldWatcher)) {
                    watcherCollectionOldWatcher.setUserId(null);
                    watcherCollectionOldWatcher = em.merge(watcherCollectionOldWatcher);
                }
            }
            for (Watcher watcherCollectionNewWatcher : watcherCollectionNew) {
                if (!watcherCollectionOld.contains(watcherCollectionNewWatcher)) {
                    User oldUserIdOfWatcherCollectionNewWatcher = watcherCollectionNewWatcher.getUserId();
                    watcherCollectionNewWatcher.setUserId(user);
                    watcherCollectionNewWatcher = em.merge(watcherCollectionNewWatcher);
                    if (oldUserIdOfWatcherCollectionNewWatcher != null && !oldUserIdOfWatcherCollectionNewWatcher.equals(user)) {
                        oldUserIdOfWatcherCollectionNewWatcher.getWatcherCollection().remove(watcherCollectionNewWatcher);
                        oldUserIdOfWatcherCollectionNewWatcher = em.merge(oldUserIdOfWatcherCollectionNewWatcher);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = user.getId();
                if (findUser(id) == null) {
                    throw new NonexistentEntityException("The user with id " + id + " no longer exists.");
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
            User user;
            try {
                user = em.getReference(User.class, id);
                user.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The user with id " + id + " no longer exists.", enfe);
            }
            Collection<Request> requestCollection = user.getRequestCollection();
            for (Request requestCollectionRequest : requestCollection) {
                requestCollectionRequest.setCreatedbyId(null);
                requestCollectionRequest = em.merge(requestCollectionRequest);
            }
            Collection<Request> requestCollection1 = user.getRequestCollection1();
            for (Request requestCollection1Request : requestCollection1) {
                requestCollection1Request.setManagerId(null);
                requestCollection1Request = em.merge(requestCollection1Request);
            }
            Collection<Request> requestCollection2 = user.getRequestCollection2();
            for (Request requestCollection2Request : requestCollection2) {
                requestCollection2Request.setAssignedId(null);
                requestCollection2Request = em.merge(requestCollection2Request);
            }
            Collection<Role> roleCollection = user.getRoleCollection();
            for (Role roleCollectionRole : roleCollection) {
                roleCollectionRole.setUserId(null);
                roleCollectionRole = em.merge(roleCollectionRole);
            }
            Collection<Watcher> watcherCollection = user.getWatcherCollection();
            for (Watcher watcherCollectionWatcher : watcherCollection) {
                watcherCollectionWatcher.setUserId(null);
                watcherCollectionWatcher = em.merge(watcherCollectionWatcher);
            }
            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from User as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public User findUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from User as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
