package admin.portal;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ReasonForVisit extends Driver{
	
	JSONObject item = new JSONObject();
	Asher user = new Asher();
	Settings set = new Settings();
	
	@SuppressWarnings("unchecked")
	@Test(priority=1)
	public void AddReason() throws Exception{
		
		user.setPath("visit_reasons");
		this.user.setUsername("admin@email.com");
		this.user.setPassword("password123");
		this.user.login();
		user.gotoUrl();
		ss = new SoftAssert();
		
		this.item.put("title", "Reasons for Visit");
		this.item.put("name", "Automation Test");
		this.item.put("textFieldID", "reason");
		this.item.put("infoField", "visitreasontable_info");
		this.item.put("tableRow", "//*[@id='visitreasontable']/tbody/tr/td[1]");
		this.item.put("filterField", "//*[@id='visitreasontable_filter']/label/input");
		
		set.add(item);
		
		Thread.sleep(2000);
		ss.assertAll();
	}
	
	@SuppressWarnings("unchecked")
	@Test(priority=2)
	public void EditReason() throws Exception{
		
		ss = new SoftAssert();
		
		if(set.search(item)){
			String name = driver.findElement(By.xpath(item.get("tableRow").toString())).getText();
			ss.assertEquals(name, item.get("name").toString());
			item.put("name", "Automation Test edited");
			set.edit(item);
			set.checkItemFromTable(item);
		}else{
			Reporter.log("<font color='red'>Cannot find the item</font>");
		}
	
		Thread.sleep(2000);
		ss.assertAll();
	}
}
