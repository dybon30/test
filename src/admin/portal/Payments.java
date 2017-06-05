package admin.portal;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Payments extends Driver{
	String invNo = null;
	String receiptNo = null;
	String paymentDate = null;
	String amountPaid = null;
	String billedTo = null;
	String searchInvoice = "52386";
	
	Settings login = new Settings();
	
	SoftAssert ss = new SoftAssert();
	
  @Test
  public void View() throws Exception{
	  
	  login.login();
	  
	  //click on payments menu
	  driver.findElement(By.xpath("html/body/div[2]/div/div[1]/div[2]/div/div[1]/div/div/div/div/div/ul/li[7]/a/span[1]")).click();
	  
	  Thread.sleep(3000);
	  
	  //check if it is in the Payment page
	  String pageTitle = driver.findElement(By.xpath("//*[@id='body']/div/div[2]/div[2]/div/div/div[1]/h3")).getText();
	  ss.assertEquals(pageTitle, "Payment List");
	  
	  //log to the status to console
	  if(pageTitle.equalsIgnoreCase("Payment List")){
		  Reporter.log(pageTitle + " page succesfully loaded.");
	  }else{
		  Reporter.log("Failed to load " + pageTitle);
	  }
	  
	  searchInvoice();
	  
	  invNo = driver.findElement(By.xpath("//*[@id='payments_table']/tbody/tr[1]/td[1]")).getText();
	  paymentDate = driver.findElement(By.xpath("//*[@id='payments_table']/tbody/tr[1]/td[3]")).getText();
	  receiptNo = driver.findElement(By.xpath("//*[@id='payments_table']/tbody/tr[1]/td[4]")).getText();
	  amountPaid = driver.findElement(By.xpath("//*[@id='payments_table']/tbody/tr[1]/td[5]")).getText();
	  billedTo = driver.findElement(By.xpath("//*[@id='payments_table']/tbody/tr[1]/td[6]")).getText();
	  
	  Thread.sleep(1000);
	  
	  //click on View button
	  driver.findElement(By.xpath("//*[@id='payments_table']/tbody/tr[1]/td[7]/button")).click();
	  
	  Thread.sleep(1000);
	  
	  String title = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[1]/p")).getText();
	  ss.assertEquals(title, "Payment Details");
	  
	  //log to the status to console
	  if(title.equalsIgnoreCase("Payment Details")){
		  Reporter.log(title + " panel succesfully loaded.");
	  }
	  
	  String invNoV = driver.findElement(By.xpath("//*[@id='order_doc']/div[3]/table/tbody/tr/td[1]")).getText();
	  ss.assertEquals(invNoV, invNo);
	  consoleLog(invNoV, invNo);
	  
	  String poNoV = driver.findElement(By.xpath("//*[@id='order_doc']/div[2]/table/tbody/tr/td[3]/p[2]")).getText();
	  ss.assertEquals(poNoV, receiptNo);
	  consoleLog(poNoV, receiptNo);
	  
	  String paymentDateV = driver.findElement(By.xpath("//*[@id='order_doc']/div[2]/table/tbody/tr/td[2]/p[2]")).getText();
	  ss.assertEquals(paymentDate, paymentDateV);
	  consoleLog(paymentDateV, paymentDate);
	  
	  String amountPaidV = driver.findElement(By.xpath("//*[@id='order_doc']/div[4]/table/tbody/tr/td[3]/p[2]")).getText();
	  ss.assertEquals(amountPaid, amountPaidV);
	  consoleLog(amountPaidV, amountPaid);
	  
	  String billedToV = driver.findElement(By.xpath("//*[@id='order_doc']/div[2]/table/tbody/tr/td[1]/p[2]/span")).getText();
	  ss.assertEquals(billedTo, billedToV);
	  consoleLog(billedToV, billedTo);
	  
	  Thread.sleep(1000);
	  
	  //click on close button
	  driver.findElement(By.xpath("//*[@id='fs-panel-modal']/div/div[1]/a")).click();
	  
	  Reporter.log("View Payments successul.");
	  
	  ss.assertAll();
	  
	  Thread.sleep(2000);
  }
  
  public void searchInvoice() throws Exception{
	  //search for specific item
	  driver.findElement(By.xpath("//*[@id='payments_table_filter']/label/input")).clear();
	  driver.findElement(By.xpath("//*[@id='payments_table_filter']/label/input")).sendKeys(searchInvoice);
	  
	  //wait = new WebDriverWait(driver, 15);
	  //wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id='payments_table_info']"), "Showing 1 to 1 of 1 entries"));
	  Thread.sleep(1000);
	  
	  //check if invoice search result is correct
	  String invoice = driver.findElement(By.xpath("//*[@id='payments_table']/tbody/tr[1]/td[1]")).getText();
	  ss.assertEquals(invoice, searchInvoice);
	  
	  //log to the status to console
	  if(invoice.equalsIgnoreCase(searchInvoice)){
		  Reporter.log("Searching for "+ searchInvoice + " successfully found.");
	  }else{
		  Reporter.log("Searching for "+ searchInvoice + " not found.");
	  }
  }
  
  public void consoleLog(String actual, String expected){
	  //log to the status to console
	  if(actual.equalsIgnoreCase(expected)){
		  Reporter.log(actual + " is correct.");
	  }	  
  }
}
