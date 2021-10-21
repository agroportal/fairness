package fr.lirmm.fairness.assessment;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.lirmm.fairness.assessment.principles.AbstractPrinciple;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;
import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import fr.lirmm.fairness.assessment.utils.CombinedResult;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;


public class CombinedFair {


    private final Fair fair;
    private int fairCount;
    private TreeSet<Double> sortedScores;
    public CombinedFair(int fairCount) {
        fair = new Fair();
        this.fairCount = fairCount;
        this.sortedScores = new TreeSet<Double>();
    }

    public void addFairToCombine(JsonObject fair) throws JSONException {

        getFair().getScores().add(fair.get("score").getAsDouble() / fairCount );
        getFair().getScoresWeights().add(fair.get("maxCredits").getAsDouble() / fairCount);

        this.sortedScores.add(fair.get("score").getAsDouble());

        int i = 0;
        for (AbstractPrinciple principle : getFair().getPrinciples()) {
            combinePrinciple(i, fair.get(principle.getClass().getSimpleName()).getAsJsonObject());
            i++;
        }

    }

    public Fair getFair() {
        return fair;
    }

    private void combinePrinciple(int indexPrinciple , JsonObject newPrinciple) throws JSONException {
        AbstractPrinciple combinedPrinciple = this.getFair().getPrinciples()[indexPrinciple];
        combinedPrinciple.getScores().add(newPrinciple.get("score").getAsDouble() / fairCount);
        combinedPrinciple.getScoresWeights().add(newPrinciple.get("maxCredits").getAsDouble() / fairCount);
        int i = 0;
        for (AbstractPrincipleCriterion principleCriterion : combinedPrinciple.getPrincipleCriteria()) {
            combinePrincipleCriterion(combinedPrinciple , i , newPrinciple.get(principleCriterion.getClass().getSimpleName()).getAsJsonObject());
            i++;
        }


    }

    private void combinePrincipleCriterion(AbstractPrinciple combinedPrinciple,int indexCriterion , JsonObject newCriterion) throws JSONException {
        AbstractPrincipleCriterion combinedCriterion = combinedPrinciple.getPrincipleCriteria().get(indexCriterion);
        combinedCriterion.getScores().add(newCriterion.get("score").getAsDouble() / fairCount);
        combinedCriterion.getScoresWeights().add(newCriterion.get("maxCredits").getAsDouble() / fairCount);

        int i = 0;
        for (String result : newCriterion.get("results").getAsJsonObject().keySet()) {
            combineCriterionQuestion(combinedCriterion , i , newCriterion.get("results").getAsJsonObject().get(result).getAsJsonObject() , result);
            i++;
        }
    }

    private void combineCriterionQuestion(AbstractPrincipleCriterion combinedCriterion , int indexQuestion , JsonObject newQuestion , String questionLabel) throws JSONException {
        CombinedResult combinedQuestion;
        if(combinedCriterion.getResults().size() <= indexQuestion){

           combinedQuestion = new CombinedResult(newQuestion.get("score").getAsDouble() / fairCount  ,
                    new AbstractCriterionQuestion(questionLabel , newQuestion.get("question").getAsString() ,
                            AbstractCriterionQuestion.getQuestionResultsArray(newQuestion.get("points").getAsJsonArray()) ,
                           getProperties(newQuestion)));
            combinedCriterion.getResults().add(combinedQuestion);

        }else {
            combinedQuestion = (CombinedResult) combinedCriterion.getResults().get(indexQuestion);
            combinedQuestion.setResult( combinedQuestion.getScore() + (newQuestion.get("score").getAsDouble() / fairCount), combinedQuestion.getQuestion());
        }
        combinedQuestion.addStateCount(newQuestion.get("score").getAsDouble());
    }


    private List<String> getProperties(JsonObject newQuestion){

        JsonElement properties = new Gson().fromJson(newQuestion.get("properties"), JsonElement.class);
        List<String> out = new ArrayList<>();

        if(properties == null)
            out =  null;
        else if(properties.isJsonObject())
             out =  new ArrayList(properties.getAsJsonObject().keySet());
        else  if(properties.isJsonArray()){
            List<String> finalOut = out;
            properties.getAsJsonArray().forEach((x)-> {
                finalOut.add(x.getAsString());
            });
        }

       return out;
    }


    public double getMinScore(){
        return this.sortedScores.first();
    }
    public double getMaxScore(){
        return this.sortedScores.last();
    }

    public double getMedianScore(){
        int middle = this.fairCount/2;
        Double[] sortedScores = new Double[this.fairCount];

        sortedScores = this.sortedScores.toArray(sortedScores);
        double medianValue = 0;
        if (this.fairCount%2 == 1)
            medianValue = sortedScores[middle];
        else
            medianValue = (sortedScores[middle-1] + sortedScores[middle]) / 2;
        return medianValue;
    }
}
