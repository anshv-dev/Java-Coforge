package com.orhrm.tests;
 
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
 
public class BaseTest {
 
    protected static ExtentReports extent;
    private static ExtentSparkReporter spark;
 
    @BeforeSuite
    public void initReport() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/Automation_Report.html";
            spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("AutomationReport");
 
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
    }
 
    @AfterSuite
    public void tearDownReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
 