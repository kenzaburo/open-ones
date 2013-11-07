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
package info;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ThachLN
 *
 */
public class ConfigInfo {
    private String startSheet;
    private Integer startValue;
    private String givenCell;
    
    private Integer focusSheetNo;
    private String focusCell;
    private String outputFolder;
    
    // Special sheet
    
    /** (Sheet No start from 0, Cell Address) . */
    Map<Integer, String> mapSpecialSheetAddr = new HashMap<Integer, String>();

    /**
     * Get value of startSheet.
     * @return the startSheet
     */
    public String getStartSheet() {
        return startSheet;
    }
    /**
     * Set the value for startSheet.
     * @param startSheet the startSheet to set
     */
    public void setStartSheet(String startSheet) {
        this.startSheet = startSheet;
    }
    /**
     * Get value of startValue.
     * @return the startValue
     */
    public Integer getStartValue() {
        return startValue;
    }
    /**
     * Set the value for startValue.
     * @param startValue the startValue to set
     */
    public void setStartValue(Integer startValue) {
        this.startValue = startValue;
    }
    /**
     * Get value of givenCell.
     * @return the givenCell
     */
    public String getGivenCell() {
        return givenCell;
    }
    /**
     * Set the value for givenCell.
     * @param givenCell the givenCell to set
     */
    public void setGivenCell(String givenCell) {
        this.givenCell = givenCell;
    }
    /**
     * Get value of focusSheetNo.
     * @return the focusSheetNo
     */
    public Integer getFocusSheetNo() {
        return focusSheetNo;
    }
    /**
     * Set the value for focusSheetNo.
     * @param focusSheetNo the focusSheetNo to set
     */
    public void setFocusSheetNo(Integer focusSheetNo) {
        this.focusSheetNo = focusSheetNo;
    }
    /**
     * Get value of focusCell.
     * @return the focusCell
     */
    public String getFocusCell() {
        return focusCell;
    }
    /**
     * Set the value for focusCell.
     * @param focusCell the focusCell to set
     */
    public void setFocusCell(String focusCell) {
        this.focusCell = focusCell;
    }
    /**
     * Get value of outputFolder.
     * @return the outputFolder
     */
    public String getOutputFolder() {
        return outputFolder;
    }
    /**
     * Set the value for outputFolder.
     * @param outputFolder the outputFolder to set
     */
    public void setOutputFolder(String outputFolder) {
        this.outputFolder = outputFolder;
    }
    /**
     * [Give the description for method].
     * @param sheetNo
     * @param cellAddr
     */
    public void addSpecialSheet(Integer sheetNo, String cellAddr) {
        mapSpecialSheetAddr.put(sheetNo, cellAddr);
        
    }

    /**
     * Check there is a special sheet no.
     * @param sheetNo start from 0
     * @return
     */
    public boolean containSpecialSheetNo(int sheetNo) {
        return (mapSpecialSheetAddr != null) ? mapSpecialSheetAddr.containsKey(sheetNo) : false;
    }
    
    /**
     * Get given cell address of sheet no.
     * @param sheetNo start from 0
     * @return
     */
    public String getSpecialCellAddr(int sheetNo) {
        return (mapSpecialSheetAddr != null) ? mapSpecialSheetAddr.get(sheetNo) : null;

    }
}
