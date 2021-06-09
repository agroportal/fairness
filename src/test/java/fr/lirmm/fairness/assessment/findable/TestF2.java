package fr.lirmm.fairness.assessment.findable;

import fr.lirmm.fairness.assessment.principles.AbstractTestPrincipleCriterion;
import fr.lirmm.fairness.assessment.principles.impl.findable.F2;

public class TestF2 extends AbstractTestPrincipleCriterion<F2> {
	
	protected void fillExpectedValues() {
		this.expectedScores = new Integer[] { 56, 2, 1, 10 };
		this.expectedScore = 69;
		this.expectedExplanations = new String[] {
			"14 MUST properties provided",
			"1 SHOULD properties provided",
			"1 OPTIONAL properties provided",
			"10 NO_MAPPING properties provided"
		};
	}
}
