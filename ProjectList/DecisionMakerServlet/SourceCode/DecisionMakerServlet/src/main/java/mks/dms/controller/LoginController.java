package mks.dms.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String homelogin(Model model, @RequestParam(value = "error" , required = false) String error,
			@RequestParam(value = "logout" , required = false) String logout) {
		if(error != null){
			model.addAttribute("error", "Wrong Username or Password!");
		}
		if(logout != null){
			model.addAttribute("success", "Logout Success!");
		}
		return "login";
	}
	
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(ModelMap model, Principal user) {
		SecurityContextHolder.clearContext();
		if (user != null) {
			model.addAttribute("error", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addAttribute("error", 
			"You do not have permission to access this page!");
		}
		return "login";
	}
}
