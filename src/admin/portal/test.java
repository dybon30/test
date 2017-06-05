package admin.portal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class test{
	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		/*String fDate = null;
		String date = "August 29, 2016";
		String split[] = new String[3];
		
		StringTokenizer tok = new StringTokenizer(date, ", ");
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
		
		fDate = split[0]+"/"+split[1]+"/"+split[2];
		System.out.println("Month: "+split[0]);
		System.out.println("Day: "+split[1]);
		System.out.println("Year: "+split[2]);
		System.out.println(fDate);
		
		String a = "2";
		//char a = '2';
		String c = "swatch1";
		//String b = Integer.toString(a);
		System.out.println(c.substring(0,6)+a+c.substring(7));*/
		
		//SoftAssert ss = new SoftAssert();
		//ss.assertEquals("s", "ss");
		
		/*
			String s = "36.1";
			double ss = Double.parseDouble(s.replaceAll(",", ""));
			double sss = ss - 1.02;
			System.out.println("aw "+sss);
		*/
		/*
		String mpm = "mpmTest";
		String poStatus = "New";
		String cutomerPoNo = "2365";
		String salesPerson = "Andrew Vonn M. Jurado";
		String shipVia = "Lazada";
		String salesTerm = "Debit";
		String soldTo = "Amalia";
		String notes = "The quick brown fox jumps over the lazy dog.";
		
		List<String> order = new ArrayList<String>();
		order.add(mpm);
		order.add(poStatus);
		order.add(cutomerPoNo);
		order.add(salesPerson);
		order.add(shipVia);
		order.add(salesTerm);
		order.add(soldTo);
		order.add(notes);
		
		System.out.println(order.get(0)); */
		
		DateFormat dateFormat = new SimpleDateFormat("MM dd yy");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		
		
		JSONObject order = new JSONObject();
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
		order.put("aw", "test");

		System.out.print(order.get("aw").toString());
		
	}
}