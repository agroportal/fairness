package fr.lirmm.fairness.assessment.models;

import java.io.IOException;
import java.net.*;
import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;


import fr.lirmm.fairness.assessment.utils.OntologyRestApi;


public class PortalInstance {

	public static final String SERVER_DEFAULT_PORTAL= "SERVER_DEFAULT_PORTAL";
	private final boolean admin;
	private String name = null;
	private String url = null;
	private String apikey = null; /* will be usedto show public ontologies by default*/
	private String adminApikey = null; /* will be used for cachng all the ontologies (private + public)*/
	private boolean cacheEnabled = false;
	private boolean privateAPI = false; /* to know is an apikey can be shared or not*/

	private List<String> ontoacronyms = new ArrayList<String>();

	public PortalInstance(String name , String url, String apikey , String adminApikey, boolean cacheEnabled, boolean privateAPI , boolean admin) {
		super();
		this.name = name ;
		this.url = url.replaceFirst("/*$", "");
		this.apikey = apikey;
		this.adminApikey = adminApikey;
		this.cacheEnabled = cacheEnabled;
		this.privateAPI = privateAPI;
		this.admin = admin;
	}


	public PortalInstance(String url , String apikey, boolean cacheEnabled ,boolean privateAPI) throws URISyntaxException {
		this(getDomainName(url),url,apikey,apikey, cacheEnabled , privateAPI , false);
	}


	public static PortalInstance getFromConfiguration(Configuration configuration , String portalName,String apikey ,boolean admin) throws IOException {
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
				prop.getProperty("adminApikey"),
				Boolean.parseBoolean(prop.getProperty("cacheEnabled")),
				privateAPI ,admin);
	}

	public static PortalInstance getFromConfiguration(Configuration configuration , String portalName , boolean admin) throws IOException {
		return getFromConfiguration(configuration,portalName,"" , admin);
	}

	
	public List<String> getAllOntologiesAcronyms() throws Exception {
		if(ontoacronyms.isEmpty()){
			// Get the available resources
			OntologyRestApi restApi = new OntologyRestApi();
			String resourcesString = OntologyRestApi.get(this.url + "/ontologies?include_views=true", this.getApikey(), "application/json");
			// Get the ontologies from the link we found
			JsonNode ontologies = restApi.jsonToNode(resourcesString);

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



	public String getApikey(){
		return this.admin ? this.adminApikey : this.apikey;
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
