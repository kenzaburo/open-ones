package mks.dms.controller;

import java.security.Principal;
import java.util.List;

import mks.dms.dao.controller.ExRequestTypeJpaController;
import mks.dms.dao.entity.RequestType;
import mks.dms.service.BaseService;
import mks.dms.service.SystemService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ThachLe
 */
@Controller
public class SystemController {
    /**  */
    private static final Logger LOG = Logger.getLogger(SystemController.class);

    private final SystemService systemService;

    @Autowired
    public SystemController(SystemService requestService) {
        this.systemService = requestService;
    }

    @RequestMapping(value = "init-data", method = RequestMethod.GET)
    public ModelAndView listAnnouncement(Model model, Principal principal) {
        ModelAndView mav = new ModelAndView("init-data");

        boolean initResult = systemService.initData(principal.getName());

        mav.addObject("result", initResult);

        ExRequestTypeJpaController reqTypeDaoCtrl = new ExRequestTypeJpaController(BaseService.getEmf());
        List<RequestType> lstRequestType = reqTypeDaoCtrl.findRequestTypeEntities();
        mav.addObject("lstRequestType", lstRequestType);

        return mav;
    }
}
