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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.xslf.XSLFSlideShow;
import org.apache.poi.xslf.extractor.XSLFPowerPointExtractor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;

import rocky.common.CommonUtil;
/**
 * @author ThachLe, team member
 */
public class SizeCounterUtil {
    /** Logger. */
    static final Logger LOG = Logger.getLogger("SizeCounterBiz");

    /** Extension of document files. */
    static final String[] WORD_EXT_FILES = {"doc", "docx"};

    /** Extension of spread files. */
    static final String[] EXCEL_EXT_FILES = {"xls", "xlsx"};

    /** Extension of presentation files. */
    static final String[] PP_EXT_FILES = {"ppt", "pptx"};

    /**
     * Count Word's number of page from input directory.
     * 
     * @param filePath .
     * @return Number of A4 pages
     */
    public static int countWordFile(String filePath) {
        FileInputStream fis = null;
        int page = 0;
        try {
            fis = new FileInputStream(filePath);

            if (CommonUtil.getExtension(filePath).equals("doc")) { // When file is .DOC
                HWPFDocument doc = new HWPFDocument(fis);
                page = doc.getDocProperties().getCPg();
            } else if (CommonUtil.getExtension(filePath).equals("docx")) { // When file is .DOCX
                XWPFDocument doc = new XWPFDocument(fis);
                XWPFWordExtractor ex = new XWPFWordExtractor(doc);
                page = ex.getExtendedProperties().getUnderlyingProperties().getPages();
            }
        } catch (FileNotFoundException ex) {
            LOG.warn("File " + filePath + " not found", ex);
        } catch (IOException ex) {
            LOG.warn("Invalid when reading file.", ex);
        } catch (Exception ex) {
            LOG.warn("Can not count file " + filePath, ex);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    LOG.warn("Close the file input stream", ex);
                }
            }
        }
        return page;
    }

    /**
     * Count size of Excel file.
     * 
     * @param filePath path of Excel file
     * @return SizeMetaData: Unit,Size: SHEET; Unit1,Size1:PAGE
     */
    public static SizeMetaData countSpreadSheet(String filePath) {
        InputStream is = null;
        SizeMetaData sizeMD = new SizeMetaData();
        int nmPage = 0;
        int nmSheet;
        try {
            is = CommonUtil.loadResource(filePath);
            if (CommonUtil.getExtension(filePath).equals("xls")) {
                HSSFWorkbook wb = new HSSFWorkbook(is);
                wb.getDocumentSummaryInformation().getLineCount();
                nmSheet = wb.getNumberOfSheets();
                HSSFSheet sheet;

                for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                    sheet = wb.getSheetAt(i);

                    // Count approximately number of page
                    nmPage += sheet.getPrintSetup().getFitWidth() + sheet.getPrintSetup().getFitHeight();
                }

                sizeMD.setUnit(UnitType.SHEET);
                sizeMD.setSize(nmSheet);

                sizeMD.setUnit1(UnitType.PAGE);
                sizeMD.setSize1(nmPage);

            } else if (CommonUtil.getExtension(filePath).equals("xlsx")) {
                XSSFWorkbook xwb = new XSSFWorkbook(is);
                nmSheet = xwb.getNumberOfSheets();
                XSSFSheet sheet;

                for (int i = 0; i < xwb.getNumberOfSheets(); i++) {
                    sheet = xwb.getSheetAt(i);

                    // Count approximately number of page
                    nmPage += sheet.getPrintSetup().getFitWidth() + sheet.getPrintSetup().getFitHeight();
                }

                sizeMD.setUnit(UnitType.SHEET);
                sizeMD.setSize(nmSheet);

                sizeMD.setUnit1(UnitType.PAGE);
                sizeMD.setSize1(nmPage);
            }
        } catch (FileNotFoundException ex) {
            LOG.warn("Invalid when reading file.", ex);
        } catch (IOException ex) {
            LOG.warn("Invalid when reading file.", ex);
        } catch (Exception e) {
            LOG.warn("Can not count file " + new File(filePath).getName(), e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    LOG.warn("Close the file input stream", ex);
                }
            }
        }

        return sizeMD;
    }

    /**
     * Count excel's number of sheet from input directory.
     * 
     * @param filePath path of Excel file
     * @return sheet
     */
    public int countSheet(String filePath) {
        FileInputStream fis = null;
        int sheet = 0;
        try {
            fis = new FileInputStream(filePath);
            if (CommonUtil.getExtension(filePath).equals("xls")) {
                HSSFWorkbook doc = new HSSFWorkbook(fis);
                sheet = doc.getNumberOfSheets();
            } else if (CommonUtil.getExtension(filePath).equals("xlsx")) {
                XSSFWorkbook doc = new XSSFWorkbook(fis);
                sheet = doc.getNumberOfSheets();
            }
        } catch (FileNotFoundException ex) {
            LOG.warn("Invalid when reading file.", ex);
        } catch (IOException ex) {
            LOG.warn("Invalid when reading file.", ex);
        } catch (Exception e) {
            LOG.warn("Can not count file " + new File(filePath).getName(), e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    LOG.warn("Close the file input stream", ex);
                }
            }
        }

        return sheet;
    }

    /**
     * @param filePath path of PowerPoint file
     * @return slide
     */
    public static int countSlide(String filePath) {
        FileInputStream fis = null;
        int slide = 0;
        try {
            fis = new FileInputStream(filePath);
            if (CommonUtil.getExtension(filePath).equals("ppt")) {
                HSLFSlideShow show = new HSLFSlideShow(fis);
                slide = show.getDocumentSummaryInformation().getSlideCount();
            } else if (CommonUtil.getExtension(filePath).equals("pptx")) {
                XSLFSlideShow show = new XSLFSlideShow(filePath);
                XSLFPowerPointExtractor ex = new XSLFPowerPointExtractor(show);
                slide = ex.getExtendedProperties().getUnderlyingProperties().getSlides();
            }
        } catch (FileNotFoundException ex) {
            LOG.warn("Invalid when reading file.", ex);
        } catch (IOException ex) {
            LOG.warn("Invalid when reading file.", ex);
        } catch (OpenXML4JException ex) {
            LOG.warn("Invalid when reading file.", ex);
        } catch (XmlException ex) {
            LOG.warn("Invalid when reading file.", ex);
        } catch (Exception e) {
            LOG.warn("Can not count file " + new File(filePath).getName(), e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    LOG.warn("Close the file input stream", ex);
                }
            }
        }
        return slide;
    }

    /**
     * getNmTC.
     * 
     * @param filePath path of UT file.
     * @return 0
     */
    public static int countNmTC(String filePath, String reportSheet, String rowText) {
        FileInputStream fis = null;
        int rowOfReport = 0, nmtc = -1;

        try {
            fis = new FileInputStream(filePath);
            int i = 0;
            if (CommonUtil.getExtension(filePath).equals("xls")) {
                try {
                    HSSFWorkbook doc = new HSSFWorkbook(fis);
                    HSSFSheet sheet = doc.getSheet(reportSheet);
                    for (i = 0; i <= sheet.getLastRowNum(); i++) {
                        if (sheet.getRow(i) == null) {
                            continue;
                        }

                        if (sheet.getRow(i).getCell(2) == null) {
                            continue;
                        }

                        if (sheet.getRow(i).getCell(2).getStringCellValue() == null) {
                            continue;
                        }

                        if (sheet.getRow(i).getCell(2).getStringCellValue().equals(rowText)) {
                            rowOfReport = i;
                        }
                    }
                    nmtc = (int) sheet.getRow(rowOfReport).getCell(9).getNumericCellValue();
                } catch (Exception e) {
                    LOG.warn("Can not count number of UTC in file: " + filePath, e);
                }

            } else if (CommonUtil.getExtension(filePath).equals("xlsx")) {
                try {
                    XSSFWorkbook doc = new XSSFWorkbook(fis);
                    XSSFSheet sheet = doc.getSheet(reportSheet);
                    for (i = 0; i <= sheet.getLastRowNum(); i++) {
                        if (sheet.getRow(i).getCell(2).getStringCellValue().equals(rowText)) {
                            rowOfReport = i;
                        }
                    }
                    nmtc = (int) sheet.getRow(rowOfReport).getCell(9).getNumericCellValue();
                } catch (Exception e) {
                    LOG.warn("Can not count number of UTC in file: " + filePath, e);
                }
            }
        } catch (FileNotFoundException ex) {
            LOG.warn("Invalid when reading file.", ex);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    LOG.warn("Close the file input stream", ex);
                }
            }
        }
        return nmtc;
    }

    /**
     * get file's name from filePath without extent.
     * 
     * @param filePath .
     * @return filename
     */
    public String getFileName(String filePath) {
        File file = new File(filePath);
        // get file's extent
        String ext = CommonUtil.getExtension(filePath);
        // get full file name with extent
        String fullname = file.getName();
        // get file name without extent
        String filename = fullname.replace("." + ext, "");
        return filename;
    }

    /**
     * [Give the description for method].
     * 
     * @param ext extension of file
     * @return
     */
    public static boolean isPresentFile(String ext) {
        return Arrays.binarySearch(PP_EXT_FILES, ext) >= 0;
    }

    /**
     * Check the input file Path is the UTC or UTR.
     * 
     * @param filePath path of file will be checked
     * @param requiredSheetNames Excel file must contains these sheets
     * @return true if the filePath is UTC or UTR
     * @throws IOException when error reading file
     */
    public static boolean isUTCFile(String filePath, String[] requiredSheetNames) {
        try {
            InputStream is = CommonUtil.loadResource(filePath);
            if (CommonUtil.getExtension(filePath).equals("xls")) {
                HSSFWorkbook wb = new HSSFWorkbook(is);

                // Check all required sheets
                for (String sheetName : requiredSheetNames) {
                    if (wb.getSheet(sheetName) == null) {
                        return false;
                    }
                }
            } else if (CommonUtil.getExtension(filePath).equals("xlsx")) {
                XSSFWorkbook xwb = new XSSFWorkbook(is);
                // Check all required sheets
                for (String sheetName : requiredSheetNames) {
                    if (xwb.getSheet(sheetName) == null) {
                        return false;
                    }
                }
            }
        } catch (IOException ioEx) {
            LOG.error("Could not check file '" + filePath + "'", ioEx);
            return false;
        }

        return true;
    }

    /**
     * [Give the description for method].
     * 
     * @param ext
     * @return
     */
    public static boolean isExcelFile(String ext) {
        return Arrays.binarySearch(EXCEL_EXT_FILES, ext) >= 0;
    }

    /**
     * [Give the description for method].
     * 
     * @param ext
     * @return
     */
    public static boolean isWordFile(String ext) {
        return Arrays.binarySearch(WORD_EXT_FILES, ext) >= 0;
    }

    public static boolean isJavaFile(String ext) {
        return Arrays.binarySearch(WORD_EXT_FILES, ext) >= 0;
    }

}
