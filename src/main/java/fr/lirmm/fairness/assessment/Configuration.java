package fr.lirmm.fairness.assessment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Configuration {
	
	private static Configuration instance = null;
	private final static String defaultPropFileName = "config.properties";
	private Map<String, Properties> propertiesMap = new HashMap<String, Properties>();
	
	private Configuration() {
		super();
	}
	
	public static Configuration getInstance() {
		if(instance == null) {
			instance = new Configuration();
		}
		return instance;
	}
	
	public Properties getProperties() throws IOException {
		return this.getProperties("common");
	}
 
	public Properties getProperties(String configScope) throws IOException {
		
		Properties properties = this.propertiesMap.get(configScope);
		
		if(properties == null) {
		
			InputStream inputStream = null;
			properties = new Properties();
			
			try {
				String propFileName = String.format("config/%s/%s", configScope, defaultPropFileName);
				inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
				
				if (inputStream != null) {
					properties.load(inputStream);
					this.propertiesMap.put(configScope, properties);
				} else {
					throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
				}
			} catch (Exception e) {
				System.out.println("Exception: " + e);
				properties = null;
			} finally {
				if(inputStream != null) {
					inputStream.close();
				}
			}
		}
		
		return properties;
	}
}
