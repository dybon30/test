package admin.portal;

import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Invoices extends Driver{
	String invNo = null;
	String salesPerson = null;
	String client = null;
	String status = null;
	String invDate = null;
	String dueDate = null;
	String invTotal = null;
	String balanceDue = null;
	String company = null;
	
	String searchInvoice = "52386";
	
	Settings login = new Settings();
	SoftAssert ss = new SoftAssert();
	
  @Test(priority=1)
  public void ViewInvoice() throws Exception{
	  
	  login.login();
	  
	  //click on invoices menu
	  driver.findElement(By.xpath("html/body/div[2]/div/div[1]/div[2]/div/div[1]/div/div/div/div/div/ul/li[5]/a/span[1]")).click();
	  
	  Thread.sleep(3000);
	  
	  //check if it is in the Customer page
	  String pageTitle = driver.findElement(By.xpath("//*[@id='body']/div/div[2]/div[2]/div/div/div[1]/h3")).getText();
	  ss.assertEquals(pageTitle, "Invoice List");
	  
	  //log to the status to console
	  if(pageTitle.equalsIgnoreCase("Invoice List")){
		  Reporter.log(pageTitle + " page succesfully loaded.");
	  }else{
		  Reporter.log("Failed to load " + pageTitle);
	  }
	  
	  searchInvoice();
	  
	  Thread.sleep(1000);
	  
	  salesPerson = driver.findElement(By.xpath("//*[@id='invoice_table']/tbody/tr/td[2]")).getText();
	  client = driver.findElement(By.xpath("//*[@id='invoice_table']/tbody/tr/td[3]")).getText();
	  company = driver.findElement(By.xpath("//*[@id='invoice_table']/tbody/tr/td[4]")).getText();
	  status = driver.findElement(By.xpath("//*[@id='invoice_table']/tbody/tr/td[5]")).getText();
	  invDate = driver.findElement(By.xpath("//*[@id='invoice_table']/tbody/tr/td[6]")).getText();
	  dueDate = driver.findElement(By.xpath("//*[@id='invoice_table']/tbody/tr/td[7]")).getText();
	  invTotal = driver.findElement(By.xpath("//*[@id='invoice_table']/tbody/tr/td[8]")).getText();
	  balanceDue = driver.findElement(By.xpath("//*[@id='invoice_table']/tbody/tr/td[9]")).getText();
	  
	  Thread.sleep(1000);
	  
	  //click on View Invoices
	  driver.findElement(By.xpath("//*[@id='btn-view']")).click();
	  
	  Thread.sleep(1000);
	  
	  String title = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[1]/p")).getText();
	  ss.assertEquals(title, "View Invoice");

	  //log to the status to console
	  if(title.equalsIgnoreCase("View Invoice")){
		  Reporter.log(title + " panel succesfully loaded.");
	  }
	  
	  String invNoV = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/form/div/div[1]/div[3]/div[2]")).getText();
	  ss.assertEquals(invNoV, searchInvoice);
	  consoleLog(invNoV, searchInvoice);
	  
	  String statusV = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/form/div/div[1]/div[3]/div[4]")).getText();
	  ss.assertTrue(status.equalsIgnoreCase(statusV));
	  consoleLog(statusV, status);
	  
	  String salesPersonV = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/form/div/div[3]/table/tbody/tr[1]/td[6]")).getText();
	  ss.assertEquals(salesPersonV, salesPerson);
	  consoleLog(salesPersonV, salesPerson);
	  
	  String invDateV = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/form/div/div[1]/div[3]/div[6]")).getText();
	  String fDate1 = formatDate(invDateV);
	  ss.assertEquals(fDate1, invDate);
	  consoleLog(fDate1, invDate);
	  
	  String companytV = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/form/div/div[2]/div[1]/div[2]/span")).getText();
	  ss.assertEquals(companytV, company);
	  consoleLog(companytV, company);
	  
	  String clientV = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/form/div/div[2]/div[1]/div[3]/span")).getText();
	  ss.assertEquals(clientV, client);
	  consoleLog(clientV, client);
	  
	  String dueDateV = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/form/div/div[3]/table/tbody/tr[2]/td[2]")).getText();
	  String fDate2 = formatDate(dueDateV);
	  ss.assertEquals(fDate2, dueDate);
	  consoleLog(fDate2, dueDate);
	  
	  String invTotalV = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/form/div/div[6]/div[1]/div[2]/div[2]/span[2]")).getText();
	  ss.assertEquals(invTotalV, invTotal.replace("$",""));
	  consoleLog(invTotalV, invTotal.replace("$",""));
	  
	  String balanceDueV = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div/div[2]/div/div/div/form/div/div[6]/div[2]/div[2]/div[2]/span[2]")).getText();
	  ss.assertEquals(balanceDueV, balanceDue.replace("$",""));
	  consoleLog(balanceDueV, balanceDue.replace("$",""));
	  
	  Thread.sleep(1000);
	  
	  //click on close button
	  driver.findElement(By.xpath("//*[@id='fs-panel-modal']/div/div[1]/a")).click();
	  
	  Reporter.log("View Invoice successul.");
	  
	  ss.assertAll();
	  
	  Thread.sleep(2000);
  }
  
  @Test(priority=2)
  public void ViewPayment() throws Exception{
	  
	  searchInvoice();
	  
	  Thread.sleep(1000);
	  
	  //click on View Payments
	  driver.findElement(By.xpath("//*[@id='btn-edit']")).click();
	  
	  Thread.sleep(1000);
	  
	  String panelTitle = driver.findElement(By.xpath("html/body/div[2]/div/div[5]/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[1]/h3")).getText();
	  ss.assertEquals(panelTitle, "View Payments List");
	  
	  //log to the status to console
	  if(panelTitle.equalsIgnoreCase("View Payments List")){
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
  
  public String formatDate(String date){
	  	String fDate = null;
	  	String split[] = new String[3];
		
		StringTokenizer tok = new StringTokenizer(date, ", ");
		split[0] = tok.nextToken();
		split[1] = tok.nextToken();
		split[2] = tok.nextToken();
		
		
		switch(split[0]){
		case "January": split[0] = "01"; break;
		case "February": split[0] = "02"; break;
		case "March": split[0] = "03"; break;
		case "April": split[0] = "04"; break;
		case "May": split[0] = "05"; break;
		case "June": split[0] = "06"; break;
		case "July": split[0] = "07"; break;
		case "August": split[0] = "08"; break;
		case "September": split[0] = "09"; break;
		case "October": split[0] = "10"; break;
		case "November": split[0] = "11"; break;
		case "December": split[0] = "12"; break;
		}
		
		fDate = split[0]+"/"+split[1]+"/"+split[2];
	  
	  return fDate;
  }
  
  public void searchInvoice(){
	  //search for specific item
	  driver.findElement(By.xpath("//*[@id='invoice_table_filter']/label/input")).clear();
	  driver.findElement(By.xpath("//*[@id='invoice_table_filter']/label/input")).sendKeys(searchInvoice);
	  
	  wait = new WebDriverWait(driver, 15);
	  wait.until(ExpectedConditions.textToBe(By.xpath("//*[@id='invoice_table_info']"), "Showing 1 to 1 of 1 entries"));
	  
	  //check if invoice search result is correct
	  String invoice = driver.findElement(By.xpath("//*[@id='invoice_table']/tbody/tr/td[1]")).getText();
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
