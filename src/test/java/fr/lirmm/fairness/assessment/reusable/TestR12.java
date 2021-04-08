package fr.lirmm.fairness.assessment.reusable;

import fr.lirmm.fairness.assessment.principles.AbstractTestPrincipleCriterion;
import fr.lirmm.fairness.assessment.principles.impl.reusable.R12;

public class TestR12 extends AbstractTestPrincipleCriterion<R12> {
	
	protected void fillExpectedValues() {
		this.expectedScores = new Integer[] { 3, 0, 6, 2, 0, 0 };
		this.expectedScore = 11;
		this.expectedExplanations = new String[] {
			"Ontology provides 1 information about its provenance",
			"Ontology provides 0 accrual information about methods/periodicity/policy",
			"Ontology provides 3 information about versioning",
			"Ontology provides 1 information about methodology and tools used to build it",
			"Ontology provides 0 information about rationale documentation",
			"Ontology provides 0 information about its funding organization",
		};
	}
}
