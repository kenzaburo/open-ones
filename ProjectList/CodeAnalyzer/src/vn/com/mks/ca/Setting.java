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
package vn.com.mks.ca;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import rocky.common.CHARA;
import rocky.common.CommonUtil;
import rocky.common.Constant;
import rocky.poi.PoiUtil;

/**
 * @author thachle
 */
public class Setting {
    final private static Logger LOG = Logger.getLogger("Setting");

    private Workbook workbook = null;
    public Setting() {
        String settingResource = "/Setting_TableView.xls";
        try {
            workbook = PoiUtil.loadWorkbookByResource(settingResource);
        } catch (Exception ex) {
            LOG.error("Could not load setting resource '" + settingResource + "'", ex);
        }
    }

    private void parseSetting() {

    }

    /**
     * Get list of cells in the first row.
     * @param sheetName
     * @return
     */
    public String[] getHeaders(String sheetName) {
        return getDataRow(sheetName, 0);
    }

    /**
     * [Give the description for method].
     * @param sheetName
     * @param rowIdx start from 0
     * @return
     */
    private String[] getDataRow(String sheetName, int rowIdx) {
        Sheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            return null;
        }

        Row row = sheet.getRow(rowIdx);

        if (row == null) {
            return null;
        }

        short minColIx = row.getFirstCellNum();
        short maxColIx = row.getLastCellNum();
        String[] lstData = new String[maxColIx - minColIx];
        Cell cell;
        Object value;
        int i = 0;
        for (short colIx = minColIx; colIx < maxColIx; colIx++) {
            cell = row.getCell(colIx);
            if (cell == null) {
                continue;
            }

            value = PoiUtil.getValue(cell);

            if (value instanceof Date) {
                lstData[i++] = CommonUtil.formatDate((Date) value, Constant.DEF_DATEFMT);
            } else if (value instanceof Double) {
                Double dblValue = (Double) value;
                lstData[i++] = String.valueOf(dblValue.intValue());
                
            } else if (value != null) {
                lstData[i++] = value.toString();
            } else {
                lstData[i++] = CHARA.BLANK;
            }

        }

        return lstData;
    }

    /**
     * Get list of cells in the second row.
     * @param sheetName
     * @return
     */
    public String[] getProperties(String sheetName) {
        return getDataRow(sheetName, 1);
    }

    /**
     * Get list of cells in the third row. <br/>
     * if the third row is no data, get real size of columns
     * @param sheetName
     * @return
     */
    public int[] getSizes(String sheetName) {
        String[] strSizes = getDataRow(sheetName, 2);

        int len = (strSizes != null) ? strSizes.length : 0;
        int[] nSizes = new int[len];
        for (int i = 0; i < len; i++) {
            nSizes[i] = Integer.valueOf(strSizes[i]);
        }

        return nSizes;
    }

}
