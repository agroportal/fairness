package fr.lirmm.fairness.assessment.model;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import fr.lirmm.fairness.assessment.utils.requestparams.params.PortalParam;
import org.json.JSONException;



import fr.lirmm.fairness.assessment.Configuration;
import fr.lirmm.fairness.assessment.utils.OntologyRestApi;

import javax.servlet.http.HttpServletRequest;

public class PortalInstance {

	public static final String SERVER_DEFAULT_PORTAL= "SERVER_DEFAULT_PORTAL";
	private String name = null;
	private String url = null;
	private String apikey = null;
	private boolean cacheEnabled = false;

	private  static  PortalInstance  portalInstance = null;
	
	public PortalInstance() {
		super();
	}
	
	public PortalInstance(Configuration configuration , String portalName) throws IOException {
		super();
		Properties prop = configuration.getProperties(portalName);
		if (prop == null)
			throw new IOException();

		this.name = prop.getProperty("name");
		this.url = prop.getProperty("url");
		this.apikey = prop.getProperty("apikey");
		this.cacheEnabled = Boolean.parseBoolean(prop.getProperty("cacheEnabled"));
	}

	public PortalInstance(String url , String apikey , boolean cacheEnabled ) throws URISyntaxException {
		super();
		this.name = getDomainName(url) ;
		this.url = url;
		this.apikey = apikey;
		this.cacheEnabled = cacheEnabled;
	}
	
	/*
	public static PortalInstance getInstance(HttpServletRequest request) throws IOException {
		String portalName = null;
		try {
			portalName = PortalParam.getInstance().get(request);
		} catch (Exception e) {
			portalName = request.getServerName().toLowerCase(Locale.ROOT);
			Logger.getAnonymousLogger().info("Portal name not set in the URL");
		}

		if(portalName != null && portalInstance==null) {
			portalInstance = new PortalInstance(Configuration.getInstance() , portalName);
		}else if (portalInstance==null){
			portalInstance = new PortalInstance();
		}
		return portalInstance;
	}
	*/




	
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


	private static String getDomainName(String url) throws URISyntaxException {
		URI uri = new URI(url);
		String domain = uri.getHost();
		if(domain.startsWith("www."))
			domain = domain.substring(4);

		return  domain.substring(0,domain.indexOf('.'));
	}

}
