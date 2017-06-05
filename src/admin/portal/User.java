package admin.portal;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class User extends Driver{
	
	String name = "a jurado";
	String email = "ajurado@dnamicro.com";
	String mobile = "(456) 465-4654";
	String password = "Passw0rd3!!";
	String status = "Active";
	String branch = null;
	String role = null;
	String jobTitle = null;
	String host = null;
	
	SoftAssert ss;
	Settings login = new Settings();
	
  @Test(priority=1)
  public void AddUser() throws Exception{
	  
	  ss = new SoftAssert();
	  
	  login.login();
	  
	  Thread.sleep(1000);
	  
	  //click on User menu
	  driver.findElement(By.xpath("//*[@id='sidebar-container']/div/div[1]/div/div/div/div/div/ul/li[3]/a/span[1]")).click();
	  
	  Thread.sleep(3000);

	  String title = driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[1]/div/h3")).getText();
	  ss.assertEquals(title, "Users List");
	  
	  Reporter.log(title + " page successfully loaded.");
	  
	  //click on new user button
	  driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[1]/div/div/div/span/button")).click();
	  
	  Thread.sleep(1000);
	  
	  // wait till save button is clickable
	  wait = new WebDriverWait(driver, 15);
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[1]/div/div/div/div/div/div/span[1]/button")));
	  
	  String panelTitle = driver.findElement(By.xpath("//*[@id='fs-panel-modal']/div/div[1]/p")).getText();
	  ss.assertEquals(panelTitle, "Create New User");
	  
	  Reporter.log(panelTitle + " panel successfully loaded.");
	  
	  Thread.sleep(1000);
	  
	  // click on host checkbox
	  driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/form/div/div[2]/div/div/div/div[4]/div/div[1]/label/span[1]/span")).click();
	  
	  Thread.sleep(1000);
	  
	  //click on Save button to make all required fields display
	  driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[1]/div/div/div/div/div/div/span[1]/button")).click();
	  
	  Thread.sleep(1000);
	  
	  // check if all fields are required	  
	  String fNameR = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/form/div/div[1]/div[1]/div/div/div/div[2]/div/div[1]/div/div/label")).getText();
	  ss.assertEquals(fNameR, "This field is required.");
	  
	  String emailR = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/form/div/div[2]/div/div/div/div[1]/div[1]/div/div/label")).getText();
	  ss.assertEquals(emailR, "This field is required.");
	  
	  String passwordR = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/form/div/div[2]/div/div/div/div[1]/div[2]/div/div/section/label[2]")).getText();
	  ss.assertEquals(passwordR, "This field is required.");
	  
	  String branchR = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/form/div/div[2]/div/div/div/div[2]/div[1]/div/div/label")).getText();
	  ss.assertEquals(branchR, "This field is required.");
	  
	  String mobileR = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/form/div/div[2]/div/div/div/div[2]/div[2]/div/div/label")).getText();
	  ss.assertEquals(mobileR, "This field is required.");
	  
	  String roleR = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/form/div/div[2]/div/div/div/div[3]/div[1]/div/div/label")).getText();
	  ss.assertEquals(roleR, "This field is required.");
	  
	  String jobTitleR = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/form/div/div[2]/div/div/div/div[3]/div[2]/div/div/label")).getText();
	  ss.assertEquals(jobTitleR, "This field is required.");
	  
	  String hostR = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/form/div/div[2]/div/div/div/div[4]/div/div[2]/div/div/label[2]")).getText();
	  ss.assertEquals(hostR, "This field is required.");
	  /************ end of checking required fields ***********/

	  //input in Full Name field
	  driver.findElement(By.xpath("//*[@id='name']")).sendKeys(this.name);
	  
	  Thread.sleep(1000);
	  
	  //input in Email field
	  driver.findElement(By.xpath("//*[@id='email']")).sendKeys(this.email);
	  
	  Thread.sleep(1000);

	  //input in Password field
	  driver.findElement(By.xpath("//*[@id='password']")).sendKeys(this.password);
	  
	  Thread.sleep(1000);
	  
	  //input in mobile
	  driver.findElement(By.xpath("//*[@id='phone_number']")).sendKeys(this.mobile);
	  
	  Thread.sleep(1000);
	  
	  //select branch
	  Select dropdown= new Select(driver.findElement(By.xpath("//*[@id='branch']")));
	  dropdown.selectByIndex(1);
	  
	  Thread.sleep(1000);
	  
	  this.branch = driver.findElement(By.xpath("//*[@id='branch']/option[2]")).getText();
	  Reporter.log("Selected Branch: " + this.branch);
	  
	  //select role
	  dropdown= new Select(driver.findElement(By.xpath("//*[@id='role']")));
	  dropdown.selectByIndex(1);
	  
	  Thread.sleep(1000);
	  
	  this.role = driver.findElement(By.xpath("//*[@id='role']/option[2]")).getText();
	  Reporter.log("Selected Role: " + this.role);
	  
	  //select job title
	  dropdown= new Select(driver.findElement(By.xpath("//*[@id='job_title']")));
	  dropdown.selectByIndex(1);
	  
	  Thread.sleep(1000);
	  
	  this.jobTitle = driver.findElement(By.xpath("//*[@id='job_title']/option[2]")).getText();
	  Reporter.log("Selected Job Title: " + this.jobTitle);
	  
	  //select host
	  dropdown= new Select(driver.findElement(By.xpath("//*[@id='alert_type']")));
	  dropdown.selectByIndex(1);
	  
	  Thread.sleep(1000);
	  
	  this.host = driver.findElement(By.xpath("//*[@id='alert_type']/option[2]")).getText();
	  Reporter.log("Alert Type: " + this.host);
	  
	  if(!this.host.isEmpty()){
		  this.host = "Yes";
	  }else {
		  this.host = "No";
	  }
	  
	  Thread.sleep(1000);
	  
	  // check if set to primary is check; if not then click on checkbox
	  if(!elementHasClass(driver.findElement(By.xpath("//*[@id='user-form']/div/div[2]/div/div/div/div[4]/div[2]/div/label/span[1]/span")), "icon icon-fontello-check")){
		  driver.findElement(By.xpath("//*[@id='user-form']/div/div[2]/div/div/div/div[4]/div[2]/div/label/span[1]/span")).click();
	  }
	  
	  Thread.sleep(1000);
	  
	  //click on Save & Close button
	  driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[1]/div/div/div/div/div/div/span[2]/button")).click();
	  
	  Thread.sleep(1000);
	  
	  String msg1 = driver.findElement(By.xpath("//*[@id='message-body']")).getText();
	  ss.assertEquals(msg1, "Email already exist");
	  
	  Thread.sleep(1000);

	  // click on close
	  driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[1]/a")).click();
	  
	  ss.assertAll();
	  
	  Thread.sleep(3000);
	  
	  Reporter.log(msg1);
  }
  
  @Test(priority=2)
  public void EditUser() throws Exception{
	  
	  ss = new SoftAssert();
	  
	  searchUser();
	  
	  Thread.sleep(1000);
	  
	  // click on edit button	  
	  driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr/td[10]/button[1]")).click();
	  
	  Thread.sleep(1000);
	  
	  //edit the mobile number
	  driver.findElement(By.xpath("//*[@id='phone_number']")).clear();
	  driver.findElement(By.xpath("//*[@id='phone_number']")).sendKeys(" ");
	  driver.findElement(By.xpath("//*[@id='phone_number']")).sendKeys(Keys.BACK_SPACE);
	  driver.findElement(By.xpath("//*[@id='phone_number']")).sendKeys("(654) 987-4563");
	  
	  this.mobile = "(654) 987-4563";
	  
	  Thread.sleep(1000);
	  
	  this.mobile = driver.findElement(By.xpath("//*[@id='phone_number']")).getAttribute("value");
	  Reporter.log("Edit mobile#: " + this.mobile);
	  
	  // uncheck the host
	  driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/form/div/div[2]/div/div/div/div[4]/div/div[1]/label/span[1]/span")).click();
	  
	  String classHost = driver.findElement(By.xpath("//*[@id='user-form']/div/div[2]/div/div/div/div[4]/div/div[1]/label/span[1]/span")).getAttribute("class");
	  
	  if(classHost.isEmpty()) {
		  this.host = "No";
		  Reporter.log("Host: " + this.host);
	  }else{
		  this.host = "Yes";
		  Reporter.log("Host: " + this.host);
	  }
	  
	  Thread.sleep(1000);
	  
	  //click on Save & Close button
	  driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[1]/div/div/div/div/div/div/span[2]/button")).click();
	  
	  Thread.sleep(2000);
	  
	  String msg1 = driver.findElement(By.xpath("//*[@id='message-body']")).getText();
	  ss.assertEquals(msg1, "Successfully saved");
	  
	  ss.assertAll();
	  
	  Thread.sleep(3000);
	  
	  Reporter.log("User successfully edited.");
  }
  
  @Test(priority=3)
  public void DeactiveUser() throws Exception{
	  
	  ss = new SoftAssert();
	  
	  searchUser();
	  
	  Thread.sleep(1000);
	  
	  //click on button deactivate
	  driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr[1]/td[10]/button[3]")).click();
	  
	  Thread.sleep(2000);
	  
	  String msg1 = driver.findElement(By.xpath("/html/body/div[5]/div[2]/form/div[1]")).getText();
	  ss.assertEquals(msg1, "Are you sure you want to deactivate "+this.name+" ?");
	  
	  Thread.sleep(1000);
	  
	  // click Yes button
	  driver.findElement(By.xpath("/html/body/div[5]/div[2]/form/div[3]/button[1]")).click();
	  
	  Thread.sleep(2000);
	  
	  
	  String msg2 = driver.findElement(By.xpath("//*[@id='message-body']")).getText();
	  ss.assertEquals(msg2, "User "+this.name+" has been deactivated.");
	  
	  this.status = "Inactive";
	  
	  ss.assertAll();

	  Thread.sleep(3000);
	  
	  Reporter.log(msg2);
  }
  
  @Test(priority=4)
  public void ResetPassword() throws Exception{
	  
	  ss = new SoftAssert();
	  
	  searchUser();
	  
	  Thread.sleep(1000);
	  
	  //click on reset password button
	  driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr[1]/td[10]/button[2]")).click();
	  
	  Thread.sleep(2000);
	  
	  String panelTitle = driver.findElement(By.xpath("//*[@id='fs-panel-modal']/div/div[1]/p")).getText();
	  ss.assertEquals(panelTitle, this.name);
	  
	  Thread.sleep(1000);
	  
	  //input invalid password
	  driver.findElement(By.xpath("//*[@id='password']")).sendKeys("aw");
	  
	  Thread.sleep(1000);
	  
	  //input invalid / not same password
	  driver.findElement(By.xpath("//*[@id='confirmpassword']")).sendKeys("awa");
	  
	  Thread.sleep(1000);
	  
	  //click on save changes button
	  driver.findElement(By.xpath("//*[@id='btn-cancel']")).click();
	  
	  Thread.sleep(1000);
	  
	  String errorMsg1 = driver.findElement(By.xpath("//*[@id='message-body']")).getText();
	  ss.assertEquals(errorMsg1, "There seems to be an issue with resetting the user's password: Password must have a minimum of 8 characters at least 1 Alphabet, 1 Number and 1 Special Character");
	  
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
	  
	  ss.assertAll();
	  
	  Thread.sleep(2000);
  }
  
  // serach and check details of a user in the data table
  public void searchUser() throws Exception{
	  
	  // search email address
	  driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[2]/label/input")).sendKeys(this.email);
		  
	  wait = new WebDriverWait(driver, 20);
	  wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id='users_table_info']"), "Showing 1 to 1 of 1 entries"));
		  
	  Thread.sleep(1000);
		  
	  /*********************** check all details of a user in the datable **********************/
	  
	  String name = driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr/td[2]")).getText();
	  ss.assertEquals(name, this.name);
	  
	  String email = driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr/td[3]")).getText();
	  ss.assertEquals(email, this.email);
	  
	  String mobile = driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr/td[4]")).getText();
	  ss.assertEquals(mobile, this.mobile);
	  
	  String branch = driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr/td[5]")).getText();
	  ss.assertEquals(branch, this.branch);
	  
	  String jobTitle = driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr/td[6]")).getText();
	  ss.assertEquals(jobTitle, this.jobTitle);
	  
	  String role = driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr/td[7]")).getText();
	  ss.assertEquals(role, this.role);
	  
	  String host = driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr/td[8]")).getText();
	  ss.assertEquals(host, this.host);
	  
	  String status = driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr/td[9]")).getText();
	  ss.assertEquals(status, this.status);
	  
	  /********************** end of checking ***********************/
	  
	  Reporter.log("Search for " + this.email + " is found with correct details.");
  }
  
  public boolean elementHasClass(WebElement element, String active) {
	  return element.getAttribute("class").contains(active);
  }
  
}
