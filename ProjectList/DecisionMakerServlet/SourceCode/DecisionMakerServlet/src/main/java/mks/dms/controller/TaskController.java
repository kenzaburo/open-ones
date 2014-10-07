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
public class TaskController {
	/**  */
	private static final Logger LOG = Logger.getLogger(TaskController.class);
	
    private final RequestService requestService;

    @Autowired
    public TaskController(RequestService requestService) {
        this.requestService = requestService;
    }
	    
    @RequestMapping(value="listTask" , method = RequestMethod.GET)
    public ModelAndView listTask(Model model, Principal principal) {
        ModelAndView mav = new ModelAndView("listTask");

        List<Request> lstTask = requestService.getListTask(principal.getName());

        mav.addObject("lstTask", lstTask);
        mav.addObject("current", "listTask");
        return mav;
    }

    @RequestMapping(value="myOpenTask" , method = RequestMethod.GET)
    public ModelAndView myOpenTask(Model model){
        ModelAndView mav = new ModelAndView("myOpenTask");
        
        model.addAttribute("page", "myOpenTask");
        
        return mav;
    }
    
    @RequestMapping(value="searchTask" , method = RequestMethod.GET)
    public ModelAndView searchTask(Model model){
        ModelAndView mav = new ModelAndView("searchTask");
        
        model.addAttribute("page", "searchTask");
        
        return mav;
    }
    
    @RequestMapping(value="detailTask" , method = RequestMethod.GET)
    public ModelAndView detailTask(Model model){
        ModelAndView mav = new ModelAndView("detailTask");
        
        model.addAttribute("page", "detailTask");
        
        return mav;
    }
}
