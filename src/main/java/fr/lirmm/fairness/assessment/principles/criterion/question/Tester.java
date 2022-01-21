package fr.lirmm.fairness.assessment.principles.criterion.question;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.model.Property;
import fr.lirmm.fairness.assessment.utils.QuestionResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Tester {

    public static QuestionResult doEvaluation(Ontology ontology, AbstractCriterionQuestion question, Testable test){

        test.doTest(ontology, question) ;
         if(question.getProperties()!=null){
             List<Map.Entry<String,Property>> propertiesToEvaluate =  ontology.getPropertiesMap().entrySet()
                     .stream().filter( kv -> question.getProperties().contains(kv.getValue().getModEquivalent())).collect(Collectors.toList());
             Property values = null;
             String label = "";
             for (Map.Entry<String, Property> property : propertiesToEvaluate) {
                  values =  property.getValue();
                  label = property.getValue().getModEquivalent();
                 if(values.getValue() != null && values.getValue().size() == 1) {
                     test.addTestValue(label, values.getValue().get(0).toString());
                 }else if (values.getValue() != null) {
                     test.addTestValue(label , values.getValue().toString());
                 }else {
                     test.addTestValue(label , "property not found"); //useful for debugging
                 }
             }
         }

         return test.getResult();
    }
}

