package SelinumPac;
 
import java.util.List;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
public class Lab5_4 {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
 
        driver.get("https://tutorialsninja.com/demo");
        driver.manage().window().maximize();
 
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();
 
        driver.findElement(By.id("input-firstname")).sendKeys("Ansh");
        driver.findElement(By.id("input-lastname")).sendKeys("Verma");
        driver.findElement(By.id("input-email")).sendKeys("ansh11v@gmail.com");
        driver.findElement(By.id("input-telephone")).sendKeys("8279596843");
        driver.findElement(By.id("input-password")).sendKeys("Ab@c1ef12");
        driver.findElement(By.id("input-confirm")).sendKeys("Ab@c1ef12");
        
        boolean subs=driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/form/fieldset[3]/div[1]/div[1]/label/input[@value='1']")).isSelected();
		if(subs)
		{
			System.out.println("yes is selected");
		}else {
			driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/form/fieldset[3]/div[1]/div[1]/label/input[@value='1']")).click();
		}
 
		driver.findElement(By.name("agree")).click();
		
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();
        
        
 
        List<WebElement> errors = driver.findElements(By.cssSelector(".text-danger"));
        for (WebElement e : errors) {
            System.out.println("Error: " + e.getText());
        }
        
//        driver.quit();
	}
 
}