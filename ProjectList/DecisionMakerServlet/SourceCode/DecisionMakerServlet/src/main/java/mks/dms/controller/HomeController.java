package mks.dms.controller;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
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
    public String root(Model model, Principal principal) {
        LOG.debug("User=" + principal.getName());
      
        if (ADMIN_USER.equalsIgnoreCase(principal.getName())) {
            return "home-admin";
        } else {
            return "home";
        }
    }
    
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model, Principal principal) {
	    LOG.debug("User=" + principal.getName());
	    
	    if (ADMIN_USER.equalsIgnoreCase(principal.getName())) {
	        return "home-admin";
	    } else {
	        return "home";
	    }
	}
}
