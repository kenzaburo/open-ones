package rocky.poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import rocky.common.CommonUtil;
import rocky.common.Constant;

/**
 * Common utility to use POI. 
 * @author Thach.Le
 */
public class PoiUtil {
    final static Logger LOG = Logger.getLogger(PoiUtil.class);

    /**
     * [Give the description for method].
     * @param resource
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Workbook loadWorkbookByResource(String resource) throws FileNotFoundException, IOException {
        if (resource == null) {
            return null;
        }
        
        Workbook workbook = null;
        if (resource.endsWith(Constant.DOT_XLS)) {
            // Excel 2003
            workbook = new HSSFWorkbook(new POIFSFileSystem(CommonUtil.loadResource(resource)));
        } else if (resource.endsWith(Constant.DOT_XLSX)) {
            // Excel 2007
            workbook = new XSSFWorkbook(CommonUtil.loadResource(resource));
        }

        return workbook;
    }
    
    /**
     * [Give the description for method].
     * @param is
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static HSSFWorkbook loadFile(InputStream is) throws FileNotFoundException, IOException {
        HSSFWorkbook wk = null;
        POIFSFileSystem pfs = null;
        // create a Poi File from FileInputStream
        pfs = new POIFSFileSystem(is);
        // create workbook
        wk = new HSSFWorkbook(pfs);

        return wk;
    }

    public static XSSFWorkbook loadFileX(InputStream is) throws FileNotFoundException, IOException {
        XSSFWorkbook wk = null;
        // create workbook
        wk = new XSSFWorkbook(is);

        return wk;
    }

    /**
     * Remove sheet from Excel file.
     * @param filePath file path
     * @param sheetName name of sheet
     * @return false
     * filePath of workbook is not existed
     * sheetName is empty or not existed in the workbook
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public static boolean removeSheet(String filePath, String sheetName) throws FileNotFoundException, IOException {
        if (!CommonUtil.isNNandNB(sheetName)) {
            return false;
        }
        Workbook workbook = loadWorkbookByResource(filePath);
        
        if ((workbook == null)) {
            return false;
        }
        
        int len = workbook.getNumberOfSheets();
        
        Sheet sheet;
        for (int i = 0; i < len; i++) {
            sheet = workbook.getSheetAt(i);
            if (sheetName.equals(sheet.getSheetName())) {
                workbook.removeSheetAt(i);
                
                // Save file
                workbook.write(new FileOutputStream(filePath));
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Remove sheet by index.
     * @param filePath
     * @param idxSheet (start from 0)
     * @return false
     * file path is not existed
     * idxSheet is over index of sheet
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    public static boolean removeSheet(String filePath, int idxSheet) throws FileNotFoundException, IOException {
        Workbook workbook = loadWorkbookByResource(filePath);

        if ((workbook == null)) {
            return false;
        }

        if (idxSheet > workbook.getNumberOfSheets()) {
            return false;
        }
        workbook.removeSheetAt(idxSheet);

        // Save file
        workbook.write(new FileOutputStream(filePath));

        return true;
    }

//    public static HSSFCell setContent(HSSFRow row, int colIdx, Object value) {
//        HSSFCell cell = row.getCell(colIdx);
//        if (cell == null) {
//            cell = row.createCell(colIdx);
//        }
//
//        if (value instanceof Date) {
//            cell.setCellValue((Date) value);
//        } else if (value instanceof Double) {
//            cell.setCellValue(((Double) value).doubleValue());
//        } else if (value instanceof Integer) {
//            cell.setCellValue(((Integer) value).doubleValue());
//        } else if (value instanceof Boolean) {
//            cell.setCellValue((Boolean) value);
//        } else if (value instanceof Long) {
//            cell.setCellValue((Long) value);
//        } else if (value instanceof BigInteger) {
//            cell.setCellValue(((BigInteger) value).longValue());
//        } else {
//            cell.setCellValue(new HSSFRichTextString(value.toString()));
//        }
//
//        return cell;
//    }
    
    /**
     * Set content for col conIdx of row with value of Object.
     * @param row
     * @param colIdx
     * @param value
     * @return
     */
    public static Cell setContent(Row row, int colIdx, Object value) {
        if (value == null) {
            return null;
        }
        Cell cell = row.getCell(colIdx);
        if (cell == null) {
            cell = row.createCell(colIdx);
        }

        if (value instanceof Date) {
            cell.setCellValue((Date) value);
        } else if (value instanceof Double) {
            cell.setCellValue(((Double) value).doubleValue());
        } else if (value instanceof Integer) {
            cell.setCellValue(((Integer) value).doubleValue());
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof BigInteger) {
            cell.setCellValue(((BigInteger) value).longValue());
        } else {
            if (value == null) {
                // cell.setCellValue(Constant.BLANK); Skip
            } else {
                if (cell instanceof HSSFCell) {
                    cell.setCellValue(new HSSFRichTextString(value.toString()));
                } else {
                    cell.setCellValue(new XSSFRichTextString(value.toString()));
                }
            }
        }

        return cell;
    }
    
