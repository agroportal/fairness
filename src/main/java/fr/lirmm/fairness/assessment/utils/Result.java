package fr.lirmm.fairness.assessment.utils;

import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;

import java.io.Serializable;

public class Result implements Serializable {
    private static final long serialVersionUID = 2746658890779865382L;

    private double score;
    private String explication;
    private AbstractCriterionQuestion question;

    public Result() {
        this.score = 0;
        this.explication = "";
        this.question = null;
    }

    public Result(double score, String explication, AbstractCriterionQuestion question) {
        this.score = score;
        this.explication = explication;
        this.question = question;
    }

    public double getScore() {
        return score;
    }

    public String getExplication() {
        return explication;
    }

    public AbstractCriterionQuestion getQuestion() {
        return question;
    }

    public void setResult(double score , String explication , AbstractCriterionQuestion question){
        this.score = score;
        this.explication = explication;
        this.question = question;
   }


    public boolean isSuccess() {
        return  this.score == this.question.getPoints();
    }
}
