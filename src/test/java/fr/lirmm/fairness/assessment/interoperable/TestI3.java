package fr.lirmm.fairness.assessment.interoperable;

import fr.lirmm.fairness.assessment.principles.AbstractTestPrincipleCriterion;
import fr.lirmm.fairness.assessment.principles.impl.interoperable.I3;

public class TestI3 extends AbstractTestPrincipleCriterion<I3> {
	
	protected void fillExpectedValues() {
		this.expectedScores = new Integer[] {4, 3, 1 };
		this.expectedScore = 8;
		this.expectedExplanations = new String[] {
			"Default value 0, this indicator can not be evaluated in AgroPortal",
			"Default value 0, this indicator can not be evaluated in AgroPortal",
			"Default value 0, this indicator can not be evaluated in AgroPortal"
		};
	}
}
