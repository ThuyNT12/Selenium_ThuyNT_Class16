package WebDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic12_Iframe {
	WebDriver driver;
	//check push
		@BeforeClass
		public void beforeClass() {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
		}

		@Test
		public void TC_01_Iframe() {
			driver.get("https://kyna.vn/");
			//Step 2: verify Iframe facebook hien thi
			driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage']//iframe")));
			String facebookLiked = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
			Assert.assertEquals(facebookLiked, "169K likes");
			//Step 4: verify webchat hien thi
			//Switch to default
			driver.switchTo().defaultContent();
			driver.switchTo().frame("cs_chat_iframe");
			Assert.assertTrue(driver.findElement(By.cssSelector(".chat-area")).isDisplayed());
			//Step 5: sendkey va verify hien thi form
			driver.findElement(By.tagName("textarea")).sendKeys("Selenium");
			driver.findElement(By.tagName("textarea")).sendKeys(Keys.ENTER);
			Assert.assertTrue(driver.findElement(By.id("sign-in-menu")).isDisplayed());
			sleepInSecond(5);
			//step 6: search "Java"
			driver.switchTo().defaultContent();
			driver.findElement(By.id("live-search-bar")).sendKeys("Java");
			driver.findElement(By.cssSelector(".search-button")).click();
			sleepInSecond(5);
			Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(), "'Java'");
		}
		
		public void sleepInSecond (long time) {
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
