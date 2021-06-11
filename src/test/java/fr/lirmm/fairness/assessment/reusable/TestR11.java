package fr.lirmm.fairness.assessment.reusable;

import fr.lirmm.fairness.assessment.principles.AbstractTestPrincipleCriterion;
import fr.lirmm.fairness.assessment.principles.criterion.impl.reusable.R11;

public class TestR11 extends AbstractTestPrincipleCriterion<R11> {
	
	protected void fillExpectedValues() {
		this.expectedScores = new Integer[] { 6, 2, 0 };
		this.expectedScore = 8;
		this.expectedExplanations = new String[] {
			"License and access rights are clearly defined",
			"License is accessible and resolvable",
			"Neither ontology license nor access rights are accessible by machine"
		};
	}
}
