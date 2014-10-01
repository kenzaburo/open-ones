package mks.dms.it;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateRequestTask01 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCreateRequestTask01() throws Exception {
    driver.get(baseUrl + "/decisionmaker/login;jsessionid=0DE0E3040758683BC0E8A4E93B098F4F");
    driver.findElement(By.linkText("Tạo mới yêu cầu")).click();
    driver.findElement(By.cssSelector("#createTask > div > #title")).clear();
    driver.findElement(By.cssSelector("#createTask > div > #title")).sendKeys("[Test] Công việc 01");
    driver.findElement(By.id("content")).clear();
    driver.findElement(By.id("content")).sendKeys("Việc 1\nViệc 2");
    driver.findElement(By.cssSelector("#createTask > div > #title")).clear();
    driver.findElement(By.cssSelector("#createTask > div > #title")).sendKeys("[Test] Công việc 01 (Tiêu đề + Nội dung)");
    driver.findElement(By.xpath("//input[@value='Lưu']")).click();
    driver.findElement(By.linkText("Thoát")).click();
    driver.findElement(By.id("gbqfq")).clear();
    driver.findElement(By.id("gbqfq")).sendKeys("spring pom.xml test org.openqa.selenium.WebDriverBackedSelenium");
    driver.findElement(By.id("gbqfb")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("admin");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("admin");
    driver.findElement(By.id("loginBtn")).click();
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
