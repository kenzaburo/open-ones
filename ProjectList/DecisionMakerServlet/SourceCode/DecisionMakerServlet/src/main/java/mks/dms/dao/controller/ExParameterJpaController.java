package mks.dms.dao.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import mks.dms.dao.entity.Parameter;

public class ExParameterJpaController extends ParameterJpaController {

	public ExParameterJpaController(EntityManagerFactory emf) {
		super(emf);
	}
	
	public List<Parameter> getParameterByCd(String cd) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery cq = cb.createQuery();
        Root<Parameter> rt = cq.from(Parameter.class);
        cq.where(cb.equal(rt.get("cd"), cd));
        Query query = em.createQuery(cq);
        List<Parameter> result = query.getResultList();
        return result;
	}

}
