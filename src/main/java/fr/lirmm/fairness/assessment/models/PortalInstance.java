package fr.lirmm.fairness.assessment.models;

import java.io.IOException;
import java.net.*;
import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;


import fr.lirmm.fairness.assessment.utils.OntologyRestApi;


public class PortalInstance {

	public static final String SERVER_DEFAULT_PORTAL= "SERVER_DEFAULT_PORTAL";
	private String name = null;
	private String url = null;
	private String apikey = null;
	private boolean cacheEnabled = false;
	private boolean privateAPI = false;

	private List<String> ontoacronyms = new ArrayList<String>();

	public PortalInstance(String name , String url, String apikey , boolean cacheEnabled, boolean privateAPI) throws MalformedURLException {
		super();
		this.name = name ;
		this.url = url.replaceFirst("/*$", "");
		this.apikey = apikey;
		this.cacheEnabled = cacheEnabled;
		this.privateAPI = privateAPI;
	}


	public PortalInstance(String url , String apikey , boolean cacheEnabled ,boolean privateAPI) throws URISyntaxException, MalformedURLException {
		this(getDomainName(url),url,apikey,cacheEnabled , privateAPI);
	}


	public static PortalInstance getFromConfiguration(Configuration configuration , String portalName , String apikey) throws IOException {
		Properties prop = configuration.getPortalProperties(portalName);
		if (prop == null)
			throw new IOException();

		String apikeyConfig;
		boolean privateAPI = apikey.trim().equals("");

		if(!privateAPI){
			apikeyConfig = apikey;
		}else {
			apikeyConfig = prop.getProperty("apikey");
		}

		return new PortalInstance(prop.getProperty("name"),
				prop.getProperty("url"),
				apikeyConfig,
				Boolean.parseBoolean(prop.getProperty("cacheEnabled")), privateAPI);
	}

	public static PortalInstance getFromConfiguration(Configuration configuration , String portalName) throws IOException {
		return getFromConfiguration(configuration,portalName,"");
	}

	
	public List<String> getAllOntologiesAcronyms() throws Exception {

		if(ontoacronyms.isEmpty()){
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

	public boolean isPrivateAPI() {
		return privateAPI;
	}

	private static String getDomainName(String url) throws URISyntaxException {
		URI uri = new URI(url);
		String domain = uri.getHost();
		if(domain.startsWith("www."))
			domain = domain.substring(4);

		return  domain.substring(0,domain.indexOf('.'));
	}

}
