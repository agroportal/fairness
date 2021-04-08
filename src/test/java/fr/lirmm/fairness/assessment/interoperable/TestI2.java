package fr.lirmm.fairness.assessment.interoperable;

import fr.lirmm.fairness.assessment.principles.AbstractTestPrincipleCriterion;
import fr.lirmm.fairness.assessment.principles.impl.interoperable.I2;

public class TestI2 extends AbstractTestPrincipleCriterion<I2> {
	
	protected void fillExpectedValues() {
		this.expectedScores = new Integer[] { 4, 0, 0, 0, 0, 0, 0, 0, 5 };
		this.expectedScore = 9;
		this.expectedExplanations = new String[] {
			"Ontology imports other vocabularies",
			"Ontology does not reuse other vocabularies URIS",
			"Default value 0, this indicator can not be evaluated in AgroPortal",
			"Ontology is not aligned to other vocabularies",
			"Default value 0, this indicator can not be evaluated in AgroPortal",
			"Default value 0, this indicator can not be evaluated in AgroPortal",
			"Ontology is not a view of other vocabularies",
			"Ontology does not provide influencal information",
			"Ontology does not reuse standards metadata vocabularies to describe its metadata"
		};
	}
}
