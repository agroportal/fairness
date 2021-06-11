package fr.lirmm.fairness.assessment.principles.criterion.Question;

public class AbstractCriterionQuestion {
    private String question;
    private Integer points;

    public AbstractCriterionQuestion(String text, Integer maxScore) {
        this.question = text;
        this.points = maxScore;
    }

    public String getQuestion() {
        return question;
    }

    public Integer getPoints() {
        return points;
    }
}
