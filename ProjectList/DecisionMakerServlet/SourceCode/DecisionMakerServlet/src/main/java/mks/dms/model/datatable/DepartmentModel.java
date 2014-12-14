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
package mks.dms.model.datatable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mks.dms.dao.entity.Department;
import mks.dms.util.AppCons.RESULT;
import mks.dms.util.AppUtil;

/**
 * @author ThachLN
 */
public class DepartmentModel extends AbstractTableObjectModel implements Serializable {
    /** Index column CD in data list . */
    public final static int IDX_CD = 0;
    
    /** Index column NAME in data list . */
    public final static int IDX_NAME = 1;
    
    public final static int IDX_MANAGER = 2;
    
    public final static int IDX_NOTE = 3;

    /** Store result of saving. */
    public final static int IDX_RESULT = 4;
    
    private String parentDepartment;

    public DepartmentModel() {
        // Do nothing
    }
    
    public DepartmentModel(String parentDepartment, List<Object[]> data) {
        this.parentDepartment = parentDepartment;
        this.data = data;
    }

    /**
     * Get value of parentDepartment.
     * 
     * @return the parentDepartment
     */
    public String getParentDepartment() {
        return parentDepartment;
    }

    /**
     * Set the value for parentDepartment.
     * 
     * @param parentDepartment the parentDepartment to set
     */
    public void setParentDepartment(String parentDepartment) {
        this.parentDepartment = parentDepartment;
    }
    
    /**
    * Convert from the data list (List of object[]) to contextual data list.
    * @return
    */
    public List<Department> getDataList() {
        List<Department> lstDepartment = null;
        if (data == null) {
            // Do nothing
        } else {
            lstDepartment = new ArrayList<Department>();
            Object[] row;
            Department department;

            for (Iterator<Object[]> itRow = data.iterator(); itRow.hasNext(); ) {
                row = itRow.next();

                department = new Department();
                department.setCd(String.valueOf(row[IDX_CD]));
                department.setName(String.valueOf(row[IDX_NAME]));
                department.setManagerUsername(String.valueOf(row[IDX_MANAGER]));
                department.setDescription(String.valueOf(row[IDX_NOTE]));
                

                lstDepartment.add(department);
            }
        }
        
        return lstDepartment;
    }
    
    public void setResultList(List<RESULT> lstResult) {
        if ((lstResult == null) || (this.data == null)) {
            return;
        } else {
            Object[] row;
            Department department;
            int i = 0;
            int numResult = lstResult.size();
            for (Iterator<Object[]> itRow = data.iterator(); itRow.hasNext(); i++) {
                row = itRow.next();

                department = new Department();
                department.setCd(String.valueOf(row[IDX_CD]));
                department.setName(String.valueOf(row[IDX_NAME]));
                department.setManagerUsername(String.valueOf(row[IDX_MANAGER]));
                department.setDescription(String.valueOf(row[IDX_NOTE]));

                if (i < numResult) {
                    row[IDX_RESULT] = lstResult.get(i).name();
                }
            }
        }
    }
    
    @Override
    public void setDataList(List<?> lstDepartments) {
        Iterator itDepartment = lstDepartments.iterator();
        
        Department department;
        
        if (data == null) {
            data = new ArrayList<Object[]>(lstDepartments.size());
        }
        Object[] arrObjs;
        while (itDepartment.hasNext()) {
            department = (Department) itDepartment.next();
            
            arrObjs = new Object[5];
            arrObjs[0] = AppUtil.formatJson(department.getCd());
            arrObjs[1] = AppUtil.formatJson(department.getName());
            arrObjs[2] = AppUtil.formatJson(department.getManagerUsername());
            arrObjs[3] = AppUtil.formatJson(department.getDescription());
            // Reserved column 5 for Result of saving

            data.add(arrObjs);
        }        
    } 
}
