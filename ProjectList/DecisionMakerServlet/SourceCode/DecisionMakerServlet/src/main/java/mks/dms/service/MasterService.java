/**
 * Licensed to Open-Ones under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package mks.dms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import mks.dms.dao.controller.DepartmentJpaController;
import mks.dms.dao.controller.ExDepartmentJpaController;
import mks.dms.dao.controller.ExParameterJpaController;
import mks.dms.dao.controller.ExRequestTypeJpaController;
import mks.dms.dao.controller.ExStatusFlowJpaController;
import mks.dms.dao.controller.ExUserJpaController;
import mks.dms.dao.controller.ParameterJpaController;
import mks.dms.dao.controller.RequestTypeJpaController;
import mks.dms.dao.controller.StatusFlowJpaController;
import mks.dms.dao.controller.UserJpaController;
import mks.dms.dao.entity.Department;
import mks.dms.dao.entity.Parameter;
import mks.dms.dao.entity.Request;
import mks.dms.dao.entity.RequestType;
import mks.dms.dao.entity.StatusFlow;
import mks.dms.dao.entity.User;
import mks.dms.model.DurationUnit;
import mks.dms.model.MasterNode;
import mks.dms.model.datatable.DepartmentModel;
import mks.dms.model.datatable.ParameterModel;
import mks.dms.model.datatable.RequestTypeModel;
import mks.dms.model.datatable.StatusFlowModel;
import mks.dms.model.datatable.SystemUserModel;
import mks.dms.util.AppCons;
import mks.dms.util.AppUtil;
import mks.dms.util.SaveBatchException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import rocky.common.CommonUtil;

import com.google.gson.Gson;

/**
 * @author ThachLN
 *
 */
@Service
public class MasterService extends BaseService {
    /** Logger. */
    private final static Logger LOG = Logger.getLogger(MasterService.class);
    
    /* Watcher service to work with watcher */
    private WatcherService watcherService = null;
    
    /* Request service to work with request */
    private RequestService requestService = null;
    
    /* Use for record result of import data from Ldap to Database. */
    public enum ImportResult {
        Account_existed, Success, Error
    }
    
    public static List<Department> getDepartments() {
        DepartmentJpaController daoCtrl = new DepartmentJpaController(emf);
        
        List<Department> lstDepartments = daoCtrl.findDepartmentEntities();
        
        return lstDepartments;
    }
    /* Get watcher service to work with watcher */
    public WatcherService getWatcherService() {
        // check service is null
        if (watcherService == null) {
            watcherService = new WatcherService(emf);
        }
        return watcherService;
    }
    
    /* Get request service to work with service */
    public RequestService getRequestService() {
        // check service with null
        if (requestService == null) {
            requestService = new RequestService(emf);
        }
        return requestService;
    } 
    public boolean createDepartment(DepartmentModel departmentModel) {
        boolean createOK = true;
        
        if (departmentModel == null) {
            createOK = false;
        } else {
            departmentModel.getData();
            DepartmentJpaController daoCtrl = new DepartmentJpaController(emf);
            Department department = new Department();

            daoCtrl.create(department);
            
        }
        
        return createOK;
    }
    
    /**
    * Get types of request.
    * <br/>
    * @return list of RequestType
    */
    public List<RequestType> getRequestTypes() {
        List<RequestType> lstRequestTypes;
        
        RequestTypeJpaController daoCtrl = new RequestTypeJpaController(emf);
        
        lstRequestTypes = daoCtrl.findRequestTypeEntities();
        
        return lstRequestTypes;
    }
    
    

    /**
    * [Give the description for method].
    * @param request
    * @param data
    * @return
    */
    public boolean createDepartment(String parentCd, List<Object[]> data) {
        Iterator<Object[]> itRowData = data.iterator();
        
        ExDepartmentJpaController daoCtrl = new ExDepartmentJpaController(emf);

        List<Department> lstDepartment = new ArrayList<Department>();
        Department department;
        String cd;
        String name;
        String manager;
        String note;
        Object[] dataRow;

        while (itRowData.hasNext()) {
            dataRow = itRowData.next();
            
            if (AppUtil.isNotEmptyRow(dataRow)) {
                cd = (String) dataRow[0];
                name = (String) dataRow[1];
                manager = (String) dataRow[2];
                note = (String) dataRow[3];

                department = new Department();
                department.setCd(cd);
                department.setName(name);
                department.setDescription(note);
                department.setParentcd(parentCd);

                lstDepartment.add(department);
            }
        }
        
        //
        boolean createdOK = daoCtrl.recreateAll(lstDepartment);
        
        
        return createdOK;
    }
    
