package fr.lirmm.fairness.assessment.reusable;

import fr.lirmm.fairness.assessment.principles.AbstractTestPrincipleCriterion;
import fr.lirmm.fairness.assessment.principles.criterion.impl.reusable.R13;

public class TestR13 extends AbstractTestPrincipleCriterion<R13> {
	
	protected void fillExpectedValues() {
		this.expectedScores = new Integer[] {1, 0, 0 };
		this.expectedScore = 1;
		this.expectedExplanations = new String[] {
			"Ontology is included in the OBO group/project",
			"Ontology is NOT included in the CROP group/project",
			"Ontology is NOT included in the INRAE group/project",
		};
	}
}
