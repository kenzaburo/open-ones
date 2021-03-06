/*
 * Copyright (c) 2009, FPT Software JSC
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *	- Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *	- Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *	- Neither the name of the FPT Software JSC nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, 
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, 
 * OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; 
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, 
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 
 package fpt.timesheet.bean.Approval;

import fpt.timesheet.bo.ComboBox.CommonListBO;
import fpt.timesheet.bo.ComboBox.ProjectComboBO;
import fpt.timesheet.constant.DATA;
import fpt.timesheet.constant.DefinitionList;
import fpt.timesheet.framework.util.StringUtil.StringMatrix;
import fpt.timesheet.util.CommonFunction;

public class PLListBean implements DefinitionList {

    // For Search Form
    private StringMatrix mtxProjectList = null;
    private StringMatrix mtxStatusList = null;
    private StringMatrix mtxSortbyList = null;

    // For Syncronize the name
    private StringMatrix mtxTypeList = null;
    private StringMatrix mtxProductList = null;
    private StringMatrix mtxProcessList = null;

    // For Select to Search
    private String strProject; // default
    private int intStatus;
    private int intSortby;
    private String strFromDate = null;
    private String strToDate = null;
    private String strAccount = "";

    // For check Id to next step
    private String strUpdateAction;
    private String[] arrIdList;
    private String strListingName = DATA.VIEW_PL_LIST;

    private String[] arrIscheck = null;
    private String[] arrComment = null;

    // For Display
    private StringMatrix mtxTimesheetList = null;

    //Paging
	private int intCurrentPage = 0;
	private int intTotalPage = 0;
	private int intTotalTimesheet = 0;

    public PLListBean() {
        this.contructList();
        this.strProject = "0";
        this.arrIdList = null;
        this.strUpdateAction = "";
        this.strListingName = DATA.VIEW_PL_LIST;
    }

    /**
     * Method setProject.
     * @param strProject
     */
    public void setProject(String strProject) {
        this.strProject = strProject;
    }

    /**
     * Method getProject.
     * @return strProject
     */
    public String getProject() {
        return strProject;
    }

    /**
     * Method setListingName.
     * @param strListingName
     */
    public void setListingName(String strListingName) {
        this.strListingName = strListingName;
    }

    /**
     * Method getListingName.
     * @return strListingName
     */
    public String getListingName() {
        return strListingName;
    }

    /**
     * Method getStatusList.
     * @return StringMatrix
     */
    public StringMatrix getStatusList() {
        if (this.mtxStatusList == null)
            contructList();
        return this.mtxStatusList;
    }

    /**
     * Method setStatusList.
     * @param List
     * @return boolean
     */
    public boolean setStatusList(StringMatrix List) {
        if (List != null) {
            this.mtxStatusList = new StringMatrix(List.toString());
            return true;
        }
        return false;
    }

    /**
     * Method getSortbyList.
     * @return mtxSortbyList
     */
    public StringMatrix getSortbyList() {
        if (this.mtxSortbyList == null)
            contructList();

        return this.mtxSortbyList;
    }

    /**
     * Method setSortbyList.
     * @param List
     * @return boolean
     */
    public boolean setSortbyList(StringMatrix List) {
        if (List != null) {
            this.mtxSortbyList = new StringMatrix(List.toString());
            return true;
        }
        return false;
    }

    /**
     * Method setStatus.
     * @param intStatus
     */
    public void setStatus(int intStatus) {
        this.intStatus = intStatus;
    }

    /**
     * Method getStatus.
     * @return intStatus
     */
    public int getStatus() {
        return intStatus;
    }

    /**
     * Method setSortby.
     * @param intSortby
     */
    public void setSortby(int intSortby) {
        this.intSortby = intSortby;
    }

    /**
     * Method getSortby.
     * @return intSortby
     */
    public int getSortby() {
        return intSortby;
    }

    /**
     * Method setFromDate.
     * @param strFromDate
     */
    public void setFromDate(String strFromDate) {
        this.strFromDate = strFromDate;
    }

    /**
     * Method getFromDate.
     * @return strFromDate
     */
    public String getFromDate() {
        if (this.strFromDate == null) {
            this.strFromDate = "";
        }
        return this.strFromDate;
    }

    /**
     * Method setToDate.
     * @param strToDate
     */
    public void setToDate(String strToDate) {
        this.strToDate = strToDate;
    }

    /**
     * Method getToDate.
     * @return strToDate
     */
    public String getToDate() {
        if (this.strToDate == null)
            this.strToDate = CommonFunction.defaultToDate();

        return this.strToDate;
    }

    /**
     * Method getProjectList.
     * @return mtxProjectList
     */
    public StringMatrix getProjectList() {
        return mtxProjectList;
    }

    /*  
	public boolean setProjectList(StringMatrix List)
    {
        if (List != null)
            {
            this.mtxProjectList = new StringMatrix(List.toString());
            return true;
        }
        return false;
    }
    */

    /**
     * Method getTimesheetList.
     * @return mtxTimesheetList
     */
    public StringMatrix getTimesheetList() {
        return mtxTimesheetList;
    }

    /**
     * Method setTimesheetList.
     * @param List
     * @return boolean
     */
    public boolean setTimesheetList(StringMatrix List) {
        if (List != null) {
            this.mtxTimesheetList = new StringMatrix(List.toString());
            return true;
        }
        return false;
    }

    /**
     * Method mapToName.
     * @param strKeyName
     * @param strId
     * @return String
     */
    public String mapToName(String strKeyName, String strId) {
        strId = strId.trim();
        String strName = "";
        String gItemId = "";
        StringMatrix mtxList = null;
        if (strKeyName.equalsIgnoreCase("Project")) {
            mtxList = this.mtxProjectList;
        }
        else if (strKeyName.equalsIgnoreCase("Type")) {
            mtxList = this.mtxTypeList;
        }
        else if (strKeyName.equalsIgnoreCase("Product")) {
            mtxList = this.mtxProductList;
        }
        else if (strKeyName.equalsIgnoreCase("Process")) {
            mtxList = this.mtxProcessList;
        }
        else if (strKeyName.equalsIgnoreCase("Status")) {
            mtxList = this.mtxStatusList;
        }
        else {
            return strId;
        }
        int maxrows = mtxList.getNumberOfRows();
        int i = 0;
        if (maxrows < 1)
            return strId;
        boolean found = false;
        while ((!found) && (i < maxrows)) {
            gItemId = mtxList.getCell(i, 0);
            if (gItemId.equalsIgnoreCase(strId)) {
                strName = mtxList.getCell(i, 1);
                found = true;
            }
            i++;
        }
        return strName;
    }

    /**
     * Method getUpdateAction.
     * @return strUpdateAction
     * @Description name of Action --> Approve, Reject or Update 
     */
    public String getUpdateAction() {
        return strUpdateAction;
    }

    /**
     * Method setUpdateAction.
     * @param strUpdateAction
     */
    public void setUpdateAction(String strUpdateAction) {
        this.strUpdateAction = strUpdateAction;
    }

    /**
     * Method setProjectList.
     * @param strRole
     * @param intUserID
     */
    public void setProjectList(String strRole, int intUserID) {
        ProjectComboBO pc = new ProjectComboBO();
        this.mtxProjectList = pc.getProjectComboList(
                strRole, intUserID, BILLABLE_PROJECT_TYPE,
                fpt.timesheet.constant.Timesheet.PROJECT_STATUS_RUNNING);
        if (this.strProject.length() < 2)
            this.strProject = this.mtxProjectList.getCell(0, 0);
    }

    /**
     * Method setProjectList.
     * @param mtxProjectList
     */
    public void setProjectList(StringMatrix mtxProjectList) {
        this.mtxProjectList = mtxProjectList;
    }

    /**
     * Method contructList.
     */
    private void contructList() {
        CommonListBO cmlRef = new CommonListBO();
        this.mtxTypeList = cmlRef.getTypeOfWorkList();
        this.mtxProductList = cmlRef.getProductList();
        this.mtxProcessList = cmlRef.getProcessList();
        // Status List
        this.mtxStatusList = new StringMatrix(4, 2);
        this.mtxStatusList.setCell(0, 0, "0");
        this.mtxStatusList.setCell(0, 1, "All");
        this.mtxStatusList.setCell(1, 0, "1");
        this.mtxStatusList.setCell(1, 1, "Unapproved");
        this.mtxStatusList.setCell(2, 0, "2");
        this.mtxStatusList.setCell(2, 1, "Approved");
        this.mtxStatusList.setCell(3, 0, "5");
        this.mtxStatusList.setCell(3, 1, "Rejected");
        this.intStatus = 1;
        // Sortby List
        this.mtxSortbyList = new StringMatrix(6, 2);
        this.mtxSortbyList.setCell(0, 0, "1");
        this.mtxSortbyList.setCell(0, 1, "Project");
        this.mtxSortbyList.setCell(1, 0, "2");
        this.mtxSortbyList.setCell(1, 1, "Account");
        this.mtxSortbyList.setCell(2, 0, "3");
        this.mtxSortbyList.setCell(2, 1, "Report Date");
        this.mtxSortbyList.setCell(3, 0, "4");
        this.mtxSortbyList.setCell(3, 1, "Process");
        this.mtxSortbyList.setCell(4, 0, "5");
        this.mtxSortbyList.setCell(4, 1, "Type");
        this.mtxSortbyList.setCell(5, 0, "6");
        this.mtxSortbyList.setCell(5, 1, "Product");
        this.intSortby = 1;
    }

    /**
     * Method getArrComment.
     * @return arrComment
     */
    public String[] getArrComment() {
        return arrComment;
    }

    /**
     * Method getArrIscheck.
     * @return arrIscheck
     */
    public String[] getArrIscheck() {
        return arrIscheck;
    }

    /**
     * Method setArrIscheck.
     * @param arrIscheck
     */
    public void setArrIscheck(String[] arrIscheck) {
        this.arrIscheck = arrIscheck;
    }

    /**
     * Method setArrComment.
     * @param arrComment
     */
    public void setArrComment(String[] arrComment) {
        this.arrComment = arrComment;
    }

    /**
     * Method getArrIdList.
     * @return arrIdList
     */
    public String[] getArrIdList() {
        return arrIdList;
    }

    /**
     * Method setArrIdList.
     * @param arrIdList
     */
    public void setArrIdList(String[] arrIdList) {
        this.arrIdList = arrIdList;
    }

    /**
     * Method getCurrentPage.
     * @return intCurrentPage
     */
    public int getCurrentPage() {
        return intCurrentPage;
    }

    /**
     * Method setCurrentPage.
     * @param intCurrentPage
     */
    public void setCurrentPage(int intCurrentPage) {
        this.intCurrentPage = intCurrentPage;
    }

    /**
     * Method getTotalPage.
     * @return intTotalPage
     */
    public int getTotalPage() {
        return intTotalPage;
    }

    /**
     * Method setTotalPage.
     * @param intTotalPage
     */
    public void setTotalPage(int intTotalPage) {
        this.intTotalPage = intTotalPage;
    }

    /**
     * Method getTotalTimesheet.
     * @return intTotalTimesheet
     */
    public int getTotalTimesheet() {
        return intTotalTimesheet;
    }

    /**
     * Method setTotalTimesheet.
     * @param intTotalTimesheet
     */
    public void setTotalTimesheet(int intTotalTimesheet) {
        this.intTotalTimesheet = intTotalTimesheet;
    }

    /**
     * Method getAccount
     * @return strAccount
     */
    public String getAccount() {
        return strAccount;
    }

    /**
     * Method setAccount
     * @param strAccount
     */
    public void setAccount(String strAccount) {
        this.strAccount = strAccount;
    }

}