    /**
    * [Give the description for method].
    * @param groupDn
    * @param data
    * Structure of a row:
    * User name, Email, Last name, First name
     * @param authUsername 
    * @return Map(username, result)
    * result: Account_existed | Success | Error
    */
    public Map<String, ImportResult> importUser(String groupDn, List<Object[]> data, String authUsername) {
        Map<String, ImportResult> mapResult = new HashMap<String, ImportResult>();
        
        Iterator<Object[]> itRowData = data.iterator();
        
        ExUserJpaController userDaoCtrl = new ExUserJpaController(emf);

        User user;
        String username;
        String email;
        String lastname;
        String firstname;
        Object[] dataRow;
        ImportResult result;
        while (itRowData.hasNext()) {
            dataRow = itRowData.next();
            
            if (AppUtil.isNotEmptyRow(dataRow)) {
                username = (String) dataRow[0];
                email = (String) dataRow[1];
                lastname = (String) dataRow[2];
                firstname = (String) dataRow[3];

                user = new User();
                user.setUsername(username);
                user.setEmail(email);
                user.setLastname(lastname);
                user.setFirstname(firstname);
                
                user.setDepartmentCd(groupDn);
                user.setDepartmentName(groupDn);
                user.setEnabled(true);
                user.setCreated(new Date());
                user.setCreatedbyUsername(authUsername);
                
                // Check exist
                if (userDaoCtrl.findUserByUsername(username) != null) {
                    result = ImportResult.Account_existed;
                } else {
                    try {
                        userDaoCtrl.create(user);
                        result = ImportResult.Success;
                    } catch (Exception ex) {
                        result = ImportResult.Error;
                        LOG.error("Could not create user '" + user.getUsername() + "'", ex);
                    }
                }
                
                mapResult.put(username, result);
            }
        }
        
        return mapResult;
    }
    
    /**
     * Get Department By Cd.
     * @param cd
     * @return Department
     */
    public Department getDepartmentByCd(int cd) {
    	Department department;
    	
    	DepartmentJpaController daoCtrl = new DepartmentJpaController(emf);
    	
    	department = daoCtrl.findDepartment(cd);
    	
    	return department;
    }

    public String getRootDepartmentJson() {
        List<MasterNode> beans = new ArrayList<MasterNode>();

        MasterNode rootDepartment = new MasterNode();
        rootDepartment.setId("root");
        rootDepartment.setType("#");
        rootDepartment.setChildren(beans);
        rootDepartment.setText("MyCompany");

        // Get departments
        List<Department> lstDepartments = getDepartments();
        Iterator<Department> itDepartment = lstDepartments.iterator();
        Department department;
        MasterNode subDepartment;
        while (itDepartment.hasNext()) {
            department = itDepartment.next();
            
            // Convert entity to node
            subDepartment = new MasterNode();
            subDepartment.setId(String.valueOf(department.getId()));
            subDepartment.setType("file");
            subDepartment.setChildren(null);
            subDepartment.setText(department.getName());
            
            beans.add(subDepartment);
        }
        // Parse object to json data
        Gson gson = new Gson();
        String jsonData = gson.toJson(rootDepartment);;

        return jsonData;
    }

    public String getDepartmentJson(String parentDepartmentId) {
        List<MasterNode> beans = new ArrayList<MasterNode>();

        MasterNode department = new MasterNode();
        department.setId("sub-department");
        department.setType("file");
        department.setChildren(null);
        department.setText("Sub department");
        beans.add(department);

        Gson gson = new Gson();
        String json = gson.toJson(beans);;

        return json;
    }

    public static RequestTypeJpaController getRequestTypeDaoController() {
        // Initialize entity manager factory
        RequestTypeJpaController daoCtrl = new RequestTypeJpaController(emf);
        
        return daoCtrl;
    }
    
    /* Get all user from database */
    public List<User> getUsers() {
        // Initialize entity manager factory
        UserJpaController controller = new UserJpaController(emf);
        
        return controller.findUserEntities();
    }
    
    public static User findUserByUsername(String username) {
        ExUserJpaController userDaoCtrl = new ExUserJpaController(BaseService.getEmf());
        return userDaoCtrl.findUserByUsername(username);
    }

    public static Department findDepartmentByCd(String deptCd) {
        ExDepartmentJpaController daoCtrl = new ExDepartmentJpaController(BaseService.getEmf());
        
        return daoCtrl.findDepartmentByCd(deptCd);
    }

    
    /**
    * [Give the description for method].
    * @return
    */
    private final static List<DurationUnit> lstDurationUnits = new ArrayList<DurationUnit>(3);
    static {
        lstDurationUnits.add(new DurationUnit("0", "Giờ"));
        lstDurationUnits.add(new DurationUnit("1", "Ngày"));
        lstDurationUnits.add(new DurationUnit("2", "Tuần"));
        lstDurationUnits.add(new DurationUnit("3", "Tháng"));
    }
    public static List<DurationUnit> getDurationUnits() {
        return lstDurationUnits;
    }
    
