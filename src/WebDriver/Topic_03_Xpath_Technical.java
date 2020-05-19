package WebDriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Technical {

	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
@Test
	
	public void TC_01_LoginWithEmptyEmailandPass() throws InterruptedException
	{
	driver.get("http://live.demoguru99.com/index.php/");
	Thread.sleep(2000);
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	driver.findElement(By.id("email")).sendKeys("");
	driver.findElement(By.id("pass")).sendKeys("");
	driver.findElement(By.name("send")).click();
	Thread.sleep(2000);
	String get_Expected_email = driver.findElement(By.id("advice-required-entry-email")).getText();
	assertEquals(get_Expected_email, "This is a required field.");
	String get_Expected_pass = driver.findElement(By.id("advice-required-entry-pass")).getText();
	assertEquals(get_Expected_pass, "This is a required field.");
	
	}
		@Test
	public void TC_02_LoginWithInvalidEmail() throws InterruptedException
	{
			driver.get("http://live.demoguru99.com/index.php/");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
			driver.findElement(By.id("email")).sendKeys("123456@123.123");
			driver.findElement(By.id("pass")).sendKeys("123456");
			driver.findElement(By.name("send")).click();	
			Thread.sleep(2000);
			String get_Expected_pass1 = driver.findElement(By.id("advice-validate-email-email")).getText();
			assertEquals(get_Expected_pass1, "Please enter a valid email address. For example johndoe@domain.com.");
			
	}
		@Test
	public void TC_03_LoginWithPassSmallThan6() throws InterruptedException
	{
			driver.get("http://live.demoguru99.com/index.php/");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
			driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
			driver.findElement(By.id("pass")).sendKeys("123");
			driver.findElement(By.name("send")).click();	
			Thread.sleep(2000);
			String get_Expected_pass2 = driver.findElement(By.id("advice-validate-password-pass")).getText();
			assertEquals(get_Expected_pass2, "Please enter 6 or more characters without leading or trailing spaces.");		
			
	}
		@Test
	public void TC_04_LoginWithIncorrectPass() throws InterruptedException
	{
		driver.get("http://live.demoguru99.com/index.php/");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123123");
		driver.findElement(By.name("send")).click();	
		Thread.sleep(2000);
		String get_Expected_pass3 = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
		assertEquals(get_Expected_pass3, "Invalid login or password.");
		
	}
		@AfterClass
		public void afterClass() {
			driver.quit();
		}

}
