package fr.lirmm.fairness.assessment.utils;

import com.google.gson.annotations.Expose;
import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import java.util.Map;

public class QuestionResult  extends  Result{

    public  static final  String DEFAULT_NOT_RESOLVABLE = "Default value 0, this indicator can not be evaluated.";

    @Expose
    private String explanation;
    private Map<String , String> values;

    public QuestionResult() {
        super();
        this.explanation = "";
        this.values = null;
    }

    public QuestionResult(double score, String explication, Map<String , String> values,AbstractCriterionQuestion question) {
        super(score, question);
        this.explanation = explication;
        this.values = values;
    }




    public void setResult(double score, String explication, Map<String ,String> values, AbstractCriterionQuestion question) {
        super.setResult(score,question);
        this.explanation = explication;
        this.values = values;
    }

    public String getExplanation() {
        return explanation;
    }

    public static Result notResolvable(AbstractCriterionQuestion question){
        QuestionResult r = new QuestionResult();
        r.setResult(0.0 , QuestionResult.DEFAULT_NOT_RESOLVABLE , null,question );
        return r;
    }

    public static Result success(String explanation,AbstractCriterionQuestion question) {
        QuestionResult r = new QuestionResult();
        r.setResult(question.getMaxPoint().getScore() , explanation , null,question );
        return r;
    }

    public Map<String ,String> getValues() {
        return values;
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