    public static Cell setContent(Row row, int colIdx, Object value, CellStyle style) {
        Cell cell = row.getCell(colIdx);
        if (cell == null) {
            cell = row.createCell(colIdx);
            if (style != null) {
                cell.setCellStyle(style);
            }
        }

        return setContent(row, colIdx, value);
    }
    
    public static Cell setContent(HSSFRow row, short colIdx, Object value) {
        return setContent(row, (int) colIdx, value);
    }
    
    /**
     * Set value for cell.
     * <b>
     * Excel 2007/2010
     * </b>
     * @param row
     * @param colIdx
     * @param value
     * @return
     * @deprecated use Cell setContent(Row row, int colIdx, Object value)
     */
    public static XSSFCell setContent(XSSFRow row, int colIdx, Object value) {
        XSSFCell cell = row.getCell(colIdx);
        if (cell == null) {
            cell = row.createCell(colIdx);
        }

        if (value instanceof Date) {
            cell.setCellValue((Date) value);
        } else if (value instanceof Double) {
            cell.setCellValue(((Double) value).doubleValue());
        } else if (value instanceof Integer) {
            cell.setCellValue(((Integer) value).doubleValue());
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof BigInteger) {
            cell.setCellValue(((BigInteger) value).longValue());
        } else {
            cell.setCellValue(new XSSFRichTextString(value.toString()));
        }

        return cell;

    }
    
    /**
     * Set value of object into cell(rowIdx, colIdx).
     * @param sheet
     * @param rowIdx row start from 0
     * @param colIdx col start from 0
     * @param value
     * @return
     */
    public static HSSFCell setContent(HSSFSheet sheet, int rowIdx, short colIdx, Object value) {
        return setContent(sheet, rowIdx, (int)colIdx, value);
    }
    
    /**
     * Set content to cell by address.
     * @param sheet
     * @param celAddr
     * @param value
     * @return
     * @see setContent(Sheet sheet, int row, int col, value)
     */
    public static Cell setContent(Sheet sheet, String celAddr, Object value) {
        CellReference cellRef = new CellReference(celAddr);
        return setContent(sheet, cellRef.getRow(), cellRef.getCol(), value);
    }
    /**
     * Set value of object into cell(rowIdx, colIdx).
     * @param sheet
     * @param rowIdx
     * @param colIdx
     * @param value
     * @return
     */
    public static Cell setContent(Sheet sheet, int rowIdx, int colIdx, Object value) {
        if (value == null) {
            return null;
        }
        Row row = sheet.getRow(rowIdx);
        if (row == null) {
            row = sheet.createRow(rowIdx);
        }
        
        Cell cell = row.getCell(colIdx);
        if (cell == null) {
            cell = row.createCell(colIdx);
        }

        if (value instanceof Date) {
            cell.setCellValue((Date) value);
        } else if (value instanceof Double) {
            cell.setCellValue(((Double) value).doubleValue());
        } else if (value instanceof Integer) {
            cell.setCellValue(((Integer) value).doubleValue());
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof BigInteger) {
            cell.setCellValue(((BigInteger) value).longValue());
        } else {
            if (cell instanceof HSSFCell) {
                cell.setCellValue(new HSSFRichTextString(value.toString()));
            } else if (cell instanceof XSSFCell) {
                cell.setCellValue(new XSSFRichTextString(value.toString()));
            } else {
                cell.setCellValue(value.toString());
            }
        }

        return cell;        
    }
    
