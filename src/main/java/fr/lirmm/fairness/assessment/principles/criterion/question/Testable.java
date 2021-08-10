package fr.lirmm.fairness.assessment.principles.criterion.question;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.utils.QuestionResult;

public abstract class Testable {

    private final QuestionResult result = new QuestionResult();

    public abstract void  doTest(Ontology ontology , AbstractCriterionQuestion question);


    public void setScore(double score ,String explication ,AbstractCriterionQuestion question) {
        result.setResult(score , explication , question);
    }

    public void setScore(int index ,AbstractCriterionQuestion question) {
        QuestionResult questionResult = question.getMaxPoint(index);
        setScore(questionResult.getScore() , questionResult.getExplanation() , question);
    }

    public void setSuccess(String explication , AbstractCriterionQuestion question) {
        result.setResult(question.getMaxPoint().getScore() , explication ,question);
    }

    public void setSuccess(AbstractCriterionQuestion question) {
       this.setSuccess(question.getMaxPoint().getExplanation() , question);
    }

    public void setFailure(String explication , AbstractCriterionQuestion question){
        result.setResult(0 , explication , question);
    }

    public void setFailure(AbstractCriterionQuestion question){
        setFailure(question.getPoints().get(0).getExplanation() , question);
    }
    public QuestionResult getResult() {
        return result;
    }
}
