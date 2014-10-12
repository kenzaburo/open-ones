package mks.dms.controller;

import java.security.Principal;
import java.util.List;

import mks.dms.dao.entity.Request;
import mks.dms.model.SearchLeaveConditionModel;
import mks.dms.service.RequestService;
import mks.dms.util.AppCons;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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


    @RequestMapping(value="myListLeave" , method = RequestMethod.GET)
    public ModelAndView showMyListLeave(@ModelAttribute(AppCons.MODEL) SearchLeaveConditionModel searchLeaveModel, Principal principal) {
        ModelAndView mav = new ModelAndView("myListLeave");

        searchLeaveModel.setUsername(principal.getName());
        List<Request> lstLeave = requestService.findLeaveOfUser(searchLeaveModel);

        mav.addObject("lstLeave", lstLeave);
        mav.addObject("current", "myListLeave");
        return mav;
    }
    
}
