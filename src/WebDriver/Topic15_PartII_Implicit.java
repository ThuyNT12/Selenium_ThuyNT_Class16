package WebDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic15_PartII_Implicit {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebElement element;
	//check push
		@BeforeClass
		public void beforeClass() {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/BrowserDriver/geckodriver.exe");
			driver = new FirefoxDriver();
			
			driver.manage().window().maximize();
			
		}

		@Test
		public void BaiTapTC03_ImplicitWait() {
			driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
			//Step 2: Define implicit wait 
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			//step 3: click start button
			driver.findElement(By.xpath("//button")).click();
			//step 4: wait the result display
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='loading']")).isDisplayed());
			//Step 5: verify the result
			Assert.assertEquals(driver.findElement(By.xpath("//h4[text()='Hello World!']")).getText(), "Hello World!");
}
}

