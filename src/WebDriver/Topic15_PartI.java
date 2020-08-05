package WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic15_PartI {
	WebDriver driver;
	WebDriverWait explicitWait;
	//check push
		@BeforeClass
		public void beforeClass() {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/BrowserDriver/geckodriver.exe");
			driver = new FirefoxDriver();
			explicitWait = new WebDriverWait(driver, 10);
			driver.manage().window().maximize();
			
		}

		@Test
		public void TC01_Visible() {
			driver.get("https://www.facebook.com/");
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginbutton")));
		}
		@Test
		public void TC02_Invisible() {
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='uiButtonText']")));
		}
		@Test
		public void TC03_Presence() {
			driver.get("https://www.facebook.com/");
			explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("email")));
			explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("loginbutton")));
		}
		@AfterClass
		public void afterClass() {
			driver.quit();
		}
}

