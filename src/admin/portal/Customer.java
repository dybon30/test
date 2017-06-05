package admin.portal;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Customer extends Driver{
	
	SoftAssert ss = new SoftAssert();
	
	Settings login = new Settings();
	
	String searchEmail = "ajurado@dnamicro.com";
	
  @Test(priority=1)
  public void ViewCustomer() throws Exception{
	  
	  login.login();
	  
	  //click on Customer menu
	  driver.findElement(By.xpath("//*[@id='sidebar-container']/div/div[1]/div/div/div/div/div/ul/li[4]/a/span[1]")).click();
	  
	  Thread.sleep(2000);
	  
	  //check if it is in the Customer page
	  String pageTitle = driver.findElement(By.xpath("//*[@id='body']/div/div[2]/div[2]/div/div/div[1]/h3")).getText();
	  ss.assertEquals(pageTitle, "Customers List");
	  
	  //log to the status to console
	  if(pageTitle.equalsIgnoreCase("Customers List")){
		  Reporter.log(pageTitle + " page succesfully loaded.");
	  }else{
		  Reporter.log("Failed to load " + pageTitle);
	  }
	  
	  Thread.sleep(1000);
	  
	  searchCustomer();	  
	  
	  Thread.sleep(1000);
	  
	  //click on View customer
	  driver.findElement(By.xpath("//*[@id='btn-view-customer']")).click();
	  
	  Thread.sleep(1000);
	  
	  //click on Send Invitation
	  driver.findElement(By.xpath("//*[@id='btn-cancel']/span")).click();
	  
	  Thread.sleep(1000);
	  
	  String msg1 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/form/div[1]")).getText();
	  ss.assertEquals("Please confirm Customer Invitation.", msg1);
	  
	  Thread.sleep(1000);
	  
	  //click on Yes button
	  driver.findElement(By.xpath("/html/body/div[5]/div[2]/form/div[3]/button[1]")).click();
	  
	  Thread.sleep(2000);
	  
	  //click on OK button
	  driver.findElement(By.xpath("html/body/div[5]/div[2]/form/div[3]/button")).click();
	  
	  Reporter.log("Customer Invitation Sent successfully.");
	  
	  Thread.sleep(1000);
	  
	  //click on close button
	  driver.findElement(By.xpath("//*[@id='fs-panel-modal']/div/div[1]/a")).click();
	  
	  ss.assertAll();
	  
	  Thread.sleep(3000);
  }
  
  @Test(priority=2)
  public void ViewPayments() throws Exception{
	  
	  searchCustomer();
	  
	  Thread.sleep(1000);

	  //click on View Payments
	  driver.findElement(By.xpath("//*[@id='btn-view-payments']")).click();
	  
	  Thread.sleep(1000);
	  
	  String panelTitle = driver.findElement(By.xpath("//*[@id='fs-panel-modal']/div/div[2]/div/div/div/div/div[2]/div/div/div[1]/h3")).getText();
	  ss.assertEquals(panelTitle, "Customer Payments List");
	  
	  //log to the status to console
	  if(panelTitle.equalsIgnoreCase("Customer Payments List")){
		  Reporter.log(panelTitle + " panel successfully loaded.");
	  }else{
		  Reporter.log("Failed to load " + panelTitle);
	  }
	  
	  Thread.sleep(2000);
	  
	  //get the records and display in the console
	  String entries = driver.findElement(By.xpath("//*[@id='paymentsTable_info']")).getText();
	  Reporter.log(entries);
	  
	  Thread.sleep(1000);
	  
	  //click on close button
	  driver.findElement(By.xpath("//*[@id='fs-panel-modal']/div/div[1]/a")).click();
	  
	  ss.assertAll();
	  
	  Thread.sleep(3000);
  }
  
  @Test(priority=3)
  public void ResetPassword() throws Exception{
	  
	  searchCustomer();
	  
	  Thread.sleep(1000);

	  //click on Reset Password
	  driver.findElement(By.xpath("//*[@id='btn-reset-password']")).click();
	  	  
	  Thread.sleep(2000);
	  
	  String panelTitle = driver.findElement(By.xpath("//*[@id='fs-panel-modal']/div/div[1]/p")).getText();
	  ss.assertEquals(panelTitle, "Reset Customer Password");
	  
	  //log to the status to console
	  if(panelTitle.equalsIgnoreCase("Reset Customer Password")){
		  Reporter.log(panelTitle + " panel successfully loaded.");
	  }else{
		  Reporter.log("Failed to load " + panelTitle);
	  }
	  
	  driver.findElement(By.xpath("//*[@id='password']")).sendKeys("aw");
	  
	  Thread.sleep(1000);
	  
	  driver.findElement(By.xpath("//*[@id='confirmpassword']")).sendKeys("awa");
	  
	  Thread.sleep(1000);
	  
	  //click on save changes button
	  driver.findElement(By.xpath("//*[@id='btn-cancel']")).click();
	  
	  Thread.sleep(1000);
	  
	  String errorMsg1 = driver.findElement(By.xpath("//*[@id='message-body']")).getText();
	  ss.assertEquals(errorMsg1, "There seems to be an issue with resetting the customer's password: Password must have a minimum of 8 characters at least 1 Alphabet, 1 Number and 1 Special Character");
	  
	  Reporter.log("Reset password with invalid data " + errorMsg1);
	  
	  Thread.sleep(3000);
	  
	  //change the password field
	  driver.findElement(By.xpath("//*[@id='password']")).clear();
	  driver.findElement(By.xpath("//*[@id='password']")).sendKeys(" ");
	  driver.findElement(By.xpath("//*[@id='password']")).sendKeys(Keys.BACK_SPACE);
	  driver.findElement(By.xpath("//*[@id='password']")).sendKeys("awawAW11!!");
	  
	  Thread.sleep(1000);
	  
	  //change the confirm password field
	  driver.findElement(By.xpath("//*[@id='confirmpassword']")).clear();
	  driver.findElement(By.xpath("//*[@id='confirmpassword']")).sendKeys(" ");
	  driver.findElement(By.xpath("//*[@id='confirmpassword']")).sendKeys(Keys.BACK_SPACE);
	  driver.findElement(By.xpath("//*[@id='confirmpassword']")).sendKeys("awawAW11!!");
	  
	  Thread.sleep(1000);
	  
	  //click on save changes button
	  driver.findElement(By.xpath("//*[@id='btn-cancel']")).click();
	  
	  Thread.sleep(2000);
	  
	  String msg2 = driver.findElement(By.xpath("//*[@id='message-body']")).getText();
	  ss.assertEquals(msg2, "Password changed successfully");
	  
	  Reporter.log("Reset password with valid data: " + msg2);
	  
	  Thread.sleep(2000);
	  
	  ss.assertAll();
	  
	  Thread.sleep(1000);
  }
  
  public void searchCustomer(){
	  //search for specific item
	  driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[2]/label/input")).clear();
	  driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[2]/label/input")).sendKeys(searchEmail);
	  
	  wait = new WebDriverWait(driver, 15);
	  wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id='customersTable_info']"), "Showing 1 to 1 of 1 entries"));
	  
	  //check if invoice search result is correct
	  String email = driver.findElement(By.xpath("//*[@id='customersTable']/tbody/tr/td[2]")).getText();
	  ss.assertEquals(email, searchEmail);
	  
	  //log to the status to console
	  if(email.equalsIgnoreCase(searchEmail)){
		  Reporter.log("Searching for "+ searchEmail + " successfully found.");
	  }else{
		  Reporter.log("Searching for "+ searchEmail + " not found.");
	  }
  }
  

}
