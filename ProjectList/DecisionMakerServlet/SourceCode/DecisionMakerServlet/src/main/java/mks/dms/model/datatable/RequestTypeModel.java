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

import mks.dms.dao.entity.RequestType;
import mks.dms.util.AppCons;
import mks.dms.util.AppCons.RESULT;
import mks.dms.util.AppUtil;

import com.google.gson.Gson;

/**
 * This class support to capture data from the Handsontable (Excel-based form)
 * @author ThachLN
 */
public class RequestTypeModel extends AbstractTableObjectModel implements Serializable {
    /** Index column CD in data list . */
    public final static int IDX_CD = 0;
    
    /** Index column NAME in data list . */
    public final static int IDX_NAME = 1;
    
    /** Store result of saving. */
    public final static int IDX_RESULT = 2;

    @Override
    public String getJsonData() {
        if (this.data == null) {
            return null;
        } else {
            Gson gson = new Gson();

            String jsonData;

            jsonData = gson.toJson(data);

            // add data into the block to support handsontable
            StringBuffer sb = new StringBuffer();
            sb.append(AppCons.BEGIN_JSON_DATA).append(jsonData).append(AppCons.END_JSON_DATA);

            jsonData = sb.toString();

            return jsonData;
        }
    }

    @Override
    public void setDataList(List<?> lstRequestTypes) {
        Iterator itRequestType = lstRequestTypes.iterator();
        
        RequestType requestType;
        
        if (data == null) {
            data = new ArrayList<Object[]>(lstRequestTypes.size());
        }
        
        Object[] arrObjs;
        while (itRequestType.hasNext()) {
            requestType = (RequestType) itRequestType.next();
            
            arrObjs = new Object[3];
            arrObjs[IDX_CD] = AppUtil.formatJson(requestType.getCd());
            arrObjs[IDX_NAME] = AppUtil.formatJson(requestType.getName());
            // Reserved column 3 for Result of saving
            
            data.add(arrObjs);
        }
    }

    /**
    * Convert from the data list (List of object[]) to contextual data list.
    * @return
    */
    public List<RequestType> getDataList() {
        List<RequestType> lstRequestType = null;
        if (data == null) {
            // Do nothing
        } else {
            lstRequestType = new ArrayList<RequestType>();
            Object[] row;
            RequestType requestType;

            for (Iterator<Object[]> itRow = data.iterator(); itRow.hasNext(); ) {
                row = itRow.next();

                requestType = new RequestType();
                requestType.setCd(String.valueOf(row[IDX_CD]));
                requestType.setName(String.valueOf(row[IDX_NAME]));

                lstRequestType.add(requestType);
            }
        }
        
        return lstRequestType;
    }

    public void setResultList(List<RESULT> lstResult) {
        if ((lstResult == null) || (this.data == null)) {
            return;
        } else {
            Object[] row;
            RequestType requestType;
            int i = 0;
            int numResult = lstResult.size();
            for (Iterator<Object[]> itRow = data.iterator(); itRow.hasNext(); i++) {
                row = itRow.next();

                requestType = new RequestType();
                requestType.setCd(String.valueOf(row[IDX_CD]));
                requestType.setName(String.valueOf(row[IDX_NAME]));

                if (i < numResult) {
                    row[IDX_RESULT] = lstResult.get(i).name();
                }
            }
        }
    }
}
