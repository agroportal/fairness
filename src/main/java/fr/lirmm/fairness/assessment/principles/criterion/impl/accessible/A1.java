package fr.lirmm.fairness.assessment.principles.criterion.impl.accessible;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import fr.lirmm.fairness.assessment.principles.criterion.question.tests.MetaDataExistTest;
import fr.lirmm.fairness.assessment.principles.criterion.question.tests.ResolvableURLTest;
import fr.lirmm.fairness.assessment.principles.criterion.question.tests.URLValidTest;
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
                String uri = ontology.getUri();
                String id = ontology.getId();
                double score = 0;
                String uriErrorMessage = "";
                String idErrorMessage = "";

                if (MetaDataExistTest.isValid(uri)) {
                    if (URLValidTest.isValid(uri)) {
                        if (ResolvableURLTest.isValid(uri)) {
                            score += 0.5;
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
                            score += 0.5;
                        } else {
                            idErrorMessage = "Not resolvable ontology ID";
                        }
                    } else {
                        idErrorMessage = "Not valid ontology ID";
                    }
                } else {
                    idErrorMessage = "Ontology ID not present";
                }

                if (score == question.getMaxPoint()) {
                    setSuccess("Resolvable ontology URI and ID", question);
                } else if (score > 0) {
                    setScore(score * question.getMaxPoint(), uriErrorMessage +
                            ((!uriErrorMessage.isEmpty() && !idErrorMessage.isEmpty()) ? " and " : "")
                            + idErrorMessage, question);

                }
            }
        });
        this.addResult(r);

        //Q2 : Does the ontology URI (if metadata is included in the ontology file) or the external metadata URI resolve to the metadata record ?

        this.addResult(1, this.questions.get(1).getMaxPoint(), "The repository provides an external metadata URI which resolves to the metadata record"); //we test on AgroPortal metadata -> max points.

        //Q3: Are ontology and its metadata supporting content negotiation?
        r = Tester.doEvaluation(ontology, this.questions.get(2), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                String[] formats = {"application/json", "application/xml", "text/html", "text/plain"};
                List<String> acceptedFormats = contentNegotiationTest(ontology.getUri(), "", formats);
                String ontologyMessage = "";
                String metadataMessage = "";
                double score = 0.0;

                System.out.println("Accepted formats for the ontology are : " + acceptedFormats);
                score += acceptedFormats.size() * (question.getMaxPoint() / (formats.length * 2));
                if (acceptedFormats.size() > 0) {
                    ontologyMessage = "Ontology accept the following formats: " + acceptedFormats;
                } else {
                    ontologyMessage = "Ontology is not content-negotiable";
                }

                acceptedFormats = contentNegotiationTest(ontology.getMetaDataURL(), ontology.getPortalInstance().getApikey(), formats);
                System.out.println("Accepted formats for the meta are : " + acceptedFormats);
                score += acceptedFormats.size() * (question.getMaxPoint() / (formats.length * 2));
                if (acceptedFormats.size() > 0) {
                    metadataMessage = "Ontology metadata accept the following formats: " + acceptedFormats;
                } else {
                    metadataMessage = "Ontology metadata is not content-negotiable";
                }


                if (score == 0) {
                    setFailure("Ontology and ontology metadata are not content-negotiable", question);
                } else {
                    setScore(score, ontologyMessage +
                            (!ontologyMessage.isEmpty() && !metadataMessage.isEmpty() ? ", " : "")
                            + metadataMessage, question);
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
                if (endPoint != null && !endPoint.isEmpty()) {
                    // TODO test if agroportal sparql with the agroportal endpoint
                    setSuccess("Ontology is accessible through a SPARQL endpoint", question);
                } else {
                    setFailure("Ontology is not accessible through another standard protocol (such as SPARQL endpoint)", question);
                }
            }
        });
        this.addResult(r);
    }


    private List<String> contentNegotiationTest(String url, String apikey, String[] formats) {

        List<String> foundFormats = new ArrayList<String>();
        List<String> args = new ArrayList<>();
        for (String format : formats) {
            args.clear();
            args.add(url);
            args.add(format);
            if (apikey != null) {
                args.add(apikey);
            }

            if (ResolvableURLTest.isValid(args.toArray(String[]::new))) {
                foundFormats.add(format);
            }
        }
        return foundFormats;
    }
}
