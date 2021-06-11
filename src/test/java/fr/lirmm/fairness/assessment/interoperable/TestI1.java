package fr.lirmm.fairness.assessment.interoperable;

import fr.lirmm.fairness.assessment.principles.AbstractTestPrincipleCriterion;
import fr.lirmm.fairness.assessment.principles.criterion.impl.interoperable.I1;

public class TestI1 extends AbstractTestPrincipleCriterion<I1> {
	
	protected void fillExpectedValues() {
		this.expectedScores = new Integer[] { 17, 2, 1, 1, 1 };
		this.expectedScore = 22;
		this.expectedExplanations = new String[] {
			"Ontology and ontology metadata are in OWL",
			"Ontology and ontology metadata are represented in a W3C language",
			"Ontology syntax is informed",
			"Ontology formality level is informed",
			"The availability of other ontology formats is informed"
		};
	}
}
