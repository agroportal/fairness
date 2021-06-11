package fr.lirmm.fairness.assessment.principles.criterion.impl.accessible;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;

import org.apache.commons.validator.routines.UrlValidator;
import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class A1 extends AbstractPrincipleCriterion {

	private static final long serialVersionUID = -9168674598661890605L;

	@Override
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException {
		
		try {
			String uri = ontology.getUri();
			String id = ontology.getId();
			String endPoint = ontology.getEndPoint();
			String xmlresource = ontology.getXmlMetadata();
			String jsonresource = ontology.getJsonMetadata();
			String htmlresource = ontology.getHtmlMetadata();
			String textresource = ontology.getTextMetadata();
			int nbFormats=0; 
			int points=0, score=0;
            
			//Q1: Does the ontology URI and other identifiers, if exist, resolve to the ontology?
			try {
				String[] CustomURISchemes = { "http", "https" };
				UrlValidator customURIValidator = new UrlValidator(CustomURISchemes);
				points= this.questionsPoints.get(0)/2; 
				if ((customURIValidator.isValid(uri)) && (customURIValidator.isValid(id))) {
					URL urluri = new URL(uri);
					URL urlid = new URL(id);
					HttpURLConnection urluriConnection = (HttpURLConnection) urluri.openConnection();
					HttpURLConnection urlidConnection = (HttpURLConnection) urlid.openConnection();
					HttpURLConnection.setFollowRedirects(false);
					urluriConnection.setRequestMethod("HEAD");
					urluriConnection.setConnectTimeout(1000); // 1 second
					urlidConnection.setRequestMethod("HEAD");
					urlidConnection.setConnectTimeout(1000); // 1 second
					int urihttpstatusCode = urluriConnection.getResponseCode();
					int idhttpstatusCode = urlidConnection.getResponseCode();
					if (((urihttpstatusCode == 200) || (urihttpstatusCode == 302)))
					{   score+=points;				
					   if (((idhttpstatusCode == 200) || (idhttpstatusCode == 302)))
					   {
						   score+=points; 
						   this.addResult(0, score, "Resolvable ontology URI and identifier");
					   }
					   if (score<this.questionsPoints.get(0))
						{
						   this.addResult(0, score, "Resolvable ontology URI or identifier but not both");
						}
					} else {
						this.addResult(0, 0, "HTTP error=" + urluriConnection.getResponseMessage());
					}
					urluriConnection.disconnect();
				}

				else {
					this.addResult(0, 0, "Invalid ontology URI/GUID");
				}
				
			     } catch (UnknownHostException | SocketTimeoutException e) {
				   this.addResult(0, 0, "UnknownHostException HTTP error");
			     }

			//Q2: Does a metadata URI/GUID (if metadata described externally) resolve to
			// the metadata record?
			this.addResult(1, this.questionsPoints.get(1), "URI/GUID metadata resolve to the metadata record"); //we test on AgroPortal metadata -> max points.

			//Q3: Are ontology and its metadata supporting content negociation?
			
			if ((xmlresource.isEmpty()) && (jsonresource.isEmpty()) && (htmlresource.isEmpty())
					&& (textresource.isEmpty())) {
				this.addResult(2, 0, "Ontology and ontology metadata are not accessible with content-negociation");
			} 
			else {
				nbFormats= 4;
				points = (this.questionsPoints.get(2))/nbFormats;
                score=0;
				if (!xmlresource.isEmpty()){
					score += points;
				}
				if (!jsonresource.isEmpty()) {
					score += points;
				}
				if (!htmlresource.isEmpty()) {
					score += points;
				}
				if (!textresource.isEmpty()) {
					score += points;	
				} 		
				this.addResult(2, score, "Ontology and its metadata are accessible in different formats");
			}

			// Q4: Is an ontology accessible through another standard protocol such as
			// SPARQL?
			if (endPoint!= null && !endPoint.isEmpty()) {
				this.addResult(3, this.questionsPoints.get(3),"Ontology is accessible through a SPARQL endpoint");
			} else {
				
				this.addResult(3, 0, "Ontology is not accessible through another standard protocol (SPARQL endpoint)");
			}

		} catch (java.net.SocketTimeoutException e) {
			e.printStackTrace();
		}
	}
	
}
