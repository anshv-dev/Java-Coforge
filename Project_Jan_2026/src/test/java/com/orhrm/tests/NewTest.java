package com.orhrm.tests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;

public class NewTest extends BaseTest {
  private AndroidDriver driver;            // also assigned to BaseTest's 'driver' for screenshots if needed
  private ExtentTest test;
  private String projectpath;

  @BeforeMethod
  public void beforeMethod() throws Exception {
    projectpath = System.getProperty("user.dir");
    // Create a test node in the shared Extent report
    test = extent.createTest(this.getClass().getSimpleName() + " - f");

    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("platformName", "Android");
    caps.setCapability("appium:automationName", "UiAutomator2");
    caps.setCapability("appium:udid", "emulator-5554");
    caps.setCapability("appium:deviceName", "Android Emulator");
    caps.setCapability("appium:autoGrantPermissions", true);
    caps.setCapability("appium:noReset", true);
    caps.setCapability("appium:appPackage", "com.google.android.youtube");
    caps.setCapability("appium:appActivity", "com.google.android.apps.youtube.app.WatchWhileActivity");
    caps.setCapability("appium:appWaitActivity", "com.google.android.apps.youtube.app.*");

    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

    // Optional: if you want BaseTest's failure-screenshot to work automatically,
    // you can also set BaseTest.driver to this same instance:
    this.driver = driver;
  }

  @AfterMethod
  public void afterMethod() throws Exception {
    if (driver != null) {
      driver.quit();
    }
    // DO NOT flush here; BaseTest @AfterSuite will flush the single consolidated report
  }

  @Test
  public void f() throws Exception {
    try {
      test.log(Status.INFO, "YouTube App started");
      test.log(Status.INFO, "Current Activity: " + driver.currentActivity());
      test.addScreenCaptureFromPath(takeScreenshot("app_launched"));

      By searchXpath = By.xpath("//android.view.ViewGroup[@content-desc='Search YouTube']");

      boolean found = false;
      WebElement searchEl = null;

      long end = System.currentTimeMillis() + 40000;
      while (System.currentTimeMillis() < end) {
        List<WebElement> list = driver.findElements(searchXpath);
        if (!list.isEmpty()) {
          searchEl = list.get(0);
          found = true;
          break;
        }
        Thread.sleep(5000);
      }

      if (found) {
        test.log(Status.PASS, "Search element IS PRESENT. Displayed: " + searchEl.isDisplayed());
        test.addScreenCaptureFromPath(takeScreenshot("search_present"));
      } else {
        test.log(Status.WARNING, "Search element NOT PRESENT after waiting. Activity: " + driver.currentActivity());
        test.addScreenCaptureFromPath(takeScreenshot("search_absent"));
      }

      Thread.sleep(1000);
      test.pass("Test completed");
    } catch (Exception e) {
      try {
        test.addScreenCaptureFromPath(takeScreenshot("failure"));
      } catch (Exception ignore) {}
      test.fail("Test failed: " + e.getMessage());
      throw e;
    }
  }

  private String takeScreenshot(String label) throws Exception {
    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String dest = projectpath + File.separator + "target" + File.separator + "Screenshots"
                  + File.separator + "Appium_" + label + "_" + timestamp + ".png";
    File destfile = new File(dest);
    destfile.getParentFile().mkdirs();
    FileUtils.copyFile(src, destfile);
    return destfile.getAbsolutePath();
  }
}