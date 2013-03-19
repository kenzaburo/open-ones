package oog.codechecker;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.AuditEvent;

public class CodeCheckerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCheck() {
      //Path to file config
      String xmlFilePath = "/Checks_v1.0.xml";
      //Path to source
      String srcPath = "/src";
      CodeChecker CodeChecker = new CodeChecker(xmlFilePath);
      Map<String, List<AuditEvent>> resultCheck = CodeChecker.check(srcPath);

      // Number of files have errors;
      assertEquals(19, resultCheck.size());
      
      
      List<AuditEvent> file1 = resultCheck.get("\\CommentDao.java");
      
      // Number of errors of file CommentDao.java
      assertEquals(9, file1.size());
      assertEquals("Missing package-info.java file.", file1.get(0).getMessage());
    
    }

    @Test
    public void testCheck_CodeChecker() {
      //Path to file config
      String xmlFilePath = "/Checks_v1.0.xml";
      //Path to source
      String srcPath = "E:\\4PSuite\\SourceCode\\ProductIntro\\src\\control";
      CodeChecker CodeChecker = new CodeChecker(xmlFilePath);
      Map<String, List<AuditEvent>> resultCheck = CodeChecker.check(srcPath);

      // Number of files have errors;
      assertEquals(4, resultCheck.size());
      
      
      List<AuditEvent> file1 = resultCheck.get("E:\\4PSuite\\SourceCode\\ProductIntro\\src\\control\\AddProductControl.java");
      
      // Number of errors of file CommentDao.java
      assertEquals(42, file1.size());
      assertEquals("Missing package-info.java file.", file1.get(0).getMessage());
    
    }
}
