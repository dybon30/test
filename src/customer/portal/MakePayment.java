package customer.portal;

import admin.portal.Driver;
import admin.portal.SoftAssert;

import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class MakePayment extends Driver{
	
	String fname = "Automation";
	String lname = "Test";
	String email = "automation@test.com";
	String phone = "4567893214";
	String add1 = "0303 California State University of the Philippines Republic";
	String add2 = "Lahug Apas";
	String city = "Cebu City";
	String zip = "60014";
	String nameCard = "Automation Test";
	String numCard = "5555555555";
	String expCard = "12/2026";
	String cvvCard = "123";
	String amountPaid = "1.02";
	
	String receiptNo = null;
	
	String rowValue[] = new String[8];
	
	SoftAssert ss;
	
  @Test(priority=1)
  public void viewInvoice() throws Exception{
	  
	  ss = new SoftAssert();
	  
	  //login to customer portal
	  login();
	  
	  //check if it directs to invoice menu
	  String str1 = driver.findElement(By.xpath("//*[@id='body']/div/div[2]/div[2]/div/div/div[1]/h3")).getText();
	  ss.assertEquals(str1, "Invoice List");
	  
	  Thread.sleep(1000);
	  
	  for(int i=0; i<8; i++) {
		  //get all the values of first row and save
		  rowValue[i] = driver.findElement(By.xpath("//*[@id='invoice_table']/tbody/tr[1]/td["+(i+1)+"]")).getText();
	  }
	  
	  Thread.sleep(1000);
	  
	  //click on view invoice button
	  driver.findElement(By.xpath("//*[@id='invoice_table']/tbody/tr[1]/td[9]/button[1]")).click();
	  
	  Thread.sleep(2000);
	  
	  wait = new WebDriverWait(driver, 20);
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fs-panel-modal']/div/div[1]/p")));
	  
	  //check if its in View Invoice Panel
	  String str2 = driver.findElement(By.xpath("//*[@id='fs-panel-modal']/div/div[1]/p")).getText();
	  ss.assertEquals(str2, "View Invoice");
	  
	  Thread.sleep(1000);
	  
	  //check invoice number if correct
	  String invNo = driver.findElement(By.xpath("//*[@id='print_header']/div[3]/div[2]")).getText();
	  ss.assertEquals(invNo, rowValue[0]);
	  
	  //check sales person if correct
	  String salesRep = driver.findElement(By.xpath("//*[@id='invoice_doc']/div[3]/table/tbody/tr[1]/td[6]")).getText();
	  ss.assertEquals(salesRep, rowValue[1]);
	  
	  //check client if correct
	  String client = driver.findElement(By.xpath("//*[@id='invoice_doc']/div[2]/div[1]/div[2]/span")).getText();
	  ss.assertEquals(client, rowValue[2]);
	  
	  //check if status is correct
	  String status = driver.findElement(By.xpath("//*[@id='print_header']/div[3]/div[4]")).getText();
	  ss.assertTrue(status.equalsIgnoreCase(rowValue[3]));
	  
	  //check if invoice date is correct
	  String invDate = driver.findElement(By.xpath("//*[@id='print_header']/div[3]/div[6]")).getText();
	  ss.assertEquals(formatDate(invDate), rowValue[4]);
	  
	  //check if due date is correct
	  String dueDate = driver.findElement(By.xpath("//*[@id='invoice_doc']/div[3]/table/tbody/tr[2]/td[2]")).getText();
	  ss.assertEquals(formatDate(dueDate), rowValue[5]);
	  
	  //check if invoice total is correct
	  String invTotal1 = driver.findElement(By.xpath("//*[@id='invoice_doc']/div[6]/div[1]/div[2]/div[2]/span[1]")).getText();
	  String invTotal2 = driver.findElement(By.xpath("//*[@id='invoice_doc']/div[6]/div[1]/div[2]/div[2]/span[2]")).getText();
	  ss.assertEquals(invTotal1+invTotal2, rowValue[6]);
	  
	  //check if balance due is correct
	  String balanceDue1 = driver.findElement(By.xpath("//*[@id='invoice_doc']/div[6]/div[2]/div[2]/div[2]/span[1]")).getText();
	  String balanceDue2 = driver.findElement(By.xpath("//*[@id='invoice_doc']/div[6]/div[2]/div[2]/div[2]/span[2]")).getText();
	  ss.assertEquals(balanceDue1+balanceDue2, rowValue[7]);	  
	  
	  ss.assertAll();
	  
	  Reporter.log("Successfully View Invoice");
	  
	  Thread.sleep(2000);
  }
  
  @Test(priority=2)
  public void makePayment() throws Exception{
	  
	  ss = new SoftAssert();
	  
	  //click on make payment button
	  driver.findElement(By.xpath("//*[@id='fs-panel-modal']/div/div[2]/div/div[1]/div/div/div/div/div/div/span[2]/button")).click();
	  
	  Thread.sleep(1000);
	  
	  wait = new WebDriverWait(driver, 20);
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='fs-panel-modal']/div/div[1]/p")));
	  
	  //check if its in Make Payment Panel
	  String title = driver.findElement(By.xpath("//*[@id='fs-panel-modal']/div/div[1]/p")).getText();
	  ss.assertEquals(title, "Make Payment");
	  
	  //clear and input first name
	  driver.findElement(By.xpath("//*[@id='first_name']")).clear();
	  driver.findElement(By.xpath("//*[@id='first_name']")).sendKeys(this.fname);
	  
	  //clear and input last name
	  driver.findElement(By.xpath("//*[@id='last_name']")).clear();
	  driver.findElement(By.xpath("//*[@id='last_name']")).sendKeys(this.lname);
	  
	  //clear and input email
	  driver.findElement(By.xpath("//*[@id='mp_email']")).clear();
	  driver.findElement(By.xpath("//*[@id='mp_email']")).sendKeys(this.email);
	  
	  //clear and input phone
	  driver.findElement(By.xpath("//*[@id='mp_phone']")).clear();
	  driver.findElement(By.xpath("//*[@id='mp_phone']")).sendKeys(this.phone);
	  
	  //clear and input address
	  driver.findElement(By.xpath("//*[@id='mp_address']")).clear();
	  driver.findElement(By.xpath("//*[@id='mp_address']")).sendKeys(this.add1);
	  
	  //clear and input address2
	  driver.findElement(By.xpath("//*[@id='mp_address2']")).clear();
	  driver.findElement(By.xpath("//*[@id='mp_address2']")).sendKeys(this.add2);
	  
	  //clear and input city
	  driver.findElement(By.xpath("//*[@id='mp_city']")).clear();
	  driver.findElement(By.xpath("//*[@id='mp_city']")).sendKeys(this.city);
	  
	  //select state
	  Select salesperson = new Select(driver.findElement(By.xpath("//*[@id='state']")));
	  salesperson.selectByIndex(1);
	  
	  //clear and input zip code
	  driver.findElement(By.xpath("//*[@id='mp_zip']")).clear();
	  driver.findElement(By.xpath("//*[@id='mp_zip']")).sendKeys(this.zip);
	  
	  //clear and input name on card
	  driver.findElement(By.xpath("//*[@id='nameCard']")).clear();
	  driver.findElement(By.xpath("//*[@id='nameCard']")).sendKeys(this.nameCard);
	  
	  //clear and input card number
	  driver.findElement(By.xpath("//*[@id='numCard']")).clear();
	  driver.findElement(By.xpath("//*[@id='numCard']")).sendKeys(this.numCard);
	  
	  //clear and input expiration of the card
	  driver.findElement(By.xpath("//*[@id='expCard']")).clear();
	  driver.findElement(By.xpath("//*[@id='expCard']")).sendKeys(this.expCard);
	  
	  //clear and input cvv
	  driver.findElement(By.xpath("//*[@id='cvvCard']")).clear();
	  driver.findElement(By.xpath("//*[@id='cvvCard']")).sendKeys(this.cvvCard);
	  
	  //clear and input amuont paid
	  driver.findElement(By.xpath("//*[@id='amount_paid']")).clear();
	  driver.findElement(By.xpath("//*[@id='amount_paid']")).sendKeys(this.amountPaid);
	  
	  Thread.sleep(1000);
	  
	  //click on submit button
	  driver.findElement(By.xpath("//*[@id='fs-panel-modal']/div/div[2]/div/div[1]/div/div/div/div/div/div/span/button")).click();
	  
	  Thread.sleep(2000);
	  
	  wait = new WebDriverWait(driver, 20);
	  wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id='fs-panel-modal']/div/div[1]/p"), "View Payment Receipt"));
	  
	  Reporter.log("Payment Successful");
	  
	  Thread.sleep(2000);
	  
	  /****************************** AFTER PAYMENT ******************************/
	  
	  //get the receipt number
	  this.receiptNo = driver.findElement(By.xpath("//*[@id='order_doc']/div[2]/table/tbody/tr/td[3]/p[2]")).getText();

	  //check payment details
	  verifyPaymentDetails();
	  
	  ss.assertAll();
	  
	  Reporter.log("Payment Details are correct");
	  
	  Thread.sleep(2000);
	  
	  //close the panel
	  driver.findElement(By.xpath("//*[@id='fs-panel-modal']/div/div[1]/a[2]")).click();
	  
	  Thread.sleep(2000);	  
  }
  
  @Test(priority=3)
  public void viewPayment() throws Exception{
	  
	  ss = new SoftAssert();
	  
	  //click on payment menu
	  driver.findElement(By.xpath("//*[@id='sidebar-container']/div/div[1]/div/div/div/div/div/ul/li[2]/a")).click();
	  
	  Thread.sleep(2000);
	  
	  //check if it is in payments page
	  String str1 = driver.findElement(By.xpath("//*[@id='body']/div/div[2]/div[2]/div/div/div[1]/h3")).getText();
	  ss.assertEquals(str1, "Payment List");
	  
	  Thread.sleep(1000);
	  
	  //search the receipt number that was recently added
	  driver.findElement(By.xpath("//*[@id='payments_table_filter']/label/input")).sendKeys(this.receiptNo);
	  
	  Thread.sleep(2000);
	  
	  //click on view payment details button
	  driver.findElement(By.xpath("//*[@id='payments_table']/tbody/tr[1]/td[7]/button")).click();
	  
	  Thread.sleep(2000);
	  
	  wait = new WebDriverWait(driver, 20);
	  wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id='fs-panel-modal']/div/div[1]/p"), "Payment Details"));
	  
	  Thread.sleep(1000);
	  
	  String str = driver.findElement(By.xpath("//*[@id='order_doc']/div[2]/table/tbody/tr/td[3]/p[2]")).getText();
	  ss.assertEquals(str, this.receiptNo);
	  
	  //check payment details
	  verifyPaymentDetails();
	  
	  ss.assertAll();
	  
	  Thread.sleep(2000);
  }
  
  public void login() throws Exception{
	  
	  driver.get("http://csr.devvm.drumbi.com:3030/login");
	  
	  waitForPageToBeReady();
	  
	  Reporter.log("Asher Customer Portal launch sauccessfully!");
	  
	  driver.findElement(By.xpath("//*[@id='emailaddress']")).sendKeys("msagalongos@dnamicro.com");
	  
	  Thread.sleep(1000);
	  
	  driver.findElement(By.xpath("//*[@id='password']")).sendKeys("Passw0rd123");
	  
	  Thread.sleep(1000);
	  
	  //click the login button with valid credentials
	  driver.findElement(By.xpath("//*[@id='btnLogin']")).click();
	  
	  wait = new WebDriverWait(driver, 60);
	  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='rubix-nav-header']/div/div/div[3]/div/ul/li[2]/a")));
	  
	  Reporter.log("Customer login successfully!");
	  
	  Thread.sleep(1000);
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
		
		fDate = split[0]+"/0"+split[1]+"/"+split[2];
	  
	  return fDate;
  }
  
  public void verifyPaymentDetails(){
	  
	  //check if name is correct
	  String name = driver.findElement(By.xpath("//*[@id='order_doc']/div[2]/table/tbody/tr/td[1]/p[2]/span")).getText();
	  ss.assertEquals(name, this.fname+" "+this.lname);
	  
	  //check if address is correct
	  String add = driver.findElement(By.xpath("//*[@id='order_doc']/div[2]/table/tbody/tr/td[1]/p[3]/span")).getText();
	  ss.assertEquals(add, this.add1);
	  
	  //check if city, state and zip is correct
	  String city = driver.findElement(By.xpath("//*[@id='order_doc']/div[2]/table/tbody/tr/td[1]/p[4]/span")).getText();
	  ss.assertEquals(city, this.city+", Alabama, "+this.zip);
	  
	  //check invoice number if correct
	  String invNo = driver.findElement(By.xpath("//*[@id='order_doc']/div[3]/table/tbody/tr/td[1]")).getText();
	  ss.assertEquals(invNo, rowValue[0]);
	  
	  //check if total is correct
	  String total = driver.findElement(By.xpath("//*[@id='order_doc']/div[3]/table/tbody/tr/td[3]")).getText();
	  ss.assertEquals(total, rowValue[6]);
	  
	  //check if previous balance is correct
	  String pBalance = driver.findElement(By.xpath("//*[@id='order_doc']/div[4]/table/tbody/tr/td[3]/p[1]")).getText();
	  ss.assertEquals(pBalance, rowValue[7]);
	  
	  //check if payment amount is correct
	  String pAmount = driver.findElement(By.xpath("//*[@id='order_doc']/div[4]/table/tbody/tr/td[3]/p[2]")).getText();
	  ss.assertEquals(pAmount, "$"+this.amountPaid);
	  
	  //check if payment amount is correct
	  String str = "";
	  try{
		  double balance = Double.parseDouble(rowValue[7]);
		  double paid = Double.parseDouble(this.amountPaid);
		  double sum = balance - paid;
	  
		  str = Double.toString(sum);
		  
		  String nBalance = driver.findElement(By.xpath("//*[@id='order_doc']/div[4]/table/tbody/tr/td[3]/p[3]")).getText();
		  ss.assertEquals(nBalance, "$"+str);
	  }catch(NumberFormatException e){
		  System.out.println("Error: "+e.getMessage());
	  }
  }
}
