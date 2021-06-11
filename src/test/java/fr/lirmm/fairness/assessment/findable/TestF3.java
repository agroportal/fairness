package fr.lirmm.fairness.assessment.findable;

import fr.lirmm.fairness.assessment.principles.AbstractTestPrincipleCriterion;
import fr.lirmm.fairness.assessment.principles.criterion.impl.findable.F3;

public class TestF3 extends AbstractTestPrincipleCriterion<F3> {
	
	protected void fillExpectedValues() {
		this.expectedScores = new Integer[] { 0, 0, 0 };
		this.expectedScore = 0;
		this.expectedExplanations = new String[] {
			"Ontology metadata are not included in the ontology file",
			"No explicit link from metadata to the ontology",
			"Ontology metadata are not included in an external file"
		};
	}
}
