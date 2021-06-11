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
		
		try {

			String endPoint = ontology.getEndPoint();
			String xmlresource = ontology.getXmlMetadata();
			String jsonresource = ontology.getJsonMetadata();
			String htmlresource = ontology.getHtmlMetadata();
			String textresource = ontology.getTextMetadata();
			int nbFormats=0; 
			double points=0, score=0;



			//Q1: Does the ontology URI and other identifiers, if exist, resolve to the ontology?
			Result r = Tester.doEvaluation(ontology, this.questions.get(0), new Testable() {
				@Override
				public Result doTest(Ontology ontology, AbstractCriterionQuestion question) {
					String uri = ontology.getUri();
					String id = ontology.getId();
					Result result = new Result();
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
									result.setResult(score * question.getPoints(), "Resolvable ontology URI and identifier" , question);
								}

								if (score < 1)
								{
									result.setResult(score * question.getPoints() ,"Resolvable ontology URI or identifier but not both" , question);
								}

							} else {
								result.setResult( 0, "HTTP error=" + urluriConnection.getResponseMessage() , question);
							}
							urluriConnection.disconnect();
						}
						else {
							result.setResult(0, "Invalid ontology URI/GUID" , question);
						}

					} catch (UnknownHostException | SocketTimeoutException | MalformedURLException e) {
						result.setResult(0, "UnknownHostException HTTP error" , question);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return result;
				}
			});
			this.addResult(0 ,  r.getScore() , r.getExplication());

			//Q2: Does a metadata URI/GUID (if metadata described externally) resolve to
			// the metadata record?
			this.addResult(1, this.questionsPoints.get(1), "URI/GUID metadata resolve to the metadata record"); //we test on AgroPortal metadata -> max points.

			//Q3: Are ontology and its metadata supporting content negociation?
			
			if ((xmlresource.isEmpty()) && (jsonresource.isEmpty()) && (htmlresource.isEmpty())
					&& (textresource.isEmpty())) {
				this.addResult(2, 0.0, "Ontology and ontology metadata are not accessible with content-negociation");
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
				
				this.addResult(3, 0.0, "Ontology is not accessible through another standard protocol (SPARQL endpoint)");
			}

		} catch (java.net.SocketTimeoutException e) {
			e.printStackTrace();
		}
	}
	
}
