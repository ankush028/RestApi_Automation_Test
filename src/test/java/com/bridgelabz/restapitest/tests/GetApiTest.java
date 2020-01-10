package com.bridgelabz.restapitest.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bridgelabz.restapitest.base.TestBase;
import com.bridgelabz.restapitest.client.RestClient;
import com.bridgelabz.restapitest.util.TestUtil;

public class GetApiTest extends TestBase {

	public TestBase base;
	public String serviceurl;
	public String apiUrl;
	public String url;
	public RestClient client;
	
	@BeforeMethod
	public void setUp()    {
		base = new TestBase();
		serviceurl = property.getProperty("URL");
		apiUrl = property.getProperty("serviceUrl");		
		url = serviceurl+apiUrl;
			
	}
	
	@Test		
	public void getTest() throws ClientProtocolException, IOException {
	
	client = new RestClient();
	CloseableHttpResponse response   =client.get(url);	
	
	Assert.assertEquals(response.getStatusLine().getStatusCode(),RESPONSE_CODE_200,"Status code is not matches");
	String responseString = EntityUtils.toString(response.getEntity(),"UTF-8");	
	JSONObject responseJson = new JSONObject(responseString);

	
//2.Per Page
	String perPage = TestUtil.getValueByJpath(responseJson,"/per_page");
	System.out.println("value of perPage is ==>> : "+perPage);
	Assert.assertEquals(Integer.parseInt(perPage),6);

//3. Total Value
	
	String totalValue = TestUtil.getValueByJpath(responseJson,"/total");
	System.out.println("To total value is :"+totalValue);
	Assert.assertEquals(Integer.parseInt(totalValue),12);

//4. Get the value from JSONArray
	
	String lastName = TestUtil.getValueByJpath(responseJson,"/data[3]/last_name");
	System.out.println(lastName);
	}
	
	@Test
	public void getApiTestWithHeader() throws ClientProtocolException, IOException {
		client = new RestClient();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("Content-Type","application/json");
//		hashMap.put("username","test@bridgelabz.com");
//		hashMap.put("password","bridgeit");
//		hashMap.put("Auth token","12345");
		CloseableHttpResponse response   =client.getHeader(url,hashMap);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),RESPONSE_CODE_200,"Status code is not matches");
		String responseString = EntityUtils.toString(response.getEntity(),"UTF-8");	
		JSONObject responseJson = new JSONObject(responseString);
		
		
	//2.Per Page
		String perPage = TestUtil.getValueByJpath(responseJson,"/per_page");
		System.out.println("value of perPage is ==>> : "+perPage);
		Assert.assertEquals(Integer.parseInt(perPage),6);

	//3. Total Value
		
		String totalValue = TestUtil.getValueByJpath(responseJson,"/total");
		System.out.println("To total value is :"+totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue),12);

	//4. Get the value from JSONArray
		
		String lastName = TestUtil.getValueByJpath(responseJson,"/data[0]/last_name");
		System.out.println(lastName);
		
	}
	
	
}
