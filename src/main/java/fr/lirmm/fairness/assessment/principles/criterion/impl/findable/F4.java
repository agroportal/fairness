package fr.lirmm.fairness.assessment.principles.criterion.impl.findable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.internal.LinkedTreeMap;
import fr.lirmm.fairness.assessment.models.Configuration;
import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import fr.lirmm.fairness.assessment.principles.criterion.question.Testable;
import fr.lirmm.fairness.assessment.principles.criterion.question.Tester;
import fr.lirmm.fairness.assessment.principles.criterion.question.tests.MetaDataExistTest;
import org.json.JSONException;

import fr.lirmm.fairness.assessment.models.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;

public class F4 extends AbstractPrincipleCriterion {

    private static final long serialVersionUID = -3047153112624011099L;

    @Override
    protected void doEvaluation(Ontology ontology) throws JSONException, IOException {

        ArrayList<LinkedTreeMap<?,?>> repos = (ArrayList<LinkedTreeMap<?,?>>) Configuration.getInstance().getRepositoriesConfig().get("repositories");
        ArrayList<LinkedTreeMap<?,?>> libs = (ArrayList<LinkedTreeMap<?,?>>) Configuration.getInstance().getRepositoriesConfig().get("libraries");




        // Q1: Is the ontology registered in multiple ontology libraries?
        this.addResult(Tester.doEvaluation(ontology, questions.get(0), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                String currentRepo = ontology.getPortalInstance().getUrl();
                List<String> includedInDataCatalog = ontology.getIncludedInDataCatalog();
                int found = 0;
                found+= foundIn(repos,currentRepo)? 1 : 0;
                if (MetaDataExistTest.isValid(includedInDataCatalog.toString())) {
                    for (String catalog : includedInDataCatalog) {
                        found += foundIn(repos, catalog)? 1 : 0;
                        found += foundIn(libs, catalog)? 1 : 0;
                    }

                }
                if(found>0){
                    setScoreLevel(found, question);
                } else {
                    setFailure(question.getMaxPoint(0).getExplanation() + getLabels(repos) + ""
                            + getLabels(libs), question);
                }
            }
        }));


        // Q2: Is the ontology registered in multiple open ontology repositories?  Here we
        // consider OBO foundry, NCBO Bioportal, Ontobee, Aber and OLS.
        this.addResult(Tester.doEvaluation(ontology, questions.get(1), new Testable() {
            @Override
            public void doTest(Ontology ontology, AbstractCriterionQuestion question) {
                int found = 0;
                List<String> includedInDataCatalog = ontology.getIncludedInDataCatalog();
                String currentRepo = ontology.getPortalInstance().getUrl();

                found+= foundIn(repos,currentRepo) ? 1 : 0;
                if (MetaDataExistTest.isValid(includedInDataCatalog.toString())) {
                    for (String catalog : includedInDataCatalog) {
                        found += foundIn(repos, catalog)? 1 : 0;
                    }

                }

                if(found>0){
                    setScoreLevel(found, question);
                } else {
                    setFailure(question.getMaxPoint(0).getExplanation() + getLabels(repos), question);
                }

            }
        }));

        //Q3: Are the ontology libraries or repositories properly indexed by Web search engines?
        //TODO implement F4Q3
        this.setNotResolvable(2);


    }


    private boolean foundIn(ArrayList<LinkedTreeMap<?,?>> catalogs , String catalogUrlToTest){
        return catalogs.stream()
                .anyMatch(dataCatalog -> catalogUrlToTest.contains(dataCatalog.get("url").toString()));
    }
    private List<Object> getLabels(ArrayList<LinkedTreeMap<?,?>> repos) {
        return Arrays.stream(repos.toArray()).map(x -> ((LinkedTreeMap<?,?>) x).get("label")).collect(Collectors.toList());
    }

}
