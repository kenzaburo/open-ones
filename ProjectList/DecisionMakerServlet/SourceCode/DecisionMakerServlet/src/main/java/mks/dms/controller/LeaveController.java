package mks.dms.controller;

import java.security.Principal;
import java.util.List;

import mks.dms.dao.entity.Request;
import mks.dms.service.RequestService;

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
public class LeaveController {
	/**  */
	private static final Logger LOG = Logger.getLogger(LeaveController.class);
	
	private final RequestService requestService;
	
	@Autowired
    public LeaveController(RequestService requestService) {
        this.requestService = requestService;
    }


    @RequestMapping(value="listLeave" , method = RequestMethod.GET)
    public ModelAndView listLeave(Model model, Principal principal) {
        ModelAndView mav = new ModelAndView("listLeave");

        List<Request> lstLeave = requestService.getListLeave(principal.getName());

        mav.addObject("lstLeave", lstLeave);
        mav.addObject("current", "listLeave");
        return mav;
    }
    
}
