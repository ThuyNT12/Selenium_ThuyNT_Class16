package WebDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_HTML_DownList_1 {
	WebDriver driver;
	Select select;
	//check push
		@BeforeClass
		public void beforeClass() {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
		}

		@Test
		public void TC_01_ValidateCurrentUrl() {
			//Step 1:  Login Page Url
			driver.get("https://automationfc.github.io/basic-form/index.html");
			//step 2:ktra dropdownlist k ho tro select multiple
			select = new Select(driver.findElement(By.name("user_job1")));
			Assert.assertFalse(select.isMultiple());
			//Step 3: Chon gia tri trong dropdown bang phuong thuc selectByVisibleText
			select.selectByVisibleText("Mobile Testing");
			//Step 4: kiem tra gia tri duoc chon thanh cong
			Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");
			//Step 5: Chon gia tri trong dropdown bang phuong thuc selectByValue
			select.selectByValue("manual");
			//Step 6: kiem tra gia tri duoc chon thanh cong
			Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");
			//Step 7: Chon gia tri trong dropdown bang phuong thuc selectByIndex
			select.selectByIndex(9);
			//Step 8: kiem tra gia tri duoc chon thanh cong
			Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");
			//Step 9: kiem tra dropdown co du 10 gia tri
			//Get cac gia tri trong dropdown
			List<WebElement> listJob01 = select.getOptions();
			int listJob01Size = listJob01.size();
			Assert.assertEquals(listJob01Size, 10);
			//Step 10: Ktra dropdown Job2 ho tro multiple
			select = new Select(driver.findElement(By.name("user_job2")));
			Assert.assertTrue(select.isMultiple());
			//Step 11: select multiple
			select.selectByVisibleText("Automation");
			select.selectByVisibleText("Mobile");
			select.selectByVisibleText("Desktop");
			//Step 12: Kiem tra 3 gia tri duoc select thanh cong
			List<WebElement> optionSelect = select.getAllSelectedOptions();
			int optionSelectSize = optionSelect.size();
			Assert.assertEquals(optionSelectSize, 3);
			//Step 13: Deselect all
			select.deselectAll();
			//Step 14: verify deselect thanh cong
			int optionDeselectSize = select.getAllSelectedOptions().size();
			Assert.assertEquals(optionDeselectSize, 0);
		}

		

		

		@AfterClass
		public void afterClass() {
			driver.quit();
		}

}
