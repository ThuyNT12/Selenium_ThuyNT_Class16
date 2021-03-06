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

public class Topic_06_Button_RadioButton {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor =(JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	

	
	public void TC_01_Button()
	{
	driver.get("https://www.fahasa.com/customer/account/create");
	//Click tab dang nhap
	driver.findElement(By.cssSelector("li.popup-login-tab")).click();
	sleepInSecond(2);
	
	//step 3: verify button dang nhap bi disable
	WebElement buttonDangNhap = driver.findElement(By.cssSelector(".fhs-btn-login"));
	Assert.assertFalse(buttonDangNhap.isEnabled());
	//step 4: input email/pass hop le
	driver.findElement(By.cssSelector("#login_username")).sendKeys("thuy@gmail.com");
	driver.findElement(By.cssSelector("#login_password")).sendKeys("123123");
	sleepInSecond(2);
	
	//step 5: verify button dang nhap enable
	Assert.assertTrue(driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled());
	buttonDangNhap.click();
	sleepInSecond(2);
	Assert.assertEquals(driver.findElement(By.cssSelector(".fhs-popup-msg.fhs-login-msg")).getText(), "Số điện thoại/Email hoặc Mật khẩu sai!");
	
	//step 6: tai lai trang roi qua tab dang nhap
	driver.navigate().refresh();
	driver.findElement(By.cssSelector("li.popup-login-tab")).click();
	sleepInSecond(5);
	//Step 7: remove thuoc tinh disable cua button dang nhap
	buttonDangNhap = driver.findElement(By.cssSelector(".fhs-btn-login"));
	Assert.assertFalse(buttonDangNhap.isEnabled());
	
	
	removeDisableAttribute(buttonDangNhap);
	 sleepInSecond(2);
	//step 8: click button dang nhap
	 
	 buttonDangNhap.click();
	 sleepInSecond(2);
	 //step 9: kiem tra error message xuat hien cho email/pass
	Assert.assertEquals(driver.findElement(By.xpath("//input[@id='login_username']/parent::div/following-sibling::div")).getText(), "Thông tin này không thể để trống");
	Assert.assertEquals(driver.findElement(By.xpath("//input[@id='login_password']/parent::div/following-sibling::div")).getText(), "Thông tin này không thể để trống");
	}
//@Test
public void TC_02_Radio_checkBox()
{
	driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
	//step 2: click vao checkbox Dual-zone air conditioning
	By checkbox = By.xpath("//input[@id='eq5']");
	
	driver.findElement(checkbox).click();
	sleepInSecond(2);
	//step 3: ktra dc dc chon
	Assert.assertTrue(driver.findElement(checkbox).isSelected());
	//step 4: bo chon va verify
	driver.findElement(checkbox).click();
	sleepInSecond(2);
	Assert.assertFalse(driver.findElement(checkbox).isSelected());
	//step 5: truy cap
	driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
	//step 6: click vao radio button
	By radio = By.xpath("//input[@id='engine3']");
	//ktra 
	boolean status = driver.findElement(radio).isSelected();
	
	if(status==true) {
		Assert.assertTrue(driver.findElement(radio).isSelected());
	}
	else {
		Assert.assertFalse(driver.findElement(radio).isSelected());
		driver.findElement(radio).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(radio).isSelected());
	}
}
@Test
public void TC_03_Custom_Radio_checkBox()
{
	driver.get("https://material.angular.io/components/radio/examples");
	//Step 2: Click vao radio button Summer
	By click = By.xpath("//div[contains(text(),'Summer')]/preceding-sibling::div/input");
	clicktoCheckboxAndRadio(driver.findElement(click));
	//Step 3: ktra radio button duoc chon hay chua. Neu chua chon lai
	boolean verifyRadio = driver.findElement(click).isSelected();
	if(verifyRadio==true) {
		Assert.assertTrue(driver.findElement(click).isSelected());
	}
	else {
		Assert.assertFalse(driver.findElement(click).isSelected());
		driver.findElement(click).click();
		Assert.assertTrue(driver.findElement(click).isSelected());
	}
	//Step 4:
	driver.get("https://material.angular.io/components/checkbox/examples");
	//Step 5: check vao checkbox Summer and Indeterminate
	By checkBoxSummer = By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::div//input");
	By checkBoxIndeterminate = By.xpath("//span[contains(text(),'Indeterminate')]/preceding-sibling::div//input");
	
	clicktoCheckboxAndRadio(driver.findElement(checkBoxSummer));
	sleepInSecond(2);
	clicktoCheckboxAndRadio(driver.findElement(checkBoxIndeterminate));
	sleepInSecond(2);
	//Step 6: verify duoc select
	Assert.assertTrue(driver.findElement(checkBoxSummer).isSelected());
	Assert.assertTrue(driver.findElement(checkBoxIndeterminate).isSelected());
	
	//Step 7: de-select
	clicktoCheckboxAndRadio(driver.findElement(checkBoxSummer));
	sleepInSecond(2);
	clicktoCheckboxAndRadio(driver.findElement(checkBoxIndeterminate));
	sleepInSecond(2);
	//verify
	Assert.assertFalse(driver.findElement(checkBoxSummer).isSelected());
	Assert.assertFalse(driver.findElement(checkBoxIndeterminate).isSelected());
}
public void sleepInSecond(long time) {
	try {
		Thread.sleep(time*1000);
	}catch(InterruptedException e) {
		e.printStackTrace();
	}
	
}
public void removeDisableAttribute(WebElement buttonDangNhap){
	
	jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", buttonDangNhap);
	
}
public void clicktoCheckboxAndRadio(WebElement clicktoCheckBox) {
	jsExecutor.executeScript("arguments[0].click();", clicktoCheckBox);
}
@AfterClass
public void afterClass() {
	driver.quit();
}

}
