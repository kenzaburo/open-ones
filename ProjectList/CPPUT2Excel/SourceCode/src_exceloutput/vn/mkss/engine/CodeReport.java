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
package vn.mkss.engine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;

import rocky.common.CommonUtil;
import rocky.poi.PoiUtil;
import vn.mkss.engine.codeauto.info.ClassInfo;

/**
 * @author thachle
 */
public class CodeReport {
    private final static Logger LOG = Logger.getLogger("CodeReport");

    private final static String SHEET_NAME = "Report";
    private static final short COLIDX_PATH = 0;
    private static final short COLIDX_FILENAME = 1;
    private static final short COLIDX_METHOD = 2;
    private static final short COLIDX_RETURN = 3;
    private static final short COLIDX_PARAMNAME = 4;
    private static final short COLIDX_PARAMTYPE = 4;

    HSSFWorkbook wbBugReport;

    /** On-progress sheet to write the bug list to. */
    HSSFSheet sheet = null;

    /** Cursor of row. */
    private int rowIdx = 1;

    public CodeReport(String templateReport) {
        try {
            wbBugReport = new HSSFWorkbook(CommonUtil.loadResource(templateReport));
        } catch (Exception ex) {
            LOG.error("Load report template '" + templateReport + "'", ex);
        }
    }

    public void writeReport(String path, String fileName, Collection<ClassInfo> lstClassInfo) {
//        if ((!CommonUtil.isNNandNB(fileName)) || (!CommonUtil.isNNandNB(lstClassInfo))) {
//            return;
//        }

        if (sheet == null) {
            sheet = wbBugReport.getSheet(SHEET_NAME);
            if (sheet == null) {
                wbBugReport.createSheet(SHEET_NAME);
            }
        }

        HSSFRow row;

        // Write the bug list
        for (ClassInfo classInfo : lstClassInfo) {
            row = HSSFCellUtil.getRow(rowIdx, sheet);
            PoiUtil.setContent(row, COLIDX_PATH, path);
            PoiUtil.setContent(row, COLIDX_FILENAME, fileName);
            PoiUtil.setContent(row, COLIDX_METHOD, classInfo.getName());
//            PoiUtil.setContent(row, BUG_CODE_COL, classInfo.getCd());
//            if (CommonUtil.isNNandNB(classInfo.getContent())) {
//                PoiUtil.setContent(row, NOTE_COL, classInfo.getContent());
//            }
            rowIdx++;
        }
    }

    /**
     * Write the buffer of bugs into Excel file.
     * Filename: 
     * @param outFolder file path of Excel.
     * If the path of file, the file is used (TODO)
     * If the path of directory, the file is generated with format BugReport_YYYYMMDD_HHMMSS.xls
     * In case the folder is not existed, it's created automatically.
     */
    public void toFile(String outFolder, String prefixFileName) {
        FileOutputStream fos = null;
        StringBuffer sbFilePath = new StringBuffer();
        sbFilePath.append(outFolder).append(File.separator).append(prefixFileName)
                .append(CommonUtil.formatDate(new Date(), "yyyyMMdd_HHmmss")).append(".xls");

        if (!CommonUtil.existedFile(outFolder)) {
            CommonUtil.mkdir(outFolder);
        }

        try {
            fos = new FileOutputStream(sbFilePath.toString());
            wbBugReport.write(fos);
        } catch (Exception ex) {
            LOG.error("Write to file '" + sbFilePath.toString(), ex);
        } finally {
            if (fos != null) {
                try {
                    fos.flush();
                } catch (IOException ex) {
                    LOG.warn("Flush data.", ex);
                }
            }
        }
    }
}
