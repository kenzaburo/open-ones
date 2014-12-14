package mks.dms.dao.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import mks.dms.util.AppCons;
import mks.dms.util.AppCons.RESULT;
import mks.dms.util.SaveBatchException;

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

    public List<RESULT> save(List<Parameter> lstParameter, String username) throws SaveBatchException {
        List<RESULT> lstResult = new ArrayList<AppCons.RESULT>();
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            Parameter parameter;

            Parameter currentParameter;
            AppCons.RESULT result;
            for (Iterator<Parameter> itParameter = lstParameter.iterator(); itParameter.hasNext(); ) {
                parameter = itParameter.next();

                currentParameter = findParameter(parameter.getId());
                
                if (currentParameter == null) {
                    // Create
                    em.persist(parameter);
                    result = AppCons.RESULT.CREATE_OK;
                } else {
                    // Update
                    // Name
                    currentParameter.setCd(parameter.getCd());
                    currentParameter.setName(parameter.getName());
                    currentParameter.setValue(parameter.getValue());
                    currentParameter.setDescription(parameter.getDescription());

                    currentParameter.setLastmodified(new Date());
                    currentParameter.setLastmodifiedbyUsername(username);

                    em.merge(currentParameter);
                    result = AppCons.RESULT.UPDATE_OK;
                }
                
                lstResult.add(result);
            }            
            
            em.getTransaction().commit();
        } catch (Throwable th) {
            em.getTransaction().rollback();
            
            throw new SaveBatchException(th);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return lstResult;
    }
}
