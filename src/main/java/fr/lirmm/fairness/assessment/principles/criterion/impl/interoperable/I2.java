package fr.lirmm.fairness.assessment.principles.criterion.impl.interoperable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.*;

import com.google.gson.internal.LinkedTreeMap;
import fr.lirmm.fairness.assessment.models.Configuration;
import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import fr.lirmm.fairness.assessment.principles.criterion.question.Testable;
import fr.lirmm.fairness.assessment.principles.criterion.question.Tester;
import fr.lirmm.fairness.assessment.principles.criterion.question.tests.MetaDataExistTest;
import org.json.JSONException;

import fr.lirmm.fairness.assessment.models.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;
//import fr.lirmm.fairness.assessment.utils.OntologyRestApi;

public class I2 extends AbstractPrincipleCriterion {

    private static final long serialVersionUID = -8239004504267037012L;

    @Override
    protected void doEvaluation(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException {



        // Q1: Does an ontology import other FAIR vocabularies?
        this.addResult(Tester.doEvaluation(ontology, questions.get(0), new Testable() {
            //TODO in futur test the faire score of the imported ontologies, and do an average in all questions
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                List<String> useImports = ontology.getUseImports();

                if (MetaDataExistTest.isValid(useImports.toString())) {
                    this.setSuccess(question);
                } else {
                    this.setFailure(question);
                }
            }
        }));


        // Q2: Does an ontology reuse other vocabularies URIs?
        this.addResult(Tester.doEvaluation(ontology, questions.get(1), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                List<String> ontologyRelatedTo = ontology.getOntologyRelatedTo();
                if (MetaDataExistTest.isValid(ontologyRelatedTo.toString())) {
                    this.setSuccess(question);
                } else {
                    this.setFailure(question);
                }
            }
        }));


        // Q3: If yes, does it include the minimum information for those URIs(eg. MIREOT)?
        this.setNotResolvable(2);

        // Q4: Is the ontology aligned to other FAIR vocabularies?
        this.addResult(Tester.doEvaluation(ontology, questions.get(3), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                List<String> isAlignedTo = ontology.getIsAlignedTo();
                if (MetaDataExistTest.isValid(isAlignedTo.toString())) {
                    this.setSuccess(question);
                } else {
                    this.setFailure(question);
                }
            }
        }));


        // Q5: If yes, are those alignements well represented and to unambiguous entities?
        this.setNotResolvable(4);

        // Q6: Does an ontology provide information about influential other vocabularies?
        this.addResult(Tester.doEvaluation(ontology, questions.get(5), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                List<String> similarTo = ontology.getSimilarTo();
                String explanationEvolution = ontology.getExplanationEvolution();
                String translationOfWork = ontology.getTranslationOfWork();
                String generalizes = ontology.getGeneralizes();
                String isPartOf = ontology.getViewOf();


                if ( MetaDataExistTest.isValid(similarTo.toString()) || MetaDataExistTest.isValid(translationOfWork)
                        || MetaDataExistTest.isValid(explanationEvolution)
                        || MetaDataExistTest.isValid(generalizes)
                        || MetaDataExistTest.isValid(isPartOf)) {
                    this.setSuccess(question);
                } else {
                    this.setFailure(question);
                }
            }
        }));


        // Q7: Does an ontology reuse standards metadata vocabularies to describe its metadata?
        this.addResult(Tester.doEvaluation(ontology, questions.get(6), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                try {
                    ArrayList<LinkedTreeMap<?,?>> vocs = (ArrayList<LinkedTreeMap<?,?>>) Configuration.getInstance().getMetadataVocConfig().get("vocabularies");
                    double total = 0.0;
                    int count = 0;

                    List<String> metadataVoc = ontology.getMetadataVoc();
                    if (MetaDataExistTest.isValid(metadataVoc.toString())) {
                        for (LinkedTreeMap<?, ?> voc : vocs) {
                            String pref = (String) voc.get("prefix");
                            if (metadataVoc.contains(pref)) {
                                count++;
                                total += (double) voc.get("score");
                            }
                        }
                    }

                    if(total == 0.0 && count>0){
                        setScore(question.getMaxPoint().getScore() * (total / count), question.getMaxPoint().getExplanation(), question);
                     } else {
                        setFailure(question);
                    }
                } catch (IOException e) {
                    setFailure("Fail to load the config file", question);
                }

            }
        }));

    }

}
