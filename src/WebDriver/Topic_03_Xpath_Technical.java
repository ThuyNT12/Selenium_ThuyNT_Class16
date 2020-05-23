package WebDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import java.util.Random;
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
	Assert.assertEquals(get_Expected_email, "This is a required field.");
	String get_Expected_pass = driver.findElement(By.id("advice-required-entry-pass")).getText();
	Assert.assertEquals(get_Expected_pass, "This is a required field.");
	
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
			Assert.assertEquals(get_Expected_pass1, "Please enter a valid email address. For example johndoe@domain.com.");
			
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
			Assert.assertEquals(get_Expected_pass2, "Please enter 6 or more characters without leading or trailing spaces.");		
			
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
		Assert.assertEquals(get_Expected_pass3, "Invalid login or password.");
		
	}
	@Test
	public void TC_05_LoginValid() throws InterruptedException
	{
		driver.get("http://live.demoguru99.com/index.php/");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automation_13@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123");
		driver.findElement(By.name("send")).click();	
		Thread.sleep(2000);
		String get_Expected_pass5 = driver.findElement(By.xpath("//div[@class='dashboard']//h1[text()='My Dashboard']")).getText();
		Assert.assertEquals(get_Expected_pass5, "MY DASHBOARD");
		String get_Expected_pass6 = driver.findElement(By.xpath("//div[@class='welcome-msg']//strong[text()='Hello, Automation Testing!']")).getText();
		Assert.assertEquals(get_Expected_pass6, "Hello, Automation Testing!");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box']//p[contains(text(),'Automation Testing')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box']//p[contains(.,'automation_13@gmail.com')]")).isDisplayed());
		
	}
		@Test
		public void TC_06_RegisterAnAccount() throws InterruptedException
		{
			driver.get("http://live.demoguru99.com/index.php/");
			Thread.sleep(2000);
			//step 2: click on my account
			driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
			Thread.sleep(2000);
			//Step 3: click on create new account
			driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
			//step 4: enter valid value
			
			driver.findElement(By.id("firstname")).sendKeys("Nguyen");
			driver.findElement(By.id("lastname")).sendKeys("Thuy");
			String email = "ThuyNT" + RandomEmail()+"@gmail.com";
			driver.findElement(By.id("email_address")).sendKeys(email);
			driver.findElement(By.id("password")).sendKeys("123456");
			driver.findElement(By.id("confirmation")).sendKeys("123456");
			//step 5: Click on register
			driver.findElement(By.xpath("//button[@title='Register']")).click();
			//step 6: verify message about register success
			String get_message = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
			Assert.assertEquals(get_message, "Thank you for registering with Main Website Store.");
			//step 7: verify information 
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box']//p[contains(text(),'Nguyen Thuy')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box']//p[contains(.,'" + email +"')]")).isDisplayed());
			
			//Step 8: click account
			driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
			//Click log out
			driver.findElement(By.xpath("//a[@title='Log Out']")).click();
			//step 9: wait to home page display
			Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
			
		}
		
		@AfterClass
		public void afterClass() {
			driver.quit();
		}

		public int RandomEmail() {
			Random rand = new Random();
			return rand.nextInt(999999);
			
		}
}
