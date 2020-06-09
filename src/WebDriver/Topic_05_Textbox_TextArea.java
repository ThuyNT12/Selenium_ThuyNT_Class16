package WebDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Textbox_TextArea {
	WebDriver driver;
	String userName, password, loginPageUrl;
	String name, dateOfBirth, address, city, state, pin, phoneNumber, email;
	By customerNameTextbox = By.name("name");
	By dateOfBirthTextbox = By.name("dob");
	By addressTextarea = By.name("addr");
	By cityTextbox = By.name("city");
	By stateTextbox = By.name("state");
	By pinTextbox = By.name("pinno");
	By phoneTextbox = By.name("telephoneno");
	By emailTextbox = By.name("emailid");
	By passwordTextbox = By.name("password");

	// check push
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		name = "Thuy Nguyen";
		dateOfBirth = "1990-01-01";
		address = "105 Le Loi\nLai Chau\nDa Nang";
		city = "Ha Noi";
		state = "New York";
		pin = "123456";
		phoneNumber = "0987654321";
		email = "ThuyNguyen" + RandomEmail() + "@hotmail.com";
		driver.get("http://demo.guru99.com/v4/");
		loginPageUrl = driver.getCurrentUrl();
		
	

	}

	@Test
	public void TC_01_Register() {
		// click here link( vao trong register)
		driver.findElement(By.xpath("//a[text()='here']")).click();
		// enter email
		driver.findElement(By.name("emailid")).sendKeys(email);
		// click login button
		driver.findElement(By.name("btnLogin")).click();
		// get userName and pass
		userName = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

//mngr264921 
//nanYhah
	@Test
	public void TC_02_Login() {
		driver.get(loginPageUrl);
		driver.findElement(By.name("uid")).sendKeys(userName);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),"Welcome To Manager's Page of Guru99 Bank");
	}

	@Test
	public void TC_03_New_Customer() {
		// click new customer
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		//nhap thong tin
		driver.findElement(customerNameTextbox).sendKeys(name);
		driver.findElement(dateOfBirthTextbox).sendKeys(dateOfBirth);
		driver.findElement(addressTextarea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phoneNumber);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		
		
		//click submit button
		driver.findElement(By.name("sub")).click();
		//verify create new customer successfully
		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(),"Customer Registered Successfully!!!");
		//Verify information input
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address.replace("\n", " "));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phoneNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
		

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public int RandomEmail() {
		Random rand = new Random();
		return rand.nextInt(999999);
		
	}
}