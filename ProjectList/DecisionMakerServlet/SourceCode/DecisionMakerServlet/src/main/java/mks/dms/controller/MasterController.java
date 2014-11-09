package mks.dms.controller;

import java.util.List;

import mks.dms.dao.entity.Department;
import mks.dms.model.MasterDepartmentRequest;
import mks.dms.model.datatable.DepartmentModel;
import mks.dms.service.MasterService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import rocky.common.CHARA;
import rocky.common.CommonUtil;

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
        
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveMasterDepartment")
    @ResponseBody
    public String processSaveDepartment(@RequestBody MasterDepartmentRequest request) {
        List<Object[]> data = request.getData();
        String parentDepartment = request.getParentDepartment();

        boolean createOK = masterService.createDepartment(parentDepartment, data);
        
        LOG.debug("parentDepartment=" + parentDepartment);
        
        String jsonRefreshDepartment = MasterService.getJsonDepartments();
        LOG.debug("jsonRefreshDepartment=" + jsonRefreshDepartment);

        return jsonRefreshDepartment;
    }

    
    @RequestMapping(value = "master.department.getNodeRoot", method = RequestMethod.GET)
    public @ResponseBody String getRootDepartment() {
        String jsonData = masterService.getRootDepartmentJson();
        
        return jsonData;
    }
    
    @RequestMapping(value = "master.department.getNodeChildren", method = RequestMethod.GET)
    public @ResponseBody String returnNode(@RequestParam("parentId") String parentDepartmentId) {
        String jsonData = masterService.getDepartmentJson(parentDepartmentId);
        
        return jsonData;
    }
}
