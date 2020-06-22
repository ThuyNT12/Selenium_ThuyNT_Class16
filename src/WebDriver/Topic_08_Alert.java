package WebDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Alert {
	WebDriver driver;
	Alert alert;
		@BeforeClass
		public void beforeClass() {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
		}

@Test
		public void TC_01_AcceptAlert() {
			driver.get("https://automationfc.github.io/basic-form/index.html");
			sleepInSecond(2);
			//Step 2: click on button: Click for JS Alert
			driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
			sleepInSecond(2);
			//Step 3: verify message hien thi trong alert là I am a JS Alert
			alert = driver.switchTo().alert();
			Assert.assertEquals(alert.getText(), "I am a JS Alert");
			//Step 4: accept alert va verify message result: You clicked an alert successfully 
			alert.accept();
			sleepInSecond(2);
			Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
		}
		
		@Test
		public void TC_02_ConfirmAlert() {
			driver.get("https://automationfc.github.io/basic-form/index.html");
			sleepInSecond(2);
			//Step 2: Click on button: Click for JS Confirm
			driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
			sleepInSecond(2);
			//Step 3: Verify message hien thi trong alert: I am a JS Confirm
			alert = driver.switchTo().alert();
			Assert.assertEquals(alert.getText(), "I am a JS Confirm");
			//Step 4: Cancel alert and verify message display: You clicked: Cancel
			alert.dismiss();
			sleepInSecond(2);
			Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
			
		}
		@Test
		public void TC_03_PromptAlert() {
			driver.get("https://automationfc.github.io/basic-form/index.html");
			//Step 2: click on button: Click for JS Prompt
			driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
			sleepInSecond(2);
			//step 3: Verify message hien thi trong alert: I am a JS prompt
			alert = driver.switchTo().alert();
			Assert.assertEquals(alert.getText(), "I am a JS prompt");
			//Step 4: Nhap text va verify the result
			alert.sendKeys("Automation Testing");
			alert.accept();
			sleepInSecond(2);
			Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: Automation Testing");
			
		}
		@Test
		public void TC_04_Authentication_Alert() {
			String username = "admin";
			String password = "admin";
			
			driver.get("http://" + username + ":" + password + "@"  + "the-internet.herokuapp.com/basic_auth");
			
			//Verify authen success
			Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Basic Auth']")).isDisplayed());
		}
		@Test
		public void TC_05_Authentication_Alert_getAttribute() {
			String username = "admin";
			String password = "admin";
			driver.get("http://the-internet.herokuapp.com/");
			//get link by attribute
			String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
			handleAuthenticationAlert(basicAuthenLink, username, password);
			//Verify authen success
			Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Basic Auth']")).isDisplayed());
			
		}
		public void sleepInSecond(long time) {
			try{
				Thread.sleep(time*1000);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		public void handleAuthenticationAlert(String link, String username, String password) {
			String splitLink[]= link.split("//");
			link = splitLink[0] + "//" + username + ":" + password + "@" + splitLink[1];
			driver.get(link);
			
		}
		@AfterClass
		public void afterClass() {
			driver.quit();
		}

}
