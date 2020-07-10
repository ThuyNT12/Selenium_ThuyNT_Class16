package WebDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_JavaScript_Executor {
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

		@Test
		public void TC_01_Live_Guru() {
			//Step 1: access to page
			navigateToUrlByJS("http://live.demoguru99.com/");
			//Step 2: Get domain of page
			String liveGuruDomain = (String) executeForBrowser("return document.domain");
			Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");
			//Step 3: Get URL of page
			String liveGuruURL = (String) executeForBrowser("return document.URL");
			Assert.assertEquals(liveGuruURL, "http://live.demoguru99.com/");
			//Step 4: Open Mobile page
			clickToElementByJS("//a[text()='Mobile']");
			//Step 5: Add san pham samsung vao cart
			clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
			//Step 6: Verify message duoc hien thi
			String messageLiveGuru = getPageInnerText();
			Assert.assertTrue(messageLiveGuru.contains("Samsung Galaxy was added to your shopping cart."));
			
			//Step 7: Open "Customer Service" page
			clickToElementByJS("//a[text()='Customer Service']");
			String titleCustomer = (String) executeForBrowser("return document.title");
			Assert.assertEquals(titleCustomer, "Customer Service");
			//Step 8: Scroll toi element o cuoi trang
			scrollToElement("//input[@id='newsletter']");
			//Step 9: Verify text hien thi
			Assert.assertTrue(verifyTextInInnerText("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));
			//Step 10: Navigate to page: http://demo.guru99.com/v4/
			navigateToUrlByJS("http://demo.guru99.com/v4/");
			String demoGuruDomain = (String) executeForBrowser("return document.domain");
			Assert.assertEquals(demoGuruDomain, "demo.guru99.com");
			
		}
		@Test
		public void TC_01_Remove_Attrbute() {
			
		}
		@AfterClass
		public void afterClass() {
			driver.quit();
		}
		// Browser
		public Object executeForBrowser(String javaSript) {
			return jsExecutor.executeScript(javaSript);
		}
		public String getPageInnerText() {
			return (String) jsExecutor.executeScript("return document.documentElement.innerText");
		}

		public boolean verifyTextInInnerText(String textExpected) {
			String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
			System.out.println("Text actual = " + textActual);
			return textActual.equals(textExpected);
		}

		public void scrollToBottomPage() {
			jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}

		public void navigateToUrlByJS(String url) {
			jsExecutor.executeScript("window.location = '" + url + "'");
		}

		// Element
		public void highlightElement(String locator) {
			element = driver.findElement(By.xpath(locator));
			String originalStyle = element.getAttribute("style");
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
			sleepInSecond(2);
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

		}

		public void clickToElementByJS(String locator) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].click();", element);
		}

		public void scrollToElement(String locator) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		}

		public void sendkeyToElementByJS(String locator, String value) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
		}

		public void removeAttributeInDOM(String locator, String attributeRemove) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}
		public void sleepInSecond(long time) {
			try {
				Thread.sleep(time*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
