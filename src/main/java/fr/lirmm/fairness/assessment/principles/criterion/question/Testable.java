package fr.lirmm.fairness.assessment.principles.criterion.question;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import fr.lirmm.fairness.assessment.utils.QuestionResult;
import fr.lirmm.fairness.assessment.utils.Result;

public abstract class Testable {

    private final QuestionResult result = new QuestionResult();

    public abstract void  doTest(Ontology ontology , AbstractCriterionQuestion question);


    public void setScore(double score ,String explication ,AbstractCriterionQuestion question) {
        result.setResult(score , explication , question);
    }

    public void setSuccess(String explication , AbstractCriterionQuestion question) {
        result.setResult(question.getMaxPoint() , explication ,question);
    }

    public void setFailure(String explication , AbstractCriterionQuestion question){
        result.setResult(0 , explication , question);
    }

    public QuestionResult getResult() {
        return result;
    }
}
