package org.qa.restapi.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.qa.restapi.base.TestBase;
import org.qa.restapi.client.RestClientGetReq;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RestClientGetReqTest extends TestBase {


	RestClientGetReq restclient;
	
	public RestClientGetReqTest() throws FileNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		
		String endpointurl = prop.getProperty("endpointurl");
		String serviceurl = prop.getProperty("apiurl");
		
		String geturl = endpointurl+serviceurl;
		
		restclient = new RestClientGetReq();
		
		// get call with out header attached 
		//restclient.get(geturl);
		
		HashMap<String,String> headermap = new HashMap<String,String>();
		
		headermap.put("content-type", "application/json");
		headermap.put("content-encoding", "br");
		headermap.put("x-powered-by", "Express");
		
		// get call with header attached 
		restclient.get(geturl, headermap);
	}
	
	// Getting Status Code
	@Test(priority=1)
	public void getStatusCodeTest() {
		int statuscode=restclient.getStatusCode();
		System.out.println("Status Code is:" + statuscode);
		
		Assert.assertEquals(statuscode, 200, "Expected Status Code is 200 "+"found "+statuscode);
		
	}
	
	// Getting All Headers
	@Test(priority=2)
	public void getALLHeadersTest() {
		
		Header allheader[]=restclient.getAllHeaders();
		
		HashMap<String, String> allheadermap = new HashMap<String, String>();
		
		for(Header h: allheader) {
			
			allheadermap.put(h.getName(), h.getValue());
		}
		
		System.out.println("Headers are:"+allheadermap);
		
		Assert.assertTrue(allheader.length>0, "Headers not present in Response");
	}
	
	// Getting Response Json
	@Test(priority=3)
	public void getResponseInJsonTest() throws ParseException, IOException {
		
		JSONObject jobj = restclient.getResponseInJson();
		
		System.out.println("After Parsing Response JSON from API");
		System.out.println(jobj);
	}
	
	@Test(priority=4)
	public void getStarttimeTest() throws ParseException, IOException {
		String starttimevalue=restclient.getStarttimeData();
		System.out.println("Start time is:"+starttimevalue);
		//Assert.assertEquals(starttimevalue, "1531660080000");
	}
	
	@Test(priority=5)
	public void getStoreNameFromArrayTest() throws ParseException, IOException {
	
		String storename=restclient.getDataArrayFirstElementData();
		System.out.println("Store Name is:"+storename);
		Assert.assertEquals(storename, "Store30.298");
	}
}
