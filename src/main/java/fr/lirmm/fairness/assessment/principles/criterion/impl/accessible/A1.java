package fr.lirmm.fairness.assessment.principles.criterion.impl.accessible;

import java.io.IOException;
import java.net.*;
import java.util.List;

import fr.lirmm.fairness.assessment.principles.criterion.question.tests.ContentNegotiationTest;
import fr.lirmm.fairness.assessment.principles.criterion.question.tests.MetaDataExistTest;
import fr.lirmm.fairness.assessment.principles.criterion.question.tests.ResolvableURLTest;
import fr.lirmm.fairness.assessment.principles.criterion.question.tests.URLValidTest;
import fr.lirmm.fairness.assessment.utils.QuestionResult;
import fr.lirmm.fairness.assessment.utils.Result;
import fr.lirmm.fairness.assessment.principles.criterion.question.Testable;
import fr.lirmm.fairness.assessment.principles.criterion.question.Tester;
import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
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
                String uri = ontology.getOntologyIRI();
                String id = ontology.getIdentifier();
                int count = 0;
                String uriErrorMessage = "";
                String idErrorMessage = "";

                if (MetaDataExistTest.isValid(uri)) {
                    if (URLValidTest.isValid(uri)) {
                        if (ResolvableURLTest.isValid(uri)) {
                            count ++;
                        } else {
                            uriErrorMessage = "Not resolvable ontology URI";
                        }
                    } else {
                        uriErrorMessage = "Not valid ontology URI";
                    }
                } else {
                    uriErrorMessage = "Ontology URI not present";
                }

                if (MetaDataExistTest.isValid(id)) {
                    if (URLValidTest.isValid(id)) {
                        if (ResolvableURLTest.isValid(id)) {
                            count ++;
                        } else {
                            idErrorMessage = "Not resolvable ontology ID";
                        }
                    } else {
                        idErrorMessage = "Not valid ontology ID";
                    }
                } else {
                    idErrorMessage = "Ontology ID not present";
                }

                if (count == 1.0) {
                    setSuccess(question);
                } else if (count > 0) {
                    QuestionResult result = question.getMaxPoint(count);
                    setScore( result.getScore(), result.getExplanation() + " (" + uriErrorMessage +
                            ((!uriErrorMessage.isEmpty() && !idErrorMessage.isEmpty()) ? " and " : "")
                            + idErrorMessage + ")", question);

                }
            }
        });
        this.addResult(r);

        //Q2 : Does the ontology URI (if metadata is included in the ontology file) or the external metadata URI resolve to the metadata record ?
        //we test on AgroPortal metadata -> max points.
        this.setDefaultSucesses(1 ,"The repository provides an external metadata URI which resolves to the metadata record");



        //Q3: Are ontology and its metadata supporting content negotiation?
        r = Tester.doEvaluation(ontology, this.questions.get(2), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {

                List<String> acceptedFormats = ContentNegotiationTest.acceptedFormats(ontology.getOntologyIRI(), "");
                String ontologyMessage = "";
                String metadataMessage = "";
                int count = 0;

                count += acceptedFormats.size();
                if (acceptedFormats.size() > 0) {
                    ontologyMessage = "Ontology accept the following formats: " + acceptedFormats;
                } else {
                    ontologyMessage = "Ontology is not content-negotiable";
                }

                acceptedFormats = ContentNegotiationTest.acceptedFormats(ontology.getMetaDataURL(), ontology.getPortalInstance().getApikey());

                count += acceptedFormats.size();
                if (acceptedFormats.size() > 0) {
                    metadataMessage = "Ontology metadata accept the following formats: " + acceptedFormats;
                } else {
                    metadataMessage = "Ontology metadata is not content-negotiable";
                }


                if (count == 0) {
                    setFailure(question);
                } else {
                    QuestionResult result = question.getMaxPoint(count);
                    setScore(result.getScore(),  result.getExplanation() + " ("+ontologyMessage +
                            (!ontologyMessage.isEmpty() && !metadataMessage.isEmpty() ? ", " : "")
                            + metadataMessage + ")", question);
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
                if(MetaDataExistTest.isValid(endPoint)){
                    // TODO test if agroportal sparql with the agroportal endpoint
                    // Test url resolvable url
                    setSuccess(question);
                } else {
                    setFailure(question);
                }
            }
        });
        this.addResult(r);
    }
}
