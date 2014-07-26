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
import java.util.List;

/**
 * @author ThachLN
 */
public class MasterDepartmentRequest implements Serializable {
    //@JsonProperty("parentDepartment")
    private String parentDepartment;
    
    private List<Object[]> data;

    public MasterDepartmentRequest() {
    }

    public MasterDepartmentRequest(String parentDepartment, List<Object[]> data) {
        this.parentDepartment = parentDepartment;
        this.data = data;
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
}
