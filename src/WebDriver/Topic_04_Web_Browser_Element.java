package WebDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_04_Web_Browser_Element {

	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
@Test
	
	public void TC_01_Web_Browser() throws InterruptedException
	{
	//nhap URL vao app can test
	driver.get("");
	//Lay link hien tai cua page hien tai
	String pageCurrent = driver.getCurrentUrl();
	//So sanh page actual voi expected
	Assert.assertEquals(pageCurrent, "");
	
	//Lay tat ca source: HTML/CSS/JS
	driver.getPageSource();
	
	//lay title cua page hien tai
	driver.getTitle();
	
	//Window
	driver.getWindowHandle();
	driver.getWindowHandles();
	//Web Drive wait
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//Cho page load xong
	driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	//cho JS load xong
	driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
	
	//Back ve trang truoc
	driver.navigate().back();
	//forward ve trang truoc
	driver.navigate().forward();
	//refresh page
	driver.navigate().refresh();
	
	//Close all tab
	driver.quit();
	
	//SwitchTo co 3 loai: alert, window,frame
	driver.switchTo().alert();
	driver.switchTo().window("");
	driver.switchTo().frame("");
	
	//close page hien tai
	driver.close();
	
	}
	
		
		@AfterClass
		public void afterClass() {
			driver.quit();
		}

		
}
