package fr.lirmm.fairness.assessment;

import com.google.gson.Gson;
import fr.lirmm.fairness.assessment.utils.Environments;

import java.io.*;
import java.util.*;

public class Configuration {

	public static final String FAIR_CONFIG_FILE_PATH = "config/common/questions.config.json";
	private final static String defaultPropFileName = "config";
	private Environments env;
	private static Configuration instance = null;
	private Map<String, Properties> portalsCongigsMap = new HashMap<String, Properties>();
	private Map<?, ?> FairConfigMap = new HashMap<>();

	private Configuration() {
		super();
		switch (System.getProperty("env")){
			case "dev":
				this.env = Environments.DEV;
				break;
			case "prod":
			default :
				this.env = Environments.PROD;
		}

	}

	public Environments getEnv() {
		System.out.println("get env " + this.env );
		return env;
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
		
		Properties properties = this.portalsCongigsMap.get(configScope);
		
		if(properties == null) {
		
			InputStream inputStream = null;
			properties = new Properties();
			
			try {
				String propFileName = String.format("config/%s/%s.%s.properties", configScope,defaultPropFileName, this.env.toString().toLowerCase(Locale.ROOT));
				inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
				
				if (inputStream != null) {
					properties.load(inputStream);
					this.portalsCongigsMap.put(configScope, properties);
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

	public Map<?, ?> getFairConfigs() throws IOException {
		if(this.FairConfigMap.isEmpty()){
			// create Gson instance
			Gson gson = new Gson();
			// create a reader
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FAIR_CONFIG_FILE_PATH);

			if (inputStream != null) {
				Reader reader = new InputStreamReader(inputStream);
				// convert JSON file to map
				this.FairConfigMap = gson.fromJson(reader, Map.class);
				reader.close();
			} else {
				throw new FileNotFoundException("property file '" + FAIR_CONFIG_FILE_PATH + "' not found in the classpath");
			}
		}
		return this.FairConfigMap;

	}
}
