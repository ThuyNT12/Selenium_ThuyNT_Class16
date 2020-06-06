package WebDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_BaiTap_Element {
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
@Test
	
	public void TC_01_Check_isDisplay() throws InterruptedException
	{
	//step 1: truy cap trang
	driver.get("https://automationfc.github.io/basic-form/index.html");
	//step 2: ktra cac phan tu hien thi tren trang
	//Email
	
	if(elementIsDisplayed("//input[@id='email']")) {
		driver.findElement(By.id("email")).sendKeys("Automation Testing");
		Thread.sleep(2000);
	}
	//Education
	
	if(elementIsDisplayed("//textarea[@id='edu']")) {
		driver.findElement(By.id("edu")).sendKeys("Automation Testing");
		Thread.sleep(2000);
	}
	//under_18
	if(elementIsDisplayed("//input[@id='under_18']")) {
		driver.findElement(By.id("under_18")).click();
		Thread.sleep(2000);
	}
	}

@Test

public void TC_02_Check_isEnable() throws InterruptedException
{
	
	//step 1:
	driver.get("https://automationfc.github.io/basic-form/index.html");
	Thread.sleep(2000);
	//step 2: ktra phan tu enable
	Assert.assertTrue(elementIsEnable("//input[@id='email']"));
	Assert.assertTrue(elementIsEnable("//input[@id='under_18']"));
	Assert.assertTrue(elementIsEnable("//textarea[@id='edu']"));
	//step 3: ktra phan disable
	Assert.assertFalse(elementIsEnable("//input[@id='password']"));
	Assert.assertFalse(elementIsEnable("//input[@id='radio-disabled']"));
	Assert.assertFalse(elementIsEnable("//select[@id='job3']"));
}
@Test

public void TC_03_Check_isSelected() throws InterruptedException
{
	//step 1:
		driver.get("https://automationfc.github.io/basic-form/index.html");
	//step 2: click chon
		driver.findElement(By.id("under_18")).click();
		Thread.sleep(2000);
		Assert.assertTrue(elementIsSelected("//input[@id='under_18']"));
		driver.findElement(By.id("development")).click();
		Thread.sleep(2000);
		Assert.assertTrue(elementIsSelected("//input[@id='development']"));
	//click again
		driver.findElement(By.id("under_18")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("development")).click();
		Thread.sleep(2000);
		Assert.assertTrue(elementIsSelected("//input[@id='under_18']"));
		Assert.assertFalse(elementIsSelected("//input[@id='development']"));
}
public boolean elementIsDisplayed(String XpathValue) {
	WebElement element = driver.findElement(By.xpath(XpathValue));
	if (element.isDisplayed()) {
		System.out.println("element with Xpath = " + XpathValue + "is displayed");
		return true;
	}
	else {
		System.out.println("element with Xpath = " + XpathValue + "is not displayed");
		return false;
	}
	
}
		
public boolean elementIsEnable(String XpathValue) {
	WebElement element = driver.findElement(By.xpath(XpathValue));
	if (element.isEnabled()) {
		System.out.println("element with Xpath = " + XpathValue + "is Enabled");
		return true;
	}
	else {
		System.out.println("element with Xpath = " + XpathValue + "is disabled");
		return false;
	}
	
}

public boolean elementIsSelected(String XpathValue) {
	WebElement element = driver.findElement(By.xpath(XpathValue));
	if (element.isSelected()) {
		System.out.println("element with Xpath = " + XpathValue + "is selected");
		return true;
	}
	else {
		System.out.println("element with Xpath = " + XpathValue + "is unselected");
		return false;
	}
	
}
@AfterClass
		public void afterClass() {
			driver.quit();
		}
}
