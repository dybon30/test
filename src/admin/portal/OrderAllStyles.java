package admin.portal;

import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class OrderAllStyles extends Driver{

	Asher user = new Asher();
	Settings login = new Settings();
	JSONObject order = new JSONObject();
	
	@SuppressWarnings("unchecked")
	@Test(priority=1)
	public void New_Order()throws Exception{
		
		this.user.setPath("orders");
		this.user.setUsername("admin@email.com");
		this.user.setPassword("password123");
		this.user.login();
		
		ss = new SoftAssert();
		
		this.order.put("mpm", "MPMTest1");
		this.order.put("poStatus", "NEW");
		this.order.put("customerPO", "51466");
		this.order.put("salesPerson", "Camingue Khimbyrlee");
		this.order.put("shipVia", "ASHER'S DRIVER");
		this.order.put("salesTerm", "Credit Card");
		this.order.put("soldTo", "ERIKA MARTHA");
		this.order.put("shipTo", "ERIKA MARTHA");
		this.order.put("notes", "The quick brown fox jumps over the lazy dog.");
		
		JSONArray sample = new JSONArray();
		JSONObject row = new JSONObject();
		row.put("style", "1153-LIN");
		row.put("lot", "lot 1");
		row.put("yards", 1);
		row.put("remark", "remarks1");
		sample.add(row);
		
		row = new JSONObject();
		row.put("style", "1153-LIN2");
		row.put("lot", "lot 2");
		row.put("yards", 2);
		row.put("remark", "remarks2");
		sample.add(row);
		
		row = new JSONObject();
		row.put("style", "VXR96");
		row.put("lot", "lot 3");
		row.put("yards", 3);
		row.put("remark", "remarks3");
		sample.add(row);
		
		JSONArray swatch = new JSONArray();
		row = new JSONObject();
		row.put("style", "1153-LIN");
		row.put("swatches", 1);
		row.put("remark", "remarks1");
		swatch.add(row);
		
		row = new JSONObject();
		row.put("style", "1153-LIN2");
		row.put("swatches", 2);
		row.put("remark", "remarks2");
		swatch.add(row);
		
		row = new JSONObject();
		row.put("style", "VXR96");
		row.put("swatches", 3);
		row.put("remark", "remarks3");
		swatch.add(row);
		
		JSONArray print = new JSONArray();
		row = new JSONObject();
		row.put("style", "1153-LIN");
		row.put("pattern", "pattern1");
		row.put("lot", "lot 1");
		row.put("yards", 1);
		row.put("remark", "remarks1");
		print.add(row);
		
		row = new JSONObject();
		row.put("style", "1153-LIN2");
		row.put("pattern", "pattern2");
		row.put("lot", "lot 2");
		row.put("yards", 2);
		row.put("remark", "remarks2");
		print.add(row);
		
		row = new JSONObject();
		row.put("style", "VXR96");
		row.put("pattern", "pattern3");
		row.put("lot", "lot 3");
		row.put("yards", 3);
		row.put("remark", "remarks3");
		print.add(row);
		
		JSONObject style = new JSONObject();
		
		style.put("sample", sample);
		style.put("swatch", swatch);
		style.put("print", print);
		
		order.put("style", style);
		Reporter.log("Create New Order with 3 Sample, 3 Swatches and 3 Print Styles.");

		String poNo = this.user.newOrder(order);
		
		this.order.put("poNo", poNo);
		this.user.checkOrderFromTable(order);
		
		Thread.sleep(2000);
		ss.assertAll();
	}
	
	@Test(priority=2)
	public void Activity_and_Reports()throws Exception{
		
		ss = new SoftAssert();
		user.setPath("");
		user.gotoUrl();
		user.checkActivities(order);
		
		Thread.sleep(2000);
		user.setPath("print_report");
		user.gotoUrl();
		user.checkReport(order, "print");
		
		Thread.sleep(2000);
		user.setPath("sample_report");
		user.gotoUrl();
		user.checkReport(order, "sample");
		
		Thread.sleep(2000);
		user.setPath("swatch_report");
		user.gotoUrl();
		user.checkReport(order, "swatch");
		
		Thread.sleep(2000);
		ss.assertAll();
	}
	
	@SuppressWarnings("unchecked")
	@Test(priority=3)
	public void Edit_Order()throws Exception{
		
		ss = new SoftAssert();
		user.setPath("orders");
		user.gotoUrl();
		
		this.order.put("mpm", "");
		this.order.put("poStatus", "COM");
		this.order.put("customerPO", "51644 edited");
		this.order.put("salesPerson", "Khimbyrlee Camingue");
		this.order.put("shipVia", "FedEx Express");
		this.order.put("salesTerm", "Net 30");
		
		this.user.editOrder(order);
		this.user.checkOrderFromTable(order);
		
		Thread.sleep(2000);
		ss.assertAll();
	}
  
  public String formatDate(String date){
	  	String fDate = null;
	  	String split[] = new String[3];
		
		StringTokenizer tok = new StringTokenizer(date, "-");
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
		
		fDate = split[1]+"/"+split[2]+"/"+split[0];
	  
	  return fDate;
  }
  

}
