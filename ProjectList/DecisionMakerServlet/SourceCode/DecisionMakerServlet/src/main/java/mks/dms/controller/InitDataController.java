package mks.dms.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mks.dms.dao.controller.ExRequestTypeJpaController;
import mks.dms.dao.controller.RequestTypeJpaController;
import mks.dms.dao.entity.Department;
import mks.dms.dao.entity.RequestType;
import mks.dms.model.MasterDepartmentRequest;
import mks.dms.model.datatable.DepartmentModel;
import mks.dms.model.datatable.RequestTypeModel;
import mks.dms.service.BaseService;
import mks.dms.service.MasterService;
import mks.dms.service.SystemService;
import mks.dms.util.SaveBatchException;
import netscape.javascript.JSObject;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;

import rocky.common.CHARA;
import rocky.common.CommonUtil;

/**
 * @author ThachLe
 */
@Controller
public class InitDataController {
    /**  */
    private static final Logger LOG = Logger.getLogger(InitDataController.class);

    private final SystemService systemService;
    private final MasterService masterService;

    @Autowired
    public InitDataController(SystemService requestService, MasterService masterService) {
        this.systemService = requestService;
        this.masterService = masterService;
    }

    @RequestMapping(value = "init-data", method = RequestMethod.GET)
    public ModelAndView goInitData(Model model, Principal principal) {
        ModelAndView mav = new ModelAndView("init-data");

//        boolean initResult = systemService.initData(principal.getName());
//
//        mav.addObject("result", initResult);

        ExRequestTypeJpaController reqTypeDaoCtrl = new ExRequestTypeJpaController(BaseService.getEmf());
        List<RequestType> lstRequestType = reqTypeDaoCtrl.findRequestTypeEntities();
        mav.addObject("lstRequestType", lstRequestType);

        return mav;
    }
    
    @RequestMapping(value = "initData", method = RequestMethod.POST)
    public ModelAndView initData(Model model, Principal principal) {
        ModelAndView mav = new ModelAndView("init-data");

        boolean initResult = systemService.initData(principal.getName());

        mav.addObject("result", initResult);

        ExRequestTypeJpaController reqTypeDaoCtrl = new ExRequestTypeJpaController(BaseService.getEmf());
        List<RequestType> lstRequestType = reqTypeDaoCtrl.findRequestTypeEntities();
        mav.addObject("lstRequestType", lstRequestType);

        return mav;
    }
    
    /**
    * Provide array data of Request types.
    * <br/>
    * It provides data for handsontable
    * @see init-data.jsp
    * @return
    */
    @RequestMapping(value = "load-request-type", method = RequestMethod.GET)
    public @ResponseBody String loadRequestTypes() {
        return MasterService.getJsonRequestTypes();
    }

//    @RequestMapping(value = "saveAllRequestType", method = RequestMethod.GET)
//    @ResponseBody
//    public String saveAllRequestTypeGET(@RequestBody RequestTypeModel requestTypeModel, Principal principal) throws JSONException {
//        return saveAllRequestType(requestTypeModel, principal);
//    }
    
    @RequestMapping(value = "saveAllRequestType", method = RequestMethod.POST)
    @ResponseBody
    public String saveAllRequestType(@RequestBody RequestTypeModel requestTypeModel, Principal principal) throws JSONException {
    	JsonObject jo = new JsonObject();
        JSONObject jso = new JSONObject();
        try {
            requestTypeModel = masterService.saveAllRequestTyle(requestTypeModel, principal.getName());
            String jsonRefreshDepartment = requestTypeModel.getJsonData();
            LOG.debug("jsonRefreshDepartment=" + jsonRefreshDepartment);
            
//            jo.addProperty("success", "true");
            jso.put("success", "true");
//            return jso.toString();
        } catch (SaveBatchException ex) {
            LOG.error("Could not save the request types", ex);
//            jo.addProperty("error", "true");
//            jo.addProperty("data", ex.getMessage());
            jso.put("error", "true");
            jso.put("data", ex.getMessage());
        }
        System.out.println("Data : " + jso.toString());
        return jso.toString();
    }
    
    @RequestMapping(value = "testAjax", method = RequestMethod.POST)
    @ResponseBody
    public String testAjax(HttpServletRequest request, Principal principal) throws JSONException, IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json1 = "";
	        if (br != null) {
	            json1 = br.readLine();
	        }
	    JSONObject jsonObject = new JSONObject(json1);
	    String first = (String) jsonObject.get("first");
	    String second = (String) jsonObject.get("second");
    	JSONObject json = new JSONObject();
    	json.put("result", first + second);
    	return json.toString();
    }
    
    /**
    * Provide array of department.
    * @return
    */
    @RequestMapping(value = "load-department", method = RequestMethod.GET)
    public @ResponseBody String loadDepartment() {
        return MasterService.getJsonDepartments();
    }
    
    @RequestMapping(value = "load-system-user", method = RequestMethod.GET)
    public @ResponseBody String loadSystemUser() {
        return MasterService.getJsonSystemUser();
    }
    
    @RequestMapping(value = "load-status-flow-request", method = RequestMethod.GET)
    public @ResponseBody String loadStatusFlowRequest() {
        return MasterService.getJsonStatusFlowRequest();
    }
    
    @RequestMapping(value = "load-parameter", method = RequestMethod.GET)
    public @ResponseBody String loadParameter() {
        return MasterService.getJsonParameter();
    }
}
