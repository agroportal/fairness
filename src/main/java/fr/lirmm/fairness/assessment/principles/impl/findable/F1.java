package fr.lirmm.fairness.assessment.principles.impl.findable;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;

import org.apache.commons.validator.routines.UrlValidator;
import org.json.JSONException;
import org.json.JSONObject;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.impl.AbstractPrincipleCriterion;
import fr.lirmm.fairness.assessment.utils.OntologyRestApi;

public class F1 extends AbstractPrincipleCriterion {

	private static final long serialVersionUID = -7465284349442369392L;

	@Override
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException {

		String apiKey = ontology.getPortalInstance().getApikey();
		String ontologyId = ontology.getId();
		String uri = ontology.getUri();
		String ontologyVersionIri = ontology.getVersionIri();

		// Q1- Does an ontology have a local identifier i.e., a globally unique and potentially persistent identifier assigned by the developer (or developing organization)?
		String[] CustomURISchemes = { "http", "https" };
		UrlValidator customURIValidator = new UrlValidator(CustomURISchemes);
		if (customURIValidator.isValid(uri)) {
			this.addResult(0, this.questionsPoints.get(0), "Valid ontology URI");
			// Q2: If yes, is this identifier a resolvable/dereferenceable URI?
			URL urluri = new URL(uri);
			HttpURLConnection urluriConnection = (HttpURLConnection) urluri.openConnection();
			HttpURLConnection.setFollowRedirects(false);
			urluriConnection.setRequestMethod("HEAD");
			int httpstatusCode = 0;
			try {
				httpstatusCode = urluriConnection.getResponseCode();
				if ((httpstatusCode == 200) || (httpstatusCode == 302)) // Ok or Found (for redirected adresses
																		// "purl")
				{
					this.addResult(1, this.questionsPoints.get(1), "Resolvable ontology URI");
				} else {
					this.addResult(1, 0, "HTTP error=" + urluriConnection.getResponseMessage());
				}
			} catch (UnknownHostException e) {
				urluriConnection.disconnect();
				this.addResult(1, 0, "UnknownHostException HTTP error");
				
			} catch (Exception e) {
				urluriConnection.disconnect();
				this.addResult(1, 0, "");
			}
		} else {
			this.addResult(0, 0, "Invalid ontology URI");
			this.addResult(1, 0, "No ontology URI");
		}

		// Q3 : Does an ontology provide an additional external identifier i.e., a guarantee globally unique and persistent identifier assigned by an accredited body?
		String[] customIDSchemes = { "http", "https" };
		UrlValidator customIDValidator = new UrlValidator(customIDSchemes);
		if (customIDValidator.isValid(ontologyId)) {
			this.addResult(2, this.questionsPoints.get(2), "Valid GUID");
		} else {
			this.addResult(2, 0, "Invalid GUID");
		}

		// Q4: If yes, is this external identifier a DOI?
		if ((ontologyId.contains("http") && ontologyId.contains("dx.doi")) || (ontologyId.contains("https") && ontologyId.contains("dx.doi"))) {
			String[] handle;

			if (ontologyId.contains("https")) {
				handle = ontologyId.split("https://dx.doi.org/");
			} else {
				handle = ontologyId.split("http://dx.doi.org/");
			}
			String doiApiUrl = OntologyRestApi.get("https://dx.doi.org/api/handles/" + handle[1], apiKey, "application/json");

			JSONObject obj = new JSONObject(doiApiUrl);
			int responseCode = Integer.valueOf(obj.getString("responseCode"));
			if (responseCode == 1) {
				this.addResult(3, this.questionsPoints.get(3), "Valid DOI for the GUID");
			} else {
				this.addResult(3, 0, "Invalid DOI for the GUID" + responseCode);
			}
		} else if ((ontologyId.contains("http") && ontologyId.contains("doi")) || (ontologyId.contains("https") && ontologyId.contains("doi"))) {
			String[] handle;

			if (ontologyId.contains("https")) {
				handle = ontologyId.split("https://doi.org/");
			} else {
				handle = ontologyId.split("http://doi.org/");
			}
			String doiApiUrl = OntologyRestApi.get("https://doi.org/api/handles/" + handle[1], apiKey, "application/json");

			JSONObject obj = new JSONObject(doiApiUrl);
			int responseCode = Integer.valueOf(obj.getString("responseCode"));
			if (responseCode == 1) {
				this.addResult(3, this.questionsPoints.get(3), "Valid DOI for the GUID");
			} else {
				this.addResult(3, 0, "Invalid DOI for the GUID" + responseCode);
			}

		} else {
			this.addResult(3, 0, "GUID is not a DOI");
		}

		// Q5: Are the ontology metadata included in the ontology file and consequently share the same identifiers?
		this.addResult(4, 0, "Metadata are not included in the ontology file");

		// Q6: If not, is the metadata record clearly identified by a resolvable URI?
		this.addResult(5, this.questionsPoints.get(5), "Metadata are identified by a resolvable URI");

		// Q7: Does an ontology provide a version specific URI?
		if (ontologyVersionIri != null) {
			String[] CustomVersionIriSchemes = { "http", "https" };
			UrlValidator customVersionIriValidator = new UrlValidator(CustomVersionIriSchemes);
			if (customVersionIriValidator.isValid(ontologyVersionIri)) {
				this.addResult(6, this.questionsPoints.get(6), "Valid URI version");

				// Q8: If yes, is this version URI resolvable/dereferenceable?
				URL urlVersionIri = new URL(ontologyVersionIri);
				HttpURLConnection urlVersionIriConnection = (HttpURLConnection) urlVersionIri.openConnection();
				HttpURLConnection.setFollowRedirects(false);
				urlVersionIriConnection.setRequestMethod("HEAD");
				urlVersionIriConnection.setConnectTimeout(1000); // 1 second

				try {
					if (customURIValidator.isValid(ontologyVersionIri)) {

						int httpstatusCode = 0;
						httpstatusCode = urlVersionIriConnection.getResponseCode();

						if ((httpstatusCode == 200) || (httpstatusCode == 302)) {
							this.addResult(7, this.questionsPoints.get(7), "Resolvable ontology URI version");
						} else {
							this.addResult(7, 0, "HTTP error=" + urlVersionIriConnection.getResponseMessage());
						}
					}
				} catch (java.io.InterruptedIOException e) {
					urlVersionIriConnection.disconnect();
					this.addResult(7, 0, "UnknownHostException HTTP error");
				}
			} else {
				this.addResult(6, 0, "Invalid ontology URI version");
				this.addResult(7, 0, "Invalid ontology URI version");
			}
		} else {
			this.addResult(7, 0, "Inexistant ontology URI version");
		}
	}
	
}
