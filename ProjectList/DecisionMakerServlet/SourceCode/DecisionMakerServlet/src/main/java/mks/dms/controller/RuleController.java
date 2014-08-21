package mks.dms.controller;

import java.util.List;

import mks.dms.dao.entity.Request;
import mks.dms.service.RequestService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ThachLe
 *
 */
@Controller
public class RuleController {
	/**  */
	private static final Logger LOG = Logger.getLogger(RuleController.class);
	
	private final RequestService requestService;
	
	@Autowired
    public RuleController(RequestService requestService) {
        this.requestService = requestService;
    }


    @RequestMapping(value="listRule" , method = RequestMethod.GET)
    public ModelAndView listRule(Model model){
        ModelAndView mav = new ModelAndView("listRule");
        
        List<Request> lstRule = requestService.getListRule();
        
        mav.addObject("lstRule", lstRule);
        
        return mav;
    }
    
    @RequestMapping(value="viewRule" , method = RequestMethod.GET)
    public ModelAndView viewRule(@RequestParam("id") Integer requestId) {
        ModelAndView mav = new ModelAndView("viewRule");
        
        LOG.debug("requestId=" + requestId);
        Request rule = requestService.getDaoController().findRequest(requestId);
        
        mav.addObject("rule", rule);
        
        return mav;
    }
    
}
