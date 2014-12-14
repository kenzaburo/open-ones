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
import mks.dms.util.AppCons.RESULT;
import mks.dms.util.AppUtil;

import org.apache.log4j.Logger;

/**
 * @author ThachLe
 *
 */
public class ParameterModel extends AbstractTableObjectModel implements Serializable {
    private static final Logger LOG = Logger.getLogger(ParameterModel.class);
    
    public final static int IDX_ID = 0;
    
    public final static int IDX_CD = 1;
    
    /** Index column NAME in data list . */
    public final static int IDX_NAME = 2;
    
    public final static int IDX_VALUE = 3;
    
    public final static int IDX_DESCRIPTION = 4;

    /** Store result of saving. */
    public final static int IDX_RESULT = 5;
    
    public List<Parameter> getDataList() {
        List<Parameter> lstParameter = null;

        if (data == null) {
            // Do nothing
        } else {
            lstParameter = new ArrayList<Parameter>();
            Object[] row;
            Parameter parameter;

            for (Iterator<Object[]> itRow = data.iterator(); itRow.hasNext(); ) {
                row = itRow.next();

                parameter = new Parameter();
                try {
                    parameter.setId(Integer.valueOf(String.valueOf(row[IDX_ID])));
                } catch (NumberFormatException nfEx) {
                    LOG.warn("Could not parse id '" + row[IDX_ID] + "'", nfEx);
                }
                
                parameter.setCd(String.valueOf(row[IDX_CD]));
                parameter.setName(String.valueOf(row[IDX_NAME]));
                parameter.setValue(String.valueOf(row[IDX_VALUE]));
                parameter.setDescription(String.valueOf(row[IDX_DESCRIPTION]));
                

                lstParameter.add(parameter);
            }
        }
        
        return lstParameter;
    }

    public void setResultList(List<RESULT> lstResult) {
        if ((lstResult == null) || (this.data == null)) {
            return;
        } else {
            Object[] row;
            Parameter parameter;
            int i = 0;
            int numResult = lstResult.size();
            for (Iterator<Object[]> itRow = data.iterator(); itRow.hasNext(); i++) {
                row = itRow.next();

                parameter = new Parameter();
                parameter.setId(Integer.valueOf(String.valueOf(row[IDX_ID])));
                parameter.setCd(String.valueOf(row[IDX_CD]));
                parameter.setName(String.valueOf(row[IDX_NAME]));
                parameter.setValue(String.valueOf(row[IDX_VALUE]));
                parameter.setDescription(String.valueOf(row[IDX_DESCRIPTION]));

                if (i < numResult) {
                    row[IDX_RESULT] = lstResult.get(i).name();
                }
            }
        }
    }

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
            
            arrObjs = new Object[6];
            arrObjs[0] = AppUtil.formatJson(parameter.getId());
            arrObjs[1] = AppUtil.formatJson(parameter.getCd());
            arrObjs[2] = AppUtil.formatJson(parameter.getName());
            arrObjs[3] = AppUtil.formatJson(parameter.getValue());
            arrObjs[4] = AppUtil.formatJson(parameter.getDescription());
            // Reserved column 5 for Result of saving
            
            data.add(arrObjs);
        }
    }

}
