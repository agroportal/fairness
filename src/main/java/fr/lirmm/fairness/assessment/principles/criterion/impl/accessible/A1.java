package fr.lirmm.fairness.assessment.principles.criterion.impl.accessible;

import java.io.IOException;
import java.net.*;

import fr.lirmm.fairness.assessment.utils.Result;
import fr.lirmm.fairness.assessment.principles.criterion.question.Testable;
import fr.lirmm.fairness.assessment.principles.criterion.question.Tester;
import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import org.apache.commons.validator.routines.UrlValidator;
import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class A1 extends AbstractPrincipleCriterion {

	private static final long serialVersionUID = -9168674598661890605L;

	@Override
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException {


			//Q1: Does the ontology URI and other identifiers, if exist, resolve to the ontology?
			Result r = Tester.doEvaluation(ontology, this.questions.get(0), new Testable() {
				@Override
				public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
					String uri = ontology.getUri();
					String id = ontology.getId();
					double score;

					try {
						String[] CustomURISchemes = { "http", "https" };
						UrlValidator customURIValidator = new UrlValidator(CustomURISchemes);

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
							{
								score = 0.5;
								if (((idhttpstatusCode == 200) || (idhttpstatusCode == 302)))
								{
									score = 1;
									setSuccess("Resolvable ontology URI and identifier" , question);
								}

								if (score < 1)
								{
									setScore(score * question.getPoints() ,"Resolvable ontology URI or identifier but not both" , question);
								}

							} else {
								setFailure( "HTTP error=" + urluriConnection.getResponseMessage() , question);
							}
							urluriConnection.disconnect();
						}
						else {
							setFailure("Invalid ontology URI/GUID" , question);
						}

					} catch (Exception e) {
						setFailure("UnknownHostException HTTP error" , question);
					}
				}
			});
			this.addResult(r);

			//Q2: Does a metadata URI/GUID (if metadata described externally) resolve to
			// the metadata record?

			this.addResult(1, this.questions.get(1).getPoints(), "URI/GUID metadata resolve to the metadata record"); //we test on AgroPortal metadata -> max points.

			//Q3: Are ontology and its metadata supporting content negociation?
			r = Tester.doEvaluation(ontology, this.questions.get(2), new Testable() {
				@Override
				public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
					try {
						String xmlresource = ontology.getXmlMetadata();
						String jsonresource = ontology.getJsonMetadata();
						String htmlresource = ontology.getHtmlMetadata();
						String textresource = ontology.getTextMetadata();

						if ((xmlresource.isEmpty()) && (jsonresource.isEmpty()) && (htmlresource.isEmpty())
								&& (textresource.isEmpty())) {
							setFailure("Ontology and ontology metadata are not accessible with content-negociation", question);
						}else {
							int nbFormats= 4;
							double points = (question.getPoints())/nbFormats;
							double score=0;
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
							setScore( score, "Ontology and its metadata are accessible in different formats" , question);
						}
					} catch (IOException e) {
						setFailure("Ontology and ontology metadata are not accessible with content-negociation", question);
					}
				}
			});
			this.addResult(r);

			// Q4: Is an ontology accessible through another standard protocol such as
			// SPARQL?
			r = Tester.doEvaluation(ontology, this.questions.get(3), new Testable() {
				@Override
				public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
					String endPoint = ontology.getEndPoint();
					if (endPoint!= null && !endPoint.isEmpty()) {
						setSuccess("Ontology is accessible through a SPARQL endpoint" ,question);
					} else {
						setFailure( "Ontology is not accessible through another standard protocol (SPARQL endpoint)" , question);
					}
				}
			});
			this.addResult(r);
	}
	
}