    /**
     * [Give the description for method].
     * @param sheet
     * @param rowIdx
     * @param colIdx
     * @param value
     * @return
     * @deprecated use Cell setContent(Sheet sheet, int rowIdx, int colIdx, Object value)
     */
    public static HSSFCell setContent(HSSFSheet sheet, int rowIdx, int colIdx, Object value) {
        HSSFRow row = sheet.getRow(rowIdx);
        if (row == null) {
            row = sheet.createRow(rowIdx);
        }
        HSSFCell cell = row.getCell(colIdx);
        if (cell == null) {
            cell = row.createCell(colIdx);
        }

        if (value instanceof Date) {
            cell.setCellValue((Date) value);
        } else if (value instanceof Double) {
            cell.setCellValue(((Double) value).doubleValue());
        } else if (value instanceof Integer) {
            cell.setCellValue(((Integer) value).doubleValue());
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof BigInteger) {
            cell.setCellValue(((BigInteger) value).longValue());
        } else {
            cell.setCellValue(new HSSFRichTextString(value.toString()));
        }

        return cell;
    }

    public static Cell setContent(SXSSFSheet sheet, int rowIdx, int colIdx, Object value, CellStyle style) {
        Row row = sheet.getRow(rowIdx);
        if (row == null) {
            row = sheet.createRow(rowIdx);
        }
        return setContent(row, colIdx, value, style);
    }
    
    public static Cell setContent(SXSSFSheet sheet, int rowIdx, int colIdx, Object value) {
        Row row = sheet.getRow(rowIdx);
        if (row == null) {
            row = sheet.createRow(rowIdx);
        }
        return setContent(row, colIdx, value);
    }

    
    /**
     * [Give the description for method].
     * @param sheet
     * @param rowIdx
     * @param colIdx
     * @param value
     * @return
     * @deprecated use Cell setContent(Sheet sheet, int rowIdx, int colIdx, Object value)
     */
    public static XSSFCell setContent(XSSFSheet sheet, int rowIdx, int colIdx, Object value) {
        XSSFRow row = sheet.getRow(rowIdx);
        if (row == null) {
            row = sheet.createRow(rowIdx);
        }
        return setContent(row, colIdx, value);
    }

    @Deprecated
    public static Object getValue(HSSFSheet sheet, int rowIdx, short colIdx) {
        HSSFRow row = sheet.getRow(rowIdx);
        Object retValue;
        if (row == null) {
            row = sheet.createRow(rowIdx);
        }

        return getValue(row, colIdx);
    }

    public static Object getValue(HSSFSheet sheet, int rowIdx, int colIdx) {
        HSSFRow row = sheet.getRow(rowIdx);
        Object retValue;
        if (row == null) {
            row = sheet.createRow(rowIdx);
        }

        return getValue(row, colIdx);
    }

    public static Object getValue(Sheet sheet, String cellAddr) {
        CellReference cellRef = new CellReference(cellAddr);
        Cell cell = sheet.getRow(cellRef.getRow()).getCell(cellRef.getCol());

        return getValue(cell);
    }

    
    /**
     * Get value of cell.
     * @param sheet
     * @param cellAddr A1, C27 style cell references
     * @return
     */
    public static Object getValue(HSSFSheet sheet, String cellAddr) {
        CellReference cellRef = new CellReference(cellAddr);
        HSSFCell cell = sheet.getRow(cellRef.getRow()).getCell(cellRef.getCol());
        return getValue(cell);
    }

    public static Object getValue(XSSFSheet sheet, String cellAddr) {
        CellReference cellRef = new CellReference(cellAddr);
        XSSFCell cell = sheet.getRow(cellRef.getRow()).getCell(cellRef.getCol());
        return getValue(cell);
    }

    /**
     * Get value of given column of the row.
     * @param row
     * @param colIdx
     * @return Warning: for date format cell, the Double value can be returned.
     */
//    public static Object getValue(HSSFRow row, int colIdx) {
//        Object retValue = null;
//        HSSFCell cell = row.getCell(colIdx);
//
//        return getValue(cell);
//    }

