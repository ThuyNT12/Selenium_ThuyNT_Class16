package WebDriver;



import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.security.auth.kerberos.KerberosKey;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_CustomDropdown {
	WebDriver driver;
	JavascriptExecutor JsExcutor;
	WebDriverWait explicitWait;
	//check push
		@BeforeClass
		public void beforeClass() {
			driver = new FirefoxDriver();
			
			explicitWait = new WebDriverWait(driver, 30);
			JsExcutor = (JavascriptExecutor) driver;
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			
		}

		@Test
		public void TC_01_JQuery() {
			driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
			
			//Select item 10
			selectItemDropDown("//span[@id='number-button']","//li[@class='ui-menu-item']//div","10");
			//Verify item selected
			Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='10']")).isDisplayed());
			//Select item 19
			selectItemDropDown("//span[@id='number-button']","//li[@class='ui-menu-item']//div","19");
			//Verify item selected
			Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
			
		
		}

		@Test
		public void TC_02_Angular() {
			driver.get("https://bit.ly/2UV2vYi");
			//ejs-dropdownlist[@id='games']//span//span[contains(@class,'e-search-icon')]
			selectItemDropDown("//ejs-dropdownlist[@id='games']//span//span[contains(@class,'e-search-icon')]", "//ul[@id='games_options']//li", "Football");
			Assert.assertEquals(getHiddenItem("select[id='games_hidden'] option"), "Football");
			
			selectItemDropDown("//ejs-dropdownlist[@id='games']//span//span[contains(@class,'e-search-icon')]", "//div[@role='listbox']//div//span", "Justen Kitsune");
			Assert.assertEquals(getHiddenItem("select[id='games_hidden'] option"), "Justen Kitsune");
		}

		@Test
		public void TC_03_ReactJS() {
			driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
			selectItemDropDown("//i[@class='dropdown icon']", "//span[@class='text']", "Jenny Hess");
			Assert.assertTrue(driver.findElement(By.xpath("//div[@role='listbox']/div[@class='text' and text()='Jenny Hess']")).isDisplayed());
			
			selectItemDropDown("//i[@class='dropdown icon']", "//span[@class='text']", "Christian");
			Assert.assertTrue(driver.findElement(By.xpath("//div[@role='listbox']/div[@class='text' and text()='Christian']")).isDisplayed());	
		}
		@Test
		public void TC_04_Editable() {
			driver.get("http://indrimuska.github.io/jquery-editable-select/");
			sendKeysToEditDropdown("//div[@id='default-place']//input","BMW");
			Assert.assertEquals(getHiddenItem("#default-place li.es-visible"), "BMW");
			sendKeysToEditDropdown("//div[@id='default-place']//input","Audi");
			Assert.assertEquals(getHiddenItem("#default-place li.es-visible"), "Audi");
			sendKeysToEditDropdown("//div[@id='default-place']//input","Ford");
			Assert.assertEquals(getHiddenItem("#default-place li.es-visible"), "Ford");
		}
		@Test
		public void TC_05_Advance() {
			driver.get("http://multiple-select.wenzhixin.net.cn/examples#basic.html");
			driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
			
			
		}
		@AfterClass
		public void afterClass() {
			driver.quit();
		}
		public void selectItemDropDown(String parentLocator, String itemLocator, String expectedItem){
			//Click vao 1 the bat ki de cac items trong dropdown hien len
			driver.findElement(By.xpath(parentLocator)).click();
			sleepInSecond(1);
			
			//Cho cho cac item duoc so ra trong HTML
			explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(parentLocator)));
			
			//lay het tat ca item trong list dua vao 1 list webElement
			List<WebElement> allItems = driver.findElements(By.xpath(itemLocator));
			//Duyet qua tung item
			for(WebElement item: allItems) {
				//Moi lan duyet kiem tra xem co bang item minh click hay ko
				//Neu nhu ko bang thi duyet tiep
				//Neu bang thi thoat khoi vong lap
				if(item.getText().equals(expectedItem)) {
					JsExcutor.executeScript("arguments[0]. scrollIntoView(true);", item);
					sleepInSecond(1);
					explicitWait.until(ExpectedConditions.elementToBeClickable(item));
					item.click();
					sleepInSecond(2);
					break;
				}
				
			}
		}
		
		
		public String getHiddenItem(String ccsLocator) {
			return (String) JsExcutor.executeScript("return document.querySelector(\"" + ccsLocator + "\").textContent");
		}
		
		public void sendKeysToEditDropdown(String locator, String value) {
			driver.findElement(By.xpath(locator)).clear();
			driver.findElement(By.xpath(locator)).sendKeys(value);
			sleepInSecond(1);
			driver.findElement(By.xpath(locator)).sendKeys(Keys.TAB);
			sleepInSecond(1);
		}
		public void selectMultipleDropdown(String parentXpath, String allItemXpath, String[] expectedValueItem) {
			//Click vao dropdown cho no xo het gia tri
			WebElement parentDropdown = driver.findElement(By.xpath(parentXpath));
			explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
			List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
			//Duyet qua cac phan tu cho den khi thoa man dieu kien
			for(WebElement chilElement: allItems) {
				for(String item:expectedValueItem) {
					if(chilElement.getText().equals(item)) {
						JsExcutor.executeScript("arguments[0]. scrollIntoView(true);", chilElement);
						sleepInSecond(2);
						//click vao item can chon
						JsExcutor.executeScript("arguments[0].click();", chilElement);
						sleepInSecond(2);
						List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
						if(expectedValueItem.length==itemSelected.size()) {
							break;
						}
					}

			}
			}
		}
		public boolean checkItemSelected (String[] itemSelectedText) {
			List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input\""));
			return false;
			
		}
		public void sleepInSecond(long time){
			try {
				Thread.sleep(time*1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
}
