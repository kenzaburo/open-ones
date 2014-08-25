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
import mks.dms.dao.entity.Template;

/**
 *
 * @author ThachLe
 */
public class TemplateJpaController implements Serializable {

    public TemplateJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Template template) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(template);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Template template) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            template = em.merge(template);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = template.getId();
                if (findTemplate(id) == null) {
                    throw new NonexistentEntityException("The template with id " + id + " no longer exists.");
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
            Template template;
            try {
                template = em.getReference(Template.class, id);
                template.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The template with id " + id + " no longer exists.", enfe);
            }
            em.remove(template);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Template> findTemplateEntities() {
        return findTemplateEntities(true, -1, -1);
    }

    public List<Template> findTemplateEntities(int maxResults, int firstResult) {
        return findTemplateEntities(false, maxResults, firstResult);
    }

    private List<Template> findTemplateEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Template as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Template findTemplate(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Template.class, id);
        } finally {
            em.close();
        }
    }

    public int getTemplateCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Template as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
