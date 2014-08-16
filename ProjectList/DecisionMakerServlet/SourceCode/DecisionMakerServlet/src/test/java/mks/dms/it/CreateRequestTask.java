package mks.dms.it;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateRequestTask {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCreateRequestTask() throws Exception {
    driver.get(baseUrl + "/decisionmaker/login");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("admin");
    driver.findElement(By.id("loginBtn")).click();
    driver.findElement(By.linkText("Tạo yêu cầu")).click();
    driver.findElement(By.cssSelector("input.button")).click();
    driver.findElement(By.id("request.title")).clear();
    driver.findElement(By.id("request.title")).sendKeys("Công việc 01");
    driver.findElement(By.id("request.content")).clear();
    driver.findElement(By.id("request.content")).sendKeys("Test chức năng Tạo yêu cầu (loại Công việc) bằng Selenium.\nNgười thực hiện chính là người đăng nhập (admin)\nNgười quản lý là: manager\nChia sẻ: không\nCác trường khác: để trống.");
    new Select(driver.findElement(By.id("request.assignedId.id"))).selectByVisibleText("admin");
    new Select(driver.findElement(By.id("request.managerId.id"))).selectByVisibleText("manager");
    driver.findElement(By.cssSelector("input.button")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
