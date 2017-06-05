package admin.portal;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class LoginPage extends Driver{
	
	SoftAssert ss = new SoftAssert();
	
	String validEmail  = "admin@email.com";
	String invalidEmail = "invalid@";
	String inactiveEmail = "inactive@email.com";
	
	String validPassword = "password123";
	String invalidPassword = "123";
	
  @Test(priority=1)
  public void LoginFailed() throws Exception{
	  
	  driver.get("http://csr.devvm.drumbi.com:3060/login");
	  
	  waitForPageToBeReady();
	  
	  Reporter.log("Asher Admin Portal launch sauccessfully!");
	  
	  Thread.sleep(1000);
	  
	  //click the login button with empty fields
	  driver.findElement(By.xpath("//*[@id='btnLogin']")).click();
	  
	  Thread.sleep(1000);
	  
	  String errMsg1 = driver.findElement(By.xpath("//*[@id='errormessage']")).getText();
	  ss.assertEquals(errMsg1, "Email is required");
	  
	  Reporter.log("User login with empty fields: " + errMsg1);
	  
	  Thread.sleep(1000);
	  	  
	  driver.findElement(By.xpath("//*[@id='emailaddress']")).sendKeys(validEmail);
	  
	  Thread.sleep(1000);
	  
	  //click the login button with empty password field
	  driver.findElement(By.xpath("//*[@id='btnLogin']")).click();
	  
	  Thread.sleep(1000);
	  
	  String errMsg2 = driver.findElement(By.xpath("//*[@id='errormessage']")).getText();
	  ss.assertEquals(errMsg2, "Password is required");
	  
	  Reporter.log("User login with empty password field: " + errMsg2);
	  
	  Thread.sleep(1000);
	  
	  driver.findElement(By.xpath("//*[@id='password']")).sendKeys(invalidPassword);
	  
	  Thread.sleep(1000);
	  
	  //click the login button with empty incorrect password
	  driver.findElement(By.xpath("//*[@id='btnLogin']")).click();
	  
	  Thread.sleep(1000);
	  
	  errMsg1 = driver.findElement(By.xpath("//*[@id='errormessage']")).getText();
	  ss.assertEquals(errMsg1, "Incorrect Email or Password");
	  
	  Reporter.log("User login with incorrect password: " + errMsg1);
	  
	  Thread.sleep(1000);
	  
	  driver.findElement(By.xpath("//*[@id='emailaddress']")).clear();
	  driver.findElement(By.xpath("//*[@id='emailaddress']")).sendKeys(" ");
	  driver.findElement(By.xpath("//*[@id='emailaddress']")).sendKeys(Keys.BACK_SPACE);
	  driver.findElement(By.xpath("//*[@id='emailaddress']")).sendKeys(invalidEmail);
	  
	  Thread.sleep(1000);
	  
	  //click the login button with invalid credentials
	  driver.findElement(By.xpath("//*[@id='btnLogin']")).click();
	  
	  Thread.sleep(1000);
	  
	  String errMsg3 = driver.findElement(By.xpath("//*[@id='errormessage']")).getText();
	  ss.assertEquals(errMsg3, "User does not exist");
	  
	  Reporter.log("User login with invalid credentials: " + errMsg3);
	  
	  Thread.sleep(1000);
	  
	  driver.findElement(By.xpath("//*[@id='emailaddress']")).clear();
	  driver.findElement(By.xpath("//*[@id='emailaddress']")).sendKeys(" ");
	  driver.findElement(By.xpath("//*[@id='emailaddress']")).sendKeys(Keys.BACK_SPACE);
	  driver.findElement(By.xpath("//*[@id='emailaddress']")).sendKeys(inactiveEmail);
	  
	  Thread.sleep(1000);
	  
	  //click the login button with invalid credentials
	  driver.findElement(By.xpath("//*[@id='btnLogin']")).click();
	  
	  Thread.sleep(1000);
	  
	  String errMsg4 = driver.findElement(By.xpath("//*[@id='errormessage']")).getText();
	  ss.assertEquals(errMsg4, "Account is currently inactive. Contact your Administrator");
	  
	  Reporter.log("User login with inactive credentials: " + errMsg4);
	  
	  ss.assertAll();
	  
	  Thread.sleep(1000);
  }
  
  @Test(priority=2)
  public void LoginSuccess() throws Exception{
	  
	  driver.findElement(By.xpath("//*[@id='emailaddress']")).clear();
	  driver.findElement(By.xpath("//*[@id='emailaddress']")).sendKeys(" ");
	  driver.findElement(By.xpath("//*[@id='emailaddress']")).sendKeys(Keys.BACK_SPACE);
	  driver.findElement(By.xpath("//*[@id='emailaddress']")).sendKeys(validEmail);
	  
	  Thread.sleep(1000);
	  
	  driver.findElement(By.xpath("//*[@id='password']")).clear();
	  driver.findElement(By.xpath("//*[@id='password']")).sendKeys(" ");
	  driver.findElement(By.xpath("//*[@id='password']")).sendKeys(Keys.BACK_SPACE);
	  driver.findElement(By.xpath("//*[@id='password']")).sendKeys(validPassword);
	  
	  Thread.sleep(1000);
	  
	  //click the login button with valid credentials
	  driver.findElement(By.xpath("//*[@id='btnLogin']")).click();
	  
	  wait = new WebDriverWait(driver, 60);
	  wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id='body']/div/div/div[1]/ol/li/span"), "Home"));
	  
	  Reporter.log("User login successfully!");
	  
	  Thread.sleep(2000);
  }
  
  @Test(priority=3)
  public void LogoutSuccess() throws Exception{
	  
	  driver.findElement(By.xpath("//*[@id='rubix-nav-header']/div/div/div[3]/div/ul/li/a/span")).click();
	  
	  Thread.sleep(1000);
	  
	  driver.findElement(By.xpath("/html/body/div[6]/div[2]/form/div[3]/button[1]")).click();
	  
	  wait = new WebDriverWait(driver, 60);
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnLogin']")));
	  
	  Reporter.log("User logout successfully!");
	  
	  Thread.sleep(2000);
  }
  
}
