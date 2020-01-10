package com.bridgelabz.restapitest.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	public Properties property;
	public int RESPONSE_CODE_200 =200;
	public int RESPONSE_CODE_500 =500;
	public int RESPONSE_CODE_400 =400;
	public int RESPONSE_CODE_401 =401;
	public int RESPONSE_CODE_201 =201;
	
	public TestBase() {
		property = new Properties();
		try {
			FileInputStream fis = new FileInputStream("/home/admin1/Desktop/JavaAdvanced/RestApiTest/"
					+ "src/main/java/com/bridgelabz/restapitest/config/config.properties");
			property.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}
	
}
