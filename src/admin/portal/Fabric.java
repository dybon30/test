package admin.portal;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Fabric extends Driver{
	
	JSONObject item = new JSONObject();
	Asher user = new Asher();
	Settings set = new Settings();
	
	@SuppressWarnings("unchecked")
	@Test(priority=1)
	public void AddFabric() throws Exception{
		
		user.setPath("fabric_color");
		this.user.setUsername("admin@email.com");
		this.user.setPassword("password123");
		this.user.login();
		user.gotoUrl();
		ss = new SoftAssert();
		
		this.item.put("title", "Fabric Color");
		this.item.put("name", "Automation Test");
		this.item.put("textFieldID", "colorid");
		this.item.put("infoField", "fabriccolorstable_info");
		this.item.put("tableRow", "//*[@id='fabriccolorstable']/tbody/tr/td[1]");
		this.item.put("filterField", "//*[@id='fabriccolorstable_filter']/label/input");
		
		JSONArray color = new JSONArray();
		color.add("AB GEO FLORAL");
		color.add("BLACK/BONE");
		item.put("color", color);		
		
		set.add(item);
		
		Thread.sleep(2000);
		ss.assertAll();
	}	
	
	@SuppressWarnings("unchecked")
	@Test(priority=2)
	public void EditFabric() throws Exception{
		
		ss = new SoftAssert();
		
		if(set.search(item)){
			String name = driver.findElement(By.xpath(item.get("tableRow").toString())).getText();
			ss.assertEquals(name, item.get("name").toString());
			
			String actualValues = null;
			JSONArray color = (JSONArray) item.get("color");
			
			String values = driver.findElement(By.xpath("//*[@id='fabriccolorstable']/tbody/tr/td[2]")).getText();
			if(color != null){
				  for(int i=0; i<color.size(); i++){
					  if(i>0){
						  actualValues += ", "+color.get(i).toString();
					  }else{
						  actualValues = color.get(i).toString();
					  }
				  }
			}
			ss.assertEquals(values, actualValues);
			
			color.add("D. NAVY");
			item.put("color", color);
			item.put("name", "Automation Test edited");
			set.edit(item);
		}else{
			Reporter.log("<font color='red'>Cannot find the item</font>");
		}
	
		Thread.sleep(2000);
		ss.assertAll();
	}
 
}
