package com.orhrm.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

public class TC2Testauto {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @BeforeMethod
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @AfterMethod
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void tC2() {
    driver.get("https://automationexercise.com/");
    driver.manage().window().setSize(new Dimension(1280, 672));
    driver.findElement(By.linkText("Contact us")).click();
    driver.findElement(By.name("name")).sendKeys("Ansh");
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).sendKeys("anhs@gmail.com");
    driver.findElement(By.name("subject")).click();
    driver.findElement(By.name("subject")).sendKeys("Requesgting someProduct");
    driver.findElement(By.id("message")).click();
    driver.findElement(By.id("message")).sendKeys("Hi, I m here .");
    driver.findElement(By.name("submit")).click();
    assertThat(driver.switchTo().alert().getText(), is("Press OK to proceed!"));
    driver.switchTo().alert().accept();
  }
  private void assertThat(String text, Object object) {
	// TODO Auto-generated method stub
	
}
  private Object is(String string) {
	// TODO Auto-generated method stub
	return null;
  }
}
