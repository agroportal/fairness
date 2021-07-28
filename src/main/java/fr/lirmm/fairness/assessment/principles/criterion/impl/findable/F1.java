package fr.lirmm.fairness.assessment.principles.criterion.impl.findable;

import java.io.IOException;
import java.net.*;

import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import fr.lirmm.fairness.assessment.principles.criterion.question.Testable;
import fr.lirmm.fairness.assessment.principles.criterion.question.Tester;
import fr.lirmm.fairness.assessment.principles.criterion.question.tests.MetaDataExistTest;
import fr.lirmm.fairness.assessment.principles.criterion.question.tests.ResolvableURLTest;
import fr.lirmm.fairness.assessment.principles.criterion.question.tests.URLValidTest;
import fr.lirmm.fairness.assessment.principles.criterion.question.tests.ValidDOITest;
import fr.lirmm.fairness.assessment.utils.QuestionResult;
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


        // Q1- Does an ontology have a local identifier i.e., a globally unique and potentially persistent identifier assigned by the developer (or developing organization)?
        QuestionResult r = Tester.doEvaluation(ontology, this.questions.get(0), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                if (MetaDataExistTest.isValid(ontology.getUri())) {
                    if (URLValidTest.isValid(ontology.getUri())) {
                        setSuccess("Present and valid ontology URI", question);
                    } else {
                        setScore(question.getMaxPoint(1) , "Present but invalid ontology URI", question);
                    }
                } else {
                    setFailure("ontology URI " + MetaDataExistTest.NOT_EXIST_EXPLANATION, question);
                }
            }
        });
        this.addResult(r);


        // Q2  : Does an ontology provide an additional external identifier i.e., a guarantee globally unique and persistent identifier assigned by an accredited body,
        // If yes, is this external identifier a valid DOI? ?
        r = Tester.doEvaluation(ontology, questions.get(1), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                if (MetaDataExistTest.isValid(ontology.getId())) {
                    if (URLValidTest.isValid(ontology.getId())) {
                        String handle = isDOILink(ontology.getId());
                        if(!handle.isEmpty()){
                            if(ValidDOITest.isValid(handle, apiKey)){
                                setSuccess("Present and valid external identifier", question);
                            }else {
                                setScore(question.getMaxPoint(2), "The external DOI identifier is invalid", question);
                            }
                        }else {
                            setScore(question.getMaxPoint(2), "Valid ontology ID but it's not a DOI", question);
                        }
                    } else {
                        setScore(question.getMaxPoint(1), "Present but invalid ontology ID", question);
                    }
                } else {
                    setFailure("ID  " + MetaDataExistTest.NOT_EXIST_EXPLANATION, question);
                }

            }
        });
        this.addResult(r);

        //Q3 Are the ontology metadata included in the ontology file- and consequently share the same identifiers or is the metadata record clearly identified by its own URI.
        this.addResult(2, this.questions.get(2).getMaxPoint(), "The repository makes explicit relation between metadata and ontology.");


        // Q4: Does an ontology provide a version specific URI and is this URI resolvable/ dereferenceable?
        r = Tester.doEvaluation(ontology, questions.get(5), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                String ontologyVersionIri = ontology.getVersionIri();
                if (MetaDataExistTest.isValid(ontologyVersionIri)) {
                    if (URLValidTest.isValid(ontologyVersionIri)) {
                        if (ResolvableURLTest.isValid(ontologyVersionIri)) {
                            setSuccess("Present, valid and resolvable version URI", question);
                        } else {
                            setScore(question.getMaxPoint(2), "Valid but not resolvable version URI", question);
                        }
                    } else {
                        setScore(question.getMaxPoint(1),"Present but invalid ontology version URI ", question);
                    }
                } else {
                    setFailure("Ontology version uri " + MetaDataExistTest.NOT_EXIST_EXPLANATION, question);
                }


            }
        });
        this.addResult(r);
    }

    private String isDOILink(String link){
        String doi = "";
        if((link.contains("http") && link.contains("dx.doi"))){
            doi = "dx.doi";
        }else if( (link.contains("https") && link.contains("doi"))){
            doi = "doi";
        }
        if(!doi.isEmpty()){
            if (link.contains("https")) {
                return  "https://dx.doi.org/api/handles/"+link.split("https://"+doi+".org/")[1];
            } else {
                return "https://doi.org/api/handles/"+link.split("http://"+doi+".org/")[1];
            }
        }else
            return "";


    }

}
