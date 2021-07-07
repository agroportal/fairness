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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OntologyRestApi {

	private final static Set<String> INVALID_METADATA_VALUES = new HashSet<String>(
			Arrays.asList(new String[] { null, "null", "", "[]" }));
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
		JSONObject objMappings = new JSONObject(mappings.toString());
		JSONArray jsonArray = (JSONArray) objMappings.get(metadataname);
		List<String> metadataValue = new ArrayList<String>();

		for (int i = 0; i < jsonArray.length(); i++) {
			metadataValue.add(i, jsonArray.getString(i));
		}

		return (metadataValue);
	}

	public String getOntologyJsonObject(String metadataname) throws JSONException, IOException {
		JsonNode mappings = this.jsonToNode(ontologyMetadata);
		JSONObject objMappings = new JSONObject(mappings.toString());
		String metadataValue;

		metadataValue = objMappings.getJSONObject("ontology").getString(metadataname);

		return (metadataValue);
	}

	public String getSubmissionJsonObject(String metadataname) throws JSONException, IOException {
		JsonNode mappings = this.jsonToNode(ontologyMetadata);
		JSONObject objMappings = new JSONObject(mappings.toString());
		String metadataValue;
        
		metadataValue = objMappings.getString(metadataname);
		//System.out.println(metadataname+ metadataValue);

		return (metadataValue);
	}

	public String getOntologyLinksyJsonObject(String metadataname) throws JSONException, IOException {
		JsonNode mappings = this.jsonToNode(ontologyMetadata);
		String metadataValue;

		metadataValue = mappings.get("ontology").findValue("links").get(metadataname).asText();

		return (metadataValue);
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

	public static String get(String urlToGet, String api_key, String format) throws IOException {
		URL url;
		HttpURLConnection conn;
		String line;
		String result = "";
		try {
			BufferedReader rd = null;
			url = new URL(urlToGet);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "apikey token=" + api_key);
			conn.setRequestProperty("Accept", format);
			int response = conn.getResponseCode();
			if (response != 200) {
				System.out.println("Response Message= " + conn.getResponseMessage() + "  " + urlToGet);
			}
			if (response == 200) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				while ((line = rd.readLine()) != null) {
					result += line;
				}
				rd.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public static boolean isValidMetadatumValue(String value) {
		return !INVALID_METADATA_VALUES.contains(value);
	}
}
