package mks.dms.controller;

import java.util.List;

import mks.dms.dao.entity.Department;
import mks.dms.model.DepartmentModel;
import mks.dms.model.MasterDepartmentRequest;
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
public class MasterTemplateController {
    /**  */
    private static final Logger LOG = Logger.getLogger(MasterTemplateController.class);
    
    private final MasterService masterService;

    @Autowired
    public MasterTemplateController(MasterService masterService) {
        this.masterService = masterService;
    }
    
    @RequestMapping(value = "master.template", method = RequestMethod.GET)
    public ModelAndView masterDepartment(Model model) {
        DepartmentModel departModel = new DepartmentModel(); 
        ModelAndView mav = new ModelAndView("master.department", "command", departModel);
        
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, value = "saveMasterTemplate")
    @ResponseBody
    public String processSaveDepartment(@RequestBody MasterDepartmentRequest request) {
        List<Object[]> data = request.getData();
        String parentDepartment = request.getParentDepartment();

        boolean createOK = masterService.createDepartment(parentDepartment, data);
        
        LOG.debug("parentDepartment=" + parentDepartment);
        
        String jsonRefreshDepartment = getJsonDepartments();
        LOG.debug("jsonRefreshDepartment=" + jsonRefreshDepartment);

        return jsonRefreshDepartment;
    }

    /**
    * Provide array of department.
    * @return
    */
    @RequestMapping(value = "master.template.load", method = RequestMethod.GET)
    public @ResponseBody String loadDepartment() {
        return getJsonDepartments();
    }
    
    private String getJsonDepartments() {
        DepartmentModel departModel = new DepartmentModel(); 

        List<Department> lstDepartments = masterService.getDepartments();
        
        if (!CommonUtil.isNNandNB(lstDepartments)) {
            Department department = new Department();
            department.setCd(CHARA.BLANK);
            department.setName(CHARA.BLANK);
            // Add an empty
            lstDepartments.add(department );
        }
        departModel.setDepartments(lstDepartments);

        String jsonData = departModel.getJsonDepartments();

        LOG.debug("jsonData=" + jsonData);
        
        return jsonData;
    }

}
