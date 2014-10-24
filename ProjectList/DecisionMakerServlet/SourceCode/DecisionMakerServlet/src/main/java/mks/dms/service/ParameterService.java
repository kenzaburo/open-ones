package mks.dms.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import mks.dms.dao.controller.ExParameterJpaController;
import mks.dms.dao.entity.Parameter;
import mks.dms.util.AppCons;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import rocky.common.CommonUtil;

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
		List<Parameter> listParameter = controller.findParameterByCd(cd);
		return listParameter;
	}

    /**
    * [Give the description for method].
    * @param dateKey
    * @param randomKey
    * @return
    */
    public boolean checkKey(String email, String dateKey, String randomKey) {
        //Date dteVal = CommonUtil.parse(dateKey,  AppCons.DATETIME_FORMAT);
        Parameter parameter = controller.findParameterByDescription(AppCons.RESET_PASSWORD, email, dateKey, randomKey);
        
        
        return (parameter != null);
    }
}
