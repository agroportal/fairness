package fr.lirmm.fairness.assessment.principles.criterion.impl.reusable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import fr.lirmm.fairness.assessment.principles.criterion.question.Testable;
import fr.lirmm.fairness.assessment.principles.criterion.question.Tester;
import fr.lirmm.fairness.assessment.principles.criterion.question.tests.MetaDataExistTest;
import fr.lirmm.fairness.assessment.utils.Result;
import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;
import fr.lirmm.fairness.assessment.utils.OntologyRestApi;

public class R1 extends AbstractPrincipleCriterion {

    private static final long serialVersionUID = 5277719201473995295L;


    @Override
    protected void doEvaluation(Ontology ontology) throws JSONException, IOException, MalformedURLException, SocketTimeoutException {


        // Q1: Does the ontology provide information about how classes are defined?
        Result r = Tester.doEvaluation(ontology, questions.get(0), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                String[] allMetadata = {ontology.getPrefLabelProperty(), ontology.getSynonymProperty(),
                        ontology.getDefinitionProperty(), ontology.getAuthorProperty(),
                        ontology.getObsoleteProperty()};
                int count = 0;
                for (String metadata : allMetadata) {
                    if (MetaDataExistTest.isValid(metadata)) {
                        count ++;
                    }
                }
                setScore(count, question);

            }
        });
        this.addResult(r);

        // Q2: Does the ontology provide information about its hierarchy?
        r = Tester.doEvaluation(ontology, questions.get(0), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                String[] allMetadata = {ontology.getHierarchyProperty(), ontology.getObsoleteParent()};
                int count = 0;
                for (String metadata : allMetadata) {
                    if (MetaDataExistTest.isValid(metadata)) {
                        count ++;
                    }
                }

                setScore(count, question);

            }
        });
        this.addResult(r);

        //Q3: How much of the ontology classes are described with labels?
        this.setNotResolvable(2);
        //Q4: How much of the ontology classes or concepts are defined using a text description?
        this.setNotResolvable(3);
        //Q5: How much of the ontology classes (or concepts) are defined using a property restriction (e.g. OWL quantifier, cardinality or has value restrictions) or an equivalent class (e.g. an OWL named class)?
        this.setNotResolvable(4);
        //Q6: How much of the ontology objects provide provenance information with annotation properties (e.g., author, date)?
        this.setNotResolvable(5);

    }

}
