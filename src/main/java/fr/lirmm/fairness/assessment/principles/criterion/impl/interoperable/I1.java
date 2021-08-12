package fr.lirmm.fairness.assessment.principles.criterion.impl.interoperable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Arrays;

import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import fr.lirmm.fairness.assessment.principles.criterion.question.Testable;
import fr.lirmm.fairness.assessment.principles.criterion.question.Tester;
import fr.lirmm.fairness.assessment.principles.criterion.question.tests.MetaDataExistTest;
import fr.lirmm.fairness.assessment.utils.QuestionResult;
import fr.lirmm.fairness.assessment.utils.Result;
import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class I1 extends AbstractPrincipleCriterion {

    private static final long serialVersionUID = -7037423784770664158L;

    @Override
    protected void doEvaluation(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException {

        String ontoLang = ontology.getHasOntoLang();
        String ontoSyntax = ontology.getHasOntoSyntax();
        String formalLevel = ontology.getHasFormalLevel();
        String hasFormat = ontology.getHasFormat();
        String isFormatOf = ontology.getIsFormatOf();

        // Q1: What is the representation language used for (metadata)data?
        Result r = Tester.doEvaluation(ontology, questions.get(0), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                String[] languages = {"PDF", "TXT", "CSV", "XML", "OBO", "RDFS", "SKOS", "OWL"};
                String ontoLang = ontology.getHasOntoLang();
                int index = Arrays.asList(languages).indexOf(ontoLang.trim());
                if (index > -1)
                    setScoreLevel(index, question);
                else
                    setFailure(question);
            }
        });
        this.addResult(r);

        // Q2: Is the representation language used a W3C Recommendation?
        r = Tester.doEvaluation(ontology, questions.get(1), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                String[] languages = {"XML", "RDFS", "SKOS", "OWL"};
                int index = Arrays.asList(languages).indexOf(ontoLang.trim());
                if (index > -1)
                    setSuccess(question);
                else
                    setFailure(question);
            }
        });
        this.addResult(r);

        // Q3: Is the syntax of the ontology informed?
        this.addResult(Tester.doEvaluation(ontology, questions.get(2), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                if (MetaDataExistTest.isValid(ontoSyntax)) {
                    QuestionResult result = question.getMaxPoint();
                    this.setSuccess(result.getExplanation() + " (" + ontoSyntax + ")", question);
                } else {
                    this.setFailure(question);
                }
            }
        }));


        // Q4: Is the formality level of the ontology asserted by the author?
        this.addResult(Tester.doEvaluation(ontology, questions.get(3), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                if (MetaDataExistTest.isValid(formalLevel)) {
                    QuestionResult result = question.getMaxPoint();
                    this.setSuccess(result.getExplanation() + " (" + formalLevel + ")", question);
                    this.setSuccess(question);
                } else {
                    this.setFailure(question);
                }
            }
        }));

        // Q5: Is the availability of other formats informed?
        this.addResult(Tester.doEvaluation(ontology, questions.get(4), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                if (MetaDataExistTest.isValid(hasFormat) && MetaDataExistTest.isValid(isFormatOf)) {
                    this.setSuccess(question);
                } else {
                    this.setFailure(question);
                }
            }
        }));


    }

}
