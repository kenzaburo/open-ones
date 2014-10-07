package mks.dms.controller;

import java.security.Principal;
import java.util.List;

import mks.dms.dao.entity.Department;
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
 * <br/>
 * Datum are stored in session:<br/>
 * listRequestType: Types of request
 * listUser: List of users [TODO] Change to ajax request in next version
 * listDurationUnit: List of duration unit
 */
@Controller
@SessionAttributes({"listRequestType","listUser", "listDurationUnit", "listDepartment", "listStatus"})
public class HomeController {
    /** Logging. */
    private final static Logger LOG = Logger.getLogger(HomeController.class);
    
    private final String ADMIN_USER = "admin";
    
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
	    mav.addObject("current", "home");
	    return mav;
	}
	
	/**
	* Put all common data into the session.
	* <br/>
	* lstRequestTypes: List of request types
	* listUser: List of Users
	* listDurationUnit: List of Duration Units
	* @param mav
	*/
	private void shareCommonDataSession(ModelAndView mav) {
	    MasterService masterService = new MasterService();
        List<RequestType> lstRequestTypes = masterService.getRequestTypes();
        LOG.debug("lstRequestTypes=" + lstRequestTypes);
        mav.addObject("listRequestType", lstRequestTypes);
        
        UserControllerService userService = new UserControllerService(); 
        List<User> listUser = userService.getAllUser();
        mav.addObject("listUser", listUser);
        
        List<DurationUnit> listDurationUnit = MasterService.getDurationUnits();
        mav.addObject("listDurationUnit", listDurationUnit);
        
        List<Department> listDepartment = masterService.getDepartments();
        mav.addObject("listDepartment", listDepartment);
        
        List<String> listStatus = masterService.getAllStatus();
        mav.addObject("listStatus", listStatus);
	}
}
