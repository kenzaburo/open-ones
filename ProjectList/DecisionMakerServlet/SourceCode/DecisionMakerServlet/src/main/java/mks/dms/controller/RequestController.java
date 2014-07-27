package mks.dms.controller;

import java.util.List;

import mks.dms.dao.entity.RequestType;
import mks.dms.service.MasterService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ThachLe
 *
 */
@Controller
public class RequestController {
	/**  */
	private static final Logger LOG = Logger.getLogger(RequestController.class);
	
	private final MasterService masterService;
	
    @Autowired
    public RequestController(MasterService masterService) {
        this.masterService = masterService;
    }
    
    @RequestMapping(value="createRequest" , method = RequestMethod.GET)
    public ModelAndView createTask(Model model){
        ModelAndView mav = new ModelAndView("createRequest");
        
        List<RequestType> lstRequestTypes = masterService.getRequestTypes();
        LOG.debug("lstRequestTypes=" + lstRequestTypes);
        mav.addObject("lstReqTypes", lstRequestTypes);
        
    	return mav;
    }
}
