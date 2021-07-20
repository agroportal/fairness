package fr.lirmm.fairness.assessment.utils;

import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;

public class QuestionResult  extends  Result{

    private String explication;


    public QuestionResult() {
        super();
        this.explication = "";
    }

    public QuestionResult(double score, String explication, AbstractCriterionQuestion question) {
        super(score, question);
        this.explication = explication;
    }


    public void setResult(double score, String explication, AbstractCriterionQuestion question) {
        super.setResult(score,question);
        this.explication = explication;
    }

    public String getExplication() {
        return explication;
    }
}
