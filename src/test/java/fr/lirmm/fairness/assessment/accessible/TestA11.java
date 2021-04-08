package fr.lirmm.fairness.assessment.accessible;

import fr.lirmm.fairness.assessment.principles.AbstractTestPrincipleCriterion;
import fr.lirmm.fairness.assessment.principles.impl.accessible.A11;

public class TestA11 extends AbstractTestPrincipleCriterion<A11> {
	
	protected void fillExpectedValues() {
		this.expectedScores = new Integer[] { 2, 1 };
		this.expectedScore = 3;
		this.expectedExplanations = new String[] {
			"",
			""
		};
	}
}
