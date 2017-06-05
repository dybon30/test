package sales;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class AsherSales extends Driver{
  
	String username = null;
	String password = null;
	
	public void setUsername(String uname){
		this.username = uname;
	}
	
	public void setPassword(String upass){
		this.password = upass;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void login()throws Exception{
		
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("1");
		// input username
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]")).sendKeys(this.username);
		System.out.println("2");
		// input password
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[1]//*[1]//*[3]")).sendKeys(this.password);
		
		// click login submit button
		Thread.sleep(1000);
		driver.findElement(By.id("Submit")).click();
		
		try{
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("Add")));
		}catch(Exception e){
			e.getMessage();
		}
		
		Reporter.log("User successfully login.");
		Thread.sleep(10000);
	}
	
	public void logout()throws Exception{
		
		if(!driver.findElement(By.id("Logout")).isDisplayed()){
			// click bread crumb
			Thread.sleep(1000);
			driver.findElement(By.id("menu icon")).click();
		}
				
		// click logout
		Thread.sleep(1000);
		driver.findElement(By.id("Logout")).click();
		
		try{
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("Submit")));
		}catch(Exception e){
			e.getMessage();
		}
		
		Reporter.log("User successfully logout.");
		Thread.sleep(1000);
	}
	
	public void sync()throws Exception{
		
		ss = new SoftAssert();
		
		if(!driver.findElement(By.id("Sync")).isDisplayed()){
			// click bread crumb
			Thread.sleep(1000);
			driver.findElement(By.id("menu icon")).click();
		}
				
		// click sync
		Thread.sleep(1000);
		driver.findElement(By.id("Sync")).click();
		
		try{
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Information")));
			wait.until(ExpectedConditions.elementToBeClickable(By.id("OK")));
		}catch(Exception e){
			e.getMessage();
		}
		
		String msg = driver.findElement(By.xpath("//*[1]//*[1]//*[2]//*[1]//*[1]//*[2]")).getText();
		ss.assertEquals(msg, "Sync started. This process will continue in the background.");
		
		// click OK button
		Thread.sleep(1000);
		driver.findElement(By.id("OK")).click();
		
		Reporter.log("User successfully logout.");
		ss.assertAll();
		Thread.sleep(5000);
	}
	
	public boolean search()throws Exception{
		
		// send keys to search
		Thread.sleep(1000);
		driver.findElement(By.id("Search")).sendKeys("");
		
		try{
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("Empty List")));
		}catch(Exception e){
			e.getMessage();
		}
		
		if(driver.findElement(By.id("Empty List")).isDisplayed()){
			return false;
		}
		return true;
	}
	
	public void checkNewOrder(List<String> expected){
		
		ss = new SoftAssert();
		
		String poNo = driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[2]//*[1]//*[2]//*[1]//*[1]//*[2]//*[1]//*[2]")).getText();
		ss.assertEquals(poNo, expected.get(0));
		
		String salesPerson = driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[2]//*[1]//*[2]//*[1]//*[1]//*[2]//*[1]//*[4]")).getText();
		ss.assertEquals(salesPerson, expected.get(1));
		
		//String date = driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[2]//*[1]//*[2]//*[1]//*[1]//*[2]//*[1]//*[6]")).getText();
		//ss.assertEquals(date, expected.get(2));
		
		String soldTo = driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[2]//*[1]//*[2]//*[1]//*[1]//*[2]//*[1]//*[8]")).getText();
		ss.assertEquals(soldTo, expected.get(2));
		
		String shipTo = driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[2]//*[1]//*[2]//*[1]//*[1]//*[2]//*[1]//*[10]")).getText();
		ss.assertEquals(shipTo, expected.get(3));
		
		ss.assertAll();
	}
	
	public String addNewOrder(List<String> data, int sample, int swatch, int print)throws Exception{
		
		String poNo = null;

		try{
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("Add")));
		}catch(Exception e){
			e.getMessage();
		}		
		// click add button
		Thread.sleep(1000);
		driver.findElement(By.id("Add")).click();
		
		try{
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("Save")));
		}catch(Exception e){
			e.getMessage();
		}                                   
		poNo = driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]")).getText();
		
		// input on mpm
		Thread.sleep(1000);          
		driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[2]")).sendKeys(data.get(0));
		
		Thread.sleep(1000);          
		driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[2]//*[2]//*[1]//*[2]//*[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id(data.get(1))).click();
		
		// input customer po no
		Thread.sleep(1000);			 
		driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[3]//*[1]//*[1]//*[2]")).sendKeys(data.get(2));
		
		// select sales person
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[3]//*[2]//*[1]//*[2]//*[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id(data.get(3))).click();
		
		// select ship via
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[4]//*[1]//*[1]//*[2]//*[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id(data.get(4))).click();
		
		// select sales term
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[4]//*[2]//*[1]//*[2]//*[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id(data.get(5))).click();
		
		// select sold to
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[5]//*[1]//*[1]//*[2]//*[1]")).sendKeys("erika martha");
		Thread.sleep(1000);
		driver.findElement(By.id(data.get(6))).click();
		/*
		// select ship to
		Thread.sleep(1000);
		Select shipTo = new Select(driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[5]//*[2]//*[1]//*[2]//*[1]")));
		shipTo.selectByIndex(1);
		*/
		// input notes
		//Thread.sleep(1000);
		//driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[6]//*[3]")).sendKeys(data.get(7));
		
		print(print);
		swatch(swatch);
		sample(sample);
				
		// click Save button
		Thread.sleep(1000);
		driver.findElement(By.id("Save")).click();
		
		try{
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("Add")));
		}catch(Exception e){
			e.getMessage();
		}
		
		Reporter.log("New Order successfully save.");
		Thread.sleep(5000);
		return poNo;		
	}
	
	public void sample(int rowCount)throws Exception{
		
		// click sample tab
		Thread.sleep(1000);
		driver.findElement(By.id("Solid/Sample")).click();
		
		for(int i=1; i<=rowCount; i++){
			Thread.sleep(1000);                            
			driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[7]//*[4]//*[2]//*["+i+"]//*[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[2]//*[1]//*[1]//*["+i+"]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[7]//*[4]//*[2]//*["+i+"]//*[3]")).sendKeys("Lot# "+i);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[7]//*[4]//*[2]//*["+i+"]//*[4]")).sendKeys(Integer.toString(i));
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[7]//*[4]//*[2]//*["+i+"]//*[5]")).sendKeys("Remarks "+i);
		}
		Thread.sleep(1000);
	}
	
	public void swatch(int rowCount)throws Exception{
		
		// click sample tab
		Thread.sleep(1000);
		driver.findElement(By.id("Swatches")).click();
		
		for(int i=1; i<=rowCount; i++){
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[7]//*[4]//*[2]//*["+i+"]//*[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[2]//*[1]//*[1]//*["+i+"]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[7]//*[4]//*[2]//*["+i+"]//*[3]")).sendKeys(Integer.toString(i));
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[7]//*[4]//*[2]//*["+i+"]//*[4]")).sendKeys("Remarks "+i);
		}
		Thread.sleep(1000);
	}
	
	public void print(int rowCount)throws Exception{
		
		// click sample tab
		Thread.sleep(1000);
		driver.findElement(By.id("Printed Fabric")).click();
		
		for(int i=1; i<=rowCount; i++){
			Thread.sleep(1000); 
			driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[7]//*[4]//*[2]//*["+i+"]//*[1]")).click();
			Thread.sleep(1000);          
			driver.tap(1, 28, 451, 1);
			//driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[2]//*[1]//*[1]//*["+i+"]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[7]//*[4]//*[2]//*["+i+"]//*[2]")).sendKeys("Patterh "+i);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[7]//*[4]//*[2]//*["+i+"]//*[3]")).sendKeys("Lot# "+i);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[7]//*[4]//*[2]//*["+i+"]//*[4]")).sendKeys(Integer.toString(i));
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]//*[1]//*[1]//*[1]//*[1]//*[7]//*[4]//*[2]//*["+i+"]//*[5]")).sendKeys("Remarks "+i);
		}
		Thread.sleep(1000);
	}
	
	
	
	
	
	//*[1]//*[1]//*[1]//*[2]//*[1]//*[2]//*[1]//*[1]//*[2]//*[1] -> top order
}
