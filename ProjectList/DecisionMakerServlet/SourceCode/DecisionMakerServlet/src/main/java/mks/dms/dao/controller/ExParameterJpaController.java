package mks.dms.dao.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import mks.dms.dao.entity.Parameter;
import mks.dms.dao.entity.Request;

import org.apache.log4j.Logger;

public class ExParameterJpaController extends ParameterJpaController {
    /** Logging. */
    private final static Logger LOG = Logger.getLogger(ExParameterJpaController.class);
    
	public ExParameterJpaController(EntityManagerFactory emf) {
		super(emf);
	}
	
	public List<Parameter> findParameterByCd(String cd) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
        Root<Parameter> rt = cq.from(Parameter.class);
        cq.where(cb.equal(rt.get("cd"), cd));
        Query query = em.createQuery(cq);
        List<Parameter> result = query.getResultList();
        return result;
	}

    public String findParameterByName(String cd, String name, boolean isEnable) {
        List<Request> lstRequest;
        EntityManager em = getEntityManager();

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();

            Root<Parameter> root = cq.from(Parameter.class);
            cq.select(root.get("value"));
            
            // Build where condition
            Predicate predicate = cb.equal(root.get("cd"), cd);
            predicate = cb.and(predicate, cb.equal(root.get("name"), name));
            predicate = cb.and(predicate, cb.equal(root.get("enabled"), isEnable));


            cq.where(predicate);

            Query query = em.createQuery(cq);

            return (String) query.getSingleResult();

        } catch (NoResultException nrEx) {
            LOG.warn("Could not found parameter cd=" + cd + ";name=" + name + ";enabled=" + isEnable, nrEx);
        } finally {
            em.close();
        }

        return null;
    }

    public Parameter findParameterByDescription(String cd, String name, String value, String description) {
        List<Request> lstRequest;
        EntityManager em = getEntityManager();

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();

            Root<Parameter> root = cq.from(Parameter.class);
            
            // Build where condition
            Predicate predicate = cb.equal(root.get("cd"), cd);
            predicate = cb.and(predicate, cb.equal(root.get("name"), name));
            predicate = cb.and(predicate, cb.equal(root.get("value"), value));
            predicate = cb.and(predicate, cb.equal(root.get("description"), description));
            predicate = cb.and(predicate, cb.equal(root.get("enabled"), true));


            cq.where(predicate);

            Query query = em.createQuery(cq);

            return (Parameter) query.getSingleResult();

        } catch (NoResultException nrEx) {
            LOG.warn("Could not found parameter cd=" + cd + ";name=" + name + ";value=" + value + ";description=" + description, nrEx);
        } finally {
            em.close();
        }

        return null;
    }

}
