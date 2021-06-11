package fr.lirmm.fairness.assessment;

import fr.lirmm.fairness.assessment.principles.AbstractPrinciple;
import fr.lirmm.fairness.assessment.principles.criterion.AbstractPrincipleCriterion;
import fr.lirmm.fairness.assessment.utils.Result;


public class CombinedFair {


    private final Fair fair;
    private double fairCount;
    public CombinedFair(double fairCount) {
        fair = new Fair();
        this.fairCount = fairCount;
    }

    public void addFairToCombine(Fair fair){

        getFair().getScores().add(fair.getTotalScore() / fairCount );
        getFair().getScoresWeights().add(fair.getTotalScoreWeight() / fairCount);

        for (int i = 0; i < fair.getPrinciples().length ; i++) {
            combinePrinciple(i, fair.getPrinciples()[i]);
        }

    }

    public Fair getFair() {
        return fair;
    }

    private void combinePrinciple(int indexPrinciple , AbstractPrinciple newPrinciple){
        AbstractPrinciple combinedPrinciple = this.getFair().getPrinciples()[indexPrinciple];
        combinedPrinciple.getScores().add(newPrinciple.getTotalScore() / fairCount);
        combinedPrinciple.getScoresWeights().add(newPrinciple.getTotalScoreWeight() / fairCount);

        for (int i = 0; i < newPrinciple.getPrincipleCriteria().size() ; i++) {
            combinePrincipleCriterion(combinedPrinciple , i , newPrinciple.getPrincipleCriteria().get(i));
        }

    }

    private void combinePrincipleCriterion(AbstractPrinciple combinedPrinciple,int indexCriterion , AbstractPrincipleCriterion newCriterion){
        AbstractPrincipleCriterion combinedCriterion = combinedPrinciple.getPrincipleCriteria().get(indexCriterion);
        combinedCriterion.getScores().add(newCriterion.getTotalScore() / fairCount);
        combinedCriterion.getScoresWeights().add(newCriterion.getTotalScoreWeight() / fairCount);

        for (int i = 0; i < newCriterion.getResults().size() ; i++) {
            combienCriterionQuestion(combinedCriterion , i , newCriterion.getResults().get(i));
        }
    }

    private void combienCriterionQuestion(AbstractPrincipleCriterion combinedCriterion , int indexQuestion , Result newQuestion){
        if(combinedCriterion.getResults().size() <= indexQuestion){
            combinedCriterion.getResults().add(new Result(newQuestion.getScore()/ fairCount , "" ,  newQuestion.getQuestion()));
        }else {
            Result combinedQuestion = combinedCriterion.getResults().get(indexQuestion);
            combinedQuestion.setResult( combinedQuestion.getScore() + (newQuestion.getScore() / fairCount), "",combinedQuestion.getQuestion());
        }
    }
}
