package fr.lirmm.fairness.assessment.principles.criterion.question;

public class AbstractCriterionQuestion {
    private final String question;
    private final Double points;
    private final String label;

    public AbstractCriterionQuestion(String label, String text, Double maxScore) {
        this.label = label;
        this.question = text;
        this.points = maxScore;
    }

    public String getQuestion() {
        return question;
    }

    public Double getPoints() {
        return points;
    }

    public String getLabel() {
        return label;
    }
}
