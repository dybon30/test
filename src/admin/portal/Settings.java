package admin.portal;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class Settings extends Driver{
	
	 public void add(JSONObject item) throws Exception{
		 
		  WebElement createAddButton;

		  //String panelHeaderTitle = driver.findElement(By.xpath("//*[@class='rubix-panel-header']/h3")).getText();
		  //ss.assertEquals(panelHeaderTitle, item.get("title").toString());
	  //System.out.println(panelHeaderTitle);
		  if(item.get("title").toString().equalsIgnoreCase("Sales Terms") || item.get("title").toString().equalsIgnoreCase("Ship Via") || item.get("title").toString().equalsIgnoreCase("PO Status") || item.get("title").toString().equalsIgnoreCase("Fabric Color")){
			  createAddButton = driver.findElement(By.xpath("//*[@id='body']/div/div[1]/div/div/div/span/button"));
		  }else{
			  createAddButton = driver.findElement(By.id("btn-syncall"));
		  }

		  //click on button to add
		  Thread.sleep(2000);
		  createAddButton.click();

		  try{
				// wait for profile button to be clickable
				wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-save")));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn-cancel")));
			}catch(Exception e){
				e.getMessage();
		  }
		  
		  // input name
		  Thread.sleep(500);
		  driver.findElement(By.id(item.get("textFieldID").toString())).sendKeys(item.get("name").toString());
		  
		  if(item.get("title").toString().equalsIgnoreCase("Fabric Color")){
			  JSONArray color = (JSONArray) item.get("color");
			  
			  if(color != null){
				  for(int i=0; i<color.size(); i++){
					  if(searchColor(color.get(i).toString())){
						  Thread.sleep(1000); 
						  driver.findElement(By.className("srch-list-mark")).click();
					  }
				  }
			  }
		  }
		  
		  // click save & close button
		  Thread.sleep(500);
		  driver.findElement(By.id("btn-cancel")).click();
		  
		  try{
				wait = new WebDriverWait(driver, 10);
				//wait.until(ExpectedConditions.attributeToBe(By.cssSelector("div.fs-panel-content.load"), "class", "fs-panel-content unload"));
				wait.until(ExpectedConditions.attributeContains(By.id("dna-messagebox"), "class", "messagebox-show"));
			}catch(Exception e){
				e.getMessage();
			}
			String confirmMsg = driver.findElement(By.id("message-body")).getText();
			ss.assertEquals(confirmMsg, item.get("name").toString()+" has been created.");
			try{
				wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.attributeContains(By.id("dna-messagebox"), "class", "messagebox-hide"));
			}catch(Exception e){
				e.getMessage();
		  }
		  Reporter.log(item.get("name").toString()+" "+item.get("title").toString()+" successfully created.");
		  Thread.sleep(1000);
	  }
	  
   public void edit(JSONObject item) throws Exception{
		
		   // click edit button
		   Thread.sleep(500);
		   driver.findElement(By.id("btn-edit")).click();
		   
		   // input name
		   Thread.sleep(500);
		   driver.findElement(By.id(item.get("textFieldID").toString())).clear();
		   driver.findElement(By.id(item.get("textFieldID").toString())).sendKeys(item.get("name").toString());
		   
		   if(item.get("title").toString().equalsIgnoreCase("Fabric Color")){
				  JSONArray color = (JSONArray) item.get("color");
				  
				  if(color != null){
					  for(int i=0; i<color.size(); i++){
						  if(searchColor(color.get(i).toString())){
							  if(!elementHasClass(driver.findElement(By.xpath("//*[@id='fabric-color-form']/div/div/div/div/div[2]/div/div[3]/ul/li/label/span[1]")), "icon icon-fontello-check")){
								  Thread.sleep(1000);
								  driver.findElement(By.className("colorList")).click();
							  }
						  }
					  }
				  }
		   }
		   
		   // click save button
		   Thread.sleep(500);
		   driver.findElement(By.id("btn-save")).click();
		   
		   try{
				wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.attributeContains(By.id("dna-messagebox"), "class", "messagebox-show"));
			}catch(Exception e){
				e.getMessage();
			}
			String confirmMsg = driver.findElement(By.id("message-body")).getText();
			ss.assertEquals(confirmMsg, item.get("name").toString()+" has been updated.");
			try{
				wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.attributeContains(By.id("dna-messagebox"), "class", "messagebox-hide"));
			}catch(Exception e){
				e.getMessage();
			}
			
			// click close button
			Thread.sleep(1000);
			driver.findElement(By.className("fs-panel-close")).click();
			
			try{
				wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("btn-save")));
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("btn-cancel")));
			}catch(Exception e){
				e.getMessage();
			}
  }
	  
	  
	 public boolean search(JSONObject item) throws Exception{
			
			Reporter.log("Searching "+item.get("name").toString());
  
			Thread.sleep(1000);
			driver.findElement(By.xpath(item.get("filterField").toString())).clear();
			driver.findElement(By.xpath(item.get("filterField").toString())).sendKeys(item.get("name").toString());
			  
			  try{
				  // wait for the data table to be ready
				  wait = new WebDriverWait(driver, 10);
				  wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id(item.get("infoField").toString()), "Showing 1 to 1 of 1 entries"));
			  }catch(Exception e){
				  e.getMessage();
			  }
			  
			  String cntInfo = driver.findElement(By.id(item.get("infoField").toString())).getText();
			  String str = cntInfo.substring(0, 27);

			  if(str.equalsIgnoreCase("Showing 1 to 1 of 1 entries")){
				  Reporter.log(item.get("name").toString()+" successfully found");
				  return true;
			  }
			  
			  return false;
	  }
	  
	  public void checkItemFromTable(JSONObject item)throws Exception{
		  
		  if(search(item)){
			  String name = driver.findElement(By.xpath(item.get("tableRow").toString())).getText();
			  ss.assertEquals(name, item.get("name").toString());
		  }else{
				Reporter.log("<font color='red'>Cannot find the item</font>");
		  }
	  }
	  
	  public boolean searchColor(String color)throws Exception{
		  
		  	Reporter.log("Searching "+color);
	  
			Thread.sleep(1000);
			driver.findElement(By.name("fabric-colors")).clear();
			driver.findElement(By.name("fabric-colors")).sendKeys(color);
			
			Thread.sleep(1000);
			List<WebElement> allElements = driver.findElements(By.xpath("//div[@class='ULcolorList list']/li")); 
		  
			if(allElements.size() >= 1){
				Reporter.log(color+" successfully found");
				return true;
			}
		  
			return false;
	  }

	  public boolean elementHasClass(WebElement element, String active) {
		  return element.getAttribute("class").contains(active);
	  }
	  /*public void selectSubMenu(int i) throws Exception{
		  
		  //select Setting and click on Fabric submenu
		  Actions action = new Actions(driver);
		  WebElement we = driver.findElement(By.xpath("html/body/div[2]/div/div[1]/div[2]/div/div[1]/div/div/div/div/div/ul/li[8]/a"));
		  action.moveToElement(we).moveToElement(driver.findElement(By.xpath("html/body/div[2]/div/div[1]/div[2]/div/div[1]/div/div/div/div/div/ul/li[8]/ul/li["+i+"]/a"))).click().build().perform();
		  
		  switch(i){
		  case 1: this.title = "Fabric Color"; break;
		  case 2: this.title = "Yarn Color"; break;
		  case 3: this.title = "Branch"; break;
		  case 4: this.title = "Job Title"; break;
		  case 5: this.title = "Ship Via"; break;
		  case 6: this.title = "Sales Term"; break;
		  case 7: this.title = "Reasons for Visits"; break;
		  case 8: this.title = "PO Status"; break;
		  }
		  
		  Thread.sleep(3000);
	  }*/
}
