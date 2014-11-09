package mks.dms.dao.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import mks.dms.dao.entity.RequestType;
import mks.dms.util.AppCons;
import mks.dms.util.SaveBatchException;
import mks.dms.util.AppCons.RESULT;

import org.apache.log4j.Logger;

/**
 * This class extends interfaces of RequestTypeJpaController. <br/>
 * 
 * @author truongtho88.nl
 */


public class ExRequestTypeJpaController extends RequestTypeJpaController {

	/** Logging. */
    private final static Logger LOG = Logger.getLogger(ExRequestTypeJpaController.class);
	
	public ExRequestTypeJpaController(EntityManagerFactory emf) {
		super(emf);
		// TODO Auto-generated constructor stub
	}

    /**
    * Get RequestType by requestCd.
    * @param requesttypeCd
    * @return
    * @see http://wiki.eclipse.org/EclipseLink/UserGuide/JPA/Basic_JPA_Development/Querying/JPQL
    */
    public RequestType findRequestTypeByCd(String requesttypeCd) {
        EntityManager em = getEntityManager();
        try {
            
            // Query query = em.createQuery("Select e FROM RequestType e WHERE e.cd = :requestTypeCd");
            Query query = em.createNamedQuery("RequestType.findByCd");
            query.setParameter("cd", requesttypeCd);
            RequestType reqType = (RequestType) query.getSingleResult();
            
            return reqType;
        } catch (NoResultException nsEx) {
            LOG.warn("Request '" + requesttypeCd + "' not found.", nsEx);
            return null;
        } finally {
            em.close();
        }
    }

    /**
    * [Give the description for method].
    * @param lstRequestType
    * @param username
    * @return null if the transaction is fail (rollback)
    * @throws SaveBatchException
    */
    public List<RESULT> save(List<RequestType> lstRequestType, String username) throws SaveBatchException {
        List<RESULT> lstResult = new ArrayList<AppCons.RESULT>();
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            RequestType requestType;

            RequestType currentRequestType;
            AppCons.RESULT result;
            for (Iterator<RequestType> itRequestType = lstRequestType.iterator(); itRequestType.hasNext(); ) {
                requestType = itRequestType.next();

                currentRequestType = getRequestTypeByCd(requestType.getCd(), em);
                
                if (currentRequestType == null) {
                    // Create
                    em.persist(requestType);
                    result = AppCons.RESULT.CREATE_OK;
                } else {
                    // Update
                    // Name
                    currentRequestType.setName(requestType.getName());
                    currentRequestType.setLastmodified(new Date());
                    currentRequestType.setLastmodifiedbyUsername(username);

                    em.merge(currentRequestType);
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

    private RequestType getRequestTypeByCd(String reqTypeCd, EntityManager em) {
        Query query;
        RequestType requestType = null;
        
        // Try to query the current record
        query = em.createNamedQuery("RequestType.findByCd");
        query.setParameter("cd", reqTypeCd);
        try {
            requestType = (RequestType) query.getSingleResult();
        } catch (NoResultException nsEx) {
            // Do nothing
        }
        
        return requestType;
    }
}

