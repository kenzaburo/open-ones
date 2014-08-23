package mks.dms.controller;

import java.security.Principal;
import java.util.List;

import mks.dms.dao.entity.RequestType;
import mks.dms.dao.entity.User;
import mks.dms.model.DurationUnit;
import mks.dms.service.MasterService;
import mks.dms.service.UserControllerService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({"lstReqTypes","listUsers", "listDurationUnits"})
public class HomeController {
    /** Logging. */
    private final static Logger LOG = Logger.getLogger(HomeController.class);
    
    private final String ADMIN_USER = "admin";
	/**
	 * Simply selects the home view to render by returning its name.
	 */
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String homeDefault(Model model) {
//		return "home";
//	}
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView root(Model model, Principal principal) {
        return home(model, principal);
    }
    
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(Model model, Principal principal) {
	    ModelAndView mav;
	    LOG.debug("User=" + principal.getName());
	    
	    if (ADMIN_USER.equalsIgnoreCase(principal.getName())) {
	        mav = new ModelAndView("home-admin");
	    } else {
	        mav = new ModelAndView("home");
	    }
	    
	    shareCommonDataSession(mav);
	    
	    return mav;
	}
	
	
	
	/**
	* Put all common data into the session.
	* <br/>
	* lstRequestTypes: List of request types
	* listUsers: List of Users
	* listDurationUnits: List of Duration Units
	* @param mav
	*/
	private void shareCommonDataSession(ModelAndView mav) {
	    MasterService masterService = new MasterService();
        List<RequestType> lstRequestTypes = masterService.getRequestTypes();
        LOG.debug("lstRequestTypes=" + lstRequestTypes);
        mav.addObject("lstReqTypes", lstRequestTypes);
        
        UserControllerService userService = new UserControllerService(); 
        List<User> listUsers = userService.getAllUser();
        mav.addObject("listUsers", listUsers);
        
        List<DurationUnit> listDurationUnits = MasterService.getDurationUnits();
        mav.addObject("listDurationUnits", listDurationUnits);
	}
}
