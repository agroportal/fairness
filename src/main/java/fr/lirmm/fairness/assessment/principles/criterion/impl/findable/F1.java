package fr.lirmm.fairness.assessment.principles.criterion.impl.findable;

import java.io.IOException;
import java.net.*;

import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import fr.lirmm.fairness.assessment.principles.criterion.question.Testable;
import fr.lirmm.fairness.assessment.principles.criterion.question.Tester;
import fr.lirmm.fairness.assessment.utils.Result;
import org.apache.commons.validator.routines.UrlValidator;
import org.json.JSONException;
import org.json.JSONObject;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;
import fr.lirmm.fairness.assessment.utils.OntologyRestApi;

public class F1 extends AbstractPrincipleCriterion {

	private static final long serialVersionUID = -7465284349442369392L;

	@Override
	protected void doEvaluation(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException {

		String apiKey = ontology.getPortalInstance().getApikey();
		String ontologyId = ontology.getId();
		String uri = ontology.getUri();
		String ontologyVersionIri = ontology.getVersionIri();
		String[] CustomURISchemes = { "http", "https" };
		UrlValidator customURIValidator = new UrlValidator(CustomURISchemes);

		// Q1- Does an ontology have a local identifier i.e., a globally unique and potentially persistent identifier assigned by the developer (or developing organization)?
		Result r = Tester.doEvaluation(ontology, this.questions.get(0), new Testable() {
			@Override
			public void doTest(Ontology ontology, AbstractCriterionQuestion question) {

				if (customURIValidator.isValid(uri)) {
					setSuccess("Valid ontology URI", question);
				}else {
					setFailure( "Invalid ontology URI"  , question);
				}


			}
		});
		this.addResult(r);

		/*
		// OLD-Q2: If yes, is this identifier a resolvable/dereferenceable URI?
		if(r.isSuccess()){
			r = Tester.doEvaluation(ontology, this.questions.get(1), new Testable() {
			@Override
			public Result doTest(Ontology ontology, AbstractCriterionQuestion question) {
				Result r = new Result();
				HttpURLConnection urluriConnection = null;
				try {
					URL urluri = new URL(uri);
					HttpURLConnection.setFollowRedirects(false);
					urluriConnection = (HttpURLConnection) urluri.openConnection();
					urluriConnection.setRequestMethod("HEAD");
					urluriConnection.setConnectTimeout(1000); // 1 second

					int httpstatusCode = 0;
						httpstatusCode = urluriConnection.getResponseCode();
						if ((httpstatusCode == 200) || (httpstatusCode == 302)) // Ok or Found (for redirected adresses
						// "purl")
						{
							r.setResult(question.getPoints(), "Resolvable ontology URI" ,question);
						} else {
							r.setResult(0, "HTTP error=" + urluriConnection.getResponseMessage() ,question);
						}
					}
				catch (UnknownHostException e) {
					r.setResult(0, "UnknownHostException HTTP error" , question);
				} catch (Exception e) {
					r.setResult( 0, "" , question);
				}finally {
					if(urluriConnection != null)
						urluriConnection.disconnect();
				}
				return r;
			}
		});
		this.addResult(1, r.getScore(), r.getExplication());
		*/


		// Q2  : Does an ontology provide an additional external identifier i.e., a guarantee globally unique and persistent identifier assigned by an accredited body?
		r = Tester.doEvaluation(ontology, questions.get(1), new Testable() {
			@Override
			public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
				Result r = new Result();

				String[] customIDSchemes = { "http", "https" };
				UrlValidator customIDValidator = new UrlValidator(customIDSchemes);
				if (customIDValidator.isValid(ontologyId)) {
					setSuccess("Valid GUID" , question);
				} else {
					setFailure(  "Invalid GUID" , question);
				}

			}
		});
		this.addResult(r);


		// Q3: If yes, is this external identifier a DOI?
		if(r.isSuccess()){
			r = Tester.doEvaluation(ontology, questions.get(2), new Testable() {
				@Override
				public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
					if ((ontologyId.contains("http") && ontologyId.contains("dx.doi")) || (ontologyId.contains("https") && ontologyId.contains("dx.doi"))) {
						String[] handle;

						if (ontologyId.contains("https")) {
							handle = ontologyId.split("https://dx.doi.org/");
						} else {
							handle = ontologyId.split("http://dx.doi.org/");
						}
						String doiApiUrl = null;
						try {
							doiApiUrl = OntologyRestApi.get("https://dx.doi.org/api/handles/" + handle[1], apiKey, "application/json");
							JSONObject obj = new JSONObject(doiApiUrl);int responseCode = Integer.valueOf(obj.getString("responseCode"));
							if (responseCode == 1) {
								this.setSuccess("Valid DOI for the GUID" , question);
							} else {
								this.setFailure( "Invalid DOI for the GUID" , question);
							}
						} catch (IOException | JSONException e) { ;
							this.setFailure("Invalid DOI for the GUID" , question);
						}
					} else if ((ontologyId.contains("http") && ontologyId.contains("doi")) || (ontologyId.contains("https") && ontologyId.contains("doi"))) {
						String[] handle;

						if (ontologyId.contains("https")) {
							handle = ontologyId.split("https://doi.org/");
						} else {
							handle = ontologyId.split("http://doi.org/");
						}
						String doiApiUrl = null;
						try {
							doiApiUrl = OntologyRestApi.get("https://doi.org/api/handles/" + handle[1], apiKey, "application/json");
							JSONObject obj = new JSONObject(doiApiUrl);

							int responseCode = Integer.valueOf(obj.getString("responseCode"));
							if (responseCode == 1) {
								this.setSuccess( "Valid DOI for the GUID" , question);
							} else {
								this.setFailure( "Invalid DOI for the GUID" + responseCode ,question);
							}
						} catch (IOException | JSONException e) {
							this.setFailure( "Invalid DOI for the GUID"  ,question);
						}
					} else {

						this.setFailure("GUID is not a DOI" , question);
					}
				}
			});
			this.addResult(r);
		}else {
			this.addResult(2 , 0 , r.getExplication());
		}


