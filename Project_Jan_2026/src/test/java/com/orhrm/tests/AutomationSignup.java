package com.orhrm.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;


public class AutomationSignup extends BaseTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;

  private ExtentTest test;
  private String projectPath;
  private String lastScreenshotPath;

  @BeforeMethod
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<>();
    projectPath = System.getProperty("user.dir");

    // Create a per-method test node in the shared report
    test = extent.createTest(this.getClass().getSimpleName() + " - tC1");
  }

  @AfterMethod
  public void tearDown() throws Exception {
    try {
      // Always capture a final screenshot for reference
      File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
      File dir = new File(projectPath + File.separator + "target" + File.separator + "Screenshots");
      if (!dir.exists()) {
        dir.mkdirs();
      }
      String dest = dir.getAbsolutePath() + File.separator + "AutomationSignup_" + timestamp + ".png";
      File destfile = new File(dest);
      FileUtils.copyFile(src, destfile);
      lastScreenshotPath = dest;

      if (test != null) {
        test.addScreenCaptureFromPath(lastScreenshotPath);
      }
    } catch (Exception e) {
      if (test != null) {
        test.log(Status.WARNING, "Screenshot capture failed: " + e.getMessage());
      }
    } finally {
      if (driver != null) {
        driver.quit();
      }
    }
  }

  @Test
  public void tC1() {
    try {
      test.log(Status.INFO, "Navigating to login page");
      driver.get("https://automationexercise.com/login");
      driver.manage().window().setSize(new Dimension(1050, 652));

      test.log(Status.INFO, "Entering name");
      driver.findElement(By.name("name")).click();
      driver.findElement(By.name("name")).sendKeys("Shivam");

      test.log(Status.INFO, "Entering email");
      driver.findElement(By.cssSelector(".signup-form input:nth-child(3)")).click();
      driver.findElement(By.cssSelector(".signup-form input:nth-child(3)")).sendKeys("sarshivam16@gmail.com");

      test.log(Status.INFO, "Submitting signup");
      driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();

      test.log(Status.INFO, "Filling account details");
      driver.findElement(By.id("id_gender1")).click();
      driver.findElement(By.id("password")).click();
      driver.findElement(By.id("password")).sendKeys("Shivam@123");

      driver.findElement(By.id("days")).click();
      {
        WebElement dropdown = driver.findElement(By.id("days"));
        dropdown.findElement(By.xpath("//option[. = '1']")).click();
      }
      driver.findElement(By.id("months")).click();
      {
        WebElement dropdown = driver.findElement(By.id("months"));
        dropdown.findElement(By.xpath("//option[. = 'January']")).click();
      }
      driver.findElement(By.id("years")).click();
      {
        WebElement dropdown = driver.findElement(By.id("years"));
        dropdown.findElement(By.xpath("//option[. = '2000']")).click();
      }

      // FIX: '&gt;' was HTML entity; CSS selector should use '>'
      driver.findElement(By.cssSelector(".checkbox:nth-child(7) > label")).click();

      test.log(Status.INFO, "Filling address details");
      driver.findElement(By.id("first_name")).click();
      driver.findElement(By.id("first_name")).sendKeys("Shiavm");
      driver.findElement(By.id("last_name")).sendKeys("Saroj");
      driver.findElement(By.id("company")).click();
      driver.findElement(By.id("company")).sendKeys("coforge");
      driver.findElement(By.id("address1")).click();
      driver.findElement(By.id("address1")).sendKeys("coforge");
      driver.findElement(By.id("address2")).click();
      driver.findElement(By.id("address2")).sendKeys("coforge");
      driver.findElement(By.id("state")).click();
      driver.findElement(By.id("state")).sendKeys("up");
      driver.findElement(By.id("city")).click();
      driver.findElement(By.id("city")).sendKeys("noida");
      driver.findElement(By.id("zipcode")).click();
      driver.findElement(By.id("zipcode")).sendKeys("222222");
      driver.findElement(By.id("mobile_number")).click();
      driver.findElement(By.id("mobile_number")).sendKeys("9090909090");

      test.log(Status.INFO, "Creating account");
      driver.findElement(By.cssSelector(".btn:nth-child(22)")).click();

      test.log(Status.PASS, "Signup flow executed");
    } catch (Exception e) {
      if (test != null) {
        test.log(Status.FAIL, "Test failed: " + e.getMessage());
      }
      throw e;
    }
  }
}
