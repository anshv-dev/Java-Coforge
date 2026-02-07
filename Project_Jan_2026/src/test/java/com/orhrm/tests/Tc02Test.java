package com.orhrm.tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Tc02Test extends BaseTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private ExtentTest test;
    private final String projectPath = System.getProperty("user.dir");

    @BeforeMethod
    public void setUp() {
        // Create a node in the shared consolidated report for this test method
        test = extent.createTest(this.getClass().getSimpleName() + " - tc02");

        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(12));
        driver.manage().window().maximize();
    }

    @Test
    public void tc02() {
        test.info("Launching website");
        driver.get("https://automationexercise.com/");
        driver.manage().window().setSize(new Dimension(1296, 688));

        WebElement products = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(" Products")));
        products.click();
        test.pass("Clicked Products");

        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_product")));
        searchBox.clear();
        searchBox.sendKeys("jeans");
        test.pass("Entered jeans in search box");

        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit_search")));
        submit.click();
        test.pass("Clicked Submit");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".features_items")));

        WebElement product = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(".col-sm-4:nth-child(3) .single-products")));
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", product);
        js.executeScript("arguments[0].click();", product);
        test.pass("Selected a product");

        WebElement addToCart = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(".col-sm-4:nth-child(3) .product-overlay .btn")));
        js.executeScript("arguments[0].click();", addToCart);
        test.pass("Clicked Add to Cart");

        WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//u[normalize-space()='View Cart']")));
        viewCart.click();
        test.pass("Clicked View Cart");

        test.pass("TEST PASSED: tc02 completed successfully!");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        String screenshotPath = takeScreenshot(result.getName() + "_" + getStatus(result));

        try {
            if (screenshotPath != null) {
                test.addScreenCaptureFromPath(screenshotPath);
            } else {
                test.warning("Screenshot path is null, screenshot not attached.");
            }
        } catch (Exception e) {
            test.warning("Unable to attach screenshot: " + e.getMessage());
        } finally {
            if (result.getStatus() == ITestResult.SUCCESS) {
                test.pass("Test Passed");
            } else if (result.getStatus() == ITestResult.FAILURE) {
                test.fail("Test Failed: " + (result.getThrowable() != null ? result.getThrowable() : "No stacktrace"));
            } else {
                test.skip("Test Skipped");
            }
            if (driver != null) driver.quit();
            // Do NOT flush here; BaseTest @AfterSuite will flush the consolidated report
        }
    }

    private String takeScreenshot(String fileName) {
        String folderPath = projectPath + File.separator + "target" + File.separator + "Screenshots";
        new File(folderPath).mkdirs();

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String filePath = folderPath + File.separator + fileName + "_" + timestamp + ".png";
            File destFile = new File(filePath);
            FileUtils.copyFile(src, destFile);
            System.out.println("📸 Screenshot saved at: " + filePath);
            return filePath;
        } catch (Exception e) {
            System.out.println("⚠ Screenshot failed: " + e.getMessage());
            return null;
        }
    }

    private String getStatus(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) return "PASS";
        if (result.getStatus() == ITestResult.FAILURE) return "FAIL";
        if (result.getStatus() == ITestResult.SKIP) return "SKIP";
        return "UNKNOWN";
    }
}