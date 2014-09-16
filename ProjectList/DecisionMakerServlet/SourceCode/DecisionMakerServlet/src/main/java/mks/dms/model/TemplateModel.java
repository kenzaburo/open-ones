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
package mks.dms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mks.dms.dao.entity.Department;
import rocky.common.CHARA;

import com.google.gson.Gson;

/**
 * @author ThachLN
 */
public class TemplateModel implements Serializable {
    private String parentDepartment;
    private List<Object[]> data = null;
    
    public TemplateModel() {
        // TODO Auto-generated constructor stub
    }
    
    public TemplateModel(String parentDepartment, List<Object[]> data) {
        this.parentDepartment = parentDepartment;
        this.data = data;
    }

    public String getJsonDepartments() {
        Gson gson = new Gson();
        
        String jsonData;

        jsonData = gson.toJson(data);
        
        // add prefix
        StringBuffer sb = new StringBuffer();
        sb.append("{ \"data\": ")
          .append(jsonData)
          .append("}");
        
        jsonData = sb.toString();

        return jsonData;
    }
    public List<Object[]> getData() {
        return data;
    }

    public void setData(List<Object[]> data) {
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

    public void setDepartments(List<Department> lstDepartments) {
        Iterator<Department> itDepartment = lstDepartments.iterator();
        
        Department department;
        
        if (data == null) {
            data = new ArrayList<Object[]>(lstDepartments.size());
        }
        Object[] arrObjs;
        while (itDepartment.hasNext()) {
            department = itDepartment.next();
            
            arrObjs = new Object[4];
            arrObjs[0] = formatJson(department.getCd());
            arrObjs[1] = formatJson(department.getName());
            //arrObjs[2] = formatJson(department.getManagerName());
            arrObjs[2] = formatJson(department.getManagerUsername());
            arrObjs[3] = formatJson(department.getDescription());
            
            data.add(arrObjs);
        }        
    }

    private Object formatJson(String text) {
        if (text == null) {
            text = CHARA.BLANK;
        }
        
        return text;
    }

    
    
}
