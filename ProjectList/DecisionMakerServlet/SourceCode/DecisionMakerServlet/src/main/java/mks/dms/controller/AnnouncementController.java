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
public class AnnouncementController {
	/**  */
	private static final Logger LOG = Logger.getLogger(AnnouncementController.class);
	
	private final RequestService requestService;
	
	@Autowired
    public AnnouncementController(RequestService requestService) {
        this.requestService = requestService;
    }


    @RequestMapping(value="listAnnouncement" , method = RequestMethod.GET)
    public ModelAndView listAnnouncement(Model model){
        ModelAndView mav = new ModelAndView("listAnnouncement");
        
        List<Request> lstAnnouncement = requestService.getListAnnouncement();
        
        mav.addObject("lstAnnouncement", lstAnnouncement);
        
        return mav;
    }
    
    @RequestMapping(value="viewAnnouncement" , method = RequestMethod.GET)
    public ModelAndView viewAnnouncement(@RequestParam("id") Integer requestId) {
        ModelAndView mav = new ModelAndView("viewAnnouncement");
        
        LOG.debug("requestId=" + requestId);
        Request announcement = requestService.getDaoController().findRequest(requestId);
        
        mav.addObject("announcement", announcement);
        
        return mav;
    }
    
}
