package fr.lirmm.fairness.assessment.accessible;

import fr.lirmm.fairness.assessment.principles.AbstractTestPrincipleCriterion;
import fr.lirmm.fairness.assessment.principles.impl.accessible.A12;

public class TestA12 extends AbstractTestPrincipleCriterion<A12> {
	
	protected void fillExpectedValues() {
		this.expectedScores = new Integer[] { 8, 0 };
		this.expectedScore = 8;
		this.expectedExplanations = new String[] {
			"Ontology and ontology metadata are accessible in an ontology repository that supports authentification and authorization",
			"No access rights about ontology and metadata access and distribution is defined"
		};
	}
}
