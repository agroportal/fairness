package fr.lirmm.fairness.assessment.principles.criterion.impl.reusable;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;
import fr.lirmm.fairness.assessment.utils.OntologyRestApi;

public class R11 extends AbstractPrincipleCriterion {

	private static final long serialVersionUID = 1326075077978659656L;

	@Override
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException {

		try {
			String license = ontology.getLicense();
			String accessRights = ontology.getAccessRights();
			String morePermissions = ontology.getMorePermissions();
			String useGuidelines = ontology.getUseGuidelines();
			String rightsHolder = ontology.getConatct();
			final boolean licenseIsSpecified = OntologyRestApi.isValidMetadatumValue(license);
			final boolean accessRightsSpecified = OntologyRestApi.isValidMetadatumValue(accessRights);
			
			// Q1: Is the ontology license clearly specified (i.e., with a persistent, unique identifier)? 
		
			if (licenseIsSpecified) {
			      this.addResult(0, this.questionsPoints.get(0), "Ontology license is clearly specified");
			}
			else 
			{
				this.addResult(0, 0.0, "Ontology license is not clearly specified");
			}

			// Q2: If yes, is the license description accessible and resolvable by a
			// machine?
			if (licenseIsSpecified && ((license.contains("http")) || (license.contains("https")))) {
				URL urluri = new URL(license);
				HttpURLConnection urluriConnection = (HttpURLConnection) urluri.openConnection();
				HttpURLConnection.setFollowRedirects(false);
				urluriConnection.setRequestMethod("HEAD");
				int httpstatusCode = 0;
				httpstatusCode = urluriConnection.getResponseCode();
				if ((httpstatusCode == 200) || (httpstatusCode == 302)) {
					this.addResult(1, this.questionsPoints.get(1), "License is accessible and resolvable");
				} else {
					this.addResult(1, 0.0, String.format("License is not resolvable HTTP error=%s",
							urluriConnection.getResponseMessage()));
				}
			} else {
				this.addResult(1, 0.0, "License is not resolvable");
			}
             
			//Q3: Are the ontology access rights clearly specified/declared?
			
			if (accessRightsSpecified) {
				this.addResult(2, this.questionsPoints.get(2), "Access rights are clearly defined");
			} else {
				this.addResult(2, 0.0, "Access rights are not clearly defined");
			}
			
			// Q4: Are the permissions, usage guidelines and copyright holder clearly documented?
			int infoCount = 0;
			for (String info : new String[] { morePermissions, useGuidelines, rightsHolder }) {
				if (OntologyRestApi.isValidMetadatumValue(info)) {
					infoCount++;
				}
			}
			this.addResult(3, ((this.questionsPoints.get(3)/3) * infoCount),
					infoCount == 0 ? "Neither ontology license nor access rights are accessible by machine"
							: "Ontology license and/or access rights are accessible by machine");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
