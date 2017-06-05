package admin.portal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class poGroupMail extends Driver{
	
	int group = 0;
	
	String email ="ajurado@dnamicro.com";
	
	SoftAssert ss;
	Settings login = new Settings();
  
  @Test(priority=1)
  public void setAsPrimaryGroup() throws Exception{
	  
	  ss = new SoftAssert();
	  
	  login.login();
	  
	  Thread.sleep(1000);
	  
	  //click on User menu
	  driver.findElement(By.xpath("//*[@id='sidebar-container']/div/div[1]/div/div/div/div/div/ul/li[3]/a/span[1]")).click();
	  
	  Thread.sleep(3000);

	  String title = driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[1]/div/h3")).getText();
	  ss.assertEquals(title, "Users List");
	  
	  Reporter.log(title + " page successfully loaded.");
	  
	  // search email address
	  driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[2]/label/input")).sendKeys(this.email);
	  
	  wait = new WebDriverWait(driver, 20);
	  wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id='users_table_info']"), "Showing 1 to 1 of 1 entries"));
		  
	  Thread.sleep(1000);
	  
	  // click on edit button	  
	  driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr/td[10]/button[1]")).click();
		  
	  Thread.sleep(1000);
	  
	  // set as primary group
	  if(!elementHasClass(driver.findElement(By.xpath("//*[@id='user-form']/div/div[2]/div/div/div/div[4]/div[2]/div/label/span[1]/span")), "icon icon-fontello-check")){
		  driver.findElement(By.xpath("//*[@id='user-form']/div/div[2]/div/div/div/div[4]/div[2]/div/label/span[1]/span")).click();
	  }
	  
	  Thread.sleep(1000);
	  
	  boolean check = elementHasClass(driver.findElement(By.xpath("//*[@id='user-form']/div/div[2]/div/div/div/div[4]/div[2]/div/label/span[1]/span")), "icon icon-fontello-check");
	  
	  ss.assertTrue(check);
	  
	  Thread.sleep(2000);
	  
	  //click on Save & Close button
	  driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[1]/div/div/div/div/div/div/span[2]/button")).click();
	  
	  Thread.sleep(1000);
	  
	  String msg1 = driver.findElement(By.xpath("//*[@id='message-body']")).getText();
	  ss.assertEquals(msg1, "Successfully saved");
	  
	  ss.assertAll();    

	  Reporter.log(" User successfully set to primary group.");
	  
	  this.group = 1;
	  
	  Thread.sleep(3000);
	  
	  checkDistribute();
  }
  /*
  @Test(priority=2)
  public void checkGroup() throws Exception{
	  
	  checkDistribute();
  }
  */
  @Test(priority=2)
  public void setAsSecondayGroup() throws Exception{
	  
	  ss = new SoftAssert();
	  
	  //click on User menu
	  driver.findElement(By.xpath("//*[@id='sidebar-container']/div/div[1]/div/div/div/div/div/ul/li[3]/a/span[1]")).click();
	  
	  Thread.sleep(3000);

	  String title = driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[1]/div/h3")).getText();
	  ss.assertEquals(title, "Users List");
	  
	  Reporter.log(title + " page successfully loaded.");
	  
	  // search email address
	  driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[2]/label/input")).sendKeys(this.email);
	  
	  wait = new WebDriverWait(driver, 20);
	  wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id='users_table_info']"), "Showing 1 to 1 of 1 entries"));
		  
	  Thread.sleep(1000);
	  
	  // click on edit button	  
	  driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr/td[10]/button[1]")).click();
		  
	  Thread.sleep(1000);
	  
	  // set as secondary group
	  if(elementHasClass(driver.findElement(By.xpath("//*[@id='user-form']/div/div[2]/div/div/div/div[4]/div[2]/div/label/span[1]/span")), "icon icon-fontello-check")){
		  driver.findElement(By.xpath("//*[@id='user-form']/div/div[2]/div/div/div/div[4]/div[2]/div/label/span[1]/span")).click();
	  }
	  
	  Thread.sleep(1000);
	  
	  boolean check = elementHasClass(driver.findElement(By.xpath("//*[@id='user-form']/div/div[2]/div/div/div/div[4]/div[2]/div/label/span[1]/span")), "icon icon-fontello-check");
	  
	  ss.assertFalse(check);
	  
	  Thread.sleep(2000);
	  
	  //click on Save & Close button
	  driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[1]/div/div/div/div/div/div/span[2]/button")).click();
	  
	  Thread.sleep(1000);
	  
	  String msg1 = driver.findElement(By.xpath("//*[@id='message-body']")).getText();
	  ss.assertEquals(msg1, "Successfully saved");
	  
	  ss.assertAll();    
	  
	  Reporter.log(" User successfully set to Secondary Group.");
	  
	  this.group = 0;
	  
	  Thread.sleep(3000);
	  
	  checkDistribute();
  }
  /*
  @Test(priority=4)
  public void checkGroup() throws Exception{
	  
	  checkDistribute();
  }*/
  
  public void checkDistribute() throws Exception{
	  
	  ss = new SoftAssert();
	  
	  //click on orders menu
	  driver.findElement(By.xpath("html/body/div[2]/div/div[1]/div[2]/div/div[1]/div/div/div/div/div/ul/li[6]/a/span[1]")).click();
	  
	  Thread.sleep(5000);
	  
	  String title = driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[1]/h3")).getText();
	  ss.assertEquals(title, "Order List");
	  
	  Reporter.log(title + " page successfully loaded.");
	  
	  //click on Edit button
	  driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr[1]/td[8]/button[1]")).click();
	  
	  Thread.sleep(1000);
	  
	  //wait for the distribute button to be clickable
	  wait = new WebDriverWait(driver, 15);
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div[1]/div[1]/div/div/div/div/div/div/span[3]/button")));
	  
	  Thread.sleep(1000);
	  
	  //click the distribute button
	  driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div[1]/div[1]/div/div/div/div/div/div/span[3]/button")).click();
	  
	  Thread.sleep(2000);
	  
	  //search a user
	  driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div[2]/div/div[2]/div/div/input")).sendKeys(this.email);
	  
	  Thread.sleep(1000);//*[@id="ms-panel-modal"]/div/div[3]/div/ul[2]/span
	                     //*[@id="ms-panel-modal"]/div/div[3]/div/ul[1]/li/input
	  					 //*[@id="ms-panel-modal"]/div/div[3]/div/ul[1]/li/span[2]
	  
	  String checked1;
	  String checked2;
	  
	  Thread.sleep(1000);
	  
	  if(this.group == 1) {
		  checked1 = driver.findElement(By.xpath("//*[@id='ms-panel-modal']/div/div[3]/div/ul[1]/li/span[2]")).getText();
		  checked2 = driver.findElement(By.xpath("//*[@id='ms-panel-modal']/div/div[3]/div/ul[2]/span")).getText();
		  
		  ss.assertEquals(checked1, "a jurado (ajurado@dnamicro.com)");
		  ss.assertEquals(checked2, "No users available in this group");
		  Reporter.log(checked1 + " is in Primary Group.");
	  }else{
		  checked1 = driver.findElement(By.xpath("//*[@id='ms-panel-modal']/div/div[3]/div/ul[1]/span")).getText();
		  checked2 = driver.findElement(By.xpath("//*[@id='ms-panel-modal']/div/div[3]/div/ul[2]/li/span[2]")).getText();
		  
		  ss.assertEquals(checked2, "a jurado (ajurado@dnamicro.com)");
		  ss.assertEquals(checked1, "No users available in this group");
		  Reporter.log(checked2 + " is in Secondary Group.");
	  }
	  
	  ss.assertAll();
	  
	  //click close button of distribute modal
	  driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div[2]/div/div[1]/a")).click();

	  Thread.sleep(3000);
	  
	  //click close button
	  driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[1]/a")).click();
	  
	  Thread.sleep(3000);
  }
  
  public boolean elementHasClass(WebElement element, String active) {
	  return element.getAttribute("class").contains(active);
  }
  
}
