package WebDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Locator {
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}
	//input id="email" class="input-text required-entry validate-email validation-failed"
	//<input autocapitalize="off" autocorrect="off" spellcheck="false" name="login[username]" value="" id="email" class="input-text required-entry validate-email validation-failed" 
	//title="Email Address" type="email">
	@Test
	
	public void TC_01_ID() throws InterruptedException
	{
	//Tuong tac voi element
		driver.findElement(By.id("email")).sendKeys("id@gmail.com");
		Thread.sleep(2000);
	}
		
	public void TC_02_Class() throws InterruptedException
	{
		driver.findElement(By.className("input-text required-entry validate-password")).sendKeys("123456");
		//class="input-text required-entry validate-password"
		Thread.sleep(2000);
		driver.findElement(By.id("email")).clear();
	}
	public void TC_03_Name() throws InterruptedException
	
	{
		driver.findElement(By.name("login[username]")).sendKeys("Name@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.name("login[username]")).clear();
	}
	public void TC_04_TagName() throws InterruptedException
	{
		int linkNumber = driver.findElements(By.tagName("a")).size();
		System.out.println("Sum link =" + linkNumber);
		Thread.sleep(2000);
	}
	//Text tuyet doi voi duong link
	public void TC_05_LinkText() throws InterruptedException
	{
		//click vao site map link
		driver.findElement(By.linkText("SITE MAP")).click();
		Thread.sleep(2000);
	}
	//Text tuong doi voi duong link
	public void TC_06_Partical_Link_Text() throws InterruptedException
	{
		driver.findElement(By.partialLinkText("ADVANCED")).click();
		Thread.sleep(2000);
	}
	public void TC_07_CSS() throws InterruptedException
	{
		driver.findElement(By.cssSelector("#name")).sendKeys("LCD");
		Thread.sleep(2000);
		
		//class
		driver.findElement(By.cssSelector(".advanced-search")).isDisplayed();
		Thread.sleep(2000);
		
		//Name
		driver.findElement(By.cssSelector("input[name='short_description']")).sendKeys("SamSung LCD");
		Thread.sleep(2000);
		
		//linktext
		driver.findElement(By.cssSelector("a[href='http://live.demoguru99.com/index.php/catalog/seo_sitemap/category/']")).click();
		Thread.sleep(2000);
		
		//partical link text
		driver.findElement(By.cssSelector("a[herf*='/catalogsearch/advanced/")).click();
		Thread.sleep(2000);
		
		//tagname
	int linksize=	driver.findElements(By.cssSelector("a")).size();
	System.out.println("Css tagname =" + linksize);
	
	}
	public void TC_08_Xpath() throws InterruptedException
	{
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		Thread.sleep(2000);
		//ID
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("id_xpath@gmail.com");
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		Thread.sleep(2000);
		//Class
		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-password']")).sendKeys("xpath_class@gmail.com");
		Thread.sleep(2000);
		//name
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("name_xpath@gmail.com");
		Thread.sleep(2000);
		//link text
		driver.findElement(By.xpath("//a[text()='About Us'")).click();
		Thread.sleep(2000);
		//partical link text
		driver.findElement(By.xpath("//a[contains(text()='Customer')]")).click();
		Thread.sleep(2000);
		//tagname
		int link_xpath=	driver.findElements(By.xpath("//a")).size();
		System.out.println("Css tagname =" + link_xpath);
		Thread.sleep(2000);
		//css
		driver.findElement(By.xpath("//input[@title='Sign up for our newsletter']")).sendKeys("xpath_css@gmail.com");
		Thread.sleep(2000);
	}
	
	

}
