package sales;

import java.util.ArrayList;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class NewOrder extends Driver{
  
	String mpm = "mpmTest";
	String poStatus = "New";
	String cutomerPoNo = "2365";
	String salesPerson = "Andrew Vonn M. Jurado";
	String shipVia = "Lazada";
	String salesTerm = "Debit";
	String soldTo = "ERIKA MARTHA (KELLY WEARSTLER)";
	String notes = "The quick brown fox jumps over the lazy dog.";
	
	AsherSales user = new AsherSales();
	
	@Test(priority=1)
	public void createNewOrder()throws Exception{
		
		Reporter.log("Create new order with 3 sample, 3 swatch and 3 print using Salesperson account.");
		user.setUsername("dybon.dna@gmail.com");
		user.setPassword("awawAW11!!");
		user.login();
		
		List<String> order = new ArrayList<String>();
		order.add(this.mpm);
		order.add(this.poStatus);
		order.add(this.cutomerPoNo);
		order.add(this.salesPerson);
		order.add(this.shipVia);
		order.add(this.salesTerm);
		order.add(this.soldTo);
		order.add(this.notes);

		String poNo = user.addNewOrder(order,3,3,3);
		
		List<String> expected = new ArrayList<String>();
		expected.add(poNo);
		expected.add(this.salesPerson);
		expected.add(this.soldTo);
		expected.add(this.soldTo);

		user.checkNewOrder(expected);
		user.logout();		
	}
}
