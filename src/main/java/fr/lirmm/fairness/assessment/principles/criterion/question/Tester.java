package fr.lirmm.fairness.assessment.principles.criterion.question;

import fr.lirmm.fairness.assessment.model.Ontology;
import fr.lirmm.fairness.assessment.utils.Result;


public class Tester {

    public static Result doEvaluation(Ontology ontology, AbstractCriterionQuestion question, Testable test){
         test.doTest(ontology, question) ;
         return test.getResult();
    }
}