    /**
    * Get name of unit.
    * @param code 0: Giờ; 1: Ngày; 2: Tuần; 3: Tháng
    * @return String of unit name
    */
    public static String getDurationUnitName(int code) {
        String duName = null;
        Iterator<DurationUnit> itDurationUnit = lstDurationUnits.iterator();

        DurationUnit du;
        String strCode = String.valueOf(code);
        while (itDurationUnit.hasNext()) {
            du = itDurationUnit.next();
            if (du.getCd().equals(strCode)) {
                duName = du.getName();
            }
        }

        return duName;
    }

    /**
    * Get all status of Task, Leave, Announcement, Rule.
    * @return
    */
    public List<String> getAllStatus() {
        EntityManager em = getEmf().createEntityManager();
        String strQuery = "SELECT DISTINCT s.currentStatus FROM StatusFlow s ORDER BY s.seqNo";
        
        Query query = em.createQuery(strQuery);
        List<String> lstStatus = query.getResultList();
        
        return lstStatus;
    }
    
    /**
    * Get available status of current request.
    * @param request
    * @param typeOfUser
    * @return
    */
    public List<String> getNextStatus(Request request, AppCons.TYPE_USER typeOfUser) {
        EntityManager em = getEmf().createEntityManager();
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(StatusFlow.class);
        
        cq.select(root.get("nextStatus")).distinct(true);

        String currentStatus = request.getStatus();
        
        Predicate predicate = cb.equal(root.get("currentStatus"), currentStatus);
        predicate = cb.and(predicate, cb.equal(root.get("requesttypeCd"), request.getRequesttypeCd()));
        
        if (typeOfUser != null) {
            predicate = cb.and(predicate, cb.equal(root.get("typeUser"), typeOfUser.toString()));
        }
        
        cq.where(predicate);
        
        Query query = em.createQuery(cq);
        
        try {
        
            return query.getResultList();
        } catch (NoResultException nrEx) {
            LOG.info("typeOfUser=" + typeOfUser + ".Next step of status '" + currentStatus + "' not found");
            return null;
        }
        
    }
        
    public static String getJsonRequestTypes() {
        RequestTypeModel requestTypeModel = new RequestTypeModel(); 

        RequestTypeJpaController requestTypeDaoCtrl = getRequestTypeDaoController();
        List<RequestType> lstRequestTypes = requestTypeDaoCtrl.findRequestTypeEntities();
        
        if (!CommonUtil.isNNandNB(lstRequestTypes)) {
            // Set default sample data            
            ApplicationContext appCtx = new ClassPathXmlApplicationContext("init-data.xml");
            requestTypeModel = appCtx.getBean("requestTypeTableModel", RequestTypeModel.class);
            requestTypeModel.setIsSample(true);
        } else {
            requestTypeModel.setDataList(lstRequestTypes);
        }

        String jsonData = requestTypeModel.getJsonData();

        LOG.debug("jsonData=" + jsonData);
        return jsonData;
    }
    
    public static String getJsonDepartments() {
        DepartmentModel departModel = new DepartmentModel(); 

        List<Department> lstDepartments = getDepartments();
        
        if (!CommonUtil.isNNandNB(lstDepartments)) {
            // Set default sample data            
            ApplicationContext appCtx = new ClassPathXmlApplicationContext("init-data.xml");
            departModel = appCtx.getBean("departmentModel", DepartmentModel.class);
            departModel.setIsSample(true);
        } else {
            departModel.setDataList(lstDepartments);
        }

        String jsonData = departModel.getJsonData();

        LOG.debug("jsonData=" + jsonData);
        
        return jsonData;
    }
    
    public static String getJsonSystemUser() {
        SystemUserModel systemUserModel = new SystemUserModel(); 

        ExUserJpaController userDaoCtrl = new ExUserJpaController(BaseService.getEmf());
        List<User> lstUsers = null;
        User userAdmin = userDaoCtrl.findUserByUsername("admin");
        
        if (userAdmin != null) {
            lstUsers = new ArrayList<User>(); 
            lstUsers.add(userAdmin);
        } else {
            // Do nothing
        }
        
        if (!CommonUtil.isNNandNB(lstUsers)) {
            // Set default sample data
            ApplicationContext appCtx = null;

            try {
                appCtx = new ClassPathXmlApplicationContext("init-data.xml");
                systemUserModel = appCtx.getBean("systemUserModel", SystemUserModel.class);
                systemUserModel.setIsSample(true);
            } finally {
                if (appCtx != null) {
                    // close resource
                }

            }
        } else {
            systemUserModel.setDataList(lstUsers);
        }

        String jsonData = systemUserModel.getJsonData();

        LOG.debug("jsonData=" + jsonData);
        return jsonData;
    }

