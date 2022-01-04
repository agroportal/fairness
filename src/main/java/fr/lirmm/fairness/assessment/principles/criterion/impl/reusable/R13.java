package fr.lirmm.fairness.assessment.principles.criterion.impl.reusable;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import fr.lirmm.fairness.assessment.principles.criterion.question.Testable;
import fr.lirmm.fairness.assessment.principles.criterion.question.Tester;
import fr.lirmm.fairness.assessment.principles.criterion.question.tests.MetaDataExistTest;
import fr.lirmm.fairness.assessment.utils.Result;
import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class R13 extends AbstractPrincipleCriterion {

    private static final long serialVersionUID = 8721990196106759026L;


    @Override
    protected void doEvaluation(Ontology ontology) throws JSONException, IOException {


        // Q1: Does an ontology provide information about projects using or organization endorsing?
        this.addResult(Tester.doEvaluation(ontology, questions.get(0), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {

                int count = 0;
                if (MetaDataExistTest.isValid(ontology.getEndorsedBy().toString())) {
                    count++;
                }
                if (MetaDataExistTest.isValid(ontology.getProjects().toString())) {
                    count++;
                }

                this.setScoreLevel(count,question);
            }
        }));



        //Q2: Is the ontology included in a specific community set or group?
        this.addResult(Tester.doEvaluation(ontology, questions.get(1), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                final List<String> groups = ontology.getGroup();
                String[] groupsToCheck = new String[]{"OBO-FOUNDRY", "WHEAT", "CROP", "INRAE"};
                String[] oboFoundryOntologiesToCheck = new String[]{"BFO", "CHEBI", "GO", "PATO", "PO", "PR"};
                int maxScoreLevel = 0;
                for (String group : groups) {
                    if(group.contains("OBO-FOUNDRY") && Arrays.asList(oboFoundryOntologiesToCheck).contains(ontology.getAcronym())){
                        maxScoreLevel = question.getPoints().size();
                    }else if (group.contains("OBO-FOUNDRY")) {
                        if(maxScoreLevel < 2)  maxScoreLevel = 2;
                    }else {
                        Optional<String> foundGroup = Arrays.stream(groupsToCheck).filter(group::contains).findFirst();
                        if(foundGroup.isPresent()){
                            if(maxScoreLevel < 1)  maxScoreLevel = 1;
                        }
                    }
                }
                this.setScoreLevel(maxScoreLevel,question);
            }
        }));

        // Q3: Is the ontology openly and freely available?
        this.addResult(Tester.doEvaluation(ontology, questions.get(2), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                String visibility = ontology.getAccessRights();
                if (visibility.contains("public")) {
                    this.setSuccess(question);
                } else {
                    this.setFailure(question);
                }
            }
        }));

    }

}

