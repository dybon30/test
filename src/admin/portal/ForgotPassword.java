package admin.portal;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ForgotPassword extends Driver{
	
	SoftAssert ss = new SoftAssert();

  @Test
  public void forgotPassword() throws Exception{
	  
	  driver.get("http://csr.devvm.drumbi.com:3060/login");
	  
	  waitForPageToBeReady();
	  
	  Reporter.log("Asher Admin Portal launch sauccessfully!");
	  
	  //click on forgot password link
	  driver.findElement(By.xpath("//*[@id='portal-login']/div[3]/div/div/div[1]/a")).click();
	  
	  Thread.sleep(1000);
	  
	  //click on Recover Password button with empty email field
	  driver.findElement(By.xpath("//*[@id='forgotpass-form']/button")).click();
	  
	  Thread.sleep(1000);
	  
	  String errMsg1 = driver.findElement(By.xpath("//*[@id='message-body']")).getText();
	  ss.assertEquals(errMsg1, "E-Mail Address cannot be blank.");
	  
	  Reporter.log("User click on Recover Password button with empty field: " + errMsg1);
	  
	  Thread.sleep(1000);
	  
	  driver.findElement(By.xpath("//*[@id='forgotpass-form']/div/input")).sendKeys("test");
	  
	  Thread.sleep(1000);
	  
	  //click on Recover Password button with invalid email address
	  driver.findElement(By.xpath("//*[@id='forgotpass-form']/button")).click();
	  
	  Thread.sleep(1000);
	  
	  String errMsg2 = driver.findElement(By.xpath("//*[@id='message-body']")).getText();
	  ss.assertEquals(errMsg2, "There seems to be an issue with the E-mail Address you input...");
	  
	  Reporter.log("User click on Recover Password button with invalid Email: " + errMsg2);
	  
	  Thread.sleep(1000);
	  
	  driver.findElement(By.xpath("//*[@id='forgotpass-form']/div/input")).clear();
	  driver.findElement(By.xpath("//*[@id='forgotpass-form']/div/input")).sendKeys(" ");
	  driver.findElement(By.xpath("//*[@id='forgotpass-form']/div/input")).sendKeys(Keys.BACK_SPACE);
	  driver.findElement(By.xpath("//*[@id='forgotpass-form']/div/input")).sendKeys("inactive@email.com");
	  
	  Thread.sleep(1000);
	  
	  //click on Recover Password button with inactive user
	  driver.findElement(By.xpath("//*[@id='forgotpass-form']/button")).click();
	  
	  Thread.sleep(1000);
	  
	  String errMsg3 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/form/div[1]")).getText();
	  ss.assertEquals(errMsg3, "Account is currently inactive. Please contact your Administrator.");
	  
	  Reporter.log("User click on Recover Password button with inactive email: " + errMsg3);
	  
	  Thread.sleep(1000);
	  
	  //click on OK button
	  driver.findElement(By.xpath("/html/body/div[5]/div[2]/form/div[3]/button")).click();
	  
	  Thread.sleep(1000);
	  
	  driver.findElement(By.xpath("//*[@id='forgotpass-form']/div/input")).clear();
	  driver.findElement(By.xpath("//*[@id='forgotpass-form']/div/input")).sendKeys(" ");
	  driver.findElement(By.xpath("//*[@id='forgotpass-form']/div/input")).sendKeys(Keys.BACK_SPACE);
	  driver.findElement(By.xpath("//*[@id='forgotpass-form']/div/input")).sendKeys("ajurado@dnamicro.com");
	  
	  Thread.sleep(1000);
	  
	  //click on Recover Password button with inactive user
	  driver.findElement(By.xpath("//*[@id='forgotpass-form']/button")).click();
	  
	  Thread.sleep(1000);
	  
	  String errMsg4 = driver.findElement(By.xpath("/html/body/div[6]/div[2]/form/div[1]")).getText();
	  ss.assertEquals(errMsg4, "A Message has been sent to your E-mail Address!");
	  
	  Reporter.log("User click on Recover Password button with valid email: " + errMsg4);
	  
	  Thread.sleep(1000);
	  
	  //click on OK button
	  driver.findElement(By.xpath("/html/body/div[6]/div[2]/form/div[3]/button")).click();
	  
	  ss.assertAll();
	  
	  Thread.sleep(2000);
  }
}
