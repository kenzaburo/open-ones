package mks.dm.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ThachLe
 *
 */
@Controller
public class RequestController {
	/**  */
	private static final Logger LOG = Logger.getLogger(RequestController.class);
	
    @RequestMapping(value="createRequest" , method = RequestMethod.GET)
    public String createTask(Model model){
    	
    	return "createRequest";
    }
}
