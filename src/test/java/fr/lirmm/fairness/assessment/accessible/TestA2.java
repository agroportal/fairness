package fr.lirmm.fairness.assessment.accessible;

import fr.lirmm.fairness.assessment.principles.AbstractTestPrincipleCriterion;
import fr.lirmm.fairness.assessment.principles.impl.accessible.A2;

public class TestA2 extends AbstractTestPrincipleCriterion<A2> {
	
	protected void fillExpectedValues() {
		this.expectedScores = new Integer[] { 0, 2, 2, 2 };
		this.expectedScore = 6;
		this.expectedExplanations = new String[] {
			"Most ontology versions are accessible",
			"Default value 2, AgroPortal makes metadata of ontology version available",
			"Default value 2, AgroPortal enables access to ontology metadata even if no more version of the ontology are available",
			"The status of the ontology is clearly informed"
		};
	}
}