		// Q4: Are the ontology metadata included in the ontology file and consequently share the same identifiers?
		this.addResult(3, 0, "Metadata are not included in the ontology file");

		// Q5: If not, is the metadata record clearly identified by a resolvable URI?
		this.addResult(4, this.questionsPoints.get(4), "Metadata are identified by a resolvable URI");

		// Q6: Does an ontology provide a version specific URI?
		if (ontologyVersionIri != null) {
			r = Tester.doEvaluation(ontology, questions.get(5), new Testable() {
				@Override
				public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
					String[] CustomVersionIriSchemes = {"http", "https"};
					UrlValidator customVersionIriValidator = new UrlValidator(CustomVersionIriSchemes);
					if (customVersionIriValidator.isValid(ontologyVersionIri)) {
						setSuccess("Valid URI version", question);
					} else {
						setFailure("Invalid ontology URI version", question);
					}
				}
			});
			this.addResult(r);

			// Q7: If yes, is this version URI resolvable/dereferenceable?
			if(r.isSuccess()) {
				r = Tester.doEvaluation(ontology, questions.get(6), new Testable() {
					@Override
					public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
						HttpURLConnection urlVersionIriConnection = null;
						try {
							URL urlVersionIri = new URL(ontologyVersionIri);
							urlVersionIriConnection = (HttpURLConnection) urlVersionIri.openConnection();
							HttpURLConnection.setFollowRedirects(false);
							urlVersionIriConnection.setRequestMethod("HEAD");
							urlVersionIriConnection.setConnectTimeout(1000); // 1 second

							if (customURIValidator.isValid(ontologyVersionIri)) {
								int httpstatusCode = 0;
								httpstatusCode = urlVersionIriConnection.getResponseCode();

								if ((httpstatusCode == 200) || (httpstatusCode == 302)) {
									setSuccess( "Resolvable ontology URI version" ,question);
								} else {
									setFailure( "HTTP error=" + urlVersionIriConnection.getResponseMessage() , question);
								}
							}
						} catch (Exception e) {
							setFailure( "UnknownHostException HTTP error", question);
						} finally {
							if(urlVersionIriConnection != null)
								urlVersionIriConnection.disconnect();
						}
					}
				});
				this.addResult(r);
			}else {
				this.addResult(6, 0 , r.getExplication());
			}
		}else {
			this.addResult(5 ,  0 , "Inexistant ontology URI version");
			this.addResult(6 ,  0 , "Inexistant ontology URI version");
		}
	}
	
}
