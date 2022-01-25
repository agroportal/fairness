package fr.lirmm.fairness.assessment.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OntologyRestApi {


	private String ontologyMetadata = null;
	private ObjectMapper mapper = null;

	public OntologyRestApi(String ontologyMetadata) {
		super();
		this.ontologyMetadata = ontologyMetadata;
		this.mapper = new ObjectMapper();
	}

	public OntologyRestApi() {
		this(null);
	}

	public List<String> getJsonMetadataArrayObject(String metadataname) throws JSONException, IOException {
		JsonNode mappings = this.jsonToNode(ontologyMetadata);
		List<String> metadataValue = new ArrayList<String>();


		JSONObject objMappings = new JSONObject(mappings.toString());
		try{
			Object tmp = objMappings.get(metadataname);
			if(!JSONObject.NULL.equals(tmp)){
				JSONArray jsonArray = (JSONArray) tmp;
				for (int i = 0; i < jsonArray.length(); i++) {
					metadataValue.add(i, jsonArray.getString(i));
				}
			}
		} catch (Exception e){
			Logger.getAnonymousLogger().info(e.getMessage());
		}



		return (metadataValue);
	}

	public String getOntologyJsonObject(String metadataname) throws JSONException, IOException {
		JsonNode mappings = this.jsonToNode(ontologyMetadata);
		JSONObject objMappings = new JSONObject(mappings.toString());
		String metadataValue="";

		try{
			metadataValue = objMappings.getJSONObject("ontology").getString(metadataname);
		}catch (Exception e){
			Logger.getAnonymousLogger().info(e.getMessage());
		}

		return (metadataValue);
	}

	public String getSubmissionJsonObject(String metadataname) throws JSONException, IOException {
		JsonNode mappings = this.jsonToNode(ontologyMetadata);
		JSONObject objMappings = new JSONObject(mappings.toString());
		String metadataValue ="";
        
		try{
			metadataValue = objMappings.getString(metadataname);
		} catch (Exception e){
			Logger.getAnonymousLogger().info(e.getMessage());
		}

		return (metadataValue);
	}

	public String getOntologyLinksJsonObject(String metadataname) throws JSONException, IOException {
		JsonNode mappings = this.jsonToNode(ontologyMetadata);
		String metadataValue ="{}";
		mappings = mappings.get("ontology");

		if(mappings != null)
			mappings = mappings.findValue("links");

		if(mappings != null)
			mappings = mappings.get(metadataname);
		if(mappings !=null)
			metadataValue = mappings.asText();


		return metadataValue;
	}

	public JsonNode jsonToNode(String json) {
		JsonNode root = null;
		try {
			root = mapper.readTree(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return root;
	}

	public static String get(String urlToGet, String api_key, String format) throws Exception {
		URL url;
		HttpURLConnection conn;
		String line;
		StringBuilder result = new StringBuilder();
		BufferedReader rd = null;
		url = new URL(urlToGet);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", "apikey token=" + api_key);
		conn.setRequestProperty("Accept", format);
		int response = conn.getResponseCode();

		if (response != 200) {
			throw new Exception(conn.getResponseMessage());
		}

		rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		return result.toString();
	}

}
