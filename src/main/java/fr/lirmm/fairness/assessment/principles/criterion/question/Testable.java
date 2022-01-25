package fr.lirmm.fairness.assessment.principles.criterion.question;

import fr.lirmm.fairness.assessment.models.Ontology;
import fr.lirmm.fairness.assessment.principles.criterion.question.tests.MetaDataExistTest;
import fr.lirmm.fairness.assessment.utils.QuestionResult;

import java.util.HashMap;
import java.util.Map;

public abstract class Testable {

    private final QuestionResult result = new QuestionResult();

    private final Map<String , String> propertiesValues = new HashMap<>();

    public abstract void  doTest(Ontology ontology , AbstractCriterionQuestion question);



    public void setScore(double score , String explication, AbstractCriterionQuestion question) {
        result.setResult(score , explication, getPropertiesValues(),question);
    }

    public void setScoreLevel(int index, AbstractCriterionQuestion question) {
        QuestionResult questionResult = question.getMaxPoint(index);
        setScore(questionResult.getScore() , questionResult.getExplanation()  , question);
    }

    public void setSuccess(String explication,AbstractCriterionQuestion question) {
        result.setResult(question.getMaxPoint().getScore() , explication ,getPropertiesValues(),question);
    }

    public void setSuccess(AbstractCriterionQuestion question) {
       this.setSuccess(question.getMaxPoint().getExplanation(), question);
    }

    public void setFailure(String explication  ,AbstractCriterionQuestion question){
        result.setResult(0 , explication ,getPropertiesValues() ,question);
    }

    public void setFailure(AbstractCriterionQuestion question){
        setFailure(question.getPoints().get(0).getExplanation(), question);
    }
    public QuestionResult getResult() {
        return result;
    }

   public void addTestValue(String property, String value){
        this.propertiesValues.put(property,value);
   }

    public Map<String, String> getPropertiesValues() {
        return propertiesValues;
    }

    public int countExistentMetaData(String[] props , int nbLevels){
        int count = 0;
        for (int i = 0; i <  props.length && count < nbLevels -1; i++) {
            if (MetaDataExistTest.isValid(props[i])) {
                count++;
            }
        }

        return count ;
    }
}