    public static String getJsonStatusFlowRequest() {
        StatusFlowModel statusFlowModel = new StatusFlowModel(); 

        StatusFlowJpaController statusDaoCtrl = new StatusFlowJpaController(BaseService.getEmf());
        List<StatusFlow> lstStatus = statusDaoCtrl.findStatusFlowEntities();

        if (!CommonUtil.isNNandNB(lstStatus)) {
            // Set default sample data            
            ApplicationContext appCtx = new ClassPathXmlApplicationContext("init-data.xml");
            statusFlowModel = appCtx.getBean("statusFlowModel", StatusFlowModel.class);
            statusFlowModel.setIsSample(true);
        } else {
            statusFlowModel.setDataList(lstStatus);
        }

        String jsonData = statusFlowModel.getJsonData();

        LOG.debug("jsonData=" + jsonData);

        return jsonData;
    }

    public static String getJsonParameter() {
        ParameterModel parameterModel = new ParameterModel(); 

        ParameterJpaController parameterDaoCtrl = new ParameterJpaController(BaseService.getEmf());
        List<Parameter> lstParam = parameterDaoCtrl.findParameterEntities();
        
        if (!CommonUtil.isNNandNB(lstParam)) {
            // Set default sample data            
            ApplicationContext appCtx = new ClassPathXmlApplicationContext("init-data.xml");
            parameterModel = appCtx.getBean("parameterModel", ParameterModel.class);
            parameterModel.setIsSample(true);
        } else {
            parameterModel.setDataList(lstParam);
        }
        
        String jsonData = parameterModel.getJsonData();

        LOG.debug("jsonData=" + jsonData);

        return jsonData;
    }
    
    /**
    * Create or Update the request types.
    * <br/>
    * For request code existed, update Name and other information.
    * For request code did not existed, create newly.
    * @param requestTypeModel
    * @return fresh model
     * @throws SaveBatchException 
    */
    public RequestTypeModel saveAllRequestType(RequestTypeModel requestTypeModel, String username) throws SaveBatchException {
        ExRequestTypeJpaController requestTypeDaoCtrl = new ExRequestTypeJpaController(BaseService.getEmf());
        
        List<RequestType> lstRequestType = requestTypeModel.getDataList();
        List<AppCons.RESULT> lstResult = requestTypeDaoCtrl.save(lstRequestType, username);
        
        requestTypeModel.setResultList(lstResult);
        
        return requestTypeModel;
    }
    
    public DepartmentModel saveAllDepartment(DepartmentModel departmentModel, String username) throws SaveBatchException {
        ExDepartmentJpaController departmentDaoCtrl = new ExDepartmentJpaController(BaseService.getEmf());
        
        List<Department> lstDepartment = departmentModel.getDataList();
        List<AppCons.RESULT> lstResult = departmentDaoCtrl.save(lstDepartment, username);
        
        departmentModel.setResultList(lstResult);
        
        return departmentModel;
    }

    public SystemUserModel saveAllUser(SystemUserModel userModel, String username) throws SaveBatchException {
        ExUserJpaController userDaoCtrl = new ExUserJpaController(BaseService.getEmf());
        
        List<User> lstUser = userModel.getDataList();
        List<AppCons.RESULT> lstResult = userDaoCtrl.save(lstUser, username);
        
        userModel.setResultList(lstResult);
        
        return userModel;
    }
    
    public StatusFlowModel saveAllStatusFlowRequest(StatusFlowModel statusFlowModel, String username) throws SaveBatchException {
        ExStatusFlowJpaController statusFlowDaoCtrl = new ExStatusFlowJpaController(BaseService.getEmf());
        
        List<StatusFlow> lstStatusFlow = statusFlowModel.getDataList();
        List<AppCons.RESULT> lstResult = statusFlowDaoCtrl.save(lstStatusFlow, username);
        
        statusFlowModel.setResultList(lstResult);
        
        return statusFlowModel;
    }
    
    public ParameterModel saveAllParameter(ParameterModel parameterModel, String username) throws SaveBatchException {
        ExParameterJpaController parameterDaoCtrl = new ExParameterJpaController(BaseService.getEmf());
        
        List<Parameter> lstParameter = parameterModel.getDataList();
        List<AppCons.RESULT> lstResult = parameterDaoCtrl.save(lstParameter, username);
        
        parameterModel.setResultList(lstResult);
        
        return parameterModel;
    }
}
