package admin.portal;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class OrderSwatches extends Driver{

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
		
		JSONObject row = new JSONObject();
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
		
		JSONObject style = new JSONObject();

		style.put("swatch", swatch);
		
		order.put("style", style);
		Reporter.log("Create New Order with 3 Swatches Styles.");

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

}
