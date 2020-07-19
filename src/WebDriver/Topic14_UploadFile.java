package WebDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic14_UploadFile {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String source_Folder = System.getProperty("user.dir");
	String image_Name_01 = "HoaHong.jpg";
	String image_Name_02 = "HoaSung.jpg";
	String image_Name_03 = "HuongDuong.jpg";
	String image_01_path = source_Folder  + "\\Upload_File\\" + image_Name_01;
	String image_02_path = source_Folder  + "\\Upload_File\\" + image_Name_02;
	String image_03_path = source_Folder  + "\\Upload_File\\" + image_Name_03;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_UploadFile_Firefox()
	{
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		sleepInSecond(2);
		//Step 2: SendKey de upload nhieu file
		WebElement UploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		UploadFile.sendKeys(image_01_path);
		UploadFile.sendKeys(image_02_path);
		UploadFile.sendKeys(image_03_path);
		
		List<WebElement> UploadButon = driver.findElements(By.cssSelector("td .start"));
		for(WebElement start: UploadButon) {
			start.click();
			sleepInSecond(3);
		}
		//Step 3: Kiem tra 3 file upload len thanh cong
	//Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'" + image_Name_01 + "')]")).isDisplayed());
		Assert.assertTrue(isImageDisplayed("//img[contains(@src,'" + image_Name_01 + "')]"));
		Assert.assertTrue(isImageDisplayed("//img[contains(@src,'" + image_Name_02 + "')]"));
		Assert.assertTrue(isImageDisplayed("//img[contains(@src,'" + image_Name_03 + "')]"));
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + image_Name_01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + image_Name_02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + image_Name_03 + "']")).isDisplayed());
	}
	public boolean isImageDisplayed(String xpathLocator) {
		
		Boolean imagePresence = (Boolean) ((JavascriptExecutor) driver)
				.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth " + 
		"!= \"undefined\" && arguments[0].naturalWidth > 0", 
		driver.findElement(By.xpath(xpathLocator)));
		return imagePresence;
	}
	public void sleepInSecond (long time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
