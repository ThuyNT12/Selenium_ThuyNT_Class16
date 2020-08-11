package WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic15_PartIII_ExplicitWait {
	WebDriver driver;
	WebDriverWait ExplicitWait;
	//check push
		@BeforeClass
		public void beforeClass() {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/BrowserDriver/geckodriver.exe");
			driver = new FirefoxDriver();
			ExplicitWait = new WebDriverWait(driver, 10);
			driver.manage().window().maximize();
			
		}

		@Test
		public void TC01_Invisible() {
			driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
			//Step2: click on start button
			ExplicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button"))).click();
			//Step 3: Wait loading icon invisible
			ExplicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
			//Step 4: check the result is Hello World
			Assert.assertEquals(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText(), "Hello World!");
		}
		@Test
		public void TC02_Visible() {
			driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
			//Step2: click on start button
			ExplicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button"))).click();
			//Step 3: Wait Hello World visible
			ExplicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Hello World!']")));
			//Step 4: check the result is Hello World
			Assert.assertEquals(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText(), "Hello World!");
			
		}
		@Test
		public void TC03_ajaxLoading() {
			driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
			//Step 2: Wait for Date time Picker display
			ExplicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ctl00_ContentPlaceholder1_RadCalendar1_Top")));
			//Step 3:Verify: hien thi No Selected Dates to display khi chua chon 
			Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
			//Step 4: Choose current date
			ExplicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@title='Tuesday, August 11, 2020']"))).click();
			//Step 5: wait cho loading invisible
			ExplicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]//div[@class='raDiv']")));
			//Step 6: Wait the selected date duoc select
			ExplicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[contains(@class,'rcSelected')]//a[text()='11']")));
			//Step 7: validate selected date
			Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "Tuesday, August 11, 2020");
			
			
			
		}
		@AfterClass
		public void afterClass() {
			driver.quit();
		}
		
}