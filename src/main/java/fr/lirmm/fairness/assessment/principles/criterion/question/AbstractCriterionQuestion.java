package fr.lirmm.fairness.assessment.principles.criterion.question;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import fr.lirmm.fairness.assessment.utils.QuestionResult;
import org.json.JSONException;

import java.util.*;


public class AbstractCriterionQuestion {
    private final String question;
    private final List<QuestionResult> points;
    private final String label;
    private final List<String> properties;

    public AbstractCriterionQuestion(String label, String text, List<QuestionResult> points) {
        this.label = label;
        this.question = text;
        this.points = points;
        this.properties = null;
    }

    public AbstractCriterionQuestion(String label, String text, List<QuestionResult> points , List<String> properties) {
        this.label = label;
        this.question = text;
        this.points = points;
        this.points.sort(new Comparator<QuestionResult>() {
            @Override
            public int compare(QuestionResult o1, QuestionResult o2) {
                return (int) (o1.getScore() - o2.getScore());
            }
        });
        this.properties = properties;
    }
    public String getQuestion() {
        return question;
    }

    public List<QuestionResult> getPoints() {
        return points;
    }

    public  QuestionResult getMaxPoint(int index){
        return  getPoints().get(Math.min(getPoints().size()-1,index));
    }

    public QuestionResult getMaxPoint(){
        return getMaxPoint(this.points.size()-1);
    }

    public String getLabel() {
        return label;
    }

    public static List<QuestionResult> getQuestionResultsArray(JsonArray jsonArray) throws JSONException {
        List<QuestionResult> result = new ArrayList(jsonArray.size());
        String explanation = "";
        for (JsonElement jsonElement : jsonArray) {
            explanation = ( jsonElement.getAsJsonObject().get("explanation") != null ? jsonElement.getAsJsonObject().get("explanation").getAsString() :"");
            result.add(
                    new QuestionResult( jsonElement.getAsJsonObject().get("score").getAsDouble() ,
                            explanation, new HashMap<>(),null));
        }
        return result;
    }

    public List<String> getProperties() {
        return properties;
    }
}
