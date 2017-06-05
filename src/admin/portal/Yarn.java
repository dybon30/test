package admin.portal;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Yarn extends Driver{
	String fabColor = "Automation Test1";
	String fabColorEdited = "Automation Test1 Edited";
	
	SoftAssert ss; 
	Settings login = new Settings();
	
  @Test(priority=1)
  public void AddYarn() throws Exception{
	  
	  ss = new SoftAssert();
	  
	  login.login();
	  
	  Thread.sleep(1000);
	  
	  //select Setting and click on Yarn submenu
	  Actions action = new Actions(driver);
	  WebElement we = driver.findElement(By.xpath("html/body/div[2]/div/div[1]/div[2]/div/div[1]/div/div/div/div/div/ul/li[8]/a"));
	  action.moveToElement(we).moveToElement(driver.findElement(By.xpath("html/body/div[2]/div/div[1]/div[2]/div/div[1]/div/div/div/div/div/ul/li[8]/ul/li[2]/a"))).click().build().perform();
	  
	  Thread.sleep(3000);
	  
	  String title = driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[1]/h3")).getText();
	  ss.assertEquals("Yarn Color", title);
	  
	  //click on add button
	  driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[1]/div/div/div/span/button")).click();
	  
	  Thread.sleep(2000);
	  
	  String title2 = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[1]/p")).getText();
	  ss.assertEquals("Yarn Colors", title2);
	  
	  //input on fabric color
	  driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div[2]/div/div/div/form/div/div/div/div/div[1]/div/div/input")).sendKeys(fabColor);
	  
	  Thread.sleep(1000);
	  
	  //select item on checkbox 
	  driver.findElement(By.xpath("//*[@id='yarn-color-form']/div/div/div/div/div[2]/div/div[3]/ul/li[1]/label/span[1]")).click();
	  String val1 = driver.findElement(By.xpath("//*[@id='yarn-color-form']/div/div/div/div/div[2]/div/div[3]/ul/li[1]/label/span[2]")).getText();
	  driver.findElement(By.xpath("//*[@id='yarn-color-form']/div/div/div/div/div[2]/div/div[3]/ul/li[2]/label/span[1]")).click();
	  String val2 = driver.findElement(By.xpath("//*[@id='yarn-color-form']/div/div/div/div/div[2]/div/div[3]/ul/li[2]/label/span[2]")).getText();
	  
	  String selected = val1+", "+val2;
	  
	  Thread.sleep(1000);
	  
	  //click on save button
	  driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[1]/div/div/div/div/div/div/span[1]/button")).click();
	  
	  Thread.sleep(1000);
	  
	  String msg1 = driver.findElement(By.xpath("//*[@id='message-body']")).getText();
	  ss.assertEquals(fabColor+" has been created.", msg1);
	  
	  Thread.sleep(2000);
	  
	  //close the message box
	  driver.findElement(By.xpath("//*[@id='dna-messagebox']/a")).click();
	  
	  Thread.sleep(1000);
	  
	  //click on save & close button
	  driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[1]/div/div/div/div/div/div/span[2]/button")).click();
	  
	  Thread.sleep(1000);
	  
	  String msg2 = driver.findElement(By.xpath("//*[@id='message-body']")).getText();
	  ss.assertEquals(fabColor+" already exists", msg2);
	  
	  Thread.sleep(3000);
	  
	  //click on close
	  driver.findElement(By.xpath("//*[@id='fs-panel-modal']/div/div[1]/a")).click();
	  
	  Thread.sleep(3000);
	  
	  String name = driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr[1]/td[1]")).getText();
	  String values = driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr[1]/td[2]/div/span[1]")).getText();
	  
	  ss.assertEquals(fabColor, name);
	  ss.assertEquals(selected, values);
	  
	  Reporter.log("Yarn Color sauccessfully added.");
	  
	  ss.assertAll();
	
	  Thread.sleep(2000);
  }
  
  @Test(priority=2)
  public void EditYarn() throws Exception{
	  
	  ss = new SoftAssert();
	  
	  //click on edit button
	  driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr[1]/td[3]/button[1]")).click();
	  
	  Thread.sleep(3000);
	  
	  //check if the item is selected                                       
	  boolean checkSelected1 = elementHasClass(driver.findElement(By.xpath("//*[@id='yarn-color-form']/div/div/div/div/div[2]/div/div[3]/ul/li[1]/label/span[1]/span")), "icon icon-fontello-check");
	  boolean checkSelected2 = elementHasClass(driver.findElement(By.xpath("//*[@id='yarn-color-form']/div/div/div/div/div[2]/div/div[3]/ul/li[2]/label/span[1]/span")), "icon icon-fontello-check");
	  
	  ss.assertTrue(checkSelected1);
	  ss.assertTrue(checkSelected2);
	  
	  Thread.sleep(1000);
	  
	  //input on fabric color
	  driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div[2]/div/div/div/form/div/div/div/div/div[1]/div/div/input")).clear();
	  driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div[2]/div/div/div/form/div/div/div/div/div[1]/div/div/input")).sendKeys(" ");
	  driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div[2]/div/div/div/form/div/div/div/div/div[1]/div/div/input")).sendKeys(Keys.BACK_SPACE);
	  driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div[2]/div/div/div/form/div/div/div/div/div[1]/div/div/input")).sendKeys(fabColorEdited);
	  
	  Thread.sleep(1000);
	  
	  //uncheck the second item
	  driver.findElement(By.xpath("//*[@id='yarn-color-form']/div/div/div/div/div[2]/div/div[3]/ul/li[2]/label/span[1]")).click();
	  String val1 = driver.findElement(By.xpath("//*[@id='yarn-color-form']/div/div/div/div/div[2]/div/div[3]/ul/li[1]/label/span[2]")).getText();
	  
	  Thread.sleep(1000);
	  
	  //click on save and close button
	  driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[1]/div/div/div/div/div/div/span[2]/button")).click();

	  Thread.sleep(1000);
	  
	  String msg1 = driver.findElement(By.xpath("//*[@id='message-body']")).getText();
	  ss.assertEquals(fabColorEdited+" has been updated.", msg1);
	  
	  Thread.sleep(3000);
	  
	  String title = driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[1]/h3")).getText();
	  ss.assertEquals("Yarn Color", title);
	  
	  String name = driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr[1]/td[1]")).getText();
	  String values = driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr[1]/td[2]/div/span[1]")).getText();
	  
	  ss.assertEquals(fabColorEdited, name);
	  ss.assertEquals(val1, values);
	  
	  Reporter.log("Yarn Color sauccessfully edited.");
	  
	  ss.assertAll();
	
	  Thread.sleep(2000);	  
  }
  
  @Test(priority=3)
  public void DeleteYarn() throws Exception{
	  
	  ss = new SoftAssert();
	  
	  //click on delete button
	  driver.findElement(By.xpath("html/body/div[2]/div/div[3]/div/div[2]/div[2]/div/div/div[2]/div/div/div[3]/div[2]/table/tbody/tr[1]/td[3]/button[2]")).click();
	  
	  Thread.sleep(2000);
	  
	  String msg1 = driver.findElement(By.xpath("html/body/div[5]/div[2]/form/div[1]")).getText();
	  ss.assertEquals("Proceed to delete?", msg1);
	  
	  Thread.sleep(1000);
	  
	  //click on OK
	  driver.findElement(By.xpath("html/body/div[5]/div[2]/form/div[3]/button[1]")).click();
	  
	  Thread.sleep(1000);
	  
	  String msg2 = driver.findElement(By.xpath("//*[@id='message-body']")).getText();
	  ss.assertEquals(fabColorEdited+" has been deleted", msg2);
	  
	  Reporter.log("Yarn Color sauccessfully deleted.");
	  
	  Thread.sleep(3000);
	  
	  ss.assertAll();
	  
	  Thread.sleep(1000);	  
  }
  
  //check box item
  public boolean elementHasClass(WebElement element, String active) {
	  return element.getAttribute("class").contains(active);
  }
}
