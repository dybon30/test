package admin.portal;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class SalesTerm extends Driver{

	JSONObject item = new JSONObject();
	Asher user = new Asher();
	Settings set = new Settings();
	
	@SuppressWarnings("unchecked")
	@Test(priority=1)
	public void AddSalesTerm() throws Exception{
		
		user.setPath("salesterm");
		this.user.setUsername("admin@email.com");
		this.user.setPassword("password123");
		this.user.login();
		user.gotoUrl();
		ss = new SoftAssert();
		
		this.item.put("title", "Sales Terms");
		this.item.put("name", "Automation Test");
		this.item.put("textFieldID", "name");
		this.item.put("infoField", "salesTermList_info");
		this.item.put("tableRow", "//*[@id='salesTermList']/tbody/tr/td[1]");
		this.item.put("filterField", "//*[@id='salesTermList_filter']/label/input");
		
		set.add(item);
		
		Thread.sleep(2000);
		ss.assertAll();
	}
	
	@SuppressWarnings("unchecked")
	@Test(priority=2)
	public void EditJobTitle() throws Exception{
		
		ss = new SoftAssert();
		
		if(set.search(item)){
			String name = driver.findElement(By.xpath(item.get("tableRow").toString())).getText();
			ss.assertEquals(name, item.get("name").toString());
			item.put("name", "Automation Test edited");
			set.edit(item);
		}else{
			Reporter.log("<font color='red'>Cannot find the item</font>");
		}
	
		Thread.sleep(2000);
		ss.assertAll();
	}
	
	@Test(priority=3)
	public void checkNewSalesTermFromOrder()throws Exception{
		
		ss = new SoftAssert();
		user.setPath("orders");
		user.gotoUrl();
		
		try{
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orders_table_info")));
		}catch(Exception e){
			e.getMessage();
		}
		
		// click the new order button
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button.btn.btn-lg.btn-success")).click();
		
		try{
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.attributeToBe(By.id("fs-panel-modal"), "class", "fs-panel load ms-modal-closed"));
		}catch(Exception e){
			e.getMessage();
		}
		Thread.sleep(1000);
		user.checkOption("purchase_order_sales_term", item.get("name").toString());
		
		Thread.sleep(2000);
		ss.assertAll();
	}
}