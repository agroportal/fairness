package fr.lirmm.fairness.assessment.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONException;



import fr.lirmm.fairness.assessment.Configuration;
import fr.lirmm.fairness.assessment.utils.OntologyRestApi;

public class PortalInstance {
	
	private String name = null;
	private String url = null;
	private String apikey = null;
	private boolean cacheEnabled = false;
	
	public PortalInstance() {
		super();
	}
	
	public PortalInstance(Properties prop) {
		super();
		this.name = prop.getProperty("name");
		this.url = prop.getProperty("url");
		this.apikey = prop.getProperty("apikey");
		this.cacheEnabled = Boolean.parseBoolean(prop.getProperty("cacheEnabled"));
	}
	
	public static PortalInstance getInstanceByName(String name) throws IOException {
		PortalInstance instance = null;
		Properties prop = Configuration.getInstance().getProperties(name);
		if(prop != null) {
			instance = new PortalInstance(prop);
		}
		return instance;
	}
	
	public Ontology getOntologyInstanceByAcronym(String acronym) throws JSONException, IOException {
		return new Ontology(acronym, this);
	}
	
	public List<Ontology> getAllOntologies(List<String> ontologiesAcronyms) throws JSONException, IOException {
		List<Ontology> ontologies = new ArrayList<Ontology>(ontologiesAcronyms.size());
		for (String ontologyAcronym : ontologiesAcronyms) {
			ontologies.add(new Ontology(ontologyAcronym, this));
		}
		return ontologies;
	}
	
	public List<Ontology> getAllOntologies() throws JSONException, IOException {
		return this.getAllOntologies(this.getAllOntologiesAcronyms());
	}
	
	public List<String> getAllOntologiesAcronyms() throws JSONException, IOException {
		List<String> ontoacronyms = new ArrayList<String>();

		// Get the available resources
		OntologyRestApi restApi = new OntologyRestApi();
		String resourcesString = OntologyRestApi.get(this.url + "/", this.apikey, "application/json");
		JsonNode resources = restApi.jsonToNode(resourcesString);

		// Follow the ontologies link by looking for the media type in the list of links
		String link = resources.get("links").findValue("ontologies").asText();

		// Get the ontologies from the link we found
		JsonNode ontologies = restApi.jsonToNode(OntologyRestApi.get(link, this.apikey, "application/json"));

		// Get the name and ontology id from the returned list
		for (JsonNode ontology : ontologies) {
			ontoacronyms.add(ontology.get("acronym").asText());
		}

		return (ontoacronyms);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public boolean isCacheEnabled() {
		return this.cacheEnabled;
	}
}
