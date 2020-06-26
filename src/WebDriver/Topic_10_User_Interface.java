package WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.corba.se.spi.orbutil.fsm.FSM;
import com.sun.corba.se.spi.orbutil.fsm.Input;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;


public class Topic_10_User_Interface {

	WebDriver driver;
	Actions action;
	//check push
		@BeforeClass
		public void beforeClass() {
			driver = new FirefoxDriver();
			action = new Actions(driver);
				
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}

		@Test
		public void TC_01_HoverToElement() {
			driver.get("http://www.myntra.com/");
			//Step 2:  Hover to Kids
			action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']"))).perform();
			sleepInSecond(2);
			//Step 3: Click to Home & Path
			driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
			sleepInSecond(2);
			//Step 4: Verify chuyen page thanh cong
			Assert.assertEquals("https://www.myntra.com/kids-home-bath", driver.getCurrentUrl());
			Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).isDisplayed());					
		}

		@Test
		public void TC_02_ClickAndHoverToElement() {
			
			driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
			sleepInSecond(2);
			String[] AllItemExpected = {"1","2","3","4","5","6","7","8"};
			//Step 2: Click and Hover from 1 to 4
			List <WebElement> allItems = driver.findElements(By.cssSelector("#selectable>li"));
			//Click chon tu 1 den 4
			action.clickAndHold(allItems.get(0)).moveToElement(allItems.get(7)).release().perform();
			List <WebElement> allItemSelected = driver.findElements(By.cssSelector(".ui-selected"));
			//Verify size = 4
			Assert.assertEquals(allItemSelected.size(), 8);
			//Tao ra 1 array list de luu text
			ArrayList<String> allItemSelectedText = new ArrayList<String>();
			//Verify text cua cac element co text tu 1 den 8
			for(WebElement webElement : allItemSelected) {
				allItemSelectedText.add(webElement.getText());
			}
			Object[] selectedActual = (Object[]) allItemSelectedText.toArray();
			Assert.assertEquals(selectedActual, AllItemExpected);
		}
		@Test
		public void TC_03_ClickAndHoverToElementRandom() {
			driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
			sleepInSecond(2);
			
			//Step 2: Click and Hover from 1 to 4
			List <WebElement> allItems = driver.findElements(By.cssSelector("#selectable>li"));
			//an phim Control xuong
			action.keyDown(Keys.CONTROL).perform();
			//Click chon random 1,4,7,9
			action.click(allItems.get(0)).click(allItems.get(3)).click(allItems.get(6)).click(allItems.get(8)).perform();
			sleepInSecond(2);
			List <WebElement> allItemSelected = driver.findElements(By.cssSelector(".ui-selected"));
			//Verify size = 4
			Assert.assertEquals(allItemSelected.size(), 4);
		}
		@Test
		public void TC_04_DoubleClick() {
			driver.get("https://automationfc.github.io/basic-form/index.html");
			
			//Step 2: Click on Double click me
			action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
			sleepInSecond(2);
			//Step 3: Verify text hien thi: Hello Automation Guys!
			Assert.assertTrue(driver.findElement(By.xpath("//p[@id='demo' and text()='Hello Automation Guys!']")).isDisplayed());
			sleepInSecond(2);
			
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
