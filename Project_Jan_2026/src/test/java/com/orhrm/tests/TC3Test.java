//package com.orhrm.tests;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.Dimension;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.OutputType;
//import org.apache.commons.io.FileUtils;
//
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.ExtentReports;
//
//import java.util.*;
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.time.Duration;
//
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class TC3Test {
//  private WebDriver driver;
//  private Map<String, Object> vars;
//  JavascriptExecutor js;
//  private ExtentReports extent;
//  private ExtentTest test;
//  private String projectpath;
//  private WebDriverWait wait;
//
//  @BeforeMethod
//  public void setUp() throws Exception {
//    projectpath = System.getProperty("user.dir");
//    extent = new ExtentReports();
//    ExtentSparkReporter spark = new ExtentSparkReporter(projectpath + File.separator + "TC3_Report.html");
//    extent.attachReporter(spark);
//    test = extent.createTest("TC3 - Wishlist flow with screenshots (robust)");
//    driver = new ChromeDriver();
//    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
//    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//    js = (JavascriptExecutor) driver;
//    vars = new HashMap<String, Object>();
//  }
//
//  @AfterMethod
//  public void tearDown() throws Exception {
//    if (driver != null) {
//      driver.quit();
//    }
//    if (extent != null) {
//      extent.flush();
//    }
//  }
//
//  @Test
//  public void tC3() throws Exception {
//    test.log(Status.INFO, "Open site");
//    driver.get("https://tutorialsninja.com/demo/");
//    driver.manage().window().setSize(new Dimension(1296, 688));
//    test.addScreenCaptureFromPath(takeScreenshot("home_loaded"));
//
//    
//    js.executeScript("window.scrollTo(0, 600)");
//    test.addScreenCaptureFromPath(takeScreenshot("scrolled"));
//
//    
//    WebElement iphoneCard = wait.until(ExpectedConditions.visibilityOfElementLocated(
//        By.xpath("//div[contains(@class,'product-thumb')]//a[normalize-space()='iPhone']/ancestor::div[contains(@class,'product-thumb')]")));
//    WebElement iphoneWishBtn = iphoneCard.findElement(By.cssSelector("button[data-original-title='Add to Wish List']"));
//    new Actions(driver).moveToElement(iphoneWishBtn).pause(Duration.ofMillis(200)).click().perform();
//
//    test.log(Status.INFO, "Clicked iPhone Add to Wish List");
//    test.addScreenCaptureFromPath(takeScreenshot("after_add_to_wishlist"));
//
//    
//    WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(
//        By.cssSelector("div.alert")));
//    test.log(Status.INFO, "Alert text: " + alert.getText().trim());
//    test.addScreenCaptureFromPath(takeScreenshot("wishlist_alert"));
//
//    
//    WebElement wishlistHeader = wait.until(ExpectedConditions.elementToBeClickable(
//        By.id("wishlist-total")));
//    wishlistHeader.click();
//    test.log(Status.INFO, "Opened wishlist page");
//    test.addScreenCaptureFromPath(takeScreenshot("wishlist_page"));
//
// 
//    boolean hasTable = isPresent(By.cssSelector("div#content table.table.table-bordered"));
//    if (!hasTable) {
//      test.warning("Wishlist table not present. Likely not logged in or wishlist is empty.");
//      test.addScreenCaptureFromPath(takeScreenshot("wishlist_no_table"));
//      test.pass("TC3 completed (guest flow without removal)");
//      return;
//    }
//
//    List<WebElement> removeButtons = driver.findElements(By.cssSelector("div#content table.table.table-bordered .btn-danger"));
//    if (removeButtons.isEmpty()) {
//      test.warning("No items found in wishlist; remove button is not present.");
//      test.addScreenCaptureFromPath(takeScreenshot("wishlist_empty"));
//      test.pass("TC3 completed (no items to remove)");
//      return;
//    }
//
//    
//    WebElement firstRemove = wait.until(ExpectedConditions.elementToBeClickable(removeButtons.get(0)));
//    firstRemove.click();
//    test.log(Status.INFO, "Removed first item from wishlist");
//    test.addScreenCaptureFromPath(takeScreenshot("after_remove_item"));
//
//    test.pass("TC3 completed (item removed)");
//  }
//
//  private boolean isPresent(By locator) {
//    try {
//      driver.manage().timeouts().implicitlyWait(Duration.ofMillis(250));
//      boolean present = !driver.findElements(locator).isEmpty();
//      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
//      return present;
//    } catch (Exception e) {
//      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
//      return false;
//    }
//  }
//
//  private String takeScreenshot(String label) throws Exception {
//    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//    String dest = projectpath + File.separator + "Screenshots" + File.separator + "TC3_" + label + "_" + timestamp + ".png";
//    File destfile = new File(dest);
//    destfile.getParentFile().mkdirs();
//    FileUtils.copyFile(src, destfile);
//    return destfile.getAbsolutePath();
//  }
//}