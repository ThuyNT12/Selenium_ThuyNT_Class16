package WebDriver;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	JavascriptExecutor jsExecutor;
	WebElement element;
	String source_Folder = System.getProperty("user.dir");
	String image_Name_01 = "HoaHong.jpg";
	String image_Name_02 = "HoaSung.jpg";
	String image_Name_03 = "HuongDuong.jpg";
	String image_01_path = source_Folder  + "\\Upload_File\\" + image_Name_01;
	String image_02_path = source_Folder  + "\\Upload_File\\" + image_Name_02;
	String image_03_path = source_Folder  + "\\Upload_File\\" + image_Name_03;
	
		@BeforeClass
		public void beforeClass() {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/BrowserDriver/geckodriver.exe");
			driver = new FirefoxDriver();
			ExplicitWait = new WebDriverWait(driver, 30);
			jsExecutor = (JavascriptExecutor) driver;
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
		@Test
		public void TC04_uploadFiles() {
			driver.get("https://gofile.io/?t=uploadFiles");
			//Wait Update button visible
			ExplicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("btnChooseFiles")));
			//Step 2: Upload file
			String parentID = driver.getWindowHandle();
			WebElement UploadFile = driver.findElement(By.xpath("//input[@type='file']"));
			UploadFile.sendKeys(image_01_path + "\n" + image_02_path + "\n" + image_03_path);

			//Wait Upload button visible and click
			scrollToElement("//button[@id='btnUpload']");
			ExplicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btnUpload']"))).click();
			//Click upload button 
			//driver.findElement(By.cssSelector("button#btnUpload")).click();
		
			//Wait the process bar invisible
			ExplicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("progressBarData")));
			//Step 4: Click OK button
			ExplicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Ok']"))).click();
			
			
			//Click on link upload file
			ExplicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='link']"))).click();
			//driver.findElement(By.xpath("//a[@id='link']")).click();
			//Switch to link
			witchWindowByID(parentID);
			ExplicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//td[text()='" + image_Name_01 + "']/following-sibling::td[@class]//i[contains(@class,'download')]")));
			//Verify icon download display
			//driver.findElement(By.xpath("//td[text()='HoaHong.jpg']/following-sibling::td[@class]//i[contains(@class,'download')]"));
			Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + image_Name_01 + "']/following-sibling::td[@class]//i[contains(@class,'download')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + image_Name_02 + "']/following-sibling::td[@class]//i[contains(@class,'download')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + image_Name_03 + "']/following-sibling::td[@class]//i[contains(@class,'download')]")).isDisplayed());
		
			//Verify icon Play display
			Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + image_Name_01 + "']/following-sibling::td[@class]//i[contains(@class,'play')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + image_Name_02 + "']/following-sibling::td[@class]//i[contains(@class,'play')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + image_Name_03 + "']/following-sibling::td[@class]//i[contains(@class,'play')]")).isDisplayed());
		}
		public void witchWindowByID(String parentID) {
			Set<String> allWindows = driver.getWindowHandles();
			for (String runWindow: allWindows) {
				if(!runWindow.equals(parentID)) {
					driver.switchTo().window(runWindow);
					break;
				}
			}
					
		}
		public boolean isImageDisplayed(String xpathLocator) {
			
			Boolean imagePresence = (Boolean) ((JavascriptExecutor) driver)
					.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth " + 
			"!= \"undefined\" && arguments[0].naturalWidth > 0", 
			driver.findElement(By.xpath(xpathLocator)));
			return imagePresence;
		}
		public void scrollToElement(String locator) {
			 element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		}
		public void clickToElementByJS(String locator) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].click();", element);
		}
		@AfterClass
		public void afterClass() {
			driver.quit();
		}
		
}