package mks.dms.model;

import java.io.Serializable;
import java.util.List;

import mks.dms.dao.entity.Department;
import mks.dms.dao.entity.Request;
import mks.dms.dao.entity.RequestType;
import mks.dms.dao.entity.User;

/**
 * @description Contains request model, which work on view
 * @author TriLVH, ThachLe
 */
public class RequestCreateModel implements Serializable {
    /* List of current request type get on database */
//    private List<RequestType> listRequestType;
    /* List of current user from database */
    //private List<User> listUser;
    
    /* List of  Deparment from databse*/
    private List<Department> listDepartment;

    private String labels;
    /* created request */
    private Request request;

    /* List user watch request */
    private Integer[] listWatcher;

//    public List<RequestType> getListRequestType() {
//        return listRequestType;
//    }
//
//    public void setListRequestType(List<RequestType> listRequestType) {
//        this.listRequestType = listRequestType;
//    }

//    public List<User> getListUser() {
//        return listUser;
//    }
//
//    public void setListUser(List<User> listUser) {
//        this.listUser = listUser;
//    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public List<Department> getListDepartment() {
        return listDepartment;
    }

    public void setListDepartment(List<Department> listDepartment) {
        this.listDepartment = listDepartment;
    }

    public Integer[] getListWatcher() {
        return listWatcher;
    }

    public void setListWatcher(Integer[] listWatcher) {
        this.listWatcher = listWatcher;
    }

    /**
     * Get value of labels.
     * @return the labels
     */
    public String getLabels() {
        return labels;
    }

    /**
     * Set the value for labels.
     * @param labels the labels to set
     */
    public void setLabels(String labels) {
        this.labels = labels;
    }
    
}
