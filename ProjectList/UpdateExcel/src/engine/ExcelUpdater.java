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
package engine;

import info.ConfigInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import rocky.common.Constant;
import rocky.poi.PoiUtil;

/**
 * This class support to update given cell of sheet
 * @author thachln
 *
 */
public class ExcelUpdater {
    private final static Logger LOG = Logger.getLogger("ExcelUpdater");

    Workbook workbook = null;
    
    /**
     * Load information of UT code into the UT report.
     */
    public boolean update(File excelFile, ConfigInfo confInfo) {
        LOG.debug("Updating file : " + excelFile.getPath());
        try {
            String filename = excelFile.getName();
            if (filename.endsWith(Constant.DOT_XLS)) {
                workbook = new HSSFWorkbook(new FileInputStream(excelFile));
            } else if (filename.endsWith(Constant.DOT_XLSX)) {
                workbook = new XSSFWorkbook(new FileInputStream(excelFile));
            }
        } catch (FileNotFoundException ex) {
            LOG.error("Could not found '" + excelFile.getPath() + "'", ex);
        } catch (IOException ex) {
            LOG.error("Could not read/write '" + excelFile.getPath() + "'", ex);
        };
        
        Sheet sheet;
        Integer nSeqNo = confInfo.getStartValue();
        int len = workbook.getNumberOfSheets();
        String cellAddr;
        for (int sheetNo = workbook.getSheetIndex(confInfo.getStartSheet()); sheetNo < len; sheetNo++) {
            if (sheetNo < 0) {
                LOG.info("Layou file '" + excelFile.getPath() + "' is wrong.");
                return false;
            }
            
            // i is special sheet
            if (confInfo.containSpecialSheetNo(sheetNo)) {
                cellAddr = confInfo.getSpecialCellAddr(sheetNo);
            } else {
                cellAddr = confInfo.getGivenCell();
            }
            sheet = workbook.getSheetAt(sheetNo);
            PoiUtil.setContent(sheet, cellAddr, nSeqNo);
            nSeqNo++;
        }

        // Set focus
        workbook.setActiveSheet(confInfo.getFocusSheetNo());
        sheet = workbook.getSheetAt(confInfo.getFocusSheetNo());
        CellReference cellRef = new CellReference(confInfo.getFocusCell());
        sheet.showInPane((short) cellRef.getRow(), (short) cellRef.getCol());
        
        // Save file
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(excelFile);
            workbook.write(os);
        } catch (FileNotFoundException ex) {
           LOG.error("Could not save file.", ex);
        } catch (IOException ex) {
            LOG.error("Could not save file.", ex);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException ex) {
                    LOG.warn(ex);
                }
            }
        }
        
        return true;
    }
}
