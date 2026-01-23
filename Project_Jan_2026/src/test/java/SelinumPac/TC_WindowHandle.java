package SelinumPac;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_WindowHandle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		https://letcode.in/window
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://letcode.in/window");
		String wh=driver.getWindowHandle();
		System.out.println(wh);
		driver.findElement(By.id("multi")).click();
		System.out.println("After clicking multi url:"+driver.getCurrentUrl());
		for(String cw:driver.getWindowHandles())
		{
			driver.switchTo().window(cw);
			System.out.println("After switching url:"+driver.getCurrentUrl());
		}
	}
}
