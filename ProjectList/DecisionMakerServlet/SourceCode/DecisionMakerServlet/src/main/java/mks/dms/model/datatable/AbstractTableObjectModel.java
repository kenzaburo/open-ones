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
import java.util.List;

import mks.dms.util.AppCons;
import mks.dms.util.AppUtil;

import com.google.gson.Gson;

/**
 * This class support to capture data from the Handsontable (Excel-based form)
 * @author ThachLN
 */
public abstract class AbstractTableObjectModel implements Serializable {

    /** Mapping data with handsontable . */
    List<Object[]> data = null;
    
    public AbstractTableObjectModel() {
        // Do nothing
    }
    
    public AbstractTableObjectModel(List<Object[]> data) {
        this.data = data;
    }

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

    public List<Object[]> getData() {
        return data;
    }

    public void setData(List<Object[]> data) {
        boolean hasEmptyRow = false;
        do {
            // Check to remove last empty row
            int len = (data != null) ? data.size() : 0;

            if (len > 0) {
                Object[] lastRow = data.get(len - 1);
                if (!AppUtil.isNotEmptyRow(lastRow)) {
                    data.remove(len - 1);
                    hasEmptyRow = true;
                } else {
                    hasEmptyRow = false;
                }
            } else {
                // Do nothing
            }
        } while (hasEmptyRow);
        
        this.data = data;
    }

    abstract void setDataList(List<?> dataList);
    
}
