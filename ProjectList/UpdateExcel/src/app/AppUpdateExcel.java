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
package app;

import info.ConfigInfo;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import engine.ExcelUpdater;

import rocky.common.Constant;
import rocky.common.PropertiesManager;

/**
 * @author thachle
 *
 */
public class AppUpdateExcel implements FilenameFilter {
    private static final String DOT_EXCEL2003 = ".xls";
    private static final String DOT_EXCEL2007 = ".xlsx";
    private final static Logger LOG = Logger.getLogger("UpdateExcel");
    private Properties props = null;
    private String inputFolder;
    
    private static ConfigInfo confInfo = new ConfigInfo();

    ExcelUpdater updater = new ExcelUpdater();
    /**
     * Load configuration resource.
     * Parse it into entity.
     * @param configResource
     * @param inputFolder 
     */
    public AppUpdateExcel(String configResource, String inputFolder) {
        String startSheet;
        Integer startValue;
        String givenCell;
        
        Integer focusSheetNo;
        String focusCell;
        String outputFolder;
        try {
            if (configResource.endsWith(".xml")) {
                props = PropertiesManager.newInstanceFromXML(configResource);
            } else {
                props = PropertiesManager.newInstanceFromProps(configResource);
            }
            startSheet = props.getProperty("startSheet");
            startValue = Integer.valueOf(props.getProperty("startValue", "1"));
            givenCell = props.getProperty("givenCell");
            focusSheetNo = Integer.valueOf(props.getProperty("focusSheetNo", "0"));
            focusCell = props.getProperty("focusCell");
            outputFolder = props.getProperty("outputFolder");
            
            confInfo.setStartSheet(startSheet);
            confInfo.setStartValue(startValue);
            confInfo.setGivenCell(givenCell);
            confInfo.setFocusSheetNo(focusSheetNo);
            confInfo.setFocusCell(focusCell);
            confInfo.setOutputFolder(outputFolder);
            
            // Parse special sheet
            String specialSheetNo = props.getProperty("specialSheetNo");
            
            if (specialSheetNo != null) {
                String[] arrSheetNo = specialSheetNo.split(Constant.COMMA);
                String[] specialCellAddress = props.getProperty("specialCell").split(Constant.COMMA);
                int len = (arrSheetNo != null) ? arrSheetNo.length : 0;
                for (int i = 0; i < len; i++) {
                    confInfo.addSpecialSheet(Integer.valueOf(arrSheetNo[i]), specialCellAddress[i]);
                }
            }
        } catch (IOException ex) {
            LOG.error("Could not load configuration resource '" + configResource + "'", ex);
        }
        
        this.inputFolder = inputFolder;
    }

    /**
     * Entry point of application.
     * @param args first element is the resource configuration
     */
    public static void main(String[] args) {
        String configResource = null;
        String inputFolder = ".";
        if (args.length < 1) {
            usage();
            System.exit(1);
        } else if (args.length >= 1) {
            configResource = args[0];
            if (args.length >= 2) {
                inputFolder = args[1];
            }
        }
        
        new AppUpdateExcel(configResource, inputFolder).start();
    }

    /**
     * Business start the application.
     * Get all Unit Test codes from database via configuration "/META-INF/persistence.xml".
     * Parse the information to output to UT report.
     */
    private void start() {
        File file = new File(inputFolder);

        if (file.exists()) {
            if (file.isFile()) {
                updateFile(file);
            }
            if (file.isDirectory()) {
                scanFolder(file);
            }
        } else {
            LOG.error("File or folder not found :" + inputFolder);
        }
    }

    private void scanFolder(File folder) {
        // Scan files
        File[] lstFile = folder.listFiles(this);
        
        if (lstFile != null) {
            // Scan files
            for (File file : lstFile) {
                if (file.isFile()) {
                    updateFile(file);
                }
            }
            // Scan folders
            for (File file : lstFile) {
                if (file.isDirectory()) {
                    scanFolder(file);
                }
            }
        }
    }
    
    /**
     * [Give the description for method].
     * @param file
     */
    private void updateFile(File file) {
        updater.update(file, confInfo);
    }

    /**
     * Command guideline to use the application.
     */
    private static void usage() {
        System.out.println("UpdateExcel <configuration resource> [folder]");
    }

    /**
     * [Explain the description for this method here].
     * @param dir
     * @param name
     * @return
     * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
     */
    @Override
    public boolean accept(File dir, String name) {
        if (name.endsWith(DOT_EXCEL2003) || name.endsWith(DOT_EXCEL2007)) { 
            return true;
        }
        
        return false;
    }
}
