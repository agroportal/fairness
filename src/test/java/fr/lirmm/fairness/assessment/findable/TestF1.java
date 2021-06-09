package fr.lirmm.fairness.assessment.findable;

import fr.lirmm.fairness.assessment.principles.AbstractTestPrincipleCriterion;
import fr.lirmm.fairness.assessment.principles.impl.findable.F1;

public class TestF1 extends AbstractTestPrincipleCriterion<F1> {
	
	protected void fillExpectedValues() {
		this.expectedScores = new Integer[] { 1, 3, 3, 0, 0, 2, 2, 3 };
		this.expectedScore = 14;
		this.expectedExplanations = new String[] {
			"Valid ontology URI",
			"Resolvable ontlogy URI", 
			"Valid GUID",
			"GUID is not a DOI",
			"Metadata are not included in the ontology file",
			"Metadata are identified by a resolvable URI",
			"Valid URI version",
			"Resolvable ontology URI version"
		};
	}
}
