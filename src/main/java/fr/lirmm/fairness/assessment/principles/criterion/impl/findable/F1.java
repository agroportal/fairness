package fr.lirmm.fairness.assessment.principles.criterion.impl.findable;

import java.io.IOException;

import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import fr.lirmm.fairness.assessment.principles.criterion.question.Testable;
import fr.lirmm.fairness.assessment.principles.criterion.question.Tester;
import fr.lirmm.fairness.assessment.principles.criterion.question.tests.MetaDataExistTest;
import fr.lirmm.fairness.assessment.principles.criterion.question.tests.ResolvableURLTest;
import fr.lirmm.fairness.assessment.principles.criterion.question.tests.URLValidTest;
import fr.lirmm.fairness.assessment.principles.criterion.question.tests.ValidDOITest;
import org.json.JSONException;

import fr.lirmm.fairness.assessment.models.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class F1 extends AbstractPrincipleCriterion {

    private static final long serialVersionUID = -7465284349442369392L;

    @Override
    protected void doEvaluation(Ontology ontology) throws JSONException, IOException {

        String apiKey = ontology.getPortalInstance().getApikey();


        // Q1- Does an ontology have a local identifier i.e., a globally unique and potentially persistent identifier assigned by the developer (or developing organization)?
        this.addResult(Tester.doEvaluation(ontology, this.questions.get(0), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                if (MetaDataExistTest.isValid(ontology.getOntologyIRI())) {
                    if (URLValidTest.isValid(ontology.getOntologyIRI())) {
                        setSuccess(question);
                    } else {
                        setScoreLevel(1 , question);
                    }
                } else {
                    setFailure(question);
                }
            }
        }));


        // Q2  : Does an ontology provide an additional external identifier i.e., a guarantee globally unique and persistent identifier assigned by an accredited body,
        // If yes, is this external identifier a valid DOI? ?
        this.addResult(Tester.doEvaluation(ontology, questions.get(1), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                if (MetaDataExistTest.isValid(ontology.getIdentifier())) {
                    if (URLValidTest.isValid(ontology.getIdentifier())) {
                        String handle = isDOILink(ontology.getIdentifier());
                        if(!handle.isEmpty()){
                            if(ValidDOITest.isValid(handle, apiKey)){
                                setSuccess(question);
                            }else {
                                setScoreLevel(3 , question);
                            }
                        }else {
                            setScoreLevel(2, question);
                        }
                    } else {
                        setScoreLevel(1, question);
                    }
                } else {
                    setFailure(question);
                }

            }
        }));


        //Q3 Are the ontology metadata included in the ontology file- and consequently share the same identifiers or is the metadata record clearly identified by its own URI.
        this.setDefaultSuccess(2);
        

        // Q4: Does an ontology provide a version specific URI and is this URI resolvable/ dereferenceable?
        this.addResult(Tester.doEvaluation(ontology, questions.get(3), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                String ontologyVersionIri = ontology.getVersionIri();
                if (MetaDataExistTest.isValid(ontologyVersionIri)) {
                    if (URLValidTest.isValid(ontologyVersionIri)) {
                        if (ResolvableURLTest.isValid(ontologyVersionIri)) {
                            setSuccess(question);
                        } else {
                            setScoreLevel(2,question);
                        }
                    } else {
                        setScoreLevel(1, question);
                    }
                } else {
                    setFailure(question);
                }

            }
        }));

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
