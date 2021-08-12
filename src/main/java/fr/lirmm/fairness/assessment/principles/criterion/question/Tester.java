package fr.lirmm.fairness.assessment.principles.criterion.question;

import fr.lirmm.fairness.assessment.Configuration;
import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.model.Property;
import fr.lirmm.fairness.assessment.utils.QuestionResult;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Tester {

    public static QuestionResult doEvaluation(Ontology ontology, AbstractCriterionQuestion question, Testable test){

        test.doTest(ontology, question) ;
         if(question.getProperties()!=null){
             for (String property : question.getProperties()) {
                 Property values =  ontology.getProperties().values()
                         .stream().filter(x -> x.getMod().equals(property)).findFirst().orElse(null);

                 if(values!=null && values.getValue() != null && values.getValue().size() == 1)
                    test.addTestValue(property , values.getValue().get(0));
                 else if (values!= null && values.getValue()!=null) {
                     test.addTestValue(property , values.getValue().toString());
                 }
             }
         }

         return test.getResult();
    }
}

