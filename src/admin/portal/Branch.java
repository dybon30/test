package admin.portal;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Branch extends Driver{

	JSONObject item = new JSONObject();
	Asher user = new Asher();
	Settings set = new Settings();
	
	@SuppressWarnings("unchecked")
	@Test(priority=1)
	public void AddBranch() throws Exception{
		
		user.setPath("branch");
		this.user.setUsername("admin@email.com");
		this.user.setPassword("password123");
		this.user.login();
		user.gotoUrl();
		ss = new SoftAssert();
		
		this.item.put("title", "Branches");
		this.item.put("name", "Automation Test");
		this.item.put("textFieldID", "branch");
		this.item.put("infoField", "sync_status_tbl_info");
		this.item.put("tableRow", "//*[@id='sync_status_tbl']/tbody/tr/td[1]");
		this.item.put("filterField", "//*[@id='sync_status_tbl_filter']/label/input");
		
		set.add(item);
		
		Thread.sleep(2000);
		ss.assertAll();
	}
	
	@SuppressWarnings("unchecked")
	@Test(priority=2)
	public void EditBranch() throws Exception{
		
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
	public void checkNewBranchFromUsers()throws Exception{
		
		ss = new SoftAssert();
		user.setPath("users");
		user.gotoUrl();
		
		// click new user button
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button.btn.btn-lg.btn-success")).click();
		
		try{
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.attributeToBe(By.id("fs-panel-modal"), "class", "fs-panel load ms-modal-closed"));
		}catch(Exception e){
			e.getMessage();
		}
		Thread.sleep(1000);
		user.checkOption(item.get("textFieldID").toString(), item.get("name").toString());
		
		Thread.sleep(2000);
		ss.assertAll();
	}

}
