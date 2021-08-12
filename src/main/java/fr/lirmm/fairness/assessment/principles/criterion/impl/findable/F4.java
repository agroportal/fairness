package fr.lirmm.fairness.assessment.principles.criterion.impl.findable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.internal.LinkedTreeMap;
import fr.lirmm.fairness.assessment.Configuration;
import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import fr.lirmm.fairness.assessment.principles.criterion.question.Testable;
import fr.lirmm.fairness.assessment.principles.criterion.question.Tester;
import fr.lirmm.fairness.assessment.utils.Result;
import org.json.JSONException;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class F4 extends AbstractPrincipleCriterion {

    private static final long serialVersionUID = -3047153112624011099L;

    @Override
    protected void doEvaluation(Ontology ontology) throws JSONException, IOException {

        Result r;
        ArrayList<LinkedTreeMap> repos = (ArrayList<LinkedTreeMap>) Configuration.getInstance().getRepositoriesConfig().get("repositories");
        ArrayList<LinkedTreeMap> libs = (ArrayList<LinkedTreeMap>) Configuration.getInstance().getRepositoriesConfig().get("libraries");
        String currentRepo = ontology.getPortalInstance().getUrl();
        List<String> includedInDataCatalog = ontology.getIncludedInDataCatalog();


        // Q1: Is the ontology registered in multiple ontology libraries?
        r = Tester.doEvaluation(ontology, questions.get(0), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                int found = 0;
                if (includedInDataCatalog.isEmpty()) {
                    for (int i = 0; i < repos.size() && found < question.getPoints().size(); i++) {
                        if (includedInDataCatalog.contains(repos.get(i).get("url").toString()) || (currentRepo.contains(repos.get(i).get("url").toString())))
                            found++;
                    }

                    for (int i = 0; i < libs.size() && found < question.getPoints().size(); i++) {
                        if (includedInDataCatalog.contains(libs.get(i).get("url").toString()) || (currentRepo.contains(libs.get(i).get("url").toString())))
                            found++;
                    }

                    setScoreLevel(found, question);
                } else {
                    setFailure(question.getMaxPoint(0).getExplanation() + getLabels(repos) + ""
                            + getLabels(libs), question);
                }

            }
        });
        this.addResult(r);


        // Q2: Is the ontology registered in multiple open ontology repositories?  Here we
        // consider OBO foundry, NCBO Bioportal, Ontobee, Aber and OLS.
        r = Tester.doEvaluation(ontology, questions.get(1), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                int found = 0;
                List<String> includedInDataCatalog = ontology.getIncludedInDataCatalog();
                if (includedInDataCatalog.isEmpty()) {
                    for (int i = 0; i < repos.size() && found < question.getPoints().size(); i++) {
                        if (includedInDataCatalog.contains(repos.get(i).get("url").toString()) || (currentRepo.contains(repos.get(i).get("url").toString())))
                            found++;
                    }

                    setScoreLevel(found, question);
                } else {
                    setFailure(question.getMaxPoint(0).getExplanation() + getLabels(repos), question);
                }

            }
        });
        this.addResult(r);

        //Q3: Are the ontology libraries or repositories properly indexed by Web search engines?
        //TODO implement F4Q3
        this.setNotResolvable(2);


    }

    private List<Object> getLabels(ArrayList<LinkedTreeMap> repos) {
        return Arrays.stream(repos.toArray()).map(x -> ((LinkedTreeMap) x).get("label")).collect(Collectors.toList());
    }

}
