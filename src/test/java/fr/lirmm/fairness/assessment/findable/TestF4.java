package fr.lirmm.fairness.assessment.findable;

import fr.lirmm.fairness.assessment.principles.AbstractTestPrincipleCriterion;
import fr.lirmm.fairness.assessment.principles.criterion.impl.findable.F4;

public class TestF4 extends AbstractTestPrincipleCriterion<F4> {
	
	protected void fillExpectedValues() {
		this.expectedScores = new Integer[] { 6, 10, 2 };
		this.expectedScore = 18;
		this.expectedExplanations = new String[] {
			"Ontology is registred in 6 ontology libraries",
			"Ontology is registred in 4 open ontology libraries",
			"Ontology is registred in 1 de-facto ontology libraries"
		};
	}
}
