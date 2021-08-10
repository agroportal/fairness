package fr.lirmm.fairness.assessment.utils;

import fr.lirmm.fairness.assessment.principles.criterion.question.AbstractCriterionQuestion;

public class CombinedResult extends Result {


    private int successCount;
    private int averageCount;
    private int failCount;
    public CombinedResult() {
        super();
        this.successCount =  0;
        this.averageCount = 0;
        this.failCount = 0;
    }

    public CombinedResult(double score, AbstractCriterionQuestion question) {
        super(score, question);
    }



    public void addStateCount(double score){
        if(score == question.getMaxPoint().getScore())
            this.successCount++;
        else if (score > 0)
            this.averageCount++;
        else
            this.failCount++;
    }
    public int getFailCount() {
        return failCount;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public int getAverageCount() {
        return averageCount;
    }
}
