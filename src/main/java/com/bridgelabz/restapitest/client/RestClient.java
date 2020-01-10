package com.bridgelabz.restapitest.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author Ankush Kumar Agrawal
 *
 */
public class RestClient {
	
	
	/**
	 * @request GET
	 * @param url
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
//Get Method Without headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient =HttpClients.createDefault();//Create connection		
		HttpGet httpget = new HttpGet(url);	//http get request	  
		CloseableHttpResponse response =httpClient.execute(httpget); //hit GET URL
		return response;
	}	

//Get Method With Headers
		/**
		 * @request GET
		 * @param url
		 * @param headers
		 * @return httpresponse
		 * @throws ClientProtocolException
		 * @throws IOException
		 * @return httpResponse
		 */
		public CloseableHttpResponse getHeader(String url,HashMap<String,String> headers) throws ClientProtocolException, IOException {
			CloseableHttpClient httpClient =HttpClients.createDefault();//Create connection		
			HttpGet httpget = new HttpGet(url);	//http get request	  
			
			for(Map.Entry<String, String> entry:headers.entrySet()) {
				httpget.addHeader(entry.getKey(),entry.getValue());
			}
			
			CloseableHttpResponse response =httpClient.execute(httpget); //hit GET URL
			return response;		
	}
		
	//Post Method
		/**
		 * @param url
		 * @param entityString
		 * @param headerMap
		 * @return response
		 * @throws ClientProtocolException
		 * @throws IOException
		 */
		public CloseableHttpResponse post(String url,String entityString,HashMap<String,String> headerMap) throws ClientProtocolException, IOException {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new StringEntity(entityString));
			
			//For headers
			for(Map.Entry<String,String> entry:headerMap.entrySet()) {
				httpPost.addHeader(entry.getKey(),entry.getValue());
			}
			CloseableHttpResponse response = httpClient.execute(httpPost);
			return response;
		}
}
