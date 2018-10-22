package org.qa.restapi.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public Properties prop;
	public TestBase() throws FileNotFoundException{

	FileInputStream fin = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/org/qa/restapi/config/config.properties");
	
	prop = new Properties();
	
	try {
		prop.load(fin);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	}
}
