package WebDriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Check_Enviroment {
  @Test
  public void TC01() {
	  System.out.println("TC01");
  }
  @Test
  public void TC02() {
	  System.out.println("TC02");
  }
  
  @BeforeTest
  public void beforeTest() {
		  System.out.println("chay trc cac test case");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("chay sau test case");
  }

}
