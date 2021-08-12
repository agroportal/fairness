package fr.lirmm.fairness.assessment;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.lirmm.fairness.assessment.utils.Environments;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

public class Configuration {

	public static final String PROPERTIES_CONFIG_FILE_PATH = "config/common/properties.config.json";
	public static final String FAIR_CONFIG_FILE_PATH = "config/common/questions.config.json";
	public static final String REPOS_CONFIG_FILE_PATH = "config/common/catalogs.config.json";
	public static final String METADATA_VOC_CONFIG_FILE_PATH = "config/common/metadata.voc.config.json";
	private final static String defaultPropFileName = "config";
	private static Configuration instance = null;
	private final Map<String, Properties> portalsConfigsMap = new HashMap<String, Properties>();
	private Map<?, ?> FairConfigMap = new HashMap<>();
	private Map<?,?> reposConfigsMap = new HashMap<>();
	private Map<?,?> metadataVocConfigsMap = new HashMap<>();
	private Map<?,?> propertiesConfigMap = new HashMap<>();

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
		
		Properties properties = this.portalsConfigsMap.get(configScope);
		
		if(properties == null) {
		
			InputStream inputStream = null;
			properties = new Properties();
			
			try {
				String propFileName = String.format("config/%s/%s.properties", configScope,defaultPropFileName);
				inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
				
				if (inputStream != null) {
					properties.load(inputStream);
					this.portalsConfigsMap.put(configScope, properties);
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
		return this.getFileConfigConfig(FAIR_CONFIG_FILE_PATH , FairConfigMap);

	}

	public Map<?,?> getRepositoriesConfig() throws IOException {
		return this.getFileConfigConfig(REPOS_CONFIG_FILE_PATH , reposConfigsMap);
	}

	public Map<?,?> getMetadataVocConfig() throws IOException {
		return  this.getFileConfigConfig(METADATA_VOC_CONFIG_FILE_PATH , metadataVocConfigsMap);
	}
	public List<Map<?,?>> getPropertiesConfig() throws IOException {
		return (List<Map<?, ?>>) this.getFileConfigConfig(PROPERTIES_CONFIG_FILE_PATH , propertiesConfigMap).get("properties");
	}

	private Map<?,?> getFileConfigConfig(String fileName , Map<?,?> out) throws IOException {
		if(out.isEmpty()){
			// create Gson instance
			Gson gson = new Gson();
			// create a reader
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

			if (inputStream != null) {
				Reader reader = new InputStreamReader(inputStream);
				// convert JSON file to map
				out = gson.fromJson(reader , Map.class);

				reader.close();
			} else {
				throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath");
			}

		}

		return out;
	}

}
