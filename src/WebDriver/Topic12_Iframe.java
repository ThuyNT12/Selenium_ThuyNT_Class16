package WebDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic12_Iframe {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebElement element;
	//check push
		@BeforeClass
		public void beforeClass() {
			driver = new FirefoxDriver();
			jsExecutor = (JavascriptExecutor) driver;
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
		}

		//@Test
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

		@Test
		public void TC_02_Windows_Tab() {
			driver.get("https://automationfc.github.io/basic-form/index.html");
			String parentID = driver.getWindowHandle();
			//Step 2: Click to "google"
			
			clickToElementByJS("//a[text()='GOOGLE']");
			//step 3: Kiem tra title moi cua google
			switchWindowByTite("Google");
			sleepInSecond(3);
			String titleCurrent = (String) executeForBrowser("return document.title");
			Assert.assertEquals(titleCurrent, "Google");
			//Step 4: switch ve parent window
			switchWindowByTite("SELENIUM WEBDRIVER FORM DEMO");
			sleepInSecond(3);
			Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
			//Step 5: click to Facebook
			
			clickToElementByJS("//a[text()='FACEBOOK']");
			//step 6: Kiem tra title moi cua facebook
			switchWindowByTite("Facebook - Đăng nhập hoặc đăng ký");
			sleepInSecond(3);
			titleCurrent = (String) executeForBrowser("return document.title");
			Assert.assertEquals(titleCurrent, "Facebook - Đăng nhập hoặc đăng ký");
			//Step 7: click to Tiki
			switchWindowByTite("SELENIUM WEBDRIVER FORM DEMO");
			sleepInSecond(3);
			Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
			clickToElementByJS("//a[text()='TIKI']");
			//step 8: Kiem tra title moi cua TIKI
			switchWindowByTite("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
			sleepInSecond(3);
			titleCurrent = (String) executeForBrowser("return document.title");
			Assert.assertEquals(titleCurrent, "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
			//Step 9: dong tat ca cac tab tru parent window
			
		}
		public void sleepInSecond (long time) {
			try {
				Thread.sleep(time*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void clickToElementByJS(String locator) {
			 element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].click();", element);
		}
		public Object executeForBrowser(String javaSript) {
			return jsExecutor.executeScript(javaSript);
		}
		public void switchWindowByTite(String pageTitle) {
			Set<String> allWindows = driver.getWindowHandles();
			for (String Window: allWindows) {
				driver.switchTo().window(Window);
				String currentWindow = driver.getTitle();
				if(currentWindow.equals(pageTitle)) {
					break;
				}
			}
					
		}
		
		@AfterClass
		public void afterClass() {
			driver.quit();
		}
}
