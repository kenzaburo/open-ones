package mks.dm.controller;

import org.apache.log4j.Logger;
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
public class TaskController {
	/**  */
	private static final Logger LOG = Logger.getLogger(TaskController.class);
	
    @RequestMapping(value="createTask" , method = RequestMethod.GET)
    public String createTask(Model model){
    	model.addAttribute("page", "createTask");
    	
    	return "createTask";
    }

    @RequestMapping(value="listTask" , method = RequestMethod.GET)
    public ModelAndView listTask(Model model){
        ModelAndView mav = new ModelAndView("listTask");
        
        model.addAttribute("page", "listTask");
        
        return mav;
    }

    @RequestMapping(value="searchTask" , method = RequestMethod.GET)
    public ModelAndView searchTask(Model model){
        ModelAndView mav = new ModelAndView("searchTask");
        
        model.addAttribute("page", "searchTask");
        
        return mav;
    }
}
