package com.orhrm.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import java.util.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Robot;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.time.Duration;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentReports;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TC2Testauto {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  private ExtentReports extent;
  private ExtentTest test;
  private String projectpath;

  @BeforeMethod
  public void setUp() throws Exception {
    projectpath = System.getProperty("user.dir");
    extent = new ExtentReports();
    ExtentSparkReporter spark = new ExtentSparkReporter(projectpath + File.separator + "Contact_Report.html");
    extent.attachReporter(spark);
    test = extent.createTest("Verify the login");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }

  @AfterMethod
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
    if (extent != null) {
      extent.flush();
    }
  }

  @Test
  public void tC2() throws Exception {
    test.log(Status.INFO, "Navigating to site");
    driver.get("https://automationexercise.com/");
    driver.manage().window().setSize(new Dimension(1280, 672));

    test.log(Status.INFO, "Click Contact us");
    driver.findElement(By.linkText("Contact us")).click();

    test.log(Status.INFO, "Fill form fields");
    driver.findElement(By.name("name")).sendKeys("Ansh");
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).sendKeys("anhs@gmail.com");
    driver.findElement(By.name("subject")).click();
    driver.findElement(By.name("subject")).sendKeys("Requesgting someProduct");
    driver.findElement(By.id("message")).click();
    driver.findElement(By.id("message")).sendKeys("Hi, I m here .");

    String ss1 = takeScreenshot("before_submit");
    test.addScreenCaptureFromPath(ss1);

    test.log(Status.INFO, "Submit form");
    driver.findElement(By.name("submit")).click();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
    String alertText = null;
    try {
      Alert alert = wait.until(ExpectedConditions.alertIsPresent());
      String alertDesktop = takeDesktopScreenshot("alert_displayed_desktop");
      test.addScreenCaptureFromPath(alertDesktop);
      alertText = alert.getText();
      Assert.assertEquals(alertText, "Press OK to proceed!");
      test.log(Status.INFO, "Accept alert");
      alert.accept();
    } catch (NoAlertPresentException e) {
      test.fail("Alert not present when expected");
      throw e;
    } catch (org.openqa.selenium.TimeoutException e) {
      test.fail("Timed out waiting for alert");
      throw e;
    }

    String ss3 = takeScreenshot("after_alert_accept");
    test.addScreenCaptureFromPath(ss3);
    test.pass("Test completed");
  }

  private String takeScreenshot(String label) throws Exception {
    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    String dest = projectpath + File.separator + "Screenshots" + File.separator + "Screenshots_" + label + "_" + timestamp + ".png";
    File destfile = new File(dest);
    destfile.getParentFile().mkdirs();
    FileUtils.copyFile(src, destfile);
    return destfile.getAbsolutePath();
  }

  private String takeDesktopScreenshot(String label) throws Exception {
    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    String dest = projectpath + File.separator + "Screenshots" + File.separator + "Screenshots_" + label + "_" + timestamp + ".png";
    File destfile = new File(dest);
    destfile.getParentFile().mkdirs();
    Robot robot = new Robot();
    Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
    ImageIO.write(screenFullImage, "png", destfile);
    return destfile.getAbsolutePath();
  }
}
