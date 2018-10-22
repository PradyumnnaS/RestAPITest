package org.qa.restapi.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.qa.restapi.util.TestUtil;

public class RestClientGetReq {

	public CloseableHttpClient httpclient;
	public HttpGet httpgetrequest;
	public CloseableHttpResponse httpresponse;
	public JSONObject jobj;
	
	// Get method without Header
	public void get(String url) throws ClientProtocolException, IOException {
		httpclient = HttpClients.createDefault(); //Creating client connection
		
		httpgetrequest = new HttpGet(url); // Creating Get Request
		
		httpresponse=httpclient.execute(httpgetrequest); //Hit the Get URL and getting response object for the request
			
	}
	
	// Get method with Header
	public void get(String url , HashMap<String, String> headermap) throws ClientProtocolException, IOException {
		httpclient = HttpClients.createDefault(); //Creating client connection
		
		httpgetrequest = new HttpGet(url); // Creating Get Request
		
		for(Map.Entry<String, String> entrymap : headermap.entrySet()) {
			
			httpgetrequest.addHeader(entrymap.getKey(), entrymap.getValue());
		}
		
		httpresponse=httpclient.execute(httpgetrequest); //Hit the Get URL and getting response object for the request
			
	}
	
	public int getStatusCode() {
	
		return httpresponse.getStatusLine().getStatusCode();
	}
	
	public Header[] getAllHeaders() {
		
		Header[] arrayofheader=httpresponse.getAllHeaders();
		
		return arrayofheader;
	}
	
	public JSONObject getResponseInJson() throws ParseException, IOException {
		
		String responsestring=EntityUtils.toString(httpresponse.getEntity(),"UTF-8");
		
		System.out.println("Response in String");
		System.out.println(responsestring);
		
		jobj = new JSONObject(responsestring);
		
		return jobj;
	}
	
	public String getStarttimeData() throws ParseException, IOException {
		String responsestring=EntityUtils.toString(httpresponse.getEntity(),"UTF-8");
		jobj = new JSONObject(responsestring);
		
		String starttimevalue=TestUtil.getValueByJpath(jobj, "/geoStoreDTO/startTimeStamp");
	    
		return starttimevalue;
	}
	public String getDataArrayFirstElementData() throws ParseException, IOException{
		
		String responsestring=EntityUtils.toString(httpresponse.getEntity(),"UTF-8");
		jobj = new JSONObject(responsestring);
		
		String storename=TestUtil.getValueByJpath(jobj, "/geoStoreDTO/storeInfoList[0]/storeName");
	    
		return storename;
	}
	
}
