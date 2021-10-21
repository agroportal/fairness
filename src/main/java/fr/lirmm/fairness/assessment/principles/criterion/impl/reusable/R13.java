package fr.lirmm.fairness.assessment.principles.criterion.impl.reusable;

import java.io.IOException;
import java.util.Arrays;

import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import fr.lirmm.fairness.assessment.principles.criterion.question.Testable;
import fr.lirmm.fairness.assessment.principles.criterion.question.Tester;
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
                if ((ontology.getProjects().isEmpty()) && (ontology.getEndorsedBy().isEmpty())) {
                    this.setFailure(question);
                } else {
                    int count = 0;
                    if ((!ontology.getEndorsedBy().isEmpty())) {
                        count++;
                    }
                    if (!ontology.getProjects().isEmpty()) {
                        count++;
                    }
                    this.setScoreLevel(count,question);
                }
            }
        }));



        //Q2: Is the ontology included in a specific community set or group?
        this.addResult(Tester.doEvaluation(ontology, questions.get(1), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                final String group = ontology.getGroup();
                String[] groupsToCheck = new String[]{"OBO", "WHEAT", "CROP", "INRAE"};
                String[] oboFoundryOntologiesToCheck = new String[]{"BFO", "CHEBI", "GO", "PATO", "PO", "PR"};

                if (group.equals("OBO") && Arrays.asList(oboFoundryOntologiesToCheck).contains(ontology.getAcronym())) {
                    this.setSuccess(question);
                }else if (group.equals("OBO")) {
                    this.setScoreLevel(1 , question);
                }else if (Arrays.asList(groupsToCheck).contains(group)) {
                    this.setScoreLevel(0, question);
                }else {
                    this.setFailure( question);
                }
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

