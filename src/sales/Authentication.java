package sales;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Authentication extends Driver{
  
	AsherSales user = new AsherSales();
	
	@Test(priority=1)
	public void login() throws Exception{
		
		user.setUsername("dybon.dna@gmail.com");
		user.setPassword("awawAW11!!");
		
		ss = new SoftAssert();
		
		// click login submit button
		Thread.sleep(1000);
		driver.findElement(By.id("Submit")).click();
		
		try{
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Log in")));
		}catch(Exception e){
			e.getMessage();
		}
		
		String errorMsg = driver.findElement(By.xpath("//*[1]//*[1]//*[2]//*[1]//*[1]//*[2]")).getText();
		ss.assertEquals(errorMsg, "Invalid username or password");
		
		Thread.sleep(1000);
		driver.findElement(By.id("OK")).click();
		
		// input username
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[1]//*[1]//*[2]")).sendKeys(user.getUsername());

		// input password
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[1]//*[1]//*[3]")).sendKeys("fasdf");
				
		// click login submit button
		Thread.sleep(1000);
		driver.findElement(By.id("Submit")).click();
		
		try{
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Log in")));
		}catch(Exception e){
			e.getMessage();
		}
		
		errorMsg = driver.findElement(By.xpath("//*[1]//*[1]//*[2]//*[1]//*[1]//*[2]")).getText();
		ss.assertEquals(errorMsg, "Incorrect username or password");
		
		// input password
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[1]//*[1]//*[3]")).clear();
		driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[1]//*[1]//*[1]//*[3]")).sendKeys(user.getPassword());
						
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
		ss.assertAll();
		Thread.sleep(5000);
	}
	
	@Test(priority=2)
	public void logout()throws Exception{
		user.logout();
	}
}
