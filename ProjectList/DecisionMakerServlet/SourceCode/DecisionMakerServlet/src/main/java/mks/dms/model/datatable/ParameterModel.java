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

import mks.dms.dao.entity.Parameter;
import mks.dms.util.AppUtil;

/**
 * @author ThachLe
 *
 */
public class ParameterModel extends AbstractTableObjectModel implements Serializable {


    @Override
    public void setDataList(List<?> lstParameter) {
        Iterator itParameter = lstParameter.iterator();
        
        Parameter parameter;
        
        if (data == null) {
            data = new ArrayList<Object[]>(lstParameter.size());
        }
        
        Object[] arrObjs;
        while (itParameter.hasNext()) {
            parameter = (Parameter) itParameter.next();
            
            arrObjs = new Object[5];
            arrObjs[0] = AppUtil.formatJson(parameter.getId());
            arrObjs[1] = AppUtil.formatJson(parameter.getCd());
            arrObjs[2] = AppUtil.formatJson(parameter.getName());
            arrObjs[3] = AppUtil.formatJson(parameter.getValue());
            arrObjs[4] = AppUtil.formatJson(parameter.getDescription());
            
            data.add(arrObjs);
        }
    }

}
