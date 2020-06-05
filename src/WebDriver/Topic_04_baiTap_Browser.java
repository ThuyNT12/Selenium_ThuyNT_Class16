package WebDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_baiTap_Browser {
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_Verify_URL() throws InterruptedException
	{
	//step 1: truy cap trang
	driver.get("http://live.demoguru99.com/");
	Thread.sleep(2000);
	//step 2: click on My Account in footer
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	//step 3: verify URL cua login page
	Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
	Thread.sleep(2000);
	//step 4: click on create an account button
	driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	//step 5: verify URL cua register page
	Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	}
	@Test
public void TC_02_Verify_Title() throws InterruptedException
{
//step 1: truy cap trang
	driver.get("http://live.demoguru99.com/");
	Thread.sleep(2000);
	//step 2: click on My Account in footer
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	//step 3: verify title cua login page
	Assert.assertEquals(driver.getTitle(), "Customer Login");
	Thread.sleep(2000);
	//step 4: click on create an account button
	driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	//step 5: verify title cua register page
	Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
}
@Test
public void TC_03_Negative_Function() throws InterruptedException
{
	//step 1: truy cap trang
		driver.get("http://live.demoguru99.com/");
		Thread.sleep(2000);
		//step 2: click on My Account in footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		//step 3: click on create an account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		//step 4: verify URL cua register page
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		//step 5: Back lai trang login
		driver.navigate().back();
		//step 6: verify url cua login page
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		//step 7: Forward toi trang register page
		driver.navigate().forward();
		//step 8: verify title cua register page
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
}


@Test
public void TC_04_GetPageSourceCode() throws InterruptedException
{
	driver.get("http://live.demoguru99.com/");
	Thread.sleep(2000);
	//step 2: click on My Account in footer
	driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	//step 3: verify login page contains "login or create an account
	String loginPage = driver.getPageSource();
	Assert.assertTrue(loginPage.contains("Login or Create an Account"));
	//step 4 : click on create an account button
	driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	//step 5: verify register page contains "Create an Account"
	String registerPage = driver.getPageSource();
	Assert.assertTrue(registerPage.contains("Create an Account"));
}
		
		@AfterClass
		public void afterClass() {
			driver.quit();
		}
}
