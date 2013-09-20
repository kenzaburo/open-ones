/**
 * Licensed to Open-Ones Group under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Open-Ones Group licenses this file to you under the Apache License,
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
package vn.mkss.engine.codeauto.info;

import java.util.ArrayList;
import java.util.List;

/**
 * @author thachle
 */
public class ClassInfo {
    private String name;

    private List<MethodInfo> lstMethodInfo = new ArrayList<MethodInfo>();
    /**
     * @param name
     */
    public ClassInfo(String name) {
        this.name = name;
    }

    /**
     * Get value of name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value for name.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * [Give the description for method].
     * @param methodInfo
     */
    public void addMethod(MethodInfo methodInfo) {
        lstMethodInfo.add(methodInfo);
    }
    
    /**
     * Get all methods by method name.
     * @param methodName
     * @return
     */
    public List<MethodInfo> getMethod(String methodName) {
        List<MethodInfo> matchedMethodInfo = new ArrayList<MethodInfo>();
        for (MethodInfo method : this.lstMethodInfo) {
            if (methodName.equals(method.getName())) {
                matchedMethodInfo.add(method);
            }
        }
        
        return matchedMethodInfo;
    }

    /**
     * [Give the description for method].
     * @return
     */
    public List<MethodInfo> getMethods() {        
        return lstMethodInfo;
    }
}
