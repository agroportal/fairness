package fr.lirmm.fairness.assessment.reusable;

import fr.lirmm.fairness.assessment.principles.AbstractTestPrincipleCriterion;
import fr.lirmm.fairness.assessment.principles.impl.reusable.R1;

public class TestR1 extends AbstractTestPrincipleCriterion<R1> {
	
	protected void fillExpectedValues() {
		this.expectedScores = new Integer[] { 2, 1, 2, 0 };
		this.expectedScore = 5;
		this.expectedExplanations = new String[] {
			"",
			"Ontology provides 1 information about its HIERARCHY",
			"Ontology provides 2 information about its CLASSES",
			"Ontology provides 0 information about its PROVENANCE"
		};
	}
}
