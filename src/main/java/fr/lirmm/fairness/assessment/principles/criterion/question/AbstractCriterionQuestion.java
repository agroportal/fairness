package fr.lirmm.fairness.assessment.principles.criterion.question;

import java.util.function.Function;

public class AbstractCriterionQuestion {
    private String question;
    private Double points;

    public AbstractCriterionQuestion(String text, Double maxScore) {
        this.question = text;
        this.points = maxScore;
    }

    public String getQuestion() {
        return question;
    }

    public Double getPoints() {
        return points;
    }



}
