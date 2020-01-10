package com.bridgelabz.restapitest.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.bridgelabz.restapitest.base.TestBase;
import com.bridgelabz.restapitest.client.RestClient;
import com.bridgelabz.restapitest.data.Users;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PostApiTest extends TestBase{
	

		public TestBase base;
		public String serviceurl;
		public String apiUrl;
		public String url;
		public RestClient restClient;
		
		@BeforeMethod
		public void setUp()    {
			base = new TestBase();
			serviceurl = property.getProperty("URL");
			apiUrl = property.getProperty("serviceUrl");		
			url = serviceurl+apiUrl;
				
		}
	

	@Test
	public void postTest() throws IOException {
		restClient = new RestClient();
		HashMap<String,String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type","application/json");
		
		ObjectMapper mapper = new ObjectMapper();
		Users user = new Users("Ankush","SDET");
		//Object to JSON file
		
		mapper.writerWithDefaultPrettyPrinter().writeValue(new File("/home/admin1/Desktop/JavaAdvanced/RestApiTest/src/main/java/com/bridgelabz/restapitest/data/users.json"),user);		
		
		//Java object to JSON in String
		String userJson = mapper.writeValueAsString(user);
		System.out.println(userJson);
		CloseableHttpResponse httpResponse = restClient.post(url,userJson, headerMap);
		
		//Validate response from API
		
		//	1. STATUS CODE
		int code = httpResponse.getStatusLine().getStatusCode();
		System.out.println(code);	
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),RESPONSE_CODE_201);
		// 2.JSON String
		
		String responseString =EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("RESPONSE JSON :"+responseJson);
		
		//JSON to Java Object
		
		Users userResponse = mapper.readValue(responseString,Users.class);
		
		
		Assert.assertTrue(user.getName().equals(userResponse.getName()));
		Assert.assertTrue(user.getJob().equals(userResponse.getJob()));
		
 	}
	
}