    /**
     * [Give the description for method].
     * @param row
     * @param colIdx
     * @return
     */
    public static Object getValue(Row row, int colIdx) {
        Object retValue = null;
        Cell cell = row.getCell(colIdx);

        return getValue(cell);
    }
    
//    public static Object getValue(XSSFRow row, int colIdx) {
//        Object retValue = null;
//        XSSFCell cell = row.getCell(colIdx);
//
//        return getValue(cell);
//    }

//    @Deprecated
//    public static Object getValue(HSSFRow row, short colIdx) {
//        Object retValue = null;
//        HSSFCell cell = row.getCell(colIdx);
//        return getValue(cell);
//    }
    public static Date getDateValue(Sheet sheet, String cellAddr) {
        CellReference cellRef = new CellReference(cellAddr);
        Cell cell = sheet.getRow(cellRef.getRow()).getCell(cellRef.getCol());

        return cell.getDateCellValue();
    }

    public static Object getValue(Cell cell) {
        Object retValue = null;
        String strVal;

        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_FORMULA :
                    // Try to get double value
                    try {
                        retValue = cell.getNumericCellValue();
                        break;
                    } catch (java.lang.IllegalStateException nfex) {
                        // Not a double value
                        //nfex.printStackTrace();
                        retValue = cell.getRichStringCellValue().toString();
                    }
                    // Try to get Date value
                    try {
                        retValue = cell.getDateCellValue();
                    } catch (Exception ex) {
                        retValue = cell.getRichStringCellValue().toString();
                    }
                    break;
                case Cell.CELL_TYPE_NUMERIC :
                    retValue = cell.getNumericCellValue();
                    break;
                case Cell.CELL_TYPE_STRING :
                    retValue = cell.getRichStringCellValue().toString();
                    break;
                case Cell.CELL_TYPE_BOOLEAN :
                    retValue = cell.getBooleanCellValue();
                    break;

                case Cell.CELL_TYPE_ERROR :
                    LOG.debug("Error (" + cell.getRowIndex() + "," + cell.getColumnIndex() + ")"
                            + cell.getErrorCellValue());
                    retValue = "#N/A";
                    break;
                default :
                    try {
                        retValue = cell.getDateCellValue();
                    } catch (Exception ex) {
                        retValue = cell.getRichStringCellValue().toString();
                    }
            }
        }

        return retValue;
    }

//    private static Object getValue(XSSFCell cell) {
//        Object retValue = null;
//        String strVal;
//
//        if (cell != null) {
//
//            switch (cell.getCellType()) {
//                case HSSFCell.CELL_TYPE_FORMULA :
//                    // Try to get double value
//                    try {
//                        retValue = cell.getNumericCellValue();
//                        break;
//                    } catch (java.lang.IllegalStateException nfex) {
//                        // Not a double value
//                        //nfex.printStackTrace();
//                        retValue = cell.getRichStringCellValue().toString();
//                    }
//                    // Try to get Date value
//                    try {
//                        retValue = cell.getDateCellValue();
//                    } catch (Exception ex) {
//                        retValue = cell.getRichStringCellValue().toString();
//                    }
//                    break;
//                case HSSFCell.CELL_TYPE_NUMERIC :
//                    retValue = cell.getNumericCellValue();
//                    break;
//                case HSSFCell.CELL_TYPE_STRING :
//                    retValue = cell.getRichStringCellValue().toString();
//                    break;
//                case HSSFCell.CELL_TYPE_BOOLEAN :
//                    retValue = cell.getBooleanCellValue();
//                    break;
//
//                case HSSFCell.CELL_TYPE_ERROR :
//                    LOG.debug("Error (" + cell.getRowIndex() + "," + cell.getColumnIndex() + ")"
//                            + cell.getErrorCellValue());
//                    retValue = "#N/A";
//                    break;
//                default :
//                    try {
//                        retValue = cell.getDateCellValue();
//                    } catch (Exception ex) {
//                        retValue = cell.getRichStringCellValue().toString();
//                    }
//            }
//        }
//
//        return retValue;
//    }

    public static void writeExcelFile(Workbook wb, String filename) throws IOException {
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(filename);
            wb.write(fileOut);
        } finally {
            fileOut.close();
        }
    }
}
