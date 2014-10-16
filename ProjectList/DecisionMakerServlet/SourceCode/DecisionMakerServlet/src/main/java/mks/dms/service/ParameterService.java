package mks.dms.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import mks.dms.dao.controller.ExParameterJpaController;
import mks.dms.dao.entity.Parameter;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ParameterService extends BaseService {
	/* Logger to log information */
	private final static Logger LOG = Logger.getLogger(ParameterService.class);
	
	private final ExParameterJpaController controller;
	
	public ParameterService() {
		controller = new ExParameterJpaController(super.getEmf());
	}
	
	public ParameterService(EntityManagerFactory emf) {
		controller = new ExParameterJpaController(emf);
	}
	
	public List<Parameter> getParameterByCd(String cd) {
		List<Parameter> listParameter = controller.getParameterByCd(cd);
		return listParameter;
	}
}
