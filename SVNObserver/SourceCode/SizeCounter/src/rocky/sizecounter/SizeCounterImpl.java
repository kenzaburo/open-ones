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
package rocky.sizecounter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.apache.log4j.Logger;

import rocky.common.CommonUtil;
import rocky.common.PropertiesManager;
import tk.stepcounter.CountResult;
import tk.stepcounter.StepCounter;
import tk.stepcounter.StepCounterFactory;

/**
 * @author ThachLe, team member
 */
public class SizeCounterImpl implements ISizeCounter {
    /** Logger. */
    static final Logger LOG = Logger.getLogger("SizeCounterImpl");

    /** . */
    private static final String EXT_SEPARATOR = ",";

    // Load the configuration file: ApplicationResources.properties
    /** List of supported extension file. It's without dot character. */
    private static String[] supportedExtFiles = null;

    /** Extension files are source code. */
    private static String[] supportedExtSrcFiles = null;

    /** Names of sheets in UTC/UTR. */
    private static String[] utcSheets = null;

    /** Report sheet of UTC/UTR . */
    private static String utcReportSheet = null;

    /** Text of row to recognize the num of TC/TR . */
    private static String utcLocationRow = null;

    static {
        try {
            Properties props = PropertiesManager.newInstanceFromProps("/ApplicationResources.properties");

            // Get configuration of supported files
            String supportedExt = props.getProperty("SupportFiles").trim();
            supportedExtFiles = supportedExt.split(EXT_SEPARATOR);
            Arrays.sort(supportedExtFiles);

            // Get configuration of extension of files
            String srcExt = props.getProperty("SourceFiles").trim();
            supportedExtSrcFiles = srcExt.split(EXT_SEPARATOR);
            Arrays.sort(supportedExtSrcFiles);

            // Get configuration of pattern UTC file
            String utc = props.getProperty("UTC").trim();
            utcSheets = utc.split(EXT_SEPARATOR);
            Arrays.sort(utcSheets);

            // Get sheet name contains report of UTC/UTR
            utcReportSheet = props.getProperty("UTCLocation").trim();

            // Get text to recognize the row which contain report
            utcLocationRow = props.getProperty("UTCLocationRow").trim();

        } catch (Exception ex) {
            LOG.error("Load configuration file ApplicationResources.properties", ex);
        }
    }

    @Override
    public SizeMetaData countSize(String filePath) {
        // TODO Auto-generated method stub
        SizeMetaData sizeMD = new SizeMetaData();
        SourceMetaData sizeMd = new SourceMetaData();
        // File extension without dot character
        String ext = CommonUtil.getExtension(filePath);
        int nmPage;

        if (CommonUtil.isNNandNB(ext)) {
            if (isSourceFile(ext)) {
                try {
                    sizeMd = countLOC(filePath);
                    sizeMD.setSize(sizeMd.getLoc());
                    sizeMD.setUnit(UnitType.LOC);
                    sizeMD.setSize1(sizeMd.getComment());
                    sizeMD.setUnit1(UnitType.COMMENT);
                    sizeMD.setComment(sizeMd.getComment());
                } catch (UnsupportedFileType ex) {
                    // Skip the exception. The extension is not supported.
                    LOG.warn("Could not support extension '" + ext + "'", ex);
                }

            } else if (SizeCounterUtil.isWordFile(ext)) {
                nmPage = SizeCounterUtil.countWordFile(filePath);
                sizeMD.setSize(nmPage);
                sizeMD.setUnit(UnitType.PAGE);
            } else if (SizeCounterUtil.isExcelFile(ext)) {
                sizeMD = SizeCounterUtil.countSpreadSheet(filePath);

                // If the excel file is the UnitTest Case or UnitTest Report
                if (SizeCounterUtil.isUTCFile(filePath, utcSheets)) {
                    int nmbUTC = SizeCounterUtil.countNmTC(filePath, utcReportSheet, utcLocationRow);
                    sizeMD.setSize2(nmbUTC);
                    sizeMD.setUnit2(UnitType.UTC);
                }
            } else if (SizeCounterUtil.isPresentFile(ext)) {
                int nmSlide = SizeCounterUtil.countSlide(filePath);
                sizeMD.setSize(nmSlide);
                sizeMD.setUnit(UnitType.SLIDE);
            }
        }

        return sizeMD;
    }

    /**
     * [Give the description for method].
     * @param ext File extension without dot charater.
     * @return true if the ext is in the support list of the configuration file.
     */
    private boolean isSourceFile(String ext) {
        return Arrays.binarySearch(supportedExtSrcFiles, ext) >= 0;
    }

    /*
     * Explain the description for this method here
     * @see rocky.sizecounter.ISizeCounter#countLOC(java.lang.String, java.lang.String)
     */
    @Override
    public SourceMetaData countLOC(String filePath, String csName) throws UnsupportedFileType {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * Explain the description for this method here
     * @see rocky.sizecounter.ISizeCounter#countLOC(java.lang.String)
     */
    @Override
    public SourceMetaData countLOC(String filePath) throws UnsupportedFileType {
        // TODO Auto-generated method stub
        SourceMetaData srcMd = new SourceMetaData();
        StepCounter stepCounter = StepCounterFactory.getCounter(filePath);
        CountResult cntRes;

        if (stepCounter != null) {

            try {
                cntRes = stepCounter.count(new File(filePath), "UTF-8");

                if (cntRes != null) {
                    srcMd.setLoc(cntRes.getStep());
                    srcMd.setComment(cntRes.getComment());
                } else {
                    LOG.warn("Could not count file extension:" + CommonUtil.getExtension(filePath));
                }

                return srcMd;

            } catch (IOException ioex) {
                LOG.error("Error in counting size of file '" + filePath + "'", ioex);
            }
        } else {
            LOG.warn("Don't support counter for file extension:" + CommonUtil.getExtension(filePath));
            throw new UnsupportedFileType("Don't support counter for file extension:"
                    + CommonUtil.getExtension(filePath));
        }

        return null;
    }

    /**
     * Check whether the input extFile is countable size or not.
     * @param extFile the extension without the dot character.
     * @return the flag notices file extension is countable or not.
     * @see rocky.sizecounter.ISizeCounter#isCountable(java.lang.String)
     */
    @Override
    public boolean isCountable(String extFile) {
        return (Arrays.binarySearch(supportedExtFiles, extFile) >= 0);
    }
}
