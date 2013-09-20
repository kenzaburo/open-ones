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
 *
 */
public class MethodInfo {
    private String modifier;
    private String type;
    private String className;
    private String name;
    private List<ParamInfo> lstParam = new ArrayList<ParamInfo>();
    private int lineNumber;
    /**
     * Get value of type.
     * @return the type
     */
    public String getType() {
        return type;
    }
    /**
     * Set the value for type.
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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
     * Get value of lstParam.
     * @return the lstParam
     */
    public List<ParamInfo> getLstParam() {
        return lstParam;
    }
    /**
     * Set the value for lstParam.
     * @param lstParam the lstParam to set
     */
    public void setLstParam(List<ParamInfo> lstParam) {
        this.lstParam = lstParam;
    }
    /**
     * Get value of modifier.
     * @return the modifier
     */
    public String getModifier() {
        return modifier;
    }
    /**
     * Set the value for modifier.
     * @param modifier the modifier to set
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
    /**
     * Get value of className.
     * @return the className
     */
    public String getClassName() {
        return className;
    }
    /**
     * Set the value for className.
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }
    
    /**
     * [Give the description for method].
     * @param paramType
     * @param paramName
     * @param isConst
     * @param isPointer
     * @return number of parameters
     */
    public int addParam(String paramType, String paramName, boolean isConst, boolean isPointer) {
        ParamInfo paramInfo = new ParamInfo(paramType, paramName, isConst, isPointer);
        lstParam.add(paramInfo);
        
        return lstParam.size();
    }
    /**
     * Get value of lineNumber.
     * @return the lineNumber
     */
    public int getLineNumber() {
        return lineNumber;
    }
    /**
     * Set the value for lineNumber.
     * @param lineNumber the lineNumber to set
     */
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}
