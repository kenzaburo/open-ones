package mks.dms.controller;

import java.util.List;

import mks.dms.model.DepartmentModel;
import mks.dms.model.MasterDepartmentRequest;
import mks.dms.model.MasterDepartmentResult;
import mks.dms.service.MasterService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ThachLe
 */
@Controller
public class MasterController {
    /**  */
    private static final Logger LOG = Logger.getLogger(MasterController.class);
    
    private final MasterService masterService;

    @Autowired
    public MasterController(MasterService masterService) {
        this.masterService = masterService;
    }
    
    @RequestMapping(value = "master.department", method = RequestMethod.GET)
    public ModelAndView masterDepartment(Model model) {
        DepartmentModel departModel = new DepartmentModel(); 
        ModelAndView mav = new ModelAndView("master.department", "command", departModel);

        String jsonData = masterService.getMasters();
        mav.addObject("jsonData", jsonData);
        
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveMasterDepartment")
    @ResponseBody
    public MasterDepartmentResult process(@RequestBody MasterDepartmentRequest request) {
        List<Object[]> data = request.getData();
        String parentDepartment = request.getParentDepartment();

        MasterDepartmentResult result = new MasterDepartmentResult(parentDepartment, data);
        LOG.debug("parentDepartment=" + parentDepartment);
        LOG.debug("data=" + data);

        return result;
    }

}
