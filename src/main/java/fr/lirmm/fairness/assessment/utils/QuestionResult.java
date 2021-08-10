package fr.lirmm.fairness.assessment.utils;

import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;

public class QuestionResult  extends  Result{

    public  static final  String DEFAULT_NOT_RESOLVABLE = "Default value 0, this indicator can not be evaluated.";

    private String explanation;

    public QuestionResult() {
        super();
        this.explanation = "";
    }

    public QuestionResult(double score, String explication, AbstractCriterionQuestion question) {
        super(score, question);
        this.explanation = explication;
    }

    public static Result failure(AbstractCriterionQuestion question) {
        QuestionResult result = question.getMaxPoint(0);
        result.question = question;
        return result;
    }


    public void setResult(double score, String explication, AbstractCriterionQuestion question) {
        super.setResult(score,question);
        this.explanation = explication;
    }

    public String getExplanation() {
        return explanation;
    }

    public static Result notResolvable(AbstractCriterionQuestion question){
        QuestionResult r = new QuestionResult();
        r.setResult(0.0 , QuestionResult.DEFAULT_NOT_RESOLVABLE , question);
        return r;
    }

    @Override
    public String toString() {
        return "QuestionResult{" +
                "explication='" + explanation + '\'' +
                ", score=" + score +
                ", question=" + question +
                '}';
    }
}
