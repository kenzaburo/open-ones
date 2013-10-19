package rocky.poi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import rocky.common.CommonUtil;
import junit.framework.TestCase;

public class PoiUtilTest extends TestCase {
    XSSFWorkbook workbook = null;
    protected void setUp() throws Exception {
        InputStream fileTemplate = CommonUtil.loadResource("/SampleExcel.xlsx");
        workbook = new XSSFWorkbook(fileTemplate);
    }

    protected void tearDown() throws Exception {
        
    }

    public void testGetValueHSSFSheetString() {
        XSSFSheet sheet = workbook.getSheet("sheet1");
        assertEquals("Exist data 1", PoiUtil.getValue(sheet, "B2"));
    }

    /**
     * 
     * Check types of data.
     */
    public void testGetManyTypesOfData() {
        XSSFSheet sheet = workbook.getSheet("Data");
        assertEquals(1.0, PoiUtil.getValue(sheet, "B1"));
        assertEquals(0.25, PoiUtil.getValue(sheet, "B2"));
        assertEquals(0.6, PoiUtil.getValue(sheet, "B3"));
        assertEquals("Test", PoiUtil.getValue(sheet, "B4"));
        assertEquals(0.1, PoiUtil.getValue(sheet, "B5"));
        assertEquals("AB", PoiUtil.getValue(sheet, "B6"));
        
        //Date date1 = (Date) PoiUtil.getValue(sheet, "B7");
        Date date1 = PoiUtil.getDateValue(sheet, "B7");
        Date expDate = CommonUtil.parse("31-1-2012", "dd-MM-yyyy");
        assertEquals(expDate, date1);

        date1 = PoiUtil.getDateValue(sheet, "B8");
        expDate = CommonUtil.parse("19-10-2013", "dd-MM-yyyy");
        assertEquals(expDate, date1);

        date1 = PoiUtil.getDateValue(sheet, "B9");
        expDate = CommonUtil.parse("20-10-2013", "dd-MM-yyyy");
        assertEquals(expDate, date1);        
    }
    
    public void testSetContentByAddress() {
        try {
            Workbook wb = PoiUtil.loadWorkbookByResource("/SampleExcel.xls");
            Sheet sheet = wb.getSheetAt(2);
            PoiUtil.setContent(sheet, "C1", "1");
            
            PoiUtil.setContent(sheet, "C2", "1.1");
            
            PoiUtil.setContent(sheet, "C3", "0.0");
            
            PoiUtil.writeExcelFile(wb, "SampleExcel_Output.xls");
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            
        } catch (IOException ex) {
            ex.printStackTrace();
            fail(ex.getMessage());
        }
    }
}
