package admin.portal;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class Asher extends Driver{
  
	String url = "http://csr.devvm.drumbi.com:3060/";
	String path = null;
	String username = null;
	String password = null;
	
	public void setPath(String path){
		this.path = this.url + path;
	}
	
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
	
	public String getUrl(){
		return this.url;
	}
	
	public void gotoUrl(){
		driver.get(this.path);
		waitForPageToBeReady();
	}
	
	public void login()throws Exception{
		
		gotoUrl();
		
		try{
		  // wait for profile button to be clickable
		  wait = new WebDriverWait(driver, 10);
		  wait.until(ExpectedConditions.elementToBeClickable(By.id("btnLogin")));
		}catch(Exception e){
		  e.getMessage();
		}
		
		driver.findElement(By.name("emailaddress")).sendKeys(this.username);
		Thread.sleep(1000);
		driver.findElement(By.name("password")).sendKeys(this.password);
		Thread.sleep(1000);
		driver.findElement(By.id("btnLogin")).click();
		  
		waitForPageToBeReady();
		  
		try{
			// wait for profile button to be clickable
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.className("logout")));
		}catch(Exception e){
			e.getMessage();
		}

		//String home = driver.findElement(By.xpath("//*[@id='body']/div/div/div[1]/ol/li/span")).getText();
		//ss.assertEquals(home, "Home");  

		Reporter.log("User successfully login...");
	}
	
	public void logout()throws Exception{
		
		Thread.sleep(1000);
		driver.findElement(By.className("logout")).click();
		
		try{
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[6]/div[2]/form/div[3]/button[1]")));
		}catch(Exception e){
			e.getMessage();
		}
		
		driver.findElement(By.xpath("/html/body/div[6]/div[2]/form/div[3]/button[1]")).click();
		
		try{
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("btnLogin")));
		}catch(Exception e){
			e.getMessage();
		}
		
		Reporter.log("User successfully logout.");
	}
	
	public void checkOption(String element, String text){
		  Boolean found = false;
		  Select select;
		  
		  if(element.equalsIgnoreCase("select.form-control.pov-options") || element.equalsIgnoreCase("select.form-control.common_area-options")){
			  select = new Select(driver.findElement(By.cssSelector(element)));
		  }else{
			  select = new Select(driver.findElement(By.id(element))); 
		  }
		  
		  List<WebElement> allOptions = select.getOptions();
		  for(WebElement list : allOptions){
			  if(list.getText().equalsIgnoreCase(text)){
				  select.selectByVisibleText(list.getText());
				  found=true;
				  break;
			  }
		  }
		  
		  if(!found) {
		      Reporter.log("<font color='red'>Unable to find "+text+" in "+element+"</font>");
		  }
	  }
	
	public String newOrder(JSONObject order)throws Exception{
		
		String poNo = null;
		
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
		String title = driver.findElement(By.className("fs-panel-header-title")).getText();
		ss.assertEquals(title, "New Order");
		
		poNo = driver.findElement(By.cssSelector("span.f_col.col-sm-8.col-sm-9")).getText();
		//poDate = driver.findElement(By.name("purchase_order_date")).getAttribute("value");
		
		Thread.sleep(500);
		driver.findElement(By.id("purchase_order_mpm_number")).sendKeys(order.get("mpm").toString());
		Thread.sleep(500);
		checkOption("po_status", order.get("poStatus").toString());
		Thread.sleep(500);
		driver.findElement(By.id("po_number")).sendKeys(order.get("customerPO").toString());
		Thread.sleep(500);
		checkOption("sales_person", order.get("salesPerson").toString());
		Thread.sleep(500);
		checkOption("purchase_order_ship_via", order.get("shipVia").toString());
		Thread.sleep(500);
		checkOption("purchase_order_sales_term", order.get("salesTerm").toString());
		
		/************************************* Sold To ***********************************/
		// click the sold to field
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("*[id^='rss_']")).click();
		try{
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.attributeToBe(By.cssSelector("*[id^='rss_']"), "class", "r-ss-wrap r-ss-expanded"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("*[id^='rss_'][id$='_list']")));
		}catch(Exception e){
			e.getMessage();
		} 
		
		// input from sold to search
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("*[id^='rss_'][id$='_search']")).sendKeys(order.get("soldTo").toString());
		
		// click the first row
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("*[id^='rss_'][id$='_aria_option_0']")).click();
		/**********************************************************************************/
		Thread.sleep(500);
		driver.findElement(By.name("purchase_order_notes")).sendKeys(order.get("notes").toString());
		
		// scroll down
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.id("sample-tab")));

		JSONObject style = (JSONObject) order.get("style");
		
		if(style.get("sample") != null)	sample((JSONArray) style.get("sample"));
		if(style.get("swatch") != null)	swatch((JSONArray) style.get("swatch"));
		if(style.get("print") != null)	print((JSONArray) style.get("print"));
		
		// click Save & Close button
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='fs-panel-modal']/div/div[2]/div/div[1]/div/div/div/div/div/div/span[2]/button")).click();
			
		try{
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.attributeToBe(By.id("fs-panel-modal"), "class", "fs-panel ms-modal-closed"));
			wait.until(ExpectedConditions.attributeContains(By.id("dna-messagebox"), "class", "messagebox-show"));
		}catch(Exception e){
			e.getMessage();
		}
		String confirmMsg = driver.findElement(By.id("message-body")).getText();
		ss.assertEquals(confirmMsg, "Order has been created");
		try{
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.attributeContains(By.id("dna-messagebox"), "class", "messagebox-hide"));
		}catch(Exception e){
			e.getMessage();
		}

		Reporter.log("PO#: "+poNo+" successfully created.");
		Thread.sleep(1000);
		return poNo;
	}
	
	public void editOrder(JSONObject order)throws Exception{
		  
		if(search(order.get("poNo").toString())){
			// click the row
			Thread.sleep(500);
			driver.findElement(By.id("btn-edit")).click();
			
			try{
				wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.attributeToBe(By.id("fs-panel-modal"), "class", "fs-panel load ms-modal-closed"));
			}catch(Exception e){
				e.getMessage();
			}
			
			Thread.sleep(1000);
			String title = driver.findElement(By.className("fs-panel-header-title")).getText();
			ss.assertEquals(title, "Edit Order");
			
			Thread.sleep(500);
			driver.findElement(By.id("purchase_order_mpm_number")).clear();
			Thread.sleep(500);
			checkOption("po_status", order.get("poStatus").toString());
			Thread.sleep(500);
			driver.findElement(By.id("po_number")).clear();
			driver.findElement(By.id("po_number")).sendKeys(order.get("customerPO").toString());
			Thread.sleep(500);
			checkOption("sales_person", order.get("salesPerson").toString());
			Thread.sleep(500);
			checkOption("purchase_order_ship_via", order.get("shipVia").toString());
			Thread.sleep(500);
			checkOption("purchase_order_sales_term", order.get("salesTerm").toString());
			
			// scroll down
			Thread.sleep(500);
			JavascriptExecutor jse = (JavascriptExecutor)driver;
	  		jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.id("sample-tab")));

	  		JSONObject style = (JSONObject) order.get("style");
	  		
	  		if(style.get("sample") != null)	checkStyleSample((JSONArray) style.get("sample"));
			if(style.get("swatch") != null)	checkStyleSwatch((JSONArray) style.get("swatch"));
			if(style.get("print") != null)	checkStylePrint((JSONArray) style.get("print"));
	  		
			// click Save button
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='fs-panel-modal']/div/div[2]/div/div[1]/div/div/div/div/div/div/span[1]/button")).click();
			
			try{
				wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.attributeContains(By.id("dna-messagebox"), "class", "messagebox-show"));
			}catch(Exception e){
				e.getMessage();
			}
			String confirmMsg = driver.findElement(By.id("message-body")).getText();
			ss.assertEquals(confirmMsg, "Order has been updated");
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
				wait.until(ExpectedConditions.attributeToBe(By.id("fs-panel-modal"), "class", "fs-panel ms-modal-closed"));
			}catch(Exception e){
				e.getMessage();
			}
				
		}else{
			Reporter.log("<font color='red'>Cannot find PO No.</font>");
		}
	}
	
	public void sample(JSONArray sample)throws Exception{
		  
		  // click sample tab
		  Thread.sleep(500);
		  driver.findElement(By.id("sample-tab")).click();
		  Reporter.log("SOLID/SAMPLE STYLE:");

		  JSONObject style;
		  for(int i=0; i<sample.size(); i++) {
			  style = (JSONObject) sample.get(i);
			  // click the first row of drop down style list
		  		Thread.sleep(1000);
	  			driver.findElement(By.cssSelector("input.react-datalist-input.form-control.sample_datalist_"+i)).click();
	  			try{
	  				wait = new WebDriverWait(driver, 10);
					wait.until(ExpectedConditions.attributeContains(By.id("sampleList_"+i), "style", "display: block;"));
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='sampleList_"+i+"']/div[contains(.,'"+style.get("style").toString()+"')]")));
	  			}catch(Exception e){
					e.getMessage();
				}
	  			Thread.sleep(500);
	  			driver.findElement(By.xpath("//*[@id='sampleList_"+i+"']/div[contains(.,'"+style.get("style").toString()+"')]")).click();
	  			Thread.sleep(500);
		  	 	driver.findElement(By.id("samplelot_number_"+i)).sendKeys(style.get("lot").toString());
		  	 	Thread.sleep(500);
		  	 	driver.findElement(By.id("sampleyards_"+i)).clear();
		  	 	driver.findElement(By.id("sampleyards_"+i)).sendKeys(style.get("yards").toString());
		  	 	Thread.sleep(500);
		  	 	driver.findElement(By.id("sampleremarks_"+i)).sendKeys(style.get("remark").toString());
	  			Reporter.log("- "+sample.get(i).toString());
			}
		  Thread.sleep(1000);
	  }
	  
	public void swatch(JSONArray swatch)throws Exception{
		  
		  // click sample tab
		  Thread.sleep(500);
		  driver.findElement(By.id("swatches-tab")).click();
		  Reporter.log("SWATCHES STYLE:");

		  JSONObject style;
		  for(int i=0; i<swatch.size(); i++) {
			  style = (JSONObject) swatch.get(i);
			  // click the first row of drop down style list
		  		Thread.sleep(1000);
	  			driver.findElement(By.cssSelector("input.react-datalist-input.form-control.swatch_datalist_"+i)).click();
	  			try{
	  				wait = new WebDriverWait(driver, 10);
					wait.until(ExpectedConditions.attributeContains(By.id("swatchList_"+i), "style", "display: block;"));
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='swatchList_"+i+"']/div[contains(.,'"+style.get("style").toString()+"')]")));
	  			}catch(Exception e){
					e.getMessage();
				}
	  			Thread.sleep(500);
	  			driver.findElement(By.xpath("//*[@id='swatchList_"+i+"']/div[contains(.,'"+style.get("style").toString()+"')]")).click();
	  			Thread.sleep(500);
		  	 	driver.findElement(By.id("swatchswatch_"+i)).clear();
		  	 	driver.findElement(By.id("swatchswatch_"+i)).sendKeys(style.get("swatches").toString());
		  	 	Thread.sleep(500);
		  	 	driver.findElement(By.id("swatchremarks_"+i)).sendKeys(style.get("remark").toString());
	  			Reporter.log("- "+swatch.get(i).toString());
			}
		  Thread.sleep(1000);
	  }
	  
	public void print(JSONArray print)throws Exception{
		  
		  // click sample tab
		  Thread.sleep(500);
		  driver.findElement(By.id("printed-tab")).click();
		  Reporter.log("PRINT FABRIC STYLE:");

		  JSONObject style;
		  for(int i=0; i<print.size(); i++) {
			  style = (JSONObject) print.get(i);
			  // click the first row of drop down style list
		  		Thread.sleep(1000);
	  			driver.findElement(By.cssSelector("input.react-datalist-input.form-control.print_datalist_"+i)).click();
	  			try{
	  				wait = new WebDriverWait(driver, 10);
					wait.until(ExpectedConditions.attributeContains(By.id("printList_"+i), "style", "display: block;"));
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='printList_"+i+"']/div[contains(.,'"+style.get("style").toString()+"')]")));
	  			}catch(Exception e){
					e.getMessage();
				}
	  			Thread.sleep(500);
	  			driver.findElement(By.xpath("//*[@id='printList_"+i+"']/div[contains(.,'"+style.get("style").toString()+"')]")).click();
	  			Thread.sleep(500);
		  	 	driver.findElement(By.id("printpattern_"+i)).sendKeys(style.get("pattern").toString());
		  		Thread.sleep(500);
		  	 	driver.findElement(By.id("printlot_number_"+i)).sendKeys(style.get("lot").toString());
		  	 	Thread.sleep(500);
		  	 	driver.findElement(By.id("printyards_"+i)).clear();
		  	 	driver.findElement(By.id("printyards_"+i)).sendKeys(style.get("yards").toString());
		  	 	Thread.sleep(500);
		  	 	driver.findElement(By.id("printremarks_"+i)).sendKeys(style.get("remark").toString());
	  			Reporter.log("- "+print.get(i).toString());
			}
		  Thread.sleep(1000);
	  }
	
	public void checkOrderFromTable(JSONObject order)throws Exception{
			
		  if(search(order.get("poNo").toString())){
			  String mpmNo = driver.findElement(By.xpath("//*[@id='lastTableRow']/td[2]")).getText();
			  ss.assertEquals(mpmNo, order.get("mpm").toString());
			  if((JSONArray) order.get("print") != null){
				  String orderType1 = driver.findElement(By.cssSelector("span.invoice-status.print-order")).getText();
				  ss.assertEquals(orderType1, "PRINT");
			  }
			  if((JSONArray) order.get("sample") != null){
				  String orderType2 = driver.findElement(By.cssSelector("span.invoice-status.sample-order")).getText();
				  ss.assertEquals(orderType2, "SAMPLE");
			  }
			  if((JSONArray) order.get("swatch") != null){
				  String orderType3 = driver.findElement(By.cssSelector("span.invoice-status.swatch-order")).getText();
				  ss.assertEquals(orderType3, "SWATCH");
			  }
			  String customerPO = driver.findElement(By.xpath("//*[@id='lastTableRow']/td[4]")).getText();
			  ss.assertEquals(customerPO, order.get("customerPO").toString());
			  //String poDate = driver.findElement(By.xpath("//*[@id='lastTableRow']/td[5]")).getText();
			  //ss.assertEquals(poDate, this.poDate);
			  String salesPerson = driver.findElement(By.xpath("//*[@id='lastTableRow']/td[6]")).getText();
			  ss.assertEquals(salesPerson, order.get("salesPerson").toString());
			  String soldTo = driver.findElement(By.xpath("//*[@id='lastTableRow']/td[7]")).getText();
			  ss.assertEquals(soldTo, order.get("soldTo").toString());
			  String poStatus = driver.findElement(By.xpath("//*[@id='lastTableRow']/td[8]")).getText();
			  ss.assertEquals(poStatus, order.get("poStatus").toString());
		  }else{
				Reporter.log("<font color='red'>Cannot find PO No.</font>");
		  }
	  }
	  
	public boolean search(String poNO)throws Exception{
		  
		  Reporter.log("Searching "+poNO);
		  
		  // input in search box
		  Thread.sleep(1000);
		  driver.findElement(By.xpath("//*[@id='orders_table_filter']/label/input")).clear();
		  driver.findElement(By.xpath("//*[@id='orders_table_filter']/label/input")).sendKeys(poNO);
		  try{
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("orders_table_info"), "Showing 1 to 1 of 1 entries"));
		  }catch(Exception e){
			return false;
		  }
		  return true;
	  }
	  
	public void checkStylePrint(JSONArray print)throws Exception{
		  
		  Thread.sleep(500);
		  driver.findElement(By.id("printed-tab")).click();

		  Thread.sleep(500);
		  int rowCount = driver.findElements(By.xpath("//*[@id='order-form']/div/div[3]/div/div[2]/div[2]/table/tbody/tr")).size();
		  System.out.println(rowCount);
		  
		  Thread.sleep(500);
		  JSONObject row;
		  for(int i=0; i<print.size(); i++) {
			  row = (JSONObject) print.get(i);     
			  
			  String style = driver.findElement(By.cssSelector("input.react-datalist-input.form-control.print_datalist_"+i)).getAttribute("value");
			  ss.assertEquals(style, row.get("style").toString());
			  String pattern = driver.findElement(By.id("printpattern_"+i)).getAttribute("value");
			  ss.assertEquals(pattern, row.get("pattern").toString());
			  String lot = driver.findElement(By.id("printlot_number_"+i)).getAttribute("value");
			  ss.assertEquals(lot, row.get("lot").toString());
			  String yards = driver.findElement(By.id("printyards_"+i)).getAttribute("value");
			  ss.assertEquals(yards, row.get("yards").toString());
			  String remark = driver.findElement(By.id("printremarks_"+i)).getAttribute("value");
			  ss.assertEquals(remark, row.get("remark").toString());
			  
		  }
		                                             
		  String value = driver.findElement(By.xpath("//*[@id='order-form']/div/div[3]/div/div[2]/div[2]/table/tbody/tr["+rowCount+"]")).getAttribute("value");
		  try{
			  if(!value.isEmpty() || value != null) Reporter.log("<font color='red'>No extra empty style.</font>");
		  }catch(Exception e){
			  e.getMessage();
		  }
	  }
	  
	public void checkStyleSample(JSONArray sample)throws Exception{
		  
				  Thread.sleep(500);
				  driver.findElement(By.id("sample-tab")).click();

				  Thread.sleep(500);
				  int rowCount = driver.findElements(By.xpath("//*[@id='order-form']/div/div[3]/div/div[2]/div[2]/table/tbody/tr")).size();
				  System.out.println(rowCount);
				  
				  Thread.sleep(500);
				  JSONObject row;
				  for(int i=0; i<sample.size(); i++) {
					  row = (JSONObject) sample.get(i);     

					  String style = driver.findElement(By.cssSelector("input.react-datalist-input.form-control.sample_datalist_"+i)).getAttribute("value");
					  ss.assertEquals(style, row.get("style").toString());
					  //String pattern = driver.findElement(By.id("samplelot_number_"+i)).getAttribute("value");
					  //ss.assertEquals(pattern, row.get("pattern").toString());
					  String lot = driver.findElement(By.id("samplelot_number_"+i)).getAttribute("value");
					  ss.assertEquals(lot, row.get("lot").toString());
					  String yards = driver.findElement(By.id("sampleyards_"+i)).getAttribute("value");
					  ss.assertEquals(yards, row.get("yards").toString());
					  String remark = driver.findElement(By.id("sampleremarks_"+i)).getAttribute("value");
					  ss.assertEquals(remark, row.get("remark").toString());
					  
				  }
				                                             
				  String value = driver.findElement(By.xpath("//*[@id='order-form']/div/div[3]/div/div[2]/div[2]/table/tbody/tr["+rowCount+"]")).getAttribute("value");
				  try{
					  if(!value.isEmpty() || value != null) Reporter.log("<font color='red'>No extra empty style.</font>");
				  }catch(Exception e){
					  e.getMessage();
				  }
		}
	  
	public void checkStyleSwatch(JSONArray swatch)throws Exception{
		  
		  Thread.sleep(500);
		  driver.findElement(By.id("swatches-tab")).click();

		  Thread.sleep(500);
		  int rowCount = driver.findElements(By.xpath("//*[@id='order-form']/div/div[3]/div/div[2]/div[2]/table/tbody/tr")).size();
		  System.out.println(rowCount);
		  
		  Thread.sleep(500);
		  JSONObject row;
		  for(int i=0; i<swatch.size(); i++) {
			  row = (JSONObject) swatch.get(i);     

			  String style = driver.findElement(By.cssSelector("input.react-datalist-input.form-control.swatch_datalist_"+i)).getAttribute("value");
			  ss.assertEquals(style, row.get("style").toString());
			  //String pattern = driver.findElement(By.id("samplelot_number_"+i)).getAttribute("value");
			  //ss.assertEquals(pattern, row.get("pattern").toString());
			  String yards = driver.findElement(By.id("swatchswatch_"+i)).getAttribute("value");
			  ss.assertEquals(yards, row.get("swatches").toString());
			  String remark = driver.findElement(By.id("swatchremarks_"+i)).getAttribute("value");
			  ss.assertEquals(remark, row.get("remark").toString());
			  
		  }
		                                             
		  String value = driver.findElement(By.xpath("//*[@id='order-form']/div/div[3]/div/div[2]/div[2]/table/tbody/tr["+rowCount+"]")).getAttribute("value");
		  try{
			  if(!value.isEmpty() || value != null) Reporter.log("<font color='red'>No extra empty style.</font>");
		  }catch(Exception e){
			  e.getMessage();
		  }
}
	  
	public void checkActivities(JSONObject order){
		  
		  try{
			  wait = new WebDriverWait(driver, 10);
			  wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("rubix-timeline-title")));
			  wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("rubix-timeline-body")));
		  }catch(Exception e){
			  e.getMessage();
		  }
		  
		  driver.findElement(By.name("activity-search")).clear();
		  driver.findElement(By.name("activity-search")).sendKeys(order.get("poNo").toString());
		  driver.findElement(By.name("activity-search")).sendKeys(Keys.ENTER);
		  
		  try{
			  wait = new WebDriverWait(driver, 10);
			  wait.until(ExpectedConditions.attributeContains(By.id("global-preloader"), "class", "show"));
			  wait.until(ExpectedConditions.attributeContains(By.id("global-preloader"), "class", ""));
		  }catch(Exception e){
			  e.getMessage();
		  }

		  String activity = driver.findElement(By.xpath("//*[@class='rubix-timeline-body']/ul/li")).getText();
		  ss.assertEquals(activity, "New order: PO #: "+order.get("poNo")+", MPM #: "+order.get("mpm")+", CUSTOMER PO #: "+order.get("customerPO"));
		  Reporter.log(activity);
	  }
	  
	public boolean searchReport(String poNo, String style)throws Exception{
		  
		  Reporter.log("Searching "+poNo+" in "+style+" style");
		  
		  // input in search box
		  Thread.sleep(1000);
		  driver.findElement(By.xpath("//*[@id='"+style+"_report_table_filter']/label/input")).clear();
		  driver.findElement(By.xpath("//*[@id='"+style+"_report_table_filter']/label/input")).sendKeys(poNo);
		  try{
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.attributeContains(By.id(style+"_report_table_processing"), "style", "display: block;"));
			wait.until(ExpectedConditions.attributeContains(By.id(style+"_report_table_processing"), "style", "display: none;"));
		  }catch(Exception e){
			return false;
		  }
		  String table_row_count = driver.findElement(By.id(style+"_report_table_info")).getText();
		  if(!table_row_count.equalsIgnoreCase("Showing 0 to 0 of 0 entries"))
			  return true;
		  else
			  return false;
	  }
	  
	public void checkReport(JSONObject order, String tab)throws Exception{
		  
		  try{
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.attributeContains(By.id(tab+"_report_table_processing"), "style", "display: none;"));
		  }catch(Exception e){
			e.getMessage();
		  }
		  
		  JSONObject style = (JSONObject) order.get("style");
		  
		  if(searchReport(order.get("poNo").toString(), tab) && style.get(tab) != null){
			   
				  JSONArray color = (JSONArray) style.get(tab);
				  Thread.sleep(500);
				  String table_row_count = driver.findElement(By.id(tab+"_report_table_info")).getText();
				  ss.assertEquals(table_row_count, "Showing 1 to "+color.size()+" of "+color.size()+" entries");
				  System.out.println(tab+table_row_count);
				  JSONObject row;
				  for(int i=0; i<color.size(); i++) {
					  row = (JSONObject) color.get(i);
					  String customer = driver.findElement(By.xpath("//*[@id='"+tab+"_report_table']/tbody/tr["+(i+1)+"]/td[2]")).getText();
					  ss.assertEquals(customer, order.get("soldTo"));
					  String salesPerson = driver.findElement(By.xpath("//*[@id='"+tab+"_report_table']/tbody/tr["+(i+1)+"]/td[3]")).getText();
					  ss.assertEquals(salesPerson, order.get("salesPerson"));
					  String Style = driver.findElement(By.xpath("//*[@id='"+tab+"_report_table']/tbody/tr["+(i+1)+"]/td[4]")).getText();
					  ss.assertEquals(Style, row.get("style").toString());
					  String yards = driver.findElement(By.xpath("//*[@id='"+tab+"_report_table']/tbody/tr["+(i+1)+"]/td[5]")).getText();
					  String TD5 = null;
					  if(tab.equalsIgnoreCase("swatch")){
						  TD5 = "swatches";
					  }else{
						  TD5 = "yards";
					  }
					  ss.assertEquals(yards, row.get(TD5).toString());
					  String poNo = driver.findElement(By.xpath("//*[@id='"+tab+"_report_table']/tbody/tr["+(i+1)+"]/td[6]")).getText();
					  ss.assertEquals(poNo, order.get("poNo"));
					  String customerPO = driver.findElement(By.xpath("//*[@id='"+tab+"_report_table']/tbody/tr["+(i+1)+"]/td[7]")).getText();
					  ss.assertEquals(customerPO, order.get("customerPO"));
					  String workOrderNo = driver.findElement(By.xpath("//*[@id='"+tab+"_report_table']/tbody/tr["+(i+1)+"]/td[8]")).getText();
					  ss.assertEquals(workOrderNo, order.get("mpm"));
					  Reporter.log("Customer: "+customer+", Sales Person: "+salesPerson+", Style: "+Style+", Yards: "+yards+", PO No: "+poNo+", Customer PO N.: "+customerPO+", Work Order No.: "+workOrderNo);
				  }
		  }else{
			  Reporter.log("Unable to find "+order.get("poNo")+" in "+tab+" report.");
		  }
	  }
	
	public void View_Order(JSONObject order)throws Exception{
		
		if(search(order.get("poNo").toString())){
			// click the row
			Thread.sleep(500);
			driver.findElement(By.id("btn-view")).click();
			
			try{
				wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.attributeToBe(By.id("fs-panel-modal"), "class", "fs-panel load ms-modal-closed"));
			}catch(Exception e){
				e.getMessage();
			}
			
			Thread.sleep(1000);
			String title = driver.findElement(By.className("fs-panel-header-title")).getText();
			ss.assertEquals(title, "View Order");

			String currentUrl = driver.getCurrentUrl();
			ss.assertEquals(currentUrl, getUrl()+"view/po"+order.get("poNo"));
			String companyAddress1 = driver.findElement(By.xpath("//*[@id='order_doc']/div[1]/div[1]/div[1]")).getText();
			ss.assertEquals(companyAddress1, "950 S. BOYLE AVE. ");
			String companyAddress2 = driver.findElement(By.xpath("//*[@id='order_doc']/div[1]/div[1]/div[2]")).getText();
			ss.assertEquals(companyAddress2, "LOS ANGELES, CA 90023 ");
			String companyAddress3 = driver.findElement(By.xpath("//*[@id='order_doc']/div[1]/div[1]/div[3]")).getText();
			ss.assertEquals(companyAddress3, "(323) 268-1218, Fax: (323) 268-2737");
			String soldTo = driver.findElement(By.xpath("//*[@id='order_doc']/div[2]/div[1]/div[2]/div[1]")).getText();
			ss.assertEquals(soldTo, order.get("soldTo").toString());
			String shipTo = driver.findElement(By.xpath("//*[@id='order_doc']/div[2]/div[2]/div[2]/div[1]")).getText();
			ss.assertEquals(shipTo, order.get("shipTo").toString());
			
			//DateFormat dateFormat = new SimpleDateFormat("MM dd yy");
			//Date date = new Date();
			//System.out.println(dateFormat.format(date));
			
			//String currentDate = driver.findElement(By.xpath("//*[@id='order_doc']/div[2]/div[3]/div[2]/span[2]")).getText();
			//ss.assertEquals(currentDate, this.soldTo);

			String customerPONo = driver.findElement(By.xpath("//*[@id='order_doc']/div[3]/div[1]/div[2]/span[2]")).getText();
			ss.assertEquals(customerPONo, order.get("customerPO").toString().toUpperCase());

			String shipVia = driver.findElement(By.xpath("//*[@id='order_doc']/div[5]/div[1]/div[2]/span[2]")).getText();
			ss.assertEquals(shipVia, order.get("shipVia").toString().toUpperCase());
			
			String orderedBy = driver.findElement(By.xpath("//*[@id='order_doc']/div[5]/div[2]/div[2]/span[2]")).getText();
			ss.assertEquals(orderedBy, order.get("salesPerson").toString().toUpperCase());
			
			String salesPerson = driver.findElement(By.xpath("//*[@id='order_doc']/div[5]/div[3]/div[2]/span[2]")).getText();
			ss.assertEquals(salesPerson, order.get("salesPerson").toString().toUpperCase());

			String salesTerm = driver.findElement(By.xpath("//*[@id='order_doc']/div[4]/div[3]/div[2]/span[2]")).getText();
			ss.assertEquals(salesTerm, order.get("salesTerm").toString().toUpperCase());
						
			JSONObject style = (JSONObject) order.get("style");
			if(style.get("sample") != null) viewStyleSample((JSONArray) style.get("sample"));
			if(style.get("swatch") != null) viewStyleSwatch((JSONArray) style.get("swatch"));
			if(style.get("print") != null) viewStylePrint((JSONArray) style.get("print"));

			String footer1 = driver.findElement(By.xpath("//*[@id='order_doc']/div[11]/div[1]")).getText();
			ss.assertEquals(footer1, "Click it, Search it, Browse it, Love it, www.asherconcepts.com");
			footer1 = driver.findElement(By.xpath("//*[@id='order_doc']/div[11]/div[2]")).getText();
			ss.assertEquals(footer1, "Any dispute in quality or misunderstanding, we will use Los Angeles Arbitration, and not any court.");
			footer1 = driver.findElement(By.xpath("//*[@id='order_doc']/div[11]/div[3]")).getText();
			ss.assertEquals(footer1, "Thank you for your business and for choosing to work with \"ASHER Fabric Concepts\". Customer hereby agrees to pay interest at rate sellers pays to its factor on all overdue payments, and all costs of collection including reasonable attorney's fees.");
			footer1 = driver.findElement(By.xpath("//*[@id='order_doc']/div[11]/div[4]")).getText();
			ss.assertEquals(footer1, "*Please count immediately - all claims must be made within 5 days after receipt of goods. Shortages must be reported immediately. We are absolutely not responsible for any claims made after goods are cut.");
			footer1 = driver.findElement(By.xpath("//*[@id='order_doc']/div[11]/div[5]")).getText();
			ss.assertEquals(footer1, "*Returns: NO RETURNS will be accepted without a prior written authorization. Credit will not be issued for unauthorized returns. We will not accept the responsibility for any charges incurred by refusing an unauthorized shipment.");

			String authorized = driver.findElement(By.xpath("//*[@id='order_doc']/div[13]/div[1]")).getText();
			ss.assertEquals(authorized, "Authorized by (signature) ,"+order.get("shipTo"));
			
			String thankYou = driver.findElement(By.xpath("//*[@id='order_doc']/div[14]/h4/i/b")).getText();
			ss.assertEquals(thankYou, "THANK YOU FOR YOUR ORDER");
			
		}else{
			Reporter.log("<font color='red'>Cannot find PO No.</font>");
		}
	}
	
	public void viewStyleSample(JSONArray sample)throws Exception{

		Thread.sleep(500);
		String styleTitle = driver.findElement(By.xpath("//*[@id='order_doc']/div[8]/div[1]/div")).getText();
		ss.assertEquals(styleTitle, "SOLID/SAMPLE");
		String styleNameTitle = driver.findElement(By.xpath("//*[@id='order_doc']/div[8]/div[2]/div[1]")).getText();
		ss.assertEquals(styleNameTitle, "Style");
		String colorNameTitle = driver.findElement(By.xpath("//*[@id='order_doc']/div[8]/div[2]/div[2]")).getText();
		ss.assertEquals(colorNameTitle, "Color");
		String lotNameTitle = driver.findElement(By.xpath("//*[@id='order_doc']/div[8]/div[2]/div[3]")).getText();
		ss.assertEquals(lotNameTitle, "Lot #");
		String yardsNameTitle = driver.findElement(By.xpath("//*[@id='order_doc']/div[8]/div[2]/div[4]")).getText();
		ss.assertEquals(yardsNameTitle, "Yards");
		String remarkNameTitle = driver.findElement(By.xpath("//*[@id='order_doc']/div[8]/div[2]/div[5]")).getText();
		ss.assertEquals(remarkNameTitle, "Remarks");
		
		Thread.sleep(500);
		JSONObject row;
		for(int i=0; i<sample.size(); i++) {
			row = (JSONObject) sample.get(i); 
			
			String styleName = driver.findElement(By.xpath("//*[@id='order_doc']/div[8]/div[3]/div[1]")).getText();
			ss.assertEquals(styleName, row.get("style").toString());
			//String pattern = driver.findElement(By.id("samplelot_number_"+i)).getAttribute("value");
			//ss.assertEquals(pattern, row.get("pattern").toString());
			String lot = driver.findElement(By.xpath("//*[@id='order_doc']/div[8]/div[3]/div[3]")).getText();
			ss.assertEquals(lot, row.get("lot").toString());
			String yards = driver.findElement(By.xpath("//*[@id='order_doc']/div[8]/div[3]/div[4]")).getText();
			ss.assertEquals(yards, row.get("yards").toString());
			String remark = driver.findElement(By.xpath("//*[@id='order_doc']/div[8]/div[3]/div[5]")).getText();
			ss.assertEquals(remark, row.get("remark").toString());
		}
	}

	public void viewStyleSwatch(JSONArray swatch)throws Exception{
		Thread.sleep(500);
		String styleTitle = driver.findElement(By.xpath("//*[@id='order_doc']/div[9]/div[1]/div")).getText();
		ss.assertEquals(styleTitle, "SWATCHES");
		String styleNameTitle = driver.findElement(By.xpath("//*[@id='order_doc']/div[9]/div[2]/div[1]")).getText();
		ss.assertEquals(styleNameTitle, "Style");
		String colorNameTitle = driver.findElement(By.xpath("//*[@id='order_doc']/div[9]/div[2]/div[2]")).getText();
		ss.assertEquals(colorNameTitle, "Color");
		String yardsNameTitle = driver.findElement(By.xpath("//*[@id='order_doc']/div[9]/div[2]/div[3]")).getText();
		ss.assertEquals(yardsNameTitle, "Swatches");
		String remarkNameTitle = driver.findElement(By.xpath("//*[@id='order_doc']/div[9]/div[2]/div[4]")).getText();
		ss.assertEquals(remarkNameTitle, "Remarks");
		
		Thread.sleep(500);
		JSONObject row;
		for(int i=0; i<swatch.size(); i++) {
			row = (JSONObject) swatch.get(i); 
			
			String styleName = driver.findElement(By.xpath("//*[@id='order_doc']/div[9]/div[3]/div[1]")).getText();
			ss.assertEquals(styleName, row.get("style").toString());
			//String pattern = driver.findElement(By.id("samplelot_number_"+i)).getAttribute("value");
			//ss.assertEquals(pattern, row.get("pattern").toString());
			String swatches = driver.findElement(By.xpath("//*[@id='order_doc']/div[9]/div[3]/div[3]")).getText();
			ss.assertEquals(swatches, row.get("swatches").toString());
			String remark = driver.findElement(By.xpath("//*[@id='order_doc']/div[9]/div[3]/div[4]")).getText();
			ss.assertEquals(remark, row.get("remark").toString());
		}
	}
	
	public void viewStylePrint(JSONArray print)throws Exception{
		
		Thread.sleep(500);
		String styleTitle = driver.findElement(By.xpath("//*[@id='order_doc']/div[10]/div[1]/div")).getText();
		ss.assertEquals(styleTitle, "PRINTED FABRIC");
		String styleNameTitle = driver.findElement(By.xpath("//*[@id='order_doc']/div[10]/div[2]/div[1]")).getText();
		ss.assertEquals(styleNameTitle, "Style");
		String colorNameTitle = driver.findElement(By.xpath("//*[@id='order_doc']/div[10]/div[2]/div[2]")).getText();
		ss.assertEquals(colorNameTitle, "Pattern");
		String lotNameTitle = driver.findElement(By.xpath("//*[@id='order_doc']/div[10]/div[2]/div[3]")).getText();
		ss.assertEquals(lotNameTitle, "Lot #");
		String yardsNameTitle = driver.findElement(By.xpath("//*[@id='order_doc']/div[10]/div[2]/div[4]")).getText();
		ss.assertEquals(yardsNameTitle, "Yards");
		String remarkNameTitle = driver.findElement(By.xpath("//*[@id='order_doc']/div[10]/div[2]/div[5]")).getText();
		ss.assertEquals(remarkNameTitle, "Remarks");
		
		Thread.sleep(500);
		JSONObject row;
		for(int i=0; i<print.size(); i++) {
			row = (JSONObject) print.get(i); 
			
			String styleName = driver.findElement(By.xpath("//*[@id='order_doc']/div[10]/div[3]/div[1]")).getText();
			ss.assertEquals(styleName, row.get("style").toString());
			String pattern = driver.findElement(By.xpath("//*[@id='order_doc']/div[10]/div[3]/div[2]")).getText();
			ss.assertEquals(pattern, row.get("pattern").toString());
			String lot = driver.findElement(By.xpath("//*[@id='order_doc']/div[10]/div[3]/div[3]")).getText();
			ss.assertEquals(lot, row.get("lot").toString());
			String yards = driver.findElement(By.xpath("//*[@id='order_doc']/div[10]/div[3]/div[4]")).getText();
			ss.assertEquals(yards, row.get("yards").toString());
			String remark = driver.findElement(By.xpath("//*[@id='order_doc']/div[10]/div[3]/div[5]")).getText();
			ss.assertEquals(remark, row.get("remark").toString());
		}
	}
}
