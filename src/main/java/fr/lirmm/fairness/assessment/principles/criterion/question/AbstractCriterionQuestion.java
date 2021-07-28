package fr.lirmm.fairness.assessment.principles.criterion.question;

import java.util.Arrays;
import java.util.Optional;

public class AbstractCriterionQuestion {
    private final String question;
    private final Double[] points;
    private final String label;
    private final String[] properties;

    public AbstractCriterionQuestion(String label, String text, Double[] points) {
        this.label = label;
        this.question = text;
        this.points = points;
        this.properties = null;
    }

    public AbstractCriterionQuestion(String label, String text, Double[] points , String[] properties) {
        this.label = label;
        this.question = text;
        this.points = points;
        this.properties = null;
    }
    public String getQuestion() {
        return question;
    }

    public Double[] points() {
        return points;
    }

    public Double getMaxPoint(int toIndex){
        return  Arrays.stream(points).limit(toIndex).reduce(Double::sum).orElse(0.0);
    }

    public Double getMaxPoint(){
        return getMaxPoint(this.points.length);
    }

    public String getLabel() {
        return label;
    }
}
