package fr.lirmm.fairness.assessment;

import com.google.gson.JsonObject;
import fr.lirmm.fairness.assessment.principles.AbstractPrinciple;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;
import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;
import fr.lirmm.fairness.assessment.utils.Result;


public class CombinedFair {


    private final Fair fair;
    private double fairCount;
    public CombinedFair(double fairCount) {
        fair = new Fair();
        this.fairCount = fairCount;
    }

    public void addFairToCombine(JsonObject fair){

        getFair().getScores().add(fair.get("score").getAsDouble() / fairCount );
        getFair().getScoresWeights().add(fair.get("maxCredits").getAsDouble() / fairCount);
        int i = 0;
        for (AbstractPrinciple principle : getFair().getPrinciples()) {
            combinePrinciple(i, fair.get(principle.getClass().getSimpleName()).getAsJsonObject());
            i++;
        }

    }

    public Fair getFair() {
        return fair;
    }

    private void combinePrinciple(int indexPrinciple , JsonObject newPrinciple){
        AbstractPrinciple combinedPrinciple = this.getFair().getPrinciples()[indexPrinciple];
        combinedPrinciple.getScores().add(newPrinciple.get("score").getAsDouble() / fairCount);
        combinedPrinciple.getScoresWeights().add(newPrinciple.get("maxCredits").getAsDouble() / fairCount);
        int i = 0;
        for (AbstractPrincipleCriterion principleCriterion : combinedPrinciple.getPrincipleCriteria()) {
            combinePrincipleCriterion(combinedPrinciple , i , newPrinciple.get(principleCriterion.getClass().getSimpleName()).getAsJsonObject());
            i++;
        }


    }

    private void combinePrincipleCriterion(AbstractPrinciple combinedPrinciple,int indexCriterion , JsonObject newCriterion){
        AbstractPrincipleCriterion combinedCriterion = combinedPrinciple.getPrincipleCriteria().get(indexCriterion);
        combinedCriterion.getScores().add(newCriterion.get("score").getAsDouble() / fairCount);
        combinedCriterion.getScoresWeights().add(newCriterion.get("maxCredits").getAsDouble() / fairCount);

        int i = 0;
        for (String result : newCriterion.get("results").getAsJsonObject().keySet()) {
            combineCriterionQuestion(combinedCriterion , i , newCriterion.get("results").getAsJsonObject().get(result).getAsJsonObject() , result);
            i++;
        }
    }

    private void combineCriterionQuestion(AbstractPrincipleCriterion combinedCriterion , int indexQuestion , JsonObject newQuestion , String questionLabel){
        if(combinedCriterion.getResults().size() <= indexQuestion){
            combinedCriterion.getResults().add(new Result(newQuestion.get("score").getAsDouble() / fairCount , "" ,
                    new AbstractCriterionQuestion(questionLabel , newQuestion.get("question").getAsString() , newQuestion.get("maxCredits").getAsDouble())));
        }else {
            Result combinedQuestion = combinedCriterion.getResults().get(indexQuestion);
            combinedQuestion.setResult( combinedQuestion.getScore() + (newQuestion.get("score").getAsDouble() / fairCount), "",combinedQuestion.getQuestion());
        }
    }
}
