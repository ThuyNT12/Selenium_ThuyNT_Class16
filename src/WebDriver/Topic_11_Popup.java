package WebDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Popup {

	WebDriver driver;
	Actions action;
	boolean status;
	//check push
		@BeforeClass
		public void beforeClass() {
			driver = new FirefoxDriver();
			action = new Actions(driver);
				
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}

		@Test
		public void TC_01_FixPopup() {
			//Step 1:
			driver.get("https://www.zingpoll.com/");
			//Step 2: Click "Sign in"
			driver.findElement(By.id("Loginform")).click();
			//Step 3: kiem tra login form hien thi
			status = driver.findElement(By.id("Login")).isDisplayed();
			Assert.assertTrue(status);
			//Step 4: Close popup
			driver.findElement(By.xpath("//div[@id='Login']//button[@class='close']")).click();
			status = driver.findElement(By.id("Login")).isDisplayed();
			Assert.assertFalse(status);
		}
		@Test
		public void TC_02_Random_Popup() {
			//Step 1:
			driver.get("https://blog.testproject.io/");
			sleepInSecond(10);
			//step 2: ktra popup trong 2 truong hop
			//co hien thi -> dong popup -> sang step 3
			//khong hien thi -> sang step 3
			if(driver.findElement(By.xpath("//div[@class='mailch-wrap rocket-lazyload']")).isDisplayed()) {
				Assert.assertTrue(driver.findElement(By.xpath("//img[@class='right-arr lazyloaded']")).isDisplayed());
				driver.findElement(By.id("close-mailch")).click();
				sleepInSecond(2);
			}
			
			//step 3: search textbox voi tu khoa: selenium
			driver.findElement(By.xpath("//section[@id='search-2']//input[@class='search-field']")).sendKeys("Selenium");
			sleepInSecond(2);
			driver.findElement(By.xpath("//section[@id='search-2']//span[@class='glass']")).click();
			sleepInSecond(2);
			//step 4: ktra title xuat hien voi tu khoa: Selenium
			List <WebElement> allArticalTitle = driver.findElements(By.cssSelector(".post-title"));
			sleepInSecond(2);
			for(WebElement artical: allArticalTitle) {
				String articalText = artical.getText().trim();
				Assert.assertTrue(articalText.contains("Selenium"));
			}
		}
		public void sleepInSecond(long time) {
			try {
				Thread.sleep(time*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@AfterClass
		public void afterClass() {
			driver.quit();
		}
}